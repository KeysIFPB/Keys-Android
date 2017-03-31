package ifpb.edu.br.keys_app.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;

import ifpb.edu.br.keys_app.R;

import static ifpb.edu.br.keys_app.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    Button bt_locais_ocupados;
    Button bt_locais_livres;
    Button bt_locais;
    ImageButton bt_back;
    ImageButton bt_userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        bt_back = (ImageButton) findViewById(R.id.bt_back);
        bt_userInfo = (ImageButton) findViewById(R.id.bt_userInfo);
        bt_locais = (Button) findViewById(R.id.bt_locais);
        bt_locais_ocupados = (Button) findViewById(R.id.bt_locais_ocupados);
        bt_locais_livres = (Button) findViewById(R.id.bt_locais_livres);

        bt_locais_ocupados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OcupadasActivity.class);
                startActivity(intent);
                finish();

                }
            });

        bt_locais_livres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LivresActivity.class);
                startActivity(intent);
                finish();

            }
        });

        bt_locais .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocaisActivity.class);
                startActivity(intent);
                finish();

            }
        });

        bt_back .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        bt_userInfo .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }




    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Sair")
                .setMessage("Tem certeza que quer sair?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).create().show();
    }

}
