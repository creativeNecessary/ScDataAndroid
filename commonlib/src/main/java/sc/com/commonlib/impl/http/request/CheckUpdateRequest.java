package sc.com.commonlib.impl.http.request;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.Request;
import okhttp3.RequestBody;
import sc.com.commonlib.api.http.HttpRequest;
import sc.com.commonlib.common.constant.Http;
import sc.com.commonlib.common.util.HttpUtil;
import sc.com.commonlib.impl.http.entity.HttpParameter;
import sc.com.commonlib.impl.http.response.CheckUpdateResponse;

/**
 * Created by Eric on 2018/5/21.
 */
public class CheckUpdateRequest extends HttpRequest<CheckUpdateResponse>  {
    private int versionCode = -1;

    public CheckUpdateRequest(int versionCode) {
        this.versionCode = versionCode;

    }

    @Override
    protected CheckUpdateResponse initResponse(String responseStr) {
        return new CheckUpdateResponse(responseStr);
    }

    @Override
    protected Request initRequest() {
        HttpParameter parameter = new HttpParameter
                .Builder("checkUpdate","checkUpdate")
//                .Builder("checkUpdateTest","checkUpdateTest")
                .append("versionCode",versionCode)
                .build();

        return parameter.getRequest();
    }



}
