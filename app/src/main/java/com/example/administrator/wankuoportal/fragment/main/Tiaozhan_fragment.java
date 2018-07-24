package com.example.administrator.wankuoportal.fragment.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.DatipaihangAdapter;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.PaiHang;
import com.example.administrator.wankuoportal.beans.Weizhi;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.other.GlideImageLoader;
import com.example.administrator.wankuoportal.ui.DaTi.DTPaiHangActivity;
import com.example.administrator.wankuoportal.ui.DaTi.DaTi_WenActivity;
import com.example.administrator.wankuoportal.ui.DaTi.ShuoMingActivity;
import com.example.administrator.wankuoportal.ui.DaTi.WoDeDTActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyJinBiActivity;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.ClickHelper;
import com.example.administrator.wankuoportal.util.HorizontalListView;
import com.example.administrator.wankuoportal.util.HttpDataManager;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class Tiaozhan_fragment extends BaseFragment {
    private android.widget.ImageView datiback;
    private com.youth.banner.Banner datibanner;
    private com.example.administrator.wankuoportal.util.HorizontalListView datimingci;
    private android.widget.LinearLayout wenmydati;
    private android.widget.LinearLayout wenmyjinbi;
    private android.widget.LinearLayout wenstart;
    private android.widget.LinearLayout wenmyshuoming;
    private android.widget.LinearLayout wenmypaihang;
    private android.widget.LinearLayout scmydati;
    private android.widget.LinearLayout scmyjinbi;
    private android.widget.LinearLayout scstart;
    private android.widget.LinearLayout scmyshuoming;
    private android.widget.LinearLayout scmypaihang;

    Gson gson = new Gson();
    private boolean isStartAcivity = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.paotui_layout, container, false);//关联布局文件
        this.scmypaihang = (LinearLayout) rootView.findViewById(R.id.sc_mypaihang);
        this.scmyshuoming = (LinearLayout) rootView.findViewById(R.id.sc_myshuoming);
        this.scstart = (LinearLayout) rootView.findViewById(R.id.sc_start);
        this.scmyjinbi = (LinearLayout) rootView.findViewById(R.id.sc_myjinbi);
        this.scmydati = (LinearLayout) rootView.findViewById(R.id.sc_mydati);
        this.wenmypaihang = (LinearLayout) rootView.findViewById(R.id.wen_mypaihang);
        this.wenmyshuoming = (LinearLayout) rootView.findViewById(R.id.wen_myshuoming);
        this.wenstart = (LinearLayout) rootView.findViewById(R.id.wen_start);
        this.wenmyjinbi = (LinearLayout) rootView.findViewById(R.id.wen_myjinbi);
        this.wenmydati = (LinearLayout) rootView.findViewById(R.id.wen_mydati);
        this.datimingci = (HorizontalListView) rootView.findViewById(R.id.dati_mingci);
        this.datibanner = (Banner) rootView.findViewById(R.id.dati_banner);
        this.datiback = (ImageView) rootView.findViewById(R.id.dati_back);
        datiback.setVisibility(View.INVISIBLE);
        final Weizhi weizhi = new Weizhi();
        initbanner();

        //记忆赢大奖开始
        wenstart.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if (ClickHelper.isHandle()) {
                    return;
                }
                checkAnswerPermission("JY", Constant.HttpType.MEMORY);
            }
        });
        //猜题赢大奖开始
        scstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ClickHelper.isHandle()) {
                    return;
                }
                checkAnswerPermission("SC", Constant.HttpType.ANSWER);

            }
        });

        //我的排行点击
        wenmypaihang.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                if (new UserService(getActivity()).getislogin().equals("0")) {
                    startActivity(DTPaiHangActivity.class, "type", "wen");
                } else {
                    startActivity(LoginActivity.class);
                }

            }
        });
        //我的排行点击
        scmypaihang.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                if (new UserService(getActivity()).getislogin().equals("0")) {
                    startActivity(DTPaiHangActivity.class, "type", "sc");
                } else {
                    startActivity(LoginActivity.class);
                }
            }
        });
        //我的金币点击
        wenmyjinbi.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                if (new UserService(getActivity()).getislogin().equals("0")) {
                    startActivity(MyJinBiActivity.class);
                } else {
                    startActivity(LoginActivity.class);
                }

            }
        });
        //我的金币点击
        scmyjinbi.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                if (new UserService(getActivity()).getislogin().equals("0")) {
                    startActivity(MyJinBiActivity.class);
                } else {
                    startActivity(LoginActivity.class);
                }
            }
        });
        //文状元我的答题点击
        wenmydati.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                if (new UserService(getActivity()).getislogin().equals("0")) {
                    startActivity(WoDeDTActivity.class, "type", "JY");
                } else {
                    startActivity(LoginActivity.class);
                }

            }
        });
        //诗词我的答题点击
        scmydati.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                if (new UserService(getActivity()).getislogin().equals("0")) {
                    startActivity(WoDeDTActivity.class, "type", "SC");
                } else {
                    startActivity(LoginActivity.class);
                }
            }
        });
        //记忆赢大奖说明
        wenmyshuoming.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                startActivity(ShuoMingActivity.class, "type", "JY");
            }
        });

        //猜题赢大奖说明
        scmyshuoming.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                startActivity(ShuoMingActivity.class, "type", "SC");
            }
        });

        return rootView;
    }

    private void checkAnswerPermission(String type,String answerType) {
        HttpDataManager.checkAnswerPermission(getActivity(), answerType,new HttpDataManager.onResultListenr() {
            @Override
            public void onStart() {
                showHttpDialog();
            }

            @Override
            public void onSuccess(Object s) {
                dismissHttpDialog();
                startActivity(DaTi_WenActivity.class, "type", type);
            }

            @Override
            public void onFail(Object s) {
                dismissHttpDialog();
            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initHorizontalListView();
    }

    private void initHorizontalListView() {

        String url = Apis.Base + Apis.getrankingall;
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
                        L.d("paihang", response);
                        PaiHang p = gson.fromJson(response, PaiHang.class);
                        if (p.getCode() == 0) {
                            if (p.getData().getList().size() == 0) {
                                datimingci.setVisibility(View.GONE);

                            } else {
                                datimingci.setVisibility(View.VISIBLE);
                                DatipaihangAdapter d = new DatipaihangAdapter(p.getData().getList(), MyApplication.context);
                                datimingci.setAdapter(d);
                                setListViewHeightBasedOnChildren(datimingci);
                            }
                        } else if (p.getCode() == 2) {
                            showShort(p.getMsg());
                            startActivity(LoginActivity.class);
                        }


                    }
                });
    }

    private List<Integer> banner_list;
    private Integer[] banner_tu = {R.drawable.xianzhuan};

    //设置HorizontalListView的高度
    public void setListViewHeightBasedOnChildren(HorizontalListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        // listAdapter.getCount()返回数据项的数目
        View listItem = listAdapter.getView(0, null, listView);
        // 计算子项View 的宽高
        listItem.measure(0, 0);
        // 统计所有子项的总高度
        totalHeight = listItem.getMeasuredHeight();
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    private void initbanner() {

        banner_list = new ArrayList<>();
        for (int i = 0; i < banner_tu.length; i++) {
            banner_list.add(banner_tu[i]);
        }
        datibanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        datibanner.setImages(banner_list);
        //banner设置方法全部调用完毕时最后调用
        datibanner.start();
    }
}
