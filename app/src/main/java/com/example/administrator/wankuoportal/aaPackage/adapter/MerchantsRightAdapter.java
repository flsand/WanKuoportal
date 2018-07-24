package com.example.administrator.wankuoportal.aaPackage.adapter;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.NodeBean;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.base.BaseAdapter;
import com.flysand.mylibrary.listener.RecyclerViewItemClickListener;

import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/18.
 *     desc    :
 * </pre>
 */
public class MerchantsRightAdapter extends BaseAdapter {

    private static final int PROGRESS_VIEW = 1;
    private int clickPosition = -1;
    private int currentClickPosition = -1;

    private Fragment fragment;
    private List<NodeBean> nodesBeans;
    MerchantsSubDetailAdapter subDetailAdapter;
    onPackUpClickListener onPackUpClickListener;

    public interface onPackUpClickListener {
        void onPackUpClick(View v);
    }

    public MerchantsRightAdapter(Fragment context, List<?> list, List<NodeBean> nodesBeans) {
        super(context.getActivity(), list);
        this.fragment = context;
        this.nodesBeans = nodesBeans;
        currentClickPosition = -1;
        subDetailAdapter = new MerchantsSubDetailAdapter(fragment, nodesBeans);
    }

    public void setOnPackUpClickListener(MerchantsRightAdapter.onPackUpClickListener onPackUpClickListener) {
        this.onPackUpClickListener = onPackUpClickListener;
    }

    @Override
    public int getItemCount() {
        int count = super.getItemCount();
        return (clickPosition > 0) ? count + 1 : count;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == clickPosition) {
            return PROGRESS_VIEW;
        }
        return 0;
    }

    public void setClickPosition(int clickPosition) {
        this.clickPosition = clickPosition;
        this.currentClickPosition = clickPosition;
    }

    @Override
    public RVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == PROGRESS_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_merchants_content, parent, false);
            return new RVHolder(view, viewType);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_merchants_right, parent, false);
            return new RVHolder(view, viewType);
        }
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return 0;
    }

    @Override
    public void onBindViewHolder(int viewType, View view, int position) throws Exception {
        if (viewType == PROGRESS_VIEW) {
            RelativeLayout progressBar = view.findViewById(R.id.item_merchants_prigressbar);
            RecyclerView recyclerView = view.findViewById(R.id.item_merchants_rv);
            TextView packUpTv = view.findViewById(R.id.item_pack_up_tv);
            if (nodesBeans.size() > 1) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, 1));
            recyclerView.setAdapter(subDetailAdapter);
            packUpTv.setOnClickListener(v -> {
                if (onPackUpClickListener != null) {
                    try {
                        currentClickPosition = -1;
                        onPackUpClickListener.onPackUpClick(v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
////            //添加自定义分割线
//            DividerItemDecoration divider = new DividerItemDecoration(fragment.getContext(), DividerItemDecoration.VERTICAL);
//            divider.setDrawable(ContextCompat.getDrawable(fragment.getContext(),R.drawable.custom_divider));
//            recyclerView.addItemDecoration(divider);
        } else {
            final int positionFinal = clickPosition == -1 ? position : position > clickPosition ? position - 1 : position;
            NodeBean subBean = (NodeBean) getObjcet(positionFinal);
            if (fragment instanceof RecyclerViewItemClickListener && !TextUtils.isEmpty(subBean.getId())) {
                view.setOnClickListener(v -> {
                    if (positionFinal == currentClickPosition)
                        return;
                    try {
                        ((RecyclerViewItemClickListener) fragment).onRecyclerViewItemClick(MerchantsRightAdapter.this, view, positionFinal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    currentClickPosition = positionFinal;
                    notifyDataSetChanged();
                });
            }

            ImageView bgImg = view.findViewById(R.id.item_merchants_img);
            TextView title = view.findViewById(R.id.item_merchants_title_tv);

            if (subBean != null && !TextUtils.isEmpty(subBean.getLabelName())) {
                title.setText(subBean.getLabelName());
            }
            if (subBean != null && !TextUtils.isEmpty(subBean.getImg())) {
                ProjectUtil.loadRemoteImage(MyApplication.context,subBean.getImg(),bgImg);
            }
            if (currentClickPosition == position) {
                view.setBackgroundResource(R.color.white);
                title.setTextColor(context.getResources().getColor(R.color.text_orange));
            } else {
                title.setTextColor(context.getResources().getColor(R.color.main_text_color));
                view.setBackgroundResource(R.color.grey);
            }
        }

    }
}
