package sc.com.commonlib.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sc.com.commonlib.R;
import sc.com.commonlib.common.entity.HostItem;

/**
 * Created by Eric on 2018/5/24.
 */
public class CommonUtil {


    public static String getVersionName(Context context){
        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo;
        String versionName="";
        try {
            packageInfo=packageManager.getPackageInfo(context.getPackageName(),0);
            versionName=packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static String getNowDate(){

        return  DateFormat.format("yyyy-MM-dd HH:mm:ss",System.currentTimeMillis()).toString();
    }


}
