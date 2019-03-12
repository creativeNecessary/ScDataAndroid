package sc.com.commonlib.impl.http.request;

import okhttp3.Request;
import sc.com.commonlib.api.http.HttpRequest;
import sc.com.commonlib.api.http.HttpRequestTest;
import sc.com.commonlib.impl.http.entity.HttpParameter;
import sc.com.commonlib.impl.http.response.CheckUpdateResponse;
import sc.com.commonlib.impl.http.response.CheckUpdateResponseTest;

/**
 * Created by Eric on 2018/5/21.
 */
public class CheckUpdateRequestTest extends HttpRequestTest<CheckUpdateResponseTest> {


    public CheckUpdateRequestTest(int versionCode) {
        this.parameter = new HttpParameter
                .Builder("checkUpdate", "checkUpdate")
                .append("versionCode", versionCode)
                .build();
    }

    @Override
    public CheckUpdateResponseTest initResponse(String responseStr) {
        response = new CheckUpdateResponseTest(responseStr);

        return response;
    }

    @Override
    public Class getSubClass() {
        return this.getClass();
    }

    @Override
    public String getTag() {
        return parameter.getTag();
    }

    @Override
    public String change2Json() {
        return parameter.change2Json();
    }


}
