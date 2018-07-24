package com.example.administrator.wankuoportal.fragment.citypartner;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.google.gson.Gson;
import com.example.administrator.wankuoportal.beans.ACityPartner;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.AddressPickTask;
import com.example.administrator.wankuoportal.util.Forms;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.Date;
import java.util.Random;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class CityPartner_login_fragment extends BaseFragment {

    private ListView baselist;
    private android.widget.EditText chengshitx;
    private android.widget.LinearLayout chengshi;
    private android.widget.EditText jiamengtx;
    private android.widget.LinearLayout jiameng;
    private android.widget.EditText ednum;
    private android.widget.EditText edname;
    private android.widget.EditText edphone;
    private android.widget.EditText edyzm;
    private android.widget.TextView sendcode;
    private android.widget.TextView shenqing;
    private EditText quyutx;
    private LinearLayout quyu;
    ACityPartner aCityPartner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.citypartner_login_layout, container, false);//关联布局文件
        this.quyu = (LinearLayout) rootView.findViewById(R.id.quyu);
        this.quyutx = (EditText) rootView.findViewById(R.id.quyu_tx);
        this.shenqing = (TextView) rootView.findViewById(R.id.shenqing);
        this.sendcode = (TextView) rootView.findViewById(R.id.sendcode);
        this.edyzm = (EditText) rootView.findViewById(R.id.ed_yzm);
        this.edphone = (EditText) rootView.findViewById(R.id.ed_phone);
        this.edname = (EditText) rootView.findViewById(R.id.ed_name);
        this.ednum = (EditText) rootView.findViewById(R.id.ed_num);
        this.jiameng = (LinearLayout) rootView.findViewById(R.id.jiameng);
        this.jiamengtx = (EditText) rootView.findViewById(R.id.jiameng_tx);
        this.chengshi = (LinearLayout) rootView.findViewById(R.id.chengshi);
        this.chengshitx = (EditText) rootView.findViewById(R.id.chengshi_tx);
        aCityPartner = new ACityPartner();
        aCityPartner.setType(0);
        initHandler();

        edphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Forms.disValid(s.toString(), Forms.PHONENUM)) {
                    sendcode.setBackgroundResource(R.color.edittext);
                } else {
                    sendcode.setBackgroundResource(R.color.orange);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //城市选择
        chengshitx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oncityAddressPicker();
            }
        });
        //区域选择
        quyutx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onareaAddressPicker();
            }
        });
        //加盟方式选择
        jiamengtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiamengdialog();
            }
        });
        //申请点击
        shenqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aCityPartner.getType() != 0) {
                    if (aCityPartner.getProvince() != null) {
                        if (!TextUtils.isEmpty(ednum.getText())) {
                            aCityPartner.setGroupSize(ednum.getText().toString());
                            if (!TextUtils.isEmpty(edname.getText())) {
                                aCityPartner.setLinkman(edname.getText().toString());
                                if (!TextUtils.isEmpty(edphone.getText())) {
                                    aCityPartner.setMobile(edphone.getText().toString());
                                    if (!TextUtils.isEmpty(edyzm.getText())) {
                                        if (yzm.equals(edyzm.getText().toString())) {

                                            String time = new Date().getTime() + "";
                                            String token = new UserService(getActivity()).gettoken();
                                            String accountId = new UserService(getActivity()).getaccountid();
                                            token = MD5Util.md5(time + token);
                                            String url = Apis.Base + Apis.partner + "?token=" + accountId + "," + time + "," + token;
                                            OkHttpUtils
                                                    .postString()
                                                    .url(url)
                                                    .content(new Gson().toJson(aCityPartner))
                                                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                                                    .build()
                                                    .execute(new MyStringCallback() {
                                                        @Override
                                                        public void onError(Call call, Exception e, int id) {

                                                        }

                                                        @Override
                                                        public void onHttpResponse(String response, int id) throws Exception {
                                                            L.d(response);
                                                            BaseResult baseResult = gson.fromJson(response, BaseResult.class);
                                                            if (baseResult.getCode() == 0) {
                                                                showShort(baseResult.getMsg());
                                                            }

                                                        }
                                                    });
                                        } else {
                                            showShort("验证码验证失败");
                                        }
                                    } else {
                                        showShort("请输入验证码");
                                    }
                                } else {
                                    showShort("请输入联系电话");
                                }
                            } else {
                                showShort("请输入联系人");
                            }
                        } else {
                            showShort("请输入团队人数");
                        }
                    } else {
                        showShort("请选择加盟地区");
                    }
                } else {
                    showShort("请选择加盟方式");
                }
            }
        });
        //发送验证码
        sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edphone.getText())) {
                    String phone = edphone.getEditableText().toString();
                    if (Forms.disValid(phone, Forms.PHONENUM)) {
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
                                .addParams("telephone", phone)
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

                } else {
                    showShort("请输入手机号");
                }


            }
        });


        return rootView;
    }

    String yzm = "";

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

    private String[] sexArry = new String[]{"城市合伙人", "区域合伙人", "省级合伙人"};// 加盟方式选择


    //选择加盟方式
    private void jiamengdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());// 自定义对话框
        builder.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                jiamengtx.setText(sexArry[which]);
                aCityPartner.setType(which + 1);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
                if (which == 0) {
                    chengshi.setVisibility(View.VISIBLE);
                    quyu.setVisibility(View.GONE);
                } else {
                    quyu.setVisibility(View.VISIBLE);
                    chengshi.setVisibility(View.GONE);
                }

            }
        });
        builder.show();// 让弹出框显示

    }


    //选择城市
    public void oncityAddressPicker() {
        AddressPickTask task = new AddressPickTask(getActivity());
        task.setHideProvince(false);
        task.setHideCounty(true);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                showShort("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
//                    showShort(province.getAreaName() + city.getAreaName());
                    chengshitx.setText(province.getAreaName() + "-" + city.getAreaName());
                    aCityPartner.setProvince(province.getAreaName());
                    aCityPartner.setCity(city.getAreaName());

                } else {
                    aCityPartner.setProvince(province.getAreaName());
                    aCityPartner.setCity(city.getAreaName());
                    aCityPartner.setArea(county.getAreaName());
                    chengshitx.setText(province.getAreaName() + "-" + city.getAreaName() + "-" + county.getAreaName());
                }
            }
        });
        task.execute("山东", "烟台");
    }

    //选择区域
    public void onareaAddressPicker() {
        AddressPickTask task = new AddressPickTask(getActivity());
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
                    quyutx.setText(province.getAreaName() + "-" + city.getAreaName());
                    aCityPartner.setProvince(province.getAreaName());
                    aCityPartner.setCity(city.getAreaName());
                } else {
                    aCityPartner.setProvince(province.getAreaName());
                    aCityPartner.setCity(city.getAreaName());
                    aCityPartner.setArea(county.getAreaName());
                    quyutx.setText(province.getAreaName() + "-" + city.getAreaName() + "-" + county.getAreaName());
                }
            }
        });
        task.execute("山东", "烟台");
    }
}
