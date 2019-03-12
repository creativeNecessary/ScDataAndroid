package sc.com.commonlib.impl.http.request;

import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Request;
import okhttp3.RequestBody;
import sc.com.commonlib.api.http.HttpRequest;
import sc.com.commonlib.api.http.HttpResponse;
import sc.com.commonlib.common.constant.Http;
import sc.com.commonlib.common.util.HttpUtil;
import sc.com.commonlib.impl.http.entity.FilterType;
import sc.com.commonlib.impl.http.entity.HttpParameter;
import sc.com.commonlib.impl.http.response.GetShipListResponse;

/**
 * Created by Eric on 2018/3/2.
 */

public class GetShipListRequest extends HttpRequest<GetShipListResponse> {
    private int pageNum = -1;
    private FilterType filterType = null;

    public GetShipListRequest(int pageNum, FilterType filterType) {
        this.pageNum = pageNum;
        this.filterType = filterType;
    }

    @Override
    protected GetShipListResponse initResponse(String responseStr) {

        return new GetShipListResponse(responseStr);
    }

    @Override
    protected Request initRequest() {
        String type_content = "";
        if (filterType != null) {
            type_content = filterType.getType_en();
        }
        HttpParameter parameter = new HttpParameter.Builder("getShipList", "getShipList")
                .append("page_num", pageNum)
                .append("per_page", "10")
                .append("type_content", type_content)
                .build();

        return parameter.getRequest();
    }
}
