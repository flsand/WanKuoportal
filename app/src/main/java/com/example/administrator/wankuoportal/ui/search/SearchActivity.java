package com.example.administrator.wankuoportal.ui.search;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.UserService;
import com.flysand.mylibrary.util.MyHandler;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

public class SearchActivity extends BaseActivity {

    private android.widget.TextView back;
    private android.support.v7.widget.Toolbar basetoolbar;
    private android.widget.ImageView delete;
    private com.zhy.view.flowlayout.TagFlowLayout idflowlayoutlishi;
    private android.widget.ImageView shuaxin;
    private com.zhy.view.flowlayout.TagFlowLayout idflowlayoutremen;
    private String[] mVals = new String[]
            {"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android"};
    private TextView searchtypetx;
    private ImageView searchtypeima;
    private android.widget.LinearLayout searchtypelin;

    private PopupWindow mPopupWindow;
    private TextView fuwu;
    private TextView fuwushang;
    private android.widget.EditText editview;
    private LinearLayout view;
    private int type = 0;  //0为服务  1为服务商
    private View pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.pop = (View) findViewById(R.id.pop);
        this.view = (LinearLayout) findViewById(R.id.view);
        this.editview = (EditText) findViewById(R.id.editview);
        this.searchtypelin = (LinearLayout) findViewById(R.id.search_type_lin);
        this.searchtypeima = (ImageView) findViewById(R.id.search_type_ima);
        this.searchtypetx = (TextView) findViewById(R.id.search_type_tx);
        this.idflowlayoutremen = (TagFlowLayout) findViewById(R.id.id_flowlayout_remen);
        this.shuaxin = (ImageView) findViewById(R.id.shuaxin);
        this.idflowlayoutlishi = (TagFlowLayout) findViewById(R.id.id_flowlayout_lishi);
        this.delete = (ImageView) findViewById(R.id.delete);
        this.basetoolbar = (Toolbar) findViewById(R.id.base_toolbar);
        this.back = (TextView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //搜索类型选择
        searchtypelin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View popupView = getLayoutInflater().inflate(R.layout.search_typechoose, null);
                mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                mPopupWindow.setTouchable(true);
                mPopupWindow.setOutsideTouchable(true);
                mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
                fuwu = (TextView) popupView.findViewById(R.id.fuwu);
                fuwushang = (TextView) popupView.findViewById(R.id.fuwushang);

                fuwu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchtypetx.setText("服务");
                        mPopupWindow.dismiss();
                        type = 0;
                    }
                });

                fuwushang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchtypetx.setText("服务商");
                        mPopupWindow.dismiss();
                        type = 1;
                    }
                });

                int i = searchtypetx.getHeight();
                i = i * -1 ;
                mPopupWindow.showAsDropDown(pop, 5, i);

            }
        });


        editview.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (TextUtils.isEmpty(editview.getText())) {
//                        showShort("请输入要搜索的内容");
                        new UserService(SearchActivity.this).setText("");
                        startActivity(SearchResult_Activity.class, "type", type + "");
                    } else {
                        new UserService(SearchActivity.this).setText(editview.getText().toString());
                        startActivity(SearchResult_Activity.class, "type", type + "", "search", editview.getText().toString());
                    }
                }
                return false;
            }
        });

        new MyHandler(500){
            @Override
            public void run() {
                InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

            }
        };

        final LayoutInflater mInflater = LayoutInflater.from(SearchActivity.this);
        idflowlayoutremen.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv, idflowlayoutremen, false);
                tv.setText(s);
                return tv;
            }
        });
        idflowlayoutlishi.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv, idflowlayoutremen, false);
                tv.setText(s);
                return tv;
            }
        });

        idflowlayoutremen.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                showShort(mVals[position]);
                return true;
            }
        });
        idflowlayoutlishi.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                showShort(mVals[position]);
                return true;
            }
        });
        shuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotateAnimation myAnimation_Rotate;
                myAnimation_Rotate = new RotateAnimation(0, 720,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                myAnimation_Rotate.setDuration(500);
                myAnimation_Rotate.setFillEnabled(true);
                myAnimation_Rotate.setFillAfter(true);
                shuaxin.startAnimation(myAnimation_Rotate);
            }
        });
    }
}
