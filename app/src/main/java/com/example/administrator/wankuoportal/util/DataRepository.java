package com.example.administrator.wankuoportal.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.administrator.wankuoportal.beans.GetNeedMoneyBean;
import com.example.administrator.wankuoportal.beans.MyInfoAssetBean;
import com.example.administrator.wankuoportal.beans.MyInfoBean;
import com.example.administrator.wankuoportal.beans.MyInfoCommonBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.flysand.mylibrary.crash.CrashHandler;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;

import static com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyInfoActivity.INFO_READ;
import static com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyInfoActivity.PREFERENCE_MY_INFO;

public class DataRepository {

    private MyInfoBean mMyInfoBean;

    private volatile static DataRepository mInstance;

    private List<MyInfoAssetBean.DatasBean> mAssetList = new ArrayList<>();
    private List<MyInfoCommonBean.DatasBean> mCommonList = new ArrayList<>();

    private DataRepository() {
    }

    public static DataRepository getInstance() {
        if (mInstance == null) {
            synchronized (DataRepository.class) {
                if (mInstance == null) {
                    mInstance = new DataRepository();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context) {
        initMyInfo(context);
    }

    public void initMyInfo(Context context) {
        Observable
                .create(new ObservableOnSubscribe<MyInfoBean>() {
                    @Override
                    public void subscribe(ObservableEmitter<MyInfoBean> emitter) throws Exception {
                        String url = Apis.Base + Apis.pushinfofindall + "?accountId=" + new UserService(context).getaccountid();
                        OkHttpUtils
                                .get()
                                .url(url)
                                .build()
                                .execute(new MyStringCallback() {
                                    @Override
                                    public void onResponse(String response, int id) {
                                        Gson gson = new Gson();
                                        MyInfoBean bean = gson.fromJson(response, MyInfoBean.class);
                                        mMyInfoBean = bean;
                                    }
                                });
                    }
                })
                .subscribeOn(Schedulers.io()).subscribe();
    }

    public void getMyInfo(Context context, DisposableObserver<MyInfoBean> observer) {
        Observable<MyInfoBean> observable = Observable
                .create(new ObservableOnSubscribe<MyInfoBean>() {
                    @Override
                    public void subscribe(ObservableEmitter<MyInfoBean> emitter) throws Exception {
                        String url = Apis.Base + Apis.pushinfofindall + "?accountId=" + new UserService(context).getaccountid();
                        OkHttpUtils
                                .get()
                                .url(url)
                                .build()
                                .execute(new MyStringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        super.onError(call, e, id);
                                        emitter.onError(e);
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        Gson gson = new Gson();
                                        MyInfoBean bean = gson.fromJson(response, MyInfoBean.class);
                                        emitter.onNext(bean);
                                        emitter.onComplete();
                                        mMyInfoBean = bean;
                                    }
                                });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(observer);
    }

    public void setMyInfoDataList(List<MyInfoAssetBean.DatasBean> assetList, List<MyInfoCommonBean.DatasBean> commonList) {
        mAssetList = assetList;
        mCommonList = commonList;
    }

    public List<MyInfoAssetBean.DatasBean> getMyInfoAssetList() {
        return mAssetList;
    }

    public List<MyInfoCommonBean.DatasBean> getMyInfoCommonList() {
        return mCommonList;
    }

    /**
     * 异步获取
     */
    public void getNumInfoNotRead(Context context, DisposableObserver<Integer> observer) {
        Log.d("qzw", "getNumInfoNotRead");
        Observable<Integer> observable = Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        String token = new UserService(context).gettoken();
                        String time = new Date().getTime() + "";
                        final String accountId = new UserService(context).getaccountid();
                        token = MD5Util.md5(time + token);

                        Log.d("qzw", "accountId = " + accountId);
                        if (accountId.isEmpty()) {
                            emitter.onNext(0);
                            emitter.onComplete();
                            return;
                        }

                        String url = Apis.Base + Apis.assetSelect + "?token=" + accountId + "," + time + "," + token + "&accountId=" + accountId + "&page=1";

                        Log.d("qzw", "url = " + url);

                        OkHttpUtils
                                .get()
                                .url(url)
                                .build()
                                .execute(new MyStringCallback() {
                                    @Override
                                    public void onResponse(String result, int id) {
                                        Gson gson = new Gson();
                                        MyInfoAssetBean bean = gson.fromJson(result, MyInfoAssetBean.class);
                                        if (bean != null) {
                                            mAssetList = bean.getDatas();
                                            String token = new UserService(context).gettoken();
                                            String time = new Date().getTime() + "";
                                            final String accountId = new UserService(context).getaccountid();
                                            CrashHandler.getInstance(context.getApplicationContext()).putParams("phone", new UserService(context).getphone());
                                            token = MD5Util.md5(time + token);

                                            String url = Apis.Base + Apis.commonSelect + "?token=" + accountId + "," + time + "," + token + "&accountId=" + accountId + "&page=1";
                                            OkHttpUtils
                                                    .get()
                                                    .url(url)
                                                    .build()
                                                    .execute(new MyStringCallback() {
                                                        @Override
                                                        public void onResponse(String response, int id) {
                                                            try {

                                                                Gson gson = new Gson();
                                                                MyInfoCommonBean bean = gson.fromJson(result, MyInfoCommonBean.class);
                                                                if (bean != null) {
                                                                    mCommonList = bean.getDatas();
                                                                    SharedPreferences mSharedPreferences = context.getSharedPreferences(PREFERENCE_MY_INFO, Context.MODE_PRIVATE);
                                                                    Set<String> readSet = mSharedPreferences.getStringSet(INFO_READ, null);

                                                                    if (mAssetList == null || mCommonList == null) {
                                                                        emitter.onNext(0);
                                                                        emitter.onComplete();
                                                                        Log.d("qzw", "return 1");
                                                                        return;
                                                                    }
                                                                    int notRead = mAssetList.size() + mCommonList.size();
                                                                    if (readSet == null) {
                                                                        emitter.onNext(notRead);
                                                                        emitter.onComplete();
                                                                        return;
                                                                    }
                                                                    for (int i = 0; i < mAssetList.size(); i++) {
                                                                        if (readSet.contains(String.valueOf(mAssetList.get(i).getId()))) {
                                                                            notRead--;
                                                                        }
                                                                    }
                                                                    for (int i = 0; i < mCommonList.size(); i++) {
                                                                        if (readSet.contains(String.valueOf(mCommonList.get(i).getAPushinfoId()))) {
                                                                            notRead--;
                                                                        }
                                                                    }
                                                                    emitter.onNext(notRead);
                                                                    emitter.onComplete();
                                                                }
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    });
                                        }

                                    }
                                });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(observer);
    }

    public void getNeedPayMoney(Context context, DisposableObserver<GetNeedMoneyBean> observer, int type) {
        Observable<GetNeedMoneyBean> observable = Observable
                .create(new ObservableOnSubscribe<GetNeedMoneyBean>() {
                    @Override
                    public void subscribe(ObservableEmitter<GetNeedMoneyBean> emitter) throws Exception {
                        String url = Apis.Base + Apis.getNeedMoney + "?accountId="
                                + new UserService(context).getaccountid() + "&type=" + type;

                        OkHttpUtils
                                .get()
                                .url(url)
                                .build()
                                .execute(new MyStringCallback() {
                                    @Override
                                    public void onResponse(String response, int id) {
                                        Gson gson = new Gson();
                                        GetNeedMoneyBean bean = gson.fromJson(response, GetNeedMoneyBean.class);
                                        emitter.onNext(bean);
                                        emitter.onComplete();
                                    }
                                });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(observer);
    }
}
