package mundo.toyslandia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DataBase.DataBaseMananger;


public class LoginActivity extends AppCompatActivity {

    private Button btnIniciarSuc;
    private AutoCompleteTextView txtSuc;
    private EditText txtPass;
    private Intent intento;
    private DataBaseMananger mananger;
    public static String nomSuc,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
        ingresar();

    }

    private void initComponents(){
        btnIniciarSuc =(Button)findViewById(R.id.btnIniciarSuc);
        txtSuc= (AutoCompleteTextView) findViewById(R.id.txtSuc);
        txtPass=(EditText)findViewById(R.id.password);
        intento = new Intent(this,MenuActivity.class);
        mananger= new DataBaseMananger(this);

    }

    private void ingresar(){
        btnIniciarSuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomSuc =txtSuc.getText().toString();
                pass=txtPass.getText().toString();
                if(nomSuc != "" && pass != ""){
                    if (existeSucursal(nomSuc,pass)){
                        startActivity(intento);
                    }else{
                        Toast.makeText(getApplicationContext(),"La sucursal no existe",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Debes ingresar todos los datos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean existeSucursal(String suc,String pass){
        return true;
    }
}

