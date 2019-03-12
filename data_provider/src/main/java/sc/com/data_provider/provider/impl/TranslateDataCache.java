//package sc.com.data_provider.provider.impl;
//
//import android.database.Cursor;
//import android.net.Uri;
//import android.text.TextUtils;
//import java.util.ArrayList;
//import java.util.List;
//import sc.com.data_provider.content_provider.ContentProviderHelper;
//import sc.com.data_provider.entity.TranslateEntity;
//import sc.com.data_provider.provider.ProviderThreadPool;
//import sc.com.data_provider.provider.api.BaseProviderDataCache;
//import sc.com.data_provider.provider.api.ProviderDataCacheListener;
//import sc.com.data_provider.provider.api.ProviderListener;
//import static sc.com.data_provider.database.ScDatabaseConstant.TABLE_NAME_SC_TRANSLATE;
//
///**
// * Created by Eric on 2018/4/10.
// */
//
//public class TranslateDataCache implements BaseProviderDataCache<TranslateEntity> ,ProviderDataCacheListener{
//
//    private static TranslateDataCache translateDataCache = new TranslateDataCache();
//
//
//    private List<TranslateEntity> dataCache;
//
//    private TranslateDataCache() {
//        dataCache = new ArrayList<>();
//    }
//
//    public static TranslateDataCache getInstance() {
//
//        return translateDataCache;
//    }
//
//
//    @Override
//    public void query(TranslateEntity data, ProviderTask task, ProviderListener.QueryListener<TranslateEntity> queryListener) {
////        List<TranslateEntity> resultList = new ArrayList<>();
////        for (TranslateEntity translateEntity : dataCache) {
////            if (TextUtils.equals(translateEntity.getType(), data.getType())
////                    && TextUtils.equals(translateEntity.getBelong_to(), data.getBelong_to())
////                    && TextUtils.equals(translateEntity.getTag(), data.getTag())
////                    ) {
////                resultList.add(translateEntity);
////                break;
////            }
////        }
////        if (resultList.size()>0) {
////            queryListener.queryFinish(resultList);
////        }else {
////            task.setQueryListener((cursor)->{
////                //解析数据
////                queryListener.queryFinish(resultList);
////                cursor.close();
////            });
////            ProviderThreadPool.getInstance().getThreadPool().execute(task);
////        }
//
//    }
//
//    @Override
//    public void insert(List<TranslateEntity> dataList, ProviderTask task, ProviderListener.InsertListener insertListener) {
//
//    }
//
//    @Override
//    public void delete(List<TranslateEntity> dataList, ProviderTask task, ProviderListener.DeleteListener deleteListener) {
//
//    }
//
//    @Override
//    public void update(List<TranslateEntity> dataList, ProviderTask task, ProviderListener.UpdateListener updateListener) {
//
//    }
//
//
//    @Override
//    public void queryFinish(Cursor cursor) {
//
//    }
//
//    @Override
//    public void insertFinish(Uri uri) {
//
//    }
//
//    @Override
//    public void updateFinish(long data) {
//
//    }
//
//    @Override
//    public void deleteFinish(long data) {
//
//    }
//}
