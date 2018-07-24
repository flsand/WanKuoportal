package com.example.administrator.wankuoportal.ui.denglu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.fragment.login.LoginFragment_phone;
import com.example.administrator.wankuoportal.fragment.login.loginFragment_Account;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.Constant;
import com.flysand.mylibrary.util.Utils;


public class LoginActivity extends BaseActivity {


    private TextView toolbartitle;
    private TextView zhuce;
    private Toolbar basetoolbar;
    private TabLayout logintab;
    private ViewPager viewPagerlogin;
    private android.widget.ImageView back;

    LoginFragment_phone fragmentPhone;
    loginFragment_Account fragmentAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.back = (ImageView) findViewById(R.id.back);
        this.viewPagerlogin = (ViewPager) findViewById(R.id.viewPager_login);
        this.logintab = (TabLayout) findViewById(R.id.login_tab);
        this.basetoolbar = (Toolbar) findViewById(R.id.base_toolbar);
        this.zhuce = (TextView) findViewById(R.id.zhuce);
        this.toolbartitle = (TextView) findViewById(R.id.toolbar_title);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initview();
    }

    private void initview() {
        fragmentPhone = new LoginFragment_phone();
        fragmentAccount = new loginFragment_Account();

        viewPagerlogin.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] mTitles = new String[]{"账号密码登录", "手机号登录"};

            @Override
            public Fragment getItem(int position) {

                if (position == 1) {
                    return fragmentPhone;
                }
                return fragmentAccount;
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }


            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });

        logintab.setupWithViewPager(viewPagerlogin);
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("type", "zhuce");
                startActivityForResult(intent, Constant.RequestCode.REGISER);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RequestCode.REGISER) {
            if (resultCode == Constant.ResultCode.FAIL) {
                String account = data.getStringExtra(Constant.IntentKey.ACCOUNT);
                Utils.print("accout = " + account);
                fragmentAccount.updateAccout(account);
                fragmentPhone.updateAccout(account);

            }else if (requestCode == RESULT_OK){

            }
        }
    }
}
