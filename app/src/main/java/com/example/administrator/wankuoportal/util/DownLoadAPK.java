package com.example.administrator.wankuoportal.util;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import com.example.administrator.wankuoportal.app.MyApplication;

/**
 * Created by ytzht on 2017/07/28 下午9:10
 */

public class DownLoadAPK {

    //下载器
    private DownloadManager downloadManager;

    //下载的ID
    private static long downloadId;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static long downloadAPK(DownloadManager downloadManager, String apkUrl, String name, String desc) {

        try {//TODO Exception 读写权限问题


            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(apkUrl));
            request.setDestinationInExternalPublicDir("xyx", name + ".apk");//表示设置下载地址为sd卡的volunteer文件夹，文件名为name.apk。
            request.setTitle(name);//设置下载中通知栏提示的标题
            request.setDescription(desc);//设置下载中通知栏提示的介绍
            request.setVisibleInDownloadsUi(true);  //设置显示下载界面
            request.setMimeType("application/vnd.android.package-archive");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);//表示下载进行中和下载完成的通知栏是否显示。

//        //获取DownloadManager
//        downloadManager = (DownloadManager) MyApplication.context.getSystemService(Context.DOWNLOAD_SERVICE);
//        //将下载请求加入下载队列，加入下载队列后会给该任务返回一个long型的id，通过该id可以取消任务，重启任务、获取下载的文件等等
//        downloadId = downloadManager.enqueue(request);


            // 默认只显示下载中通知。
            // VISIBILITY_VISIBLE_NOTIFY_COMPLETED表示下载完成后显示通知栏提示。VISIBILITY_HIDDEN表示不显示任何通知栏提示，
            // 这个需要在AndroidMainfest中添加权限android.permission.DOWNLOAD_WITHOUT_NOTIFICATION.

//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);//表示下载允许的网络类型，默认在任何网络下都允许下载。
            //有NETWORK_MOBILE、NETWORK_WIFI、NETWORK_BLUETOOTH三种及其组合可供选择。
            //如果只允许wifi下载，而当前网络为3g，则下载会等待。

//        request.setAllowedOverRoaming(true);//移动网络情况下是否允许漫游。

//        request.setMimeType("application/cn.trinea.download.file");//设置下载文件的mineType。
            // 因为下载管理Ui中点击某个已下载完成文件及下载完成点击通知栏提示都会根据mimeType去打开文件，所以我们可以利用这个属性。
            // 比如上面设置了mimeType为application/cn.trinea.download.file，
            // 我们可以同时设置某个Activity的intent-filter为application/cn.trinea.download.file，用于响应点击的打开文件。

//        request.allowScanningByMediaScanner();//表示允许MediaScanner扫描到这个文件，默认不允许。

            //request.addRequestHeader(String header, String value)
            //添加请求下载的网络链接的http头，比如User-Agent，gzip压缩等
            //注册广播接收者，监听下载状态
//        MyApplication.context.registerReceiver(receiver,
//                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

            return downloadManager.enqueue(request);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MyApplication.context, "权限被禁止，请手动设置！", Toast.LENGTH_SHORT).show();
            return 0;
        }

    }
//    //广播监听下载的各个状态
//    private BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            checkStatus();
//        }
//    };
//    //检查下载状态
//    private void checkStatus() {
//        DownloadManager.Query query = new DownloadManager.Query();
//        //通过下载的id查找
//        query.setFilterById(downloadId);
//        Cursor c = downloadManager.query(query);
//        if (c.moveToFirst()) {
//            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
//            switch (status) {
//                //下载暂停
//                case DownloadManager.STATUS_PAUSED:
//                    break;
//                //下载延迟
//                case DownloadManager.STATUS_PENDING:
//                    break;
//                //正在下载
//                case DownloadManager.STATUS_RUNNING:
//                    break;
//                //下载完成
//                case DownloadManager.STATUS_SUCCESSFUL:
//                    //下载完成安装APK
//                    installAPK();
//                    break;
//                //下载失败
//                case DownloadManager.STATUS_FAILED:
//                    Toast.makeText(MyApplication.context, "下载失败", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//        c.close();
//    }
//
//    //下载到本地后执行安装
//    private void installAPK() {
//        //获取下载文件的Uri
//        Uri downloadFileUri = downloadManager.getUriForDownloadedFile(downloadId);
//        if (downloadFileUri != null) {
//            Intent intent= new Intent(Intent.ACTION_VIEW);
//            intent.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            MyApplication.context.startActivity(intent);
//        }
//    }


}
