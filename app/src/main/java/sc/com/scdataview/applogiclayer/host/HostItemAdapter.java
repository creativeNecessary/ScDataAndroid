package sc.com.scdataview.applogiclayer.host;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import sc.com.commonlib.common.entity.HostItem;
import sc.com.commonlib.common.glide.GlideApp;
import sc.com.scdataview.R;

/**
 * Created by Eric on 2018/5/24.
 */
public class HostItemAdapter extends BaseQuickAdapter<HostItem,BaseViewHolder> {
    public HostItemAdapter( @Nullable List<HostItem> data) {
        super(R.layout.adapter_host_drawer, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HostItem item) {

        helper.setText(R.id.item_text,item.getItem_name());
        ImageView imageView = helper.getView(R.id.item_image);
        imageView.setImageDrawable(ContextCompat.getDrawable(mContext,item.getItem_drawable()));
//        GlideApp.with(mContext)
//                .load(item.getItem_drawable())
//                .centerCrop()
//                .into(imageView);

    }
}
