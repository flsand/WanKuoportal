package com.example.administrator.wankuoportal.ui.notify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.global.BaseActivity;

/**
 * 我的消息详情
 *
 * 修改时间:2018年3月2日11:22:02
 * @author JakeChen
 */
public class NotifyDetailActivity extends BaseActivity {

    public static final String NOTIFY_TITLE = "title";
    public static final String NOTIFY_CONTENT = "content";
    public static final String NOTIFY_TIME = "time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_detail);
        initDefaultViews();
    }

    /**
     * 初始化View的方法
     */
    private void initDefaultViews() {
        ImageView mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView mTitle = (TextView) findViewById(R.id.tv_notify_title);
        TextView mContent = (TextView) findViewById(R.id.tv_notify_content);
        TextView mTime = (TextView) findViewById(R.id.tv_notify_time);
        //初始化前一个页面传递过来的消息数据
        initVariables(mTitle,mContent,mTime);
    }

    /**
     * 初始化变量的方法
     * @param mTitle
     * @param mContent
     * @param mTime
     */
    private void initVariables(TextView mTitle, TextView mContent, TextView mTime) {
        Intent mIntent = getIntent();
        String mTitleValue = mIntent.getStringExtra(NOTIFY_TITLE);
        String mContentValue = mIntent.getStringExtra(NOTIFY_CONTENT);
        String mTimeValue = mIntent.getStringExtra(NOTIFY_TIME);
        mTitle.setText(mTitleValue);
        mContent.setText(mContentValue);
        mTime.setText(mTimeValue);
    }
}
