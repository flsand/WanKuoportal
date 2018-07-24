package com.example.administrator.wankuoportal.ui.yindaoye;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.MainActivity;
import com.flysand.mylibrary.util.MyHandler;
import com.flysand.mylibrary.util.PermissionRequestUtil;
import com.flysand.mylibrary.util.Utils;

import java.util.ArrayList;
import java.util.List;


public class AppLauncherActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private long time = 3000;
    private boolean isGotoSettings = false;
    ViewPager viewpager;
    String appViersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_app_launcher);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        new UserService(MyApplication.context).setshouyecity("0");

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        animation.setFillAfter(true);

        appViersion = Utils.getAppVersion(this);
        Utils.print("version = " + appViersion);
        if (Utils.getSaveStringData(this, "version", "0").equals(appViersion)) {
            initPermission(true);
        } else {
            initViewPager();
        }
    }

    List<View> viewList;

    private void initViewPager() {
        RequestManager glide = Glide.with(this);
        viewList = new ArrayList<>();// 将要分页显示的View装入数组中
        for (int i = 0; i < 3; i++) {
            View layout = LayoutInflater.from(this).inflate(R.layout.guide_layout, null);
            ImageView imageView = (ImageView) layout.findViewById(R.id.bg_img);
            Button btn = (Button) layout.findViewById(R.id.guide_btn);
            switch (i) {
                case 0:
                    glide.load(R.drawable.guide1).into(imageView);
                    break;
                case 1:
                    glide.load(R.drawable.guide2).into(imageView);
                    break;
                case 2:
                    glide.load(R.drawable.guide3).into(imageView);
                    break;
            }
            btn.setVisibility(View.VISIBLE);
            btn.setOnClickListener(v -> {
                Utils.saveData(AppLauncherActivity.this, "version", appViersion);
                initPermission(false);
            });
            viewList.add(layout);
        }

        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        };

        viewpager.setAdapter(pagerAdapter);

    }

    private void initPermission(boolean isDelay) {
//        ActivityCompat.requestPermissions(this,
//                new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION}, PermissionRequestUtil.ACCESS_COARSE_LOCATION);

        boolean hasPermission = PermissionRequestUtil.getInstance().requestPermission(this,
                PermissionRequestUtil.ACCESS_COARSE_LOCATION,
                PermissionRequestUtil.READ_EXTERNAL_STORAGE,
                //  PermissionRequestUtil.RECORD_AUDIO,
                PermissionRequestUtil.CAMERA
        );
        Utils.print("hasPermission = " + hasPermission);
        if (hasPermission) {
            if (isDelay) {
                goMain();
            } else {
                superFinish();
            }
        }
        Utils.print("AppVersion =" + Utils.getAppVersion(this).replace(".", ""));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Utils.print("onRequestPermissionsResult");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionRequestUtil.ACCESS_COARSE_LOCATION) {
            boolean ispermission = true;
            Utils.print("permissions = " + permissions.length);
            Utils.print("grantResults = " + grantResults.length);
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    ispermission = false;
                    break;
                }
            }
            if (ispermission) {
                goMain();
            } else {
                gotoSettings();
            }
        }
    }

    private void gotoSettings() {
        if (!isGotoSettings) {
            isGotoSettings = true;
            // 拒绝权限
            Toast.makeText(this, "您拒绝了应用权限app将无法正常使用\n请修改权限设置", Toast.LENGTH_LONG).show();
            new MyHandler(1000) {
                @Override
                public void run() {
                    finish();
                }
            };
            Uri packageURI = Uri.parse("package:" + getPackageName());
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
            startActivity(intent);
        }
    }

    private void goMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                superFinish();
            }
        }, time);
    }

    public void superFinish() {

//        if (Utils.isApkDebugable(this, getPackageName()))
//            startActivity(NewMainActivity.class);
//        else
            startActivity(MainActivity.class);

        super.finish();
    }

    @Override
    public void finish() {
    }

}
