package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;

public class ShenFenRenZhengActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.widget.EditText edname;
    private android.widget.EditText ednumber;
    private TextView queding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shen_fen_ren_zheng);
        this.queding = (TextView) findViewById(R.id.queding);
        this.ednumber = (EditText) findViewById(R.id.ed_number);
        this.edname = (EditText) findViewById(R.id.ed_name);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edname.getText())){
                    showShort("请输入您的真实姓名");
                }else {
                    if (TextUtils.isEmpty(ednumber.getText())){
                        showShort("请输入身份证号");
                    }else {
                        if (!checkNum(ednumber.getText().toString())) {
                            showShort("请输入正确的身份证号");
                        }else {
                            String time = new Date().getTime()+"";
                            String token = new UserService(getBaseContext()).gettoken();
                            String accountId = new UserService(getBaseContext()).getaccountid();
                            token = MD5Util.md5(time+token);
                            String url = Apis.Base + Apis.saveidnumber+"?token=" +accountId+","+time+","+token;
                            OkHttpUtils
                                    .get()
                                    .url(url)
                                    .addParams("accountId", accountId)
                                    .addParams("name", edname.getText().toString())
                                    .addParams("idnumber", ednumber.getText().toString())
                                    .build()
                                    .execute(new MyStringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {

                                        }

                                        @Override
                                        public void onHttpResponse(String response, int id) throws Exception {
                                            L.d(response);
                                            BaseResult s = gson.fromJson(response,BaseResult.class);
                                            if (s.getCode()==0){
                                                showShort(s.getMsg());
                                                finish();
                                            }else {
                                                showShort(s.getMsg());
                                            }
                                        }
                                    });
                        }
                    }
                }
            }
        });

    }

    //身份证号码验证
    public boolean checkNum(String text) {
        Pattern patternSfzhm1 = Pattern
                .compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
        Pattern patternSfzhm2 = Pattern
                .compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
        Matcher matcherSfzhm1 = patternSfzhm1.matcher(text);
        Matcher matcherSfzhm2 = patternSfzhm2.matcher(text);
        if(!matcherSfzhm1.find() && !matcherSfzhm2.find())
            return false;
        else return true;
    }
}
