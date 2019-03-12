package sc.com.commonlib.impl.http.response;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;

import sc.com.commonlib.api.http.HttpResponse;
import sc.com.commonlib.impl.http.entity.FloatingRate;

/**
 * Created by Eric on 2019/3/11.
 */
public class GetFloatingRateResponse extends HttpResponse<FloatingRate> {
    private FloatingRate floatingRate;
    public GetFloatingRateResponse(String responseStr) {
        super(responseStr);
    }

    @Override
    protected void parseEntity() {
        try {
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(responseStr);
            if(jsonElement.isJsonArray()){
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                for (JsonElement floating : jsonArray){
                    floatingRate = gson.fromJson(floating, FloatingRate.class);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public FloatingRate getFloatingRate() {
        return floatingRate;
    }
}
