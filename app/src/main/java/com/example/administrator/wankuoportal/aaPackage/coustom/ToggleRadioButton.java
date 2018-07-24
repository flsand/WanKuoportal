package com.example.administrator.wankuoportal.aaPackage.coustom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import com.example.administrator.wankuoportal.R;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/15.
 *     desc    : 排序选择RadioButton 支持<右边>三种状态切换
 *                  usage:
 *                      sp1:  android:drawableRight="@drawable/sort_selector" (
 *                                  <item android:drawable="@drawable/up_arrow" android:state_checked="true" />
 *                                  <item android:drawable="@drawable/gray_arrow" />
 *                            )灰色与上头的图片selector
 *                      sp2:  app:taggle_right_drawable="@drawable/down_arrow" 设置第二次点击显示的drawable (下箭头)
 *                      sp3:  setOnToggleChangeListener
 *                                   isToggle() 获取切换状态
 *
 * </pre>
 */
public class ToggleRadioButton extends AppCompatRadioButton {

    private boolean isToggle;
    private int taggle_right_drawable;
    private Drawable rightDrawable;
    private Drawable oldDrawable;
    private OnToggleChangeListener changeListener;

    public interface OnToggleChangeListener {
        /**
         * Called when the check state of a compound button has toggle.
         *
         * @param buttonView The compound button view whose state has changed.
         * @param isToggle   The new toggle state of buttonView.
         */
        void onCheckedChanged(CompoundButton buttonView, boolean isToggle);
    }

    public ToggleRadioButton(Context context) {
        this(context, null);
    }

    public ToggleRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.radioButtonStyle);
    }

    public ToggleRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchRadioButton);
        this.taggle_right_drawable = typedArray.getResourceId(R.styleable.SwitchRadioButton_taggle_right_drawable, -1);
        try {
            rightDrawable = getContext().getResources().getDrawable(taggle_right_drawable);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Register a callback to be invoked when the toggle state of this button
     * changes.
     *
     * @param listener the callback to call on toggle state change
     */
    public void setOnToggleChangeListener(@Nullable OnToggleChangeListener listener) {
        this.changeListener = listener;
    }

    /**
     * 获取切换状态
     *
     * @return
     */
    public boolean isToggle() {
        return isToggle;
    }

    @Override
    public void toggle() {
        if (isChecked()) {
            if (isToggle) {
                //切换上箭头
                isToggle = false;
                setCompoundDrawables(null, null, oldDrawable, null);
            } else {
                //切换下箭头
                isToggle = true;
                Drawable[] drawables = getCompoundDrawables();
                oldDrawable = drawables[2];
                setCompoundDrawables(null, null, rightDrawable, null);
            }
        } else {
            //恢复
            if (isToggle)
                setCompoundDrawables(null, null, oldDrawable, null);
            super.toggle();
            isToggle = false;
        }

        if (changeListener != null)
            changeListener.onCheckedChanged(this, !isToggle);
    }

    @Override
    public void setChecked(boolean checked) {
        if (!checked && isToggle && isChecked()) {
            setCompoundDrawables(null, null, oldDrawable, null);
            isToggle = false;
        }
        super.setChecked(checked);
    }

}
