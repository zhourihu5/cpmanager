package cn.xiaoneng.h5_sdk;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences 工具类
 */
public class SPHelper {

	SharedPreferences sp;
	SharedPreferences.Editor editor;

	Context mContext;
	public static String spName = "Ntalker";
	
	public SPHelper (Context context){
		this(context, spName);
	}
	public SPHelper(Context context, String pName) {
		this.mContext = context;
		sp = mContext.getSharedPreferences(pName, Context.MODE_PRIVATE);
		editor = sp.edit();
	}

	public void putValue(String key, String value) {
		editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public String getValue(String key) {
		return sp.getString(key, null);
	}
}
