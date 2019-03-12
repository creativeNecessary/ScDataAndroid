package sc.com.data_provider.provider.impl;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import sc.com.data_provider.ScDataProvider;
import sc.com.data_provider.entity.DatabaseEntity;
import sc.com.data_provider.provider.api.ProviderDataCacheListener;
import sc.com.data_provider.provider.api.ProviderListener;

/**
 * Created by Eric on 2018/4/11.
 * 响应逻辑为
 * task保存发起请求回调
 * task查询完后会回调数据缓存层的回调
 * 在数据缓存层进行数据解析，缓存
 * 完成后调用task 的回调（回调至调用处，最终结果）
 */

public class ProviderTask implements Runnable {

    private String[] selectionArg;

    //与selectionArg构成where
    private String selection;

    //需要查询的列 也就是返回游标里面有什么数据
    private String[] projection;

    private String where;
    //数据集合
    private ContentValues contentValues;

    private OperateType operate_type;

    private Uri uri;

    private ContentResolver contentResolver;

    // 数据缓存层响应
    private ProviderDataCacheListener dataBaseListener;

    //每个请求对应的响应
    private ProviderListener.QueryListener queryListener;
    private ProviderListener.DeleteListener deleteListener;
    private ProviderListener.InsertListener insertListener;
    private ProviderListener.UpdateListener updateListener;

    private ProviderTask() {
        contentResolver = ScDataProvider.getContext().getContentResolver();
    }

    public enum OperateType {
        query,
        delete,
        insert,
        update
    }

    public void initDataWithBuilder(ProviderTaskBuilder builder) {
        this.operate_type = builder.operate_type;
        this.projection = builder.projection;
        this.where = builder.where;
        this.selection = builder.selection;
        this.selectionArg = builder.selectionArg;
        this.contentValues = builder.contentValues;
        this.uri = builder.uri;
        this.dataBaseListener = builder.dataBaseListener;
        this.queryListener = builder.queryListener;
        this.deleteListener = builder.deleteListener;
        this.updateListener = builder.updateListener;
        this.insertListener = builder.insertListener;

    }

    public void setDataBaseListener(ProviderDataCacheListener dataBaseListener) {
        this.dataBaseListener = dataBaseListener;
    }

    @Override
    public void run() {

        if (operate_type == OperateType.query) {
            Cursor result = contentResolver.query(uri, projection, selection, selectionArg, null);
            if (dataBaseListener != null) {
                dataBaseListener.queryFinish(this, result);
            }
        } else if (operate_type == OperateType.insert) {
            Uri result = contentResolver.insert(uri, contentValues);
            if (dataBaseListener != null) {
                dataBaseListener.insertFinish(this, result);
            }
        } else if (operate_type == OperateType.update) {
            int result = contentResolver.update(uri, contentValues, where, selectionArg);
            if (dataBaseListener != null) {
                dataBaseListener.updateFinish(this, result);
            }
        } else if (operate_type == OperateType.delete) {
            int result = contentResolver.delete(uri, where, selectionArg);
            if (dataBaseListener != null) {
                dataBaseListener.deleteFinish(this, result);
            }
        }

    }

    public  ProviderListener.QueryListener getQueryListener() {
        return queryListener;
    }

    public ProviderListener.DeleteListener getDeleteListener() {
        return deleteListener;
    }

    public ProviderListener.InsertListener getInsertListener() {
        return insertListener;
    }

    public ProviderListener.UpdateListener getUpdateListener() {
        return updateListener;
    }

    public static class ProviderTaskBuilder {

        private String[] selectionArg;

        //与selectionArg构成where
        private String selection;

        //需要查询的列 也就是返回游标里面有什么数据
        private String[] projection;

        private String where;
        //数据集合
        private ContentValues contentValues;

        private OperateType operate_type;

        private Uri uri;

        private ProviderDataCacheListener dataBaseListener;

        private ProviderListener.QueryListener queryListener;
        private ProviderListener.DeleteListener deleteListener;
        private ProviderListener.InsertListener insertListener;
        private ProviderListener.UpdateListener updateListener;


        public <T extends DatabaseEntity> ProviderTaskBuilder queryListener(ProviderListener.QueryListener<T> queryListener) {
            this.queryListener = queryListener;
            return this;
        }

        public ProviderTaskBuilder deleteListener(ProviderListener.DeleteListener deleteListener) {
            this.deleteListener = deleteListener;
            return this;
        }

        public ProviderTaskBuilder insertListener(ProviderListener.InsertListener insertListener) {
            this.insertListener = insertListener;
            return this;
        }

        public ProviderTaskBuilder updateListener(ProviderListener.UpdateListener updateListener) {
            this.updateListener = updateListener;
            return this;
        }


        public ProviderTaskBuilder dataBaseListener(ProviderDataCacheListener dataBaseListener) {
            this.dataBaseListener = dataBaseListener;
            return this;
        }


        public ProviderTaskBuilder operateType(OperateType operate_type) {
            this.operate_type = operate_type;
            return this;
        }

        public ProviderTaskBuilder selectionArg(String[] selectionArg) {
            this.selectionArg = selectionArg;
            return this;
        }

        public ProviderTaskBuilder uri(Uri uri) {
            this.uri = uri;
            return this;
        }

        public ProviderTaskBuilder selection(String selection) {
            this.selection = selection;
            return this;
        }

        public ProviderTaskBuilder projection(String[] projection) {
            this.projection = projection;
            return this;
        }

        public ProviderTaskBuilder where(String where) {
            this.where = where;
            return this;
        }

        public ProviderTaskBuilder contentValues(ContentValues contentValues) {
            this.contentValues = contentValues;
            return this;
        }


        public ProviderTask build() {
            ProviderTask providerTask = new ProviderTask();
            providerTask.initDataWithBuilder(this);
            return providerTask;
        }


    }


}
