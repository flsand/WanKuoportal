package com.example.administrator.wankuoportal.ui.Store;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.DatipaihangAdapter;
import com.example.administrator.wankuoportal.adapter.Store_ChanPin_Adapter;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.PaiHang;
import com.example.administrator.wankuoportal.beans.ShopExampleDetails;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.other.GlideImageLoader;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.HorizontalListView;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

public class StoreAnLiActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private com.youth.banner.Banner banner1;
    private com.example.administrator.wankuoportal.util.HorizontalListView datimingci;
    private Integer[] banner_tu = {R.drawable.dbl, R.drawable.dl};
    private List<Integer> banner_list;
    private Banner banner;
    private TextView name;
    private TextView xiaoliang;
    private TextView pingjia;
    private TextView xitong;
    private TextView hangye;
    private TextView leixing;
    private TextView yanse;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_anli);
        this.webview = (WebView) findViewById(R.id.webview);
        this.yanse = (TextView) findViewById(R.id.yanse);
        this.leixing = (TextView) findViewById(R.id.leixing);
        this.hangye = (TextView) findViewById(R.id.hangye);
        this.xitong = (TextView) findViewById(R.id.xitong);
        this.pingjia = (TextView) findViewById(R.id.pingjia);
        this.xiaoliang = (TextView) findViewById(R.id.xiaoliang);
        this.name = (TextView) findViewById(R.id.name);
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


        String url = Apis.Base + Apis.shopexampledetails + getIntent().getStringExtra("id");
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
                        ShopExampleDetails s = gson.fromJson(response, ShopExampleDetails.class);
                        List<String>  bannerlist  =  new ArrayList<String>();
                        if (s.getCode() == 0) {
                            name.setText(s.getData().getName()+"");
                            webview.loadUrl(s.getData().getAppHtml());
                            if (s.getData().getImg().isEmpty()){

                            }else {
                                bannerlist.add(Apis.Baseima+s.getData().getImg());
                            }
                            if (s.getData().getImg1().isEmpty()){

                            }else {
                                bannerlist.add(Apis.Baseima+s.getData().getImg1());
                            }
                            if (s.getData().getImg2().isEmpty()){

                            }else {
                                bannerlist.add(Apis.Baseima+s.getData().getImg2());
                            }
                            if (s.getData().getImg3().isEmpty()){

                            }else {
                                bannerlist.add(Apis.Baseima+s.getData().getImg3());
                            }
                            if (s.getData().getImg4().isEmpty()){

                            }else {
                                bannerlist.add(Apis.Baseima+s.getData().getImg4());
                            }
                            if (bannerlist.size()!=0){
                                banner1.setImageLoader(new GlideImageLoader());
                                //设置图片集合
                                banner1.setImages(bannerlist);
                                //banner设置方法全部调用完毕时最后调用
                                banner1.start();
                            }else {
                                banner_list = new ArrayList<>();
                                for (int i = 0; i < 2; i++) {
                                    banner_list.add(banner_tu[i]);
                                }
                                banner1.setImageLoader(new GlideImageLoader());
                                //设置图片集合
                                banner1.setImages(banner_list);
                                //banner设置方法全部调用完毕时最后调用
                                banner1.start();
                            }


                            if (s.getDatas().size()==0){
                                datimingci.setVisibility(View.GONE);
                            }else {
                                Store_ChanPin_Adapter store_chanPin_adapter  = new Store_ChanPin_Adapter(s.getDatas(), MyApplication.context);
                                datimingci.setAdapter(store_chanPin_adapter);
                                setListViewHeightBasedOnChildren(datimingci);
                            }



                        } else {
                            showShort(s.getMsg());
                        }
                    }
                });

    }



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
}
