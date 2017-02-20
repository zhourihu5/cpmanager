package cn.xiaoneng.demo;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.xiaoneng.coreapi.ChatParamsBody;
import cn.xiaoneng.coreapi.TrailActionBody;
import cn.xiaoneng.uiapi.Ntalker;
import cn.xiaoneng.uiapi.XNSDKListener;
import cn.xiaoneng.utils.CoreData;
import cn.xiaoneng.utils.MyUtil;
import cn.xiaoneng.utils.XNLOG;

import com.example.XNChatDemo3.R;

public class Home extends Activity implements OnClickListener, XNSDKListener// 实现该接口可以获得来自SDK的消息（该接口包括若干个方法）
{

	/******************************************* 集成SDK相关参数如下 ***********************************************/
	// 企业
	public static String siteid = "kf_9979,kf_8002";// 示例kf_9979,kf_8002,kf_3004,zf_1000,yy_1000
	public static String sdkkey = "FB7677EF-00AC-169D-1CAD-DEDA35F9C07B";// 示例FB7677EF-00AC-169D-1CAD-DEDA35F9C07B
	// 用户
	String userid = "";// 客户唯一标识，切忌不同客户使用同一个userid，未登录访客请传"",不能传除 . - @
						// 三个之外的特殊字符
	String username = "";// 顾客名字
	int userlevel = 0;// 用户级别,级别分为1-5,5为最高级,默认为0 非会员
		// kf_9979_1463105838610 sdk003
		// kf_9979_1463120720614 sdk004
		// kf_9979_1463106684729 neng4
		// yy_1000_1431485040504
		// kf_3004_1463278460305 jiaojiaorobot
		// kf_3004_1463278427985 jiaojiao
		// kf_3004_1467166674953 jiaojiaoee
		// zf_2356_9999     
		// zf_1000_1463392787938
	//zy_1000_1471420820467
//	zy_5678_9999
	//XPushTest001 kf_3004_1470910557323
	//XPushTest001 kf_9979_1470639363353
	//XPushTest002 kf_9979_1470639438100
//	kf_4118_1467605246559
//	kf_4118_1467605213440
	String settingid1 = "kf_9979_1452750735837";// 客服组id示例kf_9979_1452750735837
	String settingid2 = "kf_9979_1452750735837,zf_2356_9999";// 客服组id示例kf_9979_1452750735837,zf_2356_9999
												// 接待组 2
	String settingid3 = "";
	String groupName = "";// 客服组默认名称

	String kfuid = "";// 指定客服id（一般指定客服时才需传）格式：siteid_ISME9754_T2D_客服id.示例：kf_9979_ISME9754_T2D_admin,默认空字符
	String kfname = "";// 指定客服昵称,默认空字符
	// 聊天信息实例
	ChatParamsBody chatparams = null;
	// 轨迹（专有参数）
	String sellerid = "";// 商户id,平台版企业(B2B2C企业)使用此参数，B2C企业此参数传""
	String ttl = "";// 当前页标题，如首页、购物车、订单、支付
	String url = "";// 当前页地址
	String ref = "";// 前一页面的地址
	String orderid = "";// 订单id
	String orderprice = "";// 订单价格
	int isvip = 0;// 是否为vip会员
	TrailActionBody trailparams = null;// 轨迹信息实例

	// 返回值
	int initSDK = 0;
	int enableDebug = 0;
	int logIn = 0;
	int logOut = 0;
	int startChat = 0;
	int action = 0;
	int destory = 0;

	/*******************************************************************************************************/
	LinearLayout btLogin;
	LinearLayout btLogoff;
	LinearLayout  btChat1;
	TextView unRead1;
	LinearLayout  btChat2;
	LinearLayout  btChat3;
	TextView unRead2;
	LinearLayout btTrackMain;
	LinearLayout btTrackGoods;
	LinearLayout btTrackCart;
	LinearLayout btTrackOrder;
	LinearLayout btTrackPay;
	LinearLayout btDestory;
	
	LinearLayout kefulist;
	EditText etUserid;
	EditText etUsername;
	TextView tv_title;

	Ringtone ringtonenotification;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		initView();
		Uri notification = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.msgnotifyvoice);
		ringtonenotification = RingtoneManager.getRingtone(this, notification);

		/*************************************** 设置小能客服SDK的监听器 **********************************************/
		Ntalker.getInstance().setSDKListener(this);// 小能监听接口
//		Ntalker.getInstance().setShowCard(true);// 是否以连接名片的形式显示链接, true为开通
		Ntalker.getInstance().setShowVideo(true);
		/*******************************************************************************************************/
	}

	/**
	 * 初始化控件
	 */
	private void initView()
	{
		btLogin = (LinearLayout) findViewById(R.id.login);
		btLogoff = (LinearLayout) findViewById(R.id.logoff);
		btChat1 = (LinearLayout) findViewById(R.id.chat1);
		unRead1 = (TextView) findViewById(R.id.unRead1);
		btChat2 = (LinearLayout) findViewById(R.id.chat2);
		unRead2 = (TextView) findViewById(R.id.unRead2);
		btChat3 = (LinearLayout) findViewById(R.id.chat3);
		btTrackMain = (LinearLayout) findViewById(R.id.track_main);
		btTrackGoods = (LinearLayout) findViewById(R.id.track_goods);
		btTrackCart = (LinearLayout) findViewById(R.id.track_cart);
		btTrackOrder = (LinearLayout) findViewById(R.id.track_order);
		btTrackPay = (LinearLayout) findViewById(R.id.track_pay);
		btDestory = (LinearLayout) findViewById(R.id.destory);
		etUserid = (EditText) findViewById(R.id.userid);
		etUsername = (EditText) findViewById(R.id.username);
		tv_title = (TextView) findViewById(R.id.tv_title);
		kefulist = (LinearLayout) findViewById(R.id.kefulist);

		btLogin.setOnClickListener(this);
		btLogoff.setOnClickListener(this);
		btChat1.setOnClickListener(this);
		btChat2.setOnClickListener(this);
		btTrackMain.setOnClickListener(this);
		btTrackCart.setOnClickListener(this);
		btTrackOrder.setOnClickListener(this);
		btTrackPay.setOnClickListener(this);
		btTrackGoods.setOnClickListener(this);
		btDestory.setOnClickListener(this);
		etUserid.setOnClickListener(this);
		etUsername.setOnClickListener(this);
		btChat3.setOnClickListener(this);
		kefulist.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{

		switch (v.getId())
		{
		
			case R.id.kefulist:
			Intent intent = new Intent(this, SettingListActivity.class);
			startActivity(intent);
			break;
		/*************************************** 打开SDK聊窗需要调用的方法 *************************************/
			case R.id.chat1:
				chatparams = new ChatParamsBody();

				// 咨询发起页（专有参数）
				chatparams.startPageTitle = "1111111女装/女士精品 - 服饰 - 搜索店铺 - ECMall演示站";
				chatparams.startPageUrl = "http://img.meicicdn.com/p/51/050010/h-050010-1.jpg";

				// 文本匹配参数，在sdk外部打开
//				chatparams.matchstr = "www.baidu.com";

				// erp参数
				chatparams.erpParam = "";
				
				// 此参数不传就默认在sdk外部打开，即在onClickUrlorEmailorNumber方法中打开
				chatparams.clickurltoshow_type = CoreData.CLICK_TO_SDK_EXPLORER;
				
				// 商品展示（专有参数）
				
				chatparams.itemparams.clientgoodsinfo_type = CoreData.SHOW_GOODS_BY_ID;
				chatparams.itemparams.clicktoshow_type = CoreData.CLICK_TO_SDK_EXPLORER;
				
				chatparams.itemparams.itemparam = "";
				
				//使用id方式，（SHOW_GOODS_BY_ID）
				chatparams.itemparams.appgoodsinfo_type = CoreData.SHOW_GOODS_BY_ID;
				chatparams.itemparams.goods_id = "ntalker_test";//ntalker_test
				
				//使用widget方式，（SHOW_GOODS_BY_WIDGET）
//				chatparams.itemparams.appgoodsinfo_type = CoreData.SHOW_GOODS_BY_WIDGET;
//				chatparams.itemparams.goods_name = "2015年最新潮流时尚T恤偶像同款一二线城市包邮 速度抢购有超级大礼包等你来拿";
//				chatparams.itemparams.goods_price = "￥：188";
//				chatparams.itemparams.goods_image = "http://img.meicicdn.com/p/51/050010/h-050010-1.jpg";
//				chatparams.itemparams.goods_url = "http://www.baidu.com";
				
				//使用商品url方式（SHOW_GOODS_BY_URL）
//				chatparams.itemparams.appgoodsinfo_type = CoreData.SHOW_GOODS_BY_URL;
//				chatparams.itemparams.goods_showurl = "http://pic.shopex.cn/pictures/goodsdetail/29b.jpg?rnd=111111";//

	
				startChat = Ntalker.getInstance().startChat(getApplicationContext(), settingid1, groupName, null, null,
							chatparams);
				
				
				
				if (0 == startChat) {
					Log.e("startChat", "打开聊窗成功");
				} else {
					Log.e("startChat", "打开聊窗失败，错误码:" + startChat);
				}
				break;
			case R.id.chat2:
				chatparams = new ChatParamsBody();

				// 咨询发起页（专有参数）
				chatparams.startPageTitle = "1111111女装/女士精品 - 服饰 - 搜索店铺 - ECMall演示站";
				chatparams.startPageUrl = "http://img.meicicdn.com/p/51/050010/h-050010-1.jpg";

				// 文本匹配参数，在sdk外部打开
//				chatparams.matchstr = "www.baidu.com";

				// erp参数
				chatparams.erpParam = "";
				
				// 此参数不传就默认在sdk外部打开，即在onClickUrlorEmailorNumber方法中打开
				chatparams.clickurltoshow_type = CoreData.CLICK_TO_SDK_EXPLORER;
				
				// 商品展示（专有参数）
				chatparams.itemparams.appgoodsinfo_type = CoreData.SHOW_GOODS_BY_ID;
				chatparams.itemparams.clientgoodsinfo_type = CoreData.SHOW_GOODS_BY_ID;
				chatparams.itemparams.clicktoshow_type = CoreData.CLICK_TO_SDK_EXPLORER;
				
				chatparams.itemparams.itemparam = "";
				
				//使用id方式，（SHOW_GOODS_BY_ID）
				chatparams.itemparams.goods_id = "ntalker_test";//ntalker_test
				
				//使用widget方式，（SHOW_GOODS_BY_WIDGET）
//				chatparams.itemparams.goods_name = "2015年最新潮流时尚T恤偶像同款一二线城市包邮 速度抢购有超级大礼包等你来拿";
//				chatparams.itemparams.goods_price = "￥：188";
//				chatparams.itemparams.goods_image = "http://img.meicicdn.com/p/51/050010/h-050010-1.jpg";
//				chatparams.itemparams.goods_url = "http://www.baidu.com";
				
				//使用商品url方式（SHOW_GOODS_BY_URL）
//				chatparams.itemparams.goods_showurl = "http://pic.shopex.cn/pictures/goodsdetail/29b.jpg?rnd=111111";//

				startChat = Ntalker.getInstance().startChat(getApplicationContext(), settingid2, groupName, null, null, chatparams);

				if (0 == startChat)
				{
					Log.e("startChat", "打开聊窗成功");
				}
				else
				{
					Log.e("startChat", "打开聊窗失败，错误码:" + startChat);
				}
				break;
				
			case R.id.chat3:
				chatparams = new ChatParamsBody();

				chatparams.startPageTitle = "女装/女士精品 - 服饰 - 搜索店铺 - ECMall演示站";
				chatparams.startPageUrl = "http://img.meicicdn.com/p/51/050010/h-050010-1.jpg";

				chatparams.matchstr = "";

				chatparams.erpParam = "";

				// 商品展示（专有参数）
				chatparams.itemparams.appgoodsinfo_type = CoreData.SHOW_GOODS_BY_ID;
				chatparams.itemparams.clientgoodsinfo_type = CoreData.SHOW_GOODS_BY_ID;
				chatparams.itemparams.clicktoshow_type = CoreData.CLICK_TO_APP_COMPONENT;

				chatparams.itemparams.itemparam = "";//
				chatparams.itemparams.goods_id = "387";// ntalker_test

				startChat = Ntalker.getInstance().startChat(getApplicationContext(), settingid3, groupName, null, null, chatparams);

				if (0 == startChat)
				{
					Log.e("startChat", "打开聊窗成功");
				}
				else
				{
					Log.e("startChat", "打开聊窗失败，错误码:" + startChat);
				}
				break;
			/*************************************** 用户登录账号时需要调用的方法 *************************************/
			case R.id.login:
				if (TextUtils.isEmpty(etUserid.getText()))
				{
					Toast.makeText(getApplicationContext(), "userid不能为空", Toast.LENGTH_SHORT).show();
				}
				else
				{
					userid = etUserid.getText().toString().trim();
					username = etUsername.getText().toString().trim();

					logIn = Ntalker.getInstance().login(userid, username, userlevel);// 登录时调
					if (0 == logIn)
					{
						tv_title.setText(etUserid.getText());
						Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(this, "登录失败，错误码:" + logIn, Toast.LENGTH_SHORT).show();
					}
				}
				break;
			/*************************************** 用户注销账号时需要调用的方法 *************************************/
			case R.id.logoff:// 退出登录账户调
				logOut = Ntalker.getInstance().logout();
				if (0 == logOut)
				{
					tv_title.setText("游客");
					Toast.makeText(this, "注销成功", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(this, "注销失败，错误码:" + logOut, Toast.LENGTH_SHORT).show();
				}
				break;

			/******************************************** 以下为用户轨迹 ****************************************/
			case R.id.track_main:// 当用户进入首页时调用的轨迹
				try
				{
					trailparams = new TrailActionBody();

					trailparams.ttl = "www.wbiao.cn首页";
					trailparams.url = "http://www.baidu.com";
					trailparams.sellerid = sellerid;
					trailparams.ref = ref;
					trailparams.orderid = orderid;
					trailparams.orderprice = orderprice;
					trailparams.isvip = 0;
					trailparams.userlevel = 0;
					trailparams.ntalkerparam = "";

					action = Ntalker.getInstance().startAction(trailparams);
					if (0 == action)
					{
						Toast.makeText(this, "上传轨迹成功", Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(this, "上传轨迹失败，错误码:", Toast.LENGTH_SHORT).show();
					}
				}
				catch (Exception e)
				{
				}
				break;
			case R.id.track_goods:// 用户浏览商品时调用的轨迹
				try
				{
					trailparams = new TrailActionBody();

					trailparams.ttl = "瑞士柏高Paul Picot -Atelier 美学家系列 P3058.RG.1021.7204 机械男表（万表总裁同款 天文台认证）";
					trailparams.url = "http://www.wbiao.cn/paul-picot-g43714.html";
					trailparams.sellerid = sellerid;
					trailparams.ref = "http://www.wbiao.cn/paul-picot-watches/";
					trailparams.orderid = orderid;
					trailparams.orderprice = orderprice;
					trailparams.isvip = isvip;
					trailparams.userlevel = userlevel;

					JSONObject item = MyUtil.getRequiredNtalkerParams(sellerid, "43714", "瑞士柏高Paul Picot -Atelier 美学家系列 P3058.RG.1021.7204 机械男表（万表总裁同款 天文台认证）",
							"http://img.aimer.com.cn//goods/2012/11-28/211779905_450x558.jpg", "http://www.wbiao.cn/paul-picot-g43714.html");

					JSONObject itemOptional = MyUtil.getOptionalNtalkerParams(item, "127920", "127920", null, null, null, null, null);

					JSONObject itemSelfDefine = MyUtil.getSelfDefineNtalkerParams(itemOptional, "selfdefinedparam1-orange", "12", "selfdefinedparam2-apple", "13", "selfdefinedparam3-banana", "14",
							"selfdefinedparam4-pear", "15");

					String ntalkerparamStr = MyUtil.getNtalkerParam(itemSelfDefine);

					trailparams.ntalkerparam = ntalkerparamStr; // 商品信息、购物车商品JSON字符串数据

					action = Ntalker.getInstance().startAction(trailparams);
					if (0 == action)
					{
						Toast.makeText(this, "上传轨迹成功", Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(this, "上传轨迹失败，错误码:" + action, Toast.LENGTH_SHORT).show();
					}
				}
				catch (Exception e)
				{
				}

				break;
			case R.id.track_cart:// 当用户进入购物车时调用的轨迹
				try
				{
					trailparams = new TrailActionBody();

					trailparams.ttl = "购物车";
					trailparams.url = url;
					trailparams.sellerid = sellerid;
					trailparams.ref = "http://www.wbiao.cn/paul-picot-g43714.html";
					trailparams.orderid = orderid;
					trailparams.orderprice = orderprice;
					trailparams.isvip = isvip;
					trailparams.userlevel = userlevel;
					trailparams.ntalkerparam = "";

					action = Ntalker.getInstance().startAction(trailparams);
					if (0 == action)
					{
						Toast.makeText(this, "上传轨迹成功", Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(this, "上传轨迹失败，错误码:" + action, Toast.LENGTH_SHORT).show();
					}
				}
				catch (Exception e)
				{
				}
				break;
			case R.id.track_order:// 当用户进入订单页时调用的轨迹
				try
				{
					trailparams = new TrailActionBody();

					trailparams.ttl = "订单页";
					trailparams.url = url;
					trailparams.sellerid = sellerid;
					trailparams.ref = ref;
					trailparams.orderid = "12345678";
					trailparams.orderprice = "165120";
					trailparams.isvip = isvip;
					trailparams.userlevel = userlevel;
					trailparams.ntalkerparam = "";

					action = Ntalker.getInstance().startAction(trailparams);
					if (0 == action)
					{
						Toast.makeText(this, "上传轨迹成功", Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(this, "上传轨迹失败，错误码:" + action, Toast.LENGTH_SHORT).show();
					}
				}
				catch (Exception e)
				{

				}
				break;
			case R.id.track_pay:// 当用户支付成功后所调用轨迹
				try
				{
					trailparams = new TrailActionBody();

					trailparams.ttl = "支付页——支付成功";
					trailparams.url = url;
					trailparams.sellerid = sellerid;
					trailparams.ref = ref;
					trailparams.orderid = "12345678";
					trailparams.orderprice = "165120";
					trailparams.isvip = isvip;
					trailparams.userlevel = userlevel;
					trailparams.ntalkerparam = "";

					action = Ntalker.getInstance().startAction(trailparams);
					if (0 == action)
					{
						Toast.makeText(this, "上传轨迹成功", Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(this, "上传轨迹失败，错误码:" + action, Toast.LENGTH_SHORT).show();
					}
				}
				catch (Exception e)
				{

				}
				break;
//			case R.id.destory:
//				destory = Ntalker.getInstance().destroy();
//				if (0 == destory)
//				{
//					Toast.makeText(this, "注销SDK成功", Toast.LENGTH_SHORT).show();
//				}
//				else
//				{
//					Toast.makeText(this, "注销SDK失败，错误码:" + action, Toast.LENGTH_SHORT).show();
//				}F
//				break;
		}
	}

	@Override
	public void onChatMsg(boolean isSelfMsg, String settingid, String uname, String msgcontent, long msgtime, boolean hasunread)
	{
		// XNSDKListener 方法1/6 所有聊天消息监听

		if (ringtonenotification == null)
			return;
		if (isSelfMsg == false)
			ringtonenotification.play();

	}

	@Override
	public void onUnReadMsg(final String settingid, String username, String msgcontent, final int messagecount)
	{
		// XNSDKListener 方法2/6 未读聊天消息监听

		etUserid.post(new Runnable()
		{
			@Override
			public void run()
			{
				if (messagecount == 0)
				{
					if (settingid.equals(settingid1))
					{
						unRead1.setText("未读消息");
					}
					else if (settingid.equals(settingid2))
					{
						unRead2.setText("未读消息");
					}

					NotifyUnReadMsg.getInstance(getApplicationContext()).dissmissNotifyCation();

					return;
				}
				if (settingid.equals(settingid1))
				{
					unRead1.setText(settingid + "：" + messagecount + "条新消息");
				}
				else if (settingid.equals(settingid2))
				{
					unRead2.setText(settingid + "：" + messagecount + "条新消息");
				}
				String unreadmsgstr = settingid + "发来" + messagecount + "条消息哦！";

				NotifyUnReadMsg.getInstance(getApplicationContext()).setNotiType(R.drawable.visitor, "小能客服", unreadmsgstr, Home.class);
			}
		});
	}

	@Override
	public void onClickMatchedStr(String returnstr, String matchstr)
	{
		// XNSDKListener 方法3/6 匹配字符串点击事件监听

		Intent intent = new Intent(this, WebContentActivity.class);
		intent.putExtra("url", returnstr);// 实际打开的链接
		startActivity(intent);
	}

	@Override
	public void onClickShowGoods(int appgoodsinfo_type, int clientgoodsinfo_type, String goods_id, String goods_name, String goods_price, String goods_image, String goods_url, String goods_showurl)
	{
		// XNSDKListener 方法5/6 商品信息展示点击事件监听

		Intent intent = new Intent(this, WebContentActivity.class);
		intent.putExtra("url", goods_url);// 实际打开的链接
		startActivity(intent);
	}

	@Override
	public void onError(int errorcode)
	{
		// XNSDKListener 方法6/6 集成错误监听
		Toast.makeText(getApplicationContext(), "警告！发生错误（" + errorcode + "）！", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClickUrlorEmailorNumber(int contentType, String urlorEmailorNumber)
	{
		if (contentType == 1)
		{
			// 网址
			Intent intent = new Intent(this, WebContentActivity.class);
			intent.putExtra("urlorEmailorNumber", urlorEmailorNumber);// 实际打开的链接
			startActivity(intent);
		}
		
	}

}
