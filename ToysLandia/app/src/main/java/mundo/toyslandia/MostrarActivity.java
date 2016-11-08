package mundo.toyslandia;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    Button btnMenu;
    ArrayList<String> datos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        lvItems=(ListView) findViewById(R.id.lvItems);
        btnMenu=(Button) findViewById(R.id.btnMenu);

        //Comprobando si hay items

        datos=manan.cargarCursorProductos();

        if(!datos.isEmpty()) {
            adap = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
            lvItems.setAdapter(adap);
        }else{
            Toast.makeText(getApplicationContext(),"No hay ningun item",Toast.LENGTH_LONG).show();
        }
        //volviendo al menu
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intento);

            }
        });

    }
}
