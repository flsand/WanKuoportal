package com.example.administrator.wankuoportal.fragment.store;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.DianPuPingJia_Adapter;
import com.example.administrator.wankuoportal.global.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class Store_7_fragment extends BaseFragment {


    private android.widget.TextView hao;
    private android.widget.TextView zhong;
    private android.widget.TextView cha;
    private android.widget.LinearLayout pingjiaLin;
    private ListView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.store_fragment7, container, false);//关联布局文件
        this.list = (ListView) rootView.findViewById(R.id.list);
        this.pingjiaLin = (LinearLayout) rootView.findViewById(R.id.pingjia_Lin);
        this.cha = (TextView) rootView.findViewById(R.id.cha);
        this.zhong = (TextView) rootView.findViewById(R.id.zhong);
        this.hao = (TextView) rootView.findViewById(R.id.hao);
        cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initpingjiatab();
                cha.setBackgroundResource(R.drawable.bg_hong);
                List<String> title = new ArrayList<>();
                title.add("开店赚钱");
                title.add("广告投放");
                title.add("万哥跑腿");
                title.add("城市合作");
                title.add("开店赚钱");
                DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(getContext(), title);
                list.setAdapter(d);
            }
        });

        zhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initpingjiatab();
                zhong.setBackgroundResource(R.drawable.bg_hong);
                List<String> title = new ArrayList<>();
                title.add("开店赚钱");
                title.add("广告投放");
                title.add("万哥跑腿");
                title.add("开店赚钱");
                DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(getContext(), title);
                list.setAdapter(d);

            }
        });
        hao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initpingjiatab();
                hao.setBackgroundResource(R.drawable.bg_hong);
                List<String> title = new ArrayList<>();
                title.add("开店赚钱");
                title.add("万哥跑腿");
                title.add("城市合作");
                DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(getContext(), title);
                list.setAdapter(d);
            }
        });

        initpingjiatab();
        hao.setBackgroundResource(R.drawable.bg_hong);
        List<String> title = new ArrayList<>();
        title.add("开店赚钱");
        title.add("广告投放");
        title.add("万哥跑腿");
        title.add("城市合作");
        title.add("开店赚钱");
        title.add("广告投放");
        title.add("万哥跑腿");
        title.add("城市合作");
        DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(getContext(), title);
        list.setAdapter(d);

        return rootView;
    }

    private void initpingjiatab() {
        zhong.setBackgroundResource(R.color.white);
        hao.setBackgroundResource(R.color.white);
        cha.setBackgroundResource(R.color.white);
    }
}
