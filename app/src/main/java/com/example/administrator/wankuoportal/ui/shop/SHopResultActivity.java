package com.example.administrator.wankuoportal.ui.shop;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.OrderBean;
import com.example.administrator.wankuoportal.beans.OrderDetailBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.util.JSONUtil;

import org.json.JSONObject;

public class SHopResultActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private TextView ordernum;
    private TextView ordertype;
    private TextView addressnametocarbuy;
    private TextView addressaddresstocarbuy;
    private android.widget.LinearLayout address;
    private ImageView goodsimatogroupbuy;
    private TextView goodsnametogroupbuy;
    private TextView yanse;
    private TextView goodspricetogroupbuy;
    private TextView goodsnumtogroupbuy;
    private TextView fukuantype;
    private TextView fukuanjaige;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_result);
        this.fukuanjaige = (TextView) findViewById(R.id.fukuan_jaige);
        this.fukuantype = (TextView) findViewById(R.id.fukuan_type);
        this.goodsnumtogroupbuy = (TextView) findViewById(R.id.goods_num_togroupbuy);
        this.goodsimatogroupbuy = (ImageView) findViewById(R.id.goods_ima_togroupbuy);
        this.yanse = (TextView) findViewById(R.id.yanse);
        this.goodspricetogroupbuy = (TextView) findViewById(R.id.goods_price_togroupbuy);
        this.goodsnametogroupbuy = (TextView) findViewById(R.id.goods_name_togroupbuy);
        this.address = (LinearLayout) findViewById(R.id.address);
        this.addressaddresstocarbuy = (TextView) findViewById(R.id.address_address_tocarbuy);
        this.addressnametocarbuy = (TextView) findViewById(R.id.address_name_tocarbuy);
        this.ordertype = (TextView) findViewById(R.id.order_type);
        this.ordernum = (TextView) findViewById(R.id.order_num);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ShopActivity.class);
            }
        });

        initData();
    }

    private void initData() {
        try {
            OrderBean orderBean = (OrderBean) getIntent().getSerializableExtra(Constant.IntentKey.BEAN);
            RequestParams params = new RequestParams();
            params.put("orderId", orderBean.getId());
            httpPost("getOrderDetail", params, Apis.getOrderDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) throws Exception {
        super.onHttpSuccess(type, jsonObject);
        if ("getOrderDetail".equals(type)) {
            OrderDetailBean orderBean = JSONUtil.toJavaBean(OrderDetailBean.class, jsonObject);

            ordernum.setText("订单号:" + orderBean.getOrder().getId());
//            if (addOrder.getData().getAexchangeOrder().getAmountDiamonds().equals("0")) {
//                fukuanjaige.setText((addOrder.getData().getAexchangeOrder().getAmount()) + "金币");
//            } else {
//                fukuanjaige.setText((addOrder.getData().getAexchangeOrder().getAmountGold()) + "金币" + addOrder.getData().getAexchangeOrder().getAmountDiamonds() + "钻石");
//            }

            switch (orderBean.getOrder().getOrderStatus()) {
                case "0100":
                    ordertype.setText("订单取消");
                    fukuantype.setText("订单取消");
                    break;
                case "0101":
                case "0102":
                    ordertype.setText("待付款");
                    fukuantype.setText("待付款");
                    break;
                case "0103":
                case "0104":
                    ordertype.setText("待发货");
                    fukuantype.setText("已付款");
                    break;
                case "0105":
                    ordertype.setText("已发货");
                    fukuantype.setText("已付款");
                    break;
                case "0106":
                    ordertype.setText("待收货");
                    fukuantype.setText("已付款");
                    break;
                case "0107":
                    ordertype.setText("待评价");
//                    ordertype.setText("已完成");
                    fukuantype.setText("已付款");
                    break;
                default:
                    break;
            }
            addressnametocarbuy.setText(orderBean.getOrder().getConsignee() + "    " + orderBean.getOrder().getMobile());
            addressaddresstocarbuy.setText("收货地址：" + orderBean.getOrder().getProvince() + orderBean.getOrder().getCity() + orderBean.getOrder().getDistrict() + orderBean.getOrder().getAddress());
            goodsnametogroupbuy.setText(orderBean.getGList().get(0).getName());
            goodspricetogroupbuy.setText("金币 " + (orderBean.getOrder().getSaPrice()) * 100);
//            yanse.setText("分类:" + orderBean.getContent());
            goodsnumtogroupbuy.setText("数量:" + orderBean.getOrder().getCount());
//            Glide.with(MyApplication.context).load(Apis.Baseima + addOrder.getData().getAgood().getImg()).into(goodsimatogroupbuy);

            ProjectUtil.loadRemoteImage(this, orderBean.getGList().get(0).getImg(), goodsimatogroupbuy);
        }

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            startActivity(ShopActivity.class);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
