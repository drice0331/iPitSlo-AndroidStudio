package com.thepit.ipitslo.util;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by DrIce on 3/29/15.
 */
public class CustomWebViewClient  extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


}
