package com.example.administrator.wankuoportal.fragment.store;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.ShopAboutUs;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.other.GlideImageLoader;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class Store_2_fragment extends BaseFragment {


    private android.support.v4.view.ViewPager xiangce;
    private android.widget.TextView dianpuleixing;
    private android.widget.TextView dianpujianjie;
    private android.widget.TextView shopjineng;
    private android.widget.TextView shoptuandui;
    private android.widget.ScrollView scrollview;
    private ImageView ima;
    private TextView text;
    private ImageView rencaiima1;
    private TextView rencainame1;
    private TextView rencaizhiye1;
    private TextView rencaibody1;
    private android.widget.LinearLayout rencailin1;
    private ImageView rencaiima2;
    private TextView rencainame2;
    private TextView rencaizhiye2;
    private TextView rencaibody2;
    private android.widget.LinearLayout rencailin2;
    private ImageView rencaiima3;
    private TextView rencainame3;
    private TextView rencaizhiye3;
    private TextView rencaibody3;
    private android.widget.LinearLayout rencailin3;
    ShopAboutUs s;
    private com.youth.banner.Banner banner1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.store_fragment2, container, false);//关联布局文件
        this.banner1 = (Banner) rootView.findViewById(R.id.banner1);
        this.rencailin3 = (LinearLayout) rootView.findViewById(R.id.rencailin3);
        this.rencaibody3 = (TextView) rootView.findViewById(R.id.rencaibody3);
        this.rencaizhiye3 = (TextView) rootView.findViewById(R.id.rencaizhiye3);
        this.rencainame3 = (TextView) rootView.findViewById(R.id.rencainame3);
        this.rencaiima3 = (ImageView) rootView.findViewById(R.id.rencaiima3);
        this.rencailin2 = (LinearLayout) rootView.findViewById(R.id.rencailin2);
        this.rencaibody2 = (TextView) rootView.findViewById(R.id.rencaibody2);
        this.rencaizhiye2 = (TextView) rootView.findViewById(R.id.rencaizhiye2);
        this.rencainame2 = (TextView) rootView.findViewById(R.id.rencainame2);
        this.rencaiima2 = (ImageView) rootView.findViewById(R.id.rencaiima2);
        this.rencailin1 = (LinearLayout) rootView.findViewById(R.id.rencailin1);
        this.rencaibody1 = (TextView) rootView.findViewById(R.id.rencaibody1);
        this.rencaizhiye1 = (TextView) rootView.findViewById(R.id.rencaizhiye1);
        this.rencainame1 = (TextView) rootView.findViewById(R.id.rencainame1);
        this.rencaiima1 = (ImageView) rootView.findViewById(R.id.rencaiima1);
        this.text = (TextView) rootView.findViewById(R.id.text);
        this.ima = (ImageView) rootView.findViewById(R.id.ima);
        this.scrollview = (ScrollView) rootView.findViewById(R.id.scrollview);
        this.shoptuandui = (TextView) rootView.findViewById(R.id.shop_tuandui);
        this.shopjineng = (TextView) rootView.findViewById(R.id.shop_jineng);
        this.dianpujianjie = (TextView) rootView.findViewById(R.id.dianpujianjie);
        this.dianpuleixing = (TextView) rootView.findViewById(R.id.dianpuleixing);
        this.xiangce = (ViewPager) rootView.findViewById(R.id.xiangce);

        String url = Apis.Base + Apis.shopaboutus;
        String id = new UserService(getActivity()).getstoreid();
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
                        s = gson.fromJson(response, ShopAboutUs.class);
                        if (s.getCode() == 0) {
//                            showShort(s.getMsg());
                            dianpuleixing.setText(s.getData().getInfo().getType());
                            dianpujianjie.setText(s.getData().getInfo().getIntro());
                            shopjineng.setText(s.getData().getInfo().getAbility());
                            shoptuandui.setText(s.getData().getInfo().getTeamIntro());
                            text.setText(s.getData().getInfo().getAdvantageIntro());
                            Glide.with(Store_2_fragment.this).load(Apis.Baseima + s.getData().getInfo().getAdvantageImg()).into(ima);

                            List<String> bannerlist = new ArrayList<String>();
//                            if (s.getData().getInfo().getImg2().isEmpty()) {
//                                bannerlist.add(Apis.Baseima+s.getData().getInfo().getImg1());
//                            } else {
//                                bannerlist.add(Apis.Baseima+s.getData().getInfo().getImg1());
//                                bannerlist.add(Apis.Baseima+s.getData().getInfo().getImg2());
//                            }

                            if (!s.getData().getInfo().getPhotoImg1().isEmpty()) {
                                bannerlist.add(Apis.Baseima+s.getData().getInfo().getPhotoImg1());
                            }
                            if (!s.getData().getInfo().getPhotoImg2().isEmpty()) {
                                bannerlist.add(Apis.Baseima+s.getData().getInfo().getPhotoImg2());
                            }
                            if (!s.getData().getInfo().getPhotoImg3().isEmpty()) {
                                bannerlist.add(Apis.Baseima+s.getData().getInfo().getPhotoImg3());
                            }
                            if (!s.getData().getInfo().getPhotoImg4().isEmpty()) {
                                bannerlist.add(Apis.Baseima+s.getData().getInfo().getPhotoImg4());
                            }



                            banner1.setImageLoader(new GlideImageLoader());
                            //设置图片集合
                            banner1.setImages(bannerlist);
                            //banner设置方法全部调用完毕时最后调用
                            banner1.start();


                            if (s.getData().getAPersonZone().getPersonIntro3().isEmpty()) {
                                if (s.getData().getAPersonZone().getPersonImg2().isEmpty()) {
                                    if (s.getData().getAPersonZone().getPersonImg().isEmpty()) {

                                    } else {
                                        rencailin1.setVisibility(View.VISIBLE);
                                        rencaibody1.setText(s.getData().getAPersonZone().getPersonIntro());
                                        rencainame1.setText(s.getData().getAPersonZone().getPersonName());
                                        rencaizhiye1.setText(s.getData().getAPersonZone().getPersonJob());
                                        Glide.with(Store_2_fragment.this).load(Apis.Baseima + s.getData().getAPersonZone().getPersonImg()).into(rencaiima1);
                                    }
                                } else {
                                    rencailin1.setVisibility(View.VISIBLE);
                                    rencailin2.setVisibility(View.VISIBLE);
                                    rencaibody1.setText(s.getData().getAPersonZone().getPersonIntro());
                                    rencaibody2.setText(s.getData().getAPersonZone().getPersonIntro2());
                                    rencainame1.setText(s.getData().getAPersonZone().getPersonName());
                                    rencainame2.setText(s.getData().getAPersonZone().getPersonName2());
                                    rencaizhiye1.setText(s.getData().getAPersonZone().getPersonJob());
                                    rencaizhiye2.setText(s.getData().getAPersonZone().getPersonJob2());
                                    Glide.with(Store_2_fragment.this).load(Apis.Baseima + s.getData().getAPersonZone().getPersonImg()).into(rencaiima1);
                                    Glide.with(Store_2_fragment.this).load(Apis.Baseima + s.getData().getAPersonZone().getPersonImg2()).into(rencaiima2);
                                }
                            } else {
                                rencailin1.setVisibility(View.VISIBLE);
                                rencailin2.setVisibility(View.VISIBLE);
                                rencailin3.setVisibility(View.VISIBLE);

                                rencaibody1.setText(s.getData().getAPersonZone().getPersonIntro());
                                rencaibody2.setText(s.getData().getAPersonZone().getPersonIntro2());
                                rencaibody3.setText(s.getData().getAPersonZone().getPersonIntro3());

                                rencainame1.setText(s.getData().getAPersonZone().getPersonName());
                                rencainame2.setText(s.getData().getAPersonZone().getPersonName2());
                                rencainame3.setText(s.getData().getAPersonZone().getPersonName3());

                                rencaizhiye1.setText(s.getData().getAPersonZone().getPersonJob());
                                rencaizhiye2.setText(s.getData().getAPersonZone().getPersonJob2());
                                rencaizhiye3.setText(s.getData().getAPersonZone().getPersonJob3());

                                Glide.with(Store_2_fragment.this).load(Apis.Baseima + s.getData().getAPersonZone().getPersonImg()).into(rencaiima1);
                                Glide.with(Store_2_fragment.this).load(Apis.Baseima + s.getData().getAPersonZone().getPersonImg2()).into(rencaiima2);
                                Glide.with(Store_2_fragment.this).load(Apis.Baseima + s.getData().getAPersonZone().getPersonImg3()).into(rencaiima3);

                            }


                        } else {
                            showShort(s.getMsg());
                        }
                    }
                });


        xiangce.setAdapter(new SamplePagerAdapter());
        Handler handler = new Handler();
        handler.postDelayed(runnable, 300);
        return rootView;
    }

    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            scrollview.scrollTo(0, 0);// 改变滚动条的位置
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


    private class SamplePagerAdapter extends PagerAdapter {
        private int mSize;

        SamplePagerAdapter() {
            mSize = 3;
        }

        @Override
        public int getCount() {
            return mSize;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView((View) object);
        }


        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            ImageView imageView = new ImageView(view.getContext());
            if (s != null) {
                if (s.getData().getInfo().getImg2().isEmpty()) {
//                if (position == 0) {

                    Glide.with(Store_2_fragment.this).load(Apis.Baseima + s.getData().getInfo().getImg1()).into(imageView);
//                } else if (position == 1) {
//                    Glide.with(Store_2_fragment.this).load(Apis.Baseima +s.getData().getInfo().getImg2()).into(imageView);
//                }
                } else {
                    if (position == 0) {
                        Glide.with(Store_2_fragment.this).load(Apis.Baseima + s.getData().getInfo().getImg1()).into(imageView);
                    } else if (position == 1) {
                        Glide.with(Store_2_fragment.this).load(Apis.Baseima + s.getData().getInfo().getImg2()).into(imageView);
                    }
                }
            }


            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            view.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                    .MATCH_PARENT);
            return imageView;
        }

    }
}
