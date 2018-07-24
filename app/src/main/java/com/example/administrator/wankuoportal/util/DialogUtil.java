package com.example.administrator.wankuoportal.util;

import android.app.Activity;
import android.view.View;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/5.
 *     desc    :
 *
 * </pre>
 */
public class DialogUtil {

    public static void showMessage(Activity activity, String msg) {

        com.flysand.mylibrary.customView.DialogUtil dialogUtil = new com.flysand.mylibrary.customView.DialogUtil(activity) {
            @Override
            public void confirmClick(View view) {
            }
        }.setContent(msg);
        dialogUtil.showDialog(1, 0);
    }

}
