package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.UserService;
import com.jaeger.library.StatusBarUtil;
import com.example.administrator.wankuoportal.global.BaseActivity;


public class MemberActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private TextView zhifu;
    private Button mGrade1;
    private Button mGrade2;
    private Button mGrade3;
    private Button mGrade4;
    private Button mGrade5;
    private LinearLayout mBaiYinLayout;
    private LinearLayout mHuangJinLayout;
    private LinearLayout mZuanshiLayout;
    private LinearLayout mHuangguanLayout;
    private LinearLayout mZhiZhunLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        StatusBarUtil.setColor(MemberActivity.this, Color.parseColor("#FF4200"), 0);
        initView();
        initVariables();
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * 初始化变量的方法
     */
    private void initVariables() {
        int isOrdinaryMember = new UserService(MyApplication.context).getIsOrdinaryMember();
        hideMemberGrade(isOrdinaryMember);
    }

    /**
     * 隐藏不该显示的店长等级信息
     *
     * @param isOrdinaryMember
     */
    private void hideMemberGrade(int isOrdinaryMember) {
        switch (isOrdinaryMember) {
            case 0:
                mBaiYinLayout.setVisibility(View.VISIBLE);
                mHuangJinLayout.setVisibility(View.VISIBLE);
                mZuanshiLayout.setVisibility(View.VISIBLE);
                mHuangguanLayout.setVisibility(View.VISIBLE);
                mZhiZhunLayout.setVisibility(View.VISIBLE);
                break;
            case 1:
                mBaiYinLayout.setVisibility(View.VISIBLE);
                mHuangJinLayout.setVisibility(View.VISIBLE);
                mZuanshiLayout.setVisibility(View.VISIBLE);
                mHuangguanLayout.setVisibility(View.VISIBLE);
                mZhiZhunLayout.setVisibility(View.VISIBLE);
                break;
            case 101:
                mBaiYinLayout.setVisibility(View.GONE);
                mHuangJinLayout.setVisibility(View.VISIBLE);
                mZuanshiLayout.setVisibility(View.VISIBLE);
                mHuangguanLayout.setVisibility(View.VISIBLE);
                mZhiZhunLayout.setVisibility(View.VISIBLE);
                break;
            case 102:
                mBaiYinLayout.setVisibility(View.GONE);
                mHuangJinLayout.setVisibility(View.GONE);
                mZuanshiLayout.setVisibility(View.VISIBLE);
                mHuangguanLayout.setVisibility(View.VISIBLE);
                mZhiZhunLayout.setVisibility(View.VISIBLE);
                break;
            case 103:
                mBaiYinLayout.setVisibility(View.GONE);
                mHuangJinLayout.setVisibility(View.GONE);
                mZuanshiLayout.setVisibility(View.GONE);
                mHuangguanLayout.setVisibility(View.VISIBLE);
                mZhiZhunLayout.setVisibility(View.VISIBLE);
                break;
            case 104:
                mBaiYinLayout.setVisibility(View.GONE);
                mHuangJinLayout.setVisibility(View.GONE);
                mZuanshiLayout.setVisibility(View.GONE);
                mHuangguanLayout.setVisibility(View.GONE);
                mZhiZhunLayout.setVisibility(View.VISIBLE);
                break;
            case 105:
                mBaiYinLayout.setVisibility(View.GONE);
                mHuangJinLayout.setVisibility(View.GONE);
                mZuanshiLayout.setVisibility(View.GONE);
                mHuangguanLayout.setVisibility(View.GONE);
                mZhiZhunLayout.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 初始化View的方法
     */
    private void initView() {
        //初始化5个Laout
        mBaiYinLayout = (LinearLayout) findViewById(R.id.ll_baiying_layout);
        mHuangJinLayout = (LinearLayout) findViewById(R.id.ll_huangjin_layout);
        mZuanshiLayout = (LinearLayout) findViewById(R.id.ll_zuanshi_layout);
        mHuangguanLayout = (LinearLayout) findViewById(R.id.ll_huangguan_layout);
        mZhiZhunLayout = (LinearLayout) findViewById(R.id.ll_zhizhun_layout);


        //初始化5个升级按钮
        mGrade1 = (Button) findViewById(R.id.bt_member_grade_1);
        mGrade2 = (Button) findViewById(R.id.bt_member_grade_2);
        mGrade3 = (Button) findViewById(R.id.bt_member_grade_3);
        mGrade4 = (Button) findViewById(R.id.bt_member_grade_4);
        mGrade5 = (Button) findViewById(R.id.bt_member_grade_5);

        mGrade1.setOnClickListener(this);
        mGrade2.setOnClickListener(this);
        mGrade3.setOnClickListener(this);
        mGrade4.setOnClickListener(this);
        mGrade5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent mIntent = new Intent(MemberActivity.this, MemberPayActivity.class);
        switch (v.getId()) {
            case R.id.bt_member_grade_1:
                mIntent.putExtra(MemberPayActivity.SELECT_MEMBER_GRADE, 0);
                break;
            case R.id.bt_member_grade_2:
                mIntent.putExtra(MemberPayActivity.SELECT_MEMBER_GRADE, 1);
                break;
            case R.id.bt_member_grade_3:
                mIntent.putExtra(MemberPayActivity.SELECT_MEMBER_GRADE, 2);
                break;
            case R.id.bt_member_grade_4:
                mIntent.putExtra(MemberPayActivity.SELECT_MEMBER_GRADE, 3);
                break;
            case R.id.bt_member_grade_5:
                mIntent.putExtra(MemberPayActivity.SELECT_MEMBER_GRADE, 4);
                break;
        }

        startActivityForResult(mIntent, Constant.RequestCode.PAY);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RequestCode.PAY) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK);
                finish();
            }
        }
    }
}
