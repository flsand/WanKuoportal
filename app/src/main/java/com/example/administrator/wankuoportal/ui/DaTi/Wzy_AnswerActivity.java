package com.example.administrator.wankuoportal.ui.DaTi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.coomon.Define;
import com.example.administrator.wankuoportal.base.DefultHttpAnalysisHelper;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.beans.QuersitionBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.Store.StoreChanPinActivity;
import com.example.administrator.wankuoportal.ui.Store.StoreMainActivity;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.flysand.mylibrary.util.Utils;
import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Date;
import java.util.List;

import okhttp3.Call;

public class Wzy_AnswerActivity extends BaseActivity {

    private android.widget.ImageView wenback;
    private android.widget.TextView jymanposition;
    private android.widget.TextView djsjy;
    private android.widget.TextView jyquessitionposition;
    private android.widget.TextView jyqstbody;
    private android.widget.TextView jyqstqst;
    private android.widget.ImageView jyqstima;
    private android.widget.ImageView imaa;
    private android.widget.TextView questiona;
    private android.widget.LinearLayout optiona;
    private android.widget.ImageView imab;
    private android.widget.TextView questionb;
    private android.widget.LinearLayout optionb;
    private android.widget.TextView textView2;
    private android.widget.ImageView imac;
    private android.widget.TextView questionc;
    private android.widget.TextView nextquesition;
    private android.widget.LinearLayout optionc;
    private android.widget.ImageView imad;
    private android.widget.TextView questiond;
    private android.widget.LinearLayout optiond;

    int yes = 0;
    int no = 0;
    int jb = 0;
    int isanswer = 0;
    String rightAnswer = "";
    String wrongAnswer = "";

    private TextView yesanswer;
    private TextView noanswer;
    private LinearLayout answer;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wzy__answer);
        this.answer = (LinearLayout) findViewById(R.id.answer);
        this.nextquesition = (TextView) findViewById(R.id.nextquesition);
        this.optiond = (LinearLayout) findViewById(R.id.option_d);
        this.questiond = (TextView) findViewById(R.id.question_d);
        this.imad = (ImageView) findViewById(R.id.ima_d);
        this.optionc = (LinearLayout) findViewById(R.id.option_c);
        this.questionc = (TextView) findViewById(R.id.question_c);
        this.imac = (ImageView) findViewById(R.id.ima_c);
        this.textView2 = (TextView) findViewById(R.id.textView2);
        this.optionb = (LinearLayout) findViewById(R.id.option_b);
        this.questionb = (TextView) findViewById(R.id.question_b);
        this.imab = (ImageView) findViewById(R.id.ima_b);
        this.optiona = (LinearLayout) findViewById(R.id.option_a);
        this.questiona = (TextView) findViewById(R.id.question_a);
        this.imaa = (ImageView) findViewById(R.id.ima_a);
        this.jyqstima = (ImageView) findViewById(R.id.jy_qst_ima);
        this.jyqstqst = (TextView) findViewById(R.id.jy_qst_qst);
        this.jyqstbody = (TextView) findViewById(R.id.jy_qst_body);
        this.jyquessitionposition = (TextView) findViewById(R.id.jy_quessitionposition);
        this.noanswer = (TextView) findViewById(R.id.no_answer);
        this.yesanswer = (TextView) findViewById(R.id.yes_answer);
        this.djsjy = (TextView) findViewById(R.id.djs_jy);
        this.jymanposition = (TextView) findViewById(R.id.jy_manposition);
        this.title = (TextView) findViewById(R.id.title);
        this.wenback = (ImageView) findViewById(R.id.wen_back);
        title.setText("记忆赢大奖");


        StatusBarUtil.setTransparent(Wzy_AnswerActivity.this);
        initview();
        initHandler();
        djs();
        if (Utils.isApkDebugable(this, getPackageName())) {
            Button jumpBtn = (Button) findViewById(R.id.jumpBtn);
            jumpBtn.setVisibility(View.VISIBLE);
            jumpBtn.setOnClickListener(v -> {
                List<QuersitionBean.DatasBean.AprizeAnswerBean> list = q.getDatas().get(qst - 1).getAprizeAnswer();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getIsRight() == 0) {
                        chooseoption(i + 1);
                        nextquesition.performClick();
                        return;
                    }
                }
            });
        }
    }

    QuersitionBean q;

    private void initview() {

        nextquesition.setEnabled(false);
        wenback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                if (handler != null) {
                    if (runnable != null) {
                        handler.removeCallbacks(runnable);
                        runnable = null;
                    }
                    handler = null;
                }
            }
        });
        q = (QuersitionBean) getIntent().getSerializableExtra("QuersitionBean");
        L.d("QuersitionBean", q.toString());


        //初始化题目
        nextquesition.setVisibility(View.GONE);
        jyqstbody.setText(q.getDatas().get(0).getAprizeQuestion().getContent());
        jyqstqst.setText(q.getDatas().get(0).getAprizeQuestion().getQuestion());
        questiona.setText(q.getDatas().get(0).getAprizeAnswer().get(0).getAnswer());
        questionb.setText(q.getDatas().get(0).getAprizeAnswer().get(1).getAnswer());
        questionc.setText(q.getDatas().get(0).getAprizeAnswer().get(2).getAnswer());
        questiond.setText(q.getDatas().get(0).getAprizeAnswer().get(3).getAnswer());
        String imaurl = Apis.Baseima + q.getDatas().get(0).getAprizeQuestion().getImg();
        Glide.with(Wzy_AnswerActivity.this).load(imaurl).into(jyqstima);
        jymanposition.setText("您是第" + q.getDatas().get(0).getAprizeQuestion().getNumber() + "个回答此题的");


        jyqstima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    if (q.getDatas().get(qst).getAprizeQuestion().getClickType() == 0) {

                    } else if (q.getDatas().get(qst).getAprizeQuestion().getClickType() == 4) {

                        if (isanswer == 1) {
                            for (int i = qst; i < 10; i++) {
                                if (wrongAnswer.length() == 0) {
                                    wrongAnswer = wrongAnswer + q.getDatas().get(i).getAprizeRecord().getId();
                                } else {
                                    wrongAnswer = wrongAnswer + "," + q.getDatas().get(i).getAprizeRecord().getId();
                                }

                            }
                        } else {
                            for (int i = qst - 1; i < 10; i++) {
                                if (wrongAnswer.length() == 0) {
                                    wrongAnswer = wrongAnswer + q.getDatas().get(i).getAprizeRecord().getId();
                                } else {
                                    wrongAnswer = wrongAnswer + "," + q.getDatas().get(i).getAprizeRecord().getId();
                                }

                            }
                        }


                        String time = new Date().getTime() + "";
                        String token = new UserService(getBaseContext()).gettoken();
                        String accountId = new UserService(getBaseContext()).getaccountid();
                        token = MD5Util.md5(time + token);
                        String url = Apis.Base + Apis.returnquestion + "?token=" + accountId + "," + time + "," + token;

                        Utils.print("rightAnswer = "+rightAnswer);
                        Utils.print("wrongAnswer =" +wrongAnswer);
                        OkHttpUtils
                                .get()
                                .url(url)
                                .addParams("rightAnswer", rightAnswer)
                                .addParams("wrongAnswer", wrongAnswer)
                                .addParams("accountId", accountId)
                                .addParams("questiontpye", Constant.HttpType.MEMORY)
                                .build()
                                .execute(new MyStringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onHttpResponse(String response, int id) throws Exception {
                                        L.d("returnquestion", response);
                                        BaseResult baseResult = gson.fromJson(response, BaseResult.class);
                                        if (baseResult.getCode() == 0) {

                                            new UserService(Wzy_AnswerActivity.this).setstoreid(q.getDatas().get(qst).getAprizeQuestion().getClickUrl() + "");
                                            startActivity(StoreMainActivity.class);
                                            finish();
                                        } else if (baseResult.getCode() == 2) {
                                            startActivity(LoginActivity.class);
                                            new UserService(Wzy_AnswerActivity.this).setislogin("1");
                                        } else {
                                            showShort(baseResult.getMsg());
                                        }


                                    }
                                });


                        // TODO: 2017/11/11 0011 跳店铺首页
                    } else if (q.getDatas().get(qst).getAprizeQuestion().getClickType() == 2) {
                        if (isanswer == 1) {
                            for (int i = qst; i < 10; i++) {
                                if (wrongAnswer.length() == 0) {
                                    wrongAnswer = wrongAnswer + q.getDatas().get(i).getAprizeRecord().getId();
                                } else {
                                    wrongAnswer = wrongAnswer + "," + q.getDatas().get(i).getAprizeRecord().getId();
                                }

                            }
                        } else {
                            for (int i = qst - 1; i < 10; i++) {
                                if (wrongAnswer.length() == 0) {
                                    wrongAnswer = wrongAnswer + q.getDatas().get(i).getAprizeRecord().getId();
                                } else {
                                    wrongAnswer = wrongAnswer + "," + q.getDatas().get(i).getAprizeRecord().getId();
                                }

                            }
                        }


                        String time = new Date().getTime() + "";
                        String token = new UserService(getBaseContext()).gettoken();
                        String accountId = new UserService(getBaseContext()).getaccountid();
                        token = MD5Util.md5(time + token);
                        String url = Apis.Base + Apis.returnquestion + "?token=" + accountId + "," + time + "," + token;

                        Utils.print("rightAnswer = "+rightAnswer);
                        Utils.print("wrongAnswer =" +wrongAnswer);
                        OkHttpUtils
                                .get()
                                .url(url)
                                .addParams("rightAnswer", rightAnswer)
                                .addParams("wrongAnswer", wrongAnswer)
                                .addParams("accountId", accountId)
                                .addParams("questiontpye", Constant.HttpType.MEMORY)
                                .build()
                                .execute(new MyStringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onHttpResponse(String response, int id) throws Exception {
                                        L.d("returnquestion", response);
                                        BaseResult baseResult = gson.fromJson(response, BaseResult.class);
                                        if (baseResult.getCode() == 0) {

                                            startActivity(StoreChanPinActivity.class, "id", q.getDatas().get(qst).getAprizeQuestion().getClickUrl() + "");
                                            finish();
                                        } else if (baseResult.getCode() == 2) {
                                            startActivity(LoginActivity.class);
                                            new UserService(Wzy_AnswerActivity.this).setislogin("1");
                                        } else {
                                            showShort(baseResult.getMsg());
                                        }


                                    }
                                });

                        // TODO: 2017/11/11 0011 跳产品详情
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showShort(DefultHttpAnalysisHelper.ERR_MSG);
                }
            }
        });

        //答题选项a
        optiona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseoption(1);
                isanswer = 1;
                optiona.setEnabled(false);
                optionb.setEnabled(false);
                optionc.setEnabled(false);
                optiond.setEnabled(false);
//                nextquesition.setEnabled(true);
            }
        });
        //答题选项b
        optionb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseoption(2);
                isanswer = 1;
                optiona.setEnabled(false);
                optionb.setEnabled(false);
                optionc.setEnabled(false);
                optiond.setEnabled(false);
//                nextquesition.setEnabled(true);
            }
        });
        //答题选项c
        optionc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseoption(3);
                isanswer = 1;
                optiona.setEnabled(false);
                optionb.setEnabled(false);
                optionc.setEnabled(false);
                optiond.setEnabled(false);
//                nextquesition.setEnabled(true);
            }
        });
        //答题选项d
        optiond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseoption(4);
                isanswer = 1;
                optiona.setEnabled(false);
                optionb.setEnabled(false);
                optionc.setEnabled(false);
                optiond.setEnabled(false);
//                nextquesition.setEnabled(true);
            }
        });

        nextquesition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qst < 10) {
                    if (handler != null) {
                        if (runnable != null) {
                            handler.removeCallbacks(runnable);
                            runnable = null;
                        }
                        handler = null;
                    }
                    initHandler();

                    if (isanswer == 0) {
                        no++;
                        if (wrongAnswer.length() == 0) {
                            wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                        } else {
                            wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                        }
                    }
                    qst++;
                    jyquessitionposition.setText(qst + "");
                    changeposition();
//                    showShort("更新答题题目");
                    djs();
                } else {
                    if (isanswer == 0) {
                        no++;
                        if (wrongAnswer.length() == 0) {
                            wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                        } else {
                            wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                        }
                    }
                    Intent it = new Intent(Wzy_AnswerActivity.this, JY_ResultActivity.class);
                    it.putExtra("yes", yes + "");
                    it.putExtra("no", no + "");
                    it.putExtra("jb", jb + "");
                    it.putExtra("rightAnswer", rightAnswer);
                    it.putExtra("wrongAnswer", wrongAnswer);
                    it.putExtra("type", "JY");
                    startActivity(it);
                    finish();

                }
            }
        });

    }


    /**
     * 保证每一道必须看够5s
     */
    private void correctlyDelay() {
        Utils.print("sec = " + sec);
        int consuming = 15 - sec;
        Utils.print("耗时 :" + consuming);
        if (consuming < Define.Answer.DelayTime) {
            correctlyDelay = Define.Answer.DelayTime - consuming;
            nextquesition.setEnabled(false);
            nextquesition.setText("下一题(" + correctlyDelay + ")");
        } else {
            correctlyDelay = 0;
            nextquesition.setEnabled(true);
        }
//        sec = 10;
    }

    /**
     * 1s
     */
    private void timeChange() {
        Utils.print("Enabled = " + nextquesition.isEnabled());
        djsjy.setText(sec + "");
//        if (sec < 5) {
//            nextquesition.setEnabled(true);
//            Utils.print("setEnabled(true);");
//            nextquesition.setText("下一题");
//        }

        if (nextquesition.getVisibility() == View.VISIBLE) {
            Utils.print("correctlyDelay= " + correctlyDelay);
            if (correctlyDelay > 0) {
                nextquesition.setEnabled(false);
                nextquesition.setText("下一题(" + correctlyDelay + ")");
            } else {
                nextquesition.setEnabled(true);
                nextquesition.setText("下一题");
            }
            correctlyDelay--;
        }
    }


    private void chooseoption(int i) {
        isanswer = 1;
        switch (i) {
            case 1:
                questiona.setTextColor(getResources().getColor(R.color.white));
                questiona.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                if (q.getDatas().get(qst - 1).getAprizeAnswer().get(0).getIsRight() == 0) {
                    imaa.setImageResource(R.drawable.scds0101);
                    questiona.setText("恭喜您答对了，获得" + q.getDatas().get(qst - 1).getGold() + "个WK币");
                    nextquesition.setVisibility(View.VISIBLE);
                    correctlyDelay();
                    jb = jb + q.getDatas().get(qst - 1).getGold();
                    if (rightAnswer.length() == 0) {
                        rightAnswer = rightAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        rightAnswer = rightAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    yes++;
                } else if (q.getDatas().get(qst - 1).getAprizeAnswer().get(1).getIsRight() == 0) {
                    imaa.setImageResource(R.drawable.scds0102);
                    questiona.setText("您答错了，正确答案应该是B");
                    nextquesition.setVisibility(View.GONE);
                    imab.setImageResource(R.drawable.scds0101);
                    if (wrongAnswer.length() == 0) {
                        wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    no++;
                } else if (q.getDatas().get(qst - 1).getAprizeAnswer().get(2).getIsRight() == 0) {
                    imaa.setImageResource(R.drawable.scds0102);
                    questiona.setText("您答错了，正确答案应该是C");
                    nextquesition.setVisibility(View.GONE);
                    imac.setImageResource(R.drawable.scds0101);
                    if (wrongAnswer.length() == 0) {
                        wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    no++;
                } else if (q.getDatas().get(qst - 1).getAprizeAnswer().get(3).getIsRight() == 0) {
                    imaa.setImageResource(R.drawable.scds0102);
                    questiona.setText("您答错了，正确答案应该是D");
                    nextquesition.setVisibility(View.GONE);
                    imad.setImageResource(R.drawable.scds0101);
                    if (wrongAnswer.length() == 0) {
                        wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    no++;
                }
                break;
            case 2:
                questionb.setTextColor(getResources().getColor(R.color.white));
                questionb.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                if (q.getDatas().get(qst - 1).getAprizeAnswer().get(0).getIsRight() == 0) {
                    imab.setImageResource(R.drawable.scds0102);
                    imaa.setImageResource(R.drawable.scds0101);
                    if (wrongAnswer.length() == 0) {
                        wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    questionb.setText("您答错了，正确答案应该是A");

                    nextquesition.setVisibility(View.GONE);
                    no++;
                } else if (q.getDatas().get(qst - 1).getAprizeAnswer().get(1).getIsRight() == 0) {
                    imab.setImageResource(R.drawable.scds0101);
                    questionb.setText("恭喜您答对了，获得" + q.getDatas().get(qst - 1).getGold() + "个WK币");
                    nextquesition.setVisibility(View.VISIBLE);
                    correctlyDelay();
                    jb = jb + q.getDatas().get(qst - 1).getGold();
                    if (rightAnswer.length() == 0) {
                        rightAnswer = rightAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        rightAnswer = rightAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    yes++;
                    imab.setImageResource(R.drawable.scds0101);
                } else if (q.getDatas().get(qst - 1).getAprizeAnswer().get(2).getIsRight() == 0) {
                    imab.setImageResource(R.drawable.scds0102);
                    questionb.setText("您答错了，正确答案应该是C");
                    nextquesition.setVisibility(View.GONE);
                    if (wrongAnswer.length() == 0) {
                        wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    no++;
                    imac.setImageResource(R.drawable.scds0101);
                } else if (q.getDatas().get(qst - 1).getAprizeAnswer().get(3).getIsRight() == 0) {
                    imab.setImageResource(R.drawable.scds0102);
                    questionb.setText("您答错了，正确答案应该是D");
                    nextquesition.setVisibility(View.GONE);
                    imad.setImageResource(R.drawable.scds0101);
                    if (wrongAnswer.length() == 0) {
                        wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    no++;
                }
                break;
            case 3:
                questionc.setTextColor(getResources().getColor(R.color.white));
                questionc.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                if (q.getDatas().get(qst - 1).getAprizeAnswer().get(0).getIsRight() == 0) {
                    imac.setImageResource(R.drawable.scds0102);
                    questionc.setText("您答错了，正确答案应该是A");
                    nextquesition.setVisibility(View.GONE);
                    if (wrongAnswer.length() == 0) {
                        wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    no++;
                    imaa.setImageResource(R.drawable.scds0101);
                } else if (q.getDatas().get(qst - 1).getAprizeAnswer().get(1).getIsRight() == 0) {
                    imac.setImageResource(R.drawable.scds0102);
                    questionc.setText("您答错了，正确答案应该是B");
                    nextquesition.setVisibility(View.GONE);
                    if (wrongAnswer.length() == 0) {
                        wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    no++;
                    imab.setImageResource(R.drawable.scds0101);
                } else if (q.getDatas().get(qst - 1).getAprizeAnswer().get(2).getIsRight() == 0) {
                    imac.setImageResource(R.drawable.scds0101);
                    yes++;
                    if (rightAnswer.length() == 0) {
                        rightAnswer = rightAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        rightAnswer = rightAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    questionc.setText("恭喜您答对了，获得" + q.getDatas().get(qst - 1).getGold() + "个WK币");
                    nextquesition.setVisibility(View.VISIBLE);
                    correctlyDelay();
                    jb = jb + q.getDatas().get(qst - 1).getGold();
                } else if (q.getDatas().get(qst - 1).getAprizeAnswer().get(3).getIsRight() == 0) {
                    imac.setImageResource(R.drawable.scds0102);
                    questionc.setText("您答错了，正确答案应该是D");
                    nextquesition.setVisibility(View.GONE);
                    if (wrongAnswer.length() == 0) {
                        wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    no++;
                    imad.setImageResource(R.drawable.scds0101);
                }
                break;
            case 4:
                questiond.setTextColor(getResources().getColor(R.color.white));
                questiond.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                if (q.getDatas().get(qst - 1).getAprizeAnswer().get(0).getIsRight() == 0) {
                    imad.setImageResource(R.drawable.scds0102);
                    questiond.setText("您答错了，正确答案应该是A");
                    nextquesition.setVisibility(View.GONE);
                    if (wrongAnswer.length() == 0) {
                        wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    no++;
                    imaa.setImageResource(R.drawable.scds0101);
                } else if (q.getDatas().get(qst - 1).getAprizeAnswer().get(1).getIsRight() == 0) {
                    imad.setImageResource(R.drawable.scds0102);
                    questiond.setText("您答错了，正确答案应该是B");
                    nextquesition.setVisibility(View.GONE);
                    if (wrongAnswer.length() == 0) {
                        wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    no++;
                    imab.setImageResource(R.drawable.scds0101);
                } else if (q.getDatas().get(qst - 1).getAprizeAnswer().get(2).getIsRight() == 0) {
                    imad.setImageResource(R.drawable.scds0102);
                    questiond.setText("您答错了，正确答案应该是C");
                    nextquesition.setVisibility(View.GONE);
                    if (wrongAnswer.length() == 0) {
                        wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    no++;
                    imac.setImageResource(R.drawable.scds0101);
                } else if (q.getDatas().get(qst - 1).getAprizeAnswer().get(3).getIsRight() == 0) {
                    imad.setImageResource(R.drawable.scds0101);
                    questiond.setText("恭喜您答对了，获得" + q.getDatas().get(qst - 1).getGold() + "个WK币");
                    nextquesition.setVisibility(View.VISIBLE);
                    correctlyDelay();
                    jb = jb + q.getDatas().get(qst - 1).getGold();
                    if (rightAnswer.length() == 0) {
                        rightAnswer = rightAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    } else {
                        rightAnswer = rightAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                    }
                    yes++;
                }
                break;
            default:
                break;
        }
        noanswer.setText(no + "");
        yesanswer.setText(yes + "");
    }

    //更新题目
    private void changeposition() {

        noanswer.setText(no + "");
        yesanswer.setText(yes + "");
        isanswer = 0;
        optiona.setEnabled(true);
        optionb.setEnabled(true);
        optionc.setEnabled(true);
        optiond.setEnabled(true);
        nextquesition.setVisibility(View.GONE);
        nextquesition.setEnabled(false);
        questiona.setTextColor(getResources().getColor(R.color.all_textc));
        questionb.setTextColor(getResources().getColor(R.color.all_textc));
        questionc.setTextColor(getResources().getColor(R.color.all_textc));
        questiond.setTextColor(getResources().getColor(R.color.all_textc));
        questiona.setBackgroundColor(new Color().alpha(0));
        questionb.setBackgroundColor(new Color().alpha(0));
        questionc.setBackgroundColor(new Color().alpha(0));
        questiond.setBackgroundColor(new Color().alpha(0));
        imaa.setImageResource(R.drawable.jytz_quan);
        imab.setImageResource(R.drawable.jytz_quan);
        imac.setImageResource(R.drawable.jytz_quan);
        imad.setImageResource(R.drawable.jytz_quan);
        jyqstbody.setText(q.getDatas().get(qst - 1).getAprizeQuestion().getContent());
        jyqstqst.setText(q.getDatas().get(qst - 1).getAprizeQuestion().getQuestion());
        questiona.setText(q.getDatas().get(qst - 1).getAprizeAnswer().get(0).getAnswer());
        questionb.setText(q.getDatas().get(qst - 1).getAprizeAnswer().get(1).getAnswer());
        questionc.setText(q.getDatas().get(qst - 1).getAprizeAnswer().get(2).getAnswer());
        questiond.setText(q.getDatas().get(qst - 1).getAprizeAnswer().get(3).getAnswer());
        String imaurl = Apis.Baseima + q.getDatas().get(qst - 1).getAprizeQuestion().getImg();
        Glide.with(Wzy_AnswerActivity.this).load(imaurl).into(jyqstima);
        jymanposition.setText("您是第" + q.getDatas().get(qst - 1).getAprizeQuestion().getNumber() + "个回答此题的");


    }


    private void initHandler() {
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                sec--;
                if (sec > 0) {
//                    if (sec < 5) {
//                        nextquesition.setEnabled(true);
//                    } else {
//                        nextquesition.setEnabled(false);
//                    }
                    timeChange();
                    djsjy.setText(sec + "");
                    if (handler != null && runnable != null) {
                        handler.postDelayed(runnable, 1000);
                    }
                } else {
                    if (qst < 10) {
                        if (handler != null) {
                            if (runnable != null) {
                                handler.removeCallbacks(runnable);
                                runnable = null;
                            }
                            handler = null;
                        }
                        initHandler();

                        if (isanswer == 0) {
                            no++;
                            if (wrongAnswer.length() == 0) {
                                wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                            } else {
                                wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                            }
                        }
                        qst++;
                        changeposition();
                        jyquessitionposition.setText(qst + "");
//                        showShort("更新答题题目");
                        djs();
                    } else {
                        if (isanswer == 0) {
                            no++;
                            if (wrongAnswer.length() == 0) {
                                wrongAnswer = wrongAnswer + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                            } else {
                                wrongAnswer = wrongAnswer + "," + q.getDatas().get(qst - 1).getAprizeRecord().getId();
                            }
                        }
                        Intent it = new Intent(Wzy_AnswerActivity.this, JY_ResultActivity.class);
                        it.putExtra("yes", yes + "");
                        it.putExtra("no", no + "");
                        it.putExtra("jb", jb + "");
                        it.putExtra("rightAnswer", rightAnswer);
                        it.putExtra("wrongAnswer", wrongAnswer);
                        it.putExtra("type", "JY");
                        startActivity(it);
                        finish();

                    }

                }
            }
        };
    }


    private Handler handler;
    private Runnable runnable;
    private int sec = 15;
    private int qst = 1;
    private int correctlyDelay;

    public void djs() {
        sec = 15;
        handler.post(runnable);
        if (Utils.isApkDebugable(getApplicationContext())) {
            List<QuersitionBean.DatasBean.AprizeAnswerBean> list = q.getDatas().get(qst - 1).getAprizeAnswer();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getIsRight() == 0) {
                    String msg = "";
                    switch (i) {
                        case 0:
                            msg = "A";
                            break;
                        case 1:
                            msg = "B";
                            break;
                        case 2:
                            msg = "C";
                            break;
                        case 3:
                            msg = "D";
                            break;
                    }
                    showShort(msg);
                    return;
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            if (runnable != null) {
                handler.removeCallbacks(runnable);
                runnable = null;
            }
            handler = null;
        }
    }
}
