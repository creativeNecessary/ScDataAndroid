package sc.com.commonlib.impl.http.request;

import okhttp3.Request;
import sc.com.commonlib.api.http.HttpRequest;
import sc.com.commonlib.common.constant.Http;
import sc.com.commonlib.impl.http.entity.HttpParameter;
import sc.com.commonlib.impl.http.response.GetFloatingRateResponse;

/**
 * Created by Eric on 2019/3/11.
 */
public class GetFloatingRateRequest extends HttpRequest<GetFloatingRateResponse> {
    @Override
    protected GetFloatingRateResponse initResponse(String responseStr) {
        return new GetFloatingRateResponse(responseStr);
    }

    @Override
    protected Request initRequest() {
        //获取汇率
        return new Request.Builder().url("https://forex.1forge.com/1.0.3/quotes?pairs=USDCNH&api_key="+Http.ForexKey)
                .header("Cache-Control","public, max-age=86400")
                .get()
                .build();
    }
}
