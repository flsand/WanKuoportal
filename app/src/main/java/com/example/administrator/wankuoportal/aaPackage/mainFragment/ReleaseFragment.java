package com.example.administrator.wankuoportal.aaPackage.mainFragment;


import android.content.Intent;

import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.NodeBean;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.faburenwu.ReleaseDemand_Activity;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.example.administrator.wankuoportal.util.Utils;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/17.
 *     desc    :
 * </pre>
 */
public class ReleaseFragment extends MerchantsFragment {

    @Override
    protected String getTitleText() {
        return "请选择发布需求分类";
    }

    @Override
    public void onSubDeatailItemClick(NodeBean nodeBean) {
        if (new UserService(MyApplication.context).getislogin().equals("0")) {
            try {
                String lable = leftBeanList.get(leftClickPosition).getLabelName() + "-" + rightBeanList.get(rightClickPosition).getLabelName() + "-" + nodeBean.getLabelName();
                Utils.print("lable = " + lable);
                Intent intent = new Intent(getActivity(), ReleaseDemand_Activity.class);
                intent.putExtra("type3", lable);
                intent.putExtra("label_id", nodeBean.getId());
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                initData();
            }

        } else {
            ProjectUtil.jumpLogin(getActivity());
        }
    }
}
