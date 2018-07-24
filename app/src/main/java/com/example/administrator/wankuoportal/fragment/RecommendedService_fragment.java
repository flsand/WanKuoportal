package com.example.administrator.wankuoportal.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.ServiceAdapter;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.SearchFuWu;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/16 0016.推荐服务
 */

public class RecommendedService_fragment extends BaseFragment {

    private android.widget.ListView servicelist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sercice_layout, container, false);//关联布局文件
        this.servicelist = (ListView) rootView.findViewById(R.id.service_list);

        String url = Apis.Base + Apis.searchserve;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("content", "")
                .addParams("type",  "0")
                .addParams("shoptype",  "0")
                .addParams("level",  "0")
                .addParams("page",  "1")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {

                        L.d(response);
                        SearchFuWu searchFuWu = gson.fromJson(response, SearchFuWu.class);
                        if (searchFuWu.getCode() == 0) {
                            ServiceAdapter serviceAdapter = new ServiceAdapter(searchFuWu.getDatas(), MyApplication.context);
                            servicelist.setAdapter(serviceAdapter);
                        }

                    }
                });
        return rootView;
    }
}
