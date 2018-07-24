package com.example.administrator.wankuoportal.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.coustom.MyGridLayoutManager;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.flysand.mylibrary.listener.RecyclerOnScrollListener;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by JakeChen on 2018/3/22.
 */

public class ProjectUtil {

    public static void loadRemoteImage(Context context, String url, ImageView imageView) {
        if (!TextUtils.isEmpty(url))
            Glide.with(context).load(Apis.Baseima + url).into(imageView);
    }

    public static void loadHostImage(Context context, String url, ImageView imageView) {
        if (!TextUtils.isEmpty(url))
            Glide.with(context).load(url).into(imageView);
    }

    public static void loadLocalhostImage(Context context, int res, ImageView imageView) {
        try {
            Glide.with(context).load(res).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getStatusHeight(Context context) {
        int statusHeight = -1;

        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return statusHeight;
    }

    //获取屏幕原始尺寸高度，包括虚拟功能键高度
    public static int getDpi(Context context) {
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi = displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获取 虚拟按键的高度
     *
     * @param context
     * @return
     */
    public static int getBottomStatusHeight(Context context) {
        int totalHeight = getDpi(context);
        Utils.print("totalHeight = " + totalHeight);

        int contentHeight = getScreenHeight(context);
        Utils.print("contentHeight = " + contentHeight);

        int height = totalHeight - contentHeight;
        Utils.print("height = " + height);

        return height > 0 ? height : 0;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 标题栏高度
     *
     * @return
     */
    public static int getTitleHeight(Activity activity) {
        return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    }


    public static boolean isLogin() {
        if (new UserService(MyApplication.getInstance()).getislogin().equals("0")) {
            return false;
        } else {
            jumpLogin();
        }
        return true;
    }

    public static void jumpLogin() {
        Intent intent = new Intent(MyApplication.context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.context.startActivity(intent);
    }


    public static void jumpLogin(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
        new UserService(context).setislogin("1");
    }

    public static void initRecyclerView(RecyclerView mRecyclerView, RecyclerOnScrollListener.OnScrollListener listener) {
        mRecyclerView.setLayoutManager(new MyGridLayoutManager(mRecyclerView.getContext(), 1));
        if (listener != null)
            mRecyclerView.addOnScrollListener(
                    new RecyclerOnScrollListener() {
                        @Override
                        public void onLoadNextPage(View view) {
                            try {
                                Utils.print("load");
                                listener.onLoadNextPage(view);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
    }

    public static void showMessage(String str) {
        Toast.makeText(MyApplication.getInstance(), str, Toast.LENGTH_SHORT).show();
    }

    public static void showDevelopmentMessage() {
        showMessage(Constant.Tip.DEVELOPMENT);
    }
//
//    GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
//              layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//        @Override
//        public int getSpanSize(int position) {
//            return position == 0 ? 2 : 1;
//        }
//    });
//ecyclerView.setLayoutManager(layoutManager);


    /**
     * 隐藏软键盘(可用于Activity，Fragment)
     */
    public static void hideSoftKeyboard(Context context, View v) {
        if (v == null)
            return;

        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 处理手机号
     *
     * @param phone
     */
    public static String conductPhoneNumber(String phone) {

        String conductPhoneNumber = "";
        try {
            String st = phone.substring(0, 3);
            conductPhoneNumber = st + "****";
            String end = phone.substring(7, phone.length());
            conductPhoneNumber += end;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conductPhoneNumber;
    }

    public static DecimalFormat decimalFormat = new DecimalFormat("############0.00");

    public static String formatDouble(double d) {
        return com.flysand.mylibrary.util.Utils.replaceDoubleZero(decimalFormat.format(d));
    }

    public static String getWeek() {
        switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return "星期日";

            case 2:
                return "星期一";

            case 3:
                return "星期二";

            case 4:
                return "星期三";

            case 5:
                return "星期四";

            case 6:
                return "星期五";

            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    public static boolean keepDecimal(EditText editText, int length) {
        try {
            String str = editText.getText().toString();
            String subString = str.substring(str.indexOf("."), str.length());
            Utils.print("subString =" + subString);
            if (subString.length() > length + 1) {
                editText.setText(str.substring(0, str.length() - 1));
                editText.setSelection(str.length() - 1);
                return true;
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return false;
    }

    /**
     * 倒计时 分钟
     *
     * @param time
     * @return
     */
    public static String formatMinutes(long time) {
        time = time / 1000;
        if (time <= 59) {
            return String.format("00:00:%02d", time);
        } else if (time < 3600) {
            return String.format("00:%02d:%02d", time / 60, time % 60);
        } else {
            return String.format("%02d:%02d:%02d", time / 3600, time / 60 % 60, time % 60);
        }
    }

    /**
     * 判断时间是否小于每天16:30
     *
     * @return 返回类型
     * @Title: checkTime
     * @Description: TODO()
     */
    public static boolean checkTime(long currentTime) {
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dayTime = format.format(new Date(currentTime)) + " 16:30:00";
            Date dateTime = format1.parse(dayTime);
            long time = dateTime.getTime();
            return new Date(currentTime).getTime() < time;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    /**
     * 设置状态栏全透明
     *
     * @param activity 需要设置的activity
     */
    public static void setTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        transparentStatusBar(activity);
    }

    /**
     * 使状态栏透明
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static void transparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    /**
     * 添加自定义分割线
     * 分割线高度
     */
    public static void addItemDecoration(Context context, RecyclerView mRecyclerView) {
        DividerItemDecoration divider = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.custom_divider));
        mRecyclerView.addItemDecoration(divider);
    }
}
