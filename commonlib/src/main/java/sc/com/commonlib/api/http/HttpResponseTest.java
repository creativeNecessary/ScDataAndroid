package sc.com.commonlib.api.http;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import sc.com.commonlib.impl.http.entity.SerializableBaseData;

/**
 * Created by Eric on 2018/3/1.
 */

public abstract class HttpResponseTest<T extends SerializableBaseData> extends SerializableBaseData {
    protected String status;
    protected int code;
    protected String msg;
    protected String data_json_obj;

    public HttpResponseTest(String responseStr) {
        //初始化基础字段
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(responseStr);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        status = jsonObject.get("status").getAsString();
        code = jsonObject.get("code").getAsInt();
        msg = jsonObject.get("message").getAsString();
        data_json_obj = jsonObject.get("response_json").getAsJsonObject().toString();
        parseEntity();

    }


    protected JsonObject getServerJsonData() {

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(data_json_obj);

        return jsonElement.getAsJsonObject();
    }

    protected Gson getGooleGson(){
        return new Gson();
    }


    protected abstract void parseEntity();

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
