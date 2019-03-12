package sc.com.data_provider.entity;

import sc.com.data_provider.database.ScDatabaseConstant;

/**
 * Created by Eric on 2018/8/1.
 */
public class ShipNameEntity extends DatabaseEntity {

    private String ship_name;
    private String ship_name_ch;

    private String ship_id;


    public ShipNameEntity(String ship_name, String ship_name_ch, String ship_id) {
        this.ship_name = ship_name;
        this.ship_name_ch = ship_name_ch;
        this.ship_id = ship_id;
    }

    public String getShip_name() {
        return ship_name;
    }

    public String getShip_name_ch() {
        return ship_name_ch;
    }

    public String getShip_id() {
        return ship_id;
    }

    @Override
    public String[] getSelectionArg() {
        return new String[0];
    }

    @Override
    public String[] getProjection() {
        return new String[0];
    }


}
