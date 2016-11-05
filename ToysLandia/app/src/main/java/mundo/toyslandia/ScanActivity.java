package mundo.toyslandia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import DataBase.DataBaseMananger;

public class ScanActivity extends AppCompatActivity implements View.OnClickListener{

    private Button scanBtn;
    private TextView formatTxt, contentTxt;
    private String nomSuc,pass;
    private IntentResult scanning;
    private DataBaseMananger mananger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan2);
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
    private void initComponents() {
        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);
        Intent intent=getIntent();
        Bundle extras =intent.getExtras();
        if (extras != null) {
            nomSuc=(String)extras.get("nombre");
            pass= (String) extras.get("pass");
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        scanning = scanningResult;
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            if(mananger.existeItem(scanContent)){
                try {
                    mananger.actualizarStockItem(nomSuc, scanContent, 1);
                    Toast.makeText(getApplicationContext(),"Los datos se actualizaron con exito",Toast.LENGTH_LONG);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Ocurrio un problema al actualizar",Toast.LENGTH_LONG);
                }
            }else{
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(this);

                builder.setMessage("¿El item no existe deseas crear uno nuevo?")
                        .setTitle("Confirmacion")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()  {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent;
                                intent = new Intent(getApplicationContext(),nuevoItem.class);
                                intent.putExtra("codigo",contentTxt.getText().toString());
                                intent.putExtra("nomsuc",nomSuc);
                                startActivity(intent);
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.create();
                builder.show();
            }

            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }else{

        }
    }
}
