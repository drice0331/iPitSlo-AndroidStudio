package com.thepit.ipitslo.ui;

import android.os.Bundle;
import android.webkit.WebView;

import com.thepit.ipitslo.R;
import com.thepit.ipitslo.util.CoreConstants;
import com.thepit.ipitslo.util.CustomWebViewClient;

import roboguice.inject.InjectView;

public class TwitterActivity extends BaseActivity {

    @InjectView(R.id.twitterwebview)
	protected WebView webview;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitter);

        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new CustomWebViewClient());
		webview.loadUrl(CoreConstants.TWITTER_URL);
	}
}
