package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.GetNeedMoneyBean;
import com.example.administrator.wankuoportal.beans.MemberGradeDetails;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.Pay.PayChooseActivity;
import com.example.administrator.wankuoportal.util.CommonUtil;
import com.example.administrator.wankuoportal.util.DataRepository;
import com.flysand.mylibrary.util.MyHandler;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MemberPayActivity extends BaseActivity {

    public final static String SELECT_MEMBER_GRADE = "SELECT_MEMBER_GRADE";

    public static int SELECT_MEMBER_GRADE_BAIYIN = 1;
    public static int SELECT_MEMBER_GRADE_HUANGJIN = 2;
    public static int SELECT_MEMBER_GRADE_ZUANSHI = 3;
    public static int SELECT_MEMBER_GRADE_HUANGGUAN = 4;
    public static int SELECT_MEMBER_GRADE_ZHIZUN = 5;

    private TextView mBenefits1;

    private List<MemberGradeDetails> gradeDetails = new ArrayList<>();
    private TextView mBenefits2;
    private TextView mBenefits3;
    private TextView mBenefits4;
    private TextView mBenefits5;
    private TextView mBenefits6;
    private TextView mBenefits7;
    private TextView mBenefits8;
    private ImageView mGradeImg;
    private TextView mName;
    private TextView mTitle;
    private TextView mContent;
    private TextView mMoney;
    private TextView mPresentation;
    private TextView mPaySubmit;
    private TextView mUpgradeMoney;
    private TextView mOldMoney;
    private MemberGradeDetails memberGradeDetails;
    private int isOrdinaryMember;

    private double mPayMoney = 0.0f;
    private int mTarget;

    private boolean isPay = false;
    {
        //初始化白银店长信息
        MemberGradeDetails baiyin = new MemberGradeDetails();
        baiyin.setName("白银店长");
        baiyin.setImg_url(R.drawable.member_grade_1);
        baiyin.setTitle("加盟店长保证金");
        baiyin.setContent("每月固定分红,免费领取商品,区域佣金受益");
        baiyin.setPayMoney("1800");
        baiyin.setPresentation("升级至白银店长享受以下受益 :");
        baiyin.setBenefits1("1.加盟白银店长,享有每月15元金额分红红包,按天发放，每天早上8点自动发放到我的钱包余额里");
        baiyin.setBenefits2("2.免费领取价值1800元的商品,任选同等价值的商品");
        baiyin.setBenefits3("3.推荐店长加盟,直接推荐店长加盟或开店奖励10%,管理奖励5%");
        baiyin.setBenefits4("4.享有区域合伙人佣金的20%平分(本地没有区域合伙人加盟之前,本地店长平均分配佣金)");
        baiyin.setBenefits5("5.享有城市合伙人佣金的15%平分(本地没有城市合伙人加盟之前,本地店长平均分配佣金)");
        baiyin.setBenefits6("6.享有省级合伙人佣金的10%平分(本地没有省级合伙人加盟之前,本地店长平均分配佣金)");
        baiyin.setBenefits7("7.推荐好友加盟闲赚答题、领任务、雇佣服务、购买产品、定制服务、入驻开店均享有佣金的5%收益");
        baiyin.setBenefits8("8.加盟白银店长，赠送180万购物钻石，钻石可在金币商城消费抵扣,赠送钻石消费完毕，店长保证金可以全额申请领回");
        gradeDetails.add(baiyin);
        //初始化黄金店长信息
        MemberGradeDetails huangjn = new MemberGradeDetails();
        huangjn.setName("黄金店长");
        huangjn.setImg_url(R.drawable.member_grade_2);
        huangjn.setTitle("加盟店长保证金");
        huangjn.setContent("每月固定分红,免费领取商品,区域佣金受益");
        huangjn.setPayMoney("3600");
        huangjn.setPresentation("升级至黄金店长享受以下受益 :");
        huangjn.setBenefits1("1.加盟黄金店长,享有每月30元金额分红红包,按天发放，每天早上8点自动发放到我的钱包余额里");
        huangjn.setBenefits2("2.免费领取价值3600元的商品,任选同等价值的商品");
        huangjn.setBenefits3("3.推荐店长加盟,直接推荐店长加盟或开店奖励10%,管理奖励5%");
        huangjn.setBenefits4("4.享有区域合伙人佣金的20%平分(本地没有区域合伙人加盟之前,本地店长平均分配佣金)");
        huangjn.setBenefits5("5.享有城市合伙人佣金的15%平分(本地没有城市合伙人加盟之前,本地店长平均分配佣金)");
        huangjn.setBenefits6("6.享有省级合伙人佣金的10%平分(本地没有省级合伙人加盟之前,本地店长平均分配佣金)");
        huangjn.setBenefits7("7.推荐好友加盟闲赚答题、领任务、雇佣服务、购买产品、定制服务、入驻开店均享有佣金的5%收益");
        huangjn.setBenefits8("8.加盟黄金店长，赠送360万购物钻石，钻石可在金币商城消费抵扣,赠送钻石消费完毕，店长保证金可以全额申请领回");
        gradeDetails.add(huangjn);
        //初始化钻石店长信息
        MemberGradeDetails zuanshi = new MemberGradeDetails();
        zuanshi.setName("钻石店长");
        zuanshi.setImg_url(R.drawable.member_grade_3);
        zuanshi.setTitle("加盟店长保证金");
        zuanshi.setContent("每月固定分红,免费领取商品,区域佣金受益");
        zuanshi.setPayMoney("7200");
        zuanshi.setPresentation("升级至钻石店长享受以下受益 :");
        zuanshi.setBenefits1("1.加盟钻石店长,享有每月60元金额分红红包,按天发放，每天早上8点自动发放到我的钱包余额里");
        zuanshi.setBenefits2("2.免费领取价值7200元的商品,任选同等价值的商品");
        zuanshi.setBenefits3("3.推荐店长加盟,直接推荐店长加盟或开店奖励10%,管理奖励5%");
        zuanshi.setBenefits4("4.享有区域合伙人佣金的20%平分(本地没有区域合伙人加盟之前,本地店长平均分配佣金)");
        zuanshi.setBenefits5("5.享有城市合伙人佣金的15%平分(本地没有城市合伙人加盟之前,本地店长平均分配佣金)");
        zuanshi.setBenefits6("6.享有省级合伙人佣金的10%平分(本地没有省级合伙人加盟之前,本地店长平均分配佣金)");
        zuanshi.setBenefits7("7.推荐好友加盟闲赚答题、领任务、雇佣服务、购买产品、定制服务、入驻开店均享有佣金的5%收益");
        zuanshi.setBenefits8("8.加盟钻石店长，赠送720万购物钻石，钻石可在金币商城消费抵扣,赠送钻石消费完毕，店长保证金可以全额申请领回");
        gradeDetails.add(zuanshi);
        //初始化钻石店长信息
        MemberGradeDetails huangguan = new MemberGradeDetails();
        huangguan.setName("皇冠店长");
        huangguan.setImg_url(R.drawable.member_grade_4);
        huangguan.setTitle("加盟店长保证金");
        huangguan.setContent("每月固定分红,免费领取商品,区域佣金受益");
        huangguan.setPayMoney("10800");
        huangguan.setPresentation("升级至皇冠店长享受以下受益 :");
        huangguan.setBenefits1("1.加盟皇冠店长,享有每月90元金额分红红包,按天发放，每天早上8点自动发放到我的钱包余额里");
        huangguan.setBenefits2("2.免费领取价值10800元的商品,任选同等价值的商品");
        huangguan.setBenefits3("3.推荐店长加盟,直接推荐店长加盟或开店奖励10%,管理奖励5%");
        huangguan.setBenefits4("4.享有区域合伙人佣金的20%平分(本地没有区域合伙人加盟之前,本地店长平均分配佣金)");
        huangguan.setBenefits5("5.享有城市合伙人佣金的15%平分(本地没有城市合伙人加盟之前,本地店长平均分配佣金)");
        huangguan.setBenefits6("6.享有省级合伙人佣金的10%平分(本地没有省级合伙人加盟之前,本地店长平均分配佣金)");
        huangguan.setBenefits7("7.推荐好友加盟闲赚答题、领任务、雇佣服务、购买产品、定制服务、入驻开店均享有佣金的5%收益");
        huangguan.setBenefits8("8.加盟皇冠店长，赠送1080万购物钻石，钻石可在金币商城消费抵扣,赠送钻石消费完毕，店长保证金可以全额申请领回");
        gradeDetails.add(huangguan);
        //初始化至尊店长信息
        MemberGradeDetails zhizun = new MemberGradeDetails();
        zhizun.setName("至尊店长");
        zhizun.setImg_url(R.drawable.member_grade_5);
        zhizun.setTitle("加盟店长保证金");
        zhizun.setContent("每月固定分红,免费领取商品,区域佣金受益");
        zhizun.setPayMoney("18000");
        zhizun.setPresentation("升级至至尊店长享受以下受益 :");
        zhizun.setBenefits1("1.加盟至尊店长,享有每月150元金额分红红包,按天发放，每天早上8点自动发放到我的钱包余额里");
        zhizun.setBenefits2("2.免费领取价值18000元的商品,任选同等价值的商品");
        zhizun.setBenefits3("3.推荐店长加盟,直接推荐店长加盟或开店奖励10%,管理奖励5%");
        zhizun.setBenefits4("4.享有区域合伙人佣金的20%平分(本地没有区域合伙人加盟之前,本地店长平均分配佣金)");
        zhizun.setBenefits5("5.享有城市合伙人佣金的15%平分(本地没有城市合伙人加盟之前,本地店长平均分配佣金)");
        zhizun.setBenefits6("6.享有省级合伙人佣金的10%平分(本地没有省级合伙人加盟之前,本地店长平均分配佣金)");
        zhizun.setBenefits7("7.推荐好友加盟闲赚答题、领任务、雇佣服务、购买产品、定制服务、入驻开店均享有佣金的5%收益");
        zhizun.setBenefits8("8.加盟至尊店长，赠送1800万购物钻石，钻石可在金币商城消费抵扣,赠送钻石消费完毕，店长保证金可以全额申请领回");
        gradeDetails.add(zhizun);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_pay);
        initView();
        initVariables();
    }

    /**
     * 初始化变量的方法
     */
    private void initVariables() {
        Intent intent = getIntent();
        int select_member_grade = intent.getIntExtra("SELECT_MEMBER_GRADE", 1);
        initData(select_member_grade);

        int type = 101;

        switch (memberGradeDetails.getPayMoney()) {
            case "1800":
                type = 101;
                break;
            case "3600":
                type = 102;
                break;
            case "7200":
                type = 103;
                break;
            case "10800":
                type = 104;
                break;
            case "18000":
                type = 105;
                break;
        }
        mTarget = type;

        DataRepository.getInstance().getNeedPayMoney(this, new DisposableObserver<GetNeedMoneyBean>() {
            @Override
            public void onNext(GetNeedMoneyBean getNeedMoneyBean) {
                if (getNeedMoneyBean.getMoney().equals(getNeedMoneyBean.getPaymoney())) {
                    mOldMoney.setVisibility(View.GONE);
                    mUpgradeMoney.setVisibility(View.GONE);
                    mMoney.setVisibility(View.VISIBLE);
                    mMoney.setText(getNeedMoneyBean.getPaymoney());
                } else {
                    mOldMoney.setVisibility(View.VISIBLE);
                    mUpgradeMoney.setVisibility(View.VISIBLE);
                    mMoney.setVisibility(View.GONE);
                    mOldMoney.setText(getNeedMoneyBean.getMoney());
                    mUpgradeMoney.setText(getNeedMoneyBean.getPaymoney());
                }
                mPayMoney = Double.valueOf(getNeedMoneyBean.getPaymoney());
                isPay =true;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, type);
    }

    /**
     * 点击支付按钮
     *
     * @param isOrdinaryMember
     */
    private void paySubmit(int isOrdinaryMember) {
        if (isPay){
            Intent intent = new Intent(this,PayChooseActivity.class);
            intent.putExtra("price", String.valueOf(mPayMoney));
            intent.putExtra("target",String.valueOf(mTarget));
            startActivityForResult(intent, Constant.RequestCode.PAY);
        }else {
            showShort("支付金额获取失败");
            new MyHandler(1000){
                @Override
                public void run() {
                    finish();
                }
            };
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RequestCode.PAY){
            if (resultCode == RESULT_OK){
                setResult(RESULT_OK);
                finish();
            }
        }
    }

    private int getPayMoney(int isOrdinaryMember) {
        int mTempPayMoney = 0;
        switch (isOrdinaryMember) {
            case 101:
                mTempPayMoney = Integer.parseInt(memberGradeDetails.getPayMoney()) - 1800;
                break;
            case 102:
                mTempPayMoney = Integer.parseInt(memberGradeDetails.getPayMoney()) - 3600;
                break;
            case 103:
                mTempPayMoney = Integer.parseInt(memberGradeDetails.getPayMoney()) - 7200;
                break;
            case 104:
                mTempPayMoney = Integer.parseInt(memberGradeDetails.getPayMoney()) - 10800;
                break;
            case 105:
                mTempPayMoney = Integer.parseInt(memberGradeDetails.getPayMoney()) - 18000;
                break;
            default:
                mTempPayMoney = Integer.parseInt(memberGradeDetails.getPayMoney());
                break;
        }
        return mTempPayMoney;
    }

    private void initData(int memberGrade) {
        memberGradeDetails = gradeDetails.get(memberGrade);
        mGradeImg.setImageResource(memberGradeDetails.getImg_url());
        mName.setText(memberGradeDetails.getName());
        mTitle.setText(memberGradeDetails.getTitle());
        mContent.setText(memberGradeDetails.getContent());
        mMoney.setText(memberGradeDetails.getPayMoney());
        mPresentation.setText(memberGradeDetails.getPresentation());
        //填充第一条
        String benefits1 = memberGradeDetails.getBenefits1();
        SpannableStringBuilder style1 = new SpannableStringBuilder(benefits1);
        style1.setSpan(new ForegroundColorSpan(Color.parseColor("#FF4200")), 13, benefits1.length() - 24, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mBenefits1.setText(style1);
        //填充第二条
        String benefits2 = memberGradeDetails.getBenefits2();
        SpannableStringBuilder style2 = new SpannableStringBuilder(benefits2);
        style2.setSpan(new ForegroundColorSpan(Color.parseColor("#FF4200")), 8, benefits2.length() - 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mBenefits2.setText(style2);
        //填充第三条
        String benefits3 = memberGradeDetails.getBenefits3();
        SpannableStringBuilder style3 = new SpannableStringBuilder(benefits3);
        style3.setSpan(new ForegroundColorSpan(Color.parseColor("#FF4200")), 22, 25, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style3.setSpan(new ForegroundColorSpan(Color.parseColor("#FF4200")), benefits3.length() - 2, benefits3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mBenefits3.setText(style3);
        //填充第四条
        String benefits4 = memberGradeDetails.getBenefits4();
        SpannableStringBuilder style4 = new SpannableStringBuilder(benefits4);
        style4.setSpan(new ForegroundColorSpan(Color.parseColor("#FF4200")), 12, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mBenefits4.setText(style4);
        //填充第五条
        String benefits5 = memberGradeDetails.getBenefits5();
        SpannableStringBuilder style5 = new SpannableStringBuilder(benefits5);
        style5.setSpan(new ForegroundColorSpan(Color.parseColor("#FF4200")), 12, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mBenefits5.setText(style5);
        //填充第六条
        String benefits6 = memberGradeDetails.getBenefits6();
        SpannableStringBuilder style6 = new SpannableStringBuilder(benefits6);
        style6.setSpan(new ForegroundColorSpan(Color.parseColor("#FF4200")), 12, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mBenefits6.setText(style6);
        //填充第七条
        String benefits7 = memberGradeDetails.getBenefits7();
        SpannableStringBuilder style7 = new SpannableStringBuilder(benefits7);
        style7.setSpan(new ForegroundColorSpan(Color.parseColor("#FF4200")), benefits7.length() - 4, benefits7.length() - 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mBenefits7.setText(style7);
        //填充第八条
        String benefits8 = memberGradeDetails.getBenefits8();
        SpannableStringBuilder style8 = CommonUtil.setSpannableString(Color.parseColor("#FF4200"), benefits8, 11, benefits8.length() - 40);
        mBenefits8.setText(style8);


        mPaySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paySubmit(new UserService(MyApplication.context).getIsOrdinaryMember());
            }
        });
    }

    /**
     * 初始化View的方法
     */
    private void initView() {
        mPaySubmit = (TextView) findViewById(R.id.tv_member_pay_submit);
        mGradeImg = (ImageView) findViewById(R.id.iv_member_pay_img);
        mName = (TextView) findViewById(R.id.tv_member_pay_name);
        mTitle = (TextView) findViewById(R.id.iv_member_pay_title);
        mContent = (TextView) findViewById(R.id.iv_member_pay_content);
        mMoney = (TextView) findViewById(R.id.iv_member_pay_money);
        mPresentation = (TextView) findViewById(R.id.iv_member_pay_presentation);
        mBenefits1 = (TextView) findViewById(R.id.tv_member_benefits_1);
        mBenefits2 = (TextView) findViewById(R.id.tv_member_benefits_2);
        mBenefits3 = (TextView) findViewById(R.id.tv_member_benefits_3);
        mBenefits4 = (TextView) findViewById(R.id.tv_member_benefits_4);
        mBenefits5 = (TextView) findViewById(R.id.tv_member_benefits_5);
        mBenefits6 = (TextView) findViewById(R.id.tv_member_benefits_6);
        mBenefits7 = (TextView) findViewById(R.id.tv_member_benefits_7);
        mBenefits8 = (TextView) findViewById(R.id.tv_member_benefits_8);

        mOldMoney = (TextView) findViewById(R.id.iv_member_old_pay_money);
        mOldMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        mUpgradeMoney = (TextView) findViewById(R.id.iv_member_upgrade_pay_money);

        ImageView mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
