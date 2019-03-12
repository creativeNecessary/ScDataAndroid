package sc.com.commonlib.impl.http.response;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import sc.com.commonlib.api.http.HttpResponse;
import sc.com.commonlib.impl.http.entity.FilterType;

/**
 * Created by Eric on 2018/6/5.
 */
public class GetFilterDataResponse extends HttpResponse<FilterType> {

    private List<FilterType> filterTypeList;

    public GetFilterDataResponse(String responseStr) {
        super(responseStr);
    }

    @Override
    protected void parseEntity() {
        filterTypeList = new ArrayList<>();

        JsonArray data = data_json_obj.get("data").getAsJsonArray();
        for (JsonElement filter : data) {
            FilterType filterType = gson.fromJson(filter, FilterType.class);
            filterTypeList.add(filterType);
        }

    }

    public List<FilterType> getFilterTypeList() {
        return filterTypeList;
    }
}
