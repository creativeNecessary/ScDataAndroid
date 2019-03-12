package sc.com.commonlib.common.factory;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;

import sc.com.commonlib.R;
import sc.com.commonlib.common.helper.NotificationHelper;

import static sc.com.commonlib.common.util.NotificationUtil.CHANNEL_UPDATE_APK;
import static sc.com.commonlib.common.util.NotificationUtil.NAME_UPDATE_APK;
import static sc.com.commonlib.common.util.NotificationUtil.ID_UPDATE_APK;

/**
 * Created by Eric on 2018/5/22.
 */
public class NotificationFactory {

    @SuppressLint("WrongConstant")
    public static void createUpdateApkNotification(Context context, Class activity_clazz, int progress) {
        boolean can = NotificationManagerCompat.from(context).areNotificationsEnabled();
        NotificationManager mNotificationManager = NotificationHelper.getInstance().getNotificationManager();
        //发送通知，下载
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_UPDATE_APK);
        //设置小图标
        mBuilder.setSmallIcon(R.drawable.start_citizen);
        //设置标题
        mBuilder.setContentTitle("版本升级");
        //设置通知正文
        mBuilder.setSubText("正在更新版本");
        //设置是否点击消息后自动clean

        mBuilder.setProgress(100, progress, false);
        if (progress == 100) {
            mBuilder.setProgress(0, 0, false);
            mBuilder.setContentText(context.getString(R.string.down_apk_success));
        }
        mBuilder.setAutoCancel(true);
        mBuilder.setOnlyAlertOnce(true);

        //设置优先级
        mBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
        //自定义消息时间，以毫秒为单位，当前设置为比系统时间少一小时
        mBuilder.setWhen(System.currentTimeMillis());
        //设置为一个正在进行的通知，此时用户无法清除通知
        mBuilder.setOngoing(true);
        mBuilder.setDefaults(Notification.DEFAULT_LIGHTS);
        mBuilder.setVibrate(new long[]{0L});

        Intent intent = new Intent(context, activity_clazz);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
        mBuilder.setContentIntent(pIntent);
        Notification notification = mBuilder.build();
        if (mNotificationManager != null) {
            mNotificationManager.notify(ID_UPDATE_APK, notification);
        }

    }
}
