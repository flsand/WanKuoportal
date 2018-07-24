package com.example.administrator.wankuoportal.util;


import android.support.v4.app.Fragment;

import com.example.administrator.wankuoportal.fragment.main.Fenlei_fragment;
import com.example.administrator.wankuoportal.fragment.main.Showye_fragment;
import com.example.administrator.wankuoportal.fragment.main.Tiaozhan_fragment;
import com.example.administrator.wankuoportal.fragment.main.Wode_guzhu_fragment;
import com.example.administrator.wankuoportal.fragment.main.Wode_huiyuan_fragment;

/**
 * Created by JakeChen on 2018/3/2.
 * <p>
 * 进行Index页面Fragment生产的工厂类
 *
 * @author JakeChen
 */
public class IndexActivityiFactory {

    /**
     * 生成首页Fragment
     */
    public final static int INDEX_FRAGMENT_HOME = 1;
    /**
     * 生成分类Fragment
     */
    public final static int INDEX_FRAGMENT_CLASSIFY = 2;
    /**
     * 生成闲赚Fragment
     */
    public final static int INDEX_FRAGMENT_PROFIT = 3;
    /**
     * 生成会员Fragment
     */
    public final static int INDEX_FRAGMENT_MEMBER = 4;
    /**
     * 生成雇主Fragment
     */
    public final static int INDEX_FRAGMENT_EMPLOYER = 5;

    /**
     * 生成Fragment的工厂方法
     *
     * @return
     */
    public static Fragment createFragment(int position) {
        Fragment mFragment = null;
        switch (position) {
            //首页
            case INDEX_FRAGMENT_HOME:
                mFragment = new Showye_fragment();
                break;
                //分类
            case INDEX_FRAGMENT_CLASSIFY:
                mFragment = new Fenlei_fragment();
                break;
                //闲赚
            case INDEX_FRAGMENT_PROFIT:
                mFragment = new Tiaozhan_fragment();
                break;
                //会员
            case INDEX_FRAGMENT_MEMBER:
                mFragment = new Wode_huiyuan_fragment();
                break;
                //雇主
            case INDEX_FRAGMENT_EMPLOYER:
                mFragment = new Wode_guzhu_fragment();
                break;
        }
        return mFragment;
    }
}
