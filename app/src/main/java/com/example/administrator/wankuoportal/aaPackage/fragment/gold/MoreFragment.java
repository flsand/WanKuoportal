package com.example.administrator.wankuoportal.aaPackage.fragment.gold;

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
 *     time    : 2018/6/14.
 *     desc    : 更多
 * </pre>
 */
public class MoreFragment extends MyBaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_transaction_more, container, false);
        initView();
        return rootView;
    }

    private void initView() {

    }

}
