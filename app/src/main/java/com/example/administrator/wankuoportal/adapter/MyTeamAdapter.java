package com.example.administrator.wankuoportal.adapter;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.MyTeamListAllBean;
import com.example.administrator.wankuoportal.beans.MyTeamListGLJLBean;
import com.example.administrator.wankuoportal.beans.MyTeamListGLSYBean;
import com.example.administrator.wankuoportal.beans.MyTeamListTJJLBean;
import com.example.administrator.wankuoportal.beans.MyTeamListTJSYBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.ui.MyTeamActivity;
import com.example.administrator.wankuoportal.util.CommonUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class MyTeamAdapter extends RecyclerView.Adapter<MyTeamAdapter.ViewHolder> {

    private List<MyTeamListAllBean.DataBean> allBeanList = new ArrayList<>();
    private List<MyTeamListTJJLBean.DataBean> tjjlBeanList = new ArrayList<>();
    private List<MyTeamListGLJLBean.DataBean> gljlBeanList = new ArrayList<>();
    private List<MyTeamListTJSYBean.DataBean> tjsyBeanList = new ArrayList<>();
    private List<MyTeamListGLSYBean.DataBean> glsyBeanList = new ArrayList<>();

    private MyTeamActivity mActivity;

    private int mType = MyTeamActivity.PAGE_ALL;

    /**
     * 注意数据是通过set****BeanList传入，因此构造后需要继续调用对应set方法
     */
    public MyTeamAdapter(MyTeamActivity activity) {
        mActivity = activity;
    }

    public void setAllBeanList(List<MyTeamListAllBean.DataBean> list) {
        allBeanList = list;
        mType = MyTeamActivity.PAGE_ALL;
    }

    public void setTJJLBeanList(List<MyTeamListTJJLBean.DataBean> list) {
        tjjlBeanList = list;
        mType = MyTeamActivity.PAGE_TJJL;
    }

    public void setGLJLBeanList(List<MyTeamListGLJLBean.DataBean> list) {
        gljlBeanList = list;
        mType = MyTeamActivity.PAGE_GLJL;
    }

    public void setTJSYBeanList(List<MyTeamListTJSYBean.DataBean> list) {
        tjsyBeanList = list;
        mType = MyTeamActivity.PAGE_TJSY;
    }

    public void setGLSYBeanList(List<MyTeamListGLSYBean.DataBean> list) {
        glsyBeanList = list;
        mType = MyTeamActivity.PAGE_GLSY;
    }

    @Override
    public MyTeamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.my_team_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try{
            String name = "";
            switch (mType) {
                case MyTeamActivity.PAGE_ALL:
                    MyTeamListAllBean.DataBean all = allBeanList.get(position);
                    if (all.getHeadIcon().equals("")) {
//                    app:srcCompat

                        Drawable drawable = AppCompatDrawableManager.get().getDrawable(mActivity, R.drawable.my_team_ic_default_head);
//                            mActivity.getResources().getDrawable(R.drawable.my_team_ic_default_head);
                        holder.my_team_item_round_iv.setImageDrawable(drawable);
                    } else {
                        Glide.with(mActivity).load(Apis.Baseima + all.getHeadIcon()).into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                holder.my_team_item_round_iv.setImageDrawable(resource);
                            }
                        });
                    }

                    holder.my_team_item_root_jiangli.setVisibility(View.VISIBLE);
                    holder.my_team_item_root_shouyi.setVisibility(View.GONE);

                    if (all.getFlag().equals("1")) {
                        holder.my_team_item_tv_jiangli_title.setText("我的推荐奖励");
                    } else {
                        holder.my_team_item_tv_jiangli_title.setText("我的管理奖励");
                    }

                    name = all.getNickName() + "(" + all.getManger() + ")";
                    SpannableStringBuilder allNameSpanBuilder = CommonUtil.setSpannableString(mActivity.getResources().getColor(R.color.theme_color)
                            , name, all.getNickName().length(), name.length());
                    holder.my_team_item_tv_huiyuanmingcheng.setText(allNameSpanBuilder);

                    String jl = all.getMoney() + "元";
                    SpannableStringBuilder jlSpanBuilder = CommonUtil.setSpannableString(mActivity.getResources().getColor(R.color.theme_color)
                            , jl, 0, jl.length() - 1);
                    holder.my_team_item_tv_jiangli_content.setText(jlSpanBuilder);
                    break;
                case MyTeamActivity.PAGE_TJJL:
                    MyTeamListTJJLBean.DataBean tjjl = tjjlBeanList.get(position);
                    if (tjjl.getHeadIcon().equals("")) {
                        Drawable drawable = mActivity.getResources().getDrawable(R.drawable.my_team_ic_default_head);
                        holder.my_team_item_round_iv.setImageDrawable(drawable);
                    } else {
                        Glide.with(mActivity).load(Apis.Baseima + tjjl.getHeadIcon()).into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                holder.my_team_item_round_iv.setImageDrawable(resource);
                            }
                        });
                    }

                    holder.my_team_item_root_jiangli.setVisibility(View.VISIBLE);
                    holder.my_team_item_root_shouyi.setVisibility(View.GONE);

                    if (tjjl.getFlag().equals("1")) {
                        holder.my_team_item_tv_jiangli_title.setText("我的推荐奖励");
                    } else {
                        holder.my_team_item_tv_jiangli_title.setText("我的管理奖励");
                    }

                    name = tjjl.getNickName() + "(" + tjjl.getManger() + ")";
                    SpannableStringBuilder tjjlNameSpanBuilder = CommonUtil.setSpannableString(mActivity.getResources().getColor(R.color.theme_color)
                            , name, tjjl.getNickName().length(), name.length());
                    holder.my_team_item_tv_huiyuanmingcheng.setText(tjjlNameSpanBuilder);

                    String tjjlMoney = tjjl.getMoney() + "元";
                    SpannableStringBuilder tjjlMoneySpanBuilder = CommonUtil.setSpannableString(mActivity.getResources().getColor(R.color.theme_color)
                            , tjjlMoney, 0, tjjlMoney.length() - 1);
                    holder.my_team_item_tv_jiangli_content.setText(tjjlMoneySpanBuilder);
                    break;
                case MyTeamActivity.PAGE_GLJL:
                    MyTeamListGLJLBean.DataBean gljl = gljlBeanList.get(position);
                    if (gljl.getHeadIcon().equals("")) {
                        Drawable drawable = mActivity.getResources().getDrawable(R.drawable.my_team_ic_default_head);
                        holder.my_team_item_round_iv.setImageDrawable(drawable);
                    } else {
                        Glide.with(mActivity).load(Apis.Baseima + gljl.getHeadIcon()).into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                holder.my_team_item_round_iv.setImageDrawable(resource);
                            }
                        });
                    }

                    holder.my_team_item_root_jiangli.setVisibility(View.VISIBLE);
                    holder.my_team_item_root_shouyi.setVisibility(View.GONE);

                    if (gljl.getFlag().equals("1")) {
                        holder.my_team_item_tv_jiangli_title.setText("我的推荐奖励");
                    } else {
                        holder.my_team_item_tv_jiangli_title.setText("我的管理奖励");
                    }

                    name = gljl.getNickName() + "(" + gljl.getManger() + ")";
                    SpannableStringBuilder gljlNameSpanBuilder = CommonUtil.setSpannableString(mActivity.getResources().getColor(R.color.theme_color)
                            , name, gljl.getNickName().length(), name.length());
                    holder.my_team_item_tv_huiyuanmingcheng.setText(gljlNameSpanBuilder);

                    String gljlMoney = gljl.getMoney() + "元";
                    SpannableStringBuilder gljlMoneySpanBuilder = CommonUtil.setSpannableString(mActivity.getResources().getColor(R.color.theme_color)
                            , gljlMoney, 0, gljlMoney.length() - 1);
                    holder.my_team_item_tv_jiangli_content.setText(gljlMoneySpanBuilder);
                    break;
                case MyTeamActivity.PAGE_TJSY:
                    MyTeamListTJSYBean.DataBean tjsy = tjsyBeanList.get(position);
                    if (tjsy.getHeadIcon().equals("")) {
                        Drawable drawable = mActivity.getResources().getDrawable(R.drawable.my_team_ic_default_head);
                        holder.my_team_item_round_iv.setImageDrawable(drawable);
                    } else {
                        Glide.with(mActivity).load(Apis.Baseima + tjsy.getHeadIcon()).into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                holder.my_team_item_round_iv.setImageDrawable(resource);
                            }
                        });
                    }

                    holder.my_team_item_root_jiangli.setVisibility(View.VISIBLE);
                    holder.my_team_item_root_shouyi.setVisibility(View.GONE);

                    holder.my_team_item_tv_jiangli_title.setText("我的推荐收益");

                    name = tjsy.getNickName() + "(" + tjsy.getManger() + ")";
                    SpannableStringBuilder tjsyNameSpanBuilder = CommonUtil.setSpannableString(mActivity.getResources().getColor(R.color.theme_color)
                            , name, tjsy.getNickName().length(), name.length());
                    holder.my_team_item_tv_huiyuanmingcheng.setText(tjsyNameSpanBuilder);

                    String tjsyMoney = tjsy.getMoney() + "元";
                    SpannableStringBuilder tjsyMoneySpanBuilder = CommonUtil.setSpannableString(mActivity.getResources().getColor(R.color.theme_color)
                            , tjsyMoney, 0, tjsyMoney.length() - 1);
                    holder.my_team_item_tv_jiangli_content.setText(tjsyMoneySpanBuilder);
                    break;
                case MyTeamActivity.PAGE_GLSY:
                    MyTeamListGLSYBean.DataBean glsy = glsyBeanList.get(position);
                    if (glsy.getHeadIcon().equals("")) {
                        Drawable drawable = mActivity.getResources().getDrawable(R.drawable.my_team_ic_default_head);
                        holder.my_team_item_round_iv.setImageDrawable(drawable);
                    } else {
                        Glide.with(mActivity).load(Apis.Baseima + glsy.getHeadIcon()).into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                holder.my_team_item_round_iv.setImageDrawable(resource);
                            }
                        });
                    }

                    holder.my_team_item_root_jiangli.setVisibility(View.VISIBLE);
                    holder.my_team_item_root_shouyi.setVisibility(View.GONE);

                    holder.my_team_item_tv_jiangli_title.setText("我的管理收益");

                    name = glsy.getNickName() + "(" + glsy.getManger() + ")";
                    SpannableStringBuilder gnsyNameSpanBuilder = CommonUtil.setSpannableString(mActivity.getResources().getColor(R.color.theme_color)
                            , name, glsy.getNickName().length(), name.length());
                    holder.my_team_item_tv_huiyuanmingcheng.setText(gnsyNameSpanBuilder);

                    String glsyMoney = glsy.getMoney() + "元";
                    SpannableStringBuilder glsyMoneySpanBuilder = CommonUtil.setSpannableString(mActivity.getResources().getColor(R.color.theme_color)
                            , glsyMoney, 0, glsyMoney.length() - 1);
                    holder.my_team_item_tv_jiangli_content.setText(glsyMoneySpanBuilder);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        switch (mType) {
            case MyTeamActivity.PAGE_ALL:
                return allBeanList.isEmpty() ? 0 : allBeanList.size();
            case MyTeamActivity.PAGE_TJJL:
                return tjjlBeanList.isEmpty() ? 0 : tjjlBeanList.size();
            case MyTeamActivity.PAGE_GLJL:
                return gljlBeanList.isEmpty() ? 0 : gljlBeanList.size();
            case MyTeamActivity.PAGE_TJSY:
                return tjsyBeanList.isEmpty() ? 0 : tjsyBeanList.size();
            case MyTeamActivity.PAGE_GLSY:
                return glsyBeanList.isEmpty() ? 0 : glsyBeanList.size();
            default:
                return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView my_team_item_round_iv;

        TextView my_team_item_tv_huiyuanmingcheng;

        TextView my_team_item_tv_jiangli_title;

        TextView my_team_item_tv_jiangli_content;

        TextView my_team_item_tv_zongxiaoshoue;

        TextView my_team_item_tv_wodeshouru;

        View my_team_item_root_jiangli;

        View my_team_item_root_shouyi;

        public ViewHolder(View itemView) {
            super(itemView);
            my_team_item_round_iv = (RoundedImageView) itemView.findViewById(R.id.my_team_item_round_iv);
            my_team_item_tv_jiangli_title = (TextView) itemView.findViewById(R.id.my_team_item_tv_jiangli_title);
            my_team_item_root_jiangli = itemView.findViewById(R.id.my_team_item_root_jiangli);
            my_team_item_root_shouyi = itemView.findViewById(R.id.my_team_item_root_shouyi);
            my_team_item_tv_jiangli_content = (TextView) itemView.findViewById(R.id.my_team_item_tv_jiangli_content);
            my_team_item_tv_zongxiaoshoue = (TextView) itemView.findViewById(R.id.my_team_item_tv_zongxiaoshoue);
            my_team_item_tv_wodeshouru = (TextView) itemView.findViewById(R.id.my_team_item_tv_wodeshouru);
            my_team_item_tv_huiyuanmingcheng = (TextView) itemView.findViewById(R.id.my_team_item_tv_huiyuanmingcheng);
        }
    }
}
