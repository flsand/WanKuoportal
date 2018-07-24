package com.example.administrator.wankuoportal.global;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.http.APIFactory;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flysand.mylibrary.util.MyHandler;
import com.flysand.mylibrary.util.MyToast;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.nutz.lang.Strings;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zht on 2017/4/10 22:08
 */

public class BaseFragment extends Fragment {
    public static Gson gson= new Gson();
    ProgressDialog dialogproa;
    public APIFactory retrofitUtil = APIFactory.getInstance();

    private CompositeSubscription mCompositeSubscription;

    /**
     * 显示加载对话框
     */
    protected void showHttpDialog() {
        if (dialogproa != null)
            dialogproa.dismiss();
        dialogproa = new ProgressDialog(getActivity());
        dialogproa.setMessage("请稍候...");
//        dialogproa.setCancelable(false);
        dialogproa.show();
    }

    /**
     * 显示加载对话框
     *
     * @param str
     */
    protected void showHttpDialog(String str) {
        if (dialogproa != null) {
            dialogproa.dismiss();
            dialogproa = null;
        }
        dialogproa = new ProgressDialog(getActivity());
        dialogproa.setMessage(str);
//        dialogproa.setCancelable(false);
        dialogproa.show();
    }

    protected void dismissHttpDialog() {
        if (dialogproa != null) {
            dialogproa.dismiss();
        }
    }

    protected void dismissErrDialog(String msg) {

        showHttpDialog(msg);
        new MyHandler(1000) {
            @Override
            public void run() {
                if (dialogproa != null)
                    dialogproa.dismiss();
            }
        };
    }

    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }


    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        this.mCompositeSubscription.add(s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private Toast mToast = null;

    public void showLong(String msg) {
        if (!Strings.isBlank(msg)) {
            MyToast toast = new MyToast(getActivity());
            toast.setText(msg);
//            if (mToast == null) {
//                mToast = Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG);
//            } else {
//                mToast.setText(msg);
//            }
//            mToast.show();
        }
    }

    public void showShort(String msg) {
        if (!Strings.isBlank(msg)) {
            MyToast toast = new MyToast(MyApplication.getInstance());
            toast.setText(msg);
//            if (mToast == null) {
//                mToast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
//            } else {
//                mToast.setText(msg);
//            }
//            mToast.show();
        }
    }

    public void cancelToast(){
        if (mToast != null){
            mToast.cancel();
        }
    }

    public void startActivity(Class<? extends Activity> clazz) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clazz);
        startActivity(intent);
    }


    public void startActivity(Class<? extends Activity> clazz, String... data) {
        if (data.length % 2 == 1) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(getActivity(), clazz);
        for (int i = 0; i < data.length / 2; i++) {
            intent.putExtra(data[i * 2], data[i * 2 + 1]);
        }
        startActivity(intent);
    }


    public void alert(String msg) {
        NormalDialog dialog = new NormalDialog(getActivity());
        dialog.content(msg).isTitleShow(false).btnNum(1).btnText("确定").show();
    }

    public NormalDialog confirm(String msg, OnBtnClickL on) {
        NormalDialog dialog = new NormalDialog(getActivity());
        dialog.setOnBtnClickL(null, on);
        dialog.content(msg).isTitleShow(false).btnNum(2).btnText("取消", "确定")
                .show();
        return dialog;
    }

}
