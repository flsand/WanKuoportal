package com.example.administrator.wankuoportal.ui.SetUp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.YHK_Adapter;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.beans.YHK;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.Date;

import okhttp3.Call;

/***
 * 管理银行卡
 */
public class SetupYHKActivity extends BaseActivity {

    private ImageView back;
    private TextView informationtitle;
    private ListView yhklist;
    private ImageView add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_yhk);
        this.add = (ImageView) findViewById(R.id.add);
        this.yhklist = (ListView) findViewById(R.id.yhk_list);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yhk!=null){
                    if (yhk.getDatas().size()>=3){
                        showShort("最多可以添加三张银行卡，如需添加请先删除一张银行卡！");
                    }else {
                        startActivity(AddYHKActivity.class);
                    }
                }

            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        initview();
    }

    YHK yhk;
    private void initview() {

        String time = new Date().getTime()+"";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
        token = MD5Util.md5(time+token);
        String url = Apis.Base + Apis.getbankcard+"?token=" +accountId+","+time+","+token;
        OkHttpUtils
                .post()
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
                        yhk = gson.fromJson(response,YHK.class);
                        if (yhk.getCode()==0){
                            YHK_Adapter yhk_adapter = new YHK_Adapter(SetupYHKActivity.this, yhk.getDatas(), new YHK_Adapter.Callback() {
                                @Override
                                public void click(View v, int position) {
                                    String time = new Date().getTime()+"";
                                    String token = new UserService(getBaseContext()).gettoken();
                                    String accountId = new UserService(getBaseContext()).getaccountid();
                                    token = MD5Util.md5(time+token);
                                    String url = Apis.Base + Apis.delbankcard+"?token=" +accountId+","+time+","+token;
                                    OkHttpUtils
                                            .get()
                                            .url(url)
                                            .addParams("bankcardId", yhk.getDatas().get(position).getId()+"")
                                            .build()
                                            .execute(new MyStringCallback() {
                                                @Override
                                                public void onError(Call call, Exception e, int id) {

                                                }

                                                @Override
                                                public void onHttpResponse(String response, int id) throws Exception {
                                                    L.d(response);
                                                    BaseResult baseResult = gson.fromJson(response,BaseResult.class);
                                                    if (baseResult.getCode()==0){
                                                        showShort(baseResult.getMsg());
                                                        initview();
                                                    }
                                                }
                                            });
                                }

                            });
                            yhklist.setAdapter(yhk_adapter);

                        }else {

                        }
                    }
                });

    }
}
