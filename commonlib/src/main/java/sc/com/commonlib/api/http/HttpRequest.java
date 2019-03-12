package sc.com.commonlib.api.http;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import sc.com.commonlib.CommonKit;

/**
 * Created by Eric on 2018/3/1.
 */

public abstract class HttpRequest<T extends HttpResponse>{
    protected Request request;
    protected abstract T initResponse(String responseStr);

    public HttpRequest() {

    }

    protected   abstract Request initRequest();

    public  Request getRequest(){
        if(request == null){
            request = initRequest();
        }
        return request;
    }


    public void execute(final HttpCallBack<T> httpCallBack){
        CommonKit.getHttpClient().newCall(getRequest()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //全局失败
                httpCallBack.onFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //构建response
                try {
                    ResponseBody responseBody = response.body();
                    if(responseBody == null){
                        throw new NullPointerException("网络请求错误");
                    }
                    String responseStr = responseBody.string();
                    T netResponse = initResponse(responseStr);

                    httpCallBack.onResponse(netResponse);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}
