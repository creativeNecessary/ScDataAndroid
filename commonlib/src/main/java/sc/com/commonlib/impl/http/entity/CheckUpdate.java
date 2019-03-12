package sc.com.commonlib.impl.http.entity;

/**
 * Created by Eric on 2018/5/21.
 */
public class CheckUpdate extends SerializableBaseData {

    private int code = -1;
    private String version_focus = "";
    private String version_name = "";
    private String apk_download_path = "";

    private final int  NEED_UPDATE = 100;
    private final int  NEED_FORCE_UPDATE = 101;
    private final int  NOW_LAST_VERSION = 102;

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public int getCode() {
        return code;
    }

    public String getApk_download_path() {
        return apk_download_path;
    }

    public void setApk_download_path(String apk_download_path) {
        this.apk_download_path = apk_download_path;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getVersion_focus() {
        return version_focus;
    }

    public void setVersion_focus(String version_focus) {
        this.version_focus = version_focus;
    }

    public boolean needUpdate(){

        return code == NEED_UPDATE;
    }

    public boolean needForceUpdate(){
        return code == NEED_FORCE_UPDATE;
    }

}
