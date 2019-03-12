package sc.com.commonlib.api.http;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import sc.com.commonlib.CommonKit;

/**
 * Created by Eric on 2018/3/1.
 */

public abstract class DownloadRequest {

    protected Request request;

    protected  DownloadCallBack downloadCallBack;

    public DownloadRequest() {

    }

    protected abstract Request initRequest();
    public abstract void onDownloadFailure(Call call, IOException e);
    public abstract void onDownloadResponse(Call call, Response response);

    private Request getRequest() {
        if (request == null) {
            initRequest();
        }
        return request;
    }

    double lastDownload = 0;
    double nowDownload = 0;

    public void execute() {
        CommonKit.getHttpClient().newCall(getRequest()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //全局失败
                onDownloadFailure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //构建response
               onDownloadResponse(call,response);


            }
        });
    }

    public void setDownloadCallBack(DownloadCallBack downloadCallBack) {
        this.downloadCallBack = downloadCallBack;
    }
}
