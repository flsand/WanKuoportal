package com.example.administrator.wankuoportal.guide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.coustomView.MaskBean;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.util.BitmapUtil;
import com.flysand.mylibrary.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     pagckage:com.example.administrator.wankuoportal.guide
 *     author  : Administrator
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/15.
 *     desc    :
 *
 * </pre>
 */
public class GuideUtil {

    boolean isViewLoad = false;

    private Bitmap getBitmapForRes(Context context, int res, int dpWidth) {
        return BitmapUtil.fitBitmap(BitmapFactory.decodeResource(context.getResources(), res), Utils.dip2px(context, dpWidth));
    }


    public void initGuide(Activity context, View searchLayout, View left, View right) {

        List<MaskBean> beans = new ArrayList<>();

        final GuideHelper guideHelper = new GuideHelper(context, beans);
//        getLocationOnScreen
        int[] leftLocation = new int[2];
        left.getLocationInWindow(leftLocation);//获取在整个屏幕内的绝对坐标
        int[] rightLocation = new int[2];
        right.getLocationInWindow(rightLocation);//获取在整个屏幕内的绝对坐标

        MaskBean bean = new MaskBean();
        bean.setRectF(new RectF(leftLocation[0], leftLocation[1], leftLocation[0] + left.getWidth(), leftLocation[1] + left.getHeight()));
//        bean.setRectF(new RectF(10,10,100,100));
        bean.setTransparent(true);
        bean.setRadius(10);
        bean.setStyle(com.example.administrator.wankuoportal.coustomView.MaskView.CIRCLE);
        Bitmap bitmap = getBitmapForRes(context, R.drawable.guide_release, 150);
        bean.setBitmap(bitmap);
        bean.setBitmapLeft(leftLocation[0] - bitmap.getWidth());
        bean.setBitmapTop(leftLocation[1] - bitmap.getHeight());
        beans.add(bean);

        MaskBean bean2 = new MaskBean();
        int disDp = Utils.dp2px(context, 12);
        bean2.setRectF(new RectF(rightLocation[0] + disDp, rightLocation[1] + disDp, rightLocation[0] + right.getWidth() - disDp, rightLocation[1] + right.getHeight() - disDp));
        bean2.setTransparent(true);
        bean2.setRadius(10);
        bean2.setStyle(com.example.administrator.wankuoportal.coustomView.MaskView.CIRCLE);
        Bitmap bitmap2 = getBitmapForRes(context, R.drawable.guide_make_money, 170);
        bean2.setBitmap(bitmap2);
        bean2.setBitmapLeft(rightLocation[0] - bitmap2.getWidth() / 4);
        bean2.setBitmapTop(rightLocation[1] - bitmap2.getHeight());
        beans.add(bean2);

        searchLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!isViewLoad) {
                    isViewLoad = true;
                    int[] searchLocation = new int[2];
                    searchLayout.getLocationInWindow(searchLocation);//获取在整个屏幕内的绝对坐标
                    MaskBean bean3 = new MaskBean();
                    bean3.setRectF(new RectF(searchLocation[0], searchLocation[1], searchLocation[0] + searchLayout.getWidth(), searchLocation[1] + searchLayout.getHeight()));
                    bean3.setTransparent(true);
                    bean3.setStyle(com.example.administrator.wankuoportal.coustomView.MaskView.ROUNDRECT);
                    bean3.setRadius(searchLayout.getHeight() / 2);
                    bean3.setBitmap(getBitmapForRes(context, R.drawable.guide_serch, 150));
                    bean3.setBitmapLeft(searchLocation[0] + searchLayout.getWidth() / 3);
                    bean3.setBitmapTop(searchLocation[1] + searchLayout.getHeight() + Utils.dpToPx(context, 5));
                    beans.add(bean3);

                    MaskBean bean4 = new MaskBean();
                    Bitmap bitmap4 = getBitmapForRes(context, R.drawable.guide_confirm, 160);
                    bean4.setBitmap(bitmap4);
                    bean4.setBitmapTop(context.getResources().getDisplayMetrics().heightPixels / 2);
                    bean4.setBitmapLeft(context.getResources().getDisplayMetrics().widthPixels / 2 - bitmap4.getWidth() / 2);
                    bean4.setListener(() -> {
                        Utils.print("onMaskViewBitmapClick");
                        guideHelper.dismiss();
                        Utils.saveData(context, Constant.SharedKey.GUIDE_MASK, Constant.DefaultValue.GUIDE_VER);
                    });
                    beans.add(bean4);
                    guideHelper.show();
                }
            }
        });
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aboutus);


    }
}
