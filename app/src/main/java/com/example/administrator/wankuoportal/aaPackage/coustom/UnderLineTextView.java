package com.example.administrator.wankuoportal.aaPackage.coustom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.wankuoportal.R;

public class UnderLineTextView extends android.support.v7.widget.AppCompatTextView {

    private Rect mRect;
    private Paint mPaint;
    private int mColor;
    private float density;
    private float mStrokeWidth;
    private float padding;

    public UnderLineTextView(Context context) {
        this(context, null, 0);
    }

    public UnderLineTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnderLineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //获取屏幕密度
        density = context.getResources().getDisplayMetrics().density;
        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.UnderlinedTextView, defStyleAttr, 0);
        mColor = array.getColor(R.styleable.UnderlinedTextView_underlineColor, getTextColors().getColorForState(getDrawableState(), 0));
        mStrokeWidth = array.getDimension(R.styleable.UnderlinedTextView_underlineWidth, density * 1);
        padding = array.getDimension(R.styleable.UnderlinedTextView_underlinePaddding, density * 5);
        array.recycle();

        mRect = new Rect();
        mPaint = new Paint();
//        mPaint.setStyle();
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(mStrokeWidth);
        //设置画直线格式
        mPaint.setStyle(Paint.Style.STROKE);
        //设置虚线效果
        mPaint.setPathEffect(new DashPathEffect(new float[]{15, 5}, 0));
        try {
            //在view层关闭硬件加速， 画虚线无效
            setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //得到TextView显示有多少行
        int count = getLineCount();

        //得到TextView的布局
        final Layout layout = getLayout();

        float x_start, x_stop, x_diff;
        int firstCharInLine, lastCharInLine;

        for (int i = 0; i < count; i++) {

            //getLineBounds得到这一行的外包矩形,
            // 这个字符的顶部Y坐标就是rect的top 底部Y坐标就是rect的bottom
            int baseline = getLineBounds(i, mRect);
            firstCharInLine = layout.getLineStart(i);
            lastCharInLine = layout.getLineEnd(i);

            //要得到这个字符的左边X坐标 用layout.getPrimaryHorizontal
            //得到字符的右边X坐标用layout.getSecondaryHorizontal
            x_start = layout.getPrimaryHorizontal(firstCharInLine);
            x_diff = layout.getPrimaryHorizontal(firstCharInLine + 1) - x_start;
            x_stop = layout.getPrimaryHorizontal(lastCharInLine - 1) + x_diff;
            canvas.drawLine(x_start+5, baseline + mStrokeWidth + padding, x_stop, baseline + mStrokeWidth + padding, mPaint);
        }
        super.onDraw(canvas);

    }

    public int getUnderLineColor() {
        return mColor;
    }

    public void setUnderLineColor(int mColor) {
        this.mColor = mColor;
        invalidate();
    }

    public float getUnderlineWidth() {
        return mStrokeWidth;
    }

    public void setUnderlineWidth(float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        invalidate();
    }
}