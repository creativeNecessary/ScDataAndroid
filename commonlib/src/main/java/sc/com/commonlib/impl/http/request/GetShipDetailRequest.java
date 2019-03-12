package sc.com.commonlib.impl.http.request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Request;
import okhttp3.RequestBody;
import sc.com.commonlib.api.http.HttpRequest;
import sc.com.commonlib.common.constant.Http;
import sc.com.commonlib.common.util.HttpUtil;
import sc.com.commonlib.impl.http.entity.HttpParameter;
import sc.com.commonlib.impl.http.response.GetShipDetailResponse;
import sc.com.commonlib.impl.http.response.GetShipListResponse;

/**
 * Created by Eric on 2018/3/2.
 */

public class GetShipDetailRequest extends HttpRequest<GetShipDetailResponse> {
    private int ship_id = -1;

    public GetShipDetailRequest(int ship_id) {
        this.ship_id = ship_id;
    }

    @Override
    protected GetShipDetailResponse initResponse(String responseStr) {
        return new GetShipDetailResponse(responseStr);
    }

    @Override
    protected Request initRequest() {
        HttpParameter httpParameter = new HttpParameter.Builder("getShipDetail", "getShipDetail")
                .append("ship_id", ship_id)
                .build();


        return httpParameter.getRequest();
    }
}
