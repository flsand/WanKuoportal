package com.example.administrator.wankuoportal.coustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class LoadmoreScrollView extends ScrollView {
    private int count = 0;
    private OnScrollToBottomListener onScrollToBottom;

    public LoadmoreScrollView(Context context) {
        super(context);
    }


    public LoadmoreScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View childAt = getChildAt(0);


        if (childAt.getMeasuredHeight() <= getHeight() + getScrollY()) {
            count++;
            if (count == 1) {
                if (onScrollToBottom != null) {
                    onScrollToBottom.onScrollBottomListener(true);
                    count = 0;
                }
            }
        } else {
            count = 0;
            if (onScrollToBottom != null) {
                onScrollToBottom.onScrollBottomListener(false);
            }
        }
    }


    public void setOnScrollToBottomLintener(OnScrollToBottomListener listener) {
        onScrollToBottom = listener;
    }


    public interface OnScrollToBottomListener {
        void onScrollBottomListener(boolean isBottom);
    }
}

