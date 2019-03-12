package sc.com.scdataview.applogiclayer.shipfilter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import sc.com.commonlib.common.util.ShipIconUtil;
import sc.com.commonlib.impl.http.entity.FilterType;
import sc.com.commonlib.impl.http.entity.ShipEquipment;
import sc.com.scdataview.R;

/**
 * Created by Eric on 2018/3/29.
 */

public class ShipFilterAdapter extends BaseQuickAdapter<FilterType, BaseViewHolder> {

    public ShipFilterAdapter(@Nullable List<FilterType> data) {
        super(R.layout.adapter_ship_filter, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FilterType item) {
        helper.setText(R.id.type_ch, item.getType_ch());


    }

}
