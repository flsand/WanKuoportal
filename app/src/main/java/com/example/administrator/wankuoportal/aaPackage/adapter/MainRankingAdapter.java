package com.example.administrator.wankuoportal.aaPackage.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.bean.RankingBean;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.base.BaseAdapter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/2.
 *     desc    :
 * </pre>
 */
public class MainRankingAdapter extends BaseAdapter {


    public MainRankingAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_main_ranking;
    }

    @Override
    public void onBindViewHolder(int i, View view, int i1) throws Exception {

        RankingBean bean = (RankingBean) getObjcet(i1);

        CircleImageView touxiang = view.findViewById(R.id.touxiang);
        TextView wangming = view.findViewById(R.id.wangming);
        TextView jinbishu = view.findViewById(R.id.jinbishu);
        TextView mingci = view.findViewById(R.id.mingci);

        wangming.setText(bean.getNickname());
        jinbishu.setText("金币:" + bean.getGold());
        mingci.setText("第" + bean.getRanking() + "名: ");
        if (!TextUtils.isEmpty(bean.getHeadimg())) {
            ProjectUtil.loadRemoteImage(MyApplication.getInstance(), bean.getHeadimg(), touxiang);
        }

    }
}
