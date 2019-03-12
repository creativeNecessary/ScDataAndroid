package sc.com.commonlib.impl.http.entity;

/**
 * Created by zhanglimin on 2018/3/26.
 */

public class ShipEquipment extends SerializableBaseData {
    static final long serialVersionUID = 1L;
    private int id;
    private String type;
    private String name;
    private String mounts;
    private String component_size;
    private String size;
    private String details;
    private String quantity;
    private String manufacturer;
    private String component_class;
    private String type_ch;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType_ch() {
        return type_ch;
    }

    public void setType_ch(String type_ch) {
        this.type_ch = type_ch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMounts() {
        return mounts;
    }

    public void setMounts(String mounts) {
        this.mounts = mounts;
    }

    public String getComponent_size() {
        return component_size;
    }

    public void setComponent_size(String component_size) {
        this.component_size = component_size;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getComponent_class() {
        return component_class;
    }

    public void setComponent_class(String component_class) {
        this.component_class = component_class;
    }
}
