package cn.xiaoneng.demo;

import cn.xiaoneng.utils.XNLOG;

import com.example.XNChatDemo3.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebContentActivity extends Activity {

	private WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webcontent);
		webView = (WebView) findViewById(R.id.wv);

		Intent intent = getIntent();
		String urls =  intent.getStringExtra("urlorEmailorNumber");
		XNLOG.i("contentType == 1/urls111="+urls);
		if (TextUtils.isEmpty(urls)) {
			return;
		}
		if(urls.startsWith("http") == false)
			urls = "http://" + urls;
		WebSettings settings = webView.getSettings();
		settings.setDefaultTextEncodingName("UTF-8");
		settings.setUseWideViewPort(true);
		settings.setSupportMultipleWindows(true);
		settings.setLoadsImagesAutomatically(true);
		settings.setLoadWithOverviewMode(false);
		settings.setJavaScriptEnabled(true);

		//		webView.requestFocus();

		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				view.loadUrl(url);
				return true;
			}
		});
		webView.loadUrl(urls);
	}
}
