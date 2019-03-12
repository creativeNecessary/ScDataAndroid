package sc.com.commonlib.impl.http.response;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import sc.com.commonlib.api.http.HttpResponse;
import sc.com.commonlib.impl.http.entity.CommLinkDetailItem;

/**
 * Created by Eric on 2018/5/31.
 */
public class GetCommLinkDetailResponse extends HttpResponse<CommLinkDetailItem> {

    private List<CommLinkDetailItem> commLinkDetailItems;

    public GetCommLinkDetailResponse(String responseStr) {
        super(responseStr);
    }

    @Override
    protected void parseEntity() {
        commLinkDetailItems = new ArrayList<>();
        JsonArray data = data_json_obj.get("data").getAsJsonArray();
        for (JsonElement item : data){
            CommLinkDetailItem shipEntity = gson.fromJson(item,CommLinkDetailItem.class);
            commLinkDetailItems.add(shipEntity);
        }

    }

    public List<CommLinkDetailItem> getCommLinkDetailItems() {
        return commLinkDetailItems;
    }


}
