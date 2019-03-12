package sc.com.commonlib.impl.http.response;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import sc.com.commonlib.api.http.HttpResponse;
import sc.com.commonlib.impl.http.entity.ShipName;

/**
 * Created by Eric on 2018/8/1.
 */
public class GetAllShipNameListResponse extends HttpResponse<ShipName> {
    private  List<ShipName> data;

    public GetAllShipNameListResponse(String responseStr) {
        super(responseStr);
    }

    @Override
    protected void parseEntity() {
        data = new ArrayList<>();
        JsonArray jsonArray = data_json_obj.get("data").getAsJsonArray();

        for (JsonElement jsonElement:jsonArray) {
            ShipName shipName = gson.fromJson(jsonElement,ShipName.class);
            data.add(shipName);
        }
    }

    public List<ShipName> getData() {
        return data;
    }
}
