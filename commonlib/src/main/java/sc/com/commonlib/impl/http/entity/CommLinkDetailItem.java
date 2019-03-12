package sc.com.commonlib.impl.http.entity;

/**
 * Created by Eric on 2018/5/31.
 */
public class CommLinkDetailItem extends SerializableBaseData {
    private String content_type;
    private String content_data;
    private String machine_translate_data;
    private String human_translate_data;

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getContent_data() {
        return content_data;
    }

    public void setContent_data(String content_data) {
        this.content_data = content_data;
    }

    public String getMachine_translate_data() {
        return machine_translate_data;
    }

    public void setMachine_translate_data(String machine_translate_data) {
        this.machine_translate_data = machine_translate_data;
    }

    public String getHuman_translate_data() {
        return human_translate_data;
    }

    public void setHuman_translate_data(String human_translate_data) {
        this.human_translate_data = human_translate_data;
    }
}
