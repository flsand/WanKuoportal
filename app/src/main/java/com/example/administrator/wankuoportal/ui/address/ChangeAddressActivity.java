package com.example.administrator.wankuoportal.ui.address;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.AMailingAddress;
import com.example.administrator.wankuoportal.beans.Address;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.AddressPickTask;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.example.administrator.wankuoportal.util.Utils;
import com.flysand.mylibrary.http.RequestParams;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import okhttp3.MediaType;

public class ChangeAddressActivity extends BaseActivity {

    private ImageView back;
    private TextView informationtitle;
    private EditText edname;
    private EditText edphone;
    private TextView txdiqu;
    private LinearLayout diqulin;
    private EditText edxiaoqu;
    private EditText edmenpai;
    private EditText edxiangxi;
    private TextView ok;
    AMailingAddress address;
    Address.DatasBean datasBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        this.ok = (TextView) findViewById(R.id.ok);
        this.edxiangxi = (EditText) findViewById(R.id.ed_xiangxi);
        this.edmenpai = (EditText) findViewById(R.id.ed_menpai);
        this.edxiaoqu = (EditText) findViewById(R.id.ed_xiaoqu);
        this.diqulin = (LinearLayout) findViewById(R.id.diqu_lin);
        this.txdiqu = (TextView) findViewById(R.id.tx_diqu);
        this.edphone = (EditText) findViewById(R.id.ed_phone);
        this.edname = (EditText) findViewById(R.id.ed_name);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        address = new AMailingAddress();
        Intent it = getIntent();
        datasBean = (Address.DatasBean) it.getSerializableExtra("data");
        L.d(datasBean.toString());
        edname.setText(datasBean.getName());
        edphone.setText(datasBean.getPhone());
        txdiqu.setText(datasBean.getProvince()+"-"+datasBean.getCity()+"-"+datasBean.getArea());
        edxiangxi.setText(datasBean.getAddress());



        address.setAddress(datasBean.getAddress());
        address.setProvince(datasBean.getProvince());
        address.setCity(datasBean.getCity());
        address.setPhone(datasBean.getPhone());
        address.setId(datasBean.getId());
        address.setArea(datasBean.getArea());
        address.setDefaultFlay(datasBean.getDefaultFlay());
        address.setCreateTime(datasBean.getCreateTime());

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAddress();
            }
        });


        diqulin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddressPicker();
            }
        });


    }


    public void onAddressPicker() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                showShort("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
                    address.setCity(city.getName());
                    address.setArea("");
                    address.setProvince(province.getName());
                    txdiqu.setText(province.getAreaName() + "-" + city.getAreaName());
                } else {
                    address.setCity(city.getName());
                    address.setArea(county.getName());
                    address.setProvince(province.getName());
                    txdiqu.setText(province.getAreaName() + "-" + city.getAreaName() + "-" + county.getAreaName());

                }
            }
        });
        task.execute("山东", "烟台", "福山");
    }

    private void addAddress() {
        if (!TextUtils.isEmpty(edname.getText())) {
            if (!TextUtils.isEmpty(edphone.getText())) {
//                if (!TextUtils.isEmpty(edxiaoqu.getText())){
//                    if (!TextUtils.isEmpty(edmenpai.getText())){
                if (!TextUtils.isEmpty(edxiangxi.getText())) {
                    if (edxiangxi.getText().length() >= 5) {

                        address.setName(edname.getText().toString());
                        address.setPhone(edphone.getText().toString());
                        address.setAddress(edxiangxi.getText().toString());
                        changeAddressPost();
                    } else {
                        showShort("详细地址请输入至少五个字");
                    }
                } else {
                    showShort("请输入详细地址");
                }
//                    }else {
//                        showShort("请输入门牌号");
//                    }
//                }else {
//                    showShort("请填写小区名称");
//                }
            } else {
                showShort("请输入联系人电话");
            }
        } else {
            showShort("请输入联系人姓名");
        }

    }


    private void changeAddressPost() {
        try {
            String time = new Date().getTime() + "";
            String token = new UserService(MyApplication.context).gettoken();
            token = MD5Util.md5(time + token);
            String accountId = new UserService(MyApplication.context).getaccountid();
            String url = Apis.addOrUpdateAddress + "?token=" + accountId + "," + time + "," + token;
            address.setAccountId(Integer.valueOf(accountId));
            address.setDefaultFlay(1);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", new Gson().toJson(address));
            Utils.print("Gson =" + new Gson().toJson(address));
            Utils.print("json =" + jsonObject.toString());
            RequestParams params = new RequestParams();
            params.setMediaType(MediaType.parse("application/json; charset=utf-8"));
            params.setContent(new Gson().toJson(address));
            httpPost("addAddress", new RequestParams(jsonObject), url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onHttpAnalysisIntercept(String type, JSONObject body) throws Exception{
        dismissHttpDialog();
        if ("addAddress".equals(type)){
            showShort(body.getString("msg"));
            finish();
            return true;
        }
        return super.onHttpAnalysisIntercept(type, body);
    }
}
