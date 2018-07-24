package com.example.administrator.wankuoportal.aaPackage.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.OvershootInterpolator;
import android.widget.RadioGroup;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.coomon.Define;
import com.example.administrator.wankuoportal.aaPackage.mainFragment.AnswerFragment;
import com.example.administrator.wankuoportal.aaPackage.mainFragment.MainFragment;
import com.example.administrator.wankuoportal.aaPackage.mainFragment.MerchantsFragment;
import com.example.administrator.wankuoportal.aaPackage.mainFragment.MyFragment;
import com.example.administrator.wankuoportal.aaPackage.mainFragment.ReleaseFragment;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.util.DataRepository;
import com.flysand.mylibrary.util.MyHandler;
import com.flysand.mylibrary.util.MyToast;
import com.flysand.mylibrary.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.observers.DisposableObserver;

public class NewMainActivity extends MyBaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    @BindView(R.id.main_radioGroup)
    RadioGroup mainRadioGroup;
    // 主页面Fragment
    MainFragment mainFragment;
    // 商家分类
    MerchantsFragment merchantsFragment;
    // 发布需求
    ReleaseFragment releaseFragment;
    //答题赚钱
    AnswerFragment answerFragment;
    //我的
    MyFragment myFragment;
    // 当前选择的id
    private int groupCheckId;
    /**
     * 双击退出，根据时间来判断
     */
    long waitTime = 1200;
    long touchTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        ButterKnife.bind(this);
        mainRadioGroup.setOnCheckedChangeListener(this);
        if (savedInstanceState == null) {//异常恢复
            if (mainFragment == null)
                mainFragment = new MainFragment();
            if (merchantsFragment == null)
                merchantsFragment = new MerchantsFragment();
            if (releaseFragment == null)
                releaseFragment = new ReleaseFragment();
            if (answerFragment == null)
                answerFragment = new AnswerFragment();
            if (myFragment == null)
                myFragment = new MyFragment();

            FragmentManager fm = getFragmentManager();
            // 开启Fragment事务
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.main_contain_layout, mainFragment, "mainFragment");
            transaction.add(R.id.main_contain_layout, merchantsFragment, "merchantsFragment");
            transaction.add(R.id.main_contain_layout, releaseFragment, "releaseFragment");
            transaction.add(R.id.main_contain_layout, answerFragment, "answerFragment");
            transaction.add(R.id.main_contain_layout, myFragment, "myFragment");

            transaction.hide(mainFragment);
            transaction.hide(merchantsFragment);
            transaction.hide(releaseFragment);
            transaction.hide(answerFragment);
            transaction.hide(myFragment);

            transaction.commit();
            //切换主页
            findViewById(R.id.main_rb).performClick();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取消息未读数量
        initInfomation();
    }

    /**
     * 不显示标题
     */
    @Override
    protected boolean showTitle() {
        return false;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mainFragment = (MainFragment) getFragmentManager().findFragmentByTag("mainFragment");
        merchantsFragment = (MerchantsFragment) getFragmentManager().findFragmentByTag("merchantsFragment");
        releaseFragment = (ReleaseFragment) getFragmentManager().findFragmentByTag("releaseFragment");
        answerFragment = (AnswerFragment) getFragmentManager().findFragmentByTag("answerFragment");
        myFragment = (MyFragment) getFragmentManager().findFragmentByTag("myFragment");

        groupCheckId = savedInstanceState.getInt("groupCheckId");
        onCheckedChanged(mainRadioGroup, groupCheckId);
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
            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= waitTime) {
                //让Toast的显示时间和等待时间相同
                new MyToast(getApplication()).setText("再按一次退出");
//                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                touchTime = currentTime;
            } else {
                finish();
                System.exit(0);//会跳屏，关闭时会出现黑屏，需要优化，不能用finish()
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onUpdate(int i, Intent intent) {
        if (i == Constant.RequestCode.TRANSACTION) {
            findViewById(R.id.main_rb).performClick();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Utils.print("onCheckedChanged");
        try {
            FragmentManager fm = getFragmentManager();
            // 开启Fragment事务
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.hide(mainFragment);
            transaction.hide(merchantsFragment);
            transaction.hide(releaseFragment);
            transaction.hide(answerFragment);
            transaction.hide(myFragment);
            switch (checkedId) {
                case R.id.main_rb:
                    transaction.show(mainFragment);
                    break;
                case R.id.main_classification_rb:
                    transaction.show(merchantsFragment);
                    break;
                case R.id.main_release_rb:
                    transaction.show(releaseFragment);
                    break;
                case R.id.main_answer_rb:
                    transaction.show(answerFragment);
                    break;
                case R.id.main_my_rb:
                    transaction.show(myFragment);
                    break;
            }
            addAnimate(findViewById(checkedId));

            groupCheckId = checkedId;
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 切换fragment
     *
     * @param index
     */
    public void switchHalaman(int index) {

        switch (index) {
            case Define.MainPages.MAIN:
                findViewById(R.id.main_rb).performClick();
                break;
            case Define.MainPages.MERCHANTS:
                switchMerchantsFragment(-1);
                break;
            case Define.MainPages.RELEASE:
                findViewById(R.id.main_release_rb).performClick();
                break;
            case Define.MainPages.ANSWER:
                findViewById(R.id.main_answer_rb).performClick();
                break;
            case Define.MainPages.MY_EMPLOYERS:
            case Define.MainPages.MY_MEMBERS:
                myFragment.switchPage(index);
                findViewById(R.id.main_my_rb).performClick();
                break;
        }
    }

    /**
     * 切换分类
     *
     * @param index
     */
    public void switchMerchantsFragment(int index) {
        if (index > 0)
            merchantsFragment.setSubLabelPosition(index);
        findViewById(R.id.main_classification_rb).performClick();
    }

    /**
     * 添加动画
     *
     * @param view
     * @see ViewPropertyAnimator
     */
    private void addAnimate(View view) {
        view.setScaleX(0.6f);
        view.setScaleY(0.6f);
        view.animate()
                .scaleX(0.99f)
                .scaleY(0.99f)
                .alpha(1f)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(300)
                .start();
    }

    /**
     * 点击
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        mainFragment.onClick(v);
    }

    private void initInfomation() {
        DataRepository.getInstance().getNumInfoNotRead(getApplication(), new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                Utils.print("未读消息数 = " + integer);
                try {
                    if (integer > 0) {
                        mainFragment.showInfomationCount(integer);
                        myFragment.showInfomationCount(integer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void forReceiverResult(Intent var1) {
        Utils.print("forReceiverResult");
        if (var1 != null) {
            if (Constant.RequestCode.TRANSACTION == var1.getIntExtra(Constant.IntentKey.TYPE, 0)) {
                new MyHandler(500){
                    @Override
                    public void run() {
                        //切换主页
                        findViewById(R.id.main_rb).performClick();
                    }
                };
            }
        }
    }
}
