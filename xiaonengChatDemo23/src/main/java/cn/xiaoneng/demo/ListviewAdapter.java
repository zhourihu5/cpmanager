package cn.xiaoneng.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.xiaoneng.uiapi.Ntalker;
import cn.xiaoneng.utils.XNLOG;

import com.example.XNChatDemo3.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class ListviewAdapter extends BaseAdapter{
	private Context context; // 运行上下文
	private List<Map<String, Object>> listItems; // 商品信息集合
	private LayoutInflater listContainer; // 视图容器

	public ListviewAdapter(Context context, List<Map<String, Object>> listItems) {
		this.context = context;
		listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
		this.listItems = listItems;
	}
	 public final class ListItemView{                //自定义控件集合     
         public TextView tv_settingname;     
//         public TextView tv_kfname;   
         public TextView tv_msginfo; 
         public TextView tv_time;
		 public ImageButton point;
  }     
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 ListItemView  listItemView = null;   
	        if (convertView == null) {   
	            listItemView = new ListItemView();    
	            //获取list_item布局文件的视图   
	            convertView = listContainer.inflate(R.layout.list_item2, null);   
	            //获取控件对象   
	            listItemView.tv_settingname =  (TextView)convertView.findViewById(R.id.tv_settingname);   
	            listItemView.tv_msginfo = (TextView)convertView.findViewById(R.id.tv_msginfo);   
//	            listItemView.tv_kfname = (TextView)convertView.findViewById(R.id.tv_kfname); 
	            listItemView.tv_time = (TextView)convertView.findViewById(R.id.tv_time);
	            listItemView.point = (ImageButton) convertView.findViewById(R.id.point);
	            //设置控件集到convertView   
	            convertView.setTag(listItemView);   
	        }else {   
	            listItemView = (ListItemView)convertView.getTag();   
	        } 
	        
	        final Map<String,Object> settinginfo = listItems.get(position);
	        listItemView.tv_settingname.setText((String)settinginfo.get("settingid"));
	        if (!(Boolean) settinginfo.get("isSelfMsg")) //客服消息时，显示客服名字
			{
//	        	 listItemView.tv_kfname.setText("客服"+(String)settinginfo.get("uname")+":");
	        	 
	        	 boolean isunread = (Boolean) settinginfo.get("isunread");
	        	 if (isunread) {
	        		 listItemView.point.setVisibility(View.VISIBLE);
				}else{
					listItemView.point.setVisibility(View.GONE);
				}
	        	 
			}else{
//				listItemView.tv_kfname.setText("");
				listItemView.point.setVisibility(View.GONE);
			}
	        listItemView.tv_msginfo.setText((String)settinginfo.get("textmsg"));
	        
	        String time = TimeStamp2Date(settinginfo.get("msgtime")+"");
	        listItemView.tv_time.setText(time);
	        
	        convertView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Ntalker.getInstance().startChat(v.getContext(),(String)settinginfo.get("settingid"), "", null, null, null);
					
				}
			});
	        
		return convertView;
	}

	private String TimeStamp2Date(String timestampString) {
		
		Long timestamp = Long.parseLong(timestampString);
		String dateString = new SimpleDateFormat("MM-dd HH:mm").format(new Date(timestamp));
		String data1 = dateString.substring(0, 6);
		try
		{
			String time = null;
			time = getTime();
			long mdates = Long.parseLong(time);
			String dateString2 = new SimpleDateFormat("MM-dd HH:mm").format(new Date(mdates));//yy/mm/dd
			String data2 = dateString2.substring(0, 6);
			if (data1.equals(data2))
			{
				dateString = new SimpleDateFormat("HH:mm").format(new Date(timestamp));
			}else{
				dateString = new SimpleDateFormat("yy/mm/dd").format(new Date(timestamp));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return dateString;
		
	}


	public static String getTime() throws java.text.ParseException
	{
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
		String hehe = dateFormat.format(now);
		Date date = dateFormat.parse(hehe);
		return date.getTime() + "";
	}

}
