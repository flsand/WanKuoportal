package com.example.administrator.wankuoportal.aaPackage.bean;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/24.
 *     desc    :
 * </pre>
 */
public class MessageBean extends DBVO {
    /**
     * id : 10
     * nacigationQuestion : 测试公告1
     * nacigationTypeId : 8
     */

    private String id;
    private String nacigationQuestion;
    private int nacigationTypeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNacigationQuestion() {
        return nacigationQuestion;
    }

    public void setNacigationQuestion(String nacigationQuestion) {
        this.nacigationQuestion = nacigationQuestion;
    }

    public int getNacigationTypeId() {
        return nacigationTypeId;
    }

    public void setNacigationTypeId(int nacigationTypeId) {
        this.nacigationTypeId = nacigationTypeId;
    }
}
