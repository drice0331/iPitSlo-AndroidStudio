package com.thepit.ipitslo.ui;

//import roboguice.inject.InjectView;
import android.os.Bundle;
import android.webkit.WebView;

import com.thepit.ipitslo.R;
import com.thepit.ipitslo.util.CoreConstants;

public class TwitterActivity extends BaseActivity {
	
	//@InjectView(R.id.twitterwebview)
	private WebView webview;
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitter);
		
		webview = (WebView) findViewById(R.id.twitterwebview);
		webview.loadUrl(CoreConstants.TWITTER_URL);
	}
}
