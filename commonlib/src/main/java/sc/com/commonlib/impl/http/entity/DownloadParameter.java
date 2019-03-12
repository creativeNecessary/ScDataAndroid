package sc.com.commonlib.impl.http.entity;


import java.io.File;

/**
 * Created by Eric on 2018/5/23.
 */
public class DownloadParameter {

    private HttpParameter httpParameter;
    private File downloadDir;

    public HttpParameter getHttpParameter() {
        return httpParameter;
    }

    public void setHttpParameter(HttpParameter httpParameter) {
        this.httpParameter = httpParameter;
    }


    public DownloadParameter(HttpParameter httpParameter, File downloadDir) {
        this.httpParameter = httpParameter;
        this.downloadDir = downloadDir;
    }

    public File getDownloadDir() {
        return downloadDir;
    }

    public void setDownloadDir(File downloadDir) {
        this.downloadDir = downloadDir;
    }


}
