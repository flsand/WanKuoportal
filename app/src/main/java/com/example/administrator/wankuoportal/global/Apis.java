package com.example.administrator.wankuoportal.global;


import com.example.administrator.wankuoportal.AppBuildConfig;

/**
 * Created by lv on 2017/04/19 17:09
 */

public interface Apis {

    String Base = AppBuildConfig.BASE_URL;//
    //交易
    String deal = AppBuildConfig.TRANSACTION_URL;//

    String Baseima = AppBuildConfig.IMG_URL;
//    String Baseima = "http://www.wankuo5888.com/img/downloadimage/2ffc94614fab4d11b31bbd27de5246f9";

    String REGISTER_SEND = "/api/shortmessage/registersend";

    String send = "/api/shortmessage/send";

    String getonetwolabel = "/api/label/getonetwolabel";

    String getLabel = "/api/label/getLabel";

    String regist = "/api/userinfo/regist";

    String updatepwd = "/api/userinfo/updatepwd";
    //  消息未读数量
    String assetSelect = "/api/Message/assetSelect";
    //  消息未读数量
    String commonSelect = "/api/Message/commonSelect";

    String login = "/api/userinfo/login";

    String updateuserinfo = "/api/auth/userinfo/updateuserinfo";

    String getbyname = "/api/dictionary/getbyname";
    //升级店长需要的钱
    String getNeedMoney = "/api/userinfo/getNeedMoney";
    String updateheadicon = "/api/auth/userinfo/updateheadicon";

    String returnquestion = "/api/auth/prizeanswer/returnquestion";

    String findlabelbyparentid = "/api/label/findlabelbyparentid?parentId=";

    String gethelpcenter1 = "/api/helpcenter/gethelpcenter?type=1";

    String gethelpcenter2 = "/api/helpcenter/gethelpcenter?type=2";

    String gethelpcontent = "/api/helpcenter/gethelpcontent?centerid=";

    String signin = "/api/auth/userinfo/signin";

    String getmyinvitation = "/api/auth/userinfo/getmyinvitation";

    String getquestion = "/api/auth/prizeanswer/getquestion";

    String getranking = "/api/auth/prizeanswer/getranking";

    String getrankingall = "/api/prizeanswer/getrankingall";

    String getmyquestion = "/api/auth/prizeanswer/getmyquestion";

    String checksignin = "/api/auth/userinfo/checksignin";

    String searchserve = "/api/search/searchserve";

    //服务商数据
    String searchserveprovider = "/api/search/searchserveprovider";
    //分类 - > 店铺
    String getGoodShop = "/api/shopGood/getGoodShop";

    String getuserinfo = "/api/auth/userinfo/getuserinfo";
    //答题权限
    String apiAnswer = "/api/auth/apiAnswer";

    String getmyquestionrecord = "/api/auth/prizeanswer/getmyquestionrecord";

    String getshare = "/api/auth/getshare";

    String getmysignin = "/api/auth/userinfo/getmysignin";
    //金币明细
    String getmygoldpage = "/api/auth/userinfo/getmygoldpage";

    String getnavigation = "/api/getnavigation";

    String searchexchangegoods = "/api/search/searchexchangegoods";

    String getexchangegoods = "/api/search/getexchangegoods";
    //
    String add = "/api/auth/mailingaddress/add";
    //添加修改收货地址
    String addOrUpdateAddress = "/api/auth/address/addorupdateaddress";
    //收货地址列表，第一个是默认收货地址
    String queryAddress = "/api/auth/address/tomyaddress";
    //修改默认收货地址
    String defaultAddress = "/api/auth/address/defaultAddress";
    //删除收货地址
    String deladdress = "/api/auth/address/delAddress";

//    String query = "/api/auth/mailingaddress/query";

    String updataaddress = "/api/auth/mailingaddress/updata";
    //检查区域
    String checkarea = "/api/auth/userinfo/checkarea";

    //微信充值
    String weChatRechange = "/api/auth/user/recharge/wx";

    //支付宝充值
    String aliPayRechange = "/api/auth/user/recharge/zfb";

    /**
     * 支付宝支付
     */
    String zfb = "/api/auth/order/add/zfb";

    String demandZfb = "/api/auth/order/add/demandZfb";
    /**
     * 微信支付
     */
    String wx = "/api/auth/order/add/wx";

    /**
     * 钱包支付
     */
    String qianbao = "/api/auth/order/add/goldrecharge";

    String demandWx = "/api/auth/order/add/demandWx";

    String getandroid = "/api/appinfo/getandroid";

    String partner = "/api/auth/partner/add/partner";

    String getbankcard = "/api/auth/bankcard/get";

    String addbankcard = "/api/auth/bankcard/add";

    String delbankcard = "/api/auth/bankcard/del";

    String initshop = "/api/shop/init";

    //提现
    String withdrawals = "/api/auth/wallet/withdrawals";
    //提现验证
    String checkWithdraw = "/api/auth/wallet/checkWithdraw";
    //兑换余额成金币
    String togold = "/api/auth/exchange/moneyToGold";
    //    String togold = "/api/auth/wallet/togold";
    //金币兑换余额
//    String towallet = "/api/auth/wallet/towallet";
    String towallet = "/api/auth/exchange/goldToMoney";
    //获取金币
    String getmygold = "/api/auth/userinfo/getmygold";

    String addorder = "/api/auth/exchange/addorder";

    //下单
    String buyGoods = "/api/auth/order/buy_goods";
    //购买付款
    String payOrder = "/api/auth/order/payOrder";
    // 商城购买检查余额是否够用
    String checkPay = "/api/auth/order/judgeMoney";

    String getOrderDetail = "/api/auth/order/getOrderDetail";

    String getorder = "/api/auth/exchange/getorder";

    //我的钱包->交易明细
//    String getmywalletpage = "/api/auth/wallet/getmywalletpage";
    String getmywalletpage = "/api/Message/rechargeInfo";

    String xuqiupartner = "/api/auth/publishDemand/add/partner";

    String partnerFile = "/api/auth/publishDemand/add/partnerFile";

    String getxinwengonggao = "/api/helpcenter/getnavigation";

    String getnavigationdetail = "/api/helpcenter/getnavigationdetail";

    String information = "/api/shop/information";

    String shopaboutus = "/api/shop/aboutus";

    String shopcertificate = "/api/shop/certificate";

    String shopdefined = "/api/shop/defined";

    String getshareimg = "/api/auth/getshareimg";

    String shopexample = "/api/shop/example";

    String shopgoods = "/api/shop/goods";

    String shopexampledetails = "/api/shop/example/";

    String shopgoodsdetails = "/api/shop/goods/";

    String savefeedback = "/api/auth/userinfo/savefeedback";

    String gettopline = "/api/helpcenter/gettopline";

    String saveidnumber = "/api/auth/userinfo/saveidnumber";
    //营销学院
    String second = "/system/api/information/second";

    String getdetail = "/system/api/information/getdetail";

    String pushinfofindall = "/api/pushinfo/findall";

    String pushinfoupdate = "/api/pushinfo/update";

    String getindexserve = "/api/search/getindexserve";

    String collection = "/api/auth/collection/collection";

    String collectionshare = "/api/collection/share";

    String getorderid = "/system/getorderid";

    String mineTeamListUrl = "/api/auth/myteam/details";
    String mineTeamStatsUrl = "/api/auth/myteam/stats";
    String SmallStoreUrl = "/system/wx/goods/searchmyshopgoodsgo";
    String SmallStoreRecommendUrl = "/system/wx/goods/searchmyshopgoodsgogroom";

    String getRegistCount = "/api/appinfo/getRegist";

    //分类详情 ->商品
    String getGood = "/api/shopGood/getGood";
    //分类详情 -> 店铺
    String getShop = "/api/shopGood/getShop";
    //轮播图
    String getBannerUrl = "/api/getAppAdvi";
    //累计广告投放
    String getAdCount = "/api/getAppAdviNum";

    /**
     * 交易
     */
    interface Transaction {
        //我要买
        String getSellData = deal + "/api/getAllSell";
        //我要卖
        String getBuyData = deal + "/api/getAllBuy";
        //最新单价
        String getLatestPrice = deal + "/api/gold/getPrice";
        //发布收购金币
        String addBuy = deal + "/api/addBuy";
        //发布出售金币
        String addSell = deal + "/api/addSell";
        //修改出售金币价格
        String upUnitPrice = deal + "/api/upUnitPrice";
        //购买答题金币
        String addSellInfo = deal + "/api/addSellInfo";
        //我要卖->出售答题金币
        String addBuyInfo = deal + "/api/addBuyInfo";
        //交易明细
        String getDealInfo = deal + "/api/getDealInfo";
        //交易统计信息
        String getDealReport = deal + "/api/getDealReport";
        //获取服务器系统时间
        String getServerTime = deal + "/api/getServerTime";
        //获取交易限额
        String getDateGOld = deal + "/api/getDateGOld";
    }
}
