package sc.com.commonlib.impl.http.entity;

/**
 * Created by Eric on 2018/3/26.
 */

public class Manufacturer extends SerializableBaseData {

    private String name;
    private String code;
    private String known_for;
    private String description;
    private String source_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKnown_for() {
        return known_for;
    }

    public void setKnown_for(String known_for) {
        this.known_for = known_for;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }
}
