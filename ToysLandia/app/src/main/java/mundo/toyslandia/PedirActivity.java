package mundo.toyslandia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PedirActivity extends AppCompatActivity {

    private EditText txtPedido;
    private Button btnPedir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir);
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
        accion();
    }

    private void initComponents(){
        txtPedido =(EditText)findViewById(R.id.txtPedido);
        btnPedir =(Button)findViewById(R.id.btnPedir);
    }

    private void accion(){
        btnPedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtPedido.getText().toString() != ""){
                    txtPedido.setText("");
                    Toast.makeText(getBaseContext(),"Tu pedido ha sido enviado",Toast.LENGTH_SHORT).show();;
                }else {
                    Toast.makeText(getBaseContext(),"Debes ingresar un pedido",Toast.LENGTH_SHORT).show();;
                }
            }
        });
    }
}
