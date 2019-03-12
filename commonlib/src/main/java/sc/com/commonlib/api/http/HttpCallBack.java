package sc.com.commonlib.api.http;

/**
 * Created by Eric on 2018/3/2.
 */

public interface HttpCallBack <T extends HttpResponse>{

    void onFailure();

    void onResponse(T netResponse);

}
