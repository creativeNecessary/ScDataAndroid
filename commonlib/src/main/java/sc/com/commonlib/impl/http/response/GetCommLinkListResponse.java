package sc.com.commonlib.impl.http.response;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import sc.com.commonlib.api.http.HttpResponse;
import sc.com.commonlib.impl.http.entity.CommLinkItem;

/**
 * Created by Eric on 2018/5/31.
 */
public class GetCommLinkListResponse extends HttpResponse<CommLinkItem> {

    private List<CommLinkItem> commLinkItems;

    public GetCommLinkListResponse(String responseStr) {
        super(responseStr);
    }

    @Override
    protected void parseEntity() {
        commLinkItems = new ArrayList<>();
        JsonArray data = data_json_obj.get("data").getAsJsonArray();
        for (JsonElement comm_link : data){
            CommLinkItem shipEntity = gson.fromJson(comm_link,CommLinkItem.class);
            commLinkItems.add(shipEntity);
        }
    }

    public List<CommLinkItem> getCommLinkItems() {
        return commLinkItems;
    }
}
