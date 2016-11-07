package mundo.toyslandia;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import DataBase.DataBaseMananger;

public class MostrarActivity extends AppCompatActivity {
    private static DataBaseMananger manan;
    private static Cursor cursor;
    private ListAdapter adap;
    ListView lvItems;
    ArrayList<String> datos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        lvItems=(ListView) findViewById(R.id.lvItems);

        //Comprobando si hay items

        datos=manan.cargarCursorProductos();

        if(!datos.isEmpty()) {
            adap = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
            lvItems.setAdapter(adap);
        }else{
            Toast.makeText(getApplicationContext(),"No hay ningun item",Toast.LENGTH_LONG).show();
        }

        String[]from=new String []{"SElECT SELECT NOM_SUC FROM SUCITEM","SELECT COD_ITE FROM SUCITEM","SELECT STOCK FROM SUCITEM"};
        int[]to=new int[]{android.R.id.text1,android.R.id.text2};
        manan=new DataBaseMananger(this);
        cursor=manan.cargarC();
        adap=new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,cursor,from,to,0);

        lvItems.setAdapter(adap);


    }
}
