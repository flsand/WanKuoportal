package com.example.administrator.wankuoportal.guide;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wankuoportal.coustomView.MaskBean;
import com.flysand.mylibrary.util.Utils;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * <pre>
 *     author  : Administrator
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/9.
 *     desc    :
 *
 * </pre>
 */
class MaskView extends ViewGroup {

    private final RectF mFullingRect = new RectF();
    private final Paint mFullingPaint = new Paint();
    private boolean mOverlayTarget;
    private int mCorner = 0;
    private Paint mEraser;
    private Paint bitmapPrint;

    private Paint mPaint;
    private Paint transparentPaint;
    /**
     * 圆角矩形&矩形
     */
    public final static int ROUNDRECT = 0;

    /**
     * 圆形
     */
    public final static int CIRCLE = 1;
    List<MaskBean> maskBeans;

    private float statusBarHeight = 0;

    public MaskView(Context context, List<MaskBean> maskBeans, float statusBarHeight) {
        this(context);
        this.maskBeans = maskBeans;
        this.statusBarHeight = statusBarHeight;
    }

    public MaskView(Context context) {
        this(context, null);
    }

    public MaskView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaskView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setWillNotDraw(false);
        Point size = new Point();
        size.x = getResources().getDisplayMetrics().widthPixels;
        size.y = getResources().getDisplayMetrics().heightPixels;

        mPaint = new Paint();
        mPaint.setColor(0xcc000000);
        transparentPaint = new Paint();
        transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
        transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        transparentPaint.setAntiAlias(true);

        mFullingPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        mFullingPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mFullingPaint.setAntiAlias(true);


        mEraser = new Paint();
        mEraser.setColor(0xFFFFFFFF);
        mEraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        mEraser.setFlags(Paint.ANTI_ALIAS_FLAG);
        mEraser.setAntiAlias(true);

        bitmapPrint = new Paint();
        bitmapPrint.setColor(0xFFFFFFFF);
        bitmapPrint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        bitmapPrint.setFlags(Paint.ANTI_ALIAS_FLAG);
        bitmapPrint.setAntiAlias(true);

        setFullingColor(0x99000000);
    }


    public void setMaskBeans(List<MaskBean> maskBeans) {
        this.maskBeans = maskBeans;
        invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            clearFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Utils.print("onlayout " + " left =" + l + "    t= " + t + "    r= " + r + "    b= " + b);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int w = MeasureSpec.getSize(widthMeasureSpec);
        final int h = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(w, h);
        mFullingRect.set(0, 0, w, h);
        final int count = getChildCount();
        View child;
        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            if (child != null) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp == null) {
                    child.setLayoutParams(lp);
                }
                measureChild(child, w + MeasureSpec.AT_MOST, h + MeasureSpec.AT_MOST);
            }
        }
    }


    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        final long drawingTime = getDrawingTime();
        try {
            View child;
            for (int i = 0; i < getChildCount(); i++) {
                child = getChildAt(i);
                drawChild(canvas, child, drawingTime);
            }
        } catch (NullPointerException e) {

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mFullingRect, mFullingPaint);
        if (!mOverlayTarget) {
            for (int i = 0; i < maskBeans.size(); i++) {
                MaskBean maskBean = maskBeans.get(i);
                if (maskBean.getBitmap() != null) {
                    canvas.drawBitmap(maskBean.getBitmap(), maskBean.getBitmapLeft(), maskBean.getBitmapTop() - statusBarHeight, bitmapPrint);
                }
                if (maskBean.getRectF() != null) {
                    if (maskBean.isTransparent()) {
                        drawRectF(canvas, maskBean, transparentPaint);
                    } else {
                        drawRectF(canvas, maskBean, mEraser);
                    }
                }
            }
        }
    }

    private RectF changeRectF(RectF rectF) {
        rectF.set(rectF.left, rectF.top - statusBarHeight, rectF.right, rectF.bottom - statusBarHeight);
        return rectF;
    }

    private void drawRectF(Canvas canvas, MaskBean maskBean, Paint paint) {
        RectF rectF = changeRectF(maskBean.getRectF());
        switch (maskBean.getStyle()) {
            case ROUNDRECT:
                canvas.drawRoundRect(rectF, maskBean.getRadius(), maskBean.getRadius(), paint);
                break;
            case CIRCLE:
                canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2, paint);
                break;
            default:
                canvas.drawRoundRect(rectF, mCorner, mCorner, paint);
                break;
        }
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
    }

//    public void setTargetRect(Rect rect) {
//        mTargetRect.set(rect);
//        resetOutPath();
//        invalidate();
//    }
//
//    public void setFullingRect(Rect rect) {
//        mFullingRect.set(rect);
//        resetOutPath();
//        mCustomFullingRect = true;
//        invalidate();
//    }


//    MaskBean actionDownMaskBean = null;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < maskBeans.size(); i++) {
                    if (maskBeans.get(i).listener() != null) {
                        RectF rectF = maskBeans.get(i).getClickRectF();
                        Utils.print("?=" + maskBeans.get(i).getClickRectF().contains(event.getX(), event.getY()));
                        if (maskBeans.get(i).getClickRectF() != null && maskBeans.get(i).getClickRectF().contains(event.getX(), event.getY()+statusBarHeight)) {
                            Utils.print("onMaskViewBitmapClick");
//                            actionDownMaskBean = maskBeans.get(i);
//                            Utils.print("ACTION_DOWN actionDownMaskBean ="+actionDownMaskBean.getClickRectF());
                            maskBeans.get(i).listener().onMaskViewBitmapClick();
                        }

                    }
                }
                break;
            case MotionEvent.ACTION_UP:
//                Utils.print("ACTION_UP x ="+event.getX()+"    y = "+event.getY());
//                if (actionDownMaskBean != null && actionDownMaskBean.getClickRectF() != null) {
//                    if (actionDownMaskBean.getClickRectF().contains(event.getX(), event.getY())) {
//
//                    }
//                }
                break;
        }


        return super.onTouchEvent(event);
    }

    public void setFullingAlpha(int alpha) {
        mFullingPaint.setAlpha(alpha);
        invalidate();
    }

    public void setFullingColor(int color) {
        mFullingPaint.setColor(color);
        invalidate();
    }

    public void setHighTargetCorner(int corner) {
        this.mCorner = corner;
    }


    public void setOverlayTarget(boolean b) {
        mOverlayTarget = b;
    }
}
