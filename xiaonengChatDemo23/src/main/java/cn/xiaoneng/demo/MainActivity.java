package cn.xiaoneng.demo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.example.XNChatDemo3.R;

import cn.xiaoneng.h5_sdk.MainContentActivity;
import cn.xiaoneng.uiapi.Ntalker;

public class MainActivity extends Activity
{
	WebView wv_Demo = null;
	TextView tv_welcometext;
	Button btn_login0;
	Button btn_h5;
	Button xn_supportsystem;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_h5 = (Button) findViewById(R.id.btn_h5);

		xn_supportsystem = (Button) findViewById(R.id.xiaonengkefu);
		wv_Demo = (WebView) findViewById(R.id.demo_web_view);
		tv_welcometext = (TextView) findViewById(R.id.welcometext);

		/********************************* 在onCreate中打开调试模式方法如下 （默认关闭） *************************************/
		
//		Ntalker.getInstance().setHeadIconCircle(MainActivity.this);
		int enableDebug = Ntalker.getInstance().enableDebug(true);
		if (0 == enableDebug)
		{
			Log.e("enableDebug", "执行成功");
		}
		else
		{
			Log.e("enableDebug", "执行失败，错误码:" + enableDebug);
		}
		/*******************************************************************************************************/
		/********************************* 动态申请小能SDK所需权限（与图片显示有关的存储权限；与语音消息有关的录音权限和电话权限） *************************************/
		String[] permissions = {
				Manifest.permission.READ_PHONE_STATE, 
				Manifest.permission.RECORD_AUDIO, 
				Manifest.permission.WRITE_EXTERNAL_STORAGE,
				Manifest.permission.READ_EXTERNAL_STORAGE,
				Manifest.permission.CAMERA
				};
		Ntalker.getInstance().getPermissions(this, 200, permissions);
		/*******************************************************************************************************/
		/************************ 在进入APP的主要Activity中的onCreate方法里初始化SDK相关方法如下 *****************************/
		int initSDK = Ntalker.getInstance().initSDK(getApplicationContext(), Home.siteid, Home.sdkkey);
		if (0 == initSDK)
		{
			Log.e("initSDK", "初始化SDK成功");
		}
		else
		{
			Log.e("initSDK", "初始化SDK失败，错误码:" + initSDK);
		}
		/*******************************************************************************************************/
		/********************************* 设置访客没有关闭会话，点击返回按钮，关闭会话时间：取值范围1~10分钟 *************************************/
//		Ntalker.getInstance().setCloseChatSessionTime(5);
		/*******************************************************************************************************/
		btn_h5.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, MainContentActivity.class);
				startActivity(intent);

			}
		});
		// webViewDemoInit("http://www.dangdang.com/", wv_Demo);// 网站演示
		xn_supportsystem.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, Home.class);
				startActivity(intent);
			}
		});
	}

	public void webViewDemoInit(String urls, WebView wv_demo)
	{
		wv_demo.getSettings().setJavaScriptEnabled(true);
		wv_demo.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		wv_demo.setWebViewClient(new WebViewClient()
		{
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				view.loadUrl(url);
				return true;
			}
		});
		// String executeurl = urls;
		// wv_demo.loadUrl(executeurl);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			Ntalker.getInstance().destroy();
			finish();

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
}
