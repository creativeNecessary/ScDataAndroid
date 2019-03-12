package sc.com.commonlib.api.http;

import java.io.Serializable;
import sc.com.commonlib.impl.http.entity.HttpParameter;

/**
 * Created by Eric on 2018/3/1.
 */

public abstract class HttpRequestTest<T extends HttpResponseTest> implements Serializable {
    protected HttpParameter parameter;

    protected T response;

    protected HttpRequestStatus status = new HttpRequestStatus();

    public abstract T initResponse(String responseStr) throws Exception;

    public abstract Class getSubClass();

    public abstract String getTag();



    public HttpRequestTest(HttpParameter parameter) {
        this.parameter = parameter;
    }

    public void initErrorResponse(String reason){
        status.setStatus(HttpRequestStatus.Status.FAILED);
        status.setReason(reason);
    }


    public T getResponse() {
        return response;
    }

    public HttpRequestTest() {

    }

    public HttpParameter getParameter() {
        return parameter;
    }

    public HttpRequestStatus getStatus() {
        return status;
    }

    public abstract String change2Json();

//
}
