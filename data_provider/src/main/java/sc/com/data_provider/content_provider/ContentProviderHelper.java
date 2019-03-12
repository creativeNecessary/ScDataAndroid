package sc.com.data_provider.content_provider;

import android.net.Uri;

import sc.com.data_provider.database.ScDatabaseConstant;

/**
 * Created by Eric on 2018/4/10.
 */

public class ContentProviderHelper {

    public static final String authority = "sc.com.database.contentProvider";
    public static final int CODE_SC_TRANSLATE = 0x01;
    public static final int CODE_SC_SHIP_NAME = 0x02;

    public static String getTableNameFromMatcher(int matcher) {
        String tableName = "";
        switch (matcher) {
            case CODE_SC_TRANSLATE:
                tableName = ScDatabaseConstant.TABLE_NAME_SC_TRANSLATE;
                break;
            case CODE_SC_SHIP_NAME:
                tableName = ScDatabaseConstant.TABLE_NAME_SC_SHIP_NAME;
                break;
        }
        return tableName;
    }

    /**
     * @param path 为在 ScContentProvider 中注册的String 字段 为数据库表名
     * @return
     */
    public static Uri getContentUri(String path) {
        String uriStr = "content://" + authority + "/" + path;
        return Uri.parse(uriStr);
    }

}
