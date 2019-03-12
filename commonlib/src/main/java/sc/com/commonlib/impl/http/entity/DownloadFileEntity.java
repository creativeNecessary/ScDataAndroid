package sc.com.commonlib.impl.http.entity;

import sc.com.commonlib.common.util.FileUtil;

/**
 * Created by Eric on 2018/4/24.
 */
public class DownloadFileEntity extends SerializableBaseData {
    private String name;
    private String path;
    private long length;
    private String extension;

    public DownloadFileEntity() {

    }

    public DownloadFileEntity(String name, String path) {
        this.name = name;
        this.path = path;
        this.extension = FileUtil.getExtension(name);
    }

    public DownloadFileEntity(String name, String path, long length) {
        this.name = name;
        this.path = path;
        this.length = length;
        this.extension = FileUtil.getExtension(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.extension = FileUtil.getExtension(name);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
