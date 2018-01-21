package com.example.bao.mvpgank.base;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebHistoryItem;
import android.widget.TextView;

import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.utils.DayNightHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BAO on 2017/12/7.
 */

public abstract class BaseActivity extends AppCompatActivity {


    private static final String TAG = BaseActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_text)
    TextView title;
    @BindView(R.id.subtitle_text)
    TextView subTitle;

    @Override
    protected void onStart() {
        super.onStart();
        /**
         * 判断是否有Toolbar,并默认显示返回按钮
         */
        if(null != getToolbar() && isShowingBack()){
            showBack();
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        if (toolbar != null){
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setTitle("");
            setSupportActionBar(toolbar);
        }

    }

    protected abstract int getLayoutId();

    public void setTitle(String titleText){
        if (title != null)
            title.setText(titleText);
    }

    public void setSubTitle(String subTitleText){
        if (subTitle != null)
            subTitle.setText(subTitleText);
    }

    protected boolean isShowingBack(){
        return true;
    }

    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    private void showBack(){
        getToolbar().setNavigationIcon(R.drawable.ic_back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }



}
