package com.example.administrator.wankuoportal.beans;

import com.flysand.mylibrary.sqlhelper.DBVO;

import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/18.
 *     desc    :
 * </pre>
 */
public class MerchantsBean extends DBVO{


    /**
     * bj : http://www.wankuo5888.com/img/downloadimage1/6853678dcc6044c687f430a34409c48f
     * clickType : 7
     * id : 1
     * pId : -1
     * text : 商务服务
     * nodes : [{"bj":"http://www.wankuo5888.com/img/downloadimage1/aee74b5edcd049b18cbd1a955d7b8356","clickType":"7","id":2776,"nodes":[{"bj":"","clickType":"7","id":2777,"pId":2776,"text":"工商注册"}]}]
     */

    private String bj;
    private String clickType;
    private String id;
    private String pId;
    private String text;
    private List<NodesBeanX> nodes;

    public String getBj() {
        return bj;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }

    public String getClickType() {
        return clickType;
    }

    public void setClickType(String clickType) {
        this.clickType = clickType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<NodesBeanX> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodesBeanX> nodes) {
        this.nodes = nodes;
    }

    public static class NodesBeanX extends DBVO{
        /**
         * bj : http://www.wankuo5888.com/img/downloadimage1/aee74b5edcd049b18cbd1a955d7b8356
         * clickType : 7
         * id : 2776
         * nodes : [{"bj":"","clickType":"7","id":2777,"pId":2776,"text":"工商注册"}]
         */

        private String bj;
        private String clickType;
        private String id;
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getBj() {
            return bj;
        }

        public void setBj(String bj) {
            this.bj = bj;
        }

        public String getClickType() {
            return clickType;
        }

        public void setClickType(String clickType) {
            this.clickType = clickType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
