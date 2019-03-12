package sc.com.data_provider.content_provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import sc.com.data_provider.database.ScDatabaseHelper;

import static sc.com.data_provider.content_provider.ContentProviderHelper.CODE_SC_SHIP_NAME;
import static sc.com.data_provider.content_provider.ContentProviderHelper.CODE_SC_TRANSLATE;
import static sc.com.data_provider.content_provider.ContentProviderHelper.authority;
import static sc.com.data_provider.database.ScDatabaseConstant.TABLE_NAME_SC_SHIP_NAME;
import static sc.com.data_provider.database.ScDatabaseConstant.TABLE_NAME_SC_TRANSLATE;

/**
 * Created by Eric on 2018/4/10.
 */

public class ScContentProvider extends ContentProvider {
    private ScDatabaseHelper databaseHelper;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(authority, TABLE_NAME_SC_TRANSLATE, CODE_SC_TRANSLATE);
        sUriMatcher.addURI(authority, TABLE_NAME_SC_SHIP_NAME, CODE_SC_SHIP_NAME);
    }

    @Override
    public boolean onCreate() {
        databaseHelper = new ScDatabaseHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        String tableName = ContentProviderHelper.getTableNameFromMatcher(sUriMatcher.match(uri));
        if(TextUtils.isEmpty(tableName)){
            throw new NullPointerException("receive null table name");
        }

        return database.query(tableName,projection,selection,selectionArgs,null,null,sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        return  ContentProviderHelper.getTableNameFromMatcher(sUriMatcher.match(uri));
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String tableName = ContentProviderHelper.getTableNameFromMatcher(sUriMatcher.match(uri));
        if(TextUtils.isEmpty(tableName)){
            throw new NullPointerException("receive null table name");
        }
        long id =  database.insert(tableName,null,values);
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String tableName = ContentProviderHelper.getTableNameFromMatcher(sUriMatcher.match(uri));
        if(TextUtils.isEmpty(tableName)){
            throw new NullPointerException("receive null table name");
        }
        return database.delete(tableName,selection,selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String tableName = ContentProviderHelper.getTableNameFromMatcher(sUriMatcher.match(uri));
        if(TextUtils.isEmpty(tableName)){
            throw new NullPointerException("receive null table name");
        }
        return database.update(tableName,values,selection,selectionArgs);
    }
}
