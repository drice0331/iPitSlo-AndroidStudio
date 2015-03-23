package com.thepit.ipitslo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.thepit.ipitslo.R;

public class BlogDetailActivity extends Activity {

    WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);

        Intent i = getIntent();
        String url = i.getStringExtra("url");

        webView = (WebView)findViewById(R.id.blog_detail_webview);
        webView.loadUrl(url);
    }


}
