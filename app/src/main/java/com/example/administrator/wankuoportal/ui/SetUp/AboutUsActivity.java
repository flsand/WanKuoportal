package com.example.administrator.wankuoportal.ui.SetUp;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.UpDate;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.JianJieActivity;
import com.example.administrator.wankuoportal.util.DownLoadAPK;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import okhttp3.Call;

public class AboutUsActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.widget.ImageView aboutustitle;
    private android.widget.LinearLayout jianjie;
    private LinearLayout banquan;
    private LinearLayout shiyongxieyi;
    private LinearLayout shuoming;
    private TextView banben;
    boolean isWifi = false;
    private DownloadManager downloadManager;
    private UpDate upDate;
    private TextView gengxin;
    private LinearLayout gengxinlin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        this.gengxinlin = (LinearLayout) findViewById(R.id.gengxin_lin);
        this.gengxin = (TextView) findViewById(R.id.gengxin);
        this.banben = (TextView) findViewById(R.id.banben);
        this.shuoming = (LinearLayout) findViewById(R.id.shuoming);
        this.shiyongxieyi = (LinearLayout) findViewById(R.id.shiyongxieyi);
        this.banquan = (LinearLayout) findViewById(R.id.banquan);
        this.jianjie = (LinearLayout) findViewById(R.id.jianjie);
        this.aboutustitle = (ImageView) findViewById(R.id.aboutus_title);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //万阔简介点击
        jianjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(JianJieActivity.class);
            }
        });
        //十大信条点击
        banquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TenXinTiaoActivity.class);
            }
        });
        //意见反馈点击
        shiyongxieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(YiJianFanKuiActivity.class);
            }
        });
        //服务协议点击
        shuoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FuWuXieYiActivity.class);
            }
        });

        gengxinlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gengxin.getText().toString().equals("有新的版本啦!")) {
                    download(downloadUrl, vName);
                } else {

                }
            }
        });

        try {
            final PackageInfo packInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            banben.setText("当前版本:" + packInfo.versionName);
            downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            isWifi = NetworkInfo.State.CONNECTED == ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

            OkHttpUtils.get().url(Apis.Base + Apis.getandroid).build().execute(new MyStringCallback() {

                @Override
                public void onHttpResponse(String response, int id) throws Exception {
                    L.d(response);
                    upDate = (new Gson()).fromJson(response, UpDate.class);
                    if (upDate.getCode() == 0) {
                        if (!packInfo.versionName.equals(upDate.getData().getVersionName())) {

                            gengxin.setText("有新的版本啦!");
                            gengxin.setTextColor(getResources().getColor(R.color.colorPrimary));
                            downloadUrl = upDate.getData().getDownLink();
                            vName = upDate.getData().getVersionName();

                        } else {
                            gengxin.setText("已经是最新版本了!");
                            gengxin.setTextColor(getResources().getColor(R.color.all_textc));
                        }

                    } else {
                        showShort(upDate.getMsg());
                    }


                }
            });
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    String downloadUrl = "";
    String vName = "";


    //更新apk 相关
    public void download(String downloadUrl, String vName) {
//        if (isWifi) {
//            //wifi下自动下载最新版本，检测目录下是否已经下载好
//            String SDPATH = Environment.getExternalStorageDirectory().getPath() + "/xyx/万阔" + vName + ".apk";//重命名，用来判断下载过没
//            if (new File(SDPATH).exists()) {
//                //安装SDPATH的文件
//                InatallDialog(SDPATH);
//            } else {
//                Toast.makeText(this, "正在后台下载，请稍后...", Toast.LENGTH_SHORT).show();
//                DownLoadAPK.downloadAPK(downloadManager, downloadUrl, "万阔" + vName, "");
//            }
//        } else {
            myDialog(downloadManager, downloadUrl, vName);
//        }
    }

    //更新apk 相关
    public void InatallDialog(final String SDPATH) {
        new AlertDialog.Builder(this).setTitle("新版本提醒")//对话框标题
                .setMessage("已下载完成最新版本，是否现在安装？")//对话框提示正文
                .setIcon(R.mipmap.ic_launcher)//对话框标题上的图片
                .setNegativeButton("暂不升级", new DialogInterface.OnClickListener() {
                    @Override//取消按钮
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AboutUsActivity.this, "请尽快更新", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("立即安装", new DialogInterface.OnClickListener() {
            @Override//确定按钮
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://" + SDPATH), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }).setCancelable(false)//点击其他区域关闭对话框
                .show();
    }

    //更新apk 相关
    public void myDialog(final DownloadManager downloadManager, final String url, final String vName) {
        new AlertDialog.Builder(this).setTitle("新版本提醒")//对话框标题
                .setMessage(upDate.getData().getUpContent())//对话框提示正文
                .setIcon(R.mipmap.ic_launcher)//对话框标题上的图片
                .setNegativeButton("暂不升级", new DialogInterface.OnClickListener() {
                    @Override//取消按钮
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AboutUsActivity.this, "请尽快更新", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("立即升级", new DialogInterface.OnClickListener() {
            @Override//确定按钮
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AboutUsActivity.this, "正在后台下载，请稍后...", Toast.LENGTH_SHORT).show();
                DownLoadAPK.downloadAPK(downloadManager, url, "万阔" + vName, "");
            }
        }).setCancelable(false)//点击其他区域关闭对话H框
                .show();
    }
}
