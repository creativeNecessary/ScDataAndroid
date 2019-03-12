package sc.com.commonlib.impl.http.entity;

import sc.com.data_provider.entity.ShipNameEntity;

/**
 * Created by Eric on 2018/8/1.
 */
public class ShipName extends SerializableBaseData {
    private String id;
    private String name;
    private String name_ch;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_ch() {
        return name_ch;
    }

    public void setName_ch(String name_ch) {
        this.name_ch = name_ch;
    }

    public ShipNameEntity getDataBaseEntity(){
        return new ShipNameEntity(name,name_ch,id);
    }
}
