package mundo.toyslandia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DataBase.DataBaseMananger;


public class LoginActivity extends AppCompatActivity {

    private Button btnIniciarSuc;
    private EditText txtPass,txtSuc;
    private Intent intento;
    private String nomSuc ,pass;
    private DataBaseMananger mananger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mananger= new DataBaseMananger(this);
        initComponents();
        ingresar();

    }

    private void initComponents(){
        btnIniciarSuc =(Button)findViewById(R.id.btnIniciarSuc);
        txtSuc= (EditText) findViewById(R.id.txtSuc);
        txtPass=(EditText) findViewById(R.id.txtPassSuc);
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
                    try {
                        if (existeSucursal(nomSuc, pass)) {
                            intento = new Intent(getApplicationContext(), MenuActivity.class);
                            intento.putExtra("nombre", nomSuc + "");
                            intento.putExtra("pass", pass + "");
                            startActivity(intento);
                        } else {
                            Toast.makeText(getApplicationContext(), "La sucursal no existe", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), "La sucursal no existe", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean existeSucursal(String suc,String pass){
        String nom= mananger.cSucursalLogin(suc,pass).get(0);
        if(!nom.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}

