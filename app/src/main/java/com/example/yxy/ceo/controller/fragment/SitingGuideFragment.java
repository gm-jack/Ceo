package com.example.yxy.ceo.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yxy.ceo.R;
import com.example.yxy.ceo.base.BaseFragment;

/**
 * 座次引导界面
 */
public class SitingGuideFragment extends BaseFragment {

    private WebView wv_commen_html;
    private ImageView iv_title_main_back;
    private TextView tv_title_main_name;
    private ProgressBar pb_commen_html;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        wv_commen_html = (WebView) view.findViewById(R.id.wv_commen_html);
        iv_title_main_back = (ImageView) view.findViewById(R.id.iv_title_main_back);
        tv_title_main_name = (TextView) view.findViewById(R.id.tv_title_main_name);
        pb_commen_html = (ProgressBar) view.findViewById(R.id.pb_commen_html);

        iv_title_main_back.setVisibility(View.VISIBLE);
        tv_title_main_name.setText(R.string.title_text_siting_guide);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_commen_webview;
    }

    @Override
    protected void initData() {
        initListener();

        WebSettings settings = wv_commen_html.getSettings();
        //支持javascript
        settings.setJavaScriptEnabled(true);
        //开启localStorage缓存
        settings.setDomStorageEnabled(true);
        //开启缓存模式
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 应用可以有数据库
//        settings.setDatabaseEnabled(true);
//        String dbPath = mContext.getDir("database", Context.MODE_PRIVATE).getPath();
//        settings.setDatabasePath(dbPath);
        // 应用可以有缓存
        settings.setAppCacheEnabled(true);
        String appCaceDir = mContext.getDir("cache", Context.MODE_PRIVATE).getPath();
        settings.setAppCachePath(appCaceDir);

        wv_commen_html.loadUrl("http://www.baidu.com");
        wv_commen_html.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
        wv_commen_html.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    pb_commen_html.setVisibility(View.GONE);
                } else {
                    if (pb_commen_html.getVisibility() == View.GONE) {
                        pb_commen_html.setVisibility(View.VISIBLE);
                    }
                    pb_commen_html.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    private void initListener() {
        iv_title_main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHoldingActivity().finish();
            }
        });
    }
}
