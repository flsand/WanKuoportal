package com.example.administrator.wankuoportal.fragment.main;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
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
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.GridFourAdapter;
import com.example.administrator.wankuoportal.adapter.GridtenAdapter;
import com.example.administrator.wankuoportal.adapter.IndexServiceAdapter;
import com.example.administrator.wankuoportal.adapter.MyGridViewAdpter;
import com.example.administrator.wankuoportal.adapter.MyGridViewCeshiAdpter;
import com.example.administrator.wankuoportal.adapter.MyViewPagerAdapter;
import com.example.administrator.wankuoportal.adapter.ServiceShangAdapter;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.BusinessBean;
import com.example.administrator.wankuoportal.beans.GetIndexserve;
import com.example.administrator.wankuoportal.beans.Gettopline;
import com.example.administrator.wankuoportal.beans.NodeBean;
import com.example.administrator.wankuoportal.beans.SearchFuwushang;
import com.example.administrator.wankuoportal.coustomView.LoadmoreScrollView;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.ButtonModel;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.ProdctBean;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.guide.GuideUtil;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.other.GlideImageLoader;
import com.example.administrator.wankuoportal.ui.CityChooseActivity;
import com.example.administrator.wankuoportal.ui.CityHeHuo.CityPartnerActivity;
import com.example.administrator.wankuoportal.ui.FWSJ.FWSJActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyInfoActivity;
import com.example.administrator.wankuoportal.ui.MainActivity;
import com.example.administrator.wankuoportal.ui.RenCai.RenCaiMainActivity;
import com.example.administrator.wankuoportal.ui.SHZX.SHZXActivity;
import com.example.administrator.wankuoportal.ui.SetUp.InvitationActivity;
import com.example.administrator.wankuoportal.ui.ShouyeKaidianActivity;
import com.example.administrator.wankuoportal.ui.Store.StoreChanPinActivity;
import com.example.administrator.wankuoportal.ui.Store.StoreMainActivity;
import com.example.administrator.wankuoportal.ui.XWGG.WebActivity;
import com.example.administrator.wankuoportal.ui.XWGG.XWGGActivity;
import com.example.administrator.wankuoportal.ui.YXXY.MarketingActivity;
import com.example.administrator.wankuoportal.ui.YXXY.YXXYActivity;
import com.example.administrator.wankuoportal.ui.ZhaoPin.RecruitmentMainActivity;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.ui.download.FuWuShangActivity;
import com.example.administrator.wankuoportal.ui.download.GuangGaoZhuActivity;
import com.example.administrator.wankuoportal.ui.download.MeiTiZhuActivity;
import com.example.administrator.wankuoportal.ui.paotui.PaoTuiActivity;
import com.example.administrator.wankuoportal.ui.search.SearchActivity;
import com.example.administrator.wankuoportal.ui.shop.ShopActivity;
import com.example.administrator.wankuoportal.util.DataRepository;
import com.example.administrator.wankuoportal.util.LogUtils;
import com.flysand.mylibrary.util.JSONUtil;
import com.flysand.mylibrary.util.MyHandler;
import com.flysand.mylibrary.util.MyToast;
import com.flysand.mylibrary.util.Utils;
import com.jauker.widget.BadgeView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;
import okhttp3.Call;


/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class Showye_fragment extends BaseFragment {


    private ViewPager viewPager;
    private LinearLayout group;//圆点指示器


    private ImageView[] ivPoints;//小圆点图片的集合
    private int totalPage; //总的页数
    private int mPageSize = 10; //每页显示的最大的数量
    private List<ProdctBean> listDatas;//总的数据源
    private List<ProdctBean> listDatasceshi;//总的数据源
    private List<ProdctBean> listinx;//总的数据源
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private String[] proName = {"商务服务", "生活服务", "技术开发", "营销推广", "微信营销",
            "微博营销", "直播营销", "新闻媒体", "新闻公告", "万哥跑腿", "兑换商城", "企业招聘", "万阔人才", "服务商家",
            "孵化园", "答题赚钱", "企业认证", "营销学院", "生活资讯", "爱心公益"};
    private Integer[] tupian = {R.drawable.s, R.drawable.tu2, R.drawable.tu3, R.drawable.tu4,
            R.drawable.tu6, R.drawable.tu7, R.drawable.tu8, R.drawable.tu5, R.drawable.tu9, R.drawable.tu10,
            R.drawable.tu11, R.drawable.tu12, R.drawable.tu13, R.drawable.tu14, R.drawable.tu15,
            R.drawable.tu16, R.drawable.tu17, R.drawable.tu18, R.drawable.tu19, R.drawable.tu20
    };
    private String[] inxname = {"全部分类", "金币商城", "企业认证", "企业招聘", "万阔人才",
            "服务商家", "答题赚钱", "营销学院", "生活资讯", "新闻公告"};
    private Integer[] inxtupian = {R.drawable.inx0011, R.drawable.inx0012, R.drawable.inx0013, R.drawable.inx0014,
            R.drawable.inx0015, R.drawable.inx0016, R.drawable.inx0017, R.drawable.inx0018, R.drawable.inx0019, R.drawable.inx0020,
    };


    private int index = 0;//textview上下滚动下标
    private int indexbiaoshi = 0;//textview上下滚动下标

    public static final int NEWS_MESSAGE_TEXTVIEW = 100;//通知公告信息

    private Integer[] banner_tu = {R.drawable.banner11, R.drawable.banner12, R.drawable.banner13};
    private List<Integer> banner_list;
    private List<Integer> banner_list3;
    private List<Integer> banner_list4;
    private Integer[] banner_tu2 = {R.drawable.banner21, R.drawable.banner22};
    private Integer[] banner_tu3 = {R.drawable.banner31, R.drawable.banner32};
    private Integer[] banner_tu4 = {R.drawable.banner41, R.drawable.banner42};
    private List<Integer> banner_list2;
    private TextView toolbartitle;
    private Toolbar basetoolbar;
    private Banner banner1;
    private LinearLayout sytoutiao;
    private ViewPager viewpager;
    private LinearLayout points;
    private GridView sygridfour;
    private Banner banner2;
    private TextView guanggaozhuhead;
    private TextView guanggaozhubody;
    private LinearLayout linguangaozhu;
    private TextView meitizhuhead;
    private TextView meitizhubody;
    private LinearLayout linmeitizhu;
    private TextView fuwushanghead;
    private TextView fuwushangbody;
    private LinearLayout linfuwushang;
    private TextView guzhuhead;
    private TextView guzhubody;
    private LinearLayout linguzhu;
    private TextView huiyuanhead;
    private TextView huiyuanbody;
    private TextView shouye_city;
    private TextView tuijianfuwushang;
    private TextView tuijianfuwu;
    private LinearLayout linhuiyuan;
    private Banner banner3;
    private GridView sygridten;
    private Banner banner4;
    private TabLayout tabLayoutsy;
    private ViewPager viewPagersy;
    private LinearLayout citylin;
    private ListView list_shouye;
    private TextSwitcher textSwitcher;
    private TextView shouyecity;
    private TextView searchshouye;
    private TextSwitcher tvmessage;
    private ListView listshouye;
    private View searchLayout;

    public LocationClient mLocationClient = null;

    public BDLocationListener myListener = new MyLocationListener();
    private android.widget.RelativeLayout fanyere;
    private GridView ceshi;
    private ImageView imageView2;
    private LoadmoreScrollView scollview;
    private ImageView xiaoxi;
    private GridView gridfenlei;
    //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口，原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明

    private List<BusinessBean> mBeanList;
    private GridFourAdapter mGridFourAdapter;
    private String numRegist = "0";
    String fuwu = "fuwu";
    SearchFuwushang searchFuwushang;
    GetIndexserve searchFuWu;
    private boolean canLoadMore = true;
    private int page = 1;
    boolean isLoadRecommendedSuccess = false;
    boolean isLoadServiceProviderSuccess = false;

    List<SearchFuwushang.DatasBean> serviceProviderlist = new ArrayList<>();
    List<GetIndexserve.DatasBean> recommendedServiceList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.shouye_layout, container, false);//关联布局文件
        this.gridfenlei = (GridView) rootView.findViewById(R.id.gridfenlei);
        this.listshouye = (ListView) rootView.findViewById(R.id.list_shouye);
        this.imageView2 = (ImageView) rootView.findViewById(R.id.imageView2);
        this.points = (LinearLayout) rootView.findViewById(R.id.points);
        this.viewpager = (ViewPager) rootView.findViewById(R.id.viewpager);
        this.tvmessage = (TextSwitcher) rootView.findViewById(R.id.tv_message);
        this.xiaoxi = (ImageView) rootView.findViewById(R.id.xiaoxi);
        this.scollview = (LoadmoreScrollView) rootView.findViewById(R.id.scollview);
        this.searchLayout = rootView.findViewById(R.id.search_layout);

        mLocationClient = new LocationClient(getActivity());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //定位
        initLocation();

        this.ceshi = (GridView) rootView.findViewById(R.id.ceshi);
        this.fanyere = (RelativeLayout) rootView.findViewById(R.id.fanye_re);
        this.searchshouye = (TextView) rootView.findViewById(R.id.search_shouye);
        this.shouyecity = (TextView) rootView.findViewById(R.id.shouye_city);
        this.citylin = (LinearLayout) rootView.findViewById(R.id.city_lin);
        this.banner4 = (Banner) rootView.findViewById(R.id.banner4);
        textSwitcher = (TextSwitcher) rootView.findViewById(R.id.tv_message);
        this.sygridten = (GridView) rootView.findViewById(R.id.sy_gridten);
        this.banner3 = (Banner) rootView.findViewById(R.id.banner3);
        this.linhuiyuan = (LinearLayout) rootView.findViewById(R.id.lin_huiyuan);
        this.huiyuanbody = (TextView) rootView.findViewById(R.id.huiyuan_body);
        this.huiyuanhead = (TextView) rootView.findViewById(R.id.huiyuan_head);
        this.linguzhu = (LinearLayout) rootView.findViewById(R.id.lin_guzhu);
        this.guzhubody = (TextView) rootView.findViewById(R.id.guzhu_body);
        this.guzhuhead = (TextView) rootView.findViewById(R.id.guzhu_head);
        this.linfuwushang = (LinearLayout) rootView.findViewById(R.id.lin_fuwushang);
        this.fuwushangbody = (TextView) rootView.findViewById(R.id.fuwushang_body);
        this.fuwushanghead = (TextView) rootView.findViewById(R.id.fuwushang_head);
        this.linmeitizhu = (LinearLayout) rootView.findViewById(R.id.lin_meitizhu);
        this.meitizhubody = (TextView) rootView.findViewById(R.id.meitizhu_body);
        this.meitizhuhead = (TextView) rootView.findViewById(R.id.meitizhu_head);
        this.linguangaozhu = (LinearLayout) rootView.findViewById(R.id.lin_guangaozhu);
        this.guanggaozhubody = (TextView) rootView.findViewById(R.id.guanggaozhu_body);
        this.guanggaozhuhead = (TextView) rootView.findViewById(R.id.guanggaozhu_head);
        this.banner2 = (Banner) rootView.findViewById(R.id.banner2);
        this.sygridfour = (GridView) rootView.findViewById(R.id.sy_gridfour);
        this.sytoutiao = (LinearLayout) rootView.findViewById(R.id.sy_toutiao);
        this.banner1 = (Banner) rootView.findViewById(R.id.banner1);
        this.basetoolbar = (Toolbar) rootView.findViewById(R.id.base_toolbar);
        this.toolbartitle = (TextView) rootView.findViewById(R.id.toolbar_title);
        this.tuijianfuwushang = (TextView) rootView.findViewById(R.id.tuijianfuwushang);
        this.tuijianfuwu = (TextView) rootView.findViewById(R.id.tuijianfuwu);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        group = (LinearLayout) rootView.findViewById(R.id.points);
        list_shouye = (ListView) rootView.findViewById(R.id.list_shouye);
        numRegist = Utils.getSaveStringData(MyApplication.context, "numRegist", "0");
        searchshouye.setOnClickListener((v) -> {
            startActivity(SearchActivity.class);
        });
        //监听搜索框获取焦点
//        searchshouye.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    startActivity(SearchActivity.class);
//                    searchshouye.clearFocus();//失去焦点
//                } else {
//                }
//            }
//        });

        //顶部四宫格点击监听
        sygridfour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("qzw", "position = " + position);
                Intent intent = null;
                if (position == 0) {
                    intent = new Intent(getActivity(), ShouyeKaidianActivity.class);
                }
                if (position == 1) {
                    intent = new Intent(getActivity(), GuangGaoZhuActivity.class);
                }
                if (position == 2) {
                    if (new UserService(getActivity()).getislogin().equals("0")) {
                        ((MainActivity) getActivity()).gotoWode();
                        return;
                    } else {
                        intent = new Intent(getActivity(), LoginActivity.class);
                    }
                }
                if (position == 3) {
                    intent = new Intent(getActivity(), CityPartnerActivity.class);
                }
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });

        //初始化控件
        initView();
        //添加业务逻辑
        initData();
        //初始化点击事件
        initOnclick();
        //初始化滚动栏
        newsMessage();
        //初始化首页四宫格
        initfoutrGrid();
        //初始化首页十宫格
        getGongGeData();
//        inittenGrid();
        //初始化轮播图
        initbanner();
        //初始化底部服务和服务商
        inittablayoutoncreat();
        //初始化上拉加载
        initPullLoadMore();


        xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyInfoActivity.class);
            }
        });

        textSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indexbiaoshi < 0) {

                } else {
                    try {
                        startActivity(WebActivity.class, "url", Apis.Base + Apis.getnavigationdetail + "?navigationtid=" + toplinelist.get(indexbiaoshi).getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

//        new MyHandler(100){
//            @Override
//            public void run() {
//                scollview.scrollTo(0,0);
//            }
//        };

        return rootView;
    }

    private void initPullLoadMore() {
        canLoadMore = true;
        page = 1;
        scollview.setOnScrollToBottomLintener(new LoadmoreScrollView.OnScrollToBottomListener() {
            @Override
            public void onScrollBottomListener(boolean isBottom) {
                if (isBottom && canLoadMore) {
                    Utils.print("LoadmoreScrollView");
                    if ("fuwu".equals(fuwu)) {
                        loadRecommendedServiceData(false);
                    } else {
                        loadServiceProviderData();
                    }

                }
            }

        });

    }


    private void initxiaoxi() {
        DataRepository.getInstance().getNumInfoNotRead(getActivity(), new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                try {
                    BadgeView badge = new BadgeView(MyApplication.context);
                    badge.setBackground(9, getResources().getColor(R.color.red_shape));
                    badge.setTargetView(xiaoxi);
                    badge.setBadgeCount(integer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private void initLocation() {

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("gcj02");
        //可选，默认gcj02，设置返回的定位结果坐标系

//        int span = 1000;
        option.setScanSpan(0);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps


        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

//        option.setIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

//    option.setWifiValidTime(5*60*1000);
        //可选，7.2版本新增能力，如果您设置了这个接口，首次启动定位时，会先判断当前WiFi是否超出有效期，超出有效期的话，会先重新扫描WiFi，然后再定位

        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    public void initGuide(MainActivity mainActivity, ImageView fbrw, LinearLayout paotuilin) {
        new GuideUtil().initGuide(mainActivity, searchLayout, fbrw, paotuilin);
    }

    public void getGongGeData() {
        String url = Apis.Base + Apis.getLabel;
        OkHttpUtils
                .post()
                .url(url)
                .build()
                .execute(new MyStringCallback() {

                    @Override
                    public void onHttpSuccess(String s, JSONArray jsonArray, int i, int i1, int i2) throws Exception {

                        List<NodeBean> nodeBeans = JSONUtil.toJavaBeanList(NodeBean.class, jsonArray);
                        inittenGrid(nodeBeans);
                    }
                });

    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            //获取定位结果
//            location.getTime();    //获取定位时间
//            location.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
//            location.getLocType();    //获取定位类型
//            location.getLatitude();    //获取纬度信息
//            location.getLongitude();    //获取经度信息
//            location.getRadius();    //获取定位精准度
//            location.getAddrStr();    //获取地址信息
//
//            location.getCountryCode();    //获取国家码
//            location.getCityCode();    //获取城市码
//
//
//            location.getCountry();    //获取国家信息
//            location.getProvince();//获取省份信息
//            location.getCity();    //获取城市信息
//            location.getDistrict();    //获取区县信息
            try {

                L.d(location.getDistrict());
                if (location.getDistrict().isEmpty() || location.getDistrict() == null
                        || location.getCountry().isEmpty() || location.getCountry() == null
                        || location.getProvince().isEmpty() || location.getProvince() == null
                        || location.getCity().isEmpty() || location.getCity() == null) {
                    new UserService(getContext()).setAddress("中国/山东省/烟台市/福山区");
                    shouyecity.setText("烟台");
                } else {

                    new UserService(MyApplication.context).setAddress(location.getCountry() + "/"
                            + location.getProvince() + "/" + location.getCity() + "/" + location.getDistrict());

                    if (!new UserService(MyApplication.context).getshouyecity().equals("0")) {
                        shouyecity.setText(new UserService(MyApplication.context).getshouyecity());
                    } else {
                        new UserService(MyApplication.context).setshouyecity(location.getCity().substring(0, location.getCity().length() - 1) + "");
                        shouyecity.setText(new UserService(MyApplication.context).getshouyecity());
                    }

                    mLocationClient.stop();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//            location.getStreet();    //获取街道信息
//            location.getStreetNumber();    //获取街道码
//            location.getLocationDescribe();    //获取当前位置描述信息
//            location.getPoiList();    //获取当前位置周边POI信息
//
//            location.getBuildingID();    //室内精准定位下，获取楼宇ID
//            location.getBuildingName();    //室内精准定位下，获取楼宇名称
//            location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息
//
//
//            //获取当前信息的 百度地图地址
//
//
//            if (location.getLocType() == BDLocation.TypeGpsLocation) {
//                //当前为GPS定位结果，可获取以下信息
//                location.getSpeed();    //获取当前速度，单位：公里每小时
//                location.getSatelliteNumber();    //获取当前卫星数
//                location.getAltitude();    //获取海拔高度信息，单位米
//                location.getDirection();    //获取方向信息，单位度
//
//            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//
//                //当前为网络定位结果，可获取以下信息
//                location.getOperators();    //获取运营商信息
//
//            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
//
//                //当前为网络定位结果
//
//            } else if (location.getLocType() == BDLocation.TypeServerError) {
//
//                //当前网络定位失败
//                //可将定位唯一ID、IMEI、定位失败时间反馈至loc-bugs@baidu.com
//
//            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//
//                //当前网络不通
//
//            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//
//                //当前缺少定位依据，可能是用户没有授权，建议弹出提示框让用户开启权限
//                //可进一步参考onLocDiagnosticMessage中的错误返回码
//            }


        }

//        /**
//         * 回调定位诊断信息，开发者可以根据相关信息解决定位遇到的一些问题
//         * 自动回调，相同的diagnosticType只会回调一次
//         *
//         * @param locType           当前定位类型
//         * @param diagnosticType    诊断类型（1~9）
//         * @param diagnosticMessage 具体的诊断信息释义
//         */
//        public void onLocDiagnosticMessage(int locType, int diagnosticType, String diagnosticMessage) {
//
//            if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_GPS) {
//
//                //建议打开GPS
//
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_WIFI) {
//
//                //建议打开wifi，不必连接，这样有助于提高网络定位精度！
//
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_LOC_PERMISSION) {
//
//                //定位权限受限，建议提示用户授予APP定位权限！
//
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_NET) {
//
//                //网络异常造成定位失败，建议用户确认网络状态是否异常！
//
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CLOSE_FLYMODE) {
//
//                //手机飞行模式造成定位失败，建议用户关闭飞行模式后再重试定位！
//
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_INSERT_SIMCARD_OR_OPEN_WIFI) {
//
//                //无法获取任何定位依据，建议用户打开wifi或者插入sim卡重试！
//
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_OPEN_PHONE_LOC_SWITCH) {
//
//                //无法获取有效定位依据，建议用户打开手机设置里的定位开关后重试！
//
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_SERVER_FAIL) {
//
//                //百度定位服务端定位失败
//                //建议反馈location.getLocationID()和大体定位时间到loc-bugs@baidu.com
//
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_FAIL_UNKNOWN) {
//
//                //无法获取有效定位依据，但无法确定具体原因
//                //建议检查是否有安全软件屏蔽相关定位权限
//                //或调用LocationClient.restart()重新启动后重试！
//
//            }
//        }


    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEWS_MESSAGE_TEXTVIEW:
                    textSwitcher.setText(toplinelist.get(index).getNacigationQuestion());
                    indexbiaoshi = index;
                    index++;
                    if (index + 1 > toplinelist.size()) {
                        index = 0;
                    }
                    break;
                default:
                    break;
            }
        }
    };

    List<Gettopline.DatasBean> toplinelist = new ArrayList();

    Thread textSwitcherThread;

    //滚动新闻
    private void newsMessage() {
        String url = Apis.Base + Apis.gettopline;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback() {

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        Gettopline s = gson.fromJson(response, Gettopline.class);
                        if (s.getCode() == 0) {
                            toplinelist = new ArrayList();
                            for (int i = 0; i < s.getDatas().size(); i++) {
                                toplinelist.add(s.getDatas().get(i));
                            }
                            if (textSwitcherThread == null) {
                                handler.sendEmptyMessage(NEWS_MESSAGE_TEXTVIEW);
                                //获取完成数据后开始滚动播放
                                textSwitcherThread = new Thread() {
                                    @Override
                                    public void run() {
                                        while (index < toplinelist.size()) {
                                            synchronized (this) {
                                                if (this.isInterrupted())
                                                    return;
                                                SystemClock.sleep(4000);//每隔4秒滚动一次
                                                handler.sendEmptyMessage(NEWS_MESSAGE_TEXTVIEW);
                                            }
                                        }
                                    }
                                };
                                textSwitcherThread.start();
                            }
                        } else {
//                            showShort(s.getMsg());
                        }
                    }
                });


        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getActivity());
                textView.setSingleLine();
                textView.setTextSize(13);//字号
                textView.setTextColor(Color.parseColor("#999999"));
                textView.setEllipsize(TextUtils.TruncateAt.END);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_VERTICAL;
                textView.setLayoutParams(params);
                return textView;
            }
        });


    }


    @Override
    public void onResume() {
        super.onResume();
        if (new UserService(MyApplication.context).getshouyecity().equals("0")) {
//            shouyecity.setText(new UserService(MyApplication.context).getshouyecity());
        } else {
            shouyecity.setText(new UserService(MyApplication.context).getshouyecity());
        }
        initxiaoxi();
        getDataAndInitFourGrid();
    }

    //初始化底部服务和服务商
    private void inittablayoutoncreat() {
        tuijianfuwu.setBackgroundResource(R.drawable.bg_hong);
        tuijianfuwu.setBackgroundResource(R.drawable.bg_hong);
        isLoadRecommendedSuccess = false;
        canLoadMore = true;
        page = 1;
        recommendedServiceList.clear();
        loadRecommendedServiceData(true);
    }


    //初始化轮播图
    private void initbanner() {
        banner_list = new ArrayList<>();
        for (int i = 0; i < banner_tu.length; i++) {
            banner_list.add(banner_tu[i]);
        }
        banner_list2 = new ArrayList<>();
        for (int i = 0; i < banner_tu2.length; i++) {
            banner_list2.add(banner_tu2[i]);
        }
        banner_list3 = new ArrayList<>();
        for (int i = 0; i < banner_tu3.length; i++) {
            banner_list3.add(banner_tu3[i]);
        }
        banner_list4 = new ArrayList<>();
        for (int i = 0; i < banner_tu4.length; i++) {
            banner_list4.add(banner_tu4[i]);
        }
        banner1.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner1.setImages(banner_list);
        banner1.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        banner1.start();
        banner2.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner2.setImages(banner_list2);
        banner2.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        banner2.start();
        //设置图片集合
        banner3.setImageLoader(new GlideImageLoader());
        banner3.setImages(banner_list3);
        banner3.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        banner3.start();
        banner4.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner4.setImages(banner_list4);
        banner4.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        banner4.start();

        banner1.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                new UserService(MyApplication.context).settabposition("4");
                startActivity(MainActivity.class);
            }
        });
        banner2.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (position == 0) {
                    new UserService(MyApplication.context).settabposition("3");
                    startActivity(MainActivity.class);
                } else {
                    startActivity(FuWuShangActivity.class);
                }
            }
        });
        banner3.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (position == 0) {
                    startActivity(FuWuShangActivity.class);
                } else {
                    startActivity(InvitationActivity.class);
                }
            }
        });
        banner4.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (position == 0) {
                    startActivity(MeiTiZhuActivity.class);
                } else {
                    new UserService(MyApplication.context).settabposition("3");
                    startActivity(MainActivity.class);
                }
            }
        });

    }


    //初始化十宫格
    private void inittenGrid(List<NodeBean> nodeBeans) {
//        title.add("商务服务");
//        title.add("生活服务");
//        title.add("技术开发");
//        title.add("营销推广");
//        title.add("孵化园");
//        title.add("微信营销");
//        title.add("微博营销");
//        title.add("新闻媒体");
        List<Integer> mDrawableList = new ArrayList<>();
        NodeBean nodeBean = new NodeBean();
        nodeBean.setLabelName("直播营销");
        nodeBeans.add(nodeBean);
        NodeBean nodeBean1 = new NodeBean();
        nodeBean1.setLabelName("爱心公益");
        nodeBeans.add(nodeBean1);
        mDrawableList.add(R.drawable.inx0003);
        mDrawableList.add(R.drawable.inx0002);
        mDrawableList.add(R.drawable.inx0004);
        mDrawableList.add(R.drawable.inx0010);
        mDrawableList.add(R.drawable.inx0005);
        mDrawableList.add(R.drawable.inx0006);
        mDrawableList.add(R.drawable.inx0007);
        mDrawableList.add(R.drawable.inx0009);
        mDrawableList.add(R.drawable.inx0008);
        mDrawableList.add(R.drawable.inx0001);

        GridtenAdapter g = new GridtenAdapter(nodeBeans, mDrawableList, getContext());
        sygridten.setAdapter(g);
        setGridViewHeightBasedOnChildren10(sygridten);
        g.notifyDataSetChanged();
        sygridten.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position < nodeBeans.size() - 2) {
                    UserService u = new UserService(getActivity());
                    u.settabposition("1");
                    u.setSubLabelPosition(position);
                    startActivity(MainActivity.class);
                } else {
                    showShort("程序猿努力开发中...");
                }
            }
        });

    }

    private void getDataAndInitFourGrid() {
        String url = Apis.Base + "/api/appinfo/getRegist";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("getUserInfo Error");
                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        int num = 0;
                        try {
                            num = getNum(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            numRegist = num + "";
                            Utils.saveData(MyApplication.context, "numRegist", numRegist);
                            updatefoutrGrid(null, null, numRegist, null);
                        }
                    }
                });
    }

    private int getNum(String s) {
        int start = 0;
        int end = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                start = i;
                break;
            }
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            if (Character.isDigit(s.charAt(i))) {
                end = i;
                break;
            }
        }
        return Integer.parseInt(s.substring(start, end + 1));
    }

    //初始化四宫格
    private void initfoutrGrid() {
        mBeanList = new ArrayList<>();
        BusinessBean bean = new BusinessBean();
        bean.setTitle("入驻开店接单赚钱");
        bean.setBody("千万优质订单等你来赚");
        bean.setNothing("已入住商家");
        bean.setNum("59808");
        bean.setUnit("家");
        bean.setImg(R.drawable.gridfour1);
        mBeanList.add(bean);

        BusinessBean bean1 = new BusinessBean();
        bean1.setTitle("创新精准广告投放");
        bean1.setBody("快速提升企业品牌和业绩");
        bean1.setNothing("已投放广告");
        bean1.setNum("59808");
        bean1.setUnit("次");
        bean1.setImg(R.drawable.gridfour2);
        mBeanList.add(bean1);

        BusinessBean bean2 = new BusinessBean();
        bean2.setTitle("注册会员立即赚钱");
        bean2.setBody("看看咨询分享就赚钱");
        bean2.setNothing("已注册会员");
        bean2.setNum(numRegist);
        bean2.setUnit("位");
        bean2.setImg(R.drawable.gridfour3);
        mBeanList.add(bean2);

        BusinessBean bean3 = new BusinessBean();
        bean3.setTitle("合伙人火爆招募中");
        bean3.setBody("坐拥城市百万年收益");
        bean3.setNothing("申请合伙人");
        bean3.setNum("59808");
        bean3.setUnit("次");
        bean3.setImg(R.drawable.gridfour4);
        mBeanList.add(bean3);

        mGridFourAdapter = new GridFourAdapter(mBeanList, getContext());
        sygridfour.setAdapter(mGridFourAdapter);
        setGridViewHeightBasedOnChildren(sygridfour);
        mGridFourAdapter.notifyDataSetChanged();
    }

    private void updatefoutrGrid(String business, String ad, String member, String partner) {

        try {
//            mBeanList.get(0).setNum(business);
//            mBeanList.get(1).setNum(ad);
            mBeanList.get(2).setNum(member);
//            mBeanList.get(3).setNum(partner);
            mGridFourAdapter = new GridFourAdapter(mBeanList, getContext());
            sygridfour.setAdapter(mGridFourAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initOnclick() {
        inittablayout();

        //地址选择
        citylin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(CityChooseActivity.class, "city", new UserService(MyApplication.context).getshouyecity());
            }
        });

        //首页列表
        list_shouye.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    new UserService(MyApplication.context).setstoreposition("0");
                    if (new UserService(MyApplication.context).getislogin().equals("0")) {
                        if (fuwu.equals("fuwu")) {
                            startActivity(StoreChanPinActivity.class, "id", recommendedServiceList.get(position).getAgood().getId() + "");
                        } else {
                            new UserService(MyApplication.context).setstoreposition("0");
                            new UserService(MyApplication.context).setstoreid(serviceProviderlist.get(position).getAshopData().getId() + "");
                            startActivity(StoreMainActivity.class);
                        }
                    } else {
                        startActivity(LoginActivity.class);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    new MyToast(MyApplication.context).setText("程序员玩命开发中");
                }

            }
        });

        //广告主点击
        linguangaozhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GuangGaoZhuActivity.class);
            }
        });

        //服务商点击
        linfuwushang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FuWuShangActivity.class);
            }
        });
        //媒体主点击
        linmeitizhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MeiTiZhuActivity.class);
            }
        });
        //雇主点击
        linguzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserService(getActivity()).setShenfen("guzhu");
                new UserService(getActivity()).settabposition("4");
                startActivity(MainActivity.class);
            }
        });
        //会员点击
        linhuiyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserService(getActivity()).setShenfen("huiyuan");
                new UserService(getActivity()).settabposition("4");
                startActivity(MainActivity.class);
            }
        });
        //推荐服务
        tuijianfuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fuwu = "fuwu";
                inittablayout();
                tuijianfuwu.setBackgroundResource(R.drawable.bg_hong);
                isLoadRecommendedSuccess = false;
                canLoadMore = true;
                page = 1;
                recommendedServiceList.clear();
                loadRecommendedServiceData(false);
            }

        });

        //推荐服务商点击
        tuijianfuwushang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fuwu = "fuwushang";
                canLoadMore = true;
                page = 1;
                serviceProviderlist.clear();
                isLoadServiceProviderSuccess = false;
                inittablayout();
                tuijianfuwushang.setBackgroundResource(R.drawable.bg_hong);
                loadServiceProviderData();
            }
        });
    }


    /*
    * * 加载推荐服务数据
       */
    private void loadRecommendedServiceData(boolean isScoll) {

        if (isLoadRecommendedSuccess)
            return;
        isLoadRecommendedSuccess = true;
        String url = Apis.Base + Apis.getindexserve;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("page", page + "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        isLoadRecommendedSuccess = false;
                        L.d(response);
                        searchFuWu = gson.fromJson(response, GetIndexserve.class);
                        if (page == 1)
                            recommendedServiceList.clear();

                        if (searchFuWu.getCode() == 0) {
                            recommendedServiceList.addAll(searchFuWu.getDatas());
                            IndexServiceAdapter serviceAdapter = new IndexServiceAdapter(recommendedServiceList, MyApplication.context);
                            list_shouye.setAdapter(serviceAdapter);
                            setListViewHeightBasedOnChildren(list_shouye, page == 1 & isScoll);
                            page++;
                        }
                        canLoadMore = !searchFuWu.getPageInfo().isLast();
                    }
                });

    }

    /**
     * 加载服务商数据
     */
    private void loadServiceProviderData() {
        if (isLoadServiceProviderSuccess)
            return;
        isLoadServiceProviderSuccess = true;
        String url = Apis.Base + Apis.searchserveprovider;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("content", "")
                .addParams("type", "0")
                .addParams("shoptype", "0")
                .addParams("level", "0")
                .addParams("page", page + "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        isLoadServiceProviderSuccess = false;
                        L.d("fuwushang", response);
                        searchFuwushang = gson.fromJson(response, SearchFuwushang.class);
                        if (page == 1)
                            serviceProviderlist.clear();
                        serviceProviderlist.addAll(searchFuwushang.getDatas());
                        if (searchFuwushang.getCode() == 0) {
                            ServiceShangAdapter serviceAdapter = new ServiceShangAdapter(serviceProviderlist, MyApplication.context);
//                                    list_shouye.clearFocus();
                            list_shouye.setAdapter(serviceAdapter);
                            setListViewHeightBasedOnChildren(list_shouye, false);
                            page++;
                        } else {
                        }
                        canLoadMore = !searchFuwushang.getPageInfo().isLast();
                    }
                });
    }

    private void inittablayout() {
        tuijianfuwushang.setBackgroundResource(R.drawable.bg_hui);
        tuijianfuwu.setBackgroundResource(R.drawable.bg_hui);
    }

    //设置listview的高度
    public void setListViewHeightBasedOnChildren(ListView listView, boolean isScroll) {
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
        if (isScroll) {
            new MyHandler(10) {
                @Override
                public void run() {
                    scollview.scrollTo(0, 0);
                }
            };
        }
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
        // 设置margin
//        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }

    //设置gridiview的高度
    public static void setGridViewHeightBasedOnChildren10(GridView listView) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 5;// listView.getNumColumns();
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
        // 设置margin
//        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }


    private void initView() {

        listinx = new ArrayList<>();
        for (int i = 0; i < inxname.length; i++) {
            listinx.add(new ProdctBean(inxname[i], inxtupian[i]));
        }
        gridfenlei.setAdapter(new MyGridViewCeshiAdpter(MyApplication.context, listinx));
        setGridViewHeightBasedOnChildren10(gridfenlei);
        ViewGroup.LayoutParams gridfenleiparams = gridfenlei.getLayoutParams();
        gridfenleiparams.height = gridfenleiparams.height + 10;
        gridfenlei.setLayoutParams(gridfenleiparams);

        gridfenlei.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object obj = gridfenlei.getItemAtPosition(position);
                if (obj != null && obj instanceof ProdctBean) {
                    System.out.println(obj);
                    if (((ProdctBean) obj).getName().equals("新闻公告")) {
                        startActivity(XWGGActivity.class);
                    } else if (((ProdctBean) obj).getName().equals("企业招聘")) {
                        startActivity(RecruitmentMainActivity.class);
                    } else if (((ProdctBean) obj).getName().equals("兑换商城")) {
                        startActivity(ShopActivity.class);
                    } else if (((ProdctBean) obj).getName().equals("金币商城")) {
                        startActivity(ShopActivity.class);
                    } else if (((ProdctBean) obj).getName().equals("答题赚钱")) {
                        ((MainActivity) getActivity()).switchAnswer();
                    } else if (((ProdctBean) obj).getName().equals("万阔人才")) {
                        startActivity(RenCaiMainActivity.class);
                    } else if (((ProdctBean) obj).getName().equals("服务商家")) {
                        startActivity(FWSJActivity.class);
                    } else if (((ProdctBean) obj).getName().equals("孵化园")) {
                        showShort("程序员正在努力奋斗！");
                    } else if (((ProdctBean) obj).getName().equals("征婚订制")) {
                        showShort("程序员正在努力奋斗！");
                    } else if (((ProdctBean) obj).getName().equals("企业认证")) {
                        showShort("程序员正在努力奋斗！");
//                        startActivity(QYRZActivity.class);
                    } else if (((ProdctBean) obj).getName().equals("营销学院")) {
//                        startActivity(YXXYActivity.class);
                        startActivity(MarketingActivity.class);
                    } else if (((ProdctBean) obj).getName().equals("生活资讯")) {
                        startActivity(SHZXActivity.class);
                    } else if (((ProdctBean) obj).getName().equals("爱心公益")) {
                        showShort("程序员正在努力奋斗！");
                    } else if (((ProdctBean) obj).getName().equals("全部分类")) {
                        UserService u = new UserService(MyApplication.context);
                        u.settabposition("1");
                        u.setfenleiposition("0");
                        u.setSubLabelPosition(0);
                        startActivity(MainActivity.class);
                    }

                }
            }
        });

        listDatas = new ArrayList<ProdctBean>();
        for (int i = 0; i < proName.length; i++) {
            listDatas.add(new ProdctBean(proName[i], tupian[i]));
        }
        listDatasceshi = new ArrayList<ProdctBean>();
        for (int i = 0; i < 10; i++) {
            listDatasceshi.add(new ProdctBean(proName[i], tupian[i]));
        }

        //动态设置翻页导航栏高度
        ceshi.setAdapter(new MyGridViewCeshiAdpter(getActivity(), listDatasceshi));
        setGridViewHeightBasedOnChildren10(ceshi);
        ViewGroup.LayoutParams params = ceshi.getLayoutParams();
        ViewGroup.LayoutParams params1 = fanyere.getLayoutParams();
        params1.height = params.height + 50;
        fanyere.setLayoutParams(params1);


    }


    private void initData() {
        //总的页数向上取整
        totalPage = (int) Math.ceil(listDatas.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<View>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) View.inflate(getActivity(), R.layout.item_gridview, null);
            gridView.setAdapter(new MyGridViewAdpter(getActivity(), listDatas, i, mPageSize));
            //添加item点击监听
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    Object obj = gridView.getItemAtPosition(position);
                    if (obj != null && obj instanceof ProdctBean) {
                        System.out.println(obj);
                        if (((ProdctBean) obj).getName().equals("新闻公告")) {
                            startActivity(XWGGActivity.class);
                        } else if (((ProdctBean) obj).getName().equals("企业招聘")) {
                            startActivity(RecruitmentMainActivity.class);
                        } else if (((ProdctBean) obj).getName().equals("金币商城")) {
                            startActivity(ShopActivity.class);
                        } else if (((ProdctBean) obj).getName().equals("万阔人才")) {
                            startActivity(RenCaiMainActivity.class);
                        } else if (((ProdctBean) obj).getName().equals("服务商家")) {
                            startActivity(FWSJActivity.class);
                        } else if (((ProdctBean) obj).getName().equals("孵化园")) {
                            showShort("程序员正在努力奋斗！");
                        } else if (((ProdctBean) obj).getName().equals("征婚订制")) {
                            showShort("程序员正在努力奋斗！");
                        } else if (((ProdctBean) obj).getName().equals("企业认证")) {
                            showShort("程序员正在努力奋斗！");
//                            startActivity(QYRZActivity.class);
                        } else if (((ProdctBean) obj).getName().equals("营销学院")) {
                            startActivity(YXXYActivity.class);
                        } else if (((ProdctBean) obj).getName().equals("生活资讯")) {
                            startActivity(SHZXActivity.class);
                        } else if (((ProdctBean) obj).getName().equals("爱心公益")) {
                            showShort("程序员正在努力奋斗！");
                        } else if (((ProdctBean) obj).getName().equals("万哥跑腿")) {
                            startActivity(PaoTuiActivity.class);
                        } else {
                            UserService u = new UserService(getActivity());
                            if (position < 8) {
                                u.settabposition("1");
                                switch (position) {
                                    case 0:
                                        u.setfenleiposition("0");
                                        startActivity(MainActivity.class);
                                        break;
                                    case 1:
                                        u.setfenleiposition("1");
                                        startActivity(MainActivity.class);
                                        break;
                                    case 2:
                                        u.setfenleiposition("2");
                                        startActivity(MainActivity.class);
                                        break;
                                    case 3:
                                        u.setfenleiposition("3");
                                        startActivity(MainActivity.class);
                                        break;
                                    case 4:
                                        u.setfenleiposition("4");
                                        startActivity(MainActivity.class);
                                        break;
                                    case 5:
                                        u.setfenleiposition("5");
                                        startActivity(MainActivity.class);
                                        break;
                                    case 6:
                                        u.setfenleiposition("6");
                                        startActivity(MainActivity.class);
                                        break;
                                    case 7:
                                        u.setfenleiposition("7");
                                        startActivity(MainActivity.class);
                                        break;
                                    default:
                                        break;
                                }


                            }
                        }

                    }
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }

        //设置ViewPager适配器
        viewPager.setAdapter(new MyViewPagerAdapter(viewPagerList));

        //添加小圆点
        ivPoints = new ImageView[totalPage];
        for (int i = 0; i < totalPage; i++) {
            //循坏加入点点图片组
            ivPoints[i] = new ImageView(getActivity());
            if (i == 0) {
                ivPoints[i].setImageResource(R.drawable.page_focuese);
            } else {
                ivPoints[i].setImageResource(R.drawable.page_unfocused);
            }
            ivPoints[i].setPadding(8, 8, 8, 8);
            group.addView(ivPoints[i]);
        }
        //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //currentPage = position;
                for (int i = 0; i < totalPage; i++) {
                    if (i == position) {
                        ivPoints[i].setImageResource(R.drawable.page_focuese);
                    } else {
                        ivPoints[i].setImageResource(R.drawable.page_unfocused);
                    }
                }
            }
        });
    }


    private List<ButtonModel> setData() {
        List<ButtonModel> data = new ArrayList<>();
        ButtonModel buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_1);
        buttonModel.setName("美食");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_2);
        buttonModel.setName("电影");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_3);
        buttonModel.setName("酒店");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_4);
        buttonModel.setName("休闲娱乐");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_5);
        buttonModel.setName("外卖");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_6);
        buttonModel.setName("机票/火车票");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_7);
        buttonModel.setName("KTV");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_8);
        buttonModel.setName("周边游");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_9);
        buttonModel.setName("丽人");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_10);
        buttonModel.setName("旅游出行");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_11);
        buttonModel.setName("品质酒店");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_12);
        buttonModel.setName("生活服务");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_13);
        buttonModel.setName("足疗按摩");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_14);
        buttonModel.setName("母婴亲子");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_15);
        buttonModel.setName("结婚");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_16);
        buttonModel.setName("景点");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_17);
        buttonModel.setName("温泉");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_18);
        buttonModel.setName("学习培训");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_19);
        buttonModel.setName("洗浴/汗蒸");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_20);
        buttonModel.setName("全部分类");
        data.add(buttonModel);
        return data;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (textSwitcherThread != null) {
            textSwitcherThread.interrupt();
            textSwitcherThread = null;
        }
    }


}
