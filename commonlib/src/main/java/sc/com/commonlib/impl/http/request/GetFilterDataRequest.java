package sc.com.commonlib.impl.http.request;

import okhttp3.Request;
import sc.com.commonlib.api.http.HttpRequest;
import sc.com.commonlib.impl.http.entity.HttpParameter;
import sc.com.commonlib.impl.http.response.GetCommLinkListResponse;
import sc.com.commonlib.impl.http.response.GetFilterDataResponse;

/**
 * Created by Eric on 2018/5/31.
 */
public class GetFilterDataRequest extends HttpRequest<GetFilterDataResponse> {

    @Override
    protected GetFilterDataResponse initResponse(String responseStr) {
        return new GetFilterDataResponse(responseStr);
    }

    @Override
    protected Request initRequest() {
        HttpParameter httpParameter = new HttpParameter
                .Builder("getFilterData","getFilterData")
                .build();

        return httpParameter.getRequest();
    }
}
