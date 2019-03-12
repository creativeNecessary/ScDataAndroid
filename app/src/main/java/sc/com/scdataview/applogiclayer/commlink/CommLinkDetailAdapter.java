package sc.com.scdataview.applogiclayer.commlink;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import sc.com.commonlib.common.glide.GlideApp;
import sc.com.commonlib.common.util.HttpUtil;
import sc.com.commonlib.impl.http.entity.CommLinkDetailItem;
import sc.com.commonlib.impl.http.entity.CommLinkItem;
import sc.com.scdataview.R;

/**
 * Created by Eric on 2018/5/31.
 */
public class CommLinkDetailAdapter extends BaseQuickAdapter<CommLinkDetailItem, BaseViewHolder> {

    public CommLinkDetailAdapter(@Nullable List<CommLinkDetailItem> data) {
        super(R.layout.adapter_comm_link_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommLinkDetailItem item) {
        LinearLayout title_layout = helper.getView(R.id.title_layout);
        LinearLayout content_layout = helper.getView(R.id.content_layout);
        ImageView item_image = helper.getView(R.id.item_image);

        TextView title_text = helper.getView(R.id.title_text);
        TextView content_en_text = helper.getView(R.id.content_en_text);
        TextView content_ch_text = helper.getView(R.id.content_ch_text);

        if (item.getContent_type().equals("image")) {
            title_layout.setVisibility(View.GONE);
            content_layout.setVisibility(View.GONE);
            item_image.setVisibility(View.VISIBLE);
            GlideApp.with(mContext)
                    .load(HttpUtil.getInstance().getRobertSpaceUrl(item.getContent_data()))
                    .thumbnail(0.1f)
                    .placeholder(R.mipmap.background_star)
                    .into(item_image);


        } else if (item.getContent_type().equals("content")) {
            title_layout.setVisibility(View.GONE);
            content_layout.setVisibility(View.VISIBLE);
            item_image.setVisibility(View.GONE);
            content_en_text.setText(item.getContent_data());
            content_ch_text.setText(item.getMachine_translate_data());


        } else if (item.getContent_type().equals("title")) {
            title_layout.setVisibility(View.VISIBLE);
            content_layout.setVisibility(View.GONE);
            item_image.setVisibility(View.GONE);
            title_text.setText(item.getContent_data());

        }

    }


}
