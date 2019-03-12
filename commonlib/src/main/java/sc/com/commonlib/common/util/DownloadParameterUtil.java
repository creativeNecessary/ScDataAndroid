package sc.com.commonlib.common.util;

import sc.com.commonlib.impl.http.entity.DownloadParameter;
import sc.com.commonlib.impl.http.entity.HttpParameter;

/**
 * Created by Eric on 2018/5/23.
 */
public class DownloadParameterUtil {

    public static DownloadParameter getUpdateApkParameter() {
        HttpParameter.Builder builder = new HttpParameter.Builder("getUpdateApkFile", "getUpdateApkFile");
        DownloadParameter downloadParameter = new DownloadParameter(builder.build(),FileUtil.getUpdateApkDir());
        return downloadParameter;
    }
}
