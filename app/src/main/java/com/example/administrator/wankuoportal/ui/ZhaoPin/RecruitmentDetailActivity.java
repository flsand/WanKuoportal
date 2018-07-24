package com.example.administrator.wankuoportal.ui.ZhaoPin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.ViewHolder;
import com.example.administrator.wankuoportal.adapter.RecruitmentAdapter;
import com.example.administrator.wankuoportal.global.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class RecruitmentDetailActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.ListView list;
    private android.widget.TextView shenqing;
    private android.widget.LinearLayout call;
    private LinearLayout more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment);
        this.more = (LinearLayout) findViewById(R.id.more);
        this.call = (LinearLayout) findViewById(R.id.call);
        this.shenqing = (TextView) findViewById(R.id.shenqing);
        this.list = (ListView) findViewById(R.id.list);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        List<String> listxinzi = new ArrayList<String>();


        listxinzi.add("销售");
        listxinzi.add("客服");
        listxinzi.add("人事/行政");
        RecruitmentAdapter r = new RecruitmentAdapter(listxinzi, RecruitmentDetailActivity.this);
        list.setAdapter(r);
        setListViewHeightBasedOnChildren(list);

        //电话资讯点击
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDialog.newInstance("1")
                        .setMargin(60)
                        .setOutCancel(false)
                        .show(getSupportFragmentManager());
            }
        });
        //简历更多点击
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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



    public static class ConfirmDialog extends BaseNiceDialog {


        public static ConfirmDialog newInstance(String type) {
            Bundle bundle = new Bundle();
            bundle.putString("type", type);
            ConfirmDialog dialog = new ConfirmDialog();
            dialog.setArguments(bundle);
            return dialog;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle bundle = getArguments();
            if (bundle == null) {
                return;
            }
        }

        @Override
        public int intLayoutId() {
            return R.layout.confirm_layout;
        }

        @Override
        public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {

            holder.setText(R.id.message, "0458-25684669");

            holder.setOnClickListener(R.id.cancel, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            holder.setOnClickListener(R.id.ok, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * 调用拨号界面
                     *
                     * @param phone 电话号码
                     */
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0458-25684669"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });
        }
    }
}
