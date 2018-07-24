package com.example.administrator.wankuoportal.ui;

import android.annotation.SuppressLint;
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
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.Regist;
import com.example.administrator.wankuoportal.beans.UpDate;
import com.example.administrator.wankuoportal.fragment.main.MerchantsFragment;
import com.example.administrator.wankuoportal.fragment.main.ReleaseFragment;
import com.example.administrator.wankuoportal.fragment.main.Showye_fragment;
import com.example.administrator.wankuoportal.fragment.main.Tiaozhan_fragment;
import com.example.administrator.wankuoportal.fragment.main.Wode_guzhu_fragment;
import com.example.administrator.wankuoportal.fragment.main.Wode_huiyuan_fragment;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.util.DownLoadAPK;
import com.example.administrator.wankuoportal.util.LogUtils;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.flysand.mylibrary.crash.CrashHandler;
import com.flysand.mylibrary.util.Utils;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import okhttp3.Call;


public class MainActivity extends AppCompatActivity {

    final UserService u = new UserService(MainActivity.this);
    private android.widget.FrameLayout viewPager;
    private android.widget.ImageView shouyeima;
    private android.widget.TextView shouyetx;
    private android.widget.LinearLayout shouyelin;
    private android.widget.ImageView fenleiima;
    private android.widget.TextView fenleitx;
    private android.widget.LinearLayout fenleilin;
    private android.widget.ImageView paotuiima;
    private android.widget.ImageView fbrw;
    private android.widget.TextView releaseTv;
    private android.widget.TextView paotuitx;
    private android.widget.LinearLayout paotuilin;
    private android.widget.ImageView wodeima;
    private android.widget.TextView wodetx;
    private android.widget.LinearLayout wodelin;
    final Fragment shouye = new Showye_fragment();
    //    final Fragment fenlei = new Fenlei_fragment();
    final Fragment fenlei = new MerchantsFragment();
    final Fragment paotui = new Tiaozhan_fragment();
    Wode_guzhu_fragment guzhuF;
    Wode_huiyuan_fragment huiyuanF;
    boolean isViewLoad = false;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用黄油刀进行View注解
        ButterKnife.bind(this);
        this.wodelin = (LinearLayout) findViewById(R.id.wode_lin);
        this.wodetx = (TextView) findViewById(R.id.wode_tx);
        this.wodeima = (ImageView) findViewById(R.id.wode_ima);
        this.paotuilin = (LinearLayout) findViewById(R.id.paotui_lin);
        this.paotuitx = (TextView) findViewById(R.id.paotui_tx);
        this.paotuiima = (ImageView) findViewById(R.id.paotui_ima);
        this.fbrw = (ImageView) findViewById(R.id.fbrw);
        this.releaseTv = (TextView) findViewById(R.id.release_tv);
        this.fenleilin = (LinearLayout) findViewById(R.id.fenlei_lin);
        this.fenleitx = (TextView) findViewById(R.id.fenlei_tx);
        this.fenleiima = (ImageView) findViewById(R.id.fenlei_ima);
        this.shouyelin = (LinearLayout) findViewById(R.id.shouye_lin);
        this.shouyetx = (TextView) findViewById(R.id.shouye_tx);
        this.shouyeima = (ImageView) findViewById(R.id.shouye_ima);
        this.viewPager = (FrameLayout) findViewById(R.id.viewPager);
        initclick();
        UpdateInfo();
        StatusBarUtil.setTransparentForImageViewInFragment(MainActivity.this, null);
        //首页
        u.settabposition("0");
        shouyeima.setImageResource(R.drawable.shouye_choose);
        shouyetx.setTextColor(getResources().getColor(R.color.colorPrimary));
        Showye_fragment shouye = new Showye_fragment();
//        android.app.Fragment mainFragment = new MainFragment_();
//
//        FragmentManager fm = getFragmentManager();
//        // 开启Fragment事务
//        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.add(R.id.viewPager, mainFragment, "mainFragment");
//        transaction.commit();
        //使用指定的fragment切换当前页面
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.viewPager, shouye)
                .commit();

        new UserService(MainActivity.this).setislogin("1");
        initUserInfo();

        if (!Constant.DefaultValue.GUIDE_VER.equals(Utils.getSaveStringData(this, Constant.SharedKey.GUIDE_MASK, "0"))) {
            //view加载完成时回调
            findViewById(R.id.mainRelativeLayout).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (!isViewLoad) {
                        isViewLoad = true;
                        try {
                            shouye.initGuide(MainActivity.this, fbrw, paotuilin);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

    }

    /**
     * 初始化用户信息的方法
     */
    private void initUserInfo() {
        String time = new Date().getTime() + "";
        String token = new UserService(this).gettoken();
        final String accountId = new UserService(this).getaccountid();
        CrashHandler.getInstance(getApplicationContext()).putParams("phone", new UserService(this).getphone());
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.getuserinfo + "?token=" + accountId + "," + time + "," + token;
        //http://www.wankuo5888.com/api/auth/userinfo/getuserinfo?token=538,1521293298390,faec14463136b8993a649554ad43ab9b
        Utils.print("initUserInfo url:" + url);
        OkHttpUtils
                .post()
                .url(url)
                .addParams("accountId", accountId)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("getUserInfo Error");
                    }

                    @Override
                    public void onLoginOutTime(String msg) {
                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        LogUtils.e("getUserInfo:", response);
                        Utils.print("getUserInfo  = " + response);
                        Gson gson = new Gson();
                        Regist regist = gson.fromJson(response, Regist.class);
                        if (regist.getCode() == 2) {
                            new UserService(MainActivity.this).setislogin("1");
                            new UserService(MainActivity.this).settoken("");
                            new UserService(MainActivity.this).setaccountid("");
//                            Toast.makeText(MainActivity.this, "登录信息过期，请重新登录", Toast.LENGTH_SHORT).show();
                        } else {
                            new UserService(MainActivity.this).setislogin("0");
                            if (regist.getData().getAaccountInfo().getProvince().isEmpty()) {

                            } else {
                                String address = "中国/" + regist.getData().getAaccountInfo().getProvince()
                                        + "/" + regist.getData().getAaccountInfo().getCity()
                                        + "/" + regist.getData().getAaccountInfo().getArea();
                                new UserService(MainActivity.this).setAddress(address);
                            }

                        }
                    }
                });
    }

    boolean isWifi = false;
    private DownloadManager downloadManager;
    private UpDate upDate;

    //更新apk 相关
    private void UpdateInfo() {
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        isWifi = NetworkInfo.State.CONNECTED == ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        try {
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            final PackageInfo packInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            //获取到versionNum 用vName接收, downloadUrl = http://file.cishan123.org/yst_1.7.apk

            OkHttpUtils.get().url(Apis.Base + Apis.getandroid).build().execute(new MyStringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onHttpResponse(String response, int id) throws Exception {
                    LogUtils.e("版本信息:", response);
                    upDate = (new Gson()).fromJson(response, UpDate.class);
                    if (upDate.getCode() == 0) {
                        String downloadUrl = "http://imtt.dd.qq.com/16891/275724DBB71F4A69C368C54043B5CFD9.apk?fsname=com.example.administrator.wankuoportal_1.0.9_11.apk&csr=1bbd";
                        L.d("下载地址==", downloadUrl);

                        boolean isUpload = false;
                        try {
                            String oldVersion = packInfo.versionName.replace(".", "");
                            String newVersion = upDate.getData().getVersionName().replace(".", "");
                            Utils.print("oldVersion = " + oldVersion + "   newVersion = " + newVersion);
                            if (Integer.valueOf(newVersion) > Integer.valueOf(oldVersion)) {
                                isUpload = true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (isUpload) {
                            if ("1".equals(upDate.getData().getIsUpdate())) {
                                download(upDate.getData().getDownLink(), upDate.getData().getVersionName());
                            } else {
                                com.flysand.mylibrary.customView.DialogUtil dialogUtil = new com.flysand.mylibrary.customView.DialogUtil(MainActivity.this) {
                                    @Override
                                    public void confirmClick(View view) {
                                        download(upDate.getData().getDownLink(), upDate.getData().getVersionName());
                                    }
                                }.setContent(upDate.getData().getUpContent())
                                        .setConfirmBtnText("立即升级");
                                dialogUtil.showDialog(2, 0);

                            }
                        }


//                        //此处有误,应用VersionCode来判断
//                        if (!packInfo.versionName.equals(upDate.getData().getVersionName())) {
//                            download(upDate.getData().getDownLink(), upDate.getData().getVersionName());
//                        } else {
//                            // TODO: 2017/10/30 0030
//                        }

                    } else {
                        Toast.makeText(MainActivity.this, upDate.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

//            packInfo.versionName;
            //下面执行网络操作访问接口的目前最新版apk版本信息，如我这里https://api.cishan123.org/v2.2/api/AutoUpdate/UpdateInfoNew?type=yst&version=1.2

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    //更新apk 相关
    public void download(String downloadUrl, String vName) {
//        if (isWifi) {
        //wifi下自动下载最新版本，检测目录下是否已经下载好
        String SDPATH = Environment.getExternalStorageDirectory().getPath() + "/xyx/万阔" + vName + ".apk";//重命名，用来判断下载过没
        if (new File(SDPATH).exists()) {
            //安装SDPATH的文件
            InatallDialog(SDPATH);
        } else {
            Toast.makeText(this, "正在后台下载，请稍后...", Toast.LENGTH_SHORT).show();
            DownLoadAPK.downloadAPK(downloadManager, downloadUrl, "万阔" + vName, "");
        }
//        } else {
//            myDialog(downloadManager, downloadUrl, vName);
//        }
    }

    //安装apk 相关
    public void InatallDialog(final String SDPATH) {
        if (upDate.getData().getIsUpdate() == 1) {
            new AlertDialog.Builder(this)
                    .setTitle("新版本提醒")//对话框标题
                    .setMessage("已下载完成最新版本，是否现在安装(不更新会导致小万崩溃的)？")//对话框提示正文
                    .setIcon(R.mipmap.ic_launcher)//对话框标题上的图片
                    .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                        @Override//取消按钮
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
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
        } else {
            new AlertDialog.Builder(this).setTitle("新版本提醒")//对话框标题
                    .setMessage("已下载完成最新版本，是否现在安装？")//对话框提示正文
                    .setIcon(R.mipmap.ic_launcher)//对话框标题上的图片
                    .setNegativeButton("暂不升级", new DialogInterface.OnClickListener() {
                        @Override//取消按钮
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "请尽快更新", Toast.LENGTH_SHORT).show();
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

    }

    //移动网络更新apk 相关
    public void myDialog(final DownloadManager downloadManager, final String url, final String vName) {

        if (upDate.getData().getIsUpdate() == 1) {
            new AlertDialog.Builder(this).setTitle("新版本提醒")//对话框标题
                    .setMessage("不更新会导致小万崩溃的")//对话框提示正文
                    .setIcon(R.mipmap.ic_launcher)//对话框标题上的图片
                    .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                        @Override//取消按钮
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }
                    }).setPositiveButton("立即升级", new DialogInterface.OnClickListener() {
                @Override//确定按钮
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "正在后台下载，请稍后...", Toast.LENGTH_SHORT).show();
                    DownLoadAPK.downloadAPK(downloadManager, url, "万阔" + vName, "");
                }
            }).setCancelable(false)//点击其他区域关闭对话框
                    .show();
        } else {
            new AlertDialog.Builder(this).setTitle("新版本提醒")//对话框标题
                    .setMessage(upDate.getData().getUpContent())//对话框提示正文
                    .setIcon(R.mipmap.ic_launcher)//对话框标题上的图片
                    .setNegativeButton("暂不升级", new DialogInterface.OnClickListener() {
                        @Override//取消按钮
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "请尽快更新", Toast.LENGTH_SHORT).show();
                        }
                    }).setPositiveButton("立即升级", new DialogInterface.OnClickListener() {
                @Override//确定按钮
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "正在后台下载，请稍后...", Toast.LENGTH_SHORT).show();
                    DownLoadAPK.downloadAPK(downloadManager, url, "万阔" + vName, "");
                }
            }).setCancelable(false)//点击其他区域关闭对话框
                    .show();
        }

    }


    private void initclick() {

        fbrw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent it = new Intent(MainActivity.this, RenWuActivity.class);
//                startActivity(it);
                initview();
                releaseTv.setTextColor(getResources().getColor(R.color.colorPrimary));
                u.settabposition("5");
                Fragment fragment = new ReleaseFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.viewPager, fragment)
                        .commit();
            }
        });
        shouyelin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                shouyeima.setImageResource(R.drawable.shouye_choose);
                shouyetx.setTextColor(getResources().getColor(R.color.colorPrimary));
                //首页

                u.settabposition("0");
                //使用指定的fragment切换当前页面
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.viewPager, shouye)
                        .commit();
            }
        });
        fenleilin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                fenleiima.setImageResource(R.drawable.fenlei_choose);
                fenleitx.setTextColor(getResources().getColor(R.color.colorPrimary));
                //分类

                u.settabposition("1");
                u.setfenleiposition("0");
                //使用指定的fragment切换当前页面
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.viewPager, fenlei)
                        .commit();
            }
        });
        paotuilin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchAnswer();
            }
        });
        wodelin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                wodeima.setImageResource(R.drawable.wode_choose);
                wodetx.setTextColor(getResources().getColor(R.color.colorPrimary));
                if (u.getshenfen().equals("guzhu")) {
                    //我的
                    guzhuF = new Wode_guzhu_fragment();
                    //使用指定的fragment切换当前页面
                    u.settabposition("4");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.viewPager, guzhuF)
                            .commit();
                } else {
                    //我的
                    huiyuanF = new Wode_huiyuan_fragment();
                    //使用指定的fragment切换当前页面
                    u.settabposition("4");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.viewPager, huiyuanF)
                            .commit();
                }
            }
        });
    }


    public void switchAnswer() {
        initview();
        paotuiima.setImageResource(R.drawable.paotui_choose);
        paotuitx.setTextColor(getResources().getColor(R.color.colorPrimary));
        u.settabposition("3");
        //使用指定的fragment切换当前页面
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.viewPager, paotui)
                .commit();
        //跑腿
    }

    public void gotoWode() {
        wodelin.callOnClick();
    }

    private void initview() {
        shouyeima.setImageResource(R.drawable.shouye_nochoose);
        shouyetx.setTextColor(getResources().getColor(R.color.henhui));
        fenleiima.setImageResource(R.drawable.fenlei_nochoose);
        fenleitx.setTextColor(getResources().getColor(R.color.henhui));
        paotuiima.setImageResource(R.drawable.paotui_nochoose);
        paotuitx.setTextColor(getResources().getColor(R.color.henhui));
        wodeima.setImageResource(R.drawable.wode_nochoose);
        wodetx.setTextColor(getResources().getColor(R.color.henhui));
        releaseTv.setTextColor(getResources().getColor(R.color.henhui));
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();      //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            new UserService(MyApplication.context).setshouyecity("0");
            finish();
            System.exit(0);

        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String tabposition = u.gettabposition();
        if (tabposition.equals("0")) {
            initview();
            shouyeima.setImageResource(R.drawable.shouye_choose);
            shouyetx.setTextColor(getResources().getColor(R.color.colorPrimary));
            Fragment shouye = new Showye_fragment();
            u.settabposition("0");
            //使用指定的fragment切换当前页面
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.viewPager, shouye)
                    .commit();
        } else if (tabposition.equals("1")) {
            //分类
            initview();
            fenleiima.setImageResource(R.drawable.fenlei_choose);
            fenleitx.setTextColor(getResources().getColor(R.color.colorPrimary));
//            Fragment fenlei = new Fenlei_fragment();
            MerchantsFragment fenlei = new MerchantsFragment();
            u.settabposition("1");
            fenlei.setSubLabelPosition(u.getSubLabelPosition());
            //使用指定的fragment切换当前页面
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.viewPager, fenlei)
                    .commit();
        } else if (tabposition.equals("3")) {
            initview();
            paotuiima.setImageResource(R.drawable.paotui_choose);
            paotuitx.setTextColor(getResources().getColor(R.color.colorPrimary));
            u.settabposition("3");
            //使用指定的fragment切换当前页面
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.viewPager, paotui)
                    .commit();
            //跑腿
        } else if (tabposition.equals("4")) {
            initview();
            wodeima.setImageResource(R.drawable.wode_choose);
            wodetx.setTextColor(getResources().getColor(R.color.colorPrimary));
            if (u.getshenfen().equals("guzhu")) {
                //我的
                Fragment wode = new Wode_guzhu_fragment();
                //使用指定的fragment切换当前页面
                u.settabposition("4");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.viewPager, wode)
                        .commit();
            } else {
                //我的
                Fragment wode = new Wode_huiyuan_fragment();
                //使用指定的fragment切换当前页面
                u.settabposition("4");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.viewPager, wode)
                        .commit();
            }

        } else if (tabposition.equals("5")) {
            initview();
            releaseTv.setTextColor(getResources().getColor(R.color.colorPrimary));
//            Fragment fragment = new FaBuRenWu_fragment();
            Fragment fragment = new ReleaseFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.viewPager, fragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
