package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.MineTeamBean;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.base.BaseAdapter;

import java.util.List;

/**
 * Created by FlySand on 2018/3/18.
 * 奖励
 */
public class MineTeamRewardAdapter extends BaseAdapter {

    public MineTeamRewardAdapter(Context var1, List<?> var2) {
        super(var1, var2);
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_mine_team_reward;
    }

    @Override
    public void onBindViewHolder(int i, View view, int poistion) throws Exception {
        MineTeamBean bean = (MineTeamBean) getObjcet(poistion);

        ImageView headIv = (ImageView) view.findViewById(R.id.item_mine_team_head_iv);
        TextView nimeTv = (TextView) view.findViewById(R.id.item_mine_team_name_tv);
        //推荐人
        TextView refereeTv = (TextView) view.findViewById(R.id.item_mine_team_referee_tv);
        TextView tiemTv = (TextView) view.findViewById(R.id.item_mine_team_time_tv);
        //总销售额
        TextView costTv = (TextView) view.findViewById(R.id.item_mine_team_cost_tv);
        //我的收益
        TextView profitTv = (TextView) view.findViewById(R.id.item_mine_team_profit_tv);

        TextView moneyTv = (TextView) view.findViewById(R.id.item_mine_team_money_tv);
        //
        TextView flagTv = (TextView) view.findViewById(R.id.item_mine_team_flag_tv);
        TextView levelTv = (TextView) view.findViewById(R.id.item_mine_team_level_tv);

        /**
         * headIcon :
         * flag : 1
         * accountId : 307
         * nickName :
         * manger : 普通会员
         * money : 0.00
         * isManger : 0
         */
        ProjectUtil.loadRemoteImage(context, bean.getHeadIcon(), headIv);
        flagTv.setText("2".equals(bean.getFlag()) ? "我的管理奖励" : "我的推荐奖励");
        levelTv.setText("(" + ("1".equals(bean.getIsManger()) ? "店长" : "普通会员") + ")");
        nimeTv.setText(bean.getManger());
        refereeTv.setText("推荐人：");
        moneyTv.setText(bean.getMoney());
//        profitTv.setText(bean.getProfit() + "元");
//        tiemTv.setText(bean.getCreate_time());


    }
}
