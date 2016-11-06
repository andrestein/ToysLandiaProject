package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    public  ArrayList<String> existeAdministrador(String nomAdm,String pass){
        ArrayList<String> lista=new ArrayList<>();
        Cursor registros;
        registros = db.rawQuery("SELECT * FROM ADMINISTRADOR WHERE NOM_ADM='"+nomAdm+"' AND PAS_ADM='"+pass+"';",null);
        if(registros.moveToFirst()){
            do {
                lista.add(registros.getString(0));
            }while (registros.moveToNext());
        }
        return lista;
    }
    //Actualiza el stock
    public void actualizarStockItem(String nomSuc,String cod,int stock){
        db.update("SUCITEM",generarConSucI(nomSuc,cod,stock+cantidadStock(nomSuc,cod)),
                "NOM_SUC =? AND COD_ITE=?",new String[]{nomSuc,cod});
    }
    //Inserta un item
    public void  insertarItem(String cod,String nom){
        db.insert("ITEM",null,generarConItem(cod,nom));
    }
    //Genera el los values para insertar un item
    private ContentValues generarConItem(String cod,String nom){
        ContentValues val=new ContentValues();
        val.put("COD_ITE",cod);
        val.put("NOM_ITE",nom);
        return val;
    }
    //Inserta un item en la SucItem
    public void insertarSucITEM(String nom,String cod){
        db.insert("SUCITEM",null,generarConSucI(nom,cod,1));
    }
    //Genera los values para generar los datos de los stocks
    private ContentValues generarConSucI(String nom,String cod,int stock){
        ContentValues val = new ContentValues();
        val.put("NOM_SUC",nom);
        val.put("COD_ITE",cod);
        val.put("STOCK",stock);
        return val;
    }
    //Informa si existe el item
    public boolean existeItem(String cod){
        ArrayList<String> lista=new ArrayList<>();
        Cursor registros;
        registros = db.rawQuery("SELECT * FROM SUCITEM WHERE COD_ITE='"+cod+"';",null);
        if(registros.moveToFirst()){
            do {
                lista.add(registros.getString(0)+registros.getString(1)+registros.getInt(2));
            }while (registros.moveToNext());
        }
        if(!lista.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    //Retorna la cantidad de un item
    private int cantidadStock(String nomSuc,String cod){
        Cursor registros;
        registros = db.rawQuery("SELECT * FROM SUCITEM WHERE NOM_SUC='"+nomSuc+"' AND COD_ITE='"+cod+"';",null);
        return Integer.parseInt(registros.getString(2));
    }
    //retorna un array de string de las suc
    public ArrayList<String> cSucursalLogin(String nomSuc,String pass){
        ArrayList<String> lista=new ArrayList<>();
        Cursor registros;
        registros = db.rawQuery("SELECT * FROM SUCURSAL WHERE NOM_SUC='"+nomSuc+"' AND PAS_SUC='"+pass+"';",null);
        if(registros.moveToFirst()){
            do {
                lista.add(registros.getString(0));
            }while (registros.moveToNext());
        }
        return lista;
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

    public static final String INSERT_BASEDATA1="insert into ADMINISTRADOR values('1017247090','ANDRES','12345');";
    public static final String INSERT_BASEDATA2="insert into SUCURSAL values('MEDELLIN','1234','ANDRES');";
    public static final String INSERT_BASEDATA3="insert into SUCITEM values('SANTA FE',12004567,'2');";
    public static final String INSERT_BASEDATA4="insert into ITEM values('12004567','BATMAN');";

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
