package sc.com.commonlib.impl.http.response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import sc.com.commonlib.api.http.HttpResponse;
import sc.com.commonlib.api.http.HttpResponseTest;
import sc.com.commonlib.impl.http.entity.CheckUpdate;

/**
 * Created by Eric on 2018/5/21.
 */
public class CheckUpdateResponseTest extends HttpResponseTest<CheckUpdate> {
    private CheckUpdate checkUpdate;

    public CheckUpdateResponseTest(String responseStr) {
        super(responseStr);
    }

    @Override
    protected void parseEntity() {
        checkUpdate = new CheckUpdate();
        JsonObject data = getServerJsonData().get("data").getAsJsonObject();

        checkUpdate = getGooleGson().fromJson(data, CheckUpdate.class);

    }

    public CheckUpdate getCheckUpdate() {
        return checkUpdate;
    }
}
