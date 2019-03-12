package sc.com.commonlib.impl.http.request;

import android.text.TextUtils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import sc.com.commonlib.api.http.DownloadRequest;
import sc.com.commonlib.common.constant.Http;
import sc.com.commonlib.common.util.HttpUtil;
import sc.com.commonlib.impl.http.entity.DownloadFileEntity;
import sc.com.commonlib.impl.http.entity.DownloadParameter;

/**
 * Created by Eric on 2018/4/23.
 */
public class DownloadFileRequest extends DownloadRequest {

    private DownloadParameter downloadParameter;

    public DownloadFileRequest(DownloadParameter downloadParameter) {
        this.downloadParameter = downloadParameter;
    }

    @Override
    protected Request initRequest() {


        RequestBody requestBody = RequestBody.create(Http.JSON, downloadParameter.getHttpParameter().getRequestParameter());
        request = new Request.Builder()
//                    .addHeader("Range","bytes=10485760-") //断点续传
                .url(HttpUtil.getInstance().getServiceApiUrl(downloadParameter.getHttpParameter().getNetUrl()))
                .post(requestBody)
                .build();

        return request;
    }

    @Override
    public void onDownloadFailure(Call call, IOException e) {

    }

    @Override
    public void onDownloadResponse(Call call, Response response) {
        ResponseBody responseBody = response.body();
        String fileName = response.header(Http.HEADER_FILENAME);

        if (TextUtils.isEmpty(fileName)) {
            downloadCallBack.onFailure("File Name is Null");
            return;
        }

        if (downloadParameter.getDownloadDir() == null) {
            downloadCallBack.onFailure(" Dir is Null do you create Dir?");
            return;
        }
        File dir = downloadParameter.getDownloadDir();

        File downloadFile = new File(dir.getAbsolutePath() + "/" + fileName);
        String filePath = downloadFile.getAbsolutePath();

        DownloadFileEntity downloadFileEntity = new DownloadFileEntity(fileName, filePath, responseBody.contentLength());

        if (downloadFile.length() == responseBody.contentLength()) {

            downloadCallBack.onDownloadFinish(downloadFileEntity);

            return;
        }
        InputStream inputStream = null;
        byte[] buffer = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            double current_download = 0;

            double total = responseBody.contentLength();

            inputStream = responseBody.byteStream();

            fileOutputStream = new FileOutputStream(downloadFile);
            downloadCallBack.onDownloadStart(downloadFileEntity);

            while ((len = inputStream.read(buffer)) != -1) {
                current_download += len;
                fileOutputStream.write(buffer, 0, len);

                downloadCallBack.updateProgress((float) ((current_download / total) * 100));
            }
            fileOutputStream.flush();
            //下载完成
            downloadCallBack.onDownloadFinish(downloadFileEntity);
        } catch (IOException | NullPointerException e) {
            downloadCallBack.onFailure("have an Exception ");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
