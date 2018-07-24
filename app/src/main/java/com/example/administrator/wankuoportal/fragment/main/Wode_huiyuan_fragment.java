package com.example.administrator.wankuoportal.fragment.main;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.beans.CheckSignin;
import com.example.administrator.wankuoportal.beans.Signin;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.CityHeHuo.CityPartnerActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.ErWeiMaActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MemberActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MemberTopHintActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyDaTiActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyInfoActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyJinBiActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyZhanghuActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.PTPingJiaActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.QianDaoJiLuActivity;
import com.example.administrator.wankuoportal.ui.KaiDianActivity;
import com.example.administrator.wankuoportal.ui.MainActivity;
import com.example.administrator.wankuoportal.ui.MyTeamActivity;
import com.example.administrator.wankuoportal.ui.SetUp.AboutUsActivity;
import com.example.administrator.wankuoportal.ui.SetUp.HelpActivity;
import com.example.administrator.wankuoportal.ui.SetUp.InvitationActivity;
import com.example.administrator.wankuoportal.ui.SetUp.SetUpActivity;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.ui.denglu.Personal_SetupActivity;
import com.example.administrator.wankuoportal.ui.shop.ShopActivity;
import com.example.administrator.wankuoportal.util.CircleImageView;
import com.example.administrator.wankuoportal.util.ClickHelper;
import com.example.administrator.wankuoportal.util.DataRepository;
import com.example.administrator.wankuoportal.util.LogUtils;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.jauker.widget.BadgeView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Date;

import io.reactivex.observers.DisposableObserver;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/16 0016.
 *
 * @author JakeChen
 */
public class Wode_huiyuan_fragment extends BaseFragment {

    private android.widget.ImageView sutup;
    private android.widget.ImageView qiandao;
    private android.widget.ImageView xiaoxi;
    private com.example.administrator.wankuoportal.util.CircleImageView denglu;
    private LinearLayout wddt;
    private TextView changeshenfen;
    private LinearLayout myjinbi;
    private LinearLayout myjianli;
    private LinearLayout myzhanghu;
    private LinearLayout wodeqiandao;
    private LinearLayout invitationfriend;
    private LinearLayout bangzhuzhongxin;
    private LinearLayout download;
    private TextView name;
    private TextView dingdan;
    private LinearLayout orderdaidui;
    private LinearLayout orderdaifa;
    private LinearLayout orderdaishou;
    private LinearLayout mypingjia;
    private LinearLayout paotyuipingjia;
    private LinearLayout kefulin;
    private TextView member;
    private LinearLayout aboutus;
    private LinearLayout shareerweima;
    private TextView shenfen;

    LinearLayout phone1;
    LinearLayout phone2;


    //金币商城
    private LinearLayout myGoldMallLayout;
    //购物金币
    private TextView myGoldGiveTv;
    //我的万店
    private LinearLayout myStoreLayout;
    //我的团队
    private LinearLayout myTeamLayout;
    //我的设置
    private LinearLayout mySettingsLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.wode_huiyuan_layout, container, false);//关联布局文件、
        //初始化默认View的方法
        initDefaultView(rootView);
        //初始化布局文件
        initview(rootView);
        return rootView;
    }

    private void initDefaultView(View rootView) {
        this.shenfen = (TextView) rootView.findViewById(R.id.shenfen);
        this.shareerweima = (LinearLayout) rootView.findViewById(R.id.share_erweima);
        this.aboutus = (LinearLayout) rootView.findViewById(R.id.aboutus);
        this.member = (TextView) rootView.findViewById(R.id.tv_member);
        this.kefulin = (LinearLayout) rootView.findViewById(R.id.kefu_lin);
        this.paotyuipingjia = (LinearLayout) rootView.findViewById(R.id.paotyuipingjia);
        this.mypingjia = (LinearLayout) rootView.findViewById(R.id.mypingjia);
        this.orderdaishou = (LinearLayout) rootView.findViewById(R.id.order_daishou);
        this.orderdaifa = (LinearLayout) rootView.findViewById(R.id.order_daifa);
        this.orderdaidui = (LinearLayout) rootView.findViewById(R.id.order_daidui);
        this.dingdan = (TextView) rootView.findViewById(R.id.dingdan);
        this.download = (LinearLayout) rootView.findViewById(R.id.download);
        this.invitationfriend = (LinearLayout) rootView.findViewById(R.id.invitationfriend);
        this.bangzhuzhongxin = (LinearLayout) rootView.findViewById(R.id.bangzhuzhongxin);
        this.wodeqiandao = (LinearLayout) rootView.findViewById(R.id.wode_qiandao);
        this.wddt = (LinearLayout) rootView.findViewById(R.id.wddt);
        this.myzhanghu = (LinearLayout) rootView.findViewById(R.id.my_zhanghu);
        this.myjianli = (LinearLayout) rootView.findViewById(R.id.my_jianli);
        this.myjinbi = (LinearLayout) rootView.findViewById(R.id.my_jinbi);
        this.changeshenfen = (TextView) rootView.findViewById(R.id.change_shenfen);
        this.name = (TextView) rootView.findViewById(R.id.name);
        this.denglu = (CircleImageView) rootView.findViewById(R.id.denglu);
        this.xiaoxi = (ImageView) rootView.findViewById(R.id.xiaoxi);
        this.qiandao = (ImageView) rootView.findViewById(R.id.qiandao);
        this.sutup = (ImageView) rootView.findViewById(R.id.sutup);

        myGoldMallLayout = (LinearLayout) rootView.findViewById(R.id.my_gold_mall_layout);
        myGoldGiveTv = (TextView) rootView.findViewById(R.id.my_gold_give_tv);
        myStoreLayout = (LinearLayout) rootView.findViewById(R.id.my_store_layout);
        myTeamLayout = (LinearLayout) rootView.findViewById(R.id.my_team_layout);
        mySettingsLayout = (LinearLayout) rootView.findViewById(R.id.my_settings_layout);
    }

    private void initview(View rootView) {

        final String accountId = new UserService(getActivity()).getaccountid();

        //分享二维码点击
        shareerweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new UserService(getActivity()).getislogin().equals("0")) {
                    startActivity(ErWeiMaActivity.class);
                } else {
                    startActivity(LoginActivity.class);
                }
            }
        });

        //客服点击
        kefulin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // 创建对话框构建器
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // 获取布局
                View view2 = View.inflate(getActivity(), R.layout.phone_choose, null);
                phone1 = (LinearLayout) view2.findViewById(R.id.phone1);
                phone2 = (LinearLayout) view2.findViewById(R.id.phone2);
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
        });

        //关于万阔点击

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AboutUsActivity.class);
            }
        });

        //升级为店长点击
        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickHelper.isHandle())
                    return;
                memberSubmit();
            }
        });

        //查看更多订单点击
        dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showShort(Constant.Tip.DEVELOPMENT);
//                if (new UserService(getActivity()).getislogin().equals("0")) {
//                    startActivity(ExchangeOrderActivity.class, "page", "0");
//                } else {
//                    startActivity(LoginActivity.class);
//                }

            }
        });
        //待兑换订单点击
        orderdaidui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showShort(Constant.Tip.DEVELOPMENT);
//                if (new UserService(getActivity()).getislogin().equals("0")) {
//                    startActivity(ExchangeOrderActivity.class, "page", "1");
//                } else {
//                    startActivity(LoginActivity.class);
//                }

            }
        });
        //待发货订单点击
        orderdaifa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showShort(Constant.Tip.DEVELOPMENT);
//                if (new UserService(getActivity()).getislogin().equals("0")) {
//                    startActivity(ExchangeOrderActivity.class, "page", "2");
//                } else {
//                    startActivity(LoginActivity.class);
//                }

            }
        });
        //待收货订单点击
        orderdaishou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showShort(Constant.Tip.DEVELOPMENT);
//                if (new UserService(getActivity()).getislogin().equals("0")) {
//                    startActivity(ExchangeOrderActivity.class, "page", "3");
//                } else {
//                    startActivity(LoginActivity.class);
//                }

            }
        });
        //跑腿评价点击
        paotyuipingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new UserService(getActivity()).getislogin().equals("0")) {
                    startActivity(PTPingJiaActivity.class);
                } else {
                    startActivity(LoginActivity.class);
                }

            }
        });

        //头像点击
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new UserService(getActivity()).getislogin().equals("0")) {
                    startActivity(Personal_SetupActivity.class);
                } else {
                    startActivity(LoginActivity.class);
                }

            }
        });
        //修改身份
        changeshenfen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UserService(getActivity()).setShenfen("guzhu");
                startActivity(MainActivity.class);
            }
        });
        //消息点击
        xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MyInfoActivity.class);
            }
        });
        //我的答题点击
        wddt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new UserService(getActivity()).getislogin().equals("0")) {
                    startActivity(MyDaTiActivity.class);
                } else {
                    startActivity(LoginActivity.class);
                }

            }
        });
        //签到点击
        qiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ClickHelper.isHandle())
                    return;
                String time = new Date().getTime() + "";
                String token = new UserService(getActivity()).gettoken();
                String accountId = new UserService(getActivity()).getaccountid();
                token = MD5Util.md5(time + token);
                String url = Apis.Base + Apis.signin + "?token=" + accountId + "," + time + "," + token;
                OkHttpUtils
                        .get()
                        .url(url)
                        .addParams("accountId", accountId)
                        .build()
                        .execute(new MyStringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onHttpResponse(String response, int id) throws Exception {
                                L.d(response);
                                Signin s = gson.fromJson(response, Signin.class);
                                if (s.getCode() == 0) {
                                    showShort(s.getMsg());
                                    qiandao.setVisibility(View.INVISIBLE);
                                } else if (s.getCode() == 2) {
                                    startActivity(LoginActivity.class);
                                    new UserService(getActivity()).setislogin("1");
                                } else {
                                    showShort(s.getMsg());
                                }
                            }
                        });
            }
        });

        //我的金币点击
        myjinbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new UserService(getActivity()).getislogin().equals("0")) {
                    startActivity(MyJinBiActivity.class);
                } else {
                    startActivity(LoginActivity.class);
                }

            }
        });
        //设置点击
        sutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSutup();
            }
        });
        mySettingsLayout.setOnClickListener((v) -> {
            gotoSutup();
        });
        //帮助中心点击
        bangzhuzhongxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(HelpActivity.class);
            }
        });
        //邀请好友点击
        invitationfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(InvitationActivity.class);
            }
        });
        //我的简历点击
        myjianli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showShort(Constant.Tip.DEVELOPMENT);
//                if (new UserService(getActivity()).getislogin().equals("0")) {
//                    startActivity(MyJianLiActivity.class);
//                } else {
//                    startActivity(LoginActivity.class);
//                }
            }
        });
        //下载商家端点击
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(KaiDianActivity.class);
            }
        });
        //我的账户点击
        myzhanghu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new UserService(getActivity()).getislogin().equals("0")) {
                    startActivity(MyZhanghuActivity.class);
                } else {
                    startActivity(LoginActivity.class);
                }
            }
        });
        //我的签到点击
        wodeqiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(QianDaoJiLuActivity.class);
            }
        });
        //金币商城
        myGoldMallLayout.setOnClickListener((v) -> {
            if (isLogin())
                startActivity(ShopActivity.class);
        });
        //我的万店
        myStoreLayout.setOnClickListener((v) -> {
            showShort(Constant.Tip.DEVELOPMENT);
//            if (isLogin())
//                SmallStoreActivity_.intent(this).start();
        });
        //我的团队
        myTeamLayout.setOnClickListener((v) -> {
            if (isLogin()) {
                Intent intent = new Intent(getActivity(), MyTeamActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isLogin() {
        if (new UserService(getActivity()).getislogin().equals("0")) {
            return true;
        } else {
            startActivity(LoginActivity.class);
            return false;
        }
    }

    private void gotoSutup() {

        if (new UserService(getActivity()).getislogin().equals("0")) {
            startActivity(SetUpActivity.class);
        } else {
            startActivity(LoginActivity.class);
        }
    }

    /**
     * 升级为店长点击
     */
    private void memberSubmit() {
        String time = new Date().getTime() + "";
        String token = new UserService(getActivity()).gettoken();
        String accountId = new UserService(getActivity()).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.checkarea + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("accountId", accountId)
                .build()
                .execute(new MyStringCallback() {

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        LogUtils.e("升级为店长点击:" + response);
                        BaseResult s = gson.fromJson(response, BaseResult.class);
                        if (s.getCode() == 0) {
                            if (new UserService(MyApplication.context).getIsOrdinaryMember() == 105) {
                                startActivity(MemberTopHintActivity.class);
                            } else {
                                startActivity(MemberActivity.class);
                            }
                        } else if (s.getCode() == 1) {
                            showShort(s.getMsg());
                            startActivity(Personal_SetupActivity.class);
                        } else if (s.getCode() == 2) {
                            showShort(s.getMsg());
                            startActivity(LoginActivity.class);
                            new UserService(getActivity()).setislogin("1");
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();

        if (new UserService(getActivity()).getislogin().equals("1")) {
            name.setText("普通会员");
            Glide.with(Wode_huiyuan_fragment.this).load(R.drawable.tou).into(denglu);
        } else {
            checkSignIn();
        }
        initxiaoxi();
    }

    private void checkSignIn() {
        String time = new Date().getTime() + "";
        String token = new UserService(getActivity()).gettoken();
        final String accountId = new UserService(getActivity()).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.checksignin + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("accountId", accountId)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        try {
                            LogUtils.e("CheckSignIn:" + response);
                            CheckSignin checkSignin = gson.fromJson(response, CheckSignin.class);
                            int mCode = checkSignin.getCode();
                            if (mCode >= 0 && mCode < 2) {
                                if (mCode == 0) {
                                    qiandao.setVisibility(View.VISIBLE);
                                } else {
                                    qiandao.setVisibility(View.INVISIBLE);
                                }
                                //保存登录状态
                                new UserService(MyApplication.context).setislogin("0");
                                //获取账户信息
                                CheckSignin.DataBean.AAccountInfoBean aAccountInfo = checkSignin.getData().getAAccountInfo();

                                //设置头像
                                if (aAccountInfo.getHeadIcon().isEmpty()) {
                                    Glide.with(Wode_huiyuan_fragment.this).load(R.drawable.tou).into(denglu);
                                } else {
                                    String imaurl = Apis.Baseima + aAccountInfo.getHeadIcon();
                                    Glide.with(Wode_huiyuan_fragment.this).load(imaurl).into(denglu);
                                }

                                //设置店长信息
                                int isOrdinaryMember = checkSignin.getData().getAAccountAuthorize().getIsOrdinaryMember();
                                String ordinaryMember = memberGradeInfo(isOrdinaryMember);
                                shenfen.setText(ordinaryMember);
                                if ("无".equals(ordinaryMember) || "普通会员".equals(ordinaryMember)){
                                    member.setText("升级为店长");
                                }else {
                                    member.setText("升级店长");
                                }
                                if ("至尊店长".equals(ordinaryMember)){
                                    member.setText("升级为合伙人");
                                    //升级为店长点击
                                    member.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
//                                            showShort("您已经是至尊店长了!");
                                            startActivity(new Intent(getActivity(), CityPartnerActivity.class));
                                        }
                                    });
                                }
                                //保存店长等级信息
                                new UserService(MyApplication.context).setIsOrdinaryMember(isOrdinaryMember);

                                if (aAccountInfo.getNickname().isEmpty()) {
                                    name.setText("游客");
                                } else {
                                    name.setText(aAccountInfo.getNickname());
                                }

                                //设置购物金币数量
                                myGoldGiveTv.setText("赠送购物金币" + checkSignin.getData().getAAccountAuthorize().getShoppingMoney());

                            } else if (mCode == 2) {
                                qiandao.setVisibility(View.VISIBLE);
                                showShort(checkSignin.getMsg());
                                Glide.with(Wode_huiyuan_fragment.this).load(R.drawable.tou).into(denglu);
                                new UserService(getActivity()).setislogin("1");
                                new UserService(getActivity()).settoken("0");
                                name.setText("登录");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 获取用户店长等级的方法
     *
     * @param isOrdinaryMember
     * @return
     */
    private String memberGradeInfo(int isOrdinaryMember) {
        String mTempGradeInfo = "普通会员";
        switch (isOrdinaryMember) {
            case 0:
                mTempGradeInfo = "无";
                break;
            case 1:
                mTempGradeInfo = "普通会员";
                break;
            case 101:
                mTempGradeInfo = "白银店长";
                break;
            case 102:
                mTempGradeInfo = "黄金店长";
                break;
            case 103:
                mTempGradeInfo = "钻石店长";
                break;
            case 104:
                mTempGradeInfo = "皇冠店长";
                break;
            case 105:
                mTempGradeInfo = "至尊店长";
                break;
        }
        return mTempGradeInfo;
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    private void initxiaoxi() {
        DataRepository.getInstance().getNumInfoNotRead(getActivity(), new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                try {
                    BadgeView badge = new BadgeView(MyApplication.context);
                    badge.setBackground(9, getActivity().getResources().getColor(R.color.red_shape));
                    badge.setTargetView(xiaoxi);
                    badge.setBadgeCount(integer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
