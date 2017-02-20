package cn.xiaoneng.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.XNChatDemo3.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;
import cn.xiaoneng.uiapi.Ntalker;
import cn.xiaoneng.utils.XNLOG;


public class DemoActivity extends Activity {

	List<Map<String,Object>> orderlist = new ArrayList<Map<String,Object>>();
	OrderAdapter orderlistadapter = null;
	ListView list_order;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		orderlist = getData();
		
		list_order = (ListView) findViewById(R.id.list_order);
		
		
		orderlistadapter = new OrderAdapter(this, orderlist);
		list_order.setAdapter(orderlistadapter);
		list_order.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				RadioButton radio_btn = (RadioButton) view.findViewById(R.id.radio_btn);
				orderlistadapter.clearStates(position);
				
				radio_btn.setChecked(orderlistadapter.getStates(position));
				orderlistadapter.notifyDataSetChanged();
				
				String title = (String) orderlist.get(position).get("title");
				Ntalker.getInstance().sendTextMessage(title);
				Toast.makeText(getApplicationContext(), "item点击位置"+position+",title="+title, Toast.LENGTH_SHORT).show();

				finish();
			}
		});
		
	}
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
        Map<String, Object> map = null;
        String url = "http://release-rd2.ntalker.com:1300/func/imagetrans/image2.php?f=ordqcNIziPFDAv1UG22WarMitMgiHCv5PdTu/iqG&q=o7JqJ9RkhK1dBPpXHGSabb086Z5FWTWjeZyjoT7RTVaTI2RSopzIHRcmlAv7xqz+PiSz8f5Y2QtWcAkfkaZeR2ZjI3+KSDVB";
        for (int i = 0; i < 20; i++) {
        	map = new HashMap<String, Object>();
        	map.put("image",url);
            map.put("title", "G"+i);
            map.put("desc", "google"+i);
			maps.add(map);
			XNLOG.i("mapmap=="+map.get("title"));
		}
        return maps;
    }
}
