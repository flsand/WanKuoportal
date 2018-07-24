package com.example.administrator.wankuoportal.beans;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class PayReturn {

    /**
     * code : 0
     * data : {"body":"zorderid(Z20171011171359037)","outTradeNo":"Z20171011171359037","partner":"2088721769406729","price":"5800.00","seller":"2377597260@qq.com","sign":"kHUhA1%2FgD2YcUsrmNOUiPcxYfyfI%2FUSEl2uGeCQy5NK45MuMoHZtrEg1Z7FODeNoS8CtDjf6W%2BjG05PIVOkpJA354vhJYZJbRSd38GzNDQenaUdLarBpGyuTdxRZuq%2ByaCLo3Xju1%2BYFyDq%2BKAT93%2F8qFq1Ff%2BvgZ35oQaX5%2F9NoVQvqIqVXS4%2Ft9ipt2FPvBDy7alNPGMDTtW54g0wd0qYlJOapp3R%2BGytq825Wym%2B35%2FAeF2AsoDPm6ro%2Fe5qTetaSnRndX15CJMWHt2ZmY4UmpBcb7%2B4q0BCMkmcexUPfgG7uY5MLjEqzwSVtivOs4UaGVkDtZ7M9JEO4wWuyVg%3D%3D","subject":"万阔-服务","url":"http://www.wankuo5888.com/wankuoportal/notify/zfb"}
     * msg : 添加成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * body : zorderid(Z20171011171359037)
         * outTradeNo : Z20171011171359037
         * partner : 2088721769406729
         * price : 5800.00
         * seller : 2377597260@qq.com
         * sign : kHUhA1%2FgD2YcUsrmNOUiPcxYfyfI%2FUSEl2uGeCQy5NK45MuMoHZtrEg1Z7FODeNoS8CtDjf6W%2BjG05PIVOkpJA354vhJYZJbRSd38GzNDQenaUdLarBpGyuTdxRZuq%2ByaCLo3Xju1%2BYFyDq%2BKAT93%2F8qFq1Ff%2BvgZ35oQaX5%2F9NoVQvqIqVXS4%2Ft9ipt2FPvBDy7alNPGMDTtW54g0wd0qYlJOapp3R%2BGytq825Wym%2B35%2FAeF2AsoDPm6ro%2Fe5qTetaSnRndX15CJMWHt2ZmY4UmpBcb7%2B4q0BCMkmcexUPfgG7uY5MLjEqzwSVtivOs4UaGVkDtZ7M9JEO4wWuyVg%3D%3D
         * subject : 万阔-服务
         * url : http://www.wankuo5888.com/wankuoportal/notify/zfb
         */

        private String subject; //"一健康-药品";
        private String sign;//签名
        private String partner;// 商户PID
        private String seller;// 商户收款账号
        private String body;//商品详细描述
        private String outTradeNo;//商户订单号
        private String price;//价格;
        private String url;//支付宝通知服务器地址

        public void setUrl(String url) {
            this.url = url;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public void setPartner(String partner) {
            this.partner = partner;
        }

        public void setSeller(String seller) {
            this.seller = seller;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        private String getSignType() {
            return "sign_type=\"RSA\"";
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        @Override
        public String toString() {
            return getOrderInfo() + "&sign=\"" + sign + "\"&" + getSignType();
        }

        /**
         * 创建订单信息
         */
        private String getOrderInfo() {

            // 签约合作者身份ID
            String orderInfo = "partner=" + "\"" + partner + "\"";

            // 签约卖家支付宝账号
            orderInfo += "&seller_id=" + "\"" + seller + "\"";

            //app_id
//            orderInfo += "&appid_id=" + "\"2017082308341420\"";

            // 商户网站唯一订单号
            orderInfo += "&out_trade_no=" + "\"" + outTradeNo + "\"";

            // 商品名称
            orderInfo += "&subject=" + "\"" + subject + "\"";

            // 商品详情
            orderInfo += "&body=" + "\"" + body + "\"";

            // 商品金额
            orderInfo += "&total_fee=" + "\"" + price + "\"";

            // 服务器异步通知页面路径
            orderInfo += "&notify_url=" + "\"" + url + "\"";

            // 服务接口名称， 固定值
            orderInfo += "&service=\"mobile.securitypay.pay\"";

            // 支付类型， 固定值
            orderInfo += "&payment_type=\"1\"";

            // 参数编码， 固定值
            orderInfo += "&_input_charset=\"utf-8\"";

            // 设置未付款交易的超时时间
            // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
            // 取值范围：1m～15d。
            // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
            // 该参数数值不接受小数点，如1.5h，可转换为90m。
            orderInfo += "&it_b_pay=\"30m\"";

            // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
            // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

            // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
            orderInfo += "&return_url=\"m.alipay.com\"";

            // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
            // orderInfo += "&paymethod=\"expressGateway\"";\


//            String orderInfo = "app_id=" + "\" 2017082308341420\"";
//
//
//            orderInfo += "&biz_content"



            return orderInfo;
        }
    }
}
