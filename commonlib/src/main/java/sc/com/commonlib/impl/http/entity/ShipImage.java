package sc.com.commonlib.impl.http.entity;

/**
 * Created by Eric on 2018/3/26.
 */

public class ShipImage extends SerializableBaseData {
   private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
