package com.example.administrator.wankuoportal.ui.shop;


import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.DianPuPingJia_Adapter;
import com.example.administrator.wankuoportal.adapter.MyGridAdapter;
import com.example.administrator.wankuoportal.base.DefultHttpAnalysisHelper;
import com.example.administrator.wankuoportal.beans.CheckSignin;
import com.example.administrator.wankuoportal.beans.Regist;
import com.example.administrator.wankuoportal.beans.ShopGoodsDetials;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.other.GlideImageLoader;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.util.Utils;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

public class ShopDetailActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.ImageView share;
    private android.widget.RelativeLayout bar;
    private android.widget.TextView title;
    private android.widget.TextView price;
    private android.widget.TextView moneyTv;
    private android.widget.TextView manposition;
    private android.widget.TextView duihuannow;
    private TextView dianpuzixun;
    private TextView dianpupingjia;
    private TextView hao;
    private TextView zhong;
    private TextView cha;
    private com.youth.banner.Banner banner;

    private PopupWindow popupWindow;
    private View contentView;
    private String[] kinds = {"休闲食品", "生鲜果蔬", "办公/家居", "其它", "鲜花", "蛋糕", "大件物品"};
    private MyGridAdapter adapter;
    private List<String> list;
    private int num = 1;
    private android.widget.ScrollView scrollview;
    private WebView webView;
    private ListView mListView;
    private android.widget.LinearLayout pingjiaLin;
    int maxnum = 100;
    String zuanshi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        this.webView = (WebView) findViewById(R.id.webView);
        this.scrollview = (ScrollView) findViewById(R.id.scrollview);
        this.banner = (Banner) findViewById(R.id.banner);
        this.bar = (RelativeLayout) findViewById(R.id.bar);
        this.duihuannow = (TextView) findViewById(R.id.duihuan_now);
        this.manposition = (TextView) findViewById(R.id.man_position);
        this.price = (TextView) findViewById(R.id.price);
        this.moneyTv = (TextView) findViewById(R.id.moneyTv);
        this.title = (TextView) findViewById(R.id.title);
        this.share = (ImageView) findViewById(R.id.share);
        this.back = (ImageView) findViewById(R.id.back);

        this.dianpupingjia = (TextView) findViewById(R.id.dianpupingjia);
        this.dianpuzixun = (TextView) findViewById(R.id.dianpuzixun);
        this.cha = (TextView) findViewById(R.id.cha);
        this.zhong = (TextView) findViewById(R.id.zhong);
        this.hao = (TextView) findViewById(R.id.hao);
        this.mListView = (ListView) findViewById(R.id.mListView);
        this.pingjiaLin = (LinearLayout) findViewById(R.id.pingjia_Lin);

        dianpuzixun.setBackgroundResource(R.drawable.bg_hong);
        hao.setBackgroundResource(R.drawable.bg_hong);

        //返回按钮点击
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //立即购买按钮点击
        duihuannow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if (ProjectUtil.isLogin())
                    return;

                String time = new Date().getTime() + "";
                String tokenTemp = new UserService(getBaseContext()).gettoken();
                String accountId = new UserService(getBaseContext()).getaccountid();
                final String token = MD5Util.md5(time + tokenTemp);
                String url = Apis.Base + Apis.getuserinfo + "?token=" + accountId + "," + time + "," + token;
                showHttpDialog();
                OkHttpUtils
                        .post()
                        .url(url)
                        .addParams("accountId", accountId)
                        .build()
                        .execute(new MyStringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                dismissHttpDialog();
                            }

                            @Override
                            public void onLoginOutTime(String msg) {
                                super.onLoginOutTime(msg);
                                dismissHttpDialog();
                            }

                            @Override
                            public void onHttpException() {
                                dismissErrDialog(DefultHttpAnalysisHelper.ERR_MSG);
                            }

                            @Override
                            public void onHttpSuccess(String s, JSONObject jsonObject) throws Exception {
                                dismissHttpDialog();

                            }

                            @Override
                            public void onHttpResponse(String response, int id) throws Exception {
                                Log.d("qzw", "onResponse " + response);
                                Regist regist = gson.fromJson(response, Regist.class);
                                phone = regist.getData().getAaccountInfo().getPhone();
                                showHttpDialog();
                                String url = Apis.Base + Apis.checksignin + "?token=" + accountId + "," + time + "," + token;
                                OkHttpUtils
                                        .get()
                                        .url(url)
                                        .addParams("accountId", accountId)
                                        .build()
                                        .execute(new MyStringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                dismissHttpDialog();
                                            }

                                            @Override
                                            public void onHttpException() {
                                                dismissErrDialog(DefultHttpAnalysisHelper.ERR_MSG);
                                            }

                                            @Override
                                            public void onLoginOutTime(String msg) {
                                                super.onLoginOutTime(msg);
                                                dismissHttpDialog();
                                            }

                                            @Override
                                            public void onHttpResponse(String response, int id) throws Exception {
                                                dismissHttpDialog();
                                                CheckSignin checkSignin = gson.fromJson(response, CheckSignin.class);
//                                                double price = s.getData().getAgood().getPirceShop();
//                                                double remainingJinbi = checkSignin.getData().getAAccountAuthorize().getShoppingMoney();
//                                                if (remainingJinbi > price) {
                                                openPopWindow(v);
//                                                } else {
//                                                    showShort("您的金币不足，快去答题攒金币吧");
//                                                }
                                            }
                                        });

                                /*if (regist.getCode() == 0) {
                                    int goldnum = regist.getData().getAaccountAuthorize().getNolimitGold();
                                    zuanshi = regist.getData().getAaccountAuthorize().getDiamonds();
                                    int price = (int) (s.getData().getAgood().getPirceShop());
                                    phone = regist.getData().getAaccountInfo().getPhone();

                                    int minzuanshi = (int) (price * 0.25);
                                    int keyongzuanshi;
                                    int minjinbi;
                                    if (minzuanshi > Integer.valueOf(zuanshi)) {
                                        keyongzuanshi = Integer.valueOf(zuanshi);
                                        minjinbi = price - keyongzuanshi;
                                    } else {
                                        keyongzuanshi = minzuanshi;
                                        minjinbi = price - keyongzuanshi;
                                    }
                                    if (goldnum > minjinbi) {
                                        openPopWindow(v);
                                    } else {
                                        showShort("您的金币不足，快去答题攒金币吧");
                                    }

                                } else if (regist.getCode() == 2) {
                                    new UserService(ShopDetailActivity.this).setislogin("1");
                                    startActivity(LoginActivity.class);
                                } else {
                                    showShort(regist.getMsg());
                                }*/
                            }
                        });

            }
        });

        Handler handler = new Handler();
        handler.postDelayed(runnable, 200);

        //店铺评价点击
        dianpupingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inittablayout();
                pingjiaLin.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
                dianpupingjia.setBackgroundResource(R.drawable.bg_hong);
                initpingjiatab();

                hao.setBackgroundResource(R.drawable.bg_hong);
                List<String> title = new ArrayList<>();
                DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(ShopDetailActivity.this, title);
                mListView.setAdapter(d);
                setListViewHeightBasedOnChildren(mListView);
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
                mListView.clearFocus();
                pingjiaLin.setVisibility(View.GONE);
                mListView.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                dianpuzixun.setBackgroundResource(R.drawable.bg_hong);
                Handler handler = new Handler();
                handler.postDelayed(runnable, 200);
            }
        });

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
                DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(ShopDetailActivity.this, title);
                mListView.setAdapter(d);
                setListViewHeightBasedOnChildren(mListView);
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
                DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(ShopDetailActivity.this, title);
                mListView.setAdapter(d);
                setListViewHeightBasedOnChildren(mListView);

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

                DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(ShopDetailActivity.this, title);
                mListView.setAdapter(d);
                setListViewHeightBasedOnChildren(mListView);
            }
        });


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

    //初始化一级类目
    private void inittablayout() {
        dianpuzixun.setBackgroundResource(R.color.white);
        dianpupingjia.setBackgroundResource(R.color.white);
    }

    /**
     * 关闭窗口
     */
    private void closePopupWindow() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
            WindowManager.LayoutParams params = ShopDetailActivity.this.getWindow().getAttributes();
            params.alpha = 1f;
            ShopDetailActivity.this.getWindow().setAttributes(params);
        }
    }

    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            scrollview.scrollTo(0, 0);// 改变滚动条的位置
        }
    };


    String goodsDetailId = "";
    String color = "";
    String pricenum = "";
    String phone = "";


    /**
     * 显示popupWindow
     */
    private void showPopwindow() {
        //加载弹出框的布局
        contentView = LayoutInflater.from(ShopDetailActivity.this).inflate(
                R.layout.shop_choosezhonglei, null);
        //绑定控件
        GridView gridView = (GridView) contentView.findViewById(R.id.grid);
        ImageView cha = (ImageView) contentView.findViewById(R.id.close);
        ImageView jia = (ImageView) contentView.findViewById(R.id.jia);
        ImageView jian = (ImageView) contentView.findViewById(R.id.jian);
        ImageView ima_goods = (ImageView) contentView.findViewById(R.id.ima_goods);
        TextView ok_order = (TextView) contentView.findViewById(R.id.ok_order);
        final TextView price_pop = (TextView) contentView.findViewById(R.id.price_pop);
        TextView fenlei_tx = (TextView) contentView.findViewById(R.id.fenlei_tx);
        final TextView number = (TextView) contentView.findViewById(R.id.num);
        LinearLayout fenlei_lin = (LinearLayout) contentView.findViewById(R.id.fenlei_lin);

        ok_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
                startActivity(Shop_OrderActivity.class,
                        "goodsDetailId", goodsDetailId,
                        "num", num + "",
                        "exchangeId", s.getData().getAproductExchangeMall().getId() + "",
                        "goodsId", s.getData().getAgood().getId() + "",
                        "ima", s.getData().getAgood().getImg(),
                        "name", s.getData().getAgood().getName(),
                        "price", pricenum,
                        "color", color,
                        "phone", phone,
                        "zuanshi", zuanshi
                );
            }
        });
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                if (num > maxnum) {
                    num--;
                } else {
//                    showShort("库存不够了亲");
                }
                number.setText(num + "");
                price_pop.setText("金币:" + Utils.replaceDoubleZero(((s.getData().getAproductExchangeMall().getPriceShop() * 100) * num) + ""));
            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num == 1) {

                } else {
                    num--;
                }

                number.setText(num + "");
                price_pop.setText("金币:" + Utils.replaceDoubleZero(((s.getData().getAproductExchangeMall().getPriceShop() * 100) * num) + ""));
            }
        });
        number.setText(num + "");

        cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        //创建集合  模拟数据
        list = new ArrayList<String>();
        if (s == null) {

        } else {
            String urlima = Apis.Baseima + s.getData().getAgood().getImg();
            Glide.with(this).load(urlima).into(ima_goods);
//            price_pop.setText(s.getData().getAgood().getPirce() * s.getData().getAproductExchangeMall().getPriceShop() * 100 + "金币");
            price_pop.setText("金币:" + Utils.replaceDoubleZero(((s.getData().getAproductExchangeMall().getPriceShop() * 100) * num) + ""));
            if (s.getData().getAgoodsDetail().size() == 0) {
                fenlei_lin.setVisibility(View.GONE);
                fenlei_tx.setText("选择数量");
//                pricenum = ((int) (s.getData().getAgood().getPirce() * s.getData().getAgood().getPhoneDiscount() * 0.01)) + "";
                pricenum = s.getData().getAproductExchangeMall().getPriceShop() + "";
            } else {
                for (int i = 0; i < s.getData().getAgoodsDetail().size(); i++) {
                    list.add(kinds[i]);
                }
                fenlei_tx.setText("选择分类");
                fenlei_lin.setVisibility(View.VISIBLE);
                //初始化适配器
                adapter = new MyGridAdapter(ShopDetailActivity.this, s.getData().getAgoodsDetail());
                //添加适配器
                gridView.setAdapter(adapter);
                adapter.setSeclection(0);
                //适配器通知数据改变
                adapter.notifyDataSetChanged();
                goodsDetailId = s.getData().getAgoodsDetail().get(0).getId() + "";
                color = s.getData().getAgoodsDetail().get(0).getContent() + "";
//                price_pop.setText(s.getData().getAgoodsDetail().get(0).getPrice() * s.getData().getAgood().getPhoneDiscount() + "金币");
//                pricenum = (int) (s.getData().getAgoodsDetail().get(0).getPrice() * s.getData().getAgood().getPhoneDiscount()) + "";
                price_pop.setText("金币:" + Utils.replaceDoubleZero("" + (s.getData().getAproductExchangeMall().getPriceShop() * 100d)));
                pricenum = (s.getData().getAproductExchangeMall().getPriceShop()) + "";
            }
        }

        try {
            fenlei_tx.setText("");
            fenlei_tx.setText(s.getData().getAgood().getLabelShop());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //gridview点击的监听
        gridView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        price_pop.setText(s.getData().getAgoodsDetail().get(position).getPrice() * s.getData().getAgood().getPhoneDiscount() + "金币");
                        //背景选择
                        adapter.setSeclection(position);
                        //适配器通知数据改变
                        adapter.notifyDataSetChanged();
                        goodsDetailId = s.getData().getAgoodsDetail().get(position).getId() + "";
                        color = s.getData().getAgoodsDetail().get(position).getContent() + "";
                        pricenum = (int) (s.getData().getAgoodsDetail().get(position).getPrice() * s.getData().getAgood().getPhoneDiscount()) + "";
                    }
                }
        );

        fenlei_lin.setVisibility(View.GONE);
//        setGridViewHeightBasedOnChildren(gridView);
//        adapter.notifyDataSetChanged();

        //设置弹出框的宽度和高度
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //进入退出的动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
//        setBackgroundAlpha(0.0f);//设置屏幕透明度

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });

        // 按下android回退物理键 PopipWindow消失解决
        gridView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ShopDetailActivity.this.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ShopDetailActivity.this.getWindow().setAttributes(lp);
    }

    /**
     *
     */
    private void initpingjiatab() {
        zhong.setBackgroundResource(R.color.white);
        hao.setBackgroundResource(R.color.white);
        cha.setBackgroundResource(R.color.white);
    }

    /**
     * 按钮的监听
     *
     * @param v
     */
    public void openPopWindow(View v) {

        showPopwindow();
        //从底部显示
        popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
    }

    private Integer[] banner_tu = {R.drawable.dbl, R.drawable.dl};
    private List<String> banner_list;

    ShopGoodsDetials s;

    @Override
    protected void onResume() {
        super.onResume();
        String id = getIntent().getStringExtra("id");
        String url = Apis.Base + Apis.getexchangegoods;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("id", id)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        L.d(response);
                        try {
                            s = gson.fromJson(response, ShopGoodsDetials.class);
                            if (s.getCode() == 0) {
                                banner_list = new ArrayList<>();
                                banner_list.add(Apis.Baseima + s.getData().getAgood().getImg1());
                                banner_list.add(Apis.Baseima + s.getData().getAgood().getImg2());
                                banner_list.add(Apis.Baseima + s.getData().getAgood().getImg3());
                                banner_list.add(Apis.Baseima + s.getData().getAgood().getImg4());
                                banner.setImageLoader(new GlideImageLoader());
                                //设置图片集合
                                banner.setImages(banner_list);
                                //banner设置方法全部调用完毕时最后调用
                                banner.start();


                                title.setText(s.getData().getAgood().getName());
//                            price.setText("金币:" + (int) (s.getData().getAgood().getPirce() * s.getData().getAgood().getPhoneDiscount() * 0.01));
//                            moneyTv.setText("价格:" + (int) (s.getData().getAgood().getPirce() * s.getData().getAgood().getPhoneDiscount() * 0.0001));
                                price.setText("金币:" + Utils.replaceDoubleZero((s.getData().getAproductExchangeMall().getPriceShop() * 100d) + ""));
                                moneyTv.setText("价格:" + Utils.replaceDoubleZero((s.getData().getAproductExchangeMall().getPriceShop()) + ""));
                                manposition.setText(s.getData().getAproductExchangeMall().getSalesVolume() + "人购买");
                                if (s.getData().getAgood().getAppHtml().isEmpty()) {
                                    webView.setVisibility(View.GONE);
                                } else {
                                    Log.d("qzw AppHtml", s.getData().getAgood().getAppHtml());
                                    Log.d("qzw PcHtml", s.getData().getAgood().getPcHtml());
                                    Log.d("qzw", s.getData().getAgood().getImg1());
                                    Log.d("qzw", s.getData().getAgood().getImg2());
                                    Log.d("qzw", s.getData().getAgood().getImg3());
                                    Log.d("qzw", s.getData().getAgood().getImg4());
                                    webView.loadDataWithBaseURL(null, "<style>*{margin:0;padding:0;width:100%}</style>" + s.getData().getAgood().getAppHtml(), "text/html", "utf-8", null);
                                    webView.getSettings().setJavaScriptEnabled(true);
                                    webView.getSettings().setUseWideViewPort(true);
                                    webView.getSettings().setSupportZoom(true);
                                    webView.getSettings().setLoadWithOverviewMode(true);
                                    webView.setWebChromeClient(new WebChromeClient());
                                }
                            } else {
                                showShort(s.getMsg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                });

    }
}
