package sc.com.scdataview.applogiclayer.shiplist;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import sc.com.commonlib.common.glide.GlideApp;
import sc.com.commonlib.common.util.HttpUtil;
import sc.com.commonlib.impl.http.entity.ShipEntity;
import sc.com.scdataview.R;

/**
 * Created by Eric on 2018/3/14.
 */

public class ShipListAdapter extends BaseQuickAdapter<ShipEntity, BaseViewHolder> {
    public ShipListAdapter(@Nullable List data) {
        super(R.layout.adapter_ship_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShipEntity item) {
        ImageView ship_icon = helper.getView(R.id.ship_icon);
        helper.setText(R.id.name_text, item.getName());
//        helper.setText(R.id.size_text, item.getSize());
//        helper.setText(R.id.focus_text, item.getFocus());
//        helper.setText(R.id.max_crew_text, item.getMax_crew());
//        helper.setText(R.id.length_text, item.getLength());
        GlideApp.with(mContext)
                .load(HttpUtil.getInstance().getRobertSpaceUrl(item.getStore_large()))
                .thumbnail(0.1f)
                .into(ship_icon);
    }


}
