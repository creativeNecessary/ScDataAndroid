package sc.com.commonlib.impl.http.response;

import com.google.gson.JsonObject;

import sc.com.commonlib.api.http.HttpRequest;
import sc.com.commonlib.api.http.HttpResponse;
import sc.com.commonlib.impl.http.entity.CheckUpdate;

/**
 * Created by Eric on 2018/5/21.
 */
public class CheckUpdateResponse extends HttpResponse <CheckUpdate>{
    private CheckUpdate checkUpdate;

    public CheckUpdateResponse(String responseStr) {
        super(responseStr);
    }

    @Override
    protected void parseEntity() {
        checkUpdate = new CheckUpdate();
        JsonObject data = data_json_obj.get("data").getAsJsonObject();
        checkUpdate = gson.fromJson(data,CheckUpdate.class);

    }

    public CheckUpdate getCheckUpdate() {
        return checkUpdate;
    }
}
