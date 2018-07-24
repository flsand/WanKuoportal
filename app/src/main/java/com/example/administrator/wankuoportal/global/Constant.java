package com.example.administrator.wankuoportal.global;

/**
 * Created by zht on 2017/04/19 17:07
 */

public class Constant {
    /**
     * 成功
     */
    public static final int SUCCESS = 0;
    /**
     * 没有数据
     */
    public static final int EMPTY = -3;
    /**
     * 没有更多数据
     */
    public static final int NOMORE = -4;

    /**
     * 网络中断，请检查您的网络状态
     */
    public static final int NETERROR = -1000;
    /**
     * 未知错误
     */
    public static final int UNKONWERROR = -1001;
    /**
     * 由聊天页面back回main
     */
    public static boolean goService = false;

    public static String getPayType(String type) {
        String defultType = "默认支付";
        switch (type) {
            case PayType.PAY_ZFB:
                return "支付宝支付";
            case PayType.PAY_WX_APP:
                return "微信APP支付";
            case PayType.PAY_SHOP:
                return "购物金币支付";
            case PayType.PAY_ANSWER:
                return "答题金币支付";
            case PayType.PAY_RECHARGE:
                return "充值金币支付";
            case PayType.PAY_DIAMONDS:
                return "钻石支付";
            case PayType.PAY_BALANCE:
                return "用户余额支付";
            case PayType.PAY_WXGZH:
                return "微信公众号支付";
            case PayType.PAY_ASSET:
                return "用户自身资产";
        }
        return defultType;
    }

    public static class PayType {
        //支付宝支付
        public static final String PAY_ZFB = "0201";
        //微信APP支付
        public static final String PAY_WX_APP = "0202";
        //购物金币支付
        public static final String PAY_SHOP = "0203";
        //答题金币支付
        public static final String PAY_ANSWER = "0204";
        //充值金币支付
        public static final String PAY_RECHARGE = "0205";
        //钻石支付
        public static final String PAY_DIAMONDS = "0206";
        //用户余额支付-充值
        public static final String PAY_BALANCE = "0207";
        //微信公众号支付
        public static final String PAY_WXGZH = "0208";
        //用户自身资产
        public static final String PAY_ASSET = "0209";
    }

    /**
     * Intent RequestCode
     */
    public class RequestCode {
        //注册
        public static final int REGISER = 0x1001;
        //支付
        public static final int PAY = 0x1002;
        //支付选择
        public static final int PAY_TYPE = 0x1003;
        //金币交易
        public static final int TRANSACTION = 0x1004;
    }

    /**
     * Intent ResultCode
     */
    public class ResultCode {
        //失败
        public static final int FAIL = 0x1F01;
    }

    /**
     * Intent key
     */
    public class IntentKey {
        public static final String ACCOUNT = "account";
        public static final String BEAN = "bean";
        public static final String STRING = "string";
        public static final String LABEL_ID = "label_id";
        public static final String TITLE = "title";
        public static final String TYPE = "type";
        public static final String PRICE = "price";
        public static final String INDEX = "index";
        public static final String DOUBLE = "double";
    }

    public class HttpType {

        //猜题
        public static final String ANSWER = "2";
        //记忆答题
        public static final String MEMORY = "1";
        public static final int wID = 1;
    }

    public class DefaultValue {
        public static final String GUIDE_VER = "1";
    }

    public class SharedKey {
        public static final String GUIDE_MASK = "guide_mask";
    }

    public class Tip {
        public static final String DEVELOPMENT = "程序猿玩命开发中";
    }

    /**
     * 获取用户店长等级的方法
     *
     * @param isOrdinaryMember
     * @return
     */
    public static String getMemberGradeInfo(int isOrdinaryMember) {
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

    public class WithdrawType {


        public static final String CARD = "card";
        public static final String AIL_PAY = "zfb";
        public static final String WECHAT = "wechat";
    }

    public class UpdateCode {
        public static final int ADD_SELL = 0;
        public static final int ADD_BUY = 1;
        public static final int BUY_SUCCESS = 2;
        public static final int SELL_SUCCESS = 3;
    }

    public class HttpCode {
        //答题金币不足
        public static final String ANSWER_GOLD = "101";
        //钱包余额不足
        public static final String PAY_MONEY = "102";
        //交易限制
        public static final String PAY_LIMIT = "103";
    }
}
