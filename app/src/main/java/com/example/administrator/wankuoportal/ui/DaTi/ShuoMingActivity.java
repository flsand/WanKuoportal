package com.example.administrator.wankuoportal.ui.DaTi;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.jaeger.library.StatusBarUtil;
import com.example.administrator.wankuoportal.global.BaseActivity;

public class ShuoMingActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.LinearLayout background;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuo_ming);
        this.text = (TextView) findViewById(R.id.text);
        this.background = (LinearLayout) findViewById(R.id.background);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        StatusBarUtil.setTransparent(ShuoMingActivity.this);
        String type = getIntent().getStringExtra("type");
        if (type.equals("SC")) {
            title.setText("猜题赢大奖说明");
            text.setText("1. 答题流程：每组10道题，每道题限时20秒，超时会自动跳转到下一题，答案4选1，答对点击下一题继续，答错需要读秒结束开始下一道题。\n" +
                    "2. 金币用途：答题金币可提现，也可以在金币商城、平台内购买任意服务和产品。\n" +
                    "3. 邀请好友：邀请好友注册答题，答题获得金币奖励，邀请人同时获得金币奖励。\n" +
                    "4. 答题赢大奖：本市和全国综合排行前三可获大奖。\n" +
                    "温馨提示：普通会员可免费答题10道；邀请朋友注册可再获得10道题的答题机会；没有邀请注册的情况下，则需要间隔1小时后方可继续答10道题。升级为店长不限量答题哟！\n" );
//            text.setText("1、操作流程：每组10道题，每道题限时20秒，答案4选1，超时自动跳转到下一题。\n2、金币用途：答题金币可提现，在平台内购买任意服务、在兑换商城兑换相应积分商品。\n3、好友邀请：邀请好友注册账号并答题，好友答对题，邀请人同时获得金币奖励。\n4.答题赢大奖：本市和全国综合排行前三可获大奖。");
        } else {
            title.setText("记忆赢大奖说明");
            text.setText("1. 答题流程：先看10个说明和答案，每个限时15秒，再开始答题，每道题限时15秒，超时会自动跳转到下一题，答案4选1，答对点击下一题继续，答错需要读秒结束开始下一道题\n" +
                    "2. 金币用途：答题金币可提现，也可以在金币商城、平台内购买任意服务和产品。\n" +
                    "3. 邀请好友：邀请好友注册答题，答题获得金币奖励，邀请人同时获得金币奖励。\n" +
                    "4. 答题赢大奖：本市和全国综合排行前三可获大奖。\n" +
                    "温馨提示：普通会员可免费答题10道；邀请朋友注册可再获得10道题的答题机会；没有邀请注册的情况下，则需要间隔1小时后方可继续答10道题。升级为店长不限量答题哟！\n");
//            text.setText("1、操作流程：先看10个说明和答案，每个限时15秒，再开始答题，每道题限时15秒，答案4选1，超时自动跳转到下一题。\n2、金币用途：答题金币可提现，在平台内购买任意服务、在兑换商城兑换相应积分商品。\n3、好友邀请：邀请好友注册账号并答题，好友答对题，邀请人同时获得金币奖励。\n4.答题赢大奖：本市和全国综合排行前三可获大奖。");
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
