package sc.com.commonlib.common.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import sc.com.commonlib.common.constant.Http;
import sc.com.commonlib.impl.http.entity.NameValueEntity;

/**
 * Created by Eric on 2018/3/15.
 */

public class HttpUtil {


    private static class SingletonHolder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }


    private String baseRobertSpaceUrl = "";
    private String baseServiceUrl = "";

    private HttpUtil() {
        baseRobertSpaceUrl = getSysUrl(Http.TYPE_ROBERT);
        baseServiceUrl = getSysUrl(Http.TYPE_SERVICE_API);

    }



    public static HttpUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public  String getRobertSpaceUrl(String netUrl) {
        if(netUrl.contains("http") || netUrl.contains("https")){
            return netUrl;
        }

        return baseRobertSpaceUrl + netUrl;
    }

    public  String getServiceApiUrl(String netUrl) {
        return baseServiceUrl + netUrl;
    }

    public JSONObject getBaseRequestParam(String action){
        JSONObject jsonObject = new JSONObject();
        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            jsonObject.put("action",action);
            jsonObject.put("timestamp",timestamp);
            jsonObject.put("digital_signature",getMd5(action,timestamp));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public  String getMd5(String action,String timestamp) {
        return getDigitalSignature(action,timestamp);
    }

    public static CacheControl getOneDayCacheControl(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int target = 24-hour;
        return new CacheControl.Builder()
                .maxAge(target, TimeUnit.HOURS)
                .build();
    }

    private static native String getSysUrl(String type);

    private static native String getDigitalSignature(String operate, String timestamp);

    static {
        System.loadLibrary("sc_tool");
    }




}
