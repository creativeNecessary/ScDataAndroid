package sc.com.data_provider.provider.api;

import android.database.Cursor;
import android.net.Uri;

import sc.com.data_provider.provider.impl.ProviderTask;

/**
 * Created by Eric on 2018/4/11.
 */

public interface ProviderDataCacheListener {
    void queryFinish(ProviderTask task, Cursor cursor);

    void insertFinish(ProviderTask task, Uri uri);

    void updateFinish(ProviderTask task, long update_result);

    void deleteFinish(ProviderTask task, long delete_result);

}
