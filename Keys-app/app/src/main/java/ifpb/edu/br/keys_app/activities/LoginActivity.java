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
import android.widget.Toast;

import ifpb.edu.br.keys_app.R;

public class LoginActivity extends AppCompatActivity {
    EditText matricula, nome;
    Button login, cadastrar;
    String matri, name;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        matricula = (EditText) findViewById(R.id.et_matricula_login);
        nome = (EditText) findViewById(R.id.et_nome_login);

        login = (Button) findViewById(R.id.bt_login);
        cadastrar = (Button) findViewById(R.id.bt_cadastro);



        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(matricula.getText().length() == 0){
                    matricula.setError("Campo vazio");
                }else if (matricula.getText().length() < 11){
                    matricula.setError("Minimo 11 letras");
                } else {
                    matri = matricula.getText().toString();
                    name = nome.getText().toString();
                    ok();
                }

                if(nome.getText().length() == 0){
                    nome.setError("Campo vazio");
                }




                // aqui acho que fica a parte de verificar se ta cadastrado
            }



        });

        cadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
                finish();


            }

        });
    }

    public void ok (){
        Intent intent = new Intent(LoginActivity.this, ListarActivity.class);
        intent.putExtra("nome", name);
        intent.putExtra("matricula", matri);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Bem vindo, " + name + "!" ,Toast.LENGTH_SHORT).show();
    }


}