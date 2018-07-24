package com.example.administrator.wankuoportal.fragment.store;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.DianPuZiXun_6_Adapter;
import com.example.administrator.wankuoportal.beans.ShopInformation;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.ZiXunDetialActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class Store_6_fragment extends BaseFragment {

    private ListView baselist;
    private ImageView cityima;
    private ListView list;
    ShopInformation s;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.store_fragment6, container, false);//关联布局文件
        this.list = (ListView) rootView.findViewById(R.id.list);
        List<String> title = new ArrayList<>();
        title.add("开店赚钱");
        title.add("广告投放");
        title.add("万哥跑腿");
        title.add("城市合作");
        title.add("开店赚钱");
        title.add("广告投放");
        title.add("万哥跑腿");
        title.add("城市合作");

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(ZiXunDetialActivity.class,"id",s.getDatas().get(position).getId()+"");
            }
        });
        String shopDataId = new UserService(getActivity()).getstoreid();
        String url = Apis.Base + Apis.information;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("shopDataId", shopDataId)
                .addParams("page", "1")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        s = gson.fromJson(response, ShopInformation.class);

                        if (s.getCode() == 0) {
                            DianPuZiXun_6_Adapter d = new DianPuZiXun_6_Adapter(getContext(), s.getDatas());
                            list.setAdapter(d);
                        } else {
                            showShort(s.getMsg());
                        }
                    }
                });
        return rootView;
    }
}
