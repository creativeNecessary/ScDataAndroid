package sc.com.commonlib.impl.http.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import okhttp3.Request;
import okhttp3.RequestBody;
import sc.com.commonlib.common.constant.Http;
import sc.com.commonlib.common.util.HttpUtil;

/**
 * Created by Eric on 2018/5/23.
 */
public class HttpParameter implements Serializable {
    private String action;
    private String netUrl;
    private String requestParameter;
    private final String tag;

    public HttpParameter(String action) {
        this.action = action;
        tag = action+String.valueOf(System.currentTimeMillis());

    }

    public String change2Json(){
        JSONObject parameter = new JSONObject();
        try {
            parameter.put("action",action);
            parameter.put("netUrl",netUrl);
            parameter.put("data",requestParameter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return parameter.toString();
    }

    public String getTag() {
        return tag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNetUrl() {
        return netUrl;
    }

    public void setNetUrl(String netUrl) {
        this.netUrl = netUrl;
    }

    public String getRequestParameter() {
        return requestParameter;
    }


    public Request getRequest() {
        RequestBody requestBody = RequestBody.create(Http.JSON, requestParameter);
        return new Request.Builder()
                .url(HttpUtil.getInstance().getServiceApiUrl(getNetUrl()))
                .post(requestBody)
                .build();
    }

    public static class Builder {

        private String action;
        private String netUrl;
        private JSONObject requestParameter;

        public Builder(String action, String netUrl) {
            this.action = action;
            this.netUrl = netUrl;
            this.requestParameter = HttpUtil.getInstance().getBaseRequestParam(action);
        }

        public Builder append(String key, Object value) {
            try {
                this.requestParameter.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        public HttpParameter build() {
            HttpParameter parameter = new HttpParameter(action);
            parameter.netUrl = netUrl;
            parameter.requestParameter = requestParameter.toString();

            return parameter;
        }
    }
}
