package sc.com.data_provider.provider.api;

import android.net.Uri;

import java.util.List;

import sc.com.data_provider.entity.DatabaseEntity;

/**
 * Created by Eric on 2018/4/11.
 */

public interface ProviderListener {

    interface QueryListener<T extends DatabaseEntity> {
        void onDataBaseQueryFinish(List<T> query_result);
    }

    interface InsertListener {
        void onDataBaseInsertFinish(Uri insert_result);
    }

    interface UpdateListener {
        void onDataBaseUpdateFinish(long update_result);
    }

    interface DeleteListener {
        void onDataBaseDeleteFinish(long delete_result);
    }

}
