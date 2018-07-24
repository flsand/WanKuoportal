package com.example.administrator.wankuoportal.fragment.main;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.GridFenLeiAdapter;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.FenLei;
import com.example.administrator.wankuoportal.beans.Three_fenlei;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.ui.search.SearchResult_Activity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import okhttp3.Call;


/**
 * Created by lv on 2017/9/10 分类.
 */

public class Fenlei_fragment extends BaseFragment {


    private android.support.v7.widget.Toolbar basetoolbar;
    private android.widget.TextView yiji1;
    private android.widget.TextView yiji2;
    private android.widget.TextView yiji3;
    private android.widget.TextView yiji4;
    private android.widget.TextView yiji5;
    private android.widget.TextView yiji6;
    private android.widget.TextView yiji7;
    private android.widget.TextView yiji8;
    private android.widget.TextView yiji9;
    private android.widget.LinearLayout two1;
    private android.widget.LinearLayout two2;
    private android.widget.LinearLayout two3;
    private android.widget.GridView grid1;
    private TextView shouqi1;
    private android.widget.LinearLayout threelin1;
    private android.widget.LinearLayout two4;
    private android.widget.LinearLayout two5;
    private android.widget.LinearLayout two6;
    private android.widget.GridView grid2;
    private TextView shouqi2;
    private android.widget.LinearLayout threelin2;
    private android.widget.LinearLayout two7;
    private android.widget.LinearLayout two8;
    private android.widget.LinearLayout two9;
    private android.widget.GridView grid3;
    private TextView shouqi3;
    private android.widget.LinearLayout threelin3;
    private android.widget.LinearLayout two10;
    private android.widget.LinearLayout two11;
    private android.widget.LinearLayout two12;
    private android.widget.GridView grid4;
    private TextView shouqi4;
    private android.widget.LinearLayout threelin4;
    private android.widget.LinearLayout two13;
    private android.widget.LinearLayout two14;
    private android.widget.LinearLayout two15;
    private android.widget.GridView grid5;
    private TextView shouqi5;
    private android.widget.LinearLayout threelin5;
    private android.widget.LinearLayout two16;
    private android.widget.LinearLayout two17;
    private android.widget.LinearLayout two18;
    private android.widget.GridView grid6;
    private TextView shouqi6;
    private android.widget.LinearLayout threelin6;
    private android.widget.LinearLayout two19;
    private android.widget.LinearLayout two20;
    private android.widget.LinearLayout two21;
    private android.widget.GridView grid7;
    private TextView shouqi7;
    private android.widget.LinearLayout threelin7;
    private android.widget.LinearLayout two22;
    private android.widget.LinearLayout two23;
    private android.widget.LinearLayout two24;
    private android.widget.GridView grid8;
    private TextView shouqi8;
    private android.widget.LinearLayout threelin8;
    private android.widget.ImageView two1ima;
    private TextView two1tx;
    private android.widget.ImageView two2ima;
    private TextView two2tx;
    private android.widget.ImageView two3ima;
    private TextView two3tx;
    private android.widget.ImageView two4ima;
    private TextView two4tx;
    private android.widget.ImageView two5ima;
    private TextView two5tx;
    private android.widget.ImageView two6ima;
    private TextView two6tx;
    private android.widget.ImageView two7ima;
    private TextView two7tx;
    private android.widget.ImageView two8ima;
    private TextView two8tx;
    private android.widget.ImageView two9ima;
    private TextView two9tx;
    private android.widget.ImageView two10ima;
    private TextView two10tx;
    private android.widget.ImageView two11ima;
    private TextView two11tx;
    private android.widget.ImageView two12ima;
    private TextView two12tx;
    private android.widget.ImageView two13ima;
    private TextView two13tx;
    private android.widget.ImageView two14ima;
    private TextView two14tx;
    private android.widget.ImageView two15ima;
    private TextView two15tx;
    private android.widget.ImageView two16ima;
    private TextView two16tx;
    private android.widget.ImageView two17ima;
    private TextView two17tx;
    private android.widget.ImageView two18ima;
    private TextView two18tx;
    private android.widget.ImageView two19ima;
    private TextView two19tx;
    private android.widget.ImageView two20ima;
    private TextView two20tx;
    private android.widget.ImageView two21ima;
    private TextView two21tx;
    private android.widget.ImageView tw22ima;
    private TextView two22tx;
    private android.widget.ImageView two23ima;
    private TextView two23tx;
    private android.widget.ImageView two24ima;
    private TextView two24tx;
    Gson gson = new Gson();
    private android.widget.ScrollView scrollview;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fenlei_layout, container, false);//关联布局文件
        this.scrollview = (ScrollView) rootView.findViewById(R.id.scrollview);

        initviewall(rootView);

        return rootView;
    }

    FenLei fenLei;

    //初始化全部布局
    private void initviewall(View rootView) {

        final ProgressDialog dialogproa = new ProgressDialog(getActivity());
        dialogproa.setMessage("请稍候...");
//        dialogproa.setCancelable(false);
        dialogproa.show();
        //测试
//        String url = "http://www.wankuo5888.com/wankuoportal/api/label/getalllabel";
        //正式
//        String url = "http://192.168.1.198:8080/wankuoportal/api/label/getonetwolabel";

        String url = Apis.Base + Apis.getonetwolabel;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback() {

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        UserService u = new UserService(MyApplication.context);

                        L.d(response);
                        fenLei = gson.fromJson(response, FenLei.class);
                        if (fenLei.getCode() == 0) {
                            yiji1.setText(fenLei.getDatas().get(0).getText());
                            yiji2.setText(fenLei.getDatas().get(1).getText());
                            yiji3.setText(fenLei.getDatas().get(2).getText());
                            yiji4.setText(fenLei.getDatas().get(3).getText());
                            yiji5.setText(fenLei.getDatas().get(4).getText());
                            yiji6.setText(fenLei.getDatas().get(5).getText());
                            yiji7.setText(fenLei.getDatas().get(6).getText());
                            yiji8.setText(fenLei.getDatas().get(7).getText());
                            yiji9.setText("推荐服务");
                            if (u.getfenleiposition().equals("0")) {
                                settwo(0, fenLei);
                            } else if (u.getfenleiposition().equals("1")) {
                                settwo(1, fenLei);
                            } else if (u.getfenleiposition().equals("2")) {
                                settwo(2, fenLei);
                            } else if (u.getfenleiposition().equals("3")) {
                                settwo(3, fenLei);
                            } else if (u.getfenleiposition().equals("4")) {
                                settwo(4, fenLei);
                            } else if (u.getfenleiposition().equals("5")) {
                                settwo(5, fenLei);
                            } else if (u.getfenleiposition().equals("6")) {
                                settwo(6, fenLei);
                            } else if (u.getfenleiposition().equals("7")) {
                                settwo(7, fenLei);
                            }
                            if (dialogproa.isShowing()) {
                                dialogproa.dismiss();
                            }
                        } else {
                            if (dialogproa.isShowing()) {
                                dialogproa.dismiss();
                            }
                            showShort(fenLei.getMsg());

                        }

                    }
                });
        this.threelin8 = (LinearLayout) rootView.findViewById(R.id.three_lin8);
        this.shouqi8 = (TextView) rootView.findViewById(R.id.shouqi_8);
        this.grid8 = (GridView) rootView.findViewById(R.id.grid8);
        this.two24 = (LinearLayout) rootView.findViewById(R.id.two_24);
        this.two24tx = (TextView) rootView.findViewById(R.id.two24_tx);
        this.two24ima = (ImageView) rootView.findViewById(R.id.two24_ima);
        this.two23 = (LinearLayout) rootView.findViewById(R.id.two_23);
        this.two23tx = (TextView) rootView.findViewById(R.id.two23_tx);
        this.two23ima = (ImageView) rootView.findViewById(R.id.two23_ima);
        this.two22 = (LinearLayout) rootView.findViewById(R.id.two_22);
        this.two22tx = (TextView) rootView.findViewById(R.id.two22_tx);
        this.tw22ima = (ImageView) rootView.findViewById(R.id.tw22_ima);
        this.threelin7 = (LinearLayout) rootView.findViewById(R.id.three_lin7);
        this.shouqi7 = (TextView) rootView.findViewById(R.id.shouqi_7);
        this.grid7 = (GridView) rootView.findViewById(R.id.grid7);
        this.two21 = (LinearLayout) rootView.findViewById(R.id.two_21);
        this.two21tx = (TextView) rootView.findViewById(R.id.two21_tx);
        this.two21ima = (ImageView) rootView.findViewById(R.id.two21_ima);
        this.two20 = (LinearLayout) rootView.findViewById(R.id.two_20);
        this.two20tx = (TextView) rootView.findViewById(R.id.two20_tx);
        this.two20ima = (ImageView) rootView.findViewById(R.id.two20_ima);
        this.two19 = (LinearLayout) rootView.findViewById(R.id.two_19);
        this.two19tx = (TextView) rootView.findViewById(R.id.two19_tx);
        this.two19ima = (ImageView) rootView.findViewById(R.id.two19_ima);
        this.threelin6 = (LinearLayout) rootView.findViewById(R.id.three_lin6);
        this.shouqi6 = (TextView) rootView.findViewById(R.id.shouqi_6);
        this.grid6 = (GridView) rootView.findViewById(R.id.grid6);
        this.two18 = (LinearLayout) rootView.findViewById(R.id.two_18);
        this.two18tx = (TextView) rootView.findViewById(R.id.two18_tx);
        this.two18ima = (ImageView) rootView.findViewById(R.id.two18_ima);
        this.two17 = (LinearLayout) rootView.findViewById(R.id.two_17);
        this.two17tx = (TextView) rootView.findViewById(R.id.two17_tx);
        this.two17ima = (ImageView) rootView.findViewById(R.id.two17_ima);
        this.two16 = (LinearLayout) rootView.findViewById(R.id.two_16);
        this.two16tx = (TextView) rootView.findViewById(R.id.two16_tx);
        this.two16ima = (ImageView) rootView.findViewById(R.id.two16_ima);
        this.threelin5 = (LinearLayout) rootView.findViewById(R.id.three_lin5);
        this.shouqi5 = (TextView) rootView.findViewById(R.id.shouqi_5);
        this.grid5 = (GridView) rootView.findViewById(R.id.grid5);
        this.two15 = (LinearLayout) rootView.findViewById(R.id.two_15);
        this.two15tx = (TextView) rootView.findViewById(R.id.two15_tx);
        this.two15ima = (ImageView) rootView.findViewById(R.id.two15_ima);
        this.two14 = (LinearLayout) rootView.findViewById(R.id.two_14);
        this.two14tx = (TextView) rootView.findViewById(R.id.two14_tx);
        this.two14ima = (ImageView) rootView.findViewById(R.id.two14_ima);
        this.two13 = (LinearLayout) rootView.findViewById(R.id.two_13);
        this.two13tx = (TextView) rootView.findViewById(R.id.two13_tx);
        this.two13ima = (ImageView) rootView.findViewById(R.id.two13_ima);
        this.threelin4 = (LinearLayout) rootView.findViewById(R.id.three_lin4);
        this.shouqi4 = (TextView) rootView.findViewById(R.id.shouqi_4);
        this.grid4 = (GridView) rootView.findViewById(R.id.grid4);
        this.two12 = (LinearLayout) rootView.findViewById(R.id.two_12);
        this.two12tx = (TextView) rootView.findViewById(R.id.two12_tx);
        this.two12ima = (ImageView) rootView.findViewById(R.id.two12_ima);
        this.two11 = (LinearLayout) rootView.findViewById(R.id.two_11);
        this.two11tx = (TextView) rootView.findViewById(R.id.two11_tx);
        this.two11ima = (ImageView) rootView.findViewById(R.id.two11_ima);
        this.two10 = (LinearLayout) rootView.findViewById(R.id.two_10);
        this.two10tx = (TextView) rootView.findViewById(R.id.two10_tx);
        this.two10ima = (ImageView) rootView.findViewById(R.id.two10_ima);
        this.threelin3 = (LinearLayout) rootView.findViewById(R.id.three_lin3);
        this.shouqi3 = (TextView) rootView.findViewById(R.id.shouqi_3);
        this.grid3 = (GridView) rootView.findViewById(R.id.grid3);
        this.two9 = (LinearLayout) rootView.findViewById(R.id.two_9);
        this.two9tx = (TextView) rootView.findViewById(R.id.two9_tx);
        this.two9ima = (ImageView) rootView.findViewById(R.id.two9_ima);
        this.two8 = (LinearLayout) rootView.findViewById(R.id.two_8);
        this.two8tx = (TextView) rootView.findViewById(R.id.two8_tx);
        this.two8ima = (ImageView) rootView.findViewById(R.id.two8_ima);
        this.two7 = (LinearLayout) rootView.findViewById(R.id.two_7);
        this.two7tx = (TextView) rootView.findViewById(R.id.two7_tx);
        this.two7ima = (ImageView) rootView.findViewById(R.id.two7_ima);
        this.threelin2 = (LinearLayout) rootView.findViewById(R.id.three_lin2);
        this.shouqi2 = (TextView) rootView.findViewById(R.id.shouqi_2);
        this.grid2 = (GridView) rootView.findViewById(R.id.grid2);
        this.two6 = (LinearLayout) rootView.findViewById(R.id.two_6);
        this.two6tx = (TextView) rootView.findViewById(R.id.two6_tx);
        this.two6ima = (ImageView) rootView.findViewById(R.id.two6_ima);
        this.two5 = (LinearLayout) rootView.findViewById(R.id.two_5);
        this.two5tx = (TextView) rootView.findViewById(R.id.two5_tx);
        this.two5ima = (ImageView) rootView.findViewById(R.id.two5_ima);
        this.two4 = (LinearLayout) rootView.findViewById(R.id.two_4);
        this.two4tx = (TextView) rootView.findViewById(R.id.two4_tx);
        this.two4ima = (ImageView) rootView.findViewById(R.id.two4_ima);
        this.threelin1 = (LinearLayout) rootView.findViewById(R.id.three_lin1);
        this.shouqi1 = (TextView) rootView.findViewById(R.id.shouqi_1);
        this.grid1 = (GridView) rootView.findViewById(R.id.grid1);
        this.two3 = (LinearLayout) rootView.findViewById(R.id.two_3);
        this.two3tx = (TextView) rootView.findViewById(R.id.two3_tx);
        this.two3ima = (ImageView) rootView.findViewById(R.id.two3_ima);
        this.two2 = (LinearLayout) rootView.findViewById(R.id.two_2);
        this.two2tx = (TextView) rootView.findViewById(R.id.two2_tx);
        this.two2ima = (ImageView) rootView.findViewById(R.id.two2_ima);
        this.two1 = (LinearLayout) rootView.findViewById(R.id.two_1);
        this.two1tx = (TextView) rootView.findViewById(R.id.two1_tx);
        this.two1ima = (ImageView) rootView.findViewById(R.id.two1_ima);
        this.yiji9 = (TextView) rootView.findViewById(R.id.yiji_9);
        this.yiji9.setVisibility(View.GONE);
        this.yiji8 = (TextView) rootView.findViewById(R.id.yiji_8);
        this.yiji7 = (TextView) rootView.findViewById(R.id.yiji_7);
        this.yiji6 = (TextView) rootView.findViewById(R.id.yiji_6);
        this.yiji5 = (TextView) rootView.findViewById(R.id.yiji_5);
        this.yiji4 = (TextView) rootView.findViewById(R.id.yiji_4);
        this.yiji3 = (TextView) rootView.findViewById(R.id.yiji_3);
        this.yiji2 = (TextView) rootView.findViewById(R.id.yiji_2);
        this.yiji1 = (TextView) rootView.findViewById(R.id.yiji_1);
        this.basetoolbar = (Toolbar) rootView.findViewById(R.id.base_toolbar);

        final UserService u = new UserService(MyApplication.context);
        //一级分类点击事件
        yiji1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                settwo(0, fenLei);
                u.setfenleiposition("0");
                initviewtwo();
                yiji1.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                yiji1.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        yiji2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                u.setfenleiposition("1");
                settwo(1, fenLei);
                yiji2.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                initviewtwo();
                yiji2.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        yiji3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                u.setfenleiposition("2");
                settwo(2, fenLei);
                yiji3.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                initviewtwo();
                yiji3.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        yiji4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                u.setfenleiposition("3");
                settwo(3, fenLei);
                yiji4.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                initviewtwo();
                yiji4.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        yiji5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                u.setfenleiposition("4");
                settwo(4, fenLei);
                yiji5.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                initviewtwo();
                yiji5.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        yiji6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                u.setfenleiposition("5");
                settwo(5, fenLei);
                yiji6.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                initviewtwo();
                yiji6.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        yiji7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                u.setfenleiposition("6");
                settwo(6, fenLei);
                yiji7.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                initviewtwo();
                yiji7.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        yiji8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                u.setfenleiposition("7");
                settwo(7, fenLei);
                yiji8.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                yiji8.setTextColor(getResources().getColor(R.color.colorPrimary));
                initviewtwo();
            }
        });
        yiji9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                yiji9.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                yiji9.setTextColor(getResources().getColor(R.color.colorPrimary));
                initviewtwo();
            }
        });


        //二级分类点击事件
        two1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two1.setBackgroundResource(R.color.white);
                setthree(0, 1);
                two1tx.setTextColor(getResources().getColor(R.color.colorPrimary));


            }
        });
        two2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two2.setBackgroundResource(R.color.white);
                two2tx.setTextColor(getResources().getColor(R.color.colorPrimary));
                setthree(1, 1);

            }
        });
        two3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two3.setBackgroundResource(R.color.white);
                setthree(2, 1);
                two3tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two4.setBackgroundResource(R.color.white);
                setthree(3, 2);
                two4tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two5.setBackgroundResource(R.color.white);
                setthree(4, 2);
                two5tx.setTextColor(getResources().getColor(R.color.colorPrimary));

            }
        });
        two6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two6.setBackgroundResource(R.color.white);
                setthree(5, 2);
                two6tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two7.setBackgroundResource(R.color.white);
                setthree(6, 3);
                two7tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two8.setBackgroundResource(R.color.white);
                setthree(7, 3);
                two8tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two9.setBackgroundResource(R.color.white);
                setthree(8, 3);
                two9tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two10.setBackgroundResource(R.color.white);
                setthree(9, 4);
                two10tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two11.setBackgroundResource(R.color.white);
                setthree(10, 4);
                two11tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two12.setBackgroundResource(R.color.white);
                setthree(11, 4);
                two12tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two13.setBackgroundResource(R.color.white);
                setthree(12, 5);
                two13tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two14.setBackgroundResource(R.color.white);
                setthree(13, 5);
                two14tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two15.setBackgroundResource(R.color.white);
                setthree(14, 5);
                two15tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two16.setBackgroundResource(R.color.white);
                setthree(15, 6);
                two16tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two17.setBackgroundResource(R.color.white);
                setthree(16, 6);
                two17tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two18.setBackgroundResource(R.color.white);
                setthree(17, 6);
                two18tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two19.setBackgroundResource(R.color.white);
                setthree(18, 7);
                two19tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two20.setBackgroundResource(R.color.white);
                setthree(19, 7);
                two20tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two21.setBackgroundResource(R.color.white);
                setthree(20, 7);
                two21tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two22.setBackgroundResource(R.color.white);
                setthree(21, 8);
                two22tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        two23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two23.setBackgroundResource(R.color.white);
                setthree(22, 8);
                two23tx.setTextColor(getResources().getColor(R.color.colorPrimary));

            }
        });
        two24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
                two24.setBackgroundResource(R.color.white);
                setthree(23, 8);
                two24tx.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        //收起按钮点击收起所有三级分类
        shouqi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
            }
        });
        shouqi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
            }
        });
        shouqi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
            }
        });
        shouqi4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
            }
        });
        shouqi5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
            }
        });
        shouqi6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
            }
        });
        shouqi7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
            }
        });
        shouqi8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initviewtwo();
            }
        });


        switch (u.getfenleiposition()) {
            case "0":
                initview();
                yiji1.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                yiji1.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case "1":
                initview();
                yiji2.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                yiji2.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case "2":
                initview();
                yiji3.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                yiji3.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case "3":
                initview();
                yiji4.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                yiji4.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case "4":
                initview();
                yiji5.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                yiji5.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case "5":
                initview();
                yiji6.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                yiji6.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case "6":
                initview();
                yiji7.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                yiji7.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case "7":
                initview();
                yiji8.setBackgroundResource(R.drawable.xuanzhongdaihuitiao);
                yiji8.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }

    Three_fenlei t;

    private void setthree(int two, final int three) {
        fenlei2 = two;
        UserService u = new UserService(MyApplication.context);
        String one = u.getfenleiposition();
        int i = Integer.parseInt(one);
        String parentid = "";
        try {
            parentid = fenLei.getDatas().get(i).getNodes().get(two).getId() + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = Apis.Base + Apis.findlabelbyparentid + parentid;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        t = gson.fromJson(response, Three_fenlei.class);
                        GridFenLeiAdapter g = new GridFenLeiAdapter(t, getActivity());
                        switch (three) {
                            case 1:

                                grid1.setAdapter(g);
                                setListViewHeightBasedOnChildren(grid1);
                                g.notifyDataSetChanged();
                                threelin1.setVisibility(View.VISIBLE);
                                grid1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        choosethree(position);
                                    }
                                });
                                break;
                            case 2:

                                grid2.setAdapter(g);
                                setListViewHeightBasedOnChildren(grid2);
                                g.notifyDataSetChanged();
                                threelin2.setVisibility(View.VISIBLE);
                                grid2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        choosethree(position);
                                    }
                                });
                                break;
                            case 3:

                                grid3.setAdapter(g);
                                setListViewHeightBasedOnChildren(grid3);
                                g.notifyDataSetChanged();
                                threelin3.setVisibility(View.VISIBLE);
                                grid3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        choosethree(position);
                                    }
                                });
                                break;
                            case 4:

                                grid4.setAdapter(g);
                                setListViewHeightBasedOnChildren(grid4);
                                g.notifyDataSetChanged();
                                threelin4.setVisibility(View.VISIBLE);
                                grid4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        choosethree(position);
                                    }
                                });
                                break;
                            case 5:

                                grid5.setAdapter(g);
                                setListViewHeightBasedOnChildren(grid5);
                                g.notifyDataSetChanged();
                                threelin5.setVisibility(View.VISIBLE);
                                grid5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        choosethree(position);
                                    }
                                });
                                break;
                            case 6:

                                grid6.setAdapter(g);
                                setListViewHeightBasedOnChildren(grid6);
                                g.notifyDataSetChanged();
                                threelin6.setVisibility(View.VISIBLE);
                                grid6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        choosethree(position);
                                    }
                                });
                                break;
                            case 7:

                                grid7.setAdapter(g);
                                setListViewHeightBasedOnChildren(grid7);
                                g.notifyDataSetChanged();
                                threelin7.setVisibility(View.VISIBLE);
                                grid7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        choosethree(position);
                                    }
                                });
                                break;
                            case 8:
                                grid8.setAdapter(g);
                                setListViewHeightBasedOnChildren(grid8);
                                g.notifyDataSetChanged();
                                threelin8.setVisibility(View.VISIBLE);
                                grid8.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        choosethree(position);
                                    }
                                });
                                break;
                        }


                    }
                });
    }

    private int fenlei1 = 0;
    private int fenlei2 = 0;
    private int fenlei3 = 0;

    private void choosethree(int position) {
        String one = fenLei.getDatas().get(fenlei1).getText();
        String two = fenLei.getDatas().get(fenlei1).getNodes().get(fenlei2).getText();
        String three = t.getDatas().get(position).getLabelName();

        if (new UserService(MyApplication.context).getislogin().equals("0")) {
            new UserService(MyApplication.context).setText(three);
            startActivity(SearchResult_Activity.class, "type", "0");
        } else {
            startActivity(LoginActivity.class);
        }

    }

    //二级分类显示
    private void settwo(int i, FenLei fenlei) {
        scrollview.scrollTo(0, 0);
        fenlei1 = i;
        two1.setVisibility(View.VISIBLE);
        two2.setVisibility(View.VISIBLE);
        two3.setVisibility(View.VISIBLE);
        two4.setVisibility(View.VISIBLE);
        two5.setVisibility(View.VISIBLE);
        two6.setVisibility(View.VISIBLE);
        two7.setVisibility(View.VISIBLE);
        two8.setVisibility(View.VISIBLE);
        two9.setVisibility(View.VISIBLE);
        two10.setVisibility(View.VISIBLE);
        two11.setVisibility(View.VISIBLE);
        two12.setVisibility(View.VISIBLE);
        two13.setVisibility(View.VISIBLE);
        two14.setVisibility(View.VISIBLE);
        two15.setVisibility(View.VISIBLE);
        two16.setVisibility(View.VISIBLE);
        two17.setVisibility(View.VISIBLE);
        two18.setVisibility(View.VISIBLE);
        two19.setVisibility(View.VISIBLE);
        two20.setVisibility(View.VISIBLE);
        two21.setVisibility(View.VISIBLE);
        two22.setVisibility(View.VISIBLE);
        two23.setVisibility(View.VISIBLE);
        two24.setVisibility(View.VISIBLE);
        two1tx.setText(fenlei.getDatas().get(i).getNodes().get(0).getText());
        two2tx.setText(fenlei.getDatas().get(i).getNodes().get(1).getText());
        two3tx.setText(fenlei.getDatas().get(i).getNodes().get(2).getText());
        two4tx.setText(fenlei.getDatas().get(i).getNodes().get(3).getText());
        two5tx.setText(fenlei.getDatas().get(i).getNodes().get(4).getText());
        two6tx.setText(fenlei.getDatas().get(i).getNodes().get(5).getText());
        two7tx.setText(fenlei.getDatas().get(i).getNodes().get(6).getText());
        two8tx.setText(fenlei.getDatas().get(i).getNodes().get(7).getText());
        two9tx.setText(fenlei.getDatas().get(i).getNodes().get(8).getText());
        two10tx.setText(fenlei.getDatas().get(i).getNodes().get(9).getText());
        two11tx.setText(fenlei.getDatas().get(i).getNodes().get(10).getText());
        two12tx.setText(fenlei.getDatas().get(i).getNodes().get(11).getText());
        two13tx.setText(fenlei.getDatas().get(i).getNodes().get(12).getText());
        two14tx.setText(fenlei.getDatas().get(i).getNodes().get(13).getText());
        two15tx.setText(fenlei.getDatas().get(i).getNodes().get(14).getText());
        two16tx.setText(fenlei.getDatas().get(i).getNodes().get(15).getText());
        two17tx.setText(fenlei.getDatas().get(i).getNodes().get(16).getText());
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(0).getBj()).into(two1ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(1).getBj()).into(two2ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(2).getBj()).into(two3ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(3).getBj()).into(two4ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(4).getBj()).into(two5ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(5).getBj()).into(two6ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(6).getBj()).into(two7ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(7).getBj()).into(two8ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(8).getBj()).into(two9ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(9).getBj()).into(two10ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(10).getBj()).into(two11ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(11).getBj()).into(two12ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(12).getBj()).into(two13ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(13).getBj()).into(two14ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(14).getBj()).into(two15ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(15).getBj()).into(two16ima);
        Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(16).getBj()).into(two17ima);

        int size = fenlei.getDatas().get(i).getNodes().size();
        if (size == 18) {
            two18tx.setText(fenlei.getDatas().get(i).getNodes().get(17).getText());
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(17).getBj()).into(two18ima);
            two19.setVisibility(View.GONE);
            two20.setVisibility(View.GONE);
            two21.setVisibility(View.GONE);
            two22.setVisibility(View.GONE);
            two23.setVisibility(View.GONE);
            two24.setVisibility(View.GONE);
        } else if (size == 19) {
            two18tx.setText(fenlei.getDatas().get(i).getNodes().get(17).getText());
            two19tx.setText(fenlei.getDatas().get(i).getNodes().get(18).getText());
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(17).getBj()).into(two18ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(18).getBj()).into(two19ima);
            two20.setVisibility(View.INVISIBLE);
            two21.setVisibility(View.INVISIBLE);
            two22.setVisibility(View.GONE);
            two23.setVisibility(View.GONE);
            two24.setVisibility(View.GONE);
        } else if (size == 20) {
            two18tx.setText(fenlei.getDatas().get(i).getNodes().get(17).getText());
            two19tx.setText(fenlei.getDatas().get(i).getNodes().get(18).getText());
            two20tx.setText(fenlei.getDatas().get(i).getNodes().get(19).getText());
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(17).getBj()).into(two18ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(18).getBj()).into(two19ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(19).getBj()).into(two20ima);
            two21.setVisibility(View.INVISIBLE);
            two22.setVisibility(View.GONE);
            two23.setVisibility(View.GONE);
            two24.setVisibility(View.GONE);
        } else if (size == 21) {
            two18tx.setText(fenlei.getDatas().get(i).getNodes().get(17).getText());
            two19tx.setText(fenlei.getDatas().get(i).getNodes().get(18).getText());
            two20tx.setText(fenlei.getDatas().get(i).getNodes().get(19).getText());
            two21tx.setText(fenlei.getDatas().get(i).getNodes().get(20).getText());
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(17).getBj()).into(two18ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(18).getBj()).into(two19ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(19).getBj()).into(two20ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(20).getBj()).into(two21ima);
            two22.setVisibility(View.GONE);
            two23.setVisibility(View.GONE);
            two24.setVisibility(View.GONE);
        } else if (size == 22) {
            two18tx.setText(fenlei.getDatas().get(i).getNodes().get(17).getText());
            two19tx.setText(fenlei.getDatas().get(i).getNodes().get(18).getText());
            two20tx.setText(fenlei.getDatas().get(i).getNodes().get(19).getText());
            two21tx.setText(fenlei.getDatas().get(i).getNodes().get(20).getText());
            two22tx.setText(fenlei.getDatas().get(i).getNodes().get(21).getText());
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(17).getBj()).into(two18ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(18).getBj()).into(two19ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(19).getBj()).into(two20ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(20).getBj()).into(two21ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(21).getBj()).into(tw22ima);
            two23.setVisibility(View.INVISIBLE);
            two24.setVisibility(View.INVISIBLE);
        } else if (size == 23) {
            two19tx.setText(fenlei.getDatas().get(i).getNodes().get(18).getText());
            two20tx.setText(fenlei.getDatas().get(i).getNodes().get(19).getText());
            two21tx.setText(fenlei.getDatas().get(i).getNodes().get(20).getText());
            two22tx.setText(fenlei.getDatas().get(i).getNodes().get(21).getText());
            two23tx.setText(fenlei.getDatas().get(i).getNodes().get(22).getText());
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(17).getBj()).into(two18ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(18).getBj()).into(two19ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(19).getBj()).into(two20ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(20).getBj()).into(two21ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(21).getBj()).into(tw22ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(22).getBj()).into(two23ima);

            two24.setVisibility(View.INVISIBLE);
        } else if (size == 24) {
            two18tx.setText(fenlei.getDatas().get(i).getNodes().get(17).getText());
            two19tx.setText(fenlei.getDatas().get(i).getNodes().get(18).getText());
            two20tx.setText(fenlei.getDatas().get(i).getNodes().get(19).getText());
            two21tx.setText(fenlei.getDatas().get(i).getNodes().get(20).getText());
            two22tx.setText(fenlei.getDatas().get(i).getNodes().get(21).getText());
            two23tx.setText(fenlei.getDatas().get(i).getNodes().get(22).getText());
            two24tx.setText(fenlei.getDatas().get(i).getNodes().get(23).getText());
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(17).getBj()).into(two18ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(18).getBj()).into(two19ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(19).getBj()).into(two20ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(20).getBj()).into(two21ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(21).getBj()).into(tw22ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(22).getBj()).into(two23ima);
            Glide.with(getActivity()).load(fenlei.getDatas().get(i).getNodes().get(23).getBj()).into(two24ima);
        }

    }


    //初始化二级分类布局收起所有三级分类
    private void initviewtwo() {
        threelin1.setVisibility(View.GONE);
        threelin2.setVisibility(View.GONE);
        threelin3.setVisibility(View.GONE);
        threelin4.setVisibility(View.GONE);
        threelin5.setVisibility(View.GONE);
        threelin6.setVisibility(View.GONE);
        threelin7.setVisibility(View.GONE);
        threelin8.setVisibility(View.GONE);
        two1.setBackgroundResource(R.color.grey);
        two2.setBackgroundResource(R.color.grey);
        two3.setBackgroundResource(R.color.grey);
        two4.setBackgroundResource(R.color.grey);
        two5.setBackgroundResource(R.color.grey);
        two6.setBackgroundResource(R.color.grey);
        two7.setBackgroundResource(R.color.grey);
        two8.setBackgroundResource(R.color.grey);
        two9.setBackgroundResource(R.color.grey);
        two10.setBackgroundResource(R.color.grey);
        two11.setBackgroundResource(R.color.grey);
        two12.setBackgroundResource(R.color.grey);
        two13.setBackgroundResource(R.color.grey);
        two14.setBackgroundResource(R.color.grey);
        two15.setBackgroundResource(R.color.grey);
        two16.setBackgroundResource(R.color.grey);
        two17.setBackgroundResource(R.color.grey);
        two18.setBackgroundResource(R.color.grey);
        two19.setBackgroundResource(R.color.grey);
        two20.setBackgroundResource(R.color.grey);
        two21.setBackgroundResource(R.color.grey);
        two22.setBackgroundResource(R.color.grey);
        two23.setBackgroundResource(R.color.grey);
        two24.setBackgroundResource(R.color.grey);
        two1tx.setTextColor(getResources().getColor(R.color.all_textc));
        two2tx.setTextColor(getResources().getColor(R.color.all_textc));
        two3tx.setTextColor(getResources().getColor(R.color.all_textc));
        two4tx.setTextColor(getResources().getColor(R.color.all_textc));
        two5tx.setTextColor(getResources().getColor(R.color.all_textc));
        two6tx.setTextColor(getResources().getColor(R.color.all_textc));
        two7tx.setTextColor(getResources().getColor(R.color.all_textc));
        two8tx.setTextColor(getResources().getColor(R.color.all_textc));
        two9tx.setTextColor(getResources().getColor(R.color.all_textc));
        two10tx.setTextColor(getResources().getColor(R.color.all_textc));
        two11tx.setTextColor(getResources().getColor(R.color.all_textc));
        two12tx.setTextColor(getResources().getColor(R.color.all_textc));
        two13tx.setTextColor(getResources().getColor(R.color.all_textc));
        two14tx.setTextColor(getResources().getColor(R.color.all_textc));
        two15tx.setTextColor(getResources().getColor(R.color.all_textc));
        two16tx.setTextColor(getResources().getColor(R.color.all_textc));
        two17tx.setTextColor(getResources().getColor(R.color.all_textc));
        two18tx.setTextColor(getResources().getColor(R.color.all_textc));
        two19tx.setTextColor(getResources().getColor(R.color.all_textc));
        two20tx.setTextColor(getResources().getColor(R.color.all_textc));
        two21tx.setTextColor(getResources().getColor(R.color.all_textc));
        two22tx.setTextColor(getResources().getColor(R.color.all_textc));
        two23tx.setTextColor(getResources().getColor(R.color.all_textc));
        two24tx.setTextColor(getResources().getColor(R.color.all_textc));

    }

    //初始化一级分类布局
    private void initview() {
        yiji1.setBackgroundResource(R.drawable.bg_grey_zuo);
        yiji2.setBackgroundResource(R.drawable.bg_grey_zuo);
        yiji3.setBackgroundResource(R.drawable.bg_grey_zuo);
        yiji4.setBackgroundResource(R.drawable.bg_grey_zuo);
        yiji5.setBackgroundResource(R.drawable.bg_grey_zuo);
        yiji6.setBackgroundResource(R.drawable.bg_grey_zuo);
        yiji7.setBackgroundResource(R.drawable.bg_grey_zuo);
        yiji8.setBackgroundResource(R.drawable.bg_grey_zuo);
        yiji9.setBackgroundResource(R.drawable.bg_grey_zuo);
        yiji1.setTextColor(getResources().getColor(R.color.all_textc));
        yiji2.setTextColor(getResources().getColor(R.color.all_textc));
        yiji3.setTextColor(getResources().getColor(R.color.all_textc));
        yiji4.setTextColor(getResources().getColor(R.color.all_textc));
        yiji5.setTextColor(getResources().getColor(R.color.all_textc));
        yiji6.setTextColor(getResources().getColor(R.color.all_textc));
        yiji7.setTextColor(getResources().getColor(R.color.all_textc));
        yiji8.setTextColor(getResources().getColor(R.color.all_textc));
        yiji9.setTextColor(getResources().getColor(R.color.all_textc));
    }


    //动态设置第三分类gridview高度
    public static void setListViewHeightBasedOnChildren(GridView listView) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 3;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }


}
