package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by LENOVO on 02/11/2016.
 */

public class DataBaseMananger {


    private DbHelper helper;
    private SQLiteDatabase db;

    public DataBaseMananger(Context context){
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }
    /* Implementando el logeo
    public Cursor cSucursalLogin(){
        String[] columnas={"NOM_SUC","PAS_SUC","ADM_SUC"};
        db.
    }
    */

    public static final String CREATE_ADMINISTRADOR="CREATE TABLE ADMINISTRADOR(COD_ADM text," +
            " NOM_ADM text not null," +
            "PAS_ADM text not null," +
            "constraint PKADM primary key (COD_ADM))";

    public static final String CREATE_ITEM="CREATE TABLE ITEM(" +
            "COD_ITE text," +
            "NOM_ITE text not null," +
            "constraint PKITEM primary key(COD_ITE)" +
            ")";


    public static final String CREATE_SUCURSAL="CREATE TABLE SUCURSAL(" +
            "NOM_SUC text," +
            "PAS_SUC text not null," +
            "ADM_SUC text not null," +
            "constraint PKSUC primary key (NOM_SUC)," +
            "constraint FKSUC_ADM foreign key(ADM_SUC) references ADMINISTRADOR (COD_ADM)" +
            ")";

    public static final String CREATE_SUCITEM="CREATE TABLE SUCITEM(" +
            "NOM_SUC text," +
            "COD_ITE text," +
            "STOCK integer not null," +
            "constraint PKSUCITEM primary key(NOM_SUC,COD_ITE)," +
            "constraint FKSUCITEM_SUC foreign key(NOM_SUC) references SUCURSAL(NOM_SUC)," +
            "constraint FKSUCITEM_ITEM foreign key (COD_ITE) references ITEM (COD_ITE)" +
            ")";

    public static final String INSERT_BASEDATA="insert into ADMINISTRADOR values('1017247090','ANDRES','12345');" +
            "insert into SUCURSAL values('SANTA FE','1234','ANDRES');";
}
