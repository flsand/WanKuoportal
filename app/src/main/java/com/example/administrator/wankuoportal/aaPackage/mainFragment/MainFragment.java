package com.example.administrator.wankuoportal.aaPackage.mainFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.activity.NewMainActivity;
import com.example.administrator.wankuoportal.aaPackage.activity.manor.ManorActivity;
import com.example.administrator.wankuoportal.aaPackage.adapter.MainProviderAapter;
import com.example.administrator.wankuoportal.aaPackage.adapter.MainServiceAdapter;
import com.example.administrator.wankuoportal.aaPackage.bean.BannerBean;
import com.example.administrator.wankuoportal.aaPackage.bean.ClassificationBean;
import com.example.administrator.wankuoportal.aaPackage.bean.MessageBean;
import com.example.administrator.wankuoportal.aaPackage.bean.RecommendedServiceBean;
import com.example.administrator.wankuoportal.aaPackage.bean.ServiceProviderBean;
import com.example.administrator.wankuoportal.aaPackage.coomon.Define;
import com.example.administrator.wankuoportal.aaPackage.coustom.StickyNavLayout;
import com.example.administrator.wankuoportal.aaPackage.helper.DataHelper;
import com.example.administrator.wankuoportal.aaPackage.utils.HidingScrollListener;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.base.MyBaseFragment;
import com.example.administrator.wankuoportal.beans.BusinessBean;
import com.example.administrator.wankuoportal.beans.NodeBean;
import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.other.GlideBannerLoader;
import com.example.administrator.wankuoportal.ui.CityHeHuo.CityPartnerActivity;
import com.example.administrator.wankuoportal.ui.FWSJ.FWSJActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyInfoActivity;
import com.example.administrator.wankuoportal.ui.RenCai.RenCaiMainActivity;
import com.example.administrator.wankuoportal.ui.SHZX.SHZXActivity;
import com.example.administrator.wankuoportal.ui.SetUp.InvitationActivity;
import com.example.administrator.wankuoportal.ui.ShouyeKaidianActivity;
import com.example.administrator.wankuoportal.ui.Store.StoreChanPinActivity;
import com.example.administrator.wankuoportal.ui.Store.StoreMainActivity;
import com.example.administrator.wankuoportal.ui.XWGG.WebActivity;
import com.example.administrator.wankuoportal.ui.XWGG.XWGGActivity;
import com.example.administrator.wankuoportal.ui.YXXY.MarketingActivity;
import com.example.administrator.wankuoportal.ui.ZhaoPin.RecruitmentMainActivity;
import com.example.administrator.wankuoportal.ui.download.FuWuShangActivity;
import com.example.administrator.wankuoportal.ui.download.GuangGaoZhuActivity;
import com.example.administrator.wankuoportal.ui.download.MeiTiZhuActivity;
import com.example.administrator.wankuoportal.ui.search.SearchActivity;
import com.example.administrator.wankuoportal.ui.shop.ShopActivity;
import com.example.administrator.wankuoportal.util.PagingHelper;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.base.BaseAdapter;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.listener.RecyclerOnScrollListener;
import com.flysand.mylibrary.listener.RecyclerViewItemClickListener;
import com.flysand.mylibrary.util.JSONUtil;
import com.flysand.mylibrary.util.Utils;
import com.jauker.widget.BadgeView;
import com.oushangfeng.marqueelayout.MarqueeLayout;
import com.oushangfeng.marqueelayout.OnItemClickListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/17.
 *     desc    : 首页
 * </pre>
 */
public class MainFragment extends MyBaseFragment implements View.OnClickListener, OnItemClickListener, RecyclerViewItemClickListener, RecyclerOnScrollListener.OnScrollListener {


    List<Integer> bannerIcons1 = Arrays.asList(R.drawable.banner11, R.drawable.banner12, R.drawable.banner13);
    List<Integer> bannerIcons2 = Arrays.asList(R.drawable.banner21, R.drawable.banner22);
    List<Integer> bannerIcons3 = Arrays.asList(R.drawable.banner31, R.drawable.banner32);
    List<Integer> bannerIcons4 = Arrays.asList(R.drawable.banner41, R.drawable.banner42);

    @BindView(R.id.myview_stickynavlayout_recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.mian_fragment_city_tv)
    TextView cityTv;

    @BindView(R.id.main_infomation_iv)
    ImageView infomationIv;

    @BindView(R.id.base_toolbar)
    Toolbar toolbar;

    @BindView(R.id.banner1)
    Banner mBanner1;

    @BindView(R.id.banner2)
    Banner mBanner2;

    @BindView(R.id.banner3)
    Banner mBanner3;

    @BindView(R.id.banner4)
    Banner mBanner4;

    @BindView(R.id.main_marquee_layout)
    MarqueeLayout messageSwitcherTs;

    @BindView(R.id.main_top_classification_gv)
    GridView mClassificationGv;//全部分类

    @BindView(R.id.main_top_classification_four_gv)
    GridView mClassificationFourGv;//四宫格

    @BindView(R.id.gridten_gv)
    GridView mGridTenGv;//十宫格

    @BindView(R.id.myview_stickynavlayout_indicator)
    TabLayout mTabLayout;

    @BindView(R.id.main_fragment_stickyNavLayout)
    StickyNavLayout stickyNavLayout;

    @BindView(R.id.main_fragment_up_bt)
    Button scorollTopBt;

    Unbinder unbinder;
    //文本自动垂直滚动消息
    List<MessageBean> messageBeanList = new ArrayList<>();
    //全部分类数据
    List<ClassificationBean> classificationBeans = new ArrayList<>();
    //四宫格数据
    List<BusinessBean> businessBeans = new ArrayList<>();

    BannerBean bannerBean;

    boolean isShowTopBtn = false;
    PagingHelper recommendedPagingHelper = new PagingHelper();
    PagingHelper providerPagingHelper = new PagingHelper();
    //推荐
    List<RecommendedServiceBean> recommendedServiceList = new ArrayList<>();
    //服务商
    List<ServiceProviderBean> serviceProviderlist = new ArrayList<>();

    MainServiceAdapter serviceAdapter;
    MainProviderAapter serviceShopAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frgment_main_layout, container, false);//关联布局文件
        unbinder = ButterKnife.bind(this, rootView);
        afterViews();
        initTopView();
        initBanner();
        getBannerData();
        initDefultData();
        getNetworkData();
        loadRecommendedServiceData();
        loadServiceProviderData();
        return rootView;
    }

    private void getBannerData() {
        httpGet("mainBanner", new RequestParams(), Apis.getBannerUrl);
    }

    private void initTopView() {

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
        mTabLayout.setSelectedTabIndicatorHeight(3);
        mTabLayout.setTabTextColors(
                getResources().getColor(R.color.main_text_color),
                getResources().getColor(R.color.colorPrimary)
        );
        mTabLayout.setBackgroundResource(R.drawable.shape_tab_layout_bg);
        TabLayout.Tab tab = mTabLayout.newTab().setText("推荐产品/服务");
        mTabLayout.addTab(tab, true);
        tab = mTabLayout.newTab().setText("推荐服务商");
        mTabLayout.addTab(tab);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mRecyclerView.stopScroll();
                int position = tab.getPosition();
                Utils.print(position == 0 ? "推荐产品/服务" : "推荐服务商");

                if (position == 0) {
                    mRecyclerView.setAdapter(serviceAdapter);
                } else {
                    mRecyclerView.setAdapter(serviceShopAdapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        stickyNavLayout.setOnOnScrollListener((x, y) -> {

            if (y > Utils.dpToPx(getActivity(), 1000) && isShowTopBtn) {
                scorollTopBt.setVisibility(View.VISIBLE);
            } else {
                scorollTopBt.setVisibility(View.GONE);
            }

        });

    }


    private void initBanner() {
        initBanner(mBanner1);
        initBanner(mBanner2);
        initBanner(mBanner3);
        initBanner(mBanner4);
    }

    private void initBanner(Banner banner) {
        int delayTime = 3000;
        banner.setImageLoader(new GlideBannerLoader());
        banner.setDelayTime(delayTime);
    }

    private void startBanner(Banner banner, List<BannerBean.BannerDetailBean> beans) {
        banner.setImages(beans);
        banner.setOnBannerListener(new BannerClick(getActivity(), beans));
        banner.start();
    }

    private static class BannerClick implements OnBannerListener {

        private List<BannerBean.BannerDetailBean> detailBeans;
        private Context context;

        public BannerClick(Context context, List<BannerBean.BannerDetailBean> detailBeans) {
            this.detailBeans = detailBeans;
            this.context = context;
        }

        @Override
        public void OnBannerClick(int position) {
            try {
//                /addressd链接地址  type链接类型 1店铺链接，2自定义链接  img 图片
                BannerBean.BannerDetailBean detailBean = detailBeans.get(position);
                if (detailBean.getType() == 1) {//1店铺链接
                    Intent intent = new Intent(context, StoreChanPinActivity.class);
                    intent.putExtra("id", detailBean.getAddress());
                    context.startActivity(intent);
                } else {//2自定义链接
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("url", detailBean.getAddress());
                    context.startActivity(intent);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initDefultData() {
        setDefultCity();
        DataHelper.initClassificationData(classificationBeans, mClassificationGv, new ClassificationOnItemClickListener());
        DataHelper.initfoutrGrid(businessBeans, mClassificationFourGv, new foutrGridOnItemClickListener());
    }

    private void getNetworkData() {
        //Switcher
        httpGet("switcher", new RequestParams(), Apis.gettopline);
        //十宫格
        httpPost("getLabel", new RequestParams(), Apis.getLabel);
    }

    @Override
    public void onResume() {
        super.onResume();
        //获取注册会员数
        getRegistCount();
        getAdCount();
    }

    private void getAdCount() {
        httpGetNoDialog("getAdCount", new RequestParams(), Apis.getAdCount);
    }

    private void getRegistCount() {
        Utils.print("businessBeans =" + businessBeans.size());
        httpGetNoDialog("getRegistCount", new RequestParams(), Apis.getRegistCount);
    }

    private void setDefultCity() {
        if (!TextUtils.isEmpty(app.getLocation().getCity())) {
            cityTv.setText(app.getLocation().getCity().replace("市", ""));
        }
    }


    protected void afterViews() {
        serviceAdapter = new MainServiceAdapter(this, recommendedServiceList);
        serviceShopAdapter = new MainProviderAapter(this, serviceProviderlist);
        mRecyclerView.setAdapter(serviceAdapter);

//        //标题滑动隐藏
        mRecyclerView.addOnScrollListener(hidingScrollListener);

        ProjectUtil.initRecyclerView(mRecyclerView, this);
/*
        mSmartRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            }

            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                Utils.print("percent =" + percent + "   mOffset =" + offset);
//                toolbar.setAlpha(1 - Math.min(percent, 1));
//                float mOffset = offset / 2;
//                toolbar.setTranslationY(mOffset - mScrollY);
//                toolbar.setAlpha(1 - Math.min(percent, 1));
            }

            @Override
            public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int extendHeight) {

                Utils.print("percent =" + percent + "   mOffset =" + offset);
            }
        });*/
    }

    private void switchHalaman(int index) {
        ((NewMainActivity) getActivity()).switchHalaman(index);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.mian_fragment_city_tv, R.id.search_layout, R.id.main_infomation_iv, R.id.main_fragment_up_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            //我是广告主
            case R.id.advertisers_layout:
                Utils.print("我是广告主");
                startActivity(GuangGaoZhuActivity.class);
                break;
            //我是店长
            case R.id.negoziante_layout:
                Utils.print("我是店长");
                startActivity(MeiTiZhuActivity.class);
                break;
            //我是服务商
            case R.id.facilitator_layout:
                Utils.print("我是服务商");
                startActivity(FuWuShangActivity.class);
                break;
            //我是雇主
            case R.id.employers_layout:
                Utils.print("我是雇主");
                switchHalaman(Define.MainPages.MY_EMPLOYERS);
                break;
            //我是会员
            case R.id.member_layout:
                switchHalaman(Define.MainPages.MY_MEMBERS);
                Utils.print("我是会员");
                break;
            case R.id.mian_fragment_city_tv:
                Utils.print("选择城市");
                DataHelper.selectCity(cityTv);
                break;
            case R.id.search_layout:
                startActivity(SearchActivity.class);
                break;
            case R.id.main_infomation_iv:
                startActivity(MyInfoActivity.class);
                break;
            case R.id.main_fragment_up_bt:
                Utils.print("滚动到顶部");
                if (isShowTopBtn) {
                    stickyNavLayout.scrollTo(0, 0);
                } else {
                    mRecyclerView.scrollToPosition(0);
                }
                scorollTopBt.setVisibility(View.GONE);
                break;

        }
    }


    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) throws Exception {
        super.onHttpSuccess(type, jsonObject);
        if ("mainBanner".equals(type)) {

            bannerBean = JSONUtil.toJavaBean(BannerBean.class, jsonObject);
            /**
             aAdvertiseRecordWx1;//获取微信的轮播图第一张
             aAdvertiseRecordWx2;//获取微信的轮播图第二张
             aAdvertiseRecordWx3;//获取微信的轮播图第三张
             aAdvertiseRecordWxMid1;//获取微信中间第一张
             aAdvertiseRecordWxMid2;//获取微信中间第二张
             aAdvertiseRecordUpMid1;//获取微信下端第一张
             aAdvertiseRecordUpMid2;//获取微信下端第二张
             aAdvertiseRecordUpMid3;//获取微信首页分类下第一张
             aAdvertiseRecordUpMid4;//获取微信首页分类第二张
             * addressd链接地址  type链接类型 1店铺链接，2自定义链接  img 图片
             */
            startBanner(mBanner1, bannerBean.getaAdvertiseRecordWx1());
            startBanner(mBanner2, bannerBean.getaAdvertiseRecordWxMid1());
            startBanner(mBanner3, bannerBean.getaAdvertiseRecordUpMid1());
            startBanner(mBanner4, bannerBean.getaAdvertiseRecordUpMid2());
        }
    }

    @Override
    public boolean onHttpAnalysisIntercept(String type, JSONObject var2) throws Exception {
        if ("getAdCount".equals(type)) {

            String adCount = var2.getString("data");
            Utils.print("adCount = " + adCount);
            Utils.saveData(getActivity(), "adCount", adCount);
            DataHelper.updatefoutrGrid(businessBeans, null, adCount, null, null, mClassificationFourGv);

            return true;
        } else if ("getRegistCount".equals(type)) {
            String numRegist = var2.getString("data");
            Utils.print("response = " + numRegist);
            com.flysand.mylibrary.util.Utils.saveData(MyApplication.context, "numRegist", numRegist);
            DataHelper.updatefoutrGrid(businessBeans, null, null, numRegist, null, mClassificationFourGv);
            return true;
        }
        return super.onHttpAnalysisIntercept(type, var2);
    }

    @Override
    public void onHttpSuccess(String type, JSONArray jsonArray, int i, int i1, int i2) throws Exception {
        super.onHttpSuccess(type, jsonArray, i, i1, i2);
        if ("switcher".equals(type)) {
            messageBeanList.clear();
            messageBeanList.addAll(JSONUtil.toJavaBeanList(MessageBean.class, jsonArray));
            DataHelper.initSwitcher(messageSwitcherTs, messageBeanList, this);
        } else if ("getLabel".equals(type)) {
            List<NodeBean> nodeBeans = JSONUtil.toJavaBeanList(NodeBean.class, jsonArray);
            DataHelper.initTenGrid(nodeBeans, mGridTenGv, new TenGridOnItemClickListener());
        }
    }

    //switcher item点击
    @Override
    public void onClick(View view, int position) {
        try {
            if (messageBeanList.size() > 0 && !TextUtils.isEmpty(messageBeanList.get(position).getId())) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", Apis.Base + Apis.getnavigationdetail + "?navigationtid=" + messageBeanList.get(position).getId());
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRecyclerViewItemClick(BaseAdapter baseAdapter, View view, int position) {
        if (ProjectUtil.isLogin())
            return;
        if (baseAdapter == serviceAdapter) {
            Intent intent = new Intent(getActivity(), StoreChanPinActivity.class);
            intent.putExtra("id", recommendedServiceList.get(position).getAgood().getId() + "");
            startActivity(intent);
        } else if (baseAdapter == serviceShopAdapter) {
            new UserService(MyApplication.context).setstoreposition("0");
            new UserService(MyApplication.context).setstoreid(serviceProviderlist.get(position).getAshopData().getId() + "");
            startActivity(StoreMainActivity.class);
        }
    }

    @OnClick(R.id.search_layout)
    public void onViewClicked() {
    }

    public void showInfomationCount(int count) {
        BadgeView badge = new BadgeView(getActivity());
        badge.setBackground(Utils.dip2px(getActivity(), 3), getResources().getColor(R.color.red_shape));
        badge.setTargetView(infomationIv);
        badge.setBadgeCount(count);
    }

    @Override
    public void onLoadNextPage(View view) {
        if (mTabLayout.getSelectedTabPosition() == 0) {//推荐产品/服务
            if (recommendedPagingHelper.canLoadMore)
                loadRecommendedServiceData();
        } else {//推荐服务商
            if (providerPagingHelper.canLoadMore)
                loadServiceProviderData();
        }
    }

    /*
    * * 加载推荐服务数据
       */
    private void loadRecommendedServiceData() {
        if (recommendedPagingHelper.isLoading)
            return;
        recommendedPagingHelper.setLoading();
        httpGetNoDialog("loadRecommendedService", new RequestParams("page", recommendedPagingHelper.page), Apis.getindexserve);
    }

    /**
     * 加载服务商数据
     */
    private void loadServiceProviderData() {
        if (providerPagingHelper.isLoading)
            return;
        providerPagingHelper.setLoading();
        RequestParams params = new RequestParams();
        params.put("page", providerPagingHelper.page);
        params.put("type", "0");
        params.put("shoptype", "0");
        params.put("level", "0");
        httpGetNoDialog("loadServiceProviderData", params, Apis.searchserveprovider);
    }

    @Override
    public void onHttpFailure(String type, String s1) throws Exception {
        if ("getAdCount".equals(type)) {
            return;
        }
        if ("getRegistCount".equals(type))
            return;
        super.onHttpFailure(type, s1);
        if (recommendedServiceList.size() < 3 || serviceProviderlist.size() < 3) {
            isShowTopBtn = true;
        }
        if ("mainBanner".equals(type)) {
            super.initBanner(mBanner1, bannerIcons1);
            super.initBanner(mBanner2, bannerIcons2);
            super.initBanner(mBanner3, bannerIcons3);
            super.initBanner(mBanner4, bannerIcons4);
            mBanner1.setOnBannerListener(position -> {
                switchHalaman(Define.MainPages.MY_EMPLOYERS);//页面
            });
            mBanner2.setOnBannerListener(position -> {
                if (position == 0) {
                    switchHalaman(Define.MainPages.ANSWER);
                } else {
                    startActivity(FuWuShangActivity.class);
                }
            });
            mBanner3.setOnBannerListener(position -> {
                if (position == 0) {
                    startActivity(FuWuShangActivity.class);
                } else {
                    startActivity(InvitationActivity.class);
                }
            });
            mBanner4.setOnBannerListener(position -> {
                if (position == 0) {
                    startActivity(MeiTiZhuActivity.class);
                } else {
                    switchHalaman(Define.MainPages.ANSWER);
                }
            });
        }
    }

    @Override
    public void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean) throws Exception {
        if ("loadRecommendedService".equals(type)) {

            if (recommendedPagingHelper.page == 1)
                recommendedServiceList.clear();

            recommendedServiceList.addAll(JSONUtil.toJavaBeanList(RecommendedServiceBean.class, datas));

            serviceAdapter.notifyDataSetChanged();
            recommendedPagingHelper.loadCompleted();
            recommendedPagingHelper.canLoadMore = !pageInfoBean.isLast();
        } else if ("loadServiceProviderData".equals(type)) {
            if (providerPagingHelper.page == 1)
                serviceProviderlist.clear();

            serviceProviderlist.addAll(JSONUtil.toJavaBeanList(ServiceProviderBean.class, datas));
            serviceShopAdapter.notifyDataSetChanged();

            providerPagingHelper.loadCompleted();
            providerPagingHelper.canLoadMore = !pageInfoBean.isLast();
        }

        super.onHttpSuccess(type, datas, pageInfoBean);
    }

    HidingScrollListener hidingScrollListener = new HidingScrollListener(com.flysand.mylibrary.util.Utils.dp2px(MyApplication.getInstance(), 45)) {

        @Override
        public void onHide() {
//            int height = getResources().getDisplayMetrics().heightPixels;
//            toolbar.animate()
//                    .translationY(-height)
//                    .setDuration(800)
//                    .setInterpolator(new AccelerateInterpolator(2))
//                    .start();

            scorollTopBt.setVisibility(View.GONE);
        }

        @Override
        public void onShow() {
            scorollTopBt.setVisibility(View.VISIBLE);
//            toolbar.animate()
//                    .translationY(0)
//                    .setInterpolator(new DecelerateInterpolator(2))
//                    .setDuration(800)
//                    .start();
        }
    };


    private class ClassificationOnItemClickListener implements AdapterView.OnItemClickListener {
        /**
         * 顶部全部分类点击
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Utils.print("VIew - " + view);
            try {
                Class traget = null;
                switch (classificationBeans.get(position).getTitle()) {
                    case "全部分类":
                        switchHalaman(Define.MainPages.MERCHANTS);
                        break;
                    case "万阔山庄":
                        startActivity(ManorActivity.class);
                        break;
                    case "金币商城":
                        traget = ShopActivity.class;
                        break;
                    case "企业招聘":
                        traget = RecruitmentMainActivity.class;
                        break;
                    case "万阔人才":
                        traget = RenCaiMainActivity.class;
                        break;
                    case "服务商家":
                        traget = FWSJActivity.class;
                        break;
                    case "营销学院":
                        traget = MarketingActivity.class;
                        break;
                    case "生活资讯":
                        traget = SHZXActivity.class;
                        break;
                    case "新闻公告":
                        traget = XWGGActivity.class;
                        break;
                    default:
                        toast.setText("程序员正在努力奋斗！");
                        break;
                }
                if (traget != null)
                    startActivity(new Intent(getActivity(), traget));
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
    }

    private class foutrGridOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            try {
                Class traget = null;
                switch (position) {
                    case 0:
                        traget = ShouyeKaidianActivity.class;
                        break;
                    case 1:
                        traget = GuangGaoZhuActivity.class;
                        break;
                    case 2://挑转我的
                        switchHalaman(Define.MainPages.MY_EMPLOYERS);
                        break;
                    case 3:
                        traget = CityPartnerActivity.class;
                        break;
                    default:
                        break;
                }

                if (traget != null) {
                    startActivity(new Intent(getActivity(), traget));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class TenGridOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            try {
                if (position < 8) {
                    ((NewMainActivity) getActivity()).switchMerchantsFragment(position);
                } else {
                    ProjectUtil.showDevelopmentMessage();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
