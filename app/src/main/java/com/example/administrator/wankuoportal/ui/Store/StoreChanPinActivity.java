package com.example.administrator.wankuoportal.ui.Store;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.DianPuPingJia_Adapter;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.BaseResultaddData;
import com.example.administrator.wankuoportal.beans.ShopGoods_xiangqing;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.other.GlideImageLoader;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.HorizontalListView;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.example.administrator.wankuoportal.util.QQ;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 * 产品详情
 */
public class StoreChanPinActivity extends BaseActivity {

    private ImageView back;
    private TextView title;
    private Banner banner1;
    private HorizontalListView datimingci;
    private Integer[] banner_tu = {R.drawable.dbl, R.drawable.dl};
    private List<String> banner_list;
    private Banner banner;
    private TextView dianpuzixun;
    private TextView dianpupingjia;
    private TextView hao;
    private TextView zhong;
    private TextView cha;
    private android.widget.LinearLayout pingjiaLin;
    private android.widget.ListView list;
    private android.widget.ScrollView scrollview;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            scrollview.scrollTo(0, 0);// 改变滚动条的位置
        }
    };
    //    private LinearLayout linima;
    private TextView name;
    private TextView jiage;
    private TextView xiaoliang;
    private TextView pingjia;
    private TextView jubao;
    private WebView webView;
    ShopGoods_xiangqing shopGoods_xiangqing;
    private LinearLayout calllin;
    private LinearLayout shoucanglin;
    private LinearLayout gostorelin;
    private ImageView shoucangima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_chanpin);
        this.shoucangima = (ImageView) findViewById(R.id.shoucang_ima);
        this.gostorelin = (LinearLayout) findViewById(R.id.gostore_lin);
        this.shoucanglin = (LinearLayout) findViewById(R.id.shoucang_lin);
        this.calllin = (LinearLayout) findViewById(R.id.call_lin);
        this.webView = (WebView) findViewById(R.id.webView);


//        //webview设置
//
//        WebSettings settings = webView.getSettings();
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        settings.setDisplayZoomControls(false);


        this.jubao = (TextView) findViewById(R.id.jubao);
        this.pingjia = (TextView) findViewById(R.id.pingjia);
        this.xiaoliang = (TextView) findViewById(R.id.xiaoliang);
        this.jiage = (TextView) findViewById(R.id.jiage);
        this.name = (TextView) findViewById(R.id.name);
//        this.linima = (LinearLayout) findViewById(R.id.lin_ima);
        this.scrollview = (ScrollView) findViewById(R.id.scrollview);
        this.list = (ListView) findViewById(R.id.list);
        this.pingjiaLin = (LinearLayout) findViewById(R.id.pingjia_Lin);
        this.cha = (TextView) findViewById(R.id.cha);
        this.zhong = (TextView) findViewById(R.id.zhong);
        this.hao = (TextView) findViewById(R.id.hao);
        this.dianpupingjia = (TextView) findViewById(R.id.dianpupingjia);
        this.dianpuzixun = (TextView) findViewById(R.id.dianpuzixun);
        this.banner = (Banner) findViewById(R.id.banner);
        this.datimingci = (HorizontalListView) findViewById(R.id.dati_mingci);
        this.banner1 = (Banner) findViewById(R.id.banner);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String url = Apis.Base + Apis.shopgoodsdetails + getIntent().getStringExtra("id");
        OkHttpUtils
                .get()
                .url(url)
                .addParams("accountId","0")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {

                        try {//TODO NullPointerException
//                            java.lang.NullPointerException: Attempt to invoke virtual method 'boolean java.lang.String.isEmpty()' on a null object reference
//                            at com.example.administrator.wankuoportal.ui.Store.StoreChanPinActivity$3.onResponse(StoreChanPinActivity.java:147)


                        L.d(response);
                        shopGoods_xiangqing = gson.fromJson(response, ShopGoods_xiangqing.class);

                        if (shopGoods_xiangqing.getCode() == 0) {
                            name.setText(shopGoods_xiangqing.getData().getName() + "");
                            jiage.setText(shopGoods_xiangqing.getData().getPirce() + "/" + shopGoods_xiangqing.getData().getUnit());
                            xiaoliang.setText("销量:" + shopGoods_xiangqing.getData().getSalesVolume() + "");
                            banner_list = new ArrayList<>();

                            if (shopGoods_xiangqing.getData().getImg1().isEmpty() || null == shopGoods_xiangqing.getData().getImg1()) {

                            } else {
                                banner_list.add(Apis.Baseima + shopGoods_xiangqing.getData().getImg1());
                            }
                            if (shopGoods_xiangqing.getData().getImg2().isEmpty() || null == shopGoods_xiangqing.getData().getImg2()) {

                            } else {
                                banner_list.add(Apis.Baseima + shopGoods_xiangqing.getData().getImg2());
                            }
                            if (shopGoods_xiangqing.getData().getImg3().isEmpty() || null == shopGoods_xiangqing.getData().getImg3()) {

                            } else {
                                banner_list.add(Apis.Baseima + shopGoods_xiangqing.getData().getImg3());
                            }
                            if (shopGoods_xiangqing.getData().getImg4().isEmpty() || null == shopGoods_xiangqing.getData().getImg4()) {

                            } else {
                                banner_list.add(Apis.Baseima + shopGoods_xiangqing.getData().getImg4());
                            }

                            banner1.setImageLoader(new GlideImageLoader());

                            //设置图片集合
                            banner1.setImages(banner_list);
                            //banner设置方法全部调用完毕时最后调用
                            banner1.start();
                            if (shopGoods_xiangqing.getData().getShopId().isEmpty()) {
                                gostorelin.setVisibility(View.INVISIBLE);
                            }

                            WebSettings settings = webView.getSettings();
                            //设置加载进来的页面自适应手机屏幕
                            settings.setUseWideViewPort(true);
                            settings.setLoadWithOverviewMode(true);
                            webView.setWebViewClient(new WebViewClient());
                            webView.loadDataWithBaseURL(null, "<style>*{margin:0;padding:0;width:100%}</style>" + shopGoods_xiangqing.getData().getAppHtml(), "text/html", "utf-8", null);

                        }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });


        initlist();

        cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inittablayout();
                pingjiaLin.setVisibility(View.VISIBLE);
                dianpupingjia.setBackgroundResource(R.drawable.bg_hong);
                initpingjiatab();
                cha.setBackgroundResource(R.drawable.bg_hong);
                List<String> title = new ArrayList<>();
//                title.add("开店赚钱");
//                title.add("广告投放");
//                title.add("万哥跑腿");
//                title.add("城市合作");
//                title.add("开店赚钱");
                DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(StoreChanPinActivity.this, title);
                list.setAdapter(d);
                setListViewHeightBasedOnChildren(list);
            }
        });

        zhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inittablayout();
                pingjiaLin.setVisibility(View.VISIBLE);
                dianpupingjia.setBackgroundResource(R.drawable.bg_hong);
                initpingjiatab();
                zhong.setBackgroundResource(R.drawable.bg_hong);
                List<String> title = new ArrayList<>();
//                title.add("开店赚钱");
//                title.add("广告投放");
//                title.add("万哥跑腿");
//                title.add("开店赚钱");
                DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(StoreChanPinActivity.this, title);
                list.setAdapter(d);
                setListViewHeightBasedOnChildren(list);

            }
        });
        hao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inittablayout();
                pingjiaLin.setVisibility(View.VISIBLE);
                dianpupingjia.setBackgroundResource(R.drawable.bg_hong);
                initpingjiatab();
                hao.setBackgroundResource(R.drawable.bg_hong);
                List<String> title = new ArrayList<>();
//                title.add("开店赚钱");
//                title.add("万哥跑腿");
//                title.add("城市合作");

                DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(StoreChanPinActivity.this, title);
                list.setAdapter(d);
                setListViewHeightBasedOnChildren(list);
            }
        });


        //店铺评价点击
        dianpupingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inittablayout();
                pingjiaLin.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);
                dianpupingjia.setBackgroundResource(R.drawable.bg_hong);
                initpingjiatab();

                hao.setBackgroundResource(R.drawable.bg_hong);
                List<String> title = new ArrayList<>();
//                title.add("开店赚钱");
//                title.add("广告投放");
//                title.add("万哥跑腿");
//                title.add("城市合作");
//                title.add("开店赚钱");
//                title.add("广告投放");
//                title.add("万哥跑腿");
//                title.add("城市合作");
                DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(StoreChanPinActivity.this, title);
                list.setAdapter(d);
                setListViewHeightBasedOnChildren(list);
                d.notifyDataSetChanged();
                Handler handler = new Handler();
                handler.postDelayed(runnable, 200);
            }
        });
        //服务详情点击
        dianpuzixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inittablayout();
                list.clearFocus();
                pingjiaLin.setVisibility(View.GONE);
                list.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                dianpuzixun.setBackgroundResource(R.drawable.bg_hong);
                Handler handler = new Handler();
                handler.postDelayed(runnable, 200);
            }
        });
        //收藏产品点击
        shoucanglin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String time = new Date().getTime() + "";
                String token = new UserService(MyApplication.context).gettoken();
                String accountId = new UserService(MyApplication.context).getaccountid();
                token = MD5Util.md5(time + token);
                String url = Apis.Base + Apis.collection + "?token=" + accountId + "," + time + "," + token;
                OkHttpUtils
                        .get()
                        .url(url)
                        .addParams("accountId", accountId)
                        .addParams("type", "2")
                        .addParams("linkId", getIntent().getStringExtra("id") + "")
                        .build()
                        .execute(new MyStringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onHttpResponse(String response, int id) throws Exception {
                                L.d(response);
                                BaseResultaddData s = gson.fromJson(response, BaseResultaddData.class);
                                if (s.getCode() == 0) {
                                    if (s.getData().equals("0")) {
                                        shoucangima.setImageDrawable(getResources().getDrawable(R.drawable.shoucangxingchoose_red));
                                    } else {
                                        shoucangima.setImageDrawable(getResources().getDrawable(R.drawable.shoucangxing));
                                    }

                                    showShort(s.getMsg());
                                } else if (s.getCode() == 2) {
                                    showShort("收藏产品前请先登录");
                                    startActivity(LoginActivity.class);
                                } else {
                                    showShort(s.getMsg());
                                }
                            }
                        });


            }
        });

        //联系服务商点击
        calllin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QQ.startQQ(MyApplication.context, 1, "2389004396");
            }
        });
        //进入店铺点击
        gostorelin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserService(StoreChanPinActivity.this).setstoreposition("0");
                new UserService(StoreChanPinActivity.this).setstoreid(shopGoods_xiangqing.getData().getShopId() + "");
                startActivity(StoreMainActivity.class);
//                finish();
            }
        });
    }

    //初始化list
    private void initlist() {
        pingjiaLin.setVisibility(View.GONE);
        list.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
        dianpuzixun.setBackgroundResource(R.drawable.bg_hong);
        Handler handler = new Handler();
        handler.postDelayed(runnable, 200);
    }

    //初始化一级类目
    private void inittablayout() {
        dianpuzixun.setBackgroundResource(R.color.white);
        dianpupingjia.setBackgroundResource(R.color.white);
    }

    //设置listview的高度
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    private void initpingjiatab() {
        zhong.setBackgroundResource(R.color.white);
        hao.setBackgroundResource(R.color.white);
        cha.setBackgroundResource(R.color.white);
    }

}
