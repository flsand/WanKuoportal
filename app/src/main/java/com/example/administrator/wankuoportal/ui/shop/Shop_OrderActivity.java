package com.example.administrator.wankuoportal.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.Log.ConfirmPayDialog;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.bean.JudgeMoneyBean;
import com.example.administrator.wankuoportal.aaPackage.bean.WechatPayBean;
import com.example.administrator.wankuoportal.aaPackage.activity.dialogAcivity.SelectPayDialogActivity;
import com.example.administrator.wankuoportal.aaPackage.listener.PayListener;
import com.example.administrator.wankuoportal.aaPackage.utils.AliPay;
import com.example.administrator.wankuoportal.base.DefultHttpAnalysisHelper;
import com.example.administrator.wankuoportal.beans.Address;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.beans.GoodBean;
import com.example.administrator.wankuoportal.beans.GoodInfoBean;
import com.example.administrator.wankuoportal.beans.OrderBean;
import com.example.administrator.wankuoportal.beans.PayGoodBean;
import com.example.administrator.wankuoportal.beans.PayResultBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.PayCore;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.SetUp.AddAddressActivity;
import com.example.administrator.wankuoportal.ui.address.ChooseAddressActivity;
import com.example.administrator.wankuoportal.util.DialogUtil;
import com.example.administrator.wankuoportal.util.LogUtils;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.flysand.mylibrary.http.HttpUtil;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.util.JSONUtil;
import com.flysand.mylibrary.util.Utils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import okhttp3.MediaType;

public class Shop_OrderActivity extends BaseActivity implements PayListener {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.TextView addressnametocarbuy;
    private android.widget.TextView addressphonetocarbuy;
    private android.widget.TextView addressaddresstocarbuy;
    private android.widget.ImageView addresschangetocarbuy;
    private android.widget.LinearLayout address;
    private ImageView imageView;
    private ImageView goodsimatogroupbuy;
    private TextView goodsnametogroupbuy;
    private TextView goodspricetogroupbuy;
    private TextView goodsnumtogroupbuy;
    public static String addressid = "";
    String goodsDetailId = "";
    int num = 1;
    String exchangeId = "";
    String goodsId = "";
    String ima = "";
    String name = "";
    String price = "";
    String color = "";
    String phone = "";
    String zuanshi = "";
    private ImageView jian;
    private ImageView jia;
    private TextView jinbi;
    private TextView shuliang;
    private TextView yanse;
    private TextView priceTv;
    int shuliangnum = 100;
    private android.widget.EditText edjianyi;
    private TextView tijiaoorder;
    String content = "";
    private TextView addaddress;
    OrderBean orderBean;
    Address.DatasBean addressBean;
    String payType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop__order);
        this.priceTv = (TextView) findViewById(R.id.goods_price_tv);
        this.addaddress = (TextView) findViewById(R.id.addaddress);
        this.tijiaoorder = (TextView) findViewById(R.id.tijiaoorder);
        this.edjianyi = (EditText) findViewById(R.id.ed_jianyi);
        this.yanse = (TextView) findViewById(R.id.yanse);
        this.shuliang = (TextView) findViewById(R.id.shuliang);
        this.jinbi = (TextView) findViewById(R.id.jinbi);
        this.jia = (ImageView) findViewById(R.id.jia);
        this.jian = (ImageView) findViewById(R.id.jian);
        this.goodsnumtogroupbuy = (TextView) findViewById(R.id.goods_num_togroupbuy);
        this.goodspricetogroupbuy = (TextView) findViewById(R.id.goods_price_togroupbuy);
        this.goodsnametogroupbuy = (TextView) findViewById(R.id.goods_name_togroupbuy);
        this.goodsimatogroupbuy = (ImageView) findViewById(R.id.goods_ima_togroupbuy);
        this.imageView = (ImageView) findViewById(R.id.imageView);
        this.address = (LinearLayout) findViewById(R.id.address);
        this.addresschangetocarbuy = (ImageView) findViewById(R.id.address_change_tocarbuy);
        this.addressaddresstocarbuy = (TextView) findViewById(R.id.address_address_tocarbuy);
        this.addressphonetocarbuy = (TextView) findViewById(R.id.address_phone_tocarbuy);
        this.addressnametocarbuy = (TextView) findViewById(R.id.address_name_tocarbuy);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ChooseAddressActivity.class);
            }
        });
        addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddAddressActivity.class);
            }
        });

        goodsDetailId = getIntent().getStringExtra("goodsDetailId");
        num = Integer.parseInt(getIntent().getStringExtra("num"));
        exchangeId = getIntent().getStringExtra("exchangeId");
        goodsId = getIntent().getStringExtra("goodsId");
        ima = getIntent().getStringExtra("ima");
        name = getIntent().getStringExtra("name");
        price = getIntent().getStringExtra("price");
        color = getIntent().getStringExtra("color");
        phone = getIntent().getStringExtra("phone");
        zuanshi = getIntent().getStringExtra("zuanshi");

        jinbi.setText(Utils.replaceDoubleZero((Double.parseDouble(price) * 100 * num) + "") + "金币");

        goodsnametogroupbuy.setText(name);
        priceTv.setText("价格：" + Utils.replaceDoubleZero((Double.parseDouble(price) * num) + ""));
        goodspricetogroupbuy.setText("金币:" + Utils.replaceDoubleZero((Double.parseDouble(price) * 100 * num) + ""));
        goodsnumtogroupbuy.setText("x" + num);
        String imaurl = Apis.Baseima + ima;
        Glide.with(Shop_OrderActivity.this).load(imaurl).into(goodsimatogroupbuy);


        shuliangnum = Integer.valueOf(num);
        shuliang.setText(num + "");
        if (color.isEmpty()) {
            yanse.setVisibility(View.INVISIBLE);
        } else {
            yanse.setText("颜色分类:" + color);
        }
//        jia.setEnabled(false);
//        jian.setEnabled(false);
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuliangnum++;
                shuliang.setText(shuliangnum + "");
//                if (Integer.valueOf(zuanshi) > 0) {

//                    if (Integer.valueOf(zuanshi) > (Integer.valueOf(price) * num * 0.25)) {
//                        jinbi.setText((int) (Integer.valueOf(price) * shuliangnum * 0.75) + "金币" + (int) (Integer.valueOf(price) * shuliangnum * 0.25) + "钻石");
//                    } else {
//                        jinbi.setText((int) (Integer.valueOf(price) * shuliangnum - Integer.valueOf(zuanshi)) + "金币" + Integer.valueOf(zuanshi) + "钻石");
//                    }
//                } else {
                jinbi.setText(Utils.replaceDoubleZero((Double.parseDouble(price) * shuliangnum * 100) + "") + "金币");
                goodsnumtogroupbuy.setText("x" + shuliangnum);
//                }
            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shuliangnum--;
                if (shuliangnum == 0) {
                    shuliangnum = 1;
                } else {
                    shuliang.setText(shuliangnum + "");
                    goodsnumtogroupbuy.setText("x" + shuliangnum);
                }

//                if (Integer.valueOf(zuanshi) > 0) {
//                    if (Integer.valueOf(zuanshi) > (Integer.valueOf(price) * num * 0.25)) {
//                        jinbi.setText((int) (Integer.valueOf(price) * shuliangnum * 0.75) + "金币" + (int) (Integer.valueOf(price) * shuliangnum * 0.25) + "钻石");
//                    } else {
//                        jinbi.setText((int) (Integer.valueOf(price) * shuliangnum - Integer.valueOf(zuanshi)) + "金币" + Integer.valueOf(zuanshi) + "钻石");
//
//                    }
//
//                } else {
                jinbi.setText(Utils.replaceDoubleZero((Double.parseDouble(price) * shuliangnum * 100) + "") + "金币");
//                }
            }
        });


        tijiaoorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (addaddress.getVisibility() == View.VISIBLE) {
                    DialogUtil.showMessage(Shop_OrderActivity.this, "请先添加地址");
                } else if (TextUtils.isEmpty(addressid)) {
                    DialogUtil.showMessage(Shop_OrderActivity.this, "请先选择地址");
                } else {
                    checkMoney();
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
                .addParams("telephone", phone)
                .addParams("random", yzm)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        BaseResult b = gson.fromJson(response, BaseResult.class);
                        if (b.getCode() == 0) {
                            showShort(b.getMsg());
//                            this.dialog = (new AlertDialog.Builder(this.context,)).create();
                            final ConfirmPayDialog y = new ConfirmPayDialog(Shop_OrderActivity.this, phone);
                            y.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉dialog的title。
                            y.show();
                            y.setClicklistener(new ConfirmPayDialog.ClickListenerInterface() {

                                @Override
                                public void doConfirm(String edit) {

                                    if (edit.isEmpty()) {
                                        showShort("请输入验证码");
                                    } else {
                                        if (edit.equals(finalYzm)) {
                                            y.dismiss();
                                            payOreder(orderBean.getId());
                                        } else {
                                            showShort("验证码错误");
//                                            y.dismiss();
                                        }

                                    }

                                }

                                @Override
                                public void doCancel() {
                                    y.dismiss();
                                    dismissHttpDialog();
                                }

                                @Override
                                public void doClose() {
                                    y.dismiss();
                                    dismissHttpDialog();
                                }
                            });

                        } else {
                            showShort(b.getMsg());
                        }

                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.RequestCode.PAY_TYPE && resultCode == RESULT_OK) {
            payType = data.getStringExtra(Constant.IntentKey.TYPE);
            Utils.print("payType = " + payType + "     " + Constant.getPayType(payType));
            addorder();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void checkMoney() {

//        var goodList=[{"goods_id":${good.id},"count":$(".goodscont").text()}];
//        var j_data={"goods":goodList,"wID":1};

        String time = new Date().getTime() + "";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.checkPay + "?token=" + accountId + "," + time + "," + token + "&accountId=" + accountId;
        Utils.print(" url = " + url);
        try {

            List<PayGoodBean> goods = new ArrayList<>();
            PayGoodBean goodBean = new PayGoodBean();
            goodBean.setCount(shuliangnum);
            goodBean.setGoods_id(goodsId);
            goods.add(goodBean);


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("goods", JSONUtil.toJsonString(goods));
            jsonObject.put("wID", Constant.HttpType.wID);
            jsonObject.put("accountId", accountId);
            Utils.print("json =" + jsonObject.toString());
            showHttpDialog();
            HttpUtil.getInstance(this).httpPost("judgeMoney", new RequestParams(jsonObject), url);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onHttpAnalysisIntercept(String type, JSONObject body) throws Exception {
        if ("judgeMoney".equals(type)) {
            try {
                if ("0".equals(body.getString("code"))) {
//                    addorder();
                    dismissHttpDialog();
                    JudgeMoneyBean judgeMoneyBean = JSONUtil.toJavaBean(JudgeMoneyBean.class, body.getString("data"));
                    Intent intent = new Intent(Shop_OrderActivity.this, SelectPayDialogActivity.class);
                    intent.putExtra(Constant.IntentKey.PRICE, String.valueOf(Double.parseDouble(price) * shuliangnum));
                    intent.putExtra(Constant.IntentKey.BEAN, judgeMoneyBean);
                    startActivityForResult(intent, Constant.RequestCode.PAY_TYPE);
                } else {
                    dismissHttpDialog();
                    DialogUtil.showMessage(this, body.getString("msg"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                dismissHttpDialog();
                showShort(DefultHttpAnalysisHelper.ERR_MSG);
            }
            return true;
        } else if (("payOrder" + Constant.PayType.PAY_WX_APP).equals(type)) {
            if ("0".equals(body.getString("code"))) {
                Utils.print("挑转微信支付");
                WechatPayBean bean = JSONUtil.toJavaBean(WechatPayBean.class, body.getString("data"));
                final IWXAPI msgApi = WXAPIFactory.createWXAPI(getBaseContext(), null);
                //注册的AppId
                msgApi.registerApp(bean.getAppId());
                PayReq request = new PayReq();
                request.appId = bean.getAppId();
                request.partnerId = bean.getPartnerId();
                request.prepayId = bean.getPrepayId();
                request.packageValue = "Sign=WXPay";
                request.nonceStr = bean.getNonceStr();
                request.timeStamp = bean.getTimestamp();
                request.sign = bean.getSign();
                msgApi.sendReq(request);

            } else {
                showShort(body.getString("msg"));
            }
            dismissHttpDialog();
            return true;

        } else if (("payOrder" + Constant.PayType.PAY_ZFB).equals(type)) {

            String content = body.getString("data");
            Utils.print("挑转支付宝支付");
            new AliPay(this).pay(content, "");

            return true;
        }
        return super.onHttpAnalysisIntercept(type, body);
    }

    private void addorder() {
        Log.d("qzw", "addorder");
        if (TextUtils.isEmpty(edjianyi.getText())) {
            content = "";
        } else {
            content = edjianyi.getText().toString();
        }

        String time = new Date().getTime() + "";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.buyGoods + "?token=" + accountId + "," + time + "," + token;
        try {
            GoodBean goodBean = new GoodBean();
            goodBean.setId(addressid);
            goodBean.setProvince(addressBean.getProvince());
            goodBean.setCity(addressBean.getCity());
            goodBean.setArea(addressBean.getArea());
            goodBean.setAddress(addressBean.getAddress());
            goodBean.setName(addressBean.getName());
            goodBean.setPhone(addressBean.getPhone());
            goodBean.setAccountId(new UserService(this).getaccountid());

            List<GoodInfoBean> goods = new ArrayList<>();
            GoodInfoBean infoBean = new GoodInfoBean();
            infoBean.setGoods_id(goodsId);
            infoBean.setCount(shuliangnum + "");
            infoBean.setName(name);
            infoBean.setmId("1");
            goods.add(infoBean);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("addr", JSONUtil.toJsonString(goodBean));
            jsonObject.put("g_order", JSONUtil.toJsonString(goods));
            Utils.print("json =" + jsonObject.toString());
            OkHttpUtils
                    .postString()
                    .url(url)
                    .content(jsonObject.toString())
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(new MyStringCallback() {

                        @Override
                        public void onHttpSuccess(String s, JSONObject jsonObject) throws Exception {
                            orderBean = JSONUtil.toJavaBean(OrderBean.class, jsonObject);
                            if (Constant.PayType.PAY_WX_APP.equals(payType) || Constant.PayType.PAY_ZFB.equals(payType)) {
                                payOreder(orderBean.getId());
                            } else {
                                sendmsg();
                            }
                        }

                        @Override
                        public void onHttpFailure(String s, String s1) throws Exception {
                            dismissErrDialog(s1);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void payOreder(int orderId) {

        httpPost("payOrder" + payType, new RequestParams("orderID", orderId, "type", payType), Apis.payOrder);
    }

    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) throws Exception {
        super.onHttpSuccess(type, jsonObject);
        if (("payOrder" + Constant.PayType.PAY_ASSET).equals(type) ||
                ("payOrder" + Constant.PayType.PAY_BALANCE).equals(type)) {
            PayResultBean resultBean = JSONUtil.toJavaBean(PayResultBean.class, jsonObject);
            Intent intent = new Intent(this, BuySuccessActivity.class);
            intent.putExtra(Constant.IntentKey.BEAN, resultBean);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onHttpFailure(String type, String msg) throws Exception {
        dismissHttpDialog();
        if ("payOrder".equals(type)) {
            buyFail(msg);
        }
    }

    private void buyFail(String msg) {
        Intent intent = new Intent(this, BuyFailActivity.class);
        intent.putExtra(Constant.IntentKey.STRING, msg);
        startActivity(intent);
    }

    private void jumpOrderActivity() {
        Intent it = new Intent(Shop_OrderActivity.this, SHopResultActivity.class);
        it.putExtra(Constant.IntentKey.BEAN, orderBean);
        startActivity(it);
        finish();
    }

//    /**
//     * 监听Back键按下事件
//     * 未付款跳转订单详情
//     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && orderBean != null) {
//            Utils.print("按下了back键   onKeyDown()");
//            jumpOrderActivity();
//            finish();
//            return false;
//        } else {
//            return super.onKeyDown(keyCode, event);
//        }
//
//    }

    @Override
    protected void onResume() {
        super.onResume();
        queryAddress();
        if (PayCore.getInstance().mWeichatState == PayCore.WeiChat_Pay_Success) {
            LogUtils.e("微信支付返回值 : " + PayCore.getInstance().mWeichatState + "");
            PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Normal;
            showShort("恭喜您购买成功！");
            jumpOrderActivity();
            finish();
        }
    }

    private void queryAddress() {
        httpPost("queryAddress", new RequestParams(), Apis.queryAddress);
    }


    @Override
    public void onHttpSuccess(String type, JSONArray jsonArray, int page, int size, int count) throws Exception {
        super.onHttpSuccess(type, jsonArray, page, size, count);
        if ("queryAddress".equals(type)) {
            if (jsonArray.length() > 0) {
                address.setVisibility(View.VISIBLE);
                addaddress.setVisibility(View.GONE);
                List<Address.DatasBean> beanList = JSONUtil.toJavaBeanList(Address.DatasBean.class, jsonArray);
                if (TextUtils.isEmpty(addressid)) {
                    updateAddress(beanList.get(0));
                } else {
                    for (int i = 0; i < beanList.size(); i++) {
                        if (addressid.equals(beanList.get(i).getId() + "")) {
                            updateAddress(beanList.get(i));
                            return;
                        }
                    }
                }
            } else {
                address.setVisibility(View.GONE);
                addaddress.setVisibility(View.VISIBLE);
            }
        }
    }

    private void updateAddress(Address.DatasBean addressBean) {
        this.addressBean = addressBean;
        addressaddresstocarbuy.setText(
                addressBean.getProvince()
                        + addressBean.getCity()
                        + addressBean.getArea()
                        + addressBean.getAddress());
        addressnametocarbuy.setText(addressBean.getName());
        addressphonetocarbuy.setText(addressBean.getPhone());
        addressid = addressBean.getId() + "";
    }

    @Override
    protected void onDestroy() {
        addressid = "";
        super.onDestroy();

    }

    @Override
    public void paySuccess(String orderId) {
        showShort("购买成功");
        jumpOrderActivity();
        finish();
    }

    @Override
    public void payFail(String orderId) {
        dismissHttpDialog();
        buyFail("支付失败");
    }

    @Override
    public void payCancel() {
        dismissHttpDialog();

    }
}
