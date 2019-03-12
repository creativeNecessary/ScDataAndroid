package sc.com.commonlib.impl.http.response;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import sc.com.commonlib.api.http.HttpResponse;
import sc.com.commonlib.impl.http.entity.ShipEntity;

/**
 * Created by Eric on 2018/3/2.
 */

public class SearchShipResponse extends HttpResponse<ShipEntity> {

    private List<ShipEntity> shipEntityList;

    public SearchShipResponse(String responseStr) {
        super(responseStr);
    }

    @Override
    protected void parseEntity() {
        shipEntityList = new ArrayList<>();
        JsonArray data = data_json_obj.get("data").getAsJsonArray();
        for (JsonElement ship : data){
            ShipEntity shipEntity = gson.fromJson(ship,ShipEntity.class);
            shipEntityList.add(shipEntity);
        }

    }

    public List<ShipEntity> getShipEntityList() {
        return shipEntityList;
    }
}
