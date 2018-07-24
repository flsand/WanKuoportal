package com.example.administrator.wankuoportal.ui.denglu;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.Occupation;
import com.example.administrator.wankuoportal.beans.Regist;
import com.example.administrator.wankuoportal.beans.Updateuserinfo;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.AddressPickTask;
import com.example.administrator.wankuoportal.util.EditDialog;
import com.example.administrator.wankuoportal.util.GifSizeFilter;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.util.FileSizeUtil;
import com.flysand.mylibrary.util.MyToast;
import com.flysand.mylibrary.util.Utils;
import com.google.gson.Gson;
import com.nanchen.compresshelper.CompressHelper;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.util.ConvertUtils;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.MediaType;

public class Personal_SetupActivity extends BaseActivity {

    private android.widget.LinearLayout changehead;
    private android.widget.LinearLayout changenickname;
    private android.widget.LinearLayout changename;
    private android.widget.TextView sextx;
    private android.widget.LinearLayout changesex;
    private android.widget.TextView timetx;
    private android.widget.LinearLayout changetime;
    private android.widget.TextView areatx;
    private android.widget.LinearLayout changeArea;
    private android.widget.TextView occupationtx;
    private android.widget.LinearLayout changeOccupation;
    private android.widget.TextView phonetx;
    private android.widget.LinearLayout changephone;
    private android.widget.TextView signtx;
    private android.widget.LinearLayout changesign;

    private android.widget.Button baocun;
    private android.widget.EditText ednick;
    private android.widget.EditText edname;
    Regist.DataBean.AaccountInfoBean aAccountInfo;
    private android.widget.ImageView back;
    private TextView title;
    private CircleImageView touxiang;
    private static final int REQUEST_CODE_CHOOSE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal__setup);
        this.touxiang = (CircleImageView) findViewById(R.id.touxiang);
        this.baocun = (Button) findViewById(R.id.baocun);
        this.changesign = (LinearLayout) findViewById(R.id.change_sign);
        this.signtx = (TextView) findViewById(R.id.sign_tx);
        this.changephone = (LinearLayout) findViewById(R.id.change_phone);
        this.phonetx = (TextView) findViewById(R.id.phone_tx);
        this.changeOccupation = (LinearLayout) findViewById(R.id.change_Occupation);
        this.occupationtx = (TextView) findViewById(R.id.occupation_tx);
        this.changeArea = (LinearLayout) findViewById(R.id.change_Area);
        this.areatx = (TextView) findViewById(R.id.area_tx);
        this.changetime = (LinearLayout) findViewById(R.id.change_time);
        this.timetx = (TextView) findViewById(R.id.time_tx);
        this.changesex = (LinearLayout) findViewById(R.id.change_sex);
        this.sextx = (TextView) findViewById(R.id.sex_tx);
        this.changename = (LinearLayout) findViewById(R.id.change_name);
        this.edname = (EditText) findViewById(R.id.ed_name);
        this.changenickname = (LinearLayout) findViewById(R.id.change_nickname);
        this.ednick = (EditText) findViewById(R.id.ed_nick);
        this.changehead = (LinearLayout) findViewById(R.id.change_head);

        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initview();
        initOnclick();

    }

    String time = "";
    String sign = "";


    private void initOnclick() {
        edname.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
            }
        });
        ednick.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
            }
        });


        changesex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSexChooseDialog();
            }
        });

        changetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onYearMonthDayPicker();
            }
        });

        changeArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddressPicker();
            }
        });

        changesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditDialog editDialog = new EditDialog(Personal_SetupActivity.this);
                editDialog.showDialog("请输入个性签名", new EditDialog.onEdit() {
                    @Override
                    public void onSure(String inputContent) {
                        String sign = inputContent;
                        aAccountInfo.setAutograph(sign);
                        signtx.setText(sign);
                    }
                });
            }
        });

        //头像修改
        changehead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Matisse.from(Personal_SetupActivity.this)
                        .choose(MimeType.ofImage(), false)
                        .countable(true)
                        .capture(true)
                        .captureStrategy(
                                new CaptureStrategy(true, "com.example.administrator.wankuoportal.fileprovider"))
                        .maxSelectable(1)
                        .addFilter(new GifSizeFilter(640, 640, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(
                                getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);
            }
        });

        //职业选择
        changeOccupation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = Apis.Base + Apis.getbyname;
                OkHttpUtils
                        .get()
                        .url(url)
                        .addParams("dictionaryname", "occupation")
                        .build()
                        .execute(new MyStringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, final int id) {
                                L.d(response);
                                Occupation occupation = gson.fromJson(response, Occupation.class);
                                if (occupation.getCode() == 0) {
                                    String[] items = new String[occupation.getDatas().size()];

                                    for (int i = 0; i < occupation.getDatas().size(); i++) {
                                        items[i] = occupation.getDatas().get(i).getContent();
                                    }

                                    OptionPicker picker = new OptionPicker(Personal_SetupActivity.this, items);
                                    picker.setOffset(2);
                                    picker.setSelectedIndex(1);
                                    picker.setTextSize(15);

                                    picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                                        @Override
                                        public void onOptionPicked(int index, String item) {
                                            occupationtx.setText(item);
                                            Occupationcode = index + 1;
                                        }

                                    });
                                    picker.show();
                                }


                            }
                        });
            }
        });

        //保存
        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(sextx.getText())) {
                    showShort("请选择性别");
                } else {
                    if (TextUtils.isEmpty(areatx.getText())) {
                        showShort("请选择城市");
                    } else {
//                        if (TextUtils.isEmpty(edname.getText())) {
//                            showShort("请输入名字");
//                        } else {
//                            aAccountInfo.setName(edname.getText().toString());
                        if (TextUtils.isEmpty(ednick.getText())) {
                            showShort("请输入昵称");
                        } else {
                            aAccountInfo.setNickname(ednick.getText().toString());
                            if (TextUtils.isEmpty(timetx.getText())) {
                                showShort("请选择出生年月");
                            } else {
                                aAccountInfo.setBirthDate(timetx.getText().toString());
                                if (TextUtils.isEmpty(occupationtx.getText())) {
                                    showShort("请选择职业");
                                } else {
                                    if (TextUtils.isEmpty(phonetx.getText().toString())) {
                                        ProjectUtil.jumpLogin(Personal_SetupActivity.this);
                                        finish();
                                    } else {
                                        aAccountInfo.setOccupation(Occupationcode + "");
                                        goSubmit();
                                    }

                                }
//                                }

                            }
                        }
                    }
                }

            }
        });
    }

    private int Occupationcode;

    private void initview() {
        Utils.print("initview()");
        aAccountInfo = new Regist.DataBean.AaccountInfoBean();
        String time = new Date().getTime() + "";
        String token = new UserService(MyApplication.context).gettoken();
        String accountId = new UserService(MyApplication.context).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.getuserinfo + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("accountId", accountId)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Utils.print("onError");
                        e.printStackTrace();
                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        Utils.print("onResponse ");
                        Utils.print("response =  " + response);
                        L.d(response);
                        Regist regist = gson.fromJson(response, Regist.class);
                        if (regist.getCode() == 0) {
                            aAccountInfo = regist.getData().getAaccountInfo();
                            ednick.setText(regist.getData().getAaccountInfo().getNickname());
                            edname.setText(regist.getData().getAaccountInfo().getName());
                            if (regist.getData().getAaccountInfo().getSex() == 1) {
                                sextx.setText("男");
                            } else if (regist.getData().getAaccountInfo().getSex() == 2) {
                                sextx.setText("女");
                            } else {

                            }
                            timetx.setText(regist.getData().getAaccountInfo().getBirthDate());
                            areatx.setText(aAccountInfo.getProvince() + aAccountInfo.getCity() + aAccountInfo.getArea());
                            occupationtx.setText(aAccountInfo.getOccupationName());
                            phonetx.setText(aAccountInfo.getPhone());
                            signtx.setText(aAccountInfo.getAutograph());

                            if (aAccountInfo.getHeadIcon().isEmpty()) {
                                Glide.with(Personal_SetupActivity.this).load(R.drawable.tou).into(touxiang);
                            } else {
                                String path = Apis.Baseima + aAccountInfo.getHeadIcon();
                                Glide.with(Personal_SetupActivity.this).load(path).into(touxiang);
                            }


                        } else if (regist.getCode() == 2) {
                            new UserService(MyApplication.context).setislogin("1");
                            startActivity(LoginActivity.class);
                        }


                    }
                });


    }

    //设置个人信息
    private void goSubmit() {
        time = new Date().getTime() + "";
        String token = new UserService(MyApplication.context).gettoken();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.updateuserinfo + "?token=" + aAccountInfo.getAccountId() + "," + time + "," + token;


        OkHttpUtils
                .postString()
                .url(url)
                .content(new Gson().toJson(aAccountInfo))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        Updateuserinfo u = gson.fromJson(response, Updateuserinfo.class);
                        if (u.getCode() == 0) {
                            ednick.setText(u.getData().getAaccountInfo().getNickname());
                            edname.setText(u.getData().getAaccountInfo().getName());
                            if (u.getData().getAaccountInfo().getSex() == 1) {
                                sextx.setText("女");
                            } else if (u.getData().getAaccountInfo().getSex() == 2) {
                                sextx.setText("男");
                            } else {
                                sextx.setText("未选");
                            }
                            timetx.setText(u.getData().getAaccountInfo().getBirthDate());
                            occupationtx.setText(u.getData().getAaccountInfo().getOccupation());
                            phonetx.setText(u.getData().getAaccountInfo().getPhone());
                            signtx.setText(u.getData().getAaccountInfo().getAutograph());
                            if (!photoPath.isEmpty()) {
                                goLuBan();
                            } else {
                                Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        } else if (u.getCode() == 2) {
                            showShort(u.getMsg());
                            startActivity(LoginActivity.class);
                            new UserService(MyApplication.context).setislogin("1");
                        } else {
                            showShort(u.getMsg());
                        }
                    }
                });

    }

    //压缩图片并上传
    private void goLuBan() {

//        if (1 == 1) {
//            new MyToast(this).setText(DefultHttpAnalysisHelper.ERR_MSG);
//            return;
//        }

        File file = new File(photoPath);
        Utils.print("file = " + FileSizeUtil.getFileOrFilesSize(file.getAbsolutePath(), FileSizeUtil.SIZETYPE_KB));

        //压缩
        File newFile = new CompressHelper.Builder(this)
                .setMaxWidth(720)  // 默认最大宽度为720
                .setMaxHeight(960) // 默认最大高度为960
                .setQuality(80)    // 默认压缩质量为80
                .setFileName("app" + System.currentTimeMillis()) // 设置你需要修改的文件名
                .setCompressFormat(Bitmap.CompressFormat.JPEG) // 设置默认压缩为jpg格式
                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).getAbsolutePath())
                .build()
                .compressToFile(file);
        Utils.print("newFile = " + FileSizeUtil.getFileOrFilesSize(newFile.getAbsolutePath(), FileSizeUtil.SIZETYPE_KB));

        uploadFilee(newFile);

    }

    private void uploadFilee(File file) {
        time = new Date().getTime() + "";
        String token = new UserService(MyApplication.context).gettoken();
        token = MD5Util.md5(time + token);
        OkHttpUtils
                .post()
                .addFile("headIcon", "temp.jpg", file)
                .url(Apis.Base + Apis.updateheadicon + "?token=" + aAccountInfo.getAccountId()
                        + "," + time + "," + token
                )
                .addParams("accountId", aAccountInfo.getAccountId() + "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onHttpSuccess(String s, JSONObject jsonObject) throws Exception {
                        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onHttpFailure(String s, String s1) throws Exception {
                        super.onHttpFailure(s, s1);
                        new MyToast(MyApplication.context).setText(s1);
                    }
                });
    }

    private String photoPath = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            if (data != null) {
                photoPath = Matisse.obtainPathResult(data).get(0).toString();
                Glide.with(this).load(photoPath).into(touxiang);
                L.d(photoPath);
            }

        }


    }

    public void onAddressPicker() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                showShort("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
//                    showShort(province.getAreaName() + city.getAreaName());
                    areatx.setText(province.getAreaName() + "-" + city.getAreaName());
                } else {
                    aAccountInfo.setCountry("中国");
                    aAccountInfo.setProvince(province.getAreaName());
                    aAccountInfo.setCity(city.getAreaName());
                    aAccountInfo.setArea(county.getAreaName());
                    areatx.setText(province.getAreaName() + "-" + city.getAreaName() + "-" + county.getAreaName());
                }
            }
        });
        task.execute("山东", "烟台", "福山");
    }


    public void onYearMonthDayPicker() {
        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        Calendar calendar = Calendar.getInstance();
        picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setRangeEnd(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE));
        picker.setRangeStart(calendar.get(Calendar.YEAR) - 150, 1, 1);
        picker.setSelectedItem(1990, calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE));
        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                Calendar now = Calendar.getInstance();
                int nowyear = now.get(Calendar.YEAR);
                int age = nowyear - Integer.valueOf(year);
                aAccountInfo.setAge(age + "");
                aAccountInfo.setBirthDate(year + "-" + month + "-" + day);
                timetx.setText(year + "-" + month + "-" + day);
            }
        });
        picker.show();
    }

    private String[] sexArry = new String[]{"女", "男"};// 性别选择

    /* 性别选择框 */
    private void showSexChooseDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);// 自定义对话框
        builder.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                sextx.setText(sexArry[which]);
                if (which == 0) {
                    aAccountInfo.setSex(2);
                } else {
                    aAccountInfo.setSex(1);
                }
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder.show();// 让弹出框显示
    }


}
