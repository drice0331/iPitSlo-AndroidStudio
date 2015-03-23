package com.thepit.ipitslo.ui;

//import roboguice.inject.InjectView;
import android.os.Bundle;
import android.webkit.WebView;

import com.thepit.ipitslo.R;
import com.thepit.ipitslo.util.CoreConstants;

public class FacebookActivity extends BaseActivity {

	//@InjectView(R.id.fbwebview)
	WebView myWebView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facebook);
		
		myWebView = (WebView)findViewById(R.id.fbwebview);
		myWebView.loadUrl(CoreConstants.FB_URL);
	}
}
