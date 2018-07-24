package com.example.administrator.wankuoportal.fragment.store;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.ShopCertificate;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class Store_3_fragment extends BaseFragment {

    private ListView baselist;
    private ImageView cityima;
    private ImageView zz1;
    private ImageView zz2;
    private ImageView zz3;
    private ImageView zhengsfz;
    private ImageView fansfz;
    private ImageView scsfz;
    private android.widget.TextView faren;
    private android.widget.TextView farenshouchi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.store_fragment3, container, false);//关联布局文件
this.farenshouchi = (TextView) rootView.findViewById(R.id.farenshouchi);
this.faren = (TextView) rootView.findViewById(R.id.faren);
        this.scsfz = (ImageView) rootView.findViewById(R.id.sc_sfz);
        this.fansfz = (ImageView) rootView.findViewById(R.id.fan_sfz);
        this.zhengsfz = (ImageView) rootView.findViewById(R.id.zheng_sfz);
        this.zz3 = (ImageView) rootView.findViewById(R.id.zz3);
        this.zz2 = (ImageView) rootView.findViewById(R.id.zz2);
        this.zz1 = (ImageView) rootView.findViewById(R.id.zz1);
//        PhotoViewAttacher attacher3= new PhotoViewAttacher(zz3);
//        PhotoViewAttacher attacher2= new PhotoViewAttacher(zz2);
//        PhotoViewAttacher attacher1= new PhotoViewAttacher(zz1);.
        String id = new UserService(getActivity()).getstoreid();
        String url = Apis.Base + Apis.shopcertificate;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("id", id)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        ShopCertificate s = gson.fromJson(response,ShopCertificate.class);
                        if (s.getCode()==0){
                            Glide.with(Store_3_fragment.this).load(Apis.Baseima + s.getData().getCertificateImg1()).into(zz1);
                            Glide.with(Store_3_fragment.this).load(Apis.Baseima + s.getData().getCertificateImg2()).into(zz2);
                            Glide.with(Store_3_fragment.this).load(Apis.Baseima + s.getData().getCertificateImg3()).into(zz3);
                            if (s.getData().getLegalIdImg()!=""&&s.getData().getLegalIdImg1()!=""){
                                faren.setText("法人身份证：(已认证)");
                            }else {
                                faren.setText("法人身份证：(未认证)");
                            }
                            Glide.with(Store_3_fragment.this).load(Apis.Baseima + s.getData().getLegalIdImg()).into(zhengsfz);
                            Glide.with(Store_3_fragment.this).load(Apis.Baseima + s.getData().getLegalIdImg1()).into(fansfz);
                            Glide.with(Store_3_fragment.this).load(Apis.Baseima + s.getData().getLegalIdImg2()).into(scsfz);


                            if (s.getData().getLegalIdImg2()!=""){
                                farenshouchi.setText("法人手持身份证：(已认证)");
                            }else {
                                farenshouchi.setText("法人手持身份证：(未认证)");
                            }
                        }else {
                            showShort(s.getMsg());
                        }
                    }
                });
        return rootView;
    }
}
