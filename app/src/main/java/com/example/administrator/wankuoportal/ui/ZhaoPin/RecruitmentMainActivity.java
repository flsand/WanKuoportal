package com.example.administrator.wankuoportal.ui.ZhaoPin;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.RecruitmentAdapter;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_zhaopin;
import com.example.administrator.wankuoportal.adapter.XinziAdapter;
import com.example.administrator.wankuoportal.beans.City;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.util.ConvertUtils;


public class RecruitmentMainActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    private android.widget.ImageView back;
    private android.widget.EditText searchshouye;


    private android.widget.TextView didiantv;
    private ImageView didianima;
    private android.widget.LinearLayout didian;
    private android.widget.TextView xinzitv;
    private ImageView xinziima;
    private android.widget.LinearLayout xinzi;
    private android.widget.TextView zhiyetv;
    private ImageView zhiyeima;
    private android.widget.LinearLayout zhiye;
    private android.widget.TextView moretv;
    private ImageView moreima;
    private android.widget.LinearLayout more;
    private LinearLayout paixu;


    private View grey;
    private String jingyan = "不限";
    private String xueli = "不限";
    private String xinzilast = "不限";
    private TextView jingyantx;
    private LinearLayout linjingyan;
    private TextView xuelitx;
    private LinearLayout linxueli;
    private TextView queding;

    private android.widget.RelativeLayout re;
    private TextView backpop;
    private ListView listView2;
    private ListView listView;
    List<String> listxinzi1;
    private ListView listView3;
    private RecyclerView mRecyclerView;
    private com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private RecyclerViewAdapter_zhaopin mRecyclerViewAdapter;
    City c;
    private View mo;
    private ImageView fabujianli;
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment_main);
        this.fabujianli = (ImageView) findViewById(R.id.fabujianli);
        this.mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMoreRecyclerView);
        this.mo = (View) findViewById(R.id.mo);

        try {
            String json = ConvertUtils.toString(this.getAssets().open("city2.json"));
            c = gson.fromJson(json, City.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.grey = (View) findViewById(R.id.grey);
        this.paixu = (LinearLayout) findViewById(R.id.paixu);
        this.more = (LinearLayout) findViewById(R.id.more);
        this.moreima = (ImageView) findViewById(R.id.more_ima);
        this.moretv = (TextView) findViewById(R.id.more_tv);
        this.zhiye = (LinearLayout) findViewById(R.id.zhiye);
        this.zhiyeima = (ImageView) findViewById(R.id.zhiye_ima);
        this.zhiyetv = (TextView) findViewById(R.id.zhiye_tv);
        this.xinzi = (LinearLayout) findViewById(R.id.xinzi);
        this.xinziima = (ImageView) findViewById(R.id.xinzi_ima);
        this.xinzitv = (TextView) findViewById(R.id.xinzi_tv);
        this.didian = (LinearLayout) findViewById(R.id.didian);
        this.didianima = (ImageView) findViewById(R.id.didian_ima);
        this.didiantv = (TextView) findViewById(R.id.didian_tv);
        this.searchshouye = (EditText) findViewById(R.id.search_shouye);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        didian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                didiantv.setTextColor(getResources().getColor(R.color.colorPrimary));
                didianima.setImageResource(R.drawable.shangla);
                initdidianpop();
            }
        });

        xinzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                xinzitv.setTextColor(getResources().getColor(R.color.colorPrimary));
                xinziima.setImageResource(R.drawable.shangla);
                initxinzipop();
            }
        });

        zhiye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                zhiyetv.setTextColor(getResources().getColor(R.color.colorPrimary));
                zhiyeima.setImageResource(R.drawable.shangla);
                initzhiyepop();
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                moretv.setTextColor(getResources().getColor(R.color.colorPrimary));
                moreima.setImageResource(R.drawable.shangla);
                initmorepop();
            }
        });
        fabujianli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ReleaseResumeActivity.class);
            }
        });

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
        mRecyclerViewAdapter = new RecyclerViewAdapter_zhaopin(getBaseContext());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter_zhaopin.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(RecruitmentDetailActivity.class);
            }
        });
        getData();
    }

    List<String> titlelist = new ArrayList<>();

    //获取数据
    private void getData() {

        titlelist.add("开店赚钱");
        titlelist.add("广告投放");
        titlelist.add("万哥跑腿");
        titlelist.add("城市合作");
        titlelist.add("开店赚钱");
        titlelist.add("广告投放");
        titlelist.add("万哥跑腿");
        titlelist.add("城市合作");
        titlelist.add("开店赚钱");
        titlelist.add("广告投放");
        titlelist.add("万哥跑腿");
        titlelist.add("城市合作");
        mRecyclerViewAdapter.addAllData(titlelist);
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();

    }

    private void initlist() {
        List<String> listxinzi = new ArrayList<String>();


        listxinzi.add("销售");
        listxinzi.add("客服");
        listxinzi.add("人事/行政");
        listxinzi.add("餐饮");
        listxinzi.add("酒店");
        listxinzi.add("旅游");
        listxinzi.add("超市/百货/零售");
        listxinzi.add("美容/美发");
        listxinzi.add("保健按摩");
        listxinzi.add("运动健身");
        RecruitmentAdapter r = new RecruitmentAdapter(listxinzi, RecruitmentMainActivity.this);

    }

    public boolean onTouchEvent(MotionEvent event) {


        if (popupWindow != null && popupWindow.isShowing()) {

            popupWindow.dismiss();

            popupWindow = null;

        }

        return super.onTouchEvent(event);

    }

    private ProgressDialog dialog;
    private List<String> province = new ArrayList<>();
    private List<String> city = new ArrayList<>();
    private List<String> area = new ArrayList<>();
    private int provincenum = -1;
    private int citynum = -1;
    private int areanum = -1;
    XinziAdapter didian0;
    XinziAdapter didian1;
    XinziAdapter didian2;
    PopupWindow popupWindow;

    //地点点击显示的popwindow
    private void initdidianpop() {


        for (int i = 0; i < c.getProvince().size(); i++) {
            province.add(c.getProvince().get(i).getAreaName());
        }
        View contentView = LayoutInflater.from(RecruitmentMainActivity.this).inflate(R.layout.three_list, null);
        this.listView3 = (ListView) contentView.findViewById(R.id.listView3);
        this.listView2 = (ListView) contentView.findViewById(R.id.listView2);
        this.listView = (ListView) contentView.findViewById(R.id.listView);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, 800);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);//点击外部收起

        didian0 = new XinziAdapter(RecruitmentMainActivity.this, province);

        listView.setAdapter(didian0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //背景选择
                didian0.setSeclection(position);
                //适配器通知数据改变
                didian0.notifyDataSetChanged();
                provincenum = position;
                city = new ArrayList<String>();
                for (int i = 0; i < c.getProvince().get(position).getCities().size(); i++) {

                    city.add(c.getProvince().get(position).getCities().get(i).getAreaName());
                }
                didian1 = new XinziAdapter(RecruitmentMainActivity.this, city);
                listView2.setAdapter(didian1);
                listView3.setVisibility(View.INVISIBLE);

            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //背景选择
                didian1.setSeclection(position);
                //适配器通知数据改变
                didian1.notifyDataSetChanged();
                citynum = position;
                area = new ArrayList<String>();
                for (int i = 0; i < c.getProvince().get(provincenum).getCities().get(citynum).getCounties().size(); i++) {

                    area.add(c.getProvince().get(provincenum).getCities().get(citynum).getCounties().get(i).getAreaName());
                }
                didian2 = new XinziAdapter(RecruitmentMainActivity.this, area);
                listView3.setAdapter(didian2);
                listView3.setVisibility(View.VISIBLE);
            }
        });

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //背景选择
                didian2.setSeclection(position);
                //适配器通知数据改变
                didian2.notifyDataSetChanged();
                areanum = position;
                popupWindow.dismiss();
            }
        });

        mo.setBackgroundResource(R.drawable.bg_transparent);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(grey);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));//popwindow 不设置背景图片点击外部不会消失
        //监听popwindow不显示时进行的动作
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {

                initview();
                mo.setBackgroundResource(R.drawable.touming);

            }
        });
    }


    XinziAdapter xinzi1;
    XinziAdapter xinzi2;

    //职业点击显示的popwindow
    private void initzhiyepop() {
        final List<String> listxinzi;

        listxinzi = new ArrayList<String>();


        listxinzi.add("销售");
        listxinzi.add("客服");
        listxinzi.add("人事/行政");
        listxinzi.add("餐饮");
        listxinzi.add("酒店");
        listxinzi.add("旅游");
        listxinzi.add("超市/百货/零售");
        listxinzi.add("美容/美发");
        listxinzi.add("保健按摩");
        listxinzi.add("运动健身");


        View contentView = LayoutInflater.from(RecruitmentMainActivity.this).inflate(R.layout.two_list, null);
        this.listView2 = (ListView) contentView.findViewById(R.id.listView2);
        this.listView = (ListView) contentView.findViewById(R.id.listView);
         popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);//点击外部收起
        xinzi1 = new XinziAdapter(RecruitmentMainActivity.this, listxinzi);
        listView.setAdapter(xinzi1);
        mo.setBackgroundResource(R.drawable.bg_transparent);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //背景选择
                xinzi1.setSeclection(position);
                //适配器通知数据改变
                xinzi1.notifyDataSetChanged();
                listxinzi1 = new ArrayList<String>();
                listxinzi1.add("销售" + position);
                listxinzi1.add("客服");
                listxinzi1.add("人事/行政");
                listxinzi1.add("餐饮");
                listxinzi1.add("酒店");
                listxinzi1.add("旅游");
                listxinzi1.add("超市/百货/零售");
                listxinzi1.add("美容/美发");
                listxinzi1.add("保健按摩");
                listxinzi1.add("运动健身");
                xinzi2 = new XinziAdapter(RecruitmentMainActivity.this, listxinzi1);
                listView2.setAdapter(xinzi2);
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initview();
                zhiyetv.setText(listxinzi1.get(position));
                popupWindow.dismiss();
            }
        });

        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(grey);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));//popwindow 不设置背景图片点击外部不会消失
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {

                initview();
                mo.setBackgroundResource(R.drawable.touming);

            }
        });

    }

    //薪资点击现实的popwindow
    private void initxinzipop() {
        final List<String> listxinzi;
        listxinzi = new ArrayList<String>();

        listxinzi.add("不限");
        listxinzi.add("1000以下");
        listxinzi.add("1000-2000元");
        listxinzi.add("2000-3000元");
        listxinzi.add("3000-5000元");
        listxinzi.add("5000-8000元");
        listxinzi.add("8000-12000元");
        listxinzi.add("12000-20000元");
        listxinzi.add("20000-25000元");
        listxinzi.add("25000元以上");

        View contentView = LayoutInflater.from(RecruitmentMainActivity.this).inflate(R.layout.one_list, null);
        this.re = (RelativeLayout) contentView.findViewById(R.id.re);
        re.setVisibility(View.GONE);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);//点击外部收起
        ListView listView = (ListView) contentView.findViewById(R.id.listView);
        XinziAdapter x = new XinziAdapter(RecruitmentMainActivity.this, listxinzi);
        listView.setAdapter(x);
        mo.setBackgroundResource(R.drawable.bg_transparent);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initview();
                xinzilast = listxinzi.get(position);
                xinzitv.setText(listxinzi.get(position));
                popupWindow.dismiss();
            }
        });

        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(grey);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));//popwindow 不设置背景图片点击外部不会消失
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                initview();
                mo.setBackgroundResource(R.drawable.touming);

            }
        });

    }

    //更多点击内部内容时显示的popwindow
    private void initmoreclickpop(final List<String> listxinzi, final String type) {

        View contentView = LayoutInflater.from(RecruitmentMainActivity.this).inflate(R.layout.one_list, null);
        this.backpop = (TextView) contentView.findViewById(R.id.backpop);
        this.listView = (ListView) contentView.findViewById(R.id.listView);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);//点击外部收起
        this.re = (RelativeLayout) contentView.findViewById(R.id.re);
        XinziAdapter x = new XinziAdapter(RecruitmentMainActivity.this, listxinzi);
        listView.setAdapter(x);
        mo.setBackgroundResource(R.drawable.bg_transparent);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initview();
                if (type.equals("xueli")) {
                    xueli = listxinzi.get(position);
                } else {
                    jingyan = listxinzi.get(position);
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

                initview();
                mo.setBackgroundResource(R.drawable.touming);

            }
        });

    }

    //更多点击现实的popwindow
    private void initmorepop() {


        View contentView = LayoutInflater.from(RecruitmentMainActivity.this).inflate(R.layout.shaixuan_more_layout, null);
        this.queding = (TextView) contentView.findViewById(R.id.queding);
        this.linxueli = (LinearLayout) contentView.findViewById(R.id.lin_xueli);
        this.xuelitx = (TextView) contentView.findViewById(R.id.xueli_tx);
        this.linjingyan = (LinearLayout) contentView.findViewById(R.id.lin_jingyan);
        this.jingyantx = (TextView) contentView.findViewById(R.id.jingyan_tx);
        xuelitx.setText(xueli);
        jingyantx.setText(jingyan);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);//点击外部收起
        mo.setBackgroundResource(R.drawable.bg_transparent);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(grey);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));//popwindow 不设置背景图片点击外部不会消失
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        linjingyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> listxinzi = new ArrayList<String>();
                listxinzi.add("不限");
                listxinzi.add("1年以下");
                listxinzi.add("1-2年");
                listxinzi.add("3-5年");
                listxinzi.add("6-7年");
                listxinzi.add("8-10年");
                listxinzi.add("10年以上");
                popupWindow.dismiss();
                initmoreclickpop(listxinzi, "jingyan");
            }
        });

        linxueli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> listxinzi = new ArrayList<String>();
                listxinzi.add("初中");
                listxinzi.add("高中");
                listxinzi.add("专科");
                listxinzi.add("本科");
                listxinzi.add("硕士");
                listxinzi.add("博士");
                listxinzi.add("博士后");
                listxinzi.add("女博士后");
                popupWindow.dismiss();
                initmoreclickpop(listxinzi, "xueli");
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                initview();
                mo.setBackgroundResource(R.drawable.touming);
            }
        });

    }

    //初始化各控件
    private void initview() {
        didiantv.setTextColor(getResources().getColor(R.color.all_textc));
        moretv.setTextColor(getResources().getColor(R.color.all_textc));
        xinzitv.setTextColor(getResources().getColor(R.color.all_textc));
        zhiyetv.setTextColor(getResources().getColor(R.color.all_textc));
        didianima.setImageResource(R.drawable.sanjiao);
        moreima.setImageResource(R.drawable.sanjiao);
        xinziima.setImageResource(R.drawable.sanjiao);
        zhiyeima.setImageResource(R.drawable.sanjiao);
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        getData();
    }
}
