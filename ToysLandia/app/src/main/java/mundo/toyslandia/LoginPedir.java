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
import android.widget.Toast;

import DataBase.DataBaseMananger;

public class LoginPedir extends AppCompatActivity {

    private Button btnIniciarPedido;
    private EditText txtPassAdmin,txtNomAdmin;
    private Intent intento;
    private String nomAdmin ,pass;
    private DataBaseMananger mananger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pedir);
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

    }

    private void initComponents(){
        btnIniciarPedido =(Button)findViewById(R.id.btnIngresarPedir);
        txtNomAdmin= (EditText) findViewById(R.id.txtAdmin);
        txtPassAdmin=(EditText) findViewById(R.id.txtPassAdmin);
    }

    private void ingresar(){
        btnIniciarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomAdmin = txtNomAdmin.getText().toString();
                pass = txtPassAdmin.getText().toString();
                if(txtNomAdmin.getText().toString().equals("") || txtPassAdmin.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Debes ingresar todos los datos",Toast.LENGTH_SHORT).show();
                }else {
                    if (existeSucursal(nomAdmin,pass) == true){
                        intento = new Intent(getApplicationContext(),PedirActivity.class);
                        startActivity(intento);
                    }else{
                        Toast.makeText(getApplicationContext(),"La sucursal no existe",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean existeSucursal(String nomAdmin,String pass){
        String nom= mananger.existeAdministrador(nomAdmin,pass).get(0);
        if(!nom.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}
