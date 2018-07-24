package com.example.administrator.wankuoportal.aaPackage.bean;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/29.
 *     desc    :
 * </pre>
 */
public class JudgeMoneyBean extends DBVO {
    /**
     * flag : true
     * info : {"nolimitGold":6500,"answerGold":0,"Diamonds":0}
     */

    private boolean flag;
    private InfoBean info;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean extends DBVO {
        /**
         * nolimitGold : 6500.0
         * answerGold : 0.0
         * Diamonds : 0
         */

        private double nolimitGold;
        private double answerGold;
        private double Diamonds;

        public double getNolimitGold() {
            return nolimitGold;
        }

        public void setNolimitGold(double nolimitGold) {
            this.nolimitGold = nolimitGold;
        }

        public double getAnswerGold() {
            return answerGold;
        }

        public void setAnswerGold(double answerGold) {
            this.answerGold = answerGold;
        }

        public double getDiamonds() {
            return Diamonds;
        }

        public void setDiamonds(double Diamonds) {
            this.Diamonds = Diamonds;
        }
    }
}
