package com.example.administrator.wankuoportal.fragment.gzpingjia;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.GZjiaoyipPingjia_Adapter;
import com.example.administrator.wankuoportal.global.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16 0016.我对服务商对的评价
 */

public class PingJiaGZToFW_fragment extends BaseFragment {

    private ListView baselist;
    private android.widget.TextView hao;
    private android.widget.TextView zhong;
    private android.widget.TextView cha;
    private android.widget.LinearLayout pingjialin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.guzhu_list, container, false);//关联布局文件
        this.pingjialin = (LinearLayout) rootView.findViewById(R.id.pingjia_lin);
        this.cha = (TextView) rootView.findViewById(R.id.cha);
        this.zhong = (TextView) rootView.findViewById(R.id.zhong);
        this.hao = (TextView) rootView.findViewById(R.id.hao);
        this.baselist = (ListView) rootView.findViewById(R.id.base_list);
        pingjialin.setVisibility(View.VISIBLE);
        hao.setBackgroundResource(R.drawable.bg_hong);
        List<String> title = new ArrayList<>();
        GZjiaoyipPingjia_Adapter adapter = new GZjiaoyipPingjia_Adapter(getActivity(), title);
        baselist.setAdapter(adapter);
        cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initpingjiatab();
                cha.setBackgroundResource(R.drawable.bg_hong);
                List<String> title = new ArrayList<>();
                GZjiaoyipPingjia_Adapter adapter = new GZjiaoyipPingjia_Adapter(getActivity(), title);
                baselist.setAdapter(adapter);
            }
        });

        zhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initpingjiatab();
                zhong.setBackgroundResource(R.drawable.bg_hong);
                List<String> title = new ArrayList<>();
                GZjiaoyipPingjia_Adapter adapter = new GZjiaoyipPingjia_Adapter(getActivity(), title);
                baselist.setAdapter(adapter);

            }
        });
        hao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initpingjiatab();
                hao.setBackgroundResource(R.drawable.bg_hong);
                List<String> title = new ArrayList<>();

                GZjiaoyipPingjia_Adapter adapter = new GZjiaoyipPingjia_Adapter(getActivity(), title);
                baselist.setAdapter(adapter);
            }
        });
        return rootView;
    }
    private void initpingjiatab() {
        zhong.setBackgroundResource(R.color.white);
        hao.setBackgroundResource(R.color.white);
        cha.setBackgroundResource(R.color.white);
    }
}
