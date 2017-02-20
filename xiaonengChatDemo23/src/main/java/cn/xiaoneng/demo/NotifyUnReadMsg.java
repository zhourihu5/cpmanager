package cn.xiaoneng.demo;

import com.example.XNChatDemo3.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

public class NotifyUnReadMsg
{
	private static NotifyUnReadMsg mNotifyUnReadMsg = null;

	public static NotifyUnReadMsg getInstance(Context context)
	{
		if (mNotifyUnReadMsg == null)
			mNotifyUnReadMsg = new NotifyUnReadMsg(context);

		return mNotifyUnReadMsg;
	}

	Context context;
	
	NotificationManager notificationManager;
	
	private NotifyUnReadMsg(Context context)
	{
		this.context = context;
	}

	@SuppressWarnings("rawtypes")
	public void setNotiType(int iconId, String contentTitle, String contentText, Class activity)
	{
		notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		Intent notifyIntent = new Intent(context, activity); // 创建新的Intent，作为点击Notification留言条时，会运行的Activity

		PendingIntent appIntent = PendingIntent.getActivity(context, 0, notifyIntent, 0);// 创建PendingIntent作为设置递延运行的Activity

		Notification myNoti = new Notification.Builder(context)
		.setSmallIcon(R.drawable.visitor)
		.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.visitor))
		.setTicker(contentTitle)
		.setAutoCancel(true)
		.setWhen(System.currentTimeMillis())
		.setContentTitle(contentTitle)
		.setContentText(contentText)
		.setContentIntent(appIntent).build();
		myNoti.defaults = Notification.DEFAULT_VIBRATE;

		notificationManager.notify(0, myNoti);// 送出Notification

		// try
		// {
		// notyfication.sound = Uri.parse("android.resource://" +
		// context.getPackageName() + "/" + R.raw.type);
		// }
		// catch (Exception e)
		// {
		// notyfication.defaults = Notification.DEFAULT_SOUND;
		// }
	}
	
	public void dissmissNotifyCation()
	{
		if(notificationManager != null)
			notificationManager.cancel(0);
	}
}
