package com.thepit.ipitslo.ui;

import android.os.Bundle;
import android.webkit.WebView;

import com.thepit.ipitslo.R;
import com.thepit.ipitslo.util.CoreConstants;
import com.thepit.ipitslo.util.CustomWebViewClient;

import roboguice.inject.InjectView;

public class FacebookActivity extends BaseActivity {

	@InjectView(R.id.fbwebview)
    protected WebView myWebView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facebook);

        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new CustomWebViewClient());
		myWebView.loadUrl(CoreConstants.FB_URL);
	}
}
