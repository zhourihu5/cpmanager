package cn.xiaoneng.h5_sdk;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.xiaoneng.demo.Home;
import cn.xiaoneng.demo.WebContentActivity;
import cn.xiaoneng.uiapi.Ntalker;
import cn.xiaoneng.uiapi.XNSDKListener;

import com.example.XNChatDemo3.R;

public class MainContentActivity extends Activity implements XNSDKListener//实现小能客服SDK监听器
{
	private LinearLayout ll;
	private EditText et_h5;
	private Button btn;
	private WebView webView;
	//http://h5.m.jia.com/beijing.html 齐家网
	//http://release-rd1.ntalker.com:10/t2d/kf_3004/index.php
	public String h5url = "http://release-rd1.ntalker.com:140/rd/kf_3004/index.php";//h5页面地址，示例：http://download.rd.ntalker.com/9979.html
	
	public String siteid = "kf_9979";//企业siteid示例kf_9979
	public String sdkKey = "233";//
	
	public String groupName = "H52SDK混合方案测试";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_content);
		webView = (WebView) findViewById(R.id.webview);
		ll = (LinearLayout) findViewById(R.id.ll);
		et_h5 = (EditText) findViewById(R.id.et_h5);
		btn = (Button) findViewById(R.id.btn);
		
		//开启调试模式
		Ntalker.getInstance().enableDebug(true);
		
		//初始化小能客服SDK
		Ntalker.getInstance().initSDK(getApplicationContext(), Home.siteid, Home.sdkkey);
		
		Ntalker.getInstance().setLinkInteralOpenEnable(false);
		//注册小能客服SDK监听器
		Ntalker.getInstance().setSDKListener(this);
		
		et_h5.setText(h5url);
		
		final SPHelper sp = new SPHelper(MainContentActivity.this, "url");
		String urls = sp.getValue("url");
		if (!TextUtils.isEmpty(urls))
		{
			et_h5.setText(urls);
		}
		
		btn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String h5 = et_h5.getText().toString().trim();
				if (TextUtils.isEmpty(siteid) || TextUtils.isEmpty(sdkKey)||TextUtils.isEmpty(h5)) {
					Toast.makeText(getApplicationContext(), "请输入企业id和sdkkey和h5地址", Toast.LENGTH_SHORT).show();
					return;
				} 
				
				if (h5 != null && !TextUtils.isEmpty(h5))
				{
					h5url = h5;
					sp.putValue("url", h5);
					ll.setVisibility(View.GONE);
					load();
				}
			}
		});
	}

	private void load()
	{
		WebSettings settings = webView.getSettings();
		settings.setDefaultTextEncodingName("UTF-8");
		settings.setUseWideViewPort(true);
		settings.setSupportMultipleWindows(false);
		settings.setLoadsImagesAutomatically(true);
		settings.setLoadWithOverviewMode(false);
		settings.setJavaScriptEnabled(true);
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		settings.setAppCacheEnabled(false);

		Ntalker.getInstance().setH5WebView(MainContentActivity.this, webView, groupName);

		webView.setWebViewClient(new WebViewClient()
		{
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				view.loadUrl(url);
				return true;
			}
		});
		
		if (!h5url.contains("http://"))
		{
			h5url = "http://" + h5url;
		}
		
		Log.i("H5-SDK","h5url="+h5url);
		
		webView.loadUrl(h5url);
	}

	@Override
	public void onChatMsg(boolean isSelfMsg, String settingid, String username, String msgcontent, long msgtime,boolean isunread)
	{

	}

	@Override
	public void onUnReadMsg(String settingid,String username, String msgcontent, int messagecount)
	{

	}

	@Override
	public void onClickMatchedStr(String returnstr, String matchstr)
	{

	}

	@Override
	public void onClickShowGoods(int appgoodsinfo_type, int clientgoodsinfo_type, String goods_id, String goods_name, String goods_price, String goods_image, String goods_url, String goods_showurl)
	{

	}

	@Override
	public void onError(int errorcode)
	{

	}

	/**
	 * 文本消息中的匹配链接点击事件监听
	 * 
	 * @param contentType 类型 （0：未知；1：网址；2：邮箱；3：数字）
	 * @param url 网址
	 * @param email 邮箱
	 * @param number 数字
	 */
	@Override
	public void onClickUrlorEmailorNumber(int contentType,
			String urlorEmailorNumber) {
		if (contentType == 1)
		{
			// 网址
			Intent intent = new Intent(this, WebContentActivity.class);
			intent.putExtra("url", urlorEmailorNumber);// 实际打开的链接
			startActivity(intent);
		}
		else if (contentType == 2)
		{
			// 邮箱
		}
		else if (contentType == 3)
		{
			// 数字（电话）
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + urlorEmailorNumber));
			intent.putExtra(Browser.EXTRA_APPLICATION_ID, getPackageName());
			startActivity(intent);
		}
	}

}
