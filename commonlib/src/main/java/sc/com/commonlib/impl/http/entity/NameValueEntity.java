package sc.com.commonlib.impl.http.entity;

/**
 * Created by Eric on 2018/3/28.
 */

public class NameValueEntity extends SerializableBaseData {

    public NameValueEntity(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
