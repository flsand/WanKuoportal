package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.Log.YZM_dialog;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.beans.Regist;
import com.example.administrator.wankuoportal.beans.Signin;
import com.example.administrator.wankuoportal.beans.YHK;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.SetUp.ChooseYHKActivity;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.flysand.mylibrary.customView.AlwaysMarqueeTextView;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.util.MyHandler;
import com.flysand.mylibrary.util.Utils;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

import okhttp3.Call;

public class TiXianActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.widget.LinearLayout chooseaddress;
    private TextView ok;
    private AlwaysMarqueeTextView tipMoney;
    private AlwaysMarqueeTextView tipFree;
    YHK yhk;
    private ImageView yhkima;
    private TextView yhkname;
    private TextView yhkhao;
    public static String bankcardId = "";
    private android.widget.EditText tixiannum;
    double totalMoney = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_xian);
        this.tixiannum = (EditText) findViewById(R.id.tixiannum);
        this.yhkhao = (TextView) findViewById(R.id.yhkhao);
        this.yhkname = (TextView) findViewById(R.id.yhkname);
        this.yhkima = (ImageView) findViewById(R.id.yhkima);
        this.tipMoney = findViewById(R.id.tip_money_tv);
        this.tipFree = findViewById(R.id.tip_free_tv);
        this.ok = (TextView) findViewById(R.id.ok);
        this.chooseaddress = (LinearLayout) findViewById(R.id.choose_address);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        updateTipFee("0", "0");
        updateMoney("0", "0");
        //选择地址
        chooseaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ChooseYHKActivity.class);
            }
        });
        //确定提现
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tixiannum.getText().toString();
                if (!TextUtils.isEmpty(text)) {

                    try {
                        double money = Double.parseDouble(text);
                        if (money % 100 == 0) {
                            if (!bankcardId.isEmpty()) {
                                checkWithdraw();
                            } else {
                                showShort("请选择要提现到的银行卡");
                            }
                        } else {
                            showShort("提现必须是100的倍数");
                        }
                    } catch (Exception E) {
                        E.printStackTrace();
                    }

                } else {
                    showShort("请输入要提现的金额");
                }

            }
        });
        String time = new Date().getTime() + "";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.getbankcard + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("accountId", accountId)
                .build()
                .execute(new MyStringCallback() {

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        yhk = gson.fromJson(response, YHK.class);
                        if (yhk.getCode() == 0) {
                            if (yhk.getDatas().size() == 0) {
                                yhkname.setText("请添加银行卡");
                            } else {
                                bankcardId = yhk.getDatas().get(0).getId() + "";
                                switch (yhk.getDatas().get(0).getBankType()) {
                                    case 1:
                                        yhkname.setText("中国工商银行");
                                        Glide.with(TiXianActivity.this).load(R.drawable.gongshang).into(yhkima);
                                        break;
                                    case 2:
                                        yhkname.setText("中国农业银行");
                                        Glide.with(TiXianActivity.this).load(R.drawable.nongye).into(yhkima);
                                        break;
                                    case 3:
                                        yhkname.setText("中国银行");
                                        Glide.with(TiXianActivity.this).load(R.drawable.zhongguo).into(yhkima);
                                        break;
                                    case 4:
                                        yhkname.setText("中国建设银行");
                                        Glide.with(TiXianActivity.this).load(R.drawable.jianshe).into(yhkima);
                                        break;
                                    case 5:
                                        yhkname.setText("中国交通银行");
                                        Glide.with(TiXianActivity.this).load(R.drawable.jiaotong).into(yhkima);
                                        break;
                                    default:
                                        break;

                                }

                                yhkhao.setText("尾号" + yhk.getDatas().get(0).getBankCardNo().substring(yhk.getDatas().get(0).getBankCardNo().length() - 4) + "储蓄卡");

                            }

                        } else {

                        }
                    }
                });

        tixiannum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    String str = s.toString();
                    if (str.startsWith("00")) {
                        tixiannum.setText("0");
                        tixiannum.setSelection(1);
                    }
                    if (str.startsWith("0") & str.length() > 1) {
                        tixiannum.setText(str.substring(1, str.length()));
                        tixiannum.setSelection(1);
                    }
                    double num = Double.parseDouble(str);
                    if (num > totalMoney) {
                        tixiannum.setText(Utils.replaceDoubleZero(totalMoney + ""));
                        tixiannum.setSelection(tixiannum.getText().length());
                        return;
                    }
                    calculateFee(num);

                } catch (Exception e) {
                    e.printStackTrace();
                    calculateFee(0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * @param totalMoney 总金额
     * @param carry      可提现
     */
    private void updateMoney(String totalMoney, String carry) {
        String text = "<customfont size='14sp' color='#%1$x'>总金额%2$s元&nbsp;&nbsp;可提现</customfont>"
                + "<customfont size='14sp' color='#%3$x'>%4$s元</customfont>";
        tipMoney.setHtmlText(String.format(text, getResources().getColor(R.color.main_text_color), totalMoney, getResources().getColor(R.color.text_orange), carry), this);
    }

    /**
     * @param fee    技术服务费
     * @param actual 实际到账
     */
    private void updateTipFee(String fee, String actual) {
        String text = "<customfont size='14sp' color='#%1$x'>技术服务费%2$s元&nbsp;&nbsp;</customfont>"
                + "<customfont size='14sp' color='#%3$x'>实际到账%4$s元</customfont>";
        tipFree.setHtmlText(String.format(text, getResources().getColor(R.color.text_orange), fee, getResources().getColor(R.color.main_text_color), actual), this);
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
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        BaseResult b = gson.fromJson(response, BaseResult.class);
                        if (b.getCode() == 0) {
                            showShort(b.getMsg());

                            final YZM_dialog y = new YZM_dialog(TiXianActivity.this, getIntent().getStringExtra("phone"));
                            y.show();
                            y.setClicklistener(new YZM_dialog.ClickListenerInterface() {

                                @Override
                                public void doConfirm(String edit) {

                                    if (edit.isEmpty()) {
                                        showShort("请输入验证码");
                                    } else {
                                        if (edit.equals(finalYzm)) {
                                            y.dismiss();
                                            tixian();
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

    /**
     * 验证 提现
     */
    private void checkWithdraw() {

        RequestParams params = new RequestParams();
        params.put("amount", tixiannum.getText().toString());
        params.put("bankcardId", bankcardId);
        httpGet("checkWithdraw", params, Apis.checkWithdraw);
    }

    @Override
    public boolean onHttpAnalysisIntercept(String type, JSONObject jsonObject) throws Exception {
        if ("checkWithdraw".equals(type)) {
            dismissHttpDialog();
            if ("0".equals(jsonObject.getString("code"))) {
                if (Utils.isApkDebugable(getApplicationContext(), getPackageName())) {
                    tixian();
                } else {
                    sendmsg();
                }
            } else {
                showShort(jsonObject.getString("msg"));
            }
            return true;
        }
        return super.onHttpAnalysisIntercept(type, jsonObject);

    }

    private void tixian() {


        String time = new Date().getTime() + "";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.withdrawals + "?token=" + accountId + "," + time + "," + token;

        OkHttpUtils
                .get()
                .url(url)
                .addParams("accountId", accountId)
                .addParams("amount", tixiannum.getText().toString())
                .addParams("bankcardId", bankcardId)
                .addParams("withdrawType", Constant.WithdrawType.CARD)
                .build()
                .execute(new MyStringCallback() {

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        Signin s = gson.fromJson(response, Signin.class);
                        if (s.getCode() == 0) {
                            showShort(s.getMsg());
                            finish();
                        } else {
                            showShort(s.getMsg());
                        }
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (yhk != null) {
            for (int i = 0; i < yhk.getDatas().size(); i++) {
                if (bankcardId.equals(yhk.getDatas().get(i).getId() + "")) {

                    switch (yhk.getDatas().get(i).getBankType()) {
                        case 1:
                            yhkname.setText("中国工商银行");
                            Glide.with(TiXianActivity.this).load(R.drawable.gongshang).into(yhkima);
                            break;
                        case 2:
                            yhkname.setText("中国农业银行");
                            Glide.with(TiXianActivity.this).load(R.drawable.nongye).into(yhkima);
                            break;
                        case 3:
                            yhkname.setText("中国银行");
                            Glide.with(TiXianActivity.this).load(R.drawable.zhongguo).into(yhkima);
                            break;
                        case 4:
                            yhkname.setText("中国建设银行");
                            Glide.with(TiXianActivity.this).load(R.drawable.jianshe).into(yhkima);
                            break;
                        case 5:
                            yhkname.setText("中国交通银行");
                            Glide.with(TiXianActivity.this).load(R.drawable.jiaotong).into(yhkima);
                            break;
                        default:
                            break;

                    }

                    yhkhao.setText("尾号" + yhk.getDatas().get(i).getBankCardNo().substring(yhk.getDatas().get(i).getBankCardNo().length() - 4) + "储蓄卡");

                    break;
                }
            }

        }
        String time = new Date().getTime() + "";
        String token = new UserService(this).gettoken();
        String accountId = new UserService(this).getaccountid();
        token = MD5Util.md5(time + token);

        String url = Apis.Base + Apis.getuserinfo + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("accountId", accountId)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        try {

                            Regist regist = gson.fromJson(response, Regist.class);

                            if (regist.getCode() == 0) {
                                totalMoney = Double.parseDouble(regist.getData().getAaccountAuthorize().getMoney());// + Double.parseDouble(g.getData().getNolimitGold());
                                updateMoney(Utils.replaceDoubleZero(totalMoney + ""), Utils.replaceDoubleZero(totalMoney + ""));
//                                tixiannum.setText(totalMoney + 1 + "");
                                calculateFee(0);
                            } else if (regist.getCode() == 2) {
                                startActivity(LoginActivity.class);
                                new UserService(MyApplication.context).setislogin("1");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        super.onError(call, e, id);
                        new MyHandler(1000) {
                            @Override
                            public void run() {
                                finish();
                            }
                        };

                    }
                });
    }

    /**
     * 计算手续费
     */
    private void calculateFee(double totalMoney) {
        double fee = totalMoney * 0.06;
        DecimalFormat df = new DecimalFormat("############0.00");
        updateTipFee(df.format(fee), df.format(totalMoney - fee));
    }
}
