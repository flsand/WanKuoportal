package com.example.administrator.wankuoportal.aaPackage.fragment.manor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.base.MyBaseFragment;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/7/11.
 *     desc    : 庄园我的
 * </pre>
 */
public class HanorMyFragment extends MyBaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_manor_my, container, false);
        initView();
        return rootView;
    }

    private void initView() {

    }
}
