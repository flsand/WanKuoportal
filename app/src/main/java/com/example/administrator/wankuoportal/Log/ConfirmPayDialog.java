package com.example.administrator.wankuoportal.Log;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;

/**
 * Created by Administrator on 2017/10/20 0020.
 */

public class ConfirmPayDialog extends Dialog {

    private Context context;
    private String title;
    private String confirmButtonText;
    private String cacelButtonText;
    private ClickListenerInterface clickListenerInterface;
    private ImageView close;
    private TextView txphone;
    private EditText edyzm;
    private TextView ok;
    private TextView cancle;

    public interface ClickListenerInterface {

        public void doConfirm(String edit);

        public void doCancel();

        public void doClose();
    }

    public ConfirmPayDialog(@NonNull Context context, String title) {
        super(context);
        this.context = context;
        this.title = title;
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_confirm_pay, null);
        setContentView(view);
        this.cancle = (TextView) view.findViewById(R.id.cancle);
        this.ok = (TextView) view.findViewById(R.id.ok);
        this.edyzm = (EditText) view.findViewById(R.id.ed_yzm);
        this.txphone = (TextView) view.findViewById(R.id.tx_phone);
        this.close = (ImageView) view.findViewById(R.id.close);

        String a = title.substring(0, 3);
        String b = title.substring(title.length() - 4);

        txphone.setText("验证码已发送到您的" + a + "****" + b + "得手机\n请注意查收");
        ok.setOnClickListener(new clickListener());
        cancle.setOnClickListener(new clickListener());
        close.setOnClickListener(new clickListener());
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);

    }

    private class clickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.ok:
                    if (TextUtils.isEmpty(edyzm.getText())) {
                        clickListenerInterface.doConfirm("");
                    } else {
                        clickListenerInterface.doConfirm(edyzm.getText().toString());
                    }
                    break;
                case R.id.cancle:
                    clickListenerInterface.doCancel();
                    break;
                case R.id.close :
                    clickListenerInterface.doClose();
                    break;
            }
        }
    }
}
