package sc.com.commonlib.common.util;


import android.Manifest;
import android.os.Build;

import java.util.ArrayList;

/**
 * Created by Eric on 2018/5/23.
 */
public class PermissionUtil {
    public static final int ALL_PERMISSION_GRANTED = 233;
    public static final int HAVE_PERMISSION_DENIED = 234;
    public static final int REQUEST_CHECK_DOWNLOAD_PERMISSION = 235;
    public static final int REQUEST_CHECK_INSTALL_APK_PERMISSION = 236;

    public static ArrayList<String> getDownloadPermission(){
        ArrayList<String> list = new ArrayList<>();
        list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        list.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        return list;
    }

    public static ArrayList<String> getInstallPermission(){
        ArrayList<String> list = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            list.add(Manifest.permission.REQUEST_INSTALL_PACKAGES);
        }
        return list;
    }

}
