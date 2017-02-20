package cn.xiaoneng.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import cn.xiaoneng.uiapi.Ntalker;
import cn.xiaoneng.uiapi.XNSDKListener;
import cn.xiaoneng.utils.XNLOG;

import com.example.XNChatDemo3.R;

public class SettingListActivity extends Activity implements XNSDKListener
{

	public static SettingListActivity settingListActivity= null;
	List<Map<String, Object>> settinginfolist = new ArrayList<Map<String,Object>>();
	ListviewAdapter listViewAdapter = null;
	ListView list_setting;
	ImageView  iv_back;
	private boolean isin = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settinglist);

		settingListActivity = this;
		list_setting = (ListView) findViewById(R.id.list_setting);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		Ntalker.getInstance().setSDKListener(this);// 小能监听接口
		
		if(Ntalker.getInstance().getSettingInfoList() != null)
			settinginfolist.addAll(Ntalker.getInstance().getSettingInfoList());   
			
		listViewAdapter = new ListviewAdapter(this, settinginfolist); //创建适配器
		list_setting.setAdapter(listViewAdapter);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (isin) {
			refreshList();
		}
		isin = false;
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		isin = true;
		super.onPause();
	}
	public void refreshList(){
		settinginfolist.clear();
		settinginfolist.addAll(Ntalker.getInstance().getSettingInfoList());
		listViewAdapter.notifyDataSetChanged();
	}

	@Override
	public void onChatMsg(boolean isSelfMsg, String settingid, String username, String msgcontent, long msgtime, boolean isunread) {

		if (!isin) {
			int location = 0;
			for (int i = 0 ;i<settinginfolist.size() ;i++ ) {
				if (settingid.equals(settinginfolist.get(i).get("settingid"))) {
					if (isSelfMsg == false) {
						
						settinginfolist.get(i).put("isSelfMsg", false);
						settinginfolist.get(i).put("isunread", isunread);
						settinginfolist.get(i).put("uname", username);
						settinginfolist.get(i).put("textmsg", msgcontent);
						settinginfolist.get(i).put("msgtime", msgtime);
						location = i;
					}
				}
			}
			
			if (location != 0) {
				
				Map<String, Object> settinginfomap = settinginfolist.get(location);
				settinginfolist.remove(location);
				settinginfolist.add(0,settinginfomap);
			}
			
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					listViewAdapter.notifyDataSetChanged();
					
				}
			});
		}
	}

	@Override
	public void onUnReadMsg(String settingid,String username, String msgcontent,int messagecount) {
	}

	@Override
	public void onClickMatchedStr(String returnstr, String matchstr) {
		
	}

	@Override
	public void onClickUrlorEmailorNumber(int contentType,
			String urlorEmailorNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClickShowGoods(int appgoodsinfo_type,
			int clientgoodsinfo_type, String goods_id, String goods_name,
			String goods_price, String goods_image, String goods_url,
			String goods_showurl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(int errorcode) {
		// TODO Auto-generated method stub
		
	}


}
