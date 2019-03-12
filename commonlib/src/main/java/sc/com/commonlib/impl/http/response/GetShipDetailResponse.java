package sc.com.commonlib.impl.http.response;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import sc.com.commonlib.api.http.HttpResponse;
import sc.com.commonlib.common.constant.ShipConstant;
import sc.com.commonlib.impl.http.entity.ShipDetail;
import sc.com.commonlib.impl.http.entity.ShipEntity;
import sc.com.commonlib.impl.http.entity.ShipEquipment;

/**
 * Created by Eric on 2018/3/2.
 */

public class GetShipDetailResponse extends HttpResponse<ShipDetail> {

    private ShipDetail shipDetail;
    public GetShipDetailResponse(String responseStr) {
        super(responseStr);
    }

    @Override
    protected void parseEntity() {
        shipDetail = new ShipDetail();
        JsonObject data = data_json_obj.get("data").getAsJsonObject();
        shipDetail = gson.fromJson(data,ShipDetail.class);


    }


    public ShipDetail getShipDetail() {
        return shipDetail;
    }
}
