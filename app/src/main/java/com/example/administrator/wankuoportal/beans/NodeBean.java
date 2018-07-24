package com.example.administrator.wankuoportal.beans;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/18.
 *     desc    :
 * </pre>
 */
public class NodeBean extends DBVO {


    /**
     * cancel : 0
     * id : 1
     * img : 6853678dcc6044c687f430a34409c48f
     * labelName : 商务服务
     * parentId : -1
     * root : 0
     */

    private String id;
    private String img;
    private String labelName;
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
