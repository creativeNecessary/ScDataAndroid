package sc.com.commonlib.common.helper;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import sc.com.commonlib.CommonKit;

import static sc.com.commonlib.common.util.NotificationUtil.CHANNEL_UPDATE_APK;
import static sc.com.commonlib.common.util.NotificationUtil.NAME_UPDATE_APK;

/**
 * Created by Eric on 2018/5/23.
 */
public class NotificationHelper {

    private NotificationHelper() {
    }


    public static NotificationHelper getInstance() {
        return NotificationHelper.SingletonHolder.INSTANCE;
    }

    public void init(){
        NotificationManager mNotificationManager = (NotificationManager) CommonKit.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_UPDATE_APK, NAME_UPDATE_APK, NotificationManager.IMPORTANCE_LOW);
            if (mNotificationManager != null) {
                notificationChannel.enableVibration(false);
                mNotificationManager.createNotificationChannel(notificationChannel);
            }
        }

    }
    public NotificationManager getNotificationManager(){
        return (NotificationManager) CommonKit.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
    }


    private static class SingletonHolder {
        private static final NotificationHelper INSTANCE = new NotificationHelper();
    }

}
