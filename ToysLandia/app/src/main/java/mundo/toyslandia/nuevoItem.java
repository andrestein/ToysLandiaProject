package mundo.toyslandia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DataBase.DataBaseMananger;

public class nuevoItem extends AppCompatActivity {

    private String codigo,nombre,nomsuc;
    private TextView txtCod;
    private EditText nomItem;
    private Button btnIngresar;
    private DataBaseMananger mananger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_item);
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
        mananger= new DataBaseMananger(this);
        initComponents();
        ingresar();
    }

    private void initComponents(){
        Intent intent=getIntent();
        Bundle extras =intent.getExtras();
        if (extras != null) {
            codigo=(String)extras.get("codigo");
            nomsuc=(String)extras.get("nomsuc");
        }
        txtCod=(TextView)findViewById(R.id.txtCodigo);
        txtCod.setText(codigo);
        nomItem=(EditText)findViewById(R.id.txtNomItem);
        btnIngresar=(Button)findViewById(R.id.btnRegistrar);
    }

    private void ingresar(){
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre= nomItem.getText().toString();
                if(nombre != ""){
                    try{
                        mananger.insertarItem(codigo,nombre);
                        mananger.insertarSucITEM(nomsuc,codigo);
                        Toast.makeText(getApplicationContext(),"El item fue registrado con exito",Toast.LENGTH_SHORT);
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"No se pudo conectar con la base de datos",Toast.LENGTH_SHORT);
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Debes ingresar el nombre del item",Toast.LENGTH_SHORT);
                }
            }
        });
    }

}
