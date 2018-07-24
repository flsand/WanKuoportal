package com.example.administrator.wankuoportal.ui.SetUp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.Date;

import okhttp3.Call;

public class YiJianFanKuiActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.EditText yijian;
    private android.widget.TextView zishu;
    private android.widget.EditText dianhua;
    private TextView tijiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_jian_fan_kui);
        this.tijiao = (TextView) findViewById(R.id.tijiao);
        this.dianhua = (EditText) findViewById(R.id.dianhua);
        this.zishu = (TextView) findViewById(R.id.zishu);
        this.yijian = (EditText) findViewById(R.id.yijian);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        yijian.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                zishu.setText(s.length() + "/240");
            }
        });


        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(yijian.getText())){
                    showShort("请输入您的建议!");
                }else {
                    if (TextUtils.isEmpty(dianhua.getText())){
                        lianxifangshi = "";
                    }else {
                        lianxifangshi = dianhua.getText().toString();
                    }
                    tijiaojianyi();
                }

            }
        });
    }

    String lianxifangshi = "";
    private void tijiaojianyi() {
        String time = new Date().getTime()+"";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
        token = MD5Util.md5(time+token);
        String url = Apis.Base + Apis.savefeedback+"?token=" +accountId+","+time+","+token;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("accountId", accountId)
                .addParams("content", yijian.getText().toString())
                .addParams("phone", lianxifangshi)
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
//                            showShort(s.getMsg());
                            Toast.makeText(MyApplication.context,s.getMsg(),Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            showShort(s.getMsg());
                        }
                    }
                });
    }
}
