package ifpb.edu.br.keys_app.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import ifpb.edu.br.keys_app.R;

import static ifpb.edu.br.keys_app.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    Button bt_locais_ocupados, bt_locais_livres, bt_locais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

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

    }
}
