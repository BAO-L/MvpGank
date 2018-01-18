package com.example.bao.mvpgank.ui.activity;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.bao.mvpgank.R;
import com.example.bao.mvpgank.base.BaseActivity;
import com.example.bao.mvpgank.widget.ProgressWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity {

    @BindView(R.id.webview)
    ProgressWebView webview;
    private String url;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        handleIntent();
        getToolbar().setTitle(title);

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);

        //在当晚webview打开链接
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webview.loadUrl(url);
//        setContentView(R.layout.activity_web);

    }

    private void handleIntent(){
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }
}
