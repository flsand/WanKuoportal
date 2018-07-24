package com.example.administrator.wankuoportal.fragment.help;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.wankuoportal.R;
import com.google.gson.Gson;
import com.example.administrator.wankuoportal.adapter.HelpAdapter;
import com.example.administrator.wankuoportal.beans.Gethelpcenter;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.ui.SetUp.Help_detailActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class HuiYuan_help_fragment extends BaseFragment {

    private ListView baselist;
    Gson gson = new Gson();
    Gethelpcenter gethelpcenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.base_list, container, false);//关联布局文件
        this.baselist = (ListView) rootView.findViewById(R.id.base_list);
        String url = Apis.Base + Apis.gethelpcenter1;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        gethelpcenter = gson.fromJson(response, Gethelpcenter.class);
                        if (gethelpcenter.getCode() == 0) {
                            HelpAdapter helpAdapter = new HelpAdapter(gethelpcenter, getActivity());
                            baselist.setAdapter(helpAdapter);
                        }
                    }
                });

        baselist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(Help_detailActivity.class, "title", gethelpcenter.getDatas().get(position).getHelpTypeName(),
                        "id", gethelpcenter.getDatas().get(position).getId() + "");
            }
        });
        return rootView;
    }
}
