package sc.com.data_provider.provider.impl;

import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sc.com.data_provider.database.ScDatabaseConstant;
import sc.com.data_provider.entity.DatabaseEntity;
import sc.com.data_provider.entity.ShipNameEntity;
import sc.com.data_provider.provider.ProviderThreadPool;
import sc.com.data_provider.provider.api.BaseProviderDataCache;
import sc.com.data_provider.provider.api.ProviderDataCacheListener;

/**
 * Created by Eric on 2018/8/1.
 */
public class ShipNameProviderDataCache implements BaseProviderDataCache<ShipNameEntity>, ProviderDataCacheListener {

    private HashMap<String, List<ShipNameEntity>> dataCache;

    private static class ClassHolder {
        private static ShipNameProviderDataCache holder = new ShipNameProviderDataCache();
    }

    private ShipNameProviderDataCache() {
        dataCache = new HashMap<>();

    }

    public static ShipNameProviderDataCache getInstance() {
        return ClassHolder.holder;
    }

    @Override
    public void query(DatabaseEntity data, ProviderTask task) {
        if(data!=null){
            List<ShipNameEntity> cacheResult = dataCache.get(data.getSelectionArg()[0]);
            if (cacheResult != null) {
                task.getQueryListener().onDataBaseQueryFinish(cacheResult);
            } else {
                task.setDataBaseListener(this);
                ProviderThreadPool.getInstance().getThreadPool().execute(task);

            }
        }else {
            task.setDataBaseListener(this);
            ProviderThreadPool.getInstance().getThreadPool().execute(task);

        }


    }

    @Override
    public void insert(ProviderTask task) {
        task.setDataBaseListener(this);
        ProviderThreadPool.getInstance().getThreadPool().execute(task);
    }

    @Override
    public void delete(ProviderTask task) {
        dataCache.clear();
        task.setDataBaseListener(this);
        ProviderThreadPool.getInstance().getThreadPool().execute(task);
    }

    @Override
    public void update(ProviderTask task) {
        dataCache.clear();
        task.setDataBaseListener(this);
        ProviderThreadPool.getInstance().getThreadPool().execute(task);
    }


    @Override
    public void queryFinish(ProviderTask task, Cursor cursor) {
        //解析数据 添加缓存 缓存的key 为查询内容
        //单字段直接使用，多字段拼接使用
        List<ShipNameEntity> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            String ship_name = cursor.getString(cursor.getColumnIndex(ScDatabaseConstant.SC_SHIP_NAME_COLUMN_SHIP_NAME));
            String ship_name_ch = cursor.getString(cursor.getColumnIndex(ScDatabaseConstant.SC_SHIP_NAME_COLUMN_SHIP_NAME_CH));
            String ship_id = cursor.getString(cursor.getColumnIndex(ScDatabaseConstant.SC_SHIP_NAME_COLUMN_SHIP_ID));
            ShipNameEntity shipName = new ShipNameEntity(ship_name,ship_name_ch, ship_id);
            data.add(shipName);
        }
        task.getQueryListener().onDataBaseQueryFinish(data);
    }

    @Override
    public void insertFinish(ProviderTask task, Uri uri) {

        task.getInsertListener().onDataBaseInsertFinish(uri);
    }

    @Override
    public void updateFinish(ProviderTask task, long data) {
        task.getUpdateListener().onDataBaseUpdateFinish(data);
    }

    @Override
    public void deleteFinish(ProviderTask task, long data) {

        task.getDeleteListener().onDataBaseDeleteFinish(data);
    }
}
