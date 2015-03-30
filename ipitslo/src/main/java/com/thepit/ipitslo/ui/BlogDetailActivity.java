package com.thepit.ipitslo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.thepit.ipitslo.R;
import com.thepit.ipitslo.util.CustomWebViewClient;

import roboguice.inject.InjectView;

public class BlogDetailActivity extends BaseActivity {

    @InjectView(R.id.blog_detail_webview)
    protected WebView webview;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);

        Intent i = getIntent();
        String url = i.getStringExtra("url");

        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new CustomWebViewClient());
        webview.loadUrl(url);
    }


}
