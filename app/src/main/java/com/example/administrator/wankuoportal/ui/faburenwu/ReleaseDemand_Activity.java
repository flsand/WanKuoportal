package com.example.administrator.wankuoportal.ui.faburenwu;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.util.GifSizeFilter;
import com.google.gson.Gson;
import com.example.administrator.wankuoportal.beans.APublishDemand;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.beans.BaseResultaddData;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.AddressPickTask;
import com.example.administrator.wankuoportal.ui.AddressPickTask_buxian;
import com.example.administrator.wankuoportal.ui.Pay.PayRenWuActivity;
import com.example.administrator.wankuoportal.ui.Pay.PayResultActivity;
import com.example.administrator.wankuoportal.util.Forms;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import okhttp3.Call;
import okhttp3.MediaType;


/**
 * 发布需求
 */
public class ReleaseDemand_Activity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.TextView type3;
    private TextView changetype3;
    private android.widget.LinearLayout fuwushangtype;
    private android.widget.LinearLayout fuwushangaddress;
    private ImageView finishima;
    private ImageView Originalima;
    private ImageView Shouhouima;
    //    private int iswancheng = 0;
//    private int isyuanchuang = 0;
//    private int isshouhou = 0;
    private TextView fuwushangarea;
    private TextView guyong;
    private TextView buguyong;
    private String[] sexArry = new String[]{"个人", "公司", "工作室"};// 性别选择
    private String[] contacts = new String[]{"QQ", "微信"};// 联系方式
    private String[] ways = new String[]{"直接手机联系", "添加微信联系", "添加QQ联系", "都可以"};// 联系方式
    private String[] priceArry = new String[]{"1-500元", "500-1000元", "1000-3000元", "3000-5000元", "5000-10000元"
            , "10000-30000元", "30000-50000元", "50000-100000元", "100000-150000元", "150000-200000元", "200000-300000元", "300000元以上"};// 性别选择
    private TextView txfuwushangtype;
    private LinearLayout myarea;
    private TextView txmyarea;
    private LinearLayout wenjianlin;
    int REQUESTCODE_FROM_ACTIVITY = 1000;
    APublishDemand aPublishDemand;
    private TextView tijiao;
    private android.widget.EditText edtitle;
    private android.widget.EditText edcontent;
    private TextView edprice;
    private EditText phonenum;
    private EditText edyzm;
    private TextView sendcode;
    private TextView qqSelectTv;
    private TextView wenjian;
    private TextView selectContactTv;
    private EditText QQnum;
    private EditText wxnum;
    private LinearLayout priceyusuan;
    private static final int REQUEST_CODE_CHOOSE = 23;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_demand_);
        this.priceyusuan = (LinearLayout) findViewById(R.id.priceyusuan);
        this.wxnum = (EditText) findViewById(R.id.wxnum);
        this.QQnum = (EditText) findViewById(R.id.QQnum);
        this.wenjian = (TextView) findViewById(R.id.wenjian);
        this.sendcode = (TextView) findViewById(R.id.sendcode);
        this.edyzm = (EditText) findViewById(R.id.ed_yzm);
        this.phonenum = (EditText) findViewById(R.id.phonenum);
        this.edprice = (TextView) findViewById(R.id.ed_price);
        this.edcontent = (EditText) findViewById(R.id.ed_content);
        this.edtitle = (EditText) findViewById(R.id.ed_title);
        this.tijiao = (TextView) findViewById(R.id.tijiao);
        this.wenjianlin = (LinearLayout) findViewById(R.id.wenjian_lin);
        this.txmyarea = (TextView) findViewById(R.id.tx_myarea);
        this.myarea = (LinearLayout) findViewById(R.id.myarea);
        this.txfuwushangtype = (TextView) findViewById(R.id.tx_fuwushang_type);
        this.buguyong = (TextView) findViewById(R.id.buguyong);
        this.guyong = (TextView) findViewById(R.id.guyong);
        this.Shouhouima = (ImageView) findViewById(R.id.Shouhou_ima);
        this.Originalima = (ImageView) findViewById(R.id.Original_ima);
        this.fuwushangarea = (TextView) findViewById(R.id.fuwushang_area);
        this.finishima = (ImageView) findViewById(R.id.finish_ima);
        this.fuwushangaddress = (LinearLayout) findViewById(R.id.fuwushang_address);
        this.fuwushangtype = (LinearLayout) findViewById(R.id.fuwushang_type);
        this.changetype3 = (TextView) findViewById(R.id.change_type3);
        this.type3 = (TextView) findViewById(R.id.type3);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        this.qqSelectTv = (TextView) findViewById(R.id.qq_select_tv);
        this.selectContactTv = (TextView) findViewById(R.id.selectContactTv);
        type3.setText("您选择：" + getIntent().getStringExtra("type3"));
        changetype3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        aPublishDemand = new APublishDemand();
        aPublishDemand.setLabelId(Integer.valueOf(getIntent().getStringExtra("label_id")));
        aPublishDemand.setIsEmploy(0);
        aPublishDemand.setServiceProvince("");
        aPublishDemand.setServiceCity("");
        aPublishDemand.setIsCountry(0);
        phonenum.setText(new UserService(getApplicationContext()).getphone());

        qqSelectTv.setOnClickListener(v -> showQQSelectDialog());
        selectContactTv.setOnClickListener(v -> showContactSelectDialog());

        //提交需求点击
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                panduan();


            }
        });


        //服务商地址选择
        fuwushangaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddressPicker();
            }
        });


        //文件选择
        wenjianlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matisse.from(ReleaseDemand_Activity.this)
                        .choose(MimeType.ofImage(), false)
                        .countable(true)
                        .capture(true)
                        .captureStrategy(
                                new CaptureStrategy(true, "com.example.administrator.wankuoportal.fileprovider"))
                        .maxSelectable(2)
                        .addFilter(new GifSizeFilter(640, 640, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(
                                getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);

//                new LFilePicker()
//                        .withActivity(ReleaseDemand_Activity.this)
//                        .withRequestCode(REQUESTCODE_FROM_ACTIVITY)
//                        .withFileFilter(new String[]{".png", ".jpg"})
//                        .withMaxNum(1)
//                        .withMutilyMode(true)
//                        .start();
            }
        });

        //雇佣点击
        guyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guyong.setBackgroundResource(R.drawable.bg_kuang_red);
                buguyong.setBackgroundResource(R.drawable.bg_hui_kuang);
                aPublishDemand.setIsEmploy(1);
            }
        });

        //不雇佣点击
        buguyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buguyong.setBackgroundResource(R.drawable.bg_kuang_red);
                guyong.setBackgroundResource(R.drawable.bg_hui_kuang);
                aPublishDemand.setIsEmploy(0);
            }
        });
        //发送验证码
        sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accout = phonenum.getEditableText().toString();
                if (Forms.disValid(accout, Forms.PHONENUM)) {
                    showShort("请输入正确的手机号");
                } else {
                    djs();
                    Random r = new Random();
                    String str = "0123456789";//列出所有的字母数字
                    yzm = "";
                    for (int i = 0; i < 4; i++)//循环4次，输出四个数
                    {
                        int a = r.nextInt(10);//从0-61中随机一个数，作为字符串的索引
                        yzm = yzm + str.substring(a, a + 1);
                    }
                    L.d("yzm", yzm);

                    String url = Apis.Base + Apis.send;
                    OkHttpUtils
                            .get()
                            .url(url)
                            .addParams("telephone", accout)
                            .addParams("random", yzm)
                            .build()
                            .execute(new MyStringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onHttpResponse(String response, int id) throws Exception {
                                    BaseResult b = gson.fromJson(response, BaseResult.class);
                                    if (b.getCode() == 1) {
                                        showShort(b.getMsg());
                                    } else {
                                        showShort(b.getMsg());
                                    }

                                }
                            });
                }
            }
        });


        //预算金额点击
        priceyusuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ReleaseDemand_Activity.this);// 自定义对话框
                builder.setSingleChoiceItems(priceArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中

                    @Override
                    public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                        // showToast(which+"");
                        edprice.setText(priceArry[which]);
                        switch (which) {
                            case 0:
                                aPublishDemand.setLowerBound("1");
                                aPublishDemand.setUpperBound("500");
                                break;
                            case 1:
                                aPublishDemand.setLowerBound("500");
                                aPublishDemand.setUpperBound("1000");
                                break;
                            case 2:
                                aPublishDemand.setLowerBound("1000");
                                aPublishDemand.setUpperBound("3000");
                                break;
                            case 3:
                                aPublishDemand.setLowerBound("3000");
                                aPublishDemand.setUpperBound("5000");
                                break;
                            case 4:
                                aPublishDemand.setLowerBound("5000");
                                aPublishDemand.setUpperBound("10000");
                                break;
                            case 5:
                                aPublishDemand.setLowerBound("10000");
                                aPublishDemand.setUpperBound("30000");
                                break;
                            case 6:
                                aPublishDemand.setLowerBound("30000");
                                aPublishDemand.setUpperBound("50000");
                                break;
                            case 7:
                                aPublishDemand.setLowerBound("50000");
                                aPublishDemand.setUpperBound("100000");
                                break;
                            case 8:
                                aPublishDemand.setLowerBound("100000");
                                aPublishDemand.setUpperBound("150000");
                                break;
                            case 9:
                                aPublishDemand.setLowerBound("150000");
                                aPublishDemand.setUpperBound("200000");
                                break;
                            case 10:
                                aPublishDemand.setLowerBound("200000");
                                aPublishDemand.setUpperBound("300000");
                                break;
                            case 11:
                                aPublishDemand.setLowerBound("300000");
                                aPublishDemand.setUpperBound("");
                                break;
                            case 12:
                                break;

                        }
                        dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
                    }
                });
                builder.show();// 让弹出框显示
            }
        });

        //服务商类型选择
        fuwushangtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ReleaseDemand_Activity.this);// 自定义对话框
                builder.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中

                    @Override
                    public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                        // showToast(which+"");
                        txfuwushangtype.setText(sexArry[which]);
                        aPublishDemand.setServiceType(which);
                        dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
                    }
                });
                builder.show();// 让弹出框显示
            }
        });
        //我的地区选择
        myarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyAddressPicker();
            }
        });
        initHandler();
    }

    private void showContactSelectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReleaseDemand_Activity.this);// 自定义对话框
        builder.setSingleChoiceItems(ways, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                selectContactTv.setText(ways[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder.show();// 让弹出框显示
    }

    private void showQQSelectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReleaseDemand_Activity.this);// 自定义对话框
        builder.setSingleChoiceItems(contacts, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                qqSelectTv.setText(contacts[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder.show();// 让弹出框显示
    }


    //判断是否填全需要的信息
    private void panduan() {
        if (!TextUtils.isEmpty(txfuwushangtype.getText())) {
//                    if (!TextUtils.isEmpty(fuwushangarea.getText())) {
            if (!TextUtils.isEmpty(edtitle.getText())) {
                aPublishDemand.setRequirementTitle(edtitle.getText().toString());
                if (!TextUtils.isEmpty(edcontent.getText())) {
                    aPublishDemand.setSpecificRequirements(edcontent.getText().toString());
//                                if (!TextUtils.isEmpty(txmyarea.getText())) {
                    if (!TextUtils.isEmpty(edprice.getText())) {
//                                    BigDecimal bigDecimal = new BigDecimal(edprice.getText().toString());
//                                    aPublishDemand.setUpperBound(bigDecimal);
                        if (!TextUtils.isEmpty(phonenum.getText())) {
                            aPublishDemand.setMobile(phonenum.getText().toString());
                            if (!TextUtils.isEmpty(edyzm.getText())) {
                                if (edyzm.getText().toString().equals(yzm)) {

                                    if (TextUtils.isEmpty(wxnum.getText()) && TextUtils.isEmpty(QQnum.getText())) {
                                        showShort("请至少输入微信或qq一种联系方式");
                                    } else {

                                        if (TextUtils.isEmpty(QQnum.getText())) {

                                        } else {
                                            aPublishDemand.setQq(QQnum.getText().toString());
                                        }
                                        if (TextUtils.isEmpty(wxnum.getText())) {

                                        } else {
                                            aPublishDemand.setWeChat(wxnum.getText().toString());
                                        }
                                        xuqiuadd();

                                    }


                                } else {
                                    showShort("验证码不正确,请重新验证");
                                }

                            } else {
                                showShort("请输入验证码");
                            }

                        } else {
                            showShort("请输入手机号");
                        }
                    } else {
                        showShort("请输入预算金额");
                    }

//                                } else {
//                                    showShort("请选择您所在的地区");
//                                }

                } else {
                    showShort("请输入需求要求");
                }
            } else {
                showShort("请输入需求标题");
            }


//                    } else {
//                        showShort("请选择服务商地区要求");
//                    }
        } else {
            showShort("请选择服务商类型");
        }
    }

    BaseResultaddData b;

    //提交需求
    private void xuqiuadd() {

        String time = new Date().getTime() + "";
        String token = new UserService(MyApplication.context).gettoken();
        String accountId = new UserService(MyApplication.context).getaccountid();
        token = MD5Util.md5(time + token);
        aPublishDemand.setAccountId(Integer.valueOf(accountId));
        String url = Apis.Base + Apis.xuqiupartner + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .postString()
                .url(url)
                .content(new Gson().toJson(aPublishDemand))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        b = gson.fromJson(response, BaseResultaddData.class);
                        if (b.getCode() == 0) {
                            if (list.size() == 0) {
                                showShort(b.getMsg());
                                pay();

                            } else {
                                wenjianshangchaun();
                            }


                        } else {
                            showShort(b.getMsg());
                        }


                    }
                });

    }


    //判断支付
    private void pay() {

        if (aPublishDemand.getIsEmploy() == 0) {
            startActivity(PayResultActivity.class);

        } else if (aPublishDemand.getIsEmploy() == 1) {
            startActivity(PayRenWuActivity.class, "price", "32", "id", b.getData());
        }
        finish();
    }

    //上传文件
    private void wenjianshangchaun() {
        String time = new Date().getTime() + "";
        String token = new UserService(MyApplication.context).gettoken();
        String accountId = new UserService(MyApplication.context).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.partnerFile + "?token=" + accountId + "," + time + "," + token;
        final File file = new File(list.get(0));

        if (list.size() == 2) {
            String wenjianweizhi = list.get(0);
            String wenjianfenge[] = wenjianweizhi.split("/");

            String wenjianweizhi1 = list.get(1);
            String wenjianfenge1[] = wenjianweizhi.split("/");
            OkHttpUtils
                    .post()
                    .addFile("uploadingAttachments", wenjianfenge[wenjianfenge.length - 1] + "", file)
                    .addFile("uploadingAttachments", wenjianfenge[wenjianfenge1.length - 1] + "", file)
                    .url(url)
                    .addParams("demandId", b.getData())
                    .build()
                    .execute(new MyStringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onHttpResponse(String response, int id) throws Exception {
                            b = gson.fromJson(response, BaseResultaddData.class);
                            if (b.getCode() == 0) {
                                showShort(b.getMsg());
                                pay();
                            } else {
                                showShort(b.getMsg());
                            }

                            L.d(response);
                        }
                    });
        } else {
            String wenjianweizhi = list.get(0);
            String wenjianfenge[] = wenjianweizhi.split("/");
            OkHttpUtils
                    .post()
                    .addFile("uploadingAttachments", wenjianfenge[wenjianfenge.length - 1] + "", file)
                    .url(url)
                    .addParams("demandId", b.getData())
                    .build()
                    .execute(new MyStringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onHttpResponse(String response, int id) throws Exception {
                            b = gson.fromJson(response, BaseResultaddData.class);
                            if (b.getCode() == 0) {
                                showShort(b.getMsg());
                                pay();
                            } else {
                                showShort(b.getMsg());
                            }

                            L.d(response);
                        }
                    });
        }


    }


    private void initHandler() {
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                sendcode.setText(sec + "");
                sec--;
                if (sec > 0) {
                    sendcode.setEnabled(false);
                    if (handler != null && runnable != null) {
                        handler.postDelayed(runnable, 1000);
                    }
                } else {
                    sendcode.setText("获取验证码");
                    sendcode.setEnabled(true);
                }
            }
        };
    }


    private Handler handler;
    private Runnable runnable;
    private int sec = 60;
    String yzm = "";

    public void djs() {
        sec = 60;
        handler.post(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            if (runnable != null) {
                handler.removeCallbacks(runnable);
                runnable = null;
            }
            handler = null;
        }
    }

    public void onMyAddressPicker() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);

        //隐藏区
        task.setHideCounty(false);

        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                showShort("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (province.getAreaName().equals("不限")) {
                    aPublishDemand.setMyProvince("");
                    aPublishDemand.setMyCity("");
                    aPublishDemand.setMyArea("");
                } else {
                    if (county == null) {
                        txmyarea.setText(province.getAreaName() + "-" + city.getAreaName());
                        aPublishDemand.setMyProvince(province.getAreaName());
                        aPublishDemand.setMyCity(city.getAreaName());
                    } else {
                        txmyarea.setText(province.getAreaName() + "-" + city.getAreaName() + "-" + county.getAreaName());
                        aPublishDemand.setMyProvince(province.getAreaName());
                        aPublishDemand.setMyCity(city.getAreaName());
                        aPublishDemand.setMyArea(county.getAreaName());
                    }
                }

            }
        });
        String str = new UserService(MyApplication.context).getAddress();
        String address[] = str.split("/");
        task.execute(address[1], address[2], address[3]);
    }

    List<String> list = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUESTCODE_FROM_ACTIVITY) {
                list = data.getStringArrayListExtra("paths");
                String wenjianweizhi = list.get(0);
                String wenjianfenge[] = wenjianweizhi.split("/");
                wenjian.setText(wenjianfenge[wenjianfenge.length - 1]);
                Toast.makeText(getApplicationContext(), "选中了" + list.size() + "个文件", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void onAddressPicker() {
        AddressPickTask_buxian task = new AddressPickTask_buxian(this);
        task.setHideProvince(false);
        //隐藏区
        task.setHideCounty(true);

        task.setCallback(new AddressPickTask_buxian.Callback() {
            @Override
            public void onAddressInitFailed() {
                showShort("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (province.getAreaName().equals("不限")) {
                    aPublishDemand.setServiceProvince("");
                    aPublishDemand.setServiceCity("");
                    aPublishDemand.setIsCountry(0);
                } else {

                    aPublishDemand.setIsCountry(1);
                    if (county == null) {
                        fuwushangarea.setText(province.getAreaName() + "-" + city.getAreaName());
                        aPublishDemand.setServiceProvince(province.getAreaName());
                        aPublishDemand.setServiceCity(city.getAreaName());
                    } else {
                        fuwushangarea.setText(province.getAreaName() + "-" + city.getAreaName() + "-" + county.getAreaName());
                        aPublishDemand.setServiceProvince(province.getAreaName());
                        aPublishDemand.setServiceCity(city.getAreaName());
                    }
                }
            }
        });
        task.execute("不限", "不限", "不限");
    }
}
