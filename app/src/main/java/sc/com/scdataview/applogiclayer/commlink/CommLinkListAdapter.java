package sc.com.scdataview.applogiclayer.commlink;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import sc.com.commonlib.common.glide.GlideApp;
import sc.com.commonlib.common.util.HttpUtil;
import sc.com.commonlib.impl.http.entity.CommLinkItem;
import sc.com.scdataview.R;

/**
 * Created by Eric on 2018/5/31.
 */
public class CommLinkListAdapter extends BaseQuickAdapter<CommLinkItem, BaseViewHolder> {
    public CommLinkListAdapter(@Nullable List<CommLinkItem> data) {
        super(R.layout.adapter_comm_link_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommLinkItem item) {
        ImageView comm_link_bg = helper.getView(R.id.comm_link_bg);
        helper.setText(R.id.title_text,item.getTitle());
        GlideApp.with(mContext)
                .load(HttpUtil.getInstance().getRobertSpaceUrl(item.getBackground()))
                .thumbnail(0.1f)
                .into(comm_link_bg);
    }
}
