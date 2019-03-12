package sc.com.data_provider.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eric on 2018/4/10.
 */

public final class ScDatabaseHelper extends SQLiteOpenHelper {

    public ScDatabaseHelper(Context context) {
        super(context, ScDatabaseConstant.DB_NAME, null, ScDatabaseConstant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScDatabaseConstant.CREATE_DB_SC_TRANSLATE);
        db.execSQL(ScDatabaseConstant.CREATE_DB_SC_SHIP_NAME);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                db.execSQL(ScDatabaseConstant.CREATE_DB_SC_SHIP_NAME);
            case 2:

            case 3:

                break;
        }

    }
}
