package com.example.administrator.wankuoportal.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class CommonUtil {

    public static int getScreenWidth(Activity activity) {
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public static int getScreenHeight(Activity activity) {
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static SpannableStringBuilder setSpannableString(int color, String all, String mark) {
        SpannableStringBuilder builder = new SpannableStringBuilder(all);
        builder.setSpan(new ForegroundColorSpan(color), all.indexOf(mark), all.indexOf(mark) + mark.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return builder;
    }

    public static SpannableStringBuilder setSpannableString(int color, String all, int start, int end) {
        SpannableStringBuilder builder = new SpannableStringBuilder(all);
        builder.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
}
