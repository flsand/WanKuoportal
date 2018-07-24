package com.example.administrator.wankuoportal.util;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/18.
 *     desc    : 分页助手
 * </pre>
 */
public class PagingHelper {

    public boolean isLoading = false;
    public boolean canLoadMore = true;
    public int page = 1;

    public void init() {
        isLoading = false;
        canLoadMore = true;
        page = 1;
    }

    public void setLoading() {
        isLoading = true;
    }

    public void loadFoot() {
        canLoadMore = false;
    }

    public void loadCompleted() {
        page++;
        isLoading = false;
    }

    public void loadFailure() {
        isLoading = false;
    }
}
