package com.example.administrator.wankuoportal.aaPackage.activity.dialogAcivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.bean.JudgeMoneyBean;
import com.example.administrator.wankuoportal.aaPackage.bean.UserBean;
import com.example.administrator.wankuoportal.base.MyBaseDialogActivity;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.example.administrator.wankuoportal.util.Utils;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.util.JSONUtil;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectPayDialogActivity extends MyBaseDialogActivity implements View.OnClickListener {

    @BindView(R.id.wechat_payable_tv)
    TextView wechatPayableTv;

    @BindView(R.id.ail_payable_tv)
    TextView ailPayableTv;

    @BindView(R.id.have_money_tv)
    TextView haveMoneyTv;

    @BindView(R.id.have_gold_tv)
    TextView haveGoldTv;

    @BindView(R.id.glod_icon_iv)
    ImageView glodIconIv;

    @BindView(R.id.wallet_icon_iv)
    ImageView walletIconIv;

    @BindView(R.id.wechant_pay_tv)
    TextView wechantPayTv;

    @BindView(R.id.ali_pay_tv)
    TextView aliPayTv;

    @BindView(R.id.wallet_pay_tv)
    TextView walletPayTv;

    @BindView(R.id.gold_pay_tv)
    TextView goldPayTv;

    JudgeMoneyBean judgeMoneyBean;
    double money = 0;
    double haveMoney = 0;
    private int currentSelectIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pay_dialog);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        try {
            judgeMoneyBean = (JudgeMoneyBean) getIntent().getSerializableExtra(Constant.IntentKey.BEAN);
            money = Double.parseDouble(getIntent().getStringExtra(Constant.IntentKey.PRICE));
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
        initTipTv();
        httpPost("getuserinfo", new RequestParams(), Apis.getuserinfo);
    }

    private void initTipTv() {
        ailPayableTv.setText("应付款:￥" + money);
        wechatPayableTv.setText("应付款:￥" + money);
        Utils.print("judgeMoneyBean.isFlag() =" + judgeMoneyBean.isFlag());
        if (judgeMoneyBean.isFlag()) {
            haveGoldTv.setText("应支付金币:" + (money * 100));
        } else {
            haveGoldTv.setText("可支付金币:" + (judgeMoneyBean.getInfo().getNolimitGold()+judgeMoneyBean.getInfo().getAnswerGold()+judgeMoneyBean.getInfo().getDiamonds()));
            haveGoldTv.setVisibility(View.VISIBLE);
            glodIconIv.setImageResource(R.drawable.wallet_money_icon);
            haveGoldTv.setTextColor(getResources().getColor(R.color.text_color_gray));
        }
    }


    private void changeEnabled(int index) {

        wechantPayTv.setEnabled(index == 0);
        aliPayTv.setEnabled(index == 1);
        wechatPayableTv.setVisibility(index == 0 ? View.VISIBLE : View.GONE);
        ailPayableTv.setVisibility(index == 1 ? View.VISIBLE : View.GONE);
        if (haveMoney >= money) {
            walletPayTv.setEnabled(index == 2);
            haveMoneyTv.setVisibility(index == 2 ? View.VISIBLE : View.GONE);
        } else if (index == 2) {//灰色撤回到之前的选择
            changeEnabled(currentSelectIndex);
            return;
        }
        if (judgeMoneyBean.isFlag()) {
            goldPayTv.setEnabled(index == 3);
            haveGoldTv.setVisibility(index == 3 ? View.VISIBLE : View.GONE);
        } else if (index == 3) {
            changeEnabled(currentSelectIndex);
            return;
        }
        switch (index) {
            case 0:
                payType = Constant.PayType.PAY_WX_APP;
                break;
            case 1:
                payType = Constant.PayType.PAY_ZFB;
                break;
            case 2:
                payType = Constant.PayType.PAY_BALANCE;
                break;
            case 3:
                payType = Constant.PayType.PAY_ASSET;
                break;
        }
        currentSelectIndex = index;
    }


    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) throws Exception {
        if ("getuserinfo".equals(type)) {
            UserBean userBean = JSONUtil.toJavaBean(UserBean.class, jsonObject);
            haveMoney = userBean.getAAccountAuthorize().getMoney();
            if (haveMoney <= money) {
                haveMoneyTv.setText("余额:￥" + haveMoney);
                haveMoneyTv.setTextColor(getResources().getColor(R.color.text_color_gray));
                haveMoneyTv.setVisibility(View.VISIBLE);
                walletIconIv.setImageResource(R.drawable.wallet_gary_icon);
            } else {
                haveMoneyTv.setText("应支付:" + money);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //有些事情可能你已经忘记，但我们依然记得。今天(农历 四月十五)是你的生日，我们唱了一首歌送给你，
            // 希望它能给你带来一些惊喜和欢乐。Happy Birthday。
            case R.id.pay_confirm_btn:
                Utils.print("confirm");
                confirm();
                break;
            case R.id.wechat_pay_layout:
                changeEnabled(0);
                break;
            case R.id.ali_pay_layout:
                changeEnabled(1);
                break;
            case R.id.weallet_pay_layout:
                changeEnabled(2);
                break;
            case R.id.gold_pay_layout:
                changeEnabled(3);
                break;
            case R.id.pay_close_layout:
                finish();
                break;
        }
    }

    String payType = Constant.PayType.PAY_WX_APP;

    private void confirm() {
        if (TextUtils.isEmpty(payType)) {
            ProjectUtil.showMessage("请选择支付方式");
            return;
        }
        getIntent().putExtra(Constant.IntentKey.TYPE, payType);
        setResult(RESULT_OK, getIntent());
        finish();
    }
}
