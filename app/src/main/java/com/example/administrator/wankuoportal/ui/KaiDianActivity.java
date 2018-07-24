package com.example.administrator.wankuoportal.ui;


import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.ui.download.FuWuShangActivity;
import com.example.administrator.wankuoportal.ui.download.GuangGaoZhuActivity;
import com.example.administrator.wankuoportal.ui.download.MeiTiZhuActivity;

public class KaiDianActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.RelativeLayout toolibar;
    private android.widget.TextView textView9;
    private android.widget.TextView meitizhu;
    private android.widget.TextView textView10;
    private android.widget.TextView guanggaozhu;
    private android.widget.TextView fuwushang;
    private TextView meitizhutx;
    private TextView guanggaozhutx;
    private TextView fuwushangtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kai_dian);
        this.fuwushangtx = (TextView) findViewById(R.id.fuwushang_tx);
        this.guanggaozhutx = (TextView) findViewById(R.id.guanggaozhu_tx);
        this.meitizhutx = (TextView) findViewById(R.id.meitizhu_tx);
        this.fuwushang = (TextView) findViewById(R.id.fuwushang);
        this.guanggaozhu = (TextView) findViewById(R.id.guanggaozhu);
        this.meitizhu = (TextView) findViewById(R.id.meitizhu);
        this.toolibar = (RelativeLayout) findViewById(R.id.toolibar);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);


        String fuwu = "<font color='red'>我是服务商：</font>商务服务、生活服务、技术开发、营销推广服务免费入驻开店赚钱，功能模式，签保、投标、派单、雇佣、直接购买。免费招聘、让有需求的客户主动找上门。";
        String guanggao = "<font color='red'>我是广告主：</font>精准广告投放，把广告做成互动答题挑战游戏，让精准用户积极参与，并且可选男女、职业、年龄、地区等，赠送免费店铺，购买、雇佣、投标一站式服务。";
        String meiti = "<font color='red'>我是媒体主：</font>微信、微博、网红、新闻媒体资源免费入驻开店赚钱，功能模式，投标、派单、雇佣、直接购买。有资源就发上来吧，让真正有需求的客户主动找上门。";
        fuwushangtx.setText(Html.fromHtml(fuwu));
        meitizhutx.setText(Html.fromHtml(meiti));
        guanggaozhutx.setText(Html.fromHtml(guanggao));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fuwushang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FuWuShangActivity.class);
            }
        });
        guanggaozhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GuangGaoZhuActivity.class);
            }
        });
        meitizhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MeiTiZhuActivity.class);
            }
        });
    }
}
