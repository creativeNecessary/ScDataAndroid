package sc.com.commonlib.impl.http.entity;

/**
 * Created by zhanglimin on 2018/3/26.
 */

public class FilterType extends SerializableBaseData {
    private String type_en;
    private String type_ch;

    public String getType_en() {
        return type_en;
    }

    public void setType_en(String type_en) {
        this.type_en = type_en;
    }

    public String getType_ch() {
        return type_ch;
    }

    public void setType_ch(String type_ch) {
        this.type_ch = type_ch;
    }
}
