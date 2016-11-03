package mundo.toyslandia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private Button btnIniciarSuc;
    private EditText txtPass,txtSuc;
    private Intent intento;
    public String nomSuc,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();

        ingresar();

    }

    private void initComponents(){
        btnIniciarSuc =(Button)findViewById(R.id.btnIniciarSuc);
        txtSuc= (EditText) findViewById(R.id.txtSuc);
        txtPass=(EditText) findViewById(R.id.txtPassSuc);
        intento = new Intent(this,MenuActivity.class);


    }

    private void ingresar(){
        btnIniciarSuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomSuc = txtSuc.getText().toString();
                pass = txtPass.getText().toString();
                if(txtSuc.getText().toString().equals("") || txtPass.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Debes ingresar todos los datos",Toast.LENGTH_SHORT).show();
                }else {
                    if (existeSucursal(nomSuc,pass) == 0){
                        startActivity(intento);
                    }else if(existeSucursal(nomSuc,pass)==1){
                        Toast.makeText(getApplicationContext(),"La sucursal no existe",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "No se pudo conectar con la base de datos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private int existeSucursal(String suc,String pass){/*
        try {
            final DataBaseMananger mananger= new DataBaseMananger(this);
            Cursor cursor = mananger.cSucursalLogin(suc, pass);
            if(cursor != null){
                return 0;
            }else {
                return 1;
            }
        }catch (Exception e) {
            return 2;
        }*/
        return 0;
    }
}

