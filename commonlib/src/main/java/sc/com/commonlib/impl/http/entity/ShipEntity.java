package sc.com.commonlib.impl.http.entity;

/**
 * Created by Eric on 2018/3/7.
 */

public class ShipEntity extends SerializableBaseData {

    private int id;
    private String name;
    private String url;
    private String store_large;
    private String size;
    private String focus;
    private String max_crew;
    private String length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStore_large() {
        return store_large;
    }

    public void setStore_large(String store_large) {
        this.store_large = store_large;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getMax_crew() {
        return max_crew;
    }

    public void setMax_crew(String max_crew) {
        this.max_crew = max_crew;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
