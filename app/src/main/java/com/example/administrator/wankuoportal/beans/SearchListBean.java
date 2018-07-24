package com.example.administrator.wankuoportal.beans;

import com.flysand.mylibrary.sqlhelper.DBVO;

import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/19.
 *     desc    :
 * </pre>
 */
public class SearchListBean extends DBVO {


    /**
     * sdlist : [[1,"科信软件","专注于软件开发，APP开发，小程序开发",103,"山东省","烟台市",0,0.01,"4da2bb1033254c6781f7e07a96f3a781"],[3,"紫熙商贸公司","化妆品服务,网站建设，APP开发，小程序开发，快速建站",101,"辽宁省","营口市",0,0.01,"d7952fe8bb2a461783e187b523c89d74"],[1,"烟台野生海参","卖野生海参、即使海参、淡干海参",1,"山东省","烟台市",0,null,"3c6b9977fd4940fb8f8bbed234d9f96b"],[1,"北京快传同城","网站开发、APP开发、小程序开发、模板建站",1,"北京市","北京市",100,null,"2c8802e2a88e455ab3e3ef8b1f44aa9e"]]
     * aglist : [[128000,"","500fa334e413492ba3c61624d4caa89f",0,"科信软件","山东省","烟台市"],[3860,"企业网站建设网站制作网站设计网站定制开发网站开发建站光荣网络","31094b4196c64879a2d943e06d4805e5",0,"北京快传同城","北京市","北京市"],[12800,"六合一网站建站 pc网站+手机网站+微信网站+APP+分销+营销","2a9687e3a41a4e9795c42186f2f904f8",0,"快传传媒","山东省","烟台市"],[12800,"","9e61f89a99b0412cba093c02394b5f0a",0,"快传传媒","山东省","烟台市"],[3000,"三合一行业网站定制 手机网站+电脑网站+微信网站","7f88567861cb44aba96d222a4f6fed1c",0,"快传传媒","山东省","烟台市"]]
     * code : 0
     * msg : 查询成功！
     * labelId : 1201
     */

    private int code;
    private String msg;
    private int labelId;
    private List<List<String>> sdlist;
    private List<List<String>> aglist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public List<List<String>> getAglist() {
        return aglist;
    }

    public void setAglist(List<List<String>> aglist) {
        this.aglist = aglist;
    }

    public List<List<String>> getSdlist() {
        return sdlist;
    }

    public void setSdlist(List<List<String>> sdlist) {
        this.sdlist = sdlist;
    }
}
