package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.ERWEIMA;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.EncodingUtils;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

public class ErWeiMaActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private de.hdodenhof.circleimageview.CircleImageView denglu;
    private android.widget.ImageView erweima;
    private View savePictureLayout;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_wei_ma);
        this.name = (TextView) findViewById(R.id.name);
        this.erweima = (ImageView) findViewById(R.id.erweima);
        this.savePictureLayout = findViewById(R.id.savePictureLayout);
        this.denglu = (CircleImageView) findViewById(R.id.denglu);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        savePictureLayout.setOnLongClickListener(v -> {

            return true;
        });
        String time = new Date().getTime() + "";
        String token = new UserService(MyApplication.context).gettoken();
        String accountId = new UserService(MyApplication.context).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.getshareimg + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("accountId", accountId)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        ERWEIMA erweimaz = gson.fromJson(response, ERWEIMA.class);
                        if (erweimaz.getCode() == 0) {
                            String imaurl = saveImage(EncodingUtils.createQRCode(erweimaz.getData().getImglink(), 200, 200, null));
                            Glide.with(ErWeiMaActivity.this).load(imaurl).into(erweima);
                            Glide.with(ErWeiMaActivity.this).load(Apis.Baseima + erweimaz.getData().getHeadicon()).into(denglu);
                            name.setText("我是" + erweimaz.getData().getNickname() + "\n" + "我在万阔答题赚了" + erweimaz.getData().getGold() + "金币");
                        } else if (erweimaz.getCode() == 2) {
                            startActivity(LoginActivity.class);
                            showShort(erweimaz.getMsg());
                            finish();
                        } else {
                            showShort(erweimaz.getMsg());
                        }
                    }
                });


    }


    public String saveImage(Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "erweima");
        if (!appDir.exists()) {
            appDir.mkdir();
        }

        String path = Environment.getExternalStorageDirectory() + "/erweima/headImg.jpg";

        L.d("saveImage1" + path);

        File file = new File(path);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            L.d("saveImage2: " + path);

            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
