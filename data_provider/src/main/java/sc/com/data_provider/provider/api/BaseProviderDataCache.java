package sc.com.data_provider.provider.api;

import android.net.Uri;

import java.util.List;

import sc.com.data_provider.entity.DatabaseEntity;
import sc.com.data_provider.provider.impl.ProviderTask;

/**
 * Created by Eric on 2018/4/11.
 */

public interface BaseProviderDataCache<T extends DatabaseEntity> {

    void query(DatabaseEntity data, ProviderTask task);

    void insert(ProviderTask task);

    void delete( ProviderTask task);

    void update (ProviderTask task);



}
