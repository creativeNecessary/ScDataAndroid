package sc.com.scdataview.applogiclayer.shipsearch;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import sc.com.data_provider.entity.ShipNameEntity;
import sc.com.scdataview.R;

/**
 * Created by Eric on 2018/8/2.
 */
public class ShipLocalSearchAdapter extends BaseQuickAdapter<ShipNameEntity,BaseViewHolder>{
    public ShipLocalSearchAdapter( @Nullable List<ShipNameEntity> data) {
        super(R.layout.adapter_local_ship_search, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShipNameEntity item) {
        helper.setText(R.id.name_text,item.getShip_name());


    }
}
