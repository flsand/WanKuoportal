package com.example.administrator.wankuoportal.aaPackage.activity.gold;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.activity.NewMainActivity;
import com.example.administrator.wankuoportal.aaPackage.bean.TransactionPriceBean;
import com.example.administrator.wankuoportal.aaPackage.bean.UserBean;
import com.example.administrator.wankuoportal.aaPackage.fragment.gold.RecordTransactionFragment;
import com.example.administrator.wankuoportal.aaPackage.fragment.gold.ReleaseTransactionFragment;
import com.example.administrator.wankuoportal.aaPackage.fragment.gold.TransactionFragment;
import com.example.administrator.wankuoportal.aaPackage.fragment.gold.TransactionOrderFragment;
import com.example.administrator.wankuoportal.base.MyApplication;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyZhanghuActivity;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.util.JSONUtil;
import com.flysand.mylibrary.util.Utils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/14.
 *     desc    : 金币交易大厅
 * </pre>
 */
public class TransactionSalaActivity extends MyBaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.transaction_radioGroup)
    RadioGroup mRadioGroup;

    @BindView(R.id.transaction_more_cb)
    CheckBox transactionMoreCb;

    @BindView(R.id.bottom_layout)
    View bottomLayout;
    @BindView(R.id.bottom_bg_btn)
    View bottomBgBtn;
    // 交易
    TransactionFragment transactionFragment;
    // 交易订单
    TransactionOrderFragment orderFragment;
    // 发布交易
    ReleaseTransactionFragment releaseFragment;
    //记录
    RecordTransactionFragment recordFragment;
    // 当前选择的id
    private int groupCheckId;

    public static TransactionPriceBean transactionPriceBean = new TransactionPriceBean();

    //交易时间是每天16:30之前
    public static boolean canTransaction = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_sala_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        mRadioGroup.setOnCheckedChangeListener(this);
        transactionMoreCb.setOnCheckedChangeListener(this);

//        if (savedInstanceState == null) {//异常恢复
        if (transactionFragment == null)
            transactionFragment = new TransactionFragment();
        if (orderFragment == null)
            orderFragment = new TransactionOrderFragment();
        if (releaseFragment == null)
            releaseFragment = new ReleaseTransactionFragment();
        if (recordFragment == null)
            recordFragment = new RecordTransactionFragment();

        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.transaction_contain_layout, transactionFragment, "transactionFragment");
        transaction.add(R.id.transaction_contain_layout, orderFragment, "orderFragment");
        transaction.add(R.id.transaction_contain_layout, recordFragment, "recordFragment");
        transaction.add(R.id.transaction_contain_layout, releaseFragment, "releaseFragment");

        transaction.hide(transactionFragment);
        transaction.hide(orderFragment);
        transaction.hide(releaseFragment);
        transaction.hide(recordFragment);

        transaction.commit();
        //切换交易
        findViewById(R.id.transaction_rb).performClick();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        findViewById(R.id.transaction_rb).performClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        httpGetNoDialog("getUserInfo", new RequestParams(), Apis.checksignin);
    }

    @Override
    public void onClick(View v) {
        Utils.print("onClick");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Utils.print("onCheckedChanged");
        try {
            FragmentManager fm = getFragmentManager();
            // 开启Fragment事务
            FragmentTransaction transaction = fm.beginTransaction();
            if (checkedId != R.id.transaction_release_rb) {
                transaction.hide(transactionFragment);
                transaction.hide(orderFragment);
                transaction.hide(releaseFragment);
                transaction.hide(recordFragment);
            }
            switch (checkedId) {
                case R.id.transaction_rb:
                    transaction.show(transactionFragment);
                    setTitleText(getTitle().toString());
                    transactionFragment.initData();
                    break;
                case R.id.transaction_order_rb:
                    transaction.show(orderFragment);
                    setTitleText("我的订单");
                    break;
                case R.id.transaction_release_rb:
                    transaction.show(releaseFragment);
                    setTitleText("发布");
                    break;
                case R.id.transaction_record_rb:
                    transaction.show(recordFragment);
                    setTitleText("统计记录");
                    recordFragment.getReportData();
                    break;
            }
            transactionMoreCb.setChecked(false);
            groupCheckId = checkedId;
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    public void onUpdate(int var1, Intent var2) {
        try {
            transactionFragment.onUpdate(var1, var2);
            orderFragment.onUpdate(var1, var2);
            releaseFragment.onUpdate(var1, var2);
            recordFragment.onUpdate(var1, var2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 拦截返回事件，提示
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            if (transactionMoreCb.isChecked()) {
                transactionMoreCb.setChecked(false);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        bottomLayout.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        bottomBgBtn.setVisibility(isChecked ? View.VISIBLE : View.GONE);
    }

    @OnClick({R.id.platform_home_page_tv, R.id.my_wallet_tv, R.id.regulation_tv, R.id.bottom_bg_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.platform_home_page_tv:
                Utils.printError("平台首页");
                Intent intent = new Intent(NewMainActivity.class.getName());
                intent.putExtra(Constant.IntentKey.TYPE, Constant.RequestCode.TRANSACTION);
                sendBroadcast(intent);
                Utils.printError("sendBroadcast");
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.my_wallet_tv:
                Utils.print("我的钱包");
                startActivity(MyZhanghuActivity.class);
                break;
            case R.id.regulation_tv:
                Utils.print("万阔规则");
                startActivity(GoldRegulationActivity.class);
                break;
            case R.id.bottom_bg_btn:
                transactionMoreCb.setChecked(false);
                return;
        }
    }

    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) {
        super.onHttpSuccess(type, jsonObject);
        if ("getUserInfo".equals(type)) {
            UserBean bean = JSONUtil.toJavaBean(UserBean.class, jsonObject);
            MyApplication.getInstance().setUserBean(bean);
        }

    }
}
