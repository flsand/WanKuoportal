package com.example.administrator.wankuoportal.ui.RenCai;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_rencai;
import com.example.administrator.wankuoportal.adapter.XinziAdapter;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.ui.ZhaoPin.ReleaseResumeActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.view.KeyEvent.KEYCODE_BACK;

public class RenCaiMainActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.TextView didiantv;
    private android.widget.ImageView didianima;
    private android.widget.LinearLayout didian;
    private android.widget.TextView xinzitv;
    private android.widget.ImageView xinziima;
    private android.widget.LinearLayout xinzi;
    private android.widget.TextView zhiyetv;
    private android.widget.ImageView zhiyeima;
    private android.widget.LinearLayout zhiye;
    private android.widget.TextView moretv;
    private android.widget.ImageView moreima;
    private android.widget.LinearLayout more;
    private android.widget.LinearLayout paixu;
    private RecyclerView mRecyclerView;
    private com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private RecyclerViewAdapter_rencai mRecyclerViewAdapter;
    List<String> titlelist = new ArrayList<>();
    private View mo;
    XinziAdapter xinzi1;
    XinziAdapter xinzi2;
    private ListView listView2;
    private ListView listView;
    List<String> listxinzi1;
    private String jingyan = "不限";
    private String xueli = "不限";
    private String xinzilast = "不限";
    private android.widget.RelativeLayout re;
    private View grey;
    private ImageView fabujianli;
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ren_cai_main);
        this.fabujianli = (ImageView) findViewById(R.id.fabujianli);
        this.grey = (View) findViewById(R.id.grey);
        this.mo = (View) findViewById(R.id.mo);
        this.mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMoreRecyclerView);
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
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        mRecyclerViewAdapter = new RecyclerViewAdapter_rencai(getBaseContext());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter_rencai.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(WanKuoRenCaiActivity.class);
            }
        });

        getData();
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
        fabujianli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ReleaseResumeActivity.class);
            }
        });
    }


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


        View contentView = LayoutInflater.from(RenCaiMainActivity.this).inflate(R.layout.two_list, null);
        this.listView2 = (ListView) contentView.findViewById(R.id.listView2);
        this.listView = (ListView) contentView.findViewById(R.id.listView);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);

        xinzi1 = new XinziAdapter(RenCaiMainActivity.this, listxinzi);
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
                xinzi2 = new XinziAdapter(RenCaiMainActivity.this, listxinzi1);
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
        popupWindow.setOutsideTouchable(true);//点击外部收起
        popupWindow.showAsDropDown(grey);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));//popwindow 不设置背景图片点击外部不会消失

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                popupWindow.dismiss();
            }
        });

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
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


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        getData();
    }

    //清空数据
    public void clearData() {
        mRecyclerViewAdapter.clearData();
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    PopupWindow popupWindow;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_BACK) {
            if (popupWindow != null) {
                popupWindow.dismiss();
                popupWindow = null;
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);

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

        View contentView = LayoutInflater.from(RenCaiMainActivity.this).inflate(R.layout.one_list, null);
        this.re = (RelativeLayout) contentView.findViewById(R.id.re);
        re.setVisibility(View.GONE);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);//点击外部收起
        ListView listView = (ListView) contentView.findViewById(R.id.listView);
        XinziAdapter x = new XinziAdapter(RenCaiMainActivity.this, listxinzi);
        listView.setAdapter(x);
        mo.setBackgroundResource(R.drawable.bg_transparent);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initview();
                xinzilast = listxinzi.get(position);
                xinzitv.setText(listxinzi.get(position));
                popupWindow.dismiss();
                popupWindow = null;
            }
        });

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
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


    public boolean onTouchEvent(MotionEvent event) {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
        return super.onTouchEvent(event);
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
}
