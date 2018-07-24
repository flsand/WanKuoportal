package com.example.administrator.wankuoportal.fragment.store;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.ShopDefined;
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

public class Store_9_fragment extends BaseFragment {


    private ImageView ima1;
    private ImageView ima2;
    private ImageView ima3;
    private ImageView ima4;
    private ImageView ima5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.store_fragment9, container, false);//关联布局文件
        this.ima5 = (ImageView) rootView.findViewById(R.id.ima5);
        this.ima4 = (ImageView) rootView.findViewById(R.id.ima4);
        this.ima3 = (ImageView) rootView.findViewById(R.id.ima3);
        this.ima2 = (ImageView) rootView.findViewById(R.id.ima2);
        this.ima1 = (ImageView) rootView.findViewById(R.id.ima1);
        String url = Apis.Base + Apis.shopdefined;
        String shopDataId = new UserService(getActivity()).getstoreid();
        OkHttpUtils
                .get()
                .url(url)
                .addParams("shopDataId", shopDataId)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        ShopDefined s = gson.fromJson(response, ShopDefined.class);
                        if (s.getCode() == 0) {

                            if (s.getDatas().size() == 1) {
                                ima1.setVisibility(View.VISIBLE);
                                ima2.setVisibility(View.GONE);
                                ima3.setVisibility(View.GONE);
                                ima4.setVisibility(View.GONE);
                                ima5.setVisibility(View.GONE);
                                Glide.with(Store_9_fragment.this).load(Apis.Baseima + s.getDatas().get(0).getImg()).into(ima1);
                            } else if (s.getDatas().size() == 2) {
                                ima1.setVisibility(View.VISIBLE);
                                ima2.setVisibility(View.VISIBLE);
                                ima3.setVisibility(View.GONE);
                                ima4.setVisibility(View.GONE);
                                ima5.setVisibility(View.GONE);
                                Glide.with(Store_9_fragment.this).load(Apis.Baseima + s.getDatas().get(0).getImg()).into(ima1);
                                Glide.with(Store_9_fragment.this).load(Apis.Baseima + s.getDatas().get(1).getImg()).into(ima2);
                                ima1.setVisibility(View.VISIBLE);
                                ima2.setVisibility(View.VISIBLE);
                                ima3.setVisibility(View.GONE);
                                ima4.setVisibility(View.GONE);
                                ima5.setVisibility(View.GONE);
                            } else if (s.getDatas().size() == 3) {
                                ima1.setVisibility(View.VISIBLE);
                                ima2.setVisibility(View.VISIBLE);
                                ima3.setVisibility(View.VISIBLE);
                                ima4.setVisibility(View.GONE);
                                ima5.setVisibility(View.GONE);
                                Glide.with(Store_9_fragment.this).load(Apis.Baseima + s.getDatas().get(0).getImg()).into(ima1);
                                Glide.with(Store_9_fragment.this).load(Apis.Baseima + s.getDatas().get(1).getImg()).into(ima2);
                                Glide.with(Store_9_fragment.this).load(Apis.Baseima + s.getDatas().get(2).getImg()).into(ima3);
                            } else if (s.getDatas().size() == 4) {
                                ima1.setVisibility(View.VISIBLE);
                                ima2.setVisibility(View.VISIBLE);
                                ima3.setVisibility(View.VISIBLE);
                                ima4.setVisibility(View.VISIBLE);
                                ima5.setVisibility(View.GONE);
                                Glide.with(Store_9_fragment.this).load(Apis.Baseima + s.getDatas().get(0).getImg()).into(ima1);
                                Glide.with(Store_9_fragment.this).load(Apis.Baseima + s.getDatas().get(1).getImg()).into(ima2);
                                Glide.with(Store_9_fragment.this).load(Apis.Baseima + s.getDatas().get(2).getImg()).into(ima3);
                                Glide.with(Store_9_fragment.this).load(Apis.Baseima + s.getDatas().get(3).getImg()).into(ima4);
                            } else if (s.getDatas().size() == 5) {
                                ima1.setVisibility(View.VISIBLE);
                                ima2.setVisibility(View.VISIBLE);
                                ima3.setVisibility(View.VISIBLE);
                                ima4.setVisibility(View.VISIBLE);
                                ima5.setVisibility(View.VISIBLE);
                                Glide.with(Store_9_fragment.this).load(Apis.Baseima + s.getDatas().get(0).getImg()).into(ima1);
                                Glide.with(Store_9_fragment.this).load(Apis.Baseima + s.getDatas().get(1).getImg()).into(ima2);
                                Glide.with(Store_9_fragment.this).load(Apis.Baseima + s.getDatas().get(2).getImg()).into(ima3);
                                Glide.with(Store_9_fragment.this).load(Apis.Baseima + s.getDatas().get(3).getImg()).into(ima4);
                                Glide.with(Store_9_fragment.this).load(Apis.Baseima + s.getDatas().get(4).getImg()).into(ima5);
                            }

                        } else {
                            showShort(s.getMsg());
                        }
                    }
                });


        return rootView;
    }
}
