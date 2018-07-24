package com.example.administrator.wankuoportal.ui.DaTi;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.jaeger.library.StatusBarUtil;

public class DaTi_WenActivity extends BaseActivity {

    private android.widget.ImageView wenback;
    private android.widget.ImageView wensatrtjy;
    private android.widget.TextView text1;
    private android.widget.TextView text2;
    private ImageButton background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da_ti__wen);
        this.background =  findViewById(R.id.background);
        this.text2 = (TextView) findViewById(R.id.text2);
        this.text1 = (TextView) findViewById(R.id.text1);
        this.wensatrtjy = (ImageView) findViewById(R.id.wen_satrt_jy);
        this.wenback = (ImageView) findViewById(R.id.wen_back);
        StatusBarUtil.setTransparent(DaTi_WenActivity.this);
        wenback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final String type = getIntent().getStringExtra("type");
        if (type.equals("SC")) {
            background.setBackgroundResource(R.drawable.sc);
            wensatrtjy.setImageResource(R.drawable.ksdt);
            text1.setText("猜题赢大奖");
            text2.setText("亲，猜题赢大奖,每道题限时20秒," +
                    "每道题答案分为A、B、C、D4选1,答对得相应金币,答错有正确答案提示！");
        } else {
            background.setBackgroundResource(R.drawable.jy);
            wensatrtjy.setImageResource(R.drawable.ksjy);
            text1.setText("记忆赢大奖");
            text2.setText("亲，先看10个说明和答案,再开始答题。" +
                    "每道题答案分为A、B、C、D4选1,答对得相应金币,答错有正确答案提示!");
        }

        wensatrtjy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("SC")) {
                    startActivity(Wzy_AnswerSCActivity.class);
                } else {
                    startActivity(MemoryAnswerActivity.class);
                }
                finish();
            }
        });

    }
}
