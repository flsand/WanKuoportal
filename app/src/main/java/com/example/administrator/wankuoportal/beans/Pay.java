package com.example.administrator.wankuoportal.beans;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class Pay {

    /**
     * code : 0
     * data : partner="2088721769406729"&seller_id="2377597260@qq.com"&out_trade_no="Z20171012104517081"&subject="万阔-服务"&body="zorderid(Z20171012104517081)"&total_fee="5800.00"&notify_url="http://www.wankuo5888.com/wankuoportal/notify/zfb"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&return_url="m.alipay.com"&sign=IRWlZhBxX5x1Cip9oMSk9Mp1Yn3V14ha+V12PuyC35FTR0m6+a8vwdFrmR2cD0LU8Gb75igBMPCdXHZjJbZxiuw5oulSnE07TpXKSWUaHs+0J6qK+JU6YFd4GSUq9xVzMIjipb/nu+FJEg3x0a9DLHvfvSEaFiSlIPqtmunqDNw=
     * msg : 添加成功
     */

    private int code;
    private String data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
