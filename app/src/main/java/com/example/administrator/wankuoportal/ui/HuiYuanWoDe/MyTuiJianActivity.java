package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.TuiJian_Adapter;
import com.example.administrator.wankuoportal.beans.Getmyinvitation;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.Date;

import okhttp3.Call;


/***
 * 我的推荐
 */
public class MyTuiJianActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.ListView list;
    private TextView tuijiannum;
    private TextView tuijianjb;
    private TextView datijb;
    private TextView xiaofeijb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tui_jian);
        this.xiaofeijb = (TextView) findViewById(R.id.xiaofeijb);
        this.datijb = (TextView) findViewById(R.id.datijb);
        this.tuijianjb = (TextView) findViewById(R.id.tuijianjb);
        this.tuijiannum = (TextView) findViewById(R.id.tuijiannum);
        this.list = (ListView) findViewById(R.id.list);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        String time = new Date().getTime() + "";
        String token = new UserService(MyTuiJianActivity.this).gettoken();
        String accountId = new UserService(MyTuiJianActivity.this).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.getmyinvitation + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("accountId", accountId)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        Getmyinvitation getmyinvitation = gson.fromJson(response, Getmyinvitation.class);
                        if (getmyinvitation.getCode() == 0) {
                            tuijiannum.setText(getmyinvitation.getData().getInvitationNum() + "");
                            tuijianjb.setText(getmyinvitation.getData().getInvitationGoldTotal() + "");
                            datijb.setText(getmyinvitation.getData().getPrizeGoldTotal() + "");
                            xiaofeijb.setText(getmyinvitation.getData().getConsumptionAmountTotal() + "");
                            if (getmyinvitation.getData().getList().size() > 0) {
                                TuiJian_Adapter t = new TuiJian_Adapter(getBaseContext(), getmyinvitation.getData().getList());
                                list.setAdapter(t);
                            }
                        } else if (getmyinvitation.getCode() == 2) {
                            startActivity(LoginActivity.class);
                            showShort(getmyinvitation.getMsg());
                            new UserService(MyTuiJianActivity.this).setislogin("1");
                        } else {
                            showShort(getmyinvitation.getMsg());
                        }


                    }
                });
    }
}
