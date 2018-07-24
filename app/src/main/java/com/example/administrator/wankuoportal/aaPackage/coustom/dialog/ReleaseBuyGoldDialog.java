package com.example.administrator.wankuoportal.aaPackage.coustom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;

/**
 * Created by Administrator on 2017/10/20 0020.
 */

public class ReleaseBuyGoldDialog extends Dialog {

    private Context context;
    private ImageView close;
    private TextView ok;
    private TextView cancle;

    public ReleaseBuyGoldDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_release_buy_gold, null);
        setContentView(view);
        this.cancle = (TextView) view.findViewById(R.id.cancle);
        this.ok = (TextView) view.findViewById(R.id.ok);
        this.close = (ImageView) view.findViewById(R.id.close);

        afterView(view);
        ok.setOnClickListener(new clickListener());
        cancle.setOnClickListener(new clickListener());
        close.setOnClickListener(new clickListener());
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.9);
        dialogWindow.setAttributes(lp);
    }

    public void afterView(View view) {

    }

    public void confirm() {
        dismiss();
    }

    public void cancel() {
        dismiss();
    }

    private class clickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.ok:
                    confirm();
                    break;
                case R.id.cancle:
                case R.id.close:
                    cancel();
                    break;
            }
        }
    }
}
