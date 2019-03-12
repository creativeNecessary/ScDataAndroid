package sc.com.commonlib.impl.http.request;

import okhttp3.Request;
import sc.com.commonlib.api.http.HttpRequest;
import sc.com.commonlib.impl.http.entity.HttpParameter;
import sc.com.commonlib.impl.http.response.GetCommLinkDetailResponse;
import sc.com.commonlib.impl.http.response.GetCommLinkListResponse;

/**
 * Created by Eric on 2018/5/31.
 */
public class GetCommLinkDetailRequest extends HttpRequest<GetCommLinkDetailResponse> {
    private int comm_link_id ;

    public GetCommLinkDetailRequest(int comm_link_id) {
        this.comm_link_id = comm_link_id;
    }

    @Override
    protected GetCommLinkDetailResponse initResponse(String responseStr) {
        return new GetCommLinkDetailResponse(responseStr);
    }

    @Override
    protected Request initRequest() {
        HttpParameter httpParameter = new HttpParameter
                .Builder("getCommLinkDetail","getCommLinkDetail")
                .append("comm_link_id",comm_link_id)
                .build();

        return httpParameter.getRequest();
    }
}
