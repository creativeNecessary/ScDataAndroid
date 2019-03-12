package sc.com.data_provider.provider.impl;

import android.content.ContentValues;
import android.net.Uri;

import sc.com.data_provider.content_provider.ContentProviderHelper;
import sc.com.data_provider.database.ScDatabaseConstant;
import sc.com.data_provider.entity.DatabaseEntity;
import sc.com.data_provider.entity.ShipNameEntity;
import sc.com.data_provider.provider.api.BaseProvider;
import sc.com.data_provider.provider.api.ProviderListener;

import static sc.com.data_provider.database.ScDatabaseConstant.SC_SHIP_NAME_COLUMN_SHIP_ID;
import static sc.com.data_provider.database.ScDatabaseConstant.SC_SHIP_NAME_COLUMN_SHIP_NAME;
import static sc.com.data_provider.database.ScDatabaseConstant.SC_SHIP_NAME_COLUMN_SHIP_NAME_CH;
import static sc.com.data_provider.database.ScDatabaseConstant.TABLE_NAME_SC_SHIP_NAME;


/**
 * Created by Eric on 2018/8/1.
 */
public class ShipNameProvider implements BaseProvider{


    private static class ClassHolder {
        private static ShipNameProvider holder = new ShipNameProvider();
    }

    private ShipNameProvider() {

    }
    public static ShipNameProvider getInstance() {
        return ClassHolder.holder;
    }

    /**
     * 模糊查询英文名
     * @param data
     * @param queryListener
     */
    public void queryLikeEn(DatabaseEntity data, ProviderListener.QueryListener<ShipNameEntity> queryListener) {
        String translate_query_selections = SC_SHIP_NAME_COLUMN_SHIP_NAME + " LIKE  ? ";
        ProviderTask providerTask = new ProviderTask.ProviderTaskBuilder()
                .selection(translate_query_selections)
                .operateType(ProviderTask.OperateType.query)
                .selectionArg(data.getSelectionArg())
                .projection(data.getProjection())
                .queryListener(queryListener)
                .uri(getUri())
                .build();
        ShipNameProviderDataCache.getInstance().query(data,providerTask);

    }

    /**
     * 模糊查询英文名
     * @param data
     * @param queryListener
     */
    public void queryLikeCh(DatabaseEntity data, ProviderListener.QueryListener<ShipNameEntity> queryListener) {
        String translate_query_selections = SC_SHIP_NAME_COLUMN_SHIP_NAME_CH + " LIKE  ? ";
        ProviderTask providerTask = new ProviderTask.ProviderTaskBuilder()
                .selection(translate_query_selections)
                .operateType(ProviderTask.OperateType.query)
                .selectionArg(data.getSelectionArg())
                .projection(data.getProjection())
                .queryListener(queryListener)
                .uri(getUri())
                .build();
        ShipNameProviderDataCache.getInstance().query(data,providerTask);

    }

    /**
     * 查询指定飞船名
     * @param data
     * @param queryListener
     */
    public void query(DatabaseEntity data, ProviderListener.QueryListener<ShipNameEntity> queryListener) {
        String translate_query_selections = SC_SHIP_NAME_COLUMN_SHIP_ID + " = ? ";
        ProviderTask providerTask = new ProviderTask.ProviderTaskBuilder()
                .selection(translate_query_selections)
                .operateType(ProviderTask.OperateType.query)
                .selectionArg(data.getSelectionArg())
                .projection(data.getProjection())
                .queryListener(queryListener)
                .uri(getUri())
                .build();
        ShipNameProviderDataCache.getInstance().query(data,providerTask);

    }

    public void queryAll(ProviderListener.QueryListener<ShipNameEntity> queryListener) {
        ProviderTask providerTask = new ProviderTask.ProviderTaskBuilder()
                .operateType(ProviderTask.OperateType.query)
                .queryListener(queryListener)
                .uri(getUri())
                .build();
        ShipNameProviderDataCache.getInstance().query(null,providerTask);

    }

    public void insert(ShipNameEntity shipNameEntity,ProviderListener.InsertListener insertListener){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ScDatabaseConstant.SC_SHIP_NAME_COLUMN_SHIP_ID,shipNameEntity.getShip_id());
        contentValues.put(ScDatabaseConstant.SC_SHIP_NAME_COLUMN_SHIP_NAME,shipNameEntity.getShip_name());
        contentValues.put(ScDatabaseConstant.SC_SHIP_NAME_COLUMN_SHIP_NAME_CH,shipNameEntity.getShip_name_ch());

        ProviderTask providerTask = new ProviderTask.ProviderTaskBuilder()
                .operateType(ProviderTask.OperateType.insert)
                .contentValues(contentValues)
                .insertListener(insertListener)
                .uri(getUri())
                .build();
        ShipNameProviderDataCache.getInstance().insert(providerTask);
    }

    public void update(ShipNameEntity shipNameEntity,ProviderListener.UpdateListener updateListener){
        String where = SC_SHIP_NAME_COLUMN_SHIP_ID + " = ? ";
        ContentValues contentValues = new ContentValues();
        contentValues.put(ScDatabaseConstant.SC_SHIP_NAME_COLUMN_SHIP_ID,shipNameEntity.getShip_id());
        contentValues.put(ScDatabaseConstant.SC_SHIP_NAME_COLUMN_SHIP_NAME,shipNameEntity.getShip_name());
        contentValues.put(ScDatabaseConstant.SC_SHIP_NAME_COLUMN_SHIP_NAME_CH,shipNameEntity.getShip_name_ch());

        ProviderTask providerTask = new ProviderTask.ProviderTaskBuilder()
                .operateType(ProviderTask.OperateType.update)
                .contentValues(contentValues)
                .updateListener(updateListener)
                .where(where)
                .selectionArg(new String[]{shipNameEntity.getShip_id()})
                .uri(getUri())
                .build();
        ShipNameProviderDataCache.getInstance().update(providerTask);
    }

    public void deleteAll(ProviderListener.DeleteListener deleteListener){

        ProviderTask providerTask = new ProviderTask.ProviderTaskBuilder()
                .operateType(ProviderTask.OperateType.delete)
                .deleteListener(deleteListener)
                .uri(getUri())
                .build();
        ShipNameProviderDataCache.getInstance().delete(providerTask);
    }


    @Override
    public Uri getUri() {
        return ContentProviderHelper.getContentUri(TABLE_NAME_SC_SHIP_NAME);
    }



}
