package DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
<<<<<<< HEAD
=======
import android.database.sqlite.SQLiteOpenHelper;
>>>>>>> 7b91149ce12e7f4da67dd05f5ae8f42607c6d11d

import java.util.ArrayList;

/**
 * Created by LENOVO on 02/11/2016.
 */

public class DataBaseMananger {


    private DbHelper helper;
    private SQLiteDatabase db;
    /*cosas que flotan por el momento
    public static final String SUCITEM="items sucursal";
    public static final String NOM_SUC="nombre sucursal";
    public static final String COD_ITE="codigo item";
    public static final String STOCK="cantidad del item";*/

    public DataBaseMananger(Context context){
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public Cursor cSucursalLogin(String nomSuc,String pass){
<<<<<<< HEAD
        String[] columnas={"SELECT NOM_SUC FROM SUCURSAL","SELECT PAS_SUC FROM SUCURSAL"};
        return db.query("SELECT NOM_SUC,PAS_SUC FROM SUCURSAL",columnas,null,null,null,null,null);
=======
        return db.rawQuery("Select NOM_SUC,PAS_SUC FROM SUCURSAL WHERE NOM_SUC = 'SANTA FE' AND PAS_SUC='1234';",null);
>>>>>>> 7b91149ce12e7f4da67dd05f5ae8f42607c6d11d
    }


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
            "NOM_SUC text not null," +
            "PAS_SUC text not null," +
            "ADM_SUC text not null," +
            "constraint PKSUC primary key (NOM_SUC)," +
            "constraint FKSUC_ADM foreign key(ADM_SUC) references ADMINISTRADOR (COD_ADM)" +
            ")";

    public static final String CREATE_SUCITEM="CREATE TABLE SUCITEM (" +
            "NOM_SUC  text not null," +
            "COD_ITE text not null," +
            "STOCK integer not null," +
            "constraint PKSUCITEM primary key(NOM_SUC)," +
            "constraint FKSUCITEM_SUC foreign key(NOM_SUC) references SUCURSAL(NOM_SUC)," +
            "constraint FKSUCITEM_ITEM foreign key (COD_ITE) references ITEM (COD_ITE)" +
            ")";

    public static final String INSERT_BASEDATA="insert into ADMINISTRADOR values('1017247090','ANDRES','12345');" +
            "insert into SUCURSAL values('SANTA FE','1234','ANDRES');"+
            "insert into SUCITEM values('SANTA FE',12004567,'2');";

    //Añadir los datos para navegar
    //forma1
    public ArrayList<String> cargarCursorProductos(){
        ArrayList<String> lista=new ArrayList<>();
        //String q="SELECT * FROM SUCITEM";
        //SQLiteDatabase db1=helper.getReadableDatabase();
        Cursor registros=db.rawQuery("SELECT * FROM SUCITEM",null);
        if(registros.moveToFirst()){
            do{
                lista.add(registros.getString(0)+" "+registros.getString(1)+" "+registros.getInt(2));

            }while(registros.moveToNext());
        }else{
            lista.add("nada");
        }
        return lista;
    }
    //forma 2
    public Cursor cargarC(){
        String[] columnas={"SELECT NOM_SUC FROM SUCITEM","SELECT COD_ITE FROM SUCITEM","SELECT STOCK FROM SUCITEM"};
        return db.query("SELECT * FROM SUCITEM",columnas,null,null,null,null,null);
    }
}
