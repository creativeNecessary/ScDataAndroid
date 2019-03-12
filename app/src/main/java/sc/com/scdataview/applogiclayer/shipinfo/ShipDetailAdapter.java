package sc.com.scdataview.applogiclayer.shipinfo;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import sc.com.commonlib.common.util.ShipIconUtil;
import sc.com.commonlib.impl.http.entity.ShipEquipment;
import sc.com.scdataview.R;

/**
 * Created by Eric on 2018/3/29.
 */

public class ShipDetailAdapter extends BaseQuickAdapter<ShipEquipment, BaseViewHolder> {

    public ShipDetailAdapter( @Nullable List<ShipEquipment> data) {
        super(R.layout.adapter_ship_detail, data);
    }
//    TOTAL RACKS --mounts
//    PER RACK -- quantity

//    RACK SIZE -- size
//    MISSILE SIZE -- component_size
    @Override
    protected void convert(BaseViewHolder helper, ShipEquipment item) {
        helper.setImageResource(R.id.icon, ShipIconUtil.getIconDrawableId(item.getType()));
        helper.setText(R.id.type_text, item.getType_ch());

        helper.setText(R.id.size_text, item.getSize());
        helper.setText(R.id.component_size_text, item.getComponent_size());
        helper.setText(R.id.quantity_text, item.getQuantity() + "*" + item.getMounts());

//
    }

}
