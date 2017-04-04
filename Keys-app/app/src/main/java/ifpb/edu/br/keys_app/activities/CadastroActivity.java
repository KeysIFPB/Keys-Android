package ifpb.edu.br.keys_app.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ifpb.edu.br.keys_app.R;

import static android.R.id.progress;

public class CadastroActivity extends AppCompatActivity {

    EditText et_nome, et_matricula;
    Button bt_cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        et_nome = (EditText) findViewById(R.id.et_nome);
        et_matricula = (EditText) findViewById(R.id.et_matricula);
        bt_cadastro = (Button) findViewById(R.id.bt_cadastro);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bt_cadastro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(et_matricula.getText().length() == 0){
                    et_matricula.setError("Campo vazio");
                }else if (et_matricula.getText().length() < 11){
                    et_matricula.setError("Minimo 11 letras");
                }

                if(et_nome.getText().length() == 0){
                    et_nome.setError("Campo vazio");
                }

                // aqui acho que fica a parte de verificar se ta cadastrado

                Intent intent = new Intent(CadastroActivity.this, ListarActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }
}
