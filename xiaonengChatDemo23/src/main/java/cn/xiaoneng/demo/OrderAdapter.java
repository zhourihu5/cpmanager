package cn.xiaoneng.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.XNChatDemo3.R;

import cn.xiaoneng.image.ImageShow;
import cn.xiaoneng.utils.XNLOG;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class OrderAdapter extends BaseAdapter {

	private Context context; // 运行上下文
	private List<Map<String,Object>> orderlist;
	private LayoutInflater listcontainer;
	private HashMap<String, Boolean> isSelected;  
	boolean res = false;
	public OrderAdapter(Context context , List<Map<String,Object>> orderlistdata) {
		this.context = context;
		orderlist = orderlistdata;
		listcontainer = LayoutInflater.from(context);
		isSelected = new HashMap<String, Boolean>();
	}
	@Override
	public int getCount() {
		return orderlist.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) { 
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = listcontainer.inflate(R.layout.list_item, null);
			viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
			viewHolder.desc = (TextView) convertView.findViewById(R.id.tv_desc);
			viewHolder.pic = (ImageView) convertView.findViewById(R.id.ib_icon);
			viewHolder.radio_btn = (RadioButton) convertView.findViewById(R.id.radio_btn);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
//		convertView.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(v.getContext(), "发送位置"+position, 1).show();
//				res = true;
//			}
//		});

		Map<String, Object> ordermap = orderlist.get(position);
		viewHolder.title.setText((String)ordermap.get("title"));
		viewHolder.desc.setText((String)ordermap.get("desc"));
		XNLOG.i("mapmap2=="+orderlist.get(2).get("desc").toString()+","+orderlist.get(2).toString());
		viewHolder.radio_btn.setOnClickListener(new View.OnClickListener() {

		      public void onClick(View v) {

		    	  clearStates(position);
		        // 重置，确保最多只有一项被选中
//		        for (String key : isSelected.keySet()) {
//		        	isSelected.put(key, false);
//
//		        }
//		        isSelected.put(String.valueOf(position), ((RadioButton) v).isChecked());
		        OrderAdapter.this.notifyDataSetChanged();
		        String title = (String) orderlist.get(position).get("title");
				Toast.makeText(v.getContext(), "RadioButton点击位置"+position+",title="+title, 1).show();
		      }
		    });
//
//		    
//		    if (isSelected.get(String.valueOf(position)) == null || isSelected.get(String.valueOf(position)) == false) {
//		      res = false;
//		      isSelected.put(String.valueOf(position), false);
//		    } else
//		      res = true;
//
//		    viewHolder.radio_btn.setChecked(res);
		 boolean res=false;
	        if(getStates(position)==null|| getStates(position)==false)//判断当前位置的radiobutton点击状态
	        {
	            res=false;
	            setStates(position, false);

	        }else{
	            res=true;
	        }
	        viewHolder.radio_btn.setChecked(res);   
		ImageShow.getInstance(context).DisplayImage(ImageShow.IMAGE_NORMAL, null, (String)ordermap.get("image"), viewHolder.pic, null, R.drawable.pic_icon, R.drawable.pic_icon, null);
		
		return convertView;
	}

	class ViewHolder
	{
		TextView title;
		TextView desc;
		ImageView pic;
		RadioButton radio_btn;
	}
	public void clearStates(int position){
        // 重置，确保最多只有一项被选中
        for(String key:isSelected.keySet()){
        	isSelected.put(key,false);
        }
        isSelected.put(String.valueOf(position), true);
    }
	 public Boolean getStates(int position){
	        return isSelected.get(String.valueOf(position));
	    }
	    //设置状态值
	    public void setStates(int position,boolean isChecked){
	    	isSelected.put(String.valueOf(position),false);
	    }
}
