package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LENOVO on 02/11/2016.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME ="ToysLandia.sqlite";
    private static final int DB_SCHEME_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseMananger.CREATE_ADMINISTRADOR);
        db.execSQL(DataBaseMananger.CREATE_ITEM);
        db.execSQL(DataBaseMananger.CREATE_SUCURSAL);
        db.execSQL(DataBaseMananger.CREATE_SUCITEM);
        db.execSQL(DataBaseMananger.INSERT_BASEDATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
