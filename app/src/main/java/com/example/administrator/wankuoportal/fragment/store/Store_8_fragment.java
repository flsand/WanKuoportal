package com.example.administrator.wankuoportal.fragment.store;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.Store_ZhaoPin_Adapter;
import com.example.administrator.wankuoportal.global.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv on 2017/9/18 .
 */

public class Store_8_fragment extends BaseFragment {


    private ListView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.store_fragment8, container, false);//关联布局文件
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
        Store_ZhaoPin_Adapter store_zhaoPin_adapter = new Store_ZhaoPin_Adapter(getActivity(),title);
        list.setAdapter(store_zhaoPin_adapter);
        return rootView;
    }
}
