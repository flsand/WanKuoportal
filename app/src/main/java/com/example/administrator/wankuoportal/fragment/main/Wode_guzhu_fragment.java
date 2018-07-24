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
import com.example.administrator.wankuoportal.beans.CheckSignin;
import com.example.administrator.wankuoportal.beans.Signin;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.ErWeiMaActivity;
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
import com.example.administrator.wankuoportal.ui.smallstroe.SmallStoreActivity_;
import com.example.administrator.wankuoportal.util.ClickHelper;
import com.example.administrator.wankuoportal.util.DataRepository;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.jauker.widget.BadgeView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.observers.DisposableObserver;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/16 0016.
 *
 * @author JakeChen
 */
public class Wode_guzhu_fragment extends BaseFragment {

    private android.widget.ImageView sutup;
    private android.widget.ImageView qiandao;
    private android.widget.ImageView xiaoxi;
    private CircleImageView denglu;
    private android.widget.TextView changeshenfen;
    private android.widget.LinearLayout myjinbi;
    private android.widget.LinearLayout myzhanghu;
    private LinearLayout bangzhuzhongxin;
    private LinearLayout invitationfriend;
    private LinearLayout download;
    private TextView name;
    private LinearLayout orderall;
    private LinearLayout orderdaituo;
    private LinearLayout orderjiaoyi;
    private LinearLayout orderdaifu;
    private LinearLayout orderdaiping;
    private LinearLayout myshoucang;
    private LinearLayout jypingjia;
    private LinearLayout kefulin;
    private LinearLayout wodedt;
    private LinearLayout wodeqiandao;
    private LinearLayout paotyuipingjia;
    private LinearLayout dingdan;
    private LinearLayout myfapiao;
    private LinearLayout myjubao;
    private LinearLayout mytuikuan;
    private LinearLayout myweiquan;
    LinearLayout phone1;
    LinearLayout phone2;
    private LinearLayout aboutus;
    private LinearLayout shareerweima;
    private TextView shenfen;
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
        View rootView = inflater.inflate(R.layout.wode_guzhu_layout, container, false);//关联布局文件、

        this.shenfen = (TextView) rootView.findViewById(R.id.shenfen);
        this.shareerweima = (LinearLayout) rootView.findViewById(R.id.share_erweima);
        this.aboutus = (LinearLayout) rootView.findViewById(R.id.aboutus);
        this.myweiquan = (LinearLayout) rootView.findViewById(R.id.myweiquan);
        this.mytuikuan = (LinearLayout) rootView.findViewById(R.id.mytuikuan);
        this.myjubao = (LinearLayout) rootView.findViewById(R.id.myjubao);
        this.myfapiao = (LinearLayout) rootView.findViewById(R.id.myfapiao);
        this.dingdan = (LinearLayout) rootView.findViewById(R.id.dingdan);
        this.paotyuipingjia = (LinearLayout) rootView.findViewById(R.id.paotyuipingjia);
        this.wodeqiandao = (LinearLayout) rootView.findViewById(R.id.wodeqiandao);
        this.wodedt = (LinearLayout) rootView.findViewById(R.id.wodedt);
        this.kefulin = (LinearLayout) rootView.findViewById(R.id.kefu_lin);
        this.jypingjia = (LinearLayout) rootView.findViewById(R.id.jypingjia);
        this.myshoucang = (LinearLayout) rootView.findViewById(R.id.my_shoucang);
        this.orderdaiping = (LinearLayout) rootView.findViewById(R.id.orderdaiping);
        this.orderdaifu = (LinearLayout) rootView.findViewById(R.id.orderdaifu);
        this.orderjiaoyi = (LinearLayout) rootView.findViewById(R.id.orderjiaoyi);
        this.orderdaituo = (LinearLayout) rootView.findViewById(R.id.orderdaituo);
        this.orderall = (LinearLayout) rootView.findViewById(R.id.orderall);
        this.name = (TextView) rootView.findViewById(R.id.name);
        this.download = (LinearLayout) rootView.findViewById(R.id.download);
        this.bangzhuzhongxin = (LinearLayout) rootView.findViewById(R.id.bangzhuzhongxin);
        this.myzhanghu = (LinearLayout) rootView.findViewById(R.id.my_zhanghu);
        this.myjinbi = (LinearLayout) rootView.findViewById(R.id.my_jinbi);
        this.changeshenfen = (TextView) rootView.findViewById(R.id.change_shenfen);
        this.denglu = (CircleImageView) rootView.findViewById(R.id.denglu);
        this.xiaoxi = (ImageView) rootView.findViewById(R.id.xiaoxi);
        this.invitationfriend = (LinearLayout) rootView.findViewById(R.id.invitationfriend);
        this.qiandao = (ImageView) rootView.findViewById(R.id.qiandao);
        this.sutup = (ImageView) rootView.findViewById(R.id.sutup);

        myGoldMallLayout = (LinearLayout) rootView.findViewById(R.id.my_gold_mall_layout);
        myGoldGiveTv = (TextView) rootView.findViewById(R.id.my_gold_give_tv);
        myStoreLayout = (LinearLayout) rootView.findViewById(R.id.my_store_layout);
        myTeamLayout = (LinearLayout) rootView.findViewById(R.id.my_team_layout);
        mySettingsLayout = (LinearLayout) rootView.findViewById(R.id.my_settings_layout);

        //消息点击
        xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyInfoActivity.class);
            }
        });

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

        //关于万阔点击

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AboutUsActivity.class);
            }
        });

        //我的维权点击
        myweiquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showShort(Constant.Tip.DEVELOPMENT);
//                startActivity(MyActivistActivity.class);
            }
        });

        //我的退款点击
        mytuikuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showShort(Constant.Tip.DEVELOPMENT);
//                startActivity(MyRefundActivity.class);
            }
        });

        //我的举报点击
        myjubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showShort(Constant.Tip.DEVELOPMENT);
//                startActivity(MyReportActivity.class);
            }
        });


        //我的发票点击
        myfapiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showShort(Constant.Tip.DEVELOPMENT);
//                startActivity(MyInvoiceActivity.class);
            }
        });


        //我的收藏点击
        myshoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShort(Constant.Tip.DEVELOPMENT);
//                startActivity(MyCollectionActivity.class);
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

        //我的答题点击
        wodedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickHelper.isHandle()) {
                    return;
                }
                if (new UserService(getActivity()).getislogin().equals("0")) {
                    startActivity(MyDaTiActivity.class);
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

        //查看更多订单点击
        orderall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showShort(Constant.Tip.DEVELOPMENT);
//                if (new UserService(getActivity()).getislogin().equals("0")) {
//                    startActivity(OrderGuZhuActivity.class, "page", "0");
//                } else {
//                    startActivity(LoginActivity.class);
//                }

            }
        });
        //查看待托管订单点击
        orderdaituo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showShort(Constant.Tip.DEVELOPMENT);
//                if (new UserService(getActivity()).getislogin().equals("0")) {
//                    startActivity(OrderGuZhuActivity.class, "page", "1");
//                } else {
//                    startActivity(LoginActivity.class);
//                }

            }
        });
        //查看交易中订单点击
        orderjiaoyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showShort(Constant.Tip.DEVELOPMENT);
//                if (new UserService(getActivity()).getislogin().equals("0")) {
//                    startActivity(OrderGuZhuActivity.class, "page", "2");
//                } else {
//                    startActivity(LoginActivity.class);
//                }

            }
        });

        //查看待付款订单点击
        orderdaifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showShort(Constant.Tip.DEVELOPMENT);
//                if (new UserService(getActivity()).getislogin().equals("0")) {
//                    startActivity(OrderGuZhuActivity.class, "page", "3");
//                } else {
//                    startActivity(LoginActivity.class);
//                }

            }
        });
        //查看待评价订单点击
        orderdaiping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShort(Constant.Tip.DEVELOPMENT);
//                if (new UserService(getActivity()).getislogin().equals("0")) {
//                    startActivity(OrderGuZhuActivity.class, "page", "4");
//                } else {
//                    startActivity(LoginActivity.class);
//                }

            }
        });


        //头像点击
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new UserService(getActivity()).getislogin().equals("0")) {
                    startActivity(Personal_SetupActivity.class);
                } else {
                    startActivity(LoginActivity.class);
                }
            }
        });

        //更换身份点击
        changeshenfen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UserService(getActivity()).setShenfen("huiyuan");
                startActivity(MainActivity.class);
            }
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
                if (new UserService(getActivity()).getislogin().equals("0")) {

                } else {
                    startActivity(InvitationActivity.class);
                }
            }
        });
        //下载商家端
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(KaiDianActivity.class);
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
        //我的金币点击
        myjinbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new UserService(getActivity()).getislogin().equals("0")) {
                    startActivity(MyJinBiActivity.class);
                } else {
                    startActivity(LoginActivity.class);
                }

            }
        });

        //我的账号点击
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
        //签到点击
        qiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                                    qiandao.setVisibility(View.INVISIBLE);
                                    showShort(s.getMsg());
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

        //交易评价点击
        jypingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShort(Constant.Tip.DEVELOPMENT);
//                startActivity(TransactionEvaluationActivity.class);
            }
        });

        //金币商城
        myGoldMallLayout.setOnClickListener((v) -> {
            if (isLogin())
                startActivity(ShopActivity.class);
        });
        //我的万店
        myStoreLayout.setOnClickListener((v) -> {
            if (isLogin())
                SmallStoreActivity_.intent(this).start();
        });
        //我的团队
        myTeamLayout.setOnClickListener((v) -> {
            if (isLogin()) {
                Intent intent = new Intent(getActivity(), MyTeamActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
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

    @Override
    public void onResume() {
        super.onResume();
        initxiaoxi();
        if (new UserService(getActivity()).getislogin().equals("1")) {
            name.setText("雇主");
            Glide.with(Wode_guzhu_fragment.this).load(R.drawable.tou).into(denglu);
        } else {
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
                        public void onHttpResponse(String response, int id) throws Exception {
                            try {
                                L.d(response);
                                CheckSignin checkSignin = gson.fromJson(response, CheckSignin.class);
                                if (checkSignin.getCode() == 0) {
                                    new UserService(getActivity()).setislogin("0");
                                    qiandao.setVisibility(View.VISIBLE);
                                    if (checkSignin.getData().getAAccountInfo().getHeadIcon() != null) {
                                        if (checkSignin.getData().getAAccountInfo().getHeadIcon().isEmpty()) {
                                            Glide.with(Wode_guzhu_fragment.this).load(R.drawable.tou).into(denglu);
                                        } else {
                                            String imaurl = Apis.Baseima + checkSignin.getData().getAAccountInfo().getHeadIcon();
                                            Glide.with(Wode_guzhu_fragment.this).load(imaurl).into(denglu);
                                        }
                                        if (checkSignin.getData().getAAccountAuthorize().getIsOrdinaryMember() == 2) {
                                            changeshenfen.setText("转换成钻石会员");
                                        } else {
                                            changeshenfen.setText("转换成普通会员");
                                        }

                                        if (checkSignin.getData().getAAccountInfo().getNickname().isEmpty()) {
                                            name.setText("游客");
                                        } else {
                                            name.setText(checkSignin.getData().getAAccountInfo().getNickname());
                                        }
                                    }

                                } else if (checkSignin.getCode() == 1) {
                                    new UserService(getActivity()).setislogin("0");
                                    qiandao.setVisibility(View.INVISIBLE);
                                    if (checkSignin.getData().getAAccountInfo().getHeadIcon().isEmpty()) {
                                        Glide.with(Wode_guzhu_fragment.this).load(R.drawable.tou).into(denglu);
                                    } else {
                                        String imaurl = Apis.Baseima + checkSignin.getData().getAAccountInfo().getHeadIcon();
                                        Glide.with(Wode_guzhu_fragment.this).load(imaurl).into(denglu);
                                    }

                                    if (checkSignin.getData().getAAccountAuthorize().getIsOrdinaryMember() == 2) {
                                        changeshenfen.setText("转换成钻石会员");
                                    } else {
                                        changeshenfen.setText("转换成普通会员");
                                    }
                                    if (checkSignin.getData().getAAccountInfo().getNickname().isEmpty()) {
                                        name.setText("游客");
                                    } else {
                                        name.setText(checkSignin.getData().getAAccountInfo().getNickname());
                                    }
                                } else if (checkSignin.getCode() == 2) {
                                    qiandao.setVisibility(View.VISIBLE);
                                    showShort(checkSignin.getMsg());
                                    Glide.with(Wode_guzhu_fragment.this).load(R.drawable.tou).into(denglu);
                                    new UserService(getActivity()).setislogin("1");
                                    new UserService(getActivity()).settoken("0");
                                    name.setText("登录");
                                }
                                //设置购物金币数量
                                myGoldGiveTv.setText("赠送购物金币" + checkSignin.getData().getAAccountAuthorize().getShoppingMoney());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
    }

    private void initxiaoxi() {
        DataRepository.getInstance().getNumInfoNotRead(getActivity(), new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                try {
                    BadgeView badge = new BadgeView(MyApplication.context);
                    badge.setBackground(9, getResources().getColor(R.color.red_shape));
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
