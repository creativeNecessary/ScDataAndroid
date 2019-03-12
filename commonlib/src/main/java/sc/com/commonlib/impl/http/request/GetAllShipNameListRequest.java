package sc.com.commonlib.impl.http.request;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Request;
import okhttp3.RequestBody;
import sc.com.commonlib.api.http.HttpRequest;
import sc.com.commonlib.common.constant.Http;
import sc.com.commonlib.common.util.HttpUtil;
import sc.com.commonlib.impl.http.entity.FilterType;
import sc.com.commonlib.impl.http.entity.HttpParameter;
import sc.com.commonlib.impl.http.response.GetAllShipNameListResponse;
import sc.com.commonlib.impl.http.response.GetShipListResponse;

/**
 * Created by Eric on 2018/3/2.
 */

public class GetAllShipNameListRequest extends HttpRequest<GetAllShipNameListResponse> {

    public GetAllShipNameListRequest() {
    }

    @Override
    protected GetAllShipNameListResponse initResponse(String responseStr) {

        return new GetAllShipNameListResponse(responseStr);
    }

    @Override
    protected Request initRequest() {
        HttpParameter parameter = new HttpParameter.Builder("getAllShipName", "getAllShipName")
                .build();

        return parameter.getRequest();
    }
}
