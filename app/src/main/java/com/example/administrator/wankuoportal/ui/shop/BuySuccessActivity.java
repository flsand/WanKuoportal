package com.example.administrator.wankuoportal.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.example.administrator.wankuoportal.beans.OrderBean;
import com.example.administrator.wankuoportal.beans.PayResultBean;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuySuccessActivity extends MyBaseActivity {


    @BindView(R.id.pay_money_tv)
    TextView payMoneyTv;
    @BindView(R.id.pay_shopping_gold_tv)
    TextView payShoppingGoldTv;
    @BindView(R.id.pay_exchange_gools_tv)
    TextView payExchangeGoolsTv;
    @BindView(R.id.pay_diamonds_tv)
    TextView payDiamondsTv;
    @BindView(R.id.pay_order_num_tv)
    TextView payOrderNumTv;
    @BindView(R.id.pay_address_tv)
    TextView payAddressTv;
    @BindView(R.id.pay_goods_name_tv)
    TextView payGoodsNameTv;
    @BindView(R.id.pay_goods_count_tv)
    TextView payGoodsCountTv;
    @BindView(R.id.pay_type_tv)
    TextView payTypeTv;
    @BindView(R.id.pay_gold_money_tv)
    TextView payGoldMoneyTv;
    @BindView(R.id.pay_time_tv)
    TextView payTimeTv;
    @BindView(R.id.pay_people_tv)
    TextView payPeopleTv;
    @BindView(R.id.pay_people_flg_tv)
    TextView payPeopleFlgTv;

    PayResultBean resultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_success);
        ButterKnife.bind(this);
        resultBean = (PayResultBean) getIntent().getSerializableExtra(Constant.IntentKey.BEAN);
        initData();

    }

    private void initData() {

        if (resultBean != null) {
            //支付金额
            payMoneyTv.setText(Utils.replaceDoubleZero(resultBean.getOrder().getSaPrice() + ""));
            //订单号
            payOrderNumTv.setText(resultBean.getOrder().getId() + "");
            String phone = ProjectUtil.conductPhoneNumber(resultBean.getOrder().getMobile());
            //收货信息
            payAddressTv.setText(resultBean.getOrder().getConsignee() + "    " + phone);
            //产品名称
            payGoodsNameTv.setText(resultBean.getGoodOrderList().get(0).getName());
            //数量
            payGoodsCountTv.setText(resultBean.getOrder().getCount() + "");
            //支付方式
            payTypeTv.setText(Constant.getPayType(resultBean.getOrder().getPayType()));
            //支付金额
            payGoldMoneyTv.setText(resultBean.getOrder().getSaPrice() + "");
            //付款时间
            payTimeTv.setText(resultBean.getLog().get(0).getPayTime());
//            //付款人
//            payPeopleTv.setText();
//            //付款人身份
//            payPeopleFlgTv.setText();
            double goldCount = 0;
            for (int i = 0; i < resultBean.getLog().size(); i++) {
                String payType = resultBean.getLog().get(i).getType();
                if (Constant.PayType.PAY_SHOP.equals(payType)) {
                    //支付购物币总额：
                    payShoppingGoldTv.setText(Utils.replaceDoubleZero(resultBean.getLog().get(i).getAmount() + ""));
                } else if (Constant.PayType.PAY_ANSWER.equals(payType)) {
                    goldCount += resultBean.getLog().get(i).getAmount();
                } else if (Constant.PayType.PAY_RECHARGE.equals(payType)) {
                    goldCount += resultBean.getLog().get(i).getAmount();
                } else if (Constant.PayType.PAY_DIAMONDS.equals(payType)) {
                    //支付购物钻石总额：
                    payDiamondsTv.setText(Utils.replaceDoubleZero(resultBean.getLog().get(i).getAmount() + ""));
                }
            }
            if (goldCount != 0) {
                //支付答题/充值金币总额
                payExchangeGoolsTv.setText(Utils.replaceDoubleZero(goldCount + ""));
            }
        }
    }

    private void jumpOrderActivity() {
        Intent it = new Intent(BuySuccessActivity.this, SHopResultActivity.class);
        OrderBean orderBean = new OrderBean();
        orderBean.setId(resultBean == null ? -1 : resultBean.getOrder().getId());
        it.putExtra(Constant.IntentKey.BEAN, orderBean);
        startActivity(it);
    }


    @Override
    protected void onKeyBackClick() {
        super.onKeyBackClick();
        jumpOrderActivity();
    }

    /**
     * 监听Back键按下事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Utils.print("按下了back键   onKeyDown()");
            jumpOrderActivity();
            finish();
        }
        return false;
    }
}