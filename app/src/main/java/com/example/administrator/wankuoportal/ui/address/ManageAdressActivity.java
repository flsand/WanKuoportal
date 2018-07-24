package com.example.administrator.wankuoportal.ui.address;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.AddressAdapter;
import com.example.administrator.wankuoportal.beans.Address;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.ui.SetUp.AddAddressActivity;
import com.example.administrator.wankuoportal.ui.shop.Shop_OrderActivity;
import com.flysand.mylibrary.customView.DialogUtil;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.util.JSONUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManageAdressActivity extends BaseActivity implements AddressAdapter.InnerItemOnclickListener {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.widget.ListView adresslist;
    private TextView addaddress;

    List<Address.DatasBean> beanList = new ArrayList<>();
    AddressAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_address);
        this.addaddress = (TextView) findViewById(R.id.addaddress);
        this.adresslist = (ListView) findViewById(R.id.adress_list);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //添加地址点击
        addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddAddressActivity.class);
            }
        });

        mAdapter = new AddressAdapter(ManageAdressActivity.this, beanList);
        adresslist.setAdapter(mAdapter);
        mAdapter.setOnInnerItemOnClickListener(ManageAdressActivity.this);
    }

    @Override
    public void itemClick(View v) {
        int position = (Integer) v.getTag();
        switch (v.getId()) {
            case R.id.address_del:
                //删除地址
                DialogUtil dialogUtil = new DialogUtil(this) {
                    @Override
                    public void confirmClick(View view) {
                        httpPost("deladdress", new RequestParams("id", beanList.get(position).getId()), Apis.deladdress);
                    }
                }.setConfirmBtnText("删除").setContent("确认删除？");
                dialogUtil.showDialog(2, 0);
                break;
            case R.id.address_set:
                // 修改地址
                Intent it = new Intent(ManageAdressActivity.this, ChangeAddressActivity.class);
                it.putExtra("data", beanList.get(position));
                startActivity(it);
                break;
            case R.id.sehzhimoren:
                httpPost("defaultAddress", new RequestParams("id", beanList.get(position).getId()), Apis.defaultAddress);
                break;
        }
    }

    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) throws Exception {
        super.onHttpSuccess(type, jsonObject);
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

    @Override
    public boolean onHttpAnalysisIntercept(String type, JSONObject jsonObject) throws Exception {
        if ("defaultAddress".equals(type)) {
            Shop_OrderActivity.addressid = "";
            queryAddress();
            return true;
        } else if ("deladdress".equals(type)) {
            queryAddress();
            showShort(jsonObject.getString("msg"));
            return true;
        }
        return super.onHttpAnalysisIntercept(type, jsonObject);
    }

    @Override
    protected void onResume() {
        queryAddress();
        super.onResume();
    }

    private void queryAddress() {
        httpPost("queryAddress", new RequestParams(), Apis.queryAddress);
    }
}
