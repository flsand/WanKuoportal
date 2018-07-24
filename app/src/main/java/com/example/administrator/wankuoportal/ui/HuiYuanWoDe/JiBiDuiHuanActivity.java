package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.Log.YZM_dialog;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Date;
import java.util.Random;

import okhttp3.Call;

public class JiBiDuiHuanActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private TextView textView5;
    private TextView gold;
    private TextView tips;
    private TextView alltx;
    private android.widget.EditText ednum;
    private TextView ok;
    double haveGoldCount = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jibiduihuan);
        this.ok = (TextView) findViewById(R.id.ok);
        this.ednum = (EditText) findViewById(R.id.ed_num);
        this.alltx = (TextView) findViewById(R.id.all_tx);
        this.tips = (TextView) findViewById(R.id.tips);
        this.gold = (TextView) findViewById(R.id.gold);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        try {
            haveGoldCount = Double.parseDouble(getIntent().getStringExtra("gold"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getIntent().getStringExtra("type").equals("jb")) {
            tips.setText("提示：当前兑换100:1 . 10000个金币起兑换");
            gold.setText(getIntent().getStringExtra("gold"));
            alltx.setText("共有金币");
            ednum.setHint("请输入要兑换的金币数");
        } else {
            tips.setText("提示：当前兑换1:100 . 100元起兑换");
            gold.setText(getIntent().getStringExtra("money"));
            alltx.setText("可用金额");
            ednum.setHint("请输入要兑换的金额");
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!TextUtils.isEmpty(ednum.getText())) {
                        double mum = Double.parseDouble(ednum.getText().toString());
                        if (getIntent().getStringExtra("type").equals("jb")) {
                            if (mum >= 10000) {
                                if (mum % 10000 == 0) {
                                    if (haveGoldCount != -1 && haveGoldCount < mum) {
                                        showShort("账户金币还不够呦，继续努力吧!");
                                    } else {
                                        sendmsg();
                                    }
                                } else {
                                    showShort("兑换数量必须为10000的倍数");
                                }
                            } else {
                                showShort("至少兑换10000枚金币");
                            }
                        } else {
                            if (Integer.valueOf(ednum.getText().toString()) >= 100) {
                                if (mum % 100 == 0) {
                                    if (haveGoldCount != -1 && haveGoldCount < mum) {
                                        showShort("账户金币还不够呦，继续努力吧!");
                                    } else {
                                        sendmsg();
                                    }
                                } else {
                                    showShort("兑换数量必须为100的倍数");
                                }
                            } else {
                                showShort("至少兑换100元");
                            }
                        }
                    } else {
                        if (getIntent().getStringExtra("type").equals("jb")) {
                            showShort("请输入要兑换的金币数");
                        } else {
                            showShort("请输入要兑换的金额");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void sendmsg() {
        final Random r = new Random();
        String str = "0123456789";//列出所有的字母数字
        String yzm = "";
        for (int i = 0; i < 4; i++)//循环4次，输出四个数
        {
            int a = r.nextInt(10);//从0-61中随机一个数，作为字符串的索引
            yzm = yzm + str.substring(a, a + 1);
        }
        L.d("yzm", yzm);

        String url = Apis.Base + Apis.send;
        final String finalYzm = yzm;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("telephone", getIntent().getStringExtra("phone"))
                .addParams("random", yzm)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        BaseResult b = gson.fromJson(response, BaseResult.class);
                        if (b.getCode() == 0) {
                            showShort(b.getMsg());

                            final YZM_dialog y = new YZM_dialog(JiBiDuiHuanActivity.this, getIntent().getStringExtra("phone"));
                            y.show();
                            y.setClicklistener(new YZM_dialog.ClickListenerInterface() {

                                @Override
                                public void doConfirm(String edit) {

                                    if (edit.isEmpty()) {
                                        showShort("请输入验证码");
                                    } else {
                                        if (edit.equals(finalYzm)) {
                                            y.dismiss();
                                            if (getIntent().getStringExtra("type").equals("jb")) {
                                                changeGold();
                                            } else {
                                                changeMoney();
                                            }
                                        } else {
                                            showShort("验证码错误");
                                            y.dismiss();
                                        }

                                    }

                                }

                                @Override
                                public void doCancel() {
                                    y.dismiss();
                                }

                                @Override
                                public void doClose() {
                                    y.dismiss();
                                }
                            });

                        } else {
                            showShort(b.getMsg());
                        }

                    }

                });
    }

    //兑换金币成余额
    private void changeGold() {
        String time = new Date().getTime() + "";
        String token = new UserService(MyApplication.context).gettoken();
        String accountId = new UserService(MyApplication.context).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.towallet + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("accountId", accountId)
                .addParams("cnt", ednum.getText().toString())
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        BaseResult s = gson.fromJson(response, BaseResult.class);
                        if (s.getCode() == 0) {
                            showShort(s.getMsg());
                            finish();
                        } else {
                            showShort(s.getMsg());
                        }
                    }
                });

    }

    //兑换余额成金币
    private void changeMoney() {
        String time = new Date().getTime() + "";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.togold + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("accountId", accountId)
                .addParams("amount", ednum.getText().toString())
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        BaseResult s = gson.fromJson(response, BaseResult.class);
                        if (s.getCode() == 0) {
                            showShort(s.getMsg());
                            finish();
                        } else {
                            showShort(s.getMsg());
                        }
                    }
                });
    }
}
