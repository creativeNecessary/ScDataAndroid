package sc.com.commonlib.api.http;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import sc.com.commonlib.impl.http.entity.SerializableBaseData;

/**
 * Created by Eric on 2018/3/1.
 */

public abstract class HttpResponse<T extends SerializableBaseData> extends SerializableBaseData{
    protected String status;
    protected int code;
    protected String msg;
    protected JsonObject data_json_obj;
    protected String responseStr;
    protected Gson gson;

    public HttpResponse(String responseStr) {
        //初始化基础字段
        gson = new Gson();
        this.responseStr = responseStr;
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(responseStr);
        if(jsonElement.isJsonObject()){
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if(jsonObject.has("status")){
                status = jsonObject.get("status").getAsString();
            }
            if(jsonObject.has("code")){
                code = jsonObject.get("code").getAsInt();
            }
            if(jsonObject.has("msg")){
                msg = jsonObject.get("message").getAsString();
            }
            if(jsonObject.has("response_json")){
                data_json_obj = jsonObject.get("response_json").getAsJsonObject();
            }
        }
        parseEntity();

    }

    protected abstract void parseEntity() ;

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
