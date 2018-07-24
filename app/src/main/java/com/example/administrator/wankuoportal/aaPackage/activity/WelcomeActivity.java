package com.example.administrator.wankuoportal.aaPackage.activity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.example.administrator.wankuoportal.beans.VersionBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.util.DownLoadAPK;
import com.flysand.mylibrary.customView.DialogUtil;
import com.flysand.mylibrary.http.HttpUtil;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.util.JSONUtil;
import com.flysand.mylibrary.util.MyHandler;
import com.flysand.mylibrary.util.PermissionRequestUtil;
import com.flysand.mylibrary.util.Utils;
import com.jaeger.library.StatusBarUtil;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends MyBaseActivity {

    String appViersion;
    private long time;
    private long delayTime = 3000;
    private boolean isGotoSettings = false;
    ViewPager viewpager;
    VersionBean versionBean;
    List<View> viewList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusBarUtil.setTransparentForImageViewInFragment(WelcomeActivity.this, null);
        super.onCreate(savedInstanceState);
        time = System.currentTimeMillis();
        appViersion = Utils.getAppVersion(this);
        updateAppVersion();
    }

    @Override
    protected boolean showTitle() {
        return false;
    }

    private void initPermission() {
        boolean hasPermission = PermissionRequestUtil.getInstance().requestPermission(this,
                PermissionRequestUtil.ACCESS_COARSE_LOCATION,
                PermissionRequestUtil.READ_EXTERNAL_STORAGE,
                PermissionRequestUtil.CAMERA
        );
        Utils.print("hasPermission = " + hasPermission);
        if (hasPermission) {
            jumpMain();
        }
        Utils.print("AppVersion =" + Utils.getAppVersion(this).replace(".", ""));
    }


    private void jumpMain() {
        long delay = delayTime - (System.currentTimeMillis() - time);
        if (delay > 0) {
            new MyHandler((int) delay) {
                @Override
                public void run() {
                    startActivity(new Intent(WelcomeActivity.this, NewMainActivity.class));
                    finish();
                }
            };
        } else {
            startActivity(new Intent(this, NewMainActivity.class));
            finish();
        }
    }

    private void updateAppVersion() {
        HttpUtil.getInstance(this).httpGet("appVersion", new RequestParams(), Apis.Base + Apis.getandroid);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionRequestUtil.ACCESS_COARSE_LOCATION) {
            boolean ispermission = true;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    ispermission = false;
                    break;
                }
            }
            if (ispermission) {
                jumpMain();
            } else {
                gotoSettings();
            }
        }
    }

    private void showGuide() {
        Utils.print("version = " + appViersion);
        if (Utils.getSaveStringData(this, "version", "0").equals(appViersion)) {
            initPermission();
        } else {
            long delay = delayTime - (System.currentTimeMillis() - time);
            if (delay > 0) {
                new MyHandler((int) delay) {
                    @Override
                    public void run() {
                        initViewPager();
                    }
                };
            } else {
                initViewPager();
            }
        }
    }

    private void initViewPager() {
        if (Utils.isApkDebugable(getApplicationContext())) {
            new UserService(MyApplication.context).setislogin("1");
        }

        setContentView(R.layout.activity_welcome);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
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
                Utils.saveData(WelcomeActivity.this, "version", appViersion);
                initPermission();
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

    //更新apk 相关
    public void download(String downloadUrl, String vName) {
        String SDPATH = Environment.getExternalStorageDirectory().getPath() + "/xyx/万阔" + vName + ".apk";//重命名，用来判断下载过没
        if (new File(SDPATH).exists()) {
            //安装SDPATH的文件
            InatallDialog(SDPATH);
        } else {
            DialogUtil dialogUtil = new DialogUtil(WelcomeActivity.this) {
                @Override
                public void confirmClick(View view) {
                    Toast.makeText(getApplicationContext(), "正在后台下载，请稍后...", Toast.LENGTH_SHORT).show();
                    DownLoadAPK.downloadAPK((DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE), downloadUrl, "万阔" + vName, "");
                    jumpMain();
                }

                @Override
                public void cancelClick(View view) {
                    updateCancel();
                }
            }.setContent(versionBean.getUpContent())
                    .setConfirmBtnText("立即升级");
            dialogUtil.showDialog(2, 0);
        }
    }

    private void updateCancel() {
        if ("1".equals(versionBean.getIsUpdate())) {
            new MyHandler(1000) {
                @Override
                public void run() {
                    finish();
                }
            };
        } else {
            showGuide();
        }
    }

    //安装apk 相关
    public void InatallDialog(final String SDPATH) {
        DialogUtil dialogUtil = new DialogUtil(WelcomeActivity.this) {
            @Override
            public void confirmClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://" + SDPATH), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }

            @Override
            public void cancelClick(View view) {
                updateCancel();
            }
        }.setContent(versionBean.getUpContent())
                .setConfirmBtnText("立即安装");
        dialogUtil.showDialog(2, 0);
    }

    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) {
        if ("appVersion".equals(type)) {
            versionBean = JSONUtil.toJavaBean(VersionBean.class, jsonObject);
            boolean isUpload = false;
            try {
                String oldVersion = appViersion.replace(".", "");
                String newVersion = versionBean.getVersionName().replace(".", "");
                Utils.print("oldVersion = " + oldVersion + "   newVersion = " + newVersion);
                if (Integer.valueOf(newVersion) > Integer.valueOf(oldVersion)) {
                    isUpload = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isUpload) {
                download(versionBean.getDownLink(), versionBean.getVersionName());
            } else {
                showGuide();
            }
        }
    }

    @Override
    public void onHttpFailure(String type, String msg) {
        Utils.print("onHttpFailure  type = " + type);
        if ("appVersion".equals(type)) {
            showGuide();
        }
    }
}
