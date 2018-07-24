package com.example.administrator.wankuoportal.aaPackage.activity.manor;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.fragment.manor.HanorFindFragment;
import com.example.administrator.wankuoportal.aaPackage.fragment.manor.HanorFragment;
import com.example.administrator.wankuoportal.aaPackage.fragment.manor.HanorMyFragment;
import com.example.administrator.wankuoportal.aaPackage.fragment.manor.HanorRecordFragment;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.flysand.mylibrary.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 买家  ->  万阔山庄
 */
public class ManorHomeActivity extends MyBaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.manor_radioGroup)
    RadioGroup manorRadioGroup;
    // 首页
    HanorFragment hanorFragment;
    // 发现
    HanorFindFragment findFragment;
    //记录
    HanorRecordFragment recordFragment;
    //我的
    HanorMyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manor_home);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        if (hanorFragment == null)
            hanorFragment = new HanorFragment();
        if (findFragment == null)
            findFragment = new HanorFindFragment();
        if (recordFragment == null)
            recordFragment = new HanorRecordFragment();
        if (myFragment == null)
            myFragment = new HanorMyFragment();

        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.transaction_contain_layout, hanorFragment, "hanorFragment");
        transaction.add(R.id.transaction_contain_layout, findFragment, "findFragment");
        transaction.add(R.id.transaction_contain_layout, recordFragment, "recordFragment");
        transaction.add(R.id.transaction_contain_layout, myFragment, "myFragment");

        transaction.hide(hanorFragment);
        transaction.hide(findFragment);
        transaction.hide(recordFragment);
        transaction.hide(myFragment);

        transaction.commit();

        manorRadioGroup.setOnCheckedChangeListener(this);
        //切换主页
        findViewById(R.id.manor_home_rb).performClick();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        findViewById(R.id.manor_home_rb).performClick();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Utils.print("onCheckedChanged");
        try {
            FragmentManager fm = getFragmentManager();
            // 开启Fragment事务
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.hide(hanorFragment);
            transaction.hide(findFragment);
            transaction.hide(recordFragment);
            transaction.hide(myFragment);
            switch (checkedId) {
                case R.id.manor_home_rb:
                    transaction.show(hanorFragment);
                    setTitleText("万阔山庄");
                    break;
                case R.id.manor_find_rb:
                    transaction.show(findFragment);
                    setTitleText("发现");
                    break;
                case R.id.manor_record_rb:
                    transaction.show(recordFragment);
                    setTitleText("记录");
                    break;
                case R.id.manor_my_rb:
                    transaction.show(myFragment);
                    setTitleText("个人中心");
                    break;
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

}
