package com.example.administrator.wankuoportal.ui.FWSJ;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_fuwushangList;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_shop;
import com.example.administrator.wankuoportal.adapter.XinziAdapter;
import com.example.administrator.wankuoportal.beans.SearchFuwushang;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.Store.StoreMainActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class FWSJActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    private android.widget.TextView sxzonghe;
    private android.widget.TextView sxhaoping;
    private android.widget.TextView sxxiaoliang;
    private android.widget.TextView sxdengji;
    private android.widget.LinearLayout sxsx;
    private com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private View toumingdu;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter_fuwushangList mAdapter;
    private android.widget.ImageView back;
    private TextView title;
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;

    private View grey;
    private TextView xuanxiang1;
    private TextView xuanxiang2;
    int shoptype = 0;
    int type = 0;
    int level = 0;

    private boolean canLoadMore = true;
    private int page = 1;
    boolean isLoadServiceProviderSuccess = false;
    List<SearchFuwushang.DatasBean> serviceProviderlist = new ArrayList<>();
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fwsj);
        this.grey = (View) findViewById(R.id.grey);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        this.toumingdu = (View) findViewById(R.id.toumingdu);
        this.mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMoreRecyclerView);
        this.sxsx = (LinearLayout) findViewById(R.id.sx_sx);
        this.sxdengji = (TextView) findViewById(R.id.sx_dengji);
        this.sxxiaoliang = (TextView) findViewById(R.id.sx_xiaoliang);
        this.sxhaoping = (TextView) findViewById(R.id.sx_haoping);
        this.sxzonghe = (TextView) findViewById(R.id.sx_zonghe);
        //获取mRecyclerView对象
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        //代码设置scrollbar无效？未解决！
        mRecyclerView.setVerticalScrollBarEnabled(true);
        //设置是否可以下拉刷新
        mPullLoadMoreRecyclerView.setPullRefreshEnable(false);
        //设置是否可以上拉刷新
        mPullLoadMoreRecyclerView.setPushRefreshEnable(true);
        //显示下拉刷新
        mPullLoadMoreRecyclerView.setRefreshing(false);
        //设置上拉刷新文字
        mPullLoadMoreRecyclerView.setFooterViewText("loading");
        //设置上拉刷新文字颜色
        mPullLoadMoreRecyclerView.setFooterViewTextColor(R.color.white);
        //设置加载更多背景色
        mPullLoadMoreRecyclerView.setFooterViewBackgroundColor(R.color.colorPrimary);
        mPullLoadMoreRecyclerView.setLinearLayout();
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mAdapter = new RecyclerViewAdapter_fuwushangList(this);
        mPullLoadMoreRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerViewAdapter_shop.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                new UserService(FWSJActivity.this).setstoreposition("0");
                new UserService(FWSJActivity.this).setstoreid(mAdapter.getList().get(position).getAshopData().getId() + "");
                startActivity(StoreMainActivity.class);
            }
        });
        sxzonghe.setTextColor(getResources().getColor(R.color.colorPrimary));
        loadServiceProviderData();

        //按销量排行
        sxxiaoliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                page = 1;
                mPullLoadMoreRecyclerView.setPushRefreshEnable(true);
                sxxiaoliang.setTextColor(getResources().getColor(R.color.colorPrimary));
                mAdapter.clearData();
                if (type == 2) {
                    type = 0;
                    sxzonghe.setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    type = 2;
                }
                loadServiceProviderData();

            }
        });
        //按综合排行
        sxzonghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                page = 1;
                mPullLoadMoreRecyclerView.setPushRefreshEnable(true);
                sxzonghe.setTextColor(getResources().getColor(R.color.colorPrimary));
                mAdapter.clearData();
                type = 0;
                loadServiceProviderData();
            }
        });
        //按好评度筛选
        sxhaoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                page = 1;
                mPullLoadMoreRecyclerView.setPushRefreshEnable(true);
                sxhaoping.setTextColor(getResources().getColor(R.color.colorPrimary));
                mAdapter.clearData();
                if (type == 1) {
                    type = 0;
                    sxzonghe.setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    type = 1;
                }
                loadServiceProviderData();

            }
        });
        //按商家登记排序
        sxdengji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                page = 1;
                sxdengji.setTextColor(getResources().getColor(R.color.colorPrimary));
                mPullLoadMoreRecyclerView.setPushRefreshEnable(true);
                mAdapter.clearData();
                if (type == 3) {
                    type = 0;
                    sxzonghe.setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    type = 3;
                }
                loadServiceProviderData();

            }
        });
        //筛选点击
        sxsx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initmorepop();
            }
        });


    }

    //初始化筛选条件
    private void initview() {
        sxdengji.setTextColor(getResources().getColor(R.color.henhui));
        sxhaoping.setTextColor(getResources().getColor(R.color.henhui));
        sxxiaoliang.setTextColor(getResources().getColor(R.color.henhui));
        sxzonghe.setTextColor(getResources().getColor(R.color.henhui));
    }

    private TextView backpop;
    private ListView listView2;
    private ListView listView;
    private android.widget.RelativeLayout re;
    private TextView jingyantx;
    private LinearLayout linjingyan;
    private TextView xuelitx;
    private LinearLayout linxueli;
    private TextView queding;
    String shoptypetx = "不限";
    String leveltx = "不限";

    //更多点击现实的popwindow
    private void initmorepop() {
        View contentView = LayoutInflater.from(FWSJActivity.this).inflate(R.layout.shaixuan_more_layout, null);
        this.xuanxiang2 = (TextView) contentView.findViewById(R.id.xuanxiang2);
        this.xuanxiang1 = (TextView) contentView.findViewById(R.id.xuanxiang1);
        this.queding = (TextView) contentView.findViewById(R.id.queding);
        this.linxueli = (LinearLayout) contentView.findViewById(R.id.lin_xueli);
        this.xuelitx = (TextView) contentView.findViewById(R.id.xueli_tx);
        this.linjingyan = (LinearLayout) contentView.findViewById(R.id.lin_jingyan);
        this.jingyantx = (TextView) contentView.findViewById(R.id.jingyan_tx);
        xuanxiang1.setText("商铺等级");
        xuanxiang2.setText("商铺类型");
        xuelitx.setText(shoptypetx + "");
        jingyantx.setText(leveltx + "");
        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);//点击外部收起
        toumingdu.setBackgroundResource(R.drawable.bg_transparent);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(grey);
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = 1;
                mPullLoadMoreRecyclerView.setPushRefreshEnable(true);
                popupWindow.dismiss();
                mAdapter.clearData();
                loadServiceProviderData();
            }
        });
        linjingyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> listxinzi = new ArrayList<String>();
                listxinzi.add("不限");
                listxinzi.add("普通会员");
                listxinzi.add("白金会员");
                listxinzi.add("钻石会员");
                listxinzi.add("皇冠会员");
                listxinzi.add("尊贵会员");
                listxinzi.add("至尊会员");
                popupWindow.dismiss();
                initmoreclickpop(listxinzi, "jingyan");
            }
        });

        linxueli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> listxinzi = new ArrayList<String>();
                listxinzi.add("不限");
                listxinzi.add("个人");
                listxinzi.add("工作室");
                listxinzi.add("公司");
                popupWindow.dismiss();
                initmoreclickpop(listxinzi, "xueli");
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                initview();
                toumingdu.setBackgroundResource(R.drawable.touming);
            }
        });

    }

    //更多点击内部内容时显示的popwindow
    private void initmoreclickpop(final List<String> listxinzi, final String type) {

        View contentView = LayoutInflater.from(FWSJActivity.this).inflate(R.layout.one_list, null);
        this.backpop = (TextView) contentView.findViewById(R.id.backpop);
        this.listView = (ListView) contentView.findViewById(R.id.listView);
        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);//点击外部收起
        this.re = (RelativeLayout) contentView.findViewById(R.id.re);
        XinziAdapter x = new XinziAdapter(FWSJActivity.this, listxinzi);
        listView.setAdapter(x);
        toumingdu.setBackgroundResource(R.drawable.bg_transparent);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                initview();
                if (type.equals("xueli")) {
                    shoptypetx = listxinzi.get(position);
                    shoptype = position;
                } else {
                    leveltx = listxinzi.get(position);
                    level = position;
                }
                popupWindow.dismiss();
                initmorepop();
            }
        });
        backpop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                initmorepop();
            }
        });

        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(grey);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                initview();
                toumingdu.setBackgroundResource(R.drawable.touming);
            }
        });

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        if (canLoadMore) {
            loadServiceProviderData();
        } else {
            mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        }
    }


    public void clearData() {
        mAdapter.clearData();
        mAdapter.notifyDataSetChanged();
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
                        SearchFuwushang searchFuwushang = gson.fromJson(response, SearchFuwushang.class);
                        if (page == 1)
                            mAdapter.clearData();
                        mAdapter.addAllData(searchFuwushang.getDatas());

                        serviceProviderlist.clear();
                        page++;
                        canLoadMore = !searchFuwushang.getPageInfo().isLast();
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }

                    @Override
                    public void onHttpException() {
                        super.onHttpException();
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                });
    }
}


