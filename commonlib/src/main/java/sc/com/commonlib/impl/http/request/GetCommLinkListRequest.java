package sc.com.commonlib.impl.http.request;

import okhttp3.Request;
import okhttp3.RequestBody;
import sc.com.commonlib.api.http.HttpRequest;
import sc.com.commonlib.common.constant.Http;
import sc.com.commonlib.common.util.HttpUtil;
import sc.com.commonlib.impl.http.entity.HttpParameter;
import sc.com.commonlib.impl.http.response.GetCommLinkListResponse;

/**
 * Created by Eric on 2018/5/31.
 */
public class GetCommLinkListRequest extends HttpRequest<GetCommLinkListResponse> {
    private String type ;
    private int page_num ;

    public GetCommLinkListRequest(String type, int page_num) {
        this.type = type;
        this.page_num = page_num;
    }

    @Override
    protected GetCommLinkListResponse initResponse(String responseStr) {
        return new GetCommLinkListResponse(responseStr);
    }

    @Override
    protected Request initRequest() {
        HttpParameter httpParameter = new HttpParameter
                .Builder("getCommLinkList","getCommLinkList")
                .append("type",type)
                .append("page_num",page_num)
                .append("per_page",10)
                .build();

        return httpParameter.getRequest();
    }
}
