package ifpb.edu.br.keys_app.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ifpb.edu.br.keys_app.R;
import ifpb.edu.br.keys_app.models.Usuario;
import ifpb.edu.br.keys_app.network.ServerConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                // faz verificação de campos
                if (matricula.getText().length() > 10 && matricula.getText().length() < 13) {
                // cria objeto que será mandando para o sistema
                    final Usuario usuarioRecebido = new Usuario(
                            nome.getText().toString(),
                            matricula.getText().toString()
                    );

                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            // chama o serviço de login
                            Call<Usuario> call = ServerConnection.getInstance().getService().login(usuarioRecebido);

                            call.enqueue(new Callback<Usuario>() {
                                @Override
                                public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                                    try {

                                        if (response.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "Bem vindo, " + nome.getText().toString() + "!" ,Toast.LENGTH_SHORT).show();
                                            // depois de logado, vai pra proxima activity levando dados do user

                                            matri = matricula.getText().toString();
                                            name = nome.getText().toString();

                                            Intent intent = new Intent(LoginActivity.this, ListarActivity.class);
                                            intent.putExtra("nome", name);
                                            intent.putExtra("matricula", matri);
                                            startActivity(intent);

                                        } else {
                                            Log.e(this.getClass().toString(), "Error on calling " + response.code());
                                        }
                                    } catch (Exception e) {
                                        Log.e(this.getClass().toString(), e.getMessage().toString());
                                    }


                                }

                                @Override
                                public void onFailure(Call<Usuario> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "Conexão falhou", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }).start();

                }else {

                    matricula.setError("Matricula inválida");
                }
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

    /*public void ok (){
        Intent intent = new Intent(LoginActivity.this, ListarActivity.class);
        intent.putExtra("nome", name);
        intent.putExtra("matricula", matri);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Bem vindo, " + name + "!" ,Toast.LENGTH_SHORT).show();
    }*/

}