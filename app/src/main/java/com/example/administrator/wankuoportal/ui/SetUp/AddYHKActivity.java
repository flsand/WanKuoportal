package com.example.administrator.wankuoportal.ui.SetUp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.google.gson.Gson;
import com.example.administrator.wankuoportal.beans.ABankCard;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.AddressPickTask;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.Date;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import okhttp3.Call;
import okhttp3.MediaType;

/***
 * 添加银行卡
 */

public class AddYHKActivity extends BaseActivity {
    private LinearLayout choose_address;
    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private LinearLayout chooseaddress;
    private TextView queding;
    ABankCard aBankCard;
    private android.widget.TextView edzhanhutype;
    private android.widget.EditText name;
    private android.widget.TextView kaihutype;
    private TextView yinhangtx;
    private LinearLayout yhchooselin;
    private android.widget.EditText zhihanged;
    private android.widget.EditText edcardnum;
    private TextView addresstx;
    private LinearLayout kaihutypelin;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_yhk);
        this.kaihutypelin = (LinearLayout) findViewById(R.id.kaihutype_lin);
        this.edzhanhutype = (TextView) findViewById(R.id.ed_zhanhutype);
        this.addresstx = (TextView) findViewById(R.id.address_tx);
        this.edcardnum = (EditText) findViewById(R.id.ed_cardnum);
        this.zhihanged = (EditText) findViewById(R.id.zhihang_ed);
        this.yhchooselin = (LinearLayout) findViewById(R.id.yhchoose_lin);
        this.yinhangtx = (TextView) findViewById(R.id.yinhang_tx);
        this.kaihutype = (TextView) findViewById(R.id.kaihutype);
        this.name = (EditText) findViewById(R.id.name);
        this.queding = (TextView) findViewById(R.id.queding);
        this.chooseaddress = (LinearLayout) findViewById(R.id.choose_address);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        chooseaddress = (LinearLayout) findViewById(R.id.choose_address);
        chooseaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddressPicker();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        aBankCard = new ABankCard();


        yhchooselin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showyinhangChooseDialog();
            }
        });

        kaihutypelin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showkaihutupeChooseDialog();
            }
        });


        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edzhanhutype.getText())) {

                    if (!TextUtils.isEmpty(name.getText())) {

                        aBankCard.setName(name.getText().toString());

                        if (!TextUtils.isEmpty(kaihutype.getText())) {

                            aBankCard.setName(name.getText().toString());

                            if (!TextUtils.isEmpty(addresstx.getText())) {


                                if (!TextUtils.isEmpty(yinhangtx.getText())) {


                                    if (!TextUtils.isEmpty(zhihanged.getText())) {

                                        aBankCard.setBankName(zhihanged.getText().toString());

                                        if (!TextUtils.isEmpty(edcardnum.getText())) {

                                            aBankCard.setBankCardNo(edcardnum.getText().toString());
                                            addcard();

                                        } else {
                                            showShort("请填写银行卡号");
                                        }

                                    } else {
                                        showShort("请填写支行名称");
                                    }

                                } else {
                                    showShort("请选择银行");
                                }

                            } else {
                                showShort("请选择开户地址");
                            }

                        } else {
                            showShort("请输入开户类型");
                        }

                    } else {
                        showShort("请输入开户人姓名");
                    }
                } else {
                    showShort("请输入银行账户类型");
                }



            }
        });
    }
    private String[] kaihutupe = new String[]{"对私账户", "对公账户"};// 账户类型选择

    /* 银行卡选择框 */
    private void showkaihutupeChooseDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);// 自定义对话框
        builder.setSingleChoiceItems(kaihutupe, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                kaihutype.setText(kaihutupe[which]);
                aBankCard.setCardType(which+1);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder.show();// 让弹出框显示
    }

    private String[] yinhangArry = new String[]{"中国工商银行", "中国农业银行", "中国银行", "中国建设银行", "中国交通银行"};// 银行选择

    /* 银行卡选择框 */
    private void showyinhangChooseDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);// 自定义对话框
        builder.setSingleChoiceItems(yinhangArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                yinhangtx.setText(yinhangArry[which]);
                aBankCard.setBankType(which+1);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder.show();// 让弹出框显示
    }

    private void addcard() {
        String time = new Date().getTime() + "";
        String token = new UserService(MyApplication.context).gettoken();
        String accountId = new UserService(MyApplication.context).getaccountid();
        token = MD5Util.md5(time + token);
        aBankCard.setAccountId(accountId+"");
        String url = Apis.Base + Apis.addbankcard + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .postString()
                .url(url)
                .content(new Gson().toJson(aBankCard))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {

                        L.d(response);
                        BaseResult b = gson.fromJson(response,BaseResult.class);
                        if (b.getCode()==0){
                            showShort(b.getMsg());
                            finish();
                        }else {
                            showShort(b.getMsg());
                        }
                    }
                });
    }

    public void onAddressPicker() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        //设置不显示区
        task.setHideCounty(true);


        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                showShort("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
                    aBankCard.setProvince(province.getAreaName());
                    aBankCard.setCity(city.getAreaName());
                    addresstx.setText(province.getAreaName() + city.getAreaName());
                } else {
                    aBankCard.setProvince(province.getAreaName());
                    aBankCard.setCity(city.getAreaName());
                    addresstx.setText(province.getAreaName() + city.getAreaName());
                }
            }
        });
        task.execute("山东", "烟台");
    }
}
