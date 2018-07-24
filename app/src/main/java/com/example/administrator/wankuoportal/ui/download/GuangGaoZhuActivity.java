package com.example.administrator.wankuoportal.ui.download;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.global.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuangGaoZhuActivity extends BaseActivity {

    @BindView(R.id.guanggaozhu_iv)
    ImageView guanggaozhu_iv;

    @BindView(R.id.bar_iv_back)
    ImageView bar_iv_back;

    @BindView(R.id.bar_tv_title)
    TextView bar_tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guang_gao);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        bar_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bar_tv_title.setText(R.string.shouye_guanggaozhu_title);

        Glide.with(this).load(R.drawable.pic_guanggaozhu).into(guanggaozhu_iv);
    }
}
