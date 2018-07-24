package com.example.administrator.wankuoportal.util;

/**
 * <pre>
 *     pagckage:com.example.administrator.wankuoportal.util
 *     author  : Administrator
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/9.
 *     desc    :
 *
 * </pre>
 */
public class ClickHelper {

    private static long lastclick = 0;
    private static long timems = 1000; //ms

    public static boolean isHandle() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastclick <= timems) {
            return true;
        }
        lastclick = System.currentTimeMillis();
        return false;
    }
}
