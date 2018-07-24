package com.example.administrator.wankuoportal.ui.SetUp;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.Regist;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.address.ManageAdressActivity;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.ui.denglu.Personal_SetupActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * 个人资料
 */

public class PersonalDataActivity extends BaseActivity {

    private android.widget.LinearLayout changehead;
    private android.widget.TextView nicknametx;
    private android.widget.LinearLayout changenickname;
    private android.widget.TextView nametx;
    private android.widget.LinearLayout changename;
    private android.widget.TextView sextx;
    private android.widget.LinearLayout changesex;
    private android.widget.TextView timetx;
    private android.widget.LinearLayout changetime;
    private android.widget.TextView areatx;
    private android.widget.LinearLayout changeArea;
    private android.widget.TextView occupationtx;
    private android.widget.LinearLayout changeOccupation;
    private android.widget.TextView phonetx;
    private android.widget.LinearLayout changephone;
    private android.widget.TextView signtx;
    private android.widget.LinearLayout changesign;
    private android.widget.LinearLayout changeIdentity;
    private android.widget.LinearLayout changeadress;
    private android.widget.ImageView back;
    private TextView title;
    private de.hdodenhof.circleimageview.CircleImageView ima;
    Regist.DataBean.AaccountInfoBean aAccountInfo;
    private TextView change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        this.change = (TextView) findViewById(R.id.change);
        this.ima = (CircleImageView) findViewById(R.id.ima);
        this.changeadress = (LinearLayout) findViewById(R.id.change_adress);
        this.changeIdentity = (LinearLayout) findViewById(R.id.change_Identity);
        this.changesign = (LinearLayout) findViewById(R.id.change_sign);
        this.signtx = (TextView) findViewById(R.id.sign_tx);
        this.changephone = (LinearLayout) findViewById(R.id.change_phone);
        this.phonetx = (TextView) findViewById(R.id.phone_tx);
        this.changeOccupation = (LinearLayout) findViewById(R.id.change_Occupation);
        this.occupationtx = (TextView) findViewById(R.id.occupation_tx);
        this.changeArea = (LinearLayout) findViewById(R.id.change_Area);
        this.areatx = (TextView) findViewById(R.id.area_tx);
        this.changetime = (LinearLayout) findViewById(R.id.change_time);
        this.timetx = (TextView) findViewById(R.id.time_tx);
        this.changesex = (LinearLayout) findViewById(R.id.change_sex);
        this.sextx = (TextView) findViewById(R.id.sex_tx);
        this.changename = (LinearLayout) findViewById(R.id.change_name);
        this.nametx = (TextView) findViewById(R.id.name_tx);
        this.changenickname = (LinearLayout) findViewById(R.id.change_nickname);
        this.nicknametx = (TextView) findViewById(R.id.nickname_tx);
        this.changehead = (LinearLayout) findViewById(R.id.change_head);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);


        changeadress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ManageAdressActivity.class);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initview();
    }

    private void initview() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Personal_SetupActivity.class);
            }
        });
        aAccountInfo = new Regist.DataBean.AaccountInfoBean();
        String time = new Date().getTime() + "";
        String token = new UserService(MyApplication.context).gettoken();
        String accountId = new UserService(MyApplication.context).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.getuserinfo ;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("token", accountId + "," + time + "," + token)
                .addParams("accountId", accountId)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        Regist regist = gson.fromJson(response,Regist.class);
                        if (regist.getCode()==0){
                            aAccountInfo = regist.getData().getAaccountInfo();
                            nicknametx.setText(regist.getData().getAaccountInfo().getNickname());
                            nametx.setText(regist.getData().getAaccountInfo().getName());
                            if (regist.getData().getAaccountInfo().getSex()==1){
                                sextx.setText("男");
                            }else if (regist.getData().getAaccountInfo().getSex()==2){
                                sextx.setText("女");
                            }else {

                            }
                            timetx.setText(regist.getData().getAaccountInfo().getBirthDate());
                            areatx.setText(aAccountInfo.getProvince()+aAccountInfo.getCity()+aAccountInfo.getArea());
                            occupationtx.setText(aAccountInfo.getOccupationName());
                            phonetx.setText(aAccountInfo.getPhone());
                            signtx.setText(aAccountInfo.getAutograph());

                            if (aAccountInfo.getHeadIcon().isEmpty()){
                                Glide.with(PersonalDataActivity.this).load(R.drawable.tou).into(ima);
                            }else {
                                String path = Apis.Baseima+ aAccountInfo.getHeadIcon();
                                Glide.with(PersonalDataActivity.this).load(path).into(ima);
                            }


                        }else if (regist.getCode()==2){
                            new UserService(MyApplication.context).setislogin("1");
                            startActivity(LoginActivity.class);
                        }else {
                            showShort(regist.getMsg());
                        }


                    }
                });
    }

}
