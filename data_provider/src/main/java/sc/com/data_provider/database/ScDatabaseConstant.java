package sc.com.data_provider.database;

/**
 * Created by Eric on 2018/4/10.
 */

public final class ScDatabaseConstant {

    public static final String DB_NAME = "SC_DATA";
    public static final int DB_VERSION = 2;


    public static final String CREATE_DB_SC_TRANSLATE = "CREATE TABLE SC_TRANSLATE ("
            + " id INT PRIMARY  KEY   NOT NULL,"
            + " type            TEXT  NOT NULL,"//翻译隶属于 飞船、星球、新闻、开发公告
            + " belong_to       TEXT  NOT NULL,"//具体数据 哪艘飞船、哪个星球、哪个开发公告
            + " tag             TEXT  NOT NULL,"//如果是飞船 是属于飞船的 名字或者简介或者其他
            + " original_text   TEXT  NOT NULL,"//原文
            + " translate_value TEXT  NOT NULL"//具体内容
            +")";

    public static final String TABLE_NAME_SC_TRANSLATE = "SC_TRANSLATE";
    public static final String SC_TRANSLATE_COLUMN_TYPE = "type";
    public static final String SC_TRANSLATE_COLUMN_BELONG_TO = "belong_to";
    public static final String SC_TRANSLATE_COLUMN_TAG = "tag";
    public static final String SC_TRANSLATE_COLUMN_ORIGINAL_TEXT = "original_text";
    public static final String SC_TRANSLATE_COLUMN_TRANSLATE_VALUE = "translate_value";


    public static final String TABLE_NAME_SC_SHIP_NAME = "SC_SHIP_NAME";
    public static final String SC_SHIP_NAME_COLUMN_SHIP_NAME = "ship_name";
    public static final String SC_SHIP_NAME_COLUMN_SHIP_NAME_CH = "ship_name_ch";
    public static final String SC_SHIP_NAME_COLUMN_SHIP_ID = "ship_id";


    public static final String CREATE_DB_SC_SHIP_NAME = "Create TABLE SC_SHIP_NAME("
            +"id INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"ship_name text,"
            +"ship_name_ch text,"
            +"ship_id text"
            +")";




}
