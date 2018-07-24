package com.example.administrator.wankuoportal.fragment.order;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.OrderGuZhu_Adapter;
import com.example.administrator.wankuoportal.global.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16 0016.会员所有订单
 */

public class OrderGZ1_fragment extends BaseFragment {

    private ListView baselist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.guzhu_list, container, false);//关联布局文件
        this.baselist = (ListView) rootView.findViewById(R.id.base_list);
        List<String> title = new ArrayList<>();
        title.add("开店赚钱");
        title.add("广告投放");
        title.add("万哥跑腿");
        title.add("城市合作");
        title.add("开店赚钱");
        title.add("广告投放");
        title.add("万哥跑腿");
        title.add("城市合作");
        OrderGuZhu_Adapter adapter = new OrderGuZhu_Adapter(getActivity(),title);
        baselist.setAdapter(adapter);
        return rootView;
    }
}
