//package sc.com.data_provider.provider.impl;
//
//import android.net.Uri;
//import android.os.AsyncTask;
//
//import java.util.List;
//
//import sc.com.data_provider.content_provider.ContentProviderHelper;
//import sc.com.data_provider.entity.TranslateEntity;
//import sc.com.data_provider.provider.api.BaseProvider;
//import sc.com.data_provider.provider.api.ProviderListener;
//
//import static sc.com.data_provider.database.ScDatabaseConstant.SC_TRANSLATE_COLUMN_BELONG_TO;
//import static sc.com.data_provider.database.ScDatabaseConstant.SC_TRANSLATE_COLUMN_TAG;
//import static sc.com.data_provider.database.ScDatabaseConstant.SC_TRANSLATE_COLUMN_TYPE;
//import static sc.com.data_provider.database.ScDatabaseConstant.TABLE_NAME_SC_TRANSLATE;
//
///**
// * Created by Eric on 2018/4/10.
// */
//
//public class TranslateProvider implements BaseProvider{
//    private static TranslateProvider provider = new TranslateProvider();
//
//    private TranslateProvider() {
//
//    }
//    public static TranslateProvider getInstance(){
//        return provider;
//    }
//
//    public  void query(TranslateEntity data, ProviderListener.QueryListener<TranslateEntity> queryListener) {
//        String translate_query_selections = SC_TRANSLATE_COLUMN_TYPE + " = ? and "
//                + SC_TRANSLATE_COLUMN_BELONG_TO + " = ? and "
//                + SC_TRANSLATE_COLUMN_TAG + " = ? ";
//        ProviderTask providerTask = new ProviderTask.ProviderTaskBuilder()
//                .selection(translate_query_selections)
//                .operateType(ProviderTask.OperateType.query)
//                .selectionArg(data.getSelectionArg())
//                .projection(data.getProjection())
//                .uri(getUri())
//                .build();
//        TranslateDataCache.getInstance().query(data, providerTask, queryListener);
//    }
//
//
//
//    @Override
//    public Uri getUri() {
//        return ContentProviderHelper.getContentUri(TABLE_NAME_SC_TRANSLATE);
//
//    }
//}
