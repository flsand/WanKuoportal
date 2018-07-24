/*
Copyright 2016 shizhefei（LuckyJayce）

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.example.administrator.wankuoportal.guide;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.WindowManager;

import com.example.administrator.wankuoportal.coustomView.MaskBean;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.util.Utils;

import java.util.List;

/**
 * <pre>
 *     author  : Administrator
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/9.
 *     desc    :
 *
 * </pre>
 */
public class GuideHelper {

    private Activity activity;
    List<MaskBean> maskBeans;
    public OnDismissListener onDismissListener;
    MaskView maskView;
    private Dialog baseDialog;

    public GuideHelper(Activity activity, List<MaskBean> maskBeans) {
        this.activity = activity;
        this.maskBeans = maskBeans;
    }

    public GuideHelper addMask(MaskBean bean) {
        maskBeans.add(bean);
        maskView.invalidate();
        return this;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }


    /**
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public GuideHelper show() {
        if (maskBeans == null || maskBeans.size() < 1)
            throw new RuntimeException("maskBeans Must not be empty");
        //关闭dialog，移除handler消息
        dismiss();
        int parentY = 0;
        //创建dialog
        baseDialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_DialogWhenLarge_NoActionBar);
        baseDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = baseDialog.getWindow().getAttributes();
            localLayoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            localLayoutParams.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        } else {
            parentY = ProjectUtil.getStatusHeight(activity);
            Utils.print("通知栏的高度 ="+parentY);
        }
        maskView = new MaskView(activity, maskBeans,parentY);
        baseDialog.setContentView(maskView);
        //设置dialog的窗口大小全屏
        baseDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        //dialog关闭的时候移除所有消息
        baseDialog.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                if (onDismissListener != null) {
                    baseDialog.setOnDismissListener(onDismissListener);
                }
            }
        });
        //显示
        baseDialog.show();
        return this;
    }


    public void dismiss() {
        if (baseDialog != null)
            baseDialog.dismiss();
        baseDialog = null;
    }

}
