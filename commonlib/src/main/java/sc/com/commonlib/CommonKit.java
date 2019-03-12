package sc.com.commonlib;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import sc.com.commonlib.common.crash.CrashHandler;
import sc.com.commonlib.common.helper.NotificationHelper;
import sc.com.commonlib.common.network.service.NetWorkService;
import sc.com.data_provider.ScDataProvider;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import sc.com.commonlib.common.util.FileUtil;

import static sc.com.commonlib.common.constant.Common.cacheSize10M;


/**
 * Created by Eric on 2018/3/1.
 */

public class CommonKit {

    private static Context mApplicationContext;
    private static OkHttpClient okHttpClient;
    private static Application hostApplication;
    private static List<Activity> activityList = new ArrayList<>();
    private static String PROGRESS_MAIN = "sc.com.scdataview";
    private static String PROGRESS_NETWORK = "star_citizen.network";
    public static float USD2CNY = 1;//默认汇率为1


    public static void init(Application application) {
        mApplicationContext = application.getApplicationContext();
        hostApplication = application;
        if (getCurrentProcessName(mApplicationContext).equals(PROGRESS_MAIN)) {
//            startNetWorkService();
            CrashHandler.getInstance().init(mApplicationContext);
        }
        initHttpClient();
        Logger.addLogAdapter(new AndroidLogAdapter());
        ScDataProvider.init(mApplicationContext);
        NotificationHelper.getInstance().init();


    }

    public static Context getApplicationContext() {
        return mApplicationContext;
    }


    private static void initHttpClient() {
        okHttpClient = new OkHttpClient().newBuilder()
                .cache(new Cache(FileUtil.getNetCacheDir(), cacheSize10M))
                .build();
    }

    public static void startNetWorkService() {
        Intent intent = new Intent(getApplicationContext(), NetWorkService.class);
        getApplicationContext().startService(intent);
    }

    public static OkHttpClient getHttpClient() {

        if (CommonKit.okHttpClient != null) {
            return okHttpClient;
        }
        return null;
    }


    public static Application getHostApplication() {
        return hostApplication;
    }

    public static <T extends Activity> void addToApplication(T activity) {
        activityList.add(activity);
    }

    public static <T extends Activity> void removeFromApplication(T activity) {
        activityList.remove(activity);
    }

    public static void clearActivity() {
        for (Activity activity : activityList) {
            if (activity != null) {
                activity.finish();
            }
        }
        activityList.clear();
        activityList = null;
    }

    public static String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        if (mActivityManager != null) {
            for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                    .getRunningAppProcesses()) {
                if (appProcess.pid == pid) {
                    return appProcess.processName;
                }
            }
        }
        return "";
    }
}
