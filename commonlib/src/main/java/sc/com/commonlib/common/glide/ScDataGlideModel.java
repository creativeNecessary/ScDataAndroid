package sc.com.commonlib.common.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

import sc.com.commonlib.common.util.FileUtil;

import static sc.com.commonlib.common.constant.Common.cacheSize10M;
import static sc.com.commonlib.common.constant.Common.cacheSize200M;

/**
 * Created by Eric on 2018/3/9.
 */

@GlideModule
public class ScDataGlideModel extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        builder.setMemoryCache(new LruResourceCache(cacheSize10M))
                .setDiskCache(new DiskLruCacheFactory(FileUtil.getImageCacheDir().getAbsolutePath(), cacheSize200M));

    }
}
