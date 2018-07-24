package com.example.administrator.wankuoportal.aaPackage.coustom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;

import com.example.administrator.wankuoportal.util.ProjectUtil;

public class TimeTextView extends android.support.v7.widget.AppCompatTextView {


    OnTimerStopListener listener;

    public TimeTextView(Context context) {
        super(context);
    }

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public interface OnTimerStopListener {
        void onStop();
    }

    public void setListener(OnTimerStopListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 在控件被销毁时移除消息
        handler.removeMessages(0);
    }

    long mTime;
    private boolean run = true; // 是否启动了

    @SuppressLint("NewApi")
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
//                    Utils.print("mTime = " + mTime);
                    if (run) {
                        if (mTime > 0) {
                            TimeTextView.this.setText(ProjectUtil.formatMinutes(mTime));
                            mTime = mTime - 1000;
                            handler.sendEmptyMessageDelayed(0, 1000);
                        } else {
                            if (listener != null)
                                listener.onStop();
                        }
                    } else {
//                        TimeTextView.this.setEnabled(View.GONE);
                        if (listener != null)
                            listener.onStop();
                    }
                    break;

            }
        }
    };

    /**
     * @param mT 剩余时间 ms
     */
    @SuppressLint("NewApi")
    public void setTimes(long mT) {
        // 标示已经启动
        mTime = mT;
        if (mTime > 0) {
            handler.removeMessages(0);
            handler.sendEmptyMessage(0);
        } else {
//            TimeTextView.this.setVisibility(View.GONE);
            if (listener != null)
                listener.onStop();
        }
    }

    public void stop() {
        run = false;
    }
}