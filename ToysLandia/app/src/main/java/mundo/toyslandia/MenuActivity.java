package mundo.toyslandia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btnScan, btnMostrar,btnIngresarPedir;
    private String nomSuc;
    private String pass;
    private Intent intento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponents();
        abrirActiviti();
        mostrarLista();
    }

    private void initComponents(){
        btnScan= (Button) findViewById(R.id.btnAbrirScaner);
        btnMostrar=(Button) findViewById(R.id.btnMostrar);
        btnIngresarPedir=(Button) findViewById(R.id.btnIngresarPedir);
        Intent intent=getIntent();
        Bundle extras =intent.getExtras();
        if (extras != null) {
            nomSuc=(String)extras.get("nombre");
            pass= (String) extras.get("pass");
        }

    }

    private void abrirActiviti(){
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intento = new Intent(getApplicationContext(),ScanActivity.class);
                intento.putExtra("nombre",nomSuc+"");
                intento.putExtra("pass",pass+"");
                startActivity(intento);
            }
        });

        btnIngresarPedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intento = new Intent(getApplicationContext(),PedirActivity.class);
                startActivity(intento);
            }
        });
    }
    //Metodo para cambiar a la Activity de Mostrar

    private void mostrarLista(){
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mostrarInte=new Intent(getApplicationContext(),MostrarActivity.class);
                startActivity(mostrarInte);
            }
        });
    }
}
