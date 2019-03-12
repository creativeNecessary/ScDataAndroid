package sc.com.commonlib.common.util;

import java.io.File;

import sc.com.commonlib.api.http.DownloadCallBack;
import sc.com.commonlib.impl.http.entity.DownloadFileEntity;
import sc.com.commonlib.impl.http.request.DownloadFileRequest;

/**
 * Created by Eric on 2018/5/23.
 */
public class DownloadUtil {

    public static DownloadFileRequest downloadUpdateApk(DownloadCallBack downloadCallBack){
        FileUtil.cleanApkDir();
        DownloadFileRequest downloadFileRequest = new DownloadFileRequest(DownloadParameterUtil.getUpdateApkParameter());
        downloadFileRequest.setDownloadCallBack(downloadCallBack);
        downloadFileRequest.execute();

        return downloadFileRequest;
    }

}
