package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.Getmyquestionrecord;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.Date;

import okhttp3.Call;

public class MyDaTiActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.widget.TextView jytzjb;
    private android.widget.TextView sctjjb;
    private TextView allpaihang;
    private TextView citypaihang;
    private TextView textView8;
    private TextView jyall;
    private TextView jyright;
    private TextView jyfault;
    private TextView textView9;
    private TextView scall;
    private TextView scright;
    private TextView scfault;
    private TextView diqu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_da_ti);
        this.diqu = (TextView) findViewById(R.id.diqu);
        this.scfault = (TextView) findViewById(R.id.sc_fault);
        this.scright = (TextView) findViewById(R.id.sc_right);
        this.scall = (TextView) findViewById(R.id.sc_all);
        this.textView9 = (TextView) findViewById(R.id.textView9);
        this.jyfault = (TextView) findViewById(R.id.jy_fault);
        this.jyright = (TextView) findViewById(R.id.jy_right);
        this.jyall = (TextView) findViewById(R.id.jy_all);
        this.textView8 = (TextView) findViewById(R.id.textView8);
        this.citypaihang = (TextView) findViewById(R.id.citypaihang);
        this.allpaihang = (TextView) findViewById(R.id.allpaihang);
        this.jytzjb = (TextView) findViewById(R.id.jytz_jb);
        this.sctjjb = (TextView) findViewById(R.id.sctj_jb);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
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
        String token = new UserService(this).gettoken();
        final String accountId = new UserService(this).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.getmyquestionrecord + "?token=" + accountId + "," + time + "," + token;
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
                        Getmyquestionrecord getmyquestionrecord = gson.fromJson(response, Getmyquestionrecord.class);
                        if (getmyquestionrecord.getCode() == 0) {
                            allpaihang.setText("" + getmyquestionrecord.getData().getRanking());
                            jyall.setText("" + getmyquestionrecord.getData().getAprizeRecordRanking().getAllNumber1());
                            jyright.setText("" + getmyquestionrecord.getData().getAprizeRecordRanking().getRightNumber1());
                            jyfault.setText("" + getmyquestionrecord.getData().getAprizeRecordRanking().getWrongNumber1());
                            scall.setText("" + getmyquestionrecord.getData().getAprizeRecordRanking().getAllNumber2());
                            scright.setText("" + getmyquestionrecord.getData().getAprizeRecordRanking().getRightNumber2());
                            scfault.setText("" + getmyquestionrecord.getData().getAprizeRecordRanking().getWrongNumber2());
                            citypaihang.setText(""+getmyquestionrecord.getData().getRankingcity());

                        }
                    }
                });
    }
}
