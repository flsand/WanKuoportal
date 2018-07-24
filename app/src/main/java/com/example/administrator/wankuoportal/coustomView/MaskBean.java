package com.example.administrator.wankuoportal.coustomView;

import android.graphics.Bitmap;
import android.graphics.RectF;

import com.flysand.mylibrary.sqlhelper.DBVO;
import com.flysand.mylibrary.util.Utils;

/**
 * <pre>
 *     author  : Administrator
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/9.
 *     desc    :
 *
 * </pre>
 */
public class MaskBean extends DBVO {

    //
    private RectF rectF;
    //圆角率
    private float radius;
    //是否透明
    private boolean isTransparent;
    private RectF clickRectF;
    private Bitmap bitmap;
    private float bitmapLeft;
    private float bitmapTop;
    private int style = MaskView.ROUNDRECT;
    private onMaskViewBitmapClickListener listener;

    public float getBitmapLeft() {
        return bitmapLeft;
    }

    public void setBitmapLeft(float bitmapLeft) {
        this.bitmapLeft = bitmapLeft;
    }

    public float getBitmapTop() {
        return bitmapTop;
    }

    public void setBitmapTop(float bitmapTop) {
        this.bitmapTop = bitmapTop;
    }

    public void setListener(onMaskViewBitmapClickListener listener) {
        this.listener = listener;
        if (rectF != null) {
            clickRectF = rectF;
        } else if (bitmap != null) {
            clickRectF = new RectF(bitmapLeft, bitmapTop, bitmapLeft + bitmap.getWidth(), bitmapTop + bitmap.getHeight());
        }
    }

    public RectF getClickRectF() {
        return clickRectF;
    }

    public onMaskViewBitmapClickListener listener() {
        return listener;
    }

    public int getStyle() {
        return style;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public RectF getRectF() {
        return rectF;
    }

    public void setRectF(RectF rectF) {
        Utils.print("rectF =" + rectF.toString());
        this.rectF = rectF;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public boolean isTransparent() {
        return isTransparent;
    }

    public void setTransparent(boolean transparent) {
        isTransparent = transparent;
    }

}
