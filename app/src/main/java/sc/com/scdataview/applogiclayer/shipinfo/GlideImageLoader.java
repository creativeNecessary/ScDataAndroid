package sc.com.scdataview.applogiclayer.shipinfo;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

import sc.com.commonlib.common.glide.GlideApp;
import sc.com.commonlib.common.util.HttpUtil;
import sc.com.scdataview.R;

/**
 * Created by Eric on 2018/3/26.
 */

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        GlideApp.with(context)
                .load(HttpUtil.getInstance().getRobertSpaceUrl((String) path))
                .thumbnail(0.1f)
                .placeholder(R.mipmap.background_star)
                .into(imageView);
    }

}
