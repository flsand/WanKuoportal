package com.example.administrator.wankuoportal.ui.address;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.Address_Adapter;
import com.example.administrator.wankuoportal.beans.Address;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.ui.shop.Shop_OrderActivity;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.util.JSONUtil;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ChooseAddressActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.TextView guanli;
    private android.widget.ListView addresslist;

    List<Address.DatasBean> beanList = new ArrayList<>();
    Address_Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_list_pop);
        this.addresslist = (ListView) findViewById(R.id.address_list);
        this.guanli = (TextView) findViewById(R.id.guanli);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //管理地址点击
        guanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ManageAdressActivity.class);
            }
        });
        mAdapter = new Address_Adapter(ChooseAddressActivity.this, beanList);
        addresslist.setAdapter(mAdapter);
        addresslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Shop_OrderActivity.addressid = beanList.get(position).getId() + "";
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryAddress();
    }

    private void queryAddress() {
        httpPost("queryAddress", new RequestParams(), Apis.queryAddress);
    }

    @Override
    public void onHttpSuccess(String type, JSONArray jsonArray, int page, int size, int count) throws Exception {
        super.onHttpSuccess(type, jsonArray, page, size, count);
        if ("queryAddress".equals(type)) {
            beanList.clear();
            beanList.addAll(JSONUtil.toJavaBeanList(Address.DatasBean.class, jsonArray));
            mAdapter.notifyDataSetChanged();
        }
    }
}
