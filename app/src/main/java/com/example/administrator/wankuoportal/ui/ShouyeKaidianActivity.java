package com.example.administrator.wankuoportal.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShouyeKaidianActivity extends AppCompatActivity {

    @BindView(R.id.shouye_kaidian_iv)
    ImageView shouye_kaidian_iv;

    @BindView(R.id.bar_iv_back)
    ImageView bar_back;

    @BindView(R.id.bar_tv_title)
    TextView bar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouye_kaidian);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bar_title.setText(R.string.shouye_kaidian_title);

        Glide.with(this).load(R.drawable.pic_kaidian).into(shouye_kaidian_iv);
    }
}
