package com.example.administrator.wankuoportal.ui.faburenwu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.fragment.FaBuRenWu_fragment;
import com.example.administrator.wankuoportal.global.BaseActivity;

public class RenWuActivity extends BaseActivity {

    private android.widget.FrameLayout framelayout;
    private android.widget.ImageView back;
    private android.widget.TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ren_wu);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        this.framelayout = (FrameLayout) findViewById(R.id.framelayout);
        Fragment fragment = new FaBuRenWu_fragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout, fragment)
                .commit();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
