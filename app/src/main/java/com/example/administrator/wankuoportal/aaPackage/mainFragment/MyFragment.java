package com.example.administrator.wankuoportal.aaPackage.mainFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.activity.gold.TransactionSalaActivity;
import com.example.administrator.wankuoportal.aaPackage.activity.my.ServiceActivity;
import com.example.administrator.wankuoportal.aaPackage.bean.UserBean;
import com.example.administrator.wankuoportal.aaPackage.coomon.Define;
import com.example.administrator.wankuoportal.aaPackage.coustom.ObservableScrollView;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.base.MyBaseFragment;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.CityHeHuo.CityPartnerActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.ErWeiMaActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MemberActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MemberTopHintActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyDaTiActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyInfoActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyJinBiActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyZhanghuActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.QianDaoJiLuActivity;
import com.example.administrator.wankuoportal.ui.MyTeamActivity;
import com.example.administrator.wankuoportal.ui.SetUp.AboutUsActivity;
import com.example.administrator.wankuoportal.ui.SetUp.HelpActivity;
import com.example.administrator.wankuoportal.ui.SetUp.InvitationActivity;
import com.example.administrator.wankuoportal.ui.SetUp.SetUpActivity;
import com.example.administrator.wankuoportal.ui.denglu.Personal_SetupActivity;
import com.example.administrator.wankuoportal.ui.shop.ShopActivity;
import com.example.administrator.wankuoportal.util.ClickHelper;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.example.administrator.wankuoportal.util.Utils;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.util.JSONUtil;
import com.jauker.widget.BadgeView;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/17.
 *     desc    : 我的
 * </pre>
 */
public class MyFragment extends MyBaseFragment {


    @BindView(R.id.fragment_title_alpha_layout)
    View titleLayout;

    @BindView(R.id.fragment_my_layout)
    View myLayout;

    @BindView(R.id.fragment_title_tv)
    View titleTv;

    @BindView(R.id.portrait_img_1)
    ImageView portrait1;

    @BindView(R.id.portrait_img_2)
    ImageView portrait2;

    @BindView(R.id.signIn_img)
    ImageView signInImg;

    @BindView(R.id.fragment_my_infomation_img)
    ImageView infomationImg;

    @BindView(R.id.fragment_my_scrollview)
    ObservableScrollView mScrollview;
    //赠送购物金币
    @BindView(R.id.my_gold_give_tv)
    TextView goldGiveTv;
    //身份"
    @BindView(R.id.identity_tv)
    TextView identityTv;
    //昵称
    @BindView(R.id.nickname_tv)
    TextView nicknameTv;
    //昵称
    @BindView(R.id.member_submit_tv)
    TextView memberSubmitTv;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my, container, false);//关联布局文件
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {

        mScrollview.setScrollViewListener((scrollView, x, y, oldx, oldy) -> {
            float height = myLayout.getBottom() - titleLayout.getHeight();
            if (y > height) {
                titleLayout.setAlpha(1);
                if ((y - height) < height) {
                    portrait1.setAlpha((y - height) / height);
                    titleTv.setAlpha(y / height / 2);
                } else {
                    portrait1.setAlpha(1f);
                    titleTv.setAlpha(1f);
                }
            } else {
                float alpha = ((float) y) / height;
                titleLayout.setAlpha(alpha);
                portrait1.setAlpha(0f);
                titleTv.setAlpha(alpha / 2);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        checksignin();
    }

    @Override
    public void onLoginOutTime(String type) {
        new UserService(getActivity()).setislogin("1");
        new UserService(getActivity()).settoken("0");
        memberSubmitTv.setText("登录");
    }

    private void checksignin() {
        httpGetNoDialog("checksignin", new RequestParams(), Apis.checksignin);
    }

    public void switchPage(int type) {
        switch (type) {
            case Define.MainPages.MY_EMPLOYERS:  //雇主
                Utils.print("切换 雇主");
                break;
            case Define.MainPages.MY_MEMBERS:    //会员
                Utils.print("切换 会员");
                break;
        }
    }

    public void showInfomationCount(int count) {
        BadgeView badge = new BadgeView(getActivity());
        badge.setBackground(com.flysand.mylibrary.util.Utils.dip2px(getActivity(), 2), getResources().getColor(R.color.red_shape));
        badge.setTargetView(infomationImg);
        badge.setBadgeCount(count);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.portrait_img_2, R.id.fragment_my_infomation_img, R.id.my_assets_layout, R.id.my_wallet_layout, R.id.my_service_layout,
            R.id.my_gold_mall_layout, R.id.my_order_layout, R.id.transaction_layout, R.id.member_submit_tv, R.id.signIn_img, R.id.my_team_layout, R.id.my_invitation_layout, R.id.my_about_us_layout, R.id.my_store_layout, R.id.my_answer_layout, R.id.my_sign_in_layout, R.id.my_qrcode_tv, R.id.my_help_layout, R.id.my_contact_tv, R.id.my_settings_layout})
    public void onViewClicked(View view) {
        if (ClickHelper.isHandle())
            return;
        switch (view.getId()) {
            case R.id.my_about_us_layout://关于万阔
                startActivity(AboutUsActivity.class);
                return;
            case R.id.my_help_layout://帮助中心
                startActivity(HelpActivity.class);
                return;
            case R.id.my_contact_tv://联系我们
                showContact();
                return;
            case R.id.my_gold_mall_layout://金币商城
                startActivity(ShopActivity.class);
                return;
        }
        if (ProjectUtil.isLogin())
            return;
        switch (view.getId()) {
            case R.id.signIn_img://签到
                signIn();
                break;
            case R.id.member_submit_tv://升级为店长
                memberSubmit();
                break;
            case R.id.fragment_my_infomation_img://消息
                startActivity(MyInfoActivity.class);
                break;
            case R.id.portrait_img_2://头像
                startActivity(Personal_SetupActivity.class);
                break;
            case R.id.my_assets_layout://我的资产
                startActivity(MyJinBiActivity.class);
                break;
            case R.id.my_wallet_layout://我的钱包
                startActivity(MyZhanghuActivity.class);
                break;
            case R.id.transaction_layout://交易大厅
                startActivity(TransactionSalaActivity.class);
                break;
            case R.id.my_service_layout://雇主服务
                startActivity(ServiceActivity.class);
                break;
            case R.id.my_order_layout://我的订单
                ProjectUtil.showDevelopmentMessage();
                break;
            case R.id.my_store_layout://我的万店
                ProjectUtil.showDevelopmentMessage();
//                SmallStoreActivity_.intent(this).start();
                break;
            case R.id.my_answer_layout://我的答题
                startActivity(MyDaTiActivity.class);
                break;
            case R.id.my_team_layout://我的团队
                startActivity(MyTeamActivity.class);
                break;
            case R.id.my_sign_in_layout://我的签到
                startActivity(QianDaoJiLuActivity.class);
                break;
            case R.id.my_invitation_layout://邀请好友
                startActivity(InvitationActivity.class);
                break;
            case R.id.my_qrcode_tv://分享二维码
                startActivity(ErWeiMaActivity.class);
                break;
            case R.id.my_settings_layout://设置
                startActivity(SetUpActivity.class);
                break;
        }
    }

    private void signIn() {
        httpGet("signIn", new RequestParams(), Apis.signin);
    }

    @Override
    public void onHttpFailure(String type, String s1) throws Exception {
        if ("checksignin".equals(type)) {
            dismissHttpDialog();
            return;
        }
        super.onHttpFailure(type, s1);
    }

    /**
     * 升级为店长点击
     */
    private void memberSubmit() {
        httpGet("checkarea", new RequestParams(), Apis.checkarea);
    }

    @Override
    public boolean onHttpAnalysisIntercept(String type, JSONObject var2) throws Exception {
        if ("checkarea".equals(type)) {//升级店长
            dismissHttpDialog();
            if ("0".equals(var2.getString("code"))) {
                if (new UserService(MyApplication.context).getIsOrdinaryMember() == 105) {
                    startActivity(MemberTopHintActivity.class);
                } else {
                    startActivity(MemberActivity.class);
                }
            } else if ("1".equals(var2.getString("code"))) {
                toast.setText(var2.getString("msg"));
                startActivity(Personal_SetupActivity.class);
            } else {
                toast.setText(var2.getString("msg"));
            }
            return true;
        } else if ("checksignin".equals(type)) {//检查签到

            UserBean bean = JSONUtil.toJavaBean(UserBean.class, var2.getString("data"));

            new UserService(getActivity()).setislogin("0");
            //签到图标
            if ("0".equals(var2.getString("code"))) {
                signInImg.setVisibility(View.VISIBLE);
            } else if ("1".equals(var2.getString("code"))) {
                signInImg.setVisibility(View.INVISIBLE);
            }
            String ordinaryMember = Constant.getMemberGradeInfo(bean.getAAccountAuthorize().getIsOrdinaryMember());
            //保存店长等级信息
            new UserService(MyApplication.context).setIsOrdinaryMember(bean.getAAccountAuthorize().getIsOrdinaryMember());
//            设置用户店长等级
            identityTv.setText(ordinaryMember);
            //设置昵称
            if (TextUtils.isEmpty(bean.getAAccountInfo().getNickname())) {
                nicknameTv.setText("游客");
            } else {
                nicknameTv.setText(bean.getAAccountInfo().getNickname());
            }
            //设置头像
            if (bean.getAAccountInfo() != null) {
                if (TextUtils.isEmpty(bean.getAAccountInfo().getHeadIcon())) {
                    ProjectUtil.loadLocalhostImage(MyApplication.getInstance(), R.drawable.tou, portrait1);
                    ProjectUtil.loadLocalhostImage(MyApplication.getInstance(), R.drawable.tou, portrait2);
                } else {
                    String url = bean.getAAccountInfo().getHeadIcon();
                    Utils.print("设置头像: " + Apis.Baseima + url);
                    ProjectUtil.loadRemoteImage(MyApplication.getInstance(), url, portrait1);
                    ProjectUtil.loadRemoteImage(MyApplication.getInstance(), url, portrait2);
                }
            }
            if ("无".equals(ordinaryMember) || "普通会员".equals(ordinaryMember)) {
                memberSubmitTv.setText("升级为店长");
            } else if ("至尊店长".equals(ordinaryMember)) {
                memberSubmitTv.setText("升级为合伙人");
                //升级为店长点击
                memberSubmitTv.setOnClickListener(v ->
                        startActivity(new Intent(getActivity(), CityPartnerActivity.class))
                );
            } else {
                memberSubmitTv.setText("升级店长");
            }
            //设置购物金币数量
            goldGiveTv.setText("赠送购物金币" + bean.getAAccountAuthorize().getShoppingMoney());

            MyApplication.getInstance().setUserBean(bean);
            return true;
        }
        return super.onHttpAnalysisIntercept(type, var2);
    }

    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) throws Exception {
        super.onHttpSuccess(type, jsonObject);
        if ("signIn".equals(type)) {
            toast.setText("签到成功");
            signInImg.setVisibility(View.INVISIBLE);
        }
    }

    private void showContact() {
        // 创建对话框构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 获取布局
        View view2 = View.inflate(getActivity(), R.layout.phone_choose, null);
        LinearLayout phone1 = view2.findViewById(R.id.phone1);
        LinearLayout phone2 = view2.findViewById(R.id.phone2);
        builder.setView(view2);
// 创建对话框
        final AlertDialog alertDialog = builder.create();
        phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0535-2166749"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                alertDialog.dismiss();// 对话框消失
            }
        });
        phone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getActivity().getResources().getString(R.string.tellPhone)));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                alertDialog.dismiss();// 对话框消失
            }
        });
        alertDialog.show();
    }
}
