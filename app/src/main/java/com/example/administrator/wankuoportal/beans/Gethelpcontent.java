package com.example.administrator.wankuoportal.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class Gethelpcontent implements Serializable{

    /**
     * code : 0
     * datas : [{"createTime":"2017-09-10 14:57:30","helpAnswerApp":"<p><img src=\"tupianurl/e45942c2d3f64ea6a0e15cadd5e65b20\" title=\"690.jpg\" alt=\"690.jpg\"/><img src=\"tupianurl/cee59818d6ec4c94959b094b5df66d1d\" title=\"weihuzizhiyuantu.jpg\" alt=\"weihuzizhiyuantu.jpg\"/>qweqweqw<br/><\/p>","helpAnswerPc":"<p>qwqwe<img src=\"tupianurl/8450698d386e40309c1f0b2a64f633e4\" title=\"690.jpg\" alt=\"690.jpg\"/><\/p>","helpQuestion":"加盟条件","helpTypeId":2,"id":2},{"createTime":"2017-09-10 14:57:30","helpAnswerApp":"<p><img src=\"tupianurl/e45942c2d3f64ea6a0e15cadd5e65b20\" title=\"690.jpg\" alt=\"690.jpg\"/><img src=\"tupianurl/cee59818d6ec4c94959b094b5df66d1d\" title=\"weihuzizhiyuantu.jpg\" alt=\"weihuzizhiyuantu.jpg\"/>qweqweqw<br/><\/p>","helpAnswerPc":"<p>加盟需要维护资质<img src=\"tupianurl/ec330b2e1e9f4a8faa6c40f4220c04cc\" title=\"weihuzizhiyuantu.jpg\" alt=\"weihuzizhiyuantu.jpg\"/><\/p>","helpQuestion":"加盟流程","helpTypeId":2,"id":3}]
     */

    private int code;
    private List<DatasBean> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean implements Serializable{
        /**
         * createTime : 2017-09-10 14:57:30
         * helpAnswerApp : <p><img src="tupianurl/e45942c2d3f64ea6a0e15cadd5e65b20" title="690.jpg" alt="690.jpg"/><img src="tupianurl/cee59818d6ec4c94959b094b5df66d1d" title="weihuzizhiyuantu.jpg" alt="weihuzizhiyuantu.jpg"/>qweqweqw<br/></p>
         * helpAnswerPc : <p>qwqwe<img src="tupianurl/8450698d386e40309c1f0b2a64f633e4" title="690.jpg" alt="690.jpg"/></p>
         * helpQuestion : 加盟条件
         * helpTypeId : 2
         * id : 2
         */

        private String createTime;
        private String helpAnswerApp;
        private String helpAnswerPc;
        private String helpQuestion;
        private int helpTypeId;
        private int id;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getHelpAnswerApp() {
            return helpAnswerApp;
        }

        public void setHelpAnswerApp(String helpAnswerApp) {
            this.helpAnswerApp = helpAnswerApp;
        }

        public String getHelpAnswerPc() {
            return helpAnswerPc;
        }

        public void setHelpAnswerPc(String helpAnswerPc) {
            this.helpAnswerPc = helpAnswerPc;
        }

        public String getHelpQuestion() {
            return helpQuestion;
        }

        public void setHelpQuestion(String helpQuestion) {
            this.helpQuestion = helpQuestion;
        }

        public int getHelpTypeId() {
            return helpTypeId;
        }

        public void setHelpTypeId(int helpTypeId) {
            this.helpTypeId = helpTypeId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
