package sc.com.commonlib.api.http;

import sc.com.commonlib.impl.http.entity.DownloadFileEntity;

/**
 * Created by Eric on 2018/3/2.
 */

public interface DownloadCallBack{
    void onDownloadStart(DownloadFileEntity downloadFileEntity);

    void updateProgress(float progress);

    void onDownloadFinish(DownloadFileEntity downloadFileEntity);

    void onFailure(String reason);


}
