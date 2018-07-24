package com.example.administrator.wankuoportal.aaPackage.helper;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.adapter.MainClassificationGridViewAdpter;
import com.example.administrator.wankuoportal.aaPackage.bean.ClassificationBean;
import com.example.administrator.wankuoportal.aaPackage.bean.MessageBean;
import com.example.administrator.wankuoportal.adapter.GridFourAdapter;
import com.example.administrator.wankuoportal.adapter.GridtenAdapter;
import com.example.administrator.wankuoportal.base.MyApplication;
import com.example.administrator.wankuoportal.beans.BusinessBean;
import com.example.administrator.wankuoportal.beans.NodeBean;
import com.example.administrator.wankuoportal.util.LocationUtil;
import com.flysand.mylibrary.util.Utils;
import com.oushangfeng.marqueelayout.MarqueeLayout;
import com.oushangfeng.marqueelayout.MarqueeLayoutAdapter;
import com.oushangfeng.marqueelayout.OnItemClickListener;
import com.zaaach.citypicker.CityPickerManger;
import com.zaaach.citypicker.listener.LocationChangeListener;
import com.zaaach.citypicker.listener.SelectCityListener;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/24.
 *     desc    :
 * </pre>
 */
public class DataHelper {

    public static void initSwitcher(MarqueeLayout mMarqueeLayout, List<MessageBean> beans, OnItemClickListener listener) {

        MarqueeLayoutAdapter mSrcAdapter = new MarqueeLayoutAdapter<MessageBean>(beans) {
            @Override
            public int getItemLayoutId() {
                return R.layout.item_simple_text;
            }

            @Override
            public void initView(View view, int position, MessageBean item) {
                try {
                    ((TextView) view).setText(item.getNacigationQuestion());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        mSrcAdapter.setItemClickListener(listener);
        mMarqueeLayout.setAdapter(mSrcAdapter);
        mMarqueeLayout.start();
    }

    public static void selectCity(TextView cityTv) {
        CityPickerManger.getInstance(cityTv.getContext()).start(new SelectCityListener() {
            LocationUtil locationUtil;

            @Override
            public void onStartLocation(LocationChangeListener locationChangeListener) {
                locationUtil = new LocationUtil(MyApplication.getInstance());
                locationUtil.setLocationListener(new LocationUtil.LocationListener() {
                    @Override
                    public void onLocationChanged(BDLocation aMapLocation) {
                        locationChangeListener.onLocationSuccess(aMapLocation.getCity(), aMapLocation.getDistrict());
                    }

                    @Override
                    public void onLocationFailure(String err) {
                        locationChangeListener.onLocationFail(null);
                    }
                });
                locationUtil.startLocation();
            }

            @Override
            public void onSelectCity(String city) {
                if (!TextUtils.isEmpty(city)) {
                    cityTv.setText(city.replace("市", ""));
                } else {
                    cityTv.setText("");
                }
            }

            @Override
            public void onCancel() {
                //                if (locationUtil != null)
                //                    locationUtil.stopLocation();

            }
        });
    }

    //初始化四宫格
    public static void initfoutrGrid(List<BusinessBean> businessBeans, GridView gridView, AdapterView.OnItemClickListener listener) {
        businessBeans.clear();
        BusinessBean bean = new BusinessBean();
        bean.setTitle("入驻开店接单赚钱");
        bean.setBody("千万优质订单等你来赚");
        bean.setNothing("已入住商家");
        bean.setNum("56086");
        bean.setUnit("家");
        bean.setImg(R.drawable.gridfour1);
        businessBeans.add(bean);

        BusinessBean bean1 = new BusinessBean();
        bean1.setTitle("创新精准广告投放");
        bean1.setBody("快速提升企业品牌和业绩");
        bean1.setNothing("已投放广告");
        bean1.setNum(Utils.getSaveStringData(MyApplication.getInstance(), "adCount", "0"));
        bean1.setUnit("次");
        bean1.setImg(R.drawable.gridfour2);
        businessBeans.add(bean1);

        BusinessBean bean2 = new BusinessBean();
        bean2.setTitle("注册会员立即赚钱");
        bean2.setBody("看看咨询分享就赚钱");
        bean2.setNothing("已注册会员");
        bean2.setNum(com.flysand.mylibrary.util.Utils.getSaveStringData(MyApplication.getInstance(), "numRegist", "0"));
        bean2.setUnit("位");
        bean2.setImg(R.drawable.gridfour3);
        businessBeans.add(bean2);

        BusinessBean bean3 = new BusinessBean();
        bean3.setTitle("合伙人火爆招募中");
        bean3.setBody("坐拥城市百万年收益");
        bean3.setNothing("申请合伙人");
        bean3.setNum("59808");
        bean3.setUnit("次");
        bean3.setImg(R.drawable.gridfour4);
        businessBeans.add(bean3);

        GridFourAdapter mGridFourAdapter = new GridFourAdapter(businessBeans, gridView.getContext());
        gridView.setAdapter(mGridFourAdapter);
        gridView.setOnItemClickListener(listener);
        setGridViewHeight(gridView, 2);
    }

    public static void setGridViewHeight(GridView gridView, int col) {
        // 固定列宽，有多少列
        int totalHeight = 0;
        for (int i = 0; i < gridView.getAdapter().getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = gridView.getAdapter().getView(i, null, gridView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }
        // 获取listview的布局参数
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置参数
        gridView.setLayoutParams(params);
    }

    public static void initClassificationData(List<ClassificationBean> classificationBeans, GridView gridView, AdapterView.OnItemClickListener listener) {
        classificationBeans.clear();
        String[] titles = {"全部分类", "金币商城", "企业认证", "企业招聘", "万阔人才", "服务商家", "万阔山庄", "营销学院", "生活资讯", "新闻公告"};
        Integer[] icons = {R.drawable.inx0011, R.drawable.inx0012, R.drawable.inx0013, R.drawable.inx0014, R.drawable.inx0015, R.drawable.inx0016, R.drawable.wk_manor_icon, R.drawable.inx0018, R.drawable.inx0019, R.drawable.inx0020,};
        for (int i = 0; i < titles.length; i++) {
            ClassificationBean bean = new ClassificationBean();
            bean.setImgRes(icons[i]);
            bean.setTitle(titles[i]);
            classificationBeans.add(bean);
        }
        gridView.setAdapter(new MainClassificationGridViewAdpter(gridView.getContext(), classificationBeans));
        gridView.setOnItemClickListener(listener);
    }

    public static void updatefoutrGrid(List<BusinessBean> businessBeans, String business, String ad, String member, String partner, GridView gridView) {
        try {
//            mBeanList.get(0).setNum(business);
            if (!TextUtils.isEmpty(ad))
                businessBeans.get(1).setNum(ad);
            if (!TextUtils.isEmpty(member))
                businessBeans.get(2).setNum(member);
//            mBeanList.get(3).setNum(partner);
            GridFourAdapter mGridFourAdapter = new GridFourAdapter(businessBeans, gridView.getContext());
            gridView.setAdapter(mGridFourAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void initTenGrid(List<NodeBean> nodeBeans, GridView gridView, AdapterView.OnItemClickListener listener) {
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

        GridtenAdapter g = new GridtenAdapter(nodeBeans, mDrawableList, gridView.getContext());
        gridView.setAdapter(g);
        setGridViewHeight(gridView, 5);
        g.notifyDataSetChanged();
        gridView.setOnItemClickListener(listener);

    }


}
