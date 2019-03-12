package sc.com.commonlib.impl.http.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglimin on 2018/3/26.
 */

public class ShipDetail extends SerializableBaseData {
    private int id;
    private String name;
    private String name_ch;
    private String url;
    private String description;
    private String store_large;
    private List<ShipImage> pic_url;
    private Manufacturer manufacturer;
    private String size;
    private String focus;
    private String production_state;
    private String max_crew;
    private String min_crew;
    private String pitch_max;
    private String yaw_max;
    private String roll_max;
    private String x_axis_acceleration;
    private String y_axis_acceleration;
    private String z_axis_acceleration;
    private String cargocapacity;
    private String mass;
    private String scm_speed;
    private String afterburner_speed;
    private String length;
    private String beam;
    private String height;
    private String ship_price;
    private List<ShipEquipment> ship_equipment;
    private String model3d_url;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ShipImage> getPic_url() {
        return pic_url;
    }

    public void setPic_url(List<ShipImage> pic_url) {
        this.pic_url = pic_url;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
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

    public String getProduction_state() {
        return production_state;
    }

    public void setProduction_state(String production_state) {
        this.production_state = production_state;
    }

    public String getMax_crew() {
        return max_crew;
    }

    public void setMax_crew(String max_crew) {
        this.max_crew = max_crew;
    }

    public String getMin_crew() {
        return min_crew;
    }

    public void setMin_crew(String min_crew) {
        this.min_crew = min_crew;
    }

    public String getPitch_max() {
        return pitch_max;
    }

    public void setPitch_max(String pitch_max) {
        this.pitch_max = pitch_max;
    }

    public String getYaw_max() {
        return yaw_max;
    }

    public void setYaw_max(String yaw_max) {
        this.yaw_max = yaw_max;
    }

    public String getRoll_max() {
        return roll_max;
    }

    public void setRoll_max(String roll_max) {
        this.roll_max = roll_max;
    }

    public String getX_axis_acceleration() {
        return x_axis_acceleration;
    }

    public void setX_axis_acceleration(String x_axis_acceleration) {
        this.x_axis_acceleration = x_axis_acceleration;
    }

    public String getY_axis_acceleration() {
        return y_axis_acceleration;
    }

    public void setY_axis_acceleration(String y_axis_acceleration) {
        this.y_axis_acceleration = y_axis_acceleration;
    }

    public String getZ_axis_acceleration() {
        return z_axis_acceleration;
    }

    public void setZ_axis_acceleration(String z_axis_acceleration) {
        this.z_axis_acceleration = z_axis_acceleration;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getScm_speed() {
        return scm_speed;
    }

    public void setScm_speed(String scm_speed) {
        this.scm_speed = scm_speed;
    }

    public String getAfterburner_speed() {
        return afterburner_speed;
    }

    public void setAfterburner_speed(String afterburner_speed) {
        this.afterburner_speed = afterburner_speed;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getBeam() {
        return beam;
    }

    public void setBeam(String beam) {
        this.beam = beam;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getModel3d_url() {
        return model3d_url;
    }

    public void setModel3d_url(String model3d_url) {
        this.model3d_url = model3d_url;
    }

    public String getName_ch() {
        return name_ch;
    }

    public void setName_ch(String name_ch) {
        this.name_ch = name_ch;
    }

    public String getStore_large() {
        return store_large;
    }

    public void setStore_large(String store_large) {
        this.store_large = store_large;
    }

    public String getCargocapacity() {
        return cargocapacity;
    }

    public void setCargocapacity(String cargocapacity) {
        this.cargocapacity = cargocapacity;
    }

    public List<ShipEquipment> getShip_equipment() {
        return ship_equipment;
    }

    public void setShip_equipment(List<ShipEquipment> ship_equipment) {
        this.ship_equipment = ship_equipment;
    }

    public String getShip_price() {
        return ship_price;
    }

    public void setShip_price(String ship_price) {
        this.ship_price = ship_price;
    }

    public List<String> operateImageUrlList() {
        List<String> urlList = new ArrayList<>();
        if (pic_url != null && pic_url.size() > 0) {
            for (ShipImage image : pic_url) {
                urlList.add(image.getUrl());
            }
        }
        return urlList;
    }



}
