package com.example.administrator.wankuoportal.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Administrator on 2017/11/6 0006.
 * 调起qq界面
 */

public class QQ {

    public static void startQQ(Context mContext, int type, String qqnum) {

        try {

            String url = null;
            switch (type) {
                case 1:
                    // 直接进入QQ聊天(对QQ号)
                    url = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqnum + "&version=1";
//                String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=100000";
                    break;
                case 2:
                    // 打开个人介绍界面（对QQ号）
                    url = "mqqapi://card/show_pslcard?src_type=internal&version=1&uin=" + qqnum
                            + "&card_type=person&source=qrcode";
                    break;
                case 3:
                    // 打开QQ群介绍界面(对QQ群号)
                    url = "mqqapi://card/show_pslcard?src_type=internal&version=1&uin=" + qqnum
                            + "&card_type=group&source=qrcode";
                    break;

                default:
                    break;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
