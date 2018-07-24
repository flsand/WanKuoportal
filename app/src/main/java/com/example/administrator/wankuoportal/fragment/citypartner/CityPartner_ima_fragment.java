package com.example.administrator.wankuoportal.fragment.citypartner;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.ui.CityHeHuo.CityPartnerActivity;
import com.example.administrator.wankuoportal.util.CommonUtil;
import com.shizhefei.view.largeimage.LargeImageView;
import com.shizhefei.view.largeimage.factory.InputStreamBitmapDecoderFactory;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class CityPartner_ima_fragment extends BaseFragment {

    @BindView(R.id.city_partner_large_image_view)
    LargeImageView city_partner_large_image_view;

    @BindView(R.id.city_partner_frame_layout)
    View city_partner_frame_layout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.citypartner1_layout, container, false);
        ButterKnife.bind(this, rootView);

        try {
            city_partner_large_image_view.setImage(new InputStreamBitmapDecoderFactory(getActivity().getAssets().open("pic_hehuoren.png")));
            // 设置缩放比例
            city_partner_large_image_view.setCriticalScaleValueHook(new LargeImageView.CriticalScaleValueHook() {
                @Override
                public float getMinScale(LargeImageView largeImageView, int imageWidth, int imageHeight, float suggestMinScale) {
                    return 1;
                }

                @Override
                public float getMaxScale(LargeImageView largeImageView, int imageWidth, int imageHeight, float suggestMaxScale) {
                    return 1;
                }
            });
            city_partner_large_image_view.setOnDoubleClickListener(null);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //拦截双击事件
        city_partner_large_image_view.setOnDoubleClickListener(new LargeImageView.OnDoubleClickListener() {
            @Override
            public boolean onDoubleClick(LargeImageView view, MotionEvent event) {
                return true;
            }
        });

        //设计点击跳转事件
        city_partner_frame_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {
                    calculateIfGotoPartner(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
        return rootView;
    }

    /**
     * 计算点击位置是否处于“立即申请成为合伙人”位置，如果是则跳转到申请加入的界面
     *
     * @param event 点击事件
     */
    private void calculateIfGotoPartner(MotionEvent event) throws Exception{
        //“立即申请成为合伙人”在全图片中的位置比例
        float startX = 0.13f;
        float endX = 0.87f;
        float startY = 0.971f;
        float endY = 0.987f;

        //点击事件的位置比例
        float eventX = event.getX() / CommonUtil.getScreenWidth(getActivity());
        float eventY = (event.getY() + (float) city_partner_large_image_view.getScrollY()) /
                (city_partner_large_image_view.getImageHeight() * CommonUtil.getScreenWidth(getActivity()) / city_partner_large_image_view.getImageWidth());

        if (eventX >= startX && eventX <= endX
                && eventY >= startY && eventY <= endY) {
            ((CityPartnerActivity) getActivity()).gotoPartnerLogin();
        }
    }
}
