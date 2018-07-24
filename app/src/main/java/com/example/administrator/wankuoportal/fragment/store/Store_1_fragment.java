package com.example.administrator.wankuoportal.fragment.store;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.DianPuPingJia_Adapter;
import com.example.administrator.wankuoportal.adapter.DianPuZiXun_Adapter;
import com.example.administrator.wankuoportal.adapter.Store1_grid2Adapter;
import com.example.administrator.wankuoportal.adapter.Store1_gridAdapter;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.BaseResultaddData;
import com.example.administrator.wankuoportal.beans.InitShop;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.other.GlideImageLoader;
import com.example.administrator.wankuoportal.ui.Store.QueRenXuQiuActivity;
import com.example.administrator.wankuoportal.ui.Store.StoreAnLiActivity;
import com.example.administrator.wankuoportal.ui.Store.StoreChanPinActivity;
import com.example.administrator.wankuoportal.ui.Store.StoreMainActivity;
import com.example.administrator.wankuoportal.ui.ZiXunDetialActivity;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.example.administrator.wankuoportal.util.QQ;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class Store_1_fragment extends BaseFragment {


    private com.youth.banner.Banner banner;
    private android.widget.TextSwitcher tvmessage;
    private List<String> banner_list;
    private List<String> banner_list1;
    private List<String> banner_list2;
    private Integer[] banner_tu = {R.drawable.dbl, R.drawable.dl};
    private GridView tuijiangrid;
    private Banner banner1;
    private GridView anligrid;
    private Banner banner2;
    private TextView dianpuzixun;
    private TextView dianpupingjia;
    private TextView hao;
    private TextView zhong;
    private TextView cha;
    private android.widget.LinearLayout pingjiaLin;
    private ListView list;
    private android.widget.ScrollView scrollview;
    private TextView allchanpin;
    private TextView allanli;
    private TextView call;
    private TextView guyong;
    private TextView share;
    private TextView morechanpin;
    private TextView moreanli;

    InitShop initShop;
    private TextView shopname;
    private TextView shoparea;
    private TextView baozhengjin;
    private TextView xiaoliang;
    private android.widget.ImageView shoucangima;
    private LinearLayout shoucang;
    private TextView baozhangjiaoyi;
    private ImageView shopima;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.store_fragment1, container, false);//关联布局文件
        this.shopima = (ImageView) rootView.findViewById(R.id.shopima);
        this.baozhangjiaoyi = (TextView) rootView.findViewById(R.id.baozhangjiaoyi);
        this.shoucang = (LinearLayout) rootView.findViewById(R.id.shoucang);
        this.shoucangima = (ImageView) rootView.findViewById(R.id.shoucang_ima);
        this.xiaoliang = (TextView) rootView.findViewById(R.id.xiaoliang);
        this.baozhengjin = (TextView) rootView.findViewById(R.id.baozhengjin);
        this.shoparea = (TextView) rootView.findViewById(R.id.shop_area);
        this.shopname = (TextView) rootView.findViewById(R.id.shopname);
        this.moreanli = (TextView) rootView.findViewById(R.id.moreanli);
        this.morechanpin = (TextView) rootView.findViewById(R.id.morechanpin);
        this.share = (TextView) rootView.findViewById(R.id.share);
        this.guyong = (TextView) rootView.findViewById(R.id.guyong);
        this.call = (TextView) rootView.findViewById(R.id.call);
        this.allanli = (TextView) rootView.findViewById(R.id.allanli);
        this.allchanpin = (TextView) rootView.findViewById(R.id.allchanpin);
        this.scrollview = (ScrollView) rootView.findViewById(R.id.scrollview);
        this.list = (ListView) rootView.findViewById(R.id.list);
        this.pingjiaLin = (LinearLayout) rootView.findViewById(R.id.pingjia_Lin);
        this.cha = (TextView) rootView.findViewById(R.id.cha);
        this.zhong = (TextView) rootView.findViewById(R.id.zhong);
        this.hao = (TextView) rootView.findViewById(R.id.hao);
        this.dianpupingjia = (TextView) rootView.findViewById(R.id.dianpupingjia);
        this.dianpuzixun = (TextView) rootView.findViewById(R.id.dianpuzixun);
        this.banner2 = (Banner) rootView.findViewById(R.id.banner2);
        this.anligrid = (GridView) rootView.findViewById(R.id.anli_grid);
        this.banner1 = (Banner) rootView.findViewById(R.id.banner1);
        this.tuijiangrid = (GridView) rootView.findViewById(R.id.tuijian_grid);
        this.tvmessage = (TextSwitcher) rootView.findViewById(R.id.tv_message);
        this.banner = (Banner) rootView.findViewById(R.id.banner);

        String id = new UserService(getActivity()).getstoreid();
        String accountId = new UserService(MyApplication.context).getaccountid();
        String initshopurl = Apis.Base + Apis.initshop;
        L.d(initshopurl);

        OkHttpUtils
                .get()
                .url(initshopurl)
                .addParams("id", id)
                .addParams("accountId", accountId)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);

                        initShop = gson.fromJson(response, InitShop.class);
                        if (initShop.getCode() == 0) {
                            shopname.setText(initShop.getData().getInfo().getShopName());
                            shoparea.setText(initShop.getData().getInfo().getType()
                                    + ": "
                                    + initShop.getData().getInfo().getShopLevel() + " "
                                    + initShop.getData().getInfo().getCity());
                            if (Integer.valueOf(initShop.getData().getInfo().getEarnestMoney()) == 0) {
                                baozhengjin.setText("服务商没有缴纳保证金，请谨慎交易");
                                baozhangjiaoyi.setVisibility(View.INVISIBLE);
                            } else {
                                baozhengjin.setText("保证金: " + initShop.getData().getInfo().getEarnestMoney() + "（先行赔付，放心交易）");
                                baozhangjiaoyi.setVisibility(View.VISIBLE);
                            }
                            xiaoliang.setText("近三个月的销量：" + initShop.getData().getInfo().getSaleNumber()
                                    + "单 收入：" + initShop.getData().getInfo().getMoney() + "元");
                            Glide.with(MyApplication.context).load(Apis.Baseima + initShop.getData().getInfo().getImg1()).into(shopima);

                            tuijiangrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    startActivity(StoreChanPinActivity.class, "id", initShop.getData().getAGoodList().get(position).getId() + "");
                                }
                            });
                            anligrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    startActivity(StoreAnLiActivity.class, "id", initShop.getData().getGoodExampleList().get(position).getId() + "");
                                }
                            });
//                            g.notifyDataSetChanged();
//                            handler = new Handler();
//                            handler.postDelayed(runnable, 200);

                            for (int i = 0; i < initShop.getData().getInformationList().size(); i++) {
                                news.add(initShop.getData().getInformationList().get(i).getTitle());
                            }


                            if (initShop.getData().getCollection().equals("true")) {
                                shoucangima.setImageDrawable(MyApplication.context.getResources().getDrawable(R.drawable.shoucangxingchoose));
                            } else {
                                shoucangima.setImageDrawable(MyApplication.context.getResources().getDrawable(R.drawable.ds));
                            }

//                            //滚动播报
//                            newsMessage();

                            //初始化banner
                            initbanner();


                            //初始化底部列表
                            initlist();

                            //初始化产品和案例
                            initfoutrGrid();

                        } else {

                            showShort("该店铺信息维护不全,请看其他店铺");
                            getActivity().finish();
                        }

                    }
                });

        //联系服务商点击

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QQ.startQQ(MyApplication.context, 1, "2389004396");
            }
        });


        //收藏点击
        shoucang.setOnClickListener(new View.OnClickListener() {
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
                        .addParams("type", "1")
                        .addParams("linkId", initShop.getData().getInfo().getId() + "")
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
                                        shoucangima.setImageDrawable(MyApplication.context.getResources().getDrawable(R.drawable.shoucangxingchoose));
                                    } else {
                                        shoucangima.setImageDrawable(MyApplication.context.getResources().getDrawable(R.drawable.ds));
                                    }

                                    showShort(s.getMsg());
                                } else if (s.getCode() == 2) {
                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                } else {
                                    showShort(s.getMsg());
                                }
                            }
                        });
            }
        });


        //所有案例点击
        allanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserService(getActivity()).setstoreposition("4");
                startActivity(StoreMainActivity.class);
            }
        });
        //所有产品点击
        allchanpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserService(getActivity()).setstoreposition("3");
                startActivity(StoreMainActivity.class);
            }
        });

        //更多案例点击
        moreanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserService(getActivity()).setstoreposition("4");
                startActivity(StoreMainActivity.class);
            }
        });
        //更多产品点击
        morechanpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserService(getActivity()).setstoreposition("3");
                startActivity(StoreMainActivity.class);
            }
        });

        //立即雇佣点击
        guyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(QueRenXuQiuActivity.class);
            }
        });
        //分享点击
//        share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                String time = new Date().getTime() + "";
//                String token = new UserService(getActivity()).gettoken();
//                String accountId = new UserService(getActivity()).getaccountid();
//                token = MD5Util.md5(time + token);
//                String url = Apis.Base + Apis.getshare + "?token=" + accountId + "," + time + "," + token;
//                OkHttpUtils
//                        .get()
//                        .url(url)
//                        .addParams("accountId", accountId)
//                        .build()
//                        .execute(new MyStringCallback() {
//                            @Override
//                            public void onError(Call call, Exception e, int id) {
//
//                            }
//
//                            @Override
//                            public void onHttpResponse(String response, int id) throws Exception {
//                                L.d(response);
//                                BaseResult baseResult = gson.fromJson(response, BaseResult.class);
//                                if (baseResult.getCode() == 0) {
//                                    shareurl = baseResult.getMsg();
//                                    UMWeb web = new UMWeb(shareurl);
//                                    web.setTitle("万阔");//标题
////                web.setThumb(thumb);  //缩略图
//                                    web.setDescription("让生活更简单！");//描述
//                                    new ShareAction(getActivity())
//                                            .withText("hello")
//                                            .withMedia(web)
//                                            .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA)
//                                            .setCallback(shareListener)
//                                            .open();
//                                } else if (baseResult.getCode() == 2) {
//                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                                    startActivity(intent);
//        getActivity().finish();
//                                } else {
//                                    Toast.makeText(getActivity(), baseResult.getMsg(), Toast.LENGTH_SHORT).show();
//                                }
//
//                            }
//                        });
//
//            }
//        });

        cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inittablayout();
                pingjiaLin.setVisibility(View.VISIBLE);
                dianpupingjia.setBackgroundResource(R.drawable.bg_hong);
                initpingjiatab();
                cha.setBackgroundResource(R.drawable.bg_hong);
                List<String> title = new ArrayList<>();
                DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(getContext(), title);
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
                DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(getContext(), title);
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

                DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(getContext(), title);
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
                dianpupingjia.setBackgroundResource(R.drawable.bg_hong);
                initpingjiatab();
                hao.setBackgroundResource(R.drawable.bg_hong);
                List<String> title = new ArrayList<>();
                DianPuPingJia_Adapter d = new DianPuPingJia_Adapter(getContext(), title);

                list.setAdapter(d);
                setListViewHeightBasedOnChildren(list);
                d.notifyDataSetChanged();
            }
        });
        //店铺资讯点击
        dianpuzixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inittablayout();
                pingjiaLin.setVisibility(View.GONE);
                dianpuzixun.setBackgroundResource(R.drawable.bg_hong);
                DianPuZiXun_Adapter d = new DianPuZiXun_Adapter(getContext(), initShop.getData().getInformationList());
                list.setAdapter(d);
                setListViewHeightBasedOnChildren(list);
                d.notifyDataSetChanged();
            }
        });
        return rootView;
    }


    //初始化轮播图
    private void initbanner() {
        banner_list = new ArrayList<>();
        banner_list1 = new ArrayList<>();
        banner_list2 = new ArrayList<>();
        for (int i = 0; i < initShop.getData().getFitting().getTopList().size(); i++) {
            banner_list.add(Apis.Baseima + initShop.getData().getFitting().getTopList().get(i).getImg());
        }
        for (int i = 0; i < initShop.getData().getFitting().getMiddleList().size(); i++) {
            banner_list1.add(Apis.Baseima + initShop.getData().getFitting().getMiddleList().get(i).getImg());
        }
        for (int i = 0; i < initShop.getData().getFitting().getBottomList().size(); i++) {
            banner_list2.add(Apis.Baseima + initShop.getData().getFitting().getBottomList().get(i).getImg());
        }

        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(banner_list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner1.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner1.setImages(banner_list1);
        //banner设置方法全部调用完毕时最后调用
        banner1.start();
        banner2.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner2.setImages(banner_list2);
        //banner设置方法全部调用完毕时最后调用
        banner2.start();
    }

    //初始化四宫格
    private void initfoutrGrid() {
        Store1_gridAdapter g = new Store1_gridAdapter(getContext(), initShop.getData().getAGoodList());
        Store1_grid2Adapter g2 = new Store1_grid2Adapter(getContext(), initShop.getData().getGoodExampleList());
        anligrid.setAdapter(g2);
        tuijiangrid.setAdapter(g);
        setGridViewHeightBasedOnChildren(anligrid);
        setGridViewHeightBasedOnChildren(tuijiangrid);
        handler = new Handler();
        handler.postDelayed(runnable, 300);
    }

    //初始化评价标题
    private void initpingjiatab() {
        zhong.setBackgroundResource(R.color.white);
        hao.setBackgroundResource(R.color.white);
        cha.setBackgroundResource(R.color.white);
    }

    String shareurl = "";

    //获取分享的地址
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        scrollview.scrollTo(0, 0);// 改变滚动条的位置
    }

    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            scrollview.scrollTo(0, 0);// 改变滚动条的位置
        }
    };

    private void initlist() {
        pingjiaLin.setVisibility(View.GONE);
        dianpuzixun.setBackgroundResource(R.drawable.bg_hong);

        DianPuZiXun_Adapter d = new DianPuZiXun_Adapter(getContext(), initShop.getData().getInformationList());
        list.setAdapter(d);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(ZiXunDetialActivity.class, "id", initShop.getData().getInformationList().get(position).getId() + "");
            }
        });
        setListViewHeightBasedOnChildren(list);
        d.notifyDataSetChanged();
        handler = new Handler();
        handler.postDelayed(runnable, 300);
    }

    private void inittablayout() {
        dianpuzixun.setBackgroundResource(R.color.white);
        dianpupingjia.setBackgroundResource(R.color.white);
    }


    //滚动新闻
    private void newsMessage() {

        tvmessage.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getActivity());
                textView.setSingleLine();
                textView.setTextSize(13);//字号
                textView.setTextColor(Color.parseColor("#ff4200"));
                textView.setEllipsize(TextUtils.TruncateAt.END);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_VERTICAL;
                textView.setLayoutParams(params);
                return textView;
            }
        });

        new Thread() {
            @Override
            public void run() {
                while (index < news.size()) {
                    synchronized (this) {
                        SystemClock.sleep(3000);//每隔4秒滚动一次
                        handler.sendEmptyMessage(NEWS_MESSAGE_TEXTVIEW);
                    }
                }
            }
        }.start();
    }

    private List<String> news = new ArrayList<>();
    public static final int NEWS_MESSAGE_TEXTVIEW = 100;//通知公告信息
    private int index = 0;//textview上下滚动下标
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEWS_MESSAGE_TEXTVIEW:
                    index++;
                    tvmessage.setText(news.get(index));
                    if (index == news.size()) {
                        index = 0;
                    }
                    break;
                default:
                    break;
            }
        }
    };

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

    //设置gridiview的高度
    public static void setGridViewHeightBasedOnChildren(GridView listView) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 2;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加2，相当于listAdapter.getCount()小于等于2时 循环一次，计算一次item的高度，
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
        // 设置参数
        listView.setLayoutParams(params);
    }


    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {

            Toast.makeText(getActivity(), "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), "取消了", Toast.LENGTH_LONG).show();

        }
    };
}
