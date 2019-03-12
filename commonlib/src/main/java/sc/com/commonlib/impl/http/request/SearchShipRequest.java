package sc.com.commonlib.impl.http.request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Request;
import okhttp3.RequestBody;
import sc.com.commonlib.api.http.HttpRequest;
import sc.com.commonlib.common.constant.Http;
import sc.com.commonlib.common.util.HttpUtil;
import sc.com.commonlib.impl.http.response.GetShipDetailResponse;
import sc.com.commonlib.impl.http.response.SearchShipResponse;

/**
 * Created by Eric on 2018/3/2.
 */

public class SearchShipRequest extends HttpRequest <SearchShipResponse>{
    private String ship_id = "";

    public SearchShipRequest(String ship_id) {
        this.ship_id = ship_id;
    }

    @Override
    protected SearchShipResponse initResponse(String responseStr) {
        return new SearchShipResponse(responseStr);
    }

    @Override
    protected Request initRequest() {
        JSONObject jsonObject = HttpUtil.getInstance().getBaseRequestParam("searchShip");
        try {
            jsonObject.put("ship_id", ship_id);
            RequestBody requestBody = RequestBody.create(Http.JSON, jsonObject.toString());
            request = new Request.Builder()
                    .url(HttpUtil.getInstance().getServiceApiUrl("searchShip"))
                    .post(requestBody)
                    .cacheControl(HttpUtil.getOneDayCacheControl())
                    .build();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return request;
    }
}
