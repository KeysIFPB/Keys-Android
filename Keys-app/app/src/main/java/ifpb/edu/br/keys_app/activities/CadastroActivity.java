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

import java.util.List;

import ifpb.edu.br.keys_app.R;
import ifpb.edu.br.keys_app.models.Chave;
import ifpb.edu.br.keys_app.models.Usuario;
import ifpb.edu.br.keys_app.network.ServerConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                Snackbar.make(view, "Para maiores informações, vá até a biblioteca! ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bt_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_matricula.getText().length() > 10 && et_matricula.getText().length() < 13 && et_nome.getText().length() > 4  ){

                    final String nome = et_nome.getText().toString();
                    final String matricula = et_matricula.getText().toString();

                    final Usuario usuario = new Usuario(nome, matricula);

                    new Thread(new Runnable() {

                        @Override
                        public void run() {

                            Call<Usuario> call = ServerConnection.getInstance().getService().insert(usuario);


                            call.enqueue(new Callback<Usuario>() {
                                @Override
                                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                    try {

                                        if (response.isSuccessful()) {

                                            Toast.makeText(getApplicationContext(), "Usuário " + response.body().getNome() + " cadastrado!"
                                                    ,Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(CadastroActivity.this, ListarActivity.class);
                                            intent.putExtra("nome", nome);
                                            intent.putExtra("matricula", matricula);
                                            startActivity(intent);

                                        } else {
                                            Log.e(this.getClass().toString(), "Error on calling " + response.code() );
                                        }
                                    } catch (Exception e) {
                                        Log.e(this.getClass().toString(), e.getMessage().toString());
                                    }

                                }

                                @Override
                                public void onFailure(Call<Usuario> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "Conexão falhou" ,Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                }).start();

            /*  Intent intent = new Intent(CadastroActivity.this, ListarActivity.class);
                intent.putExtra("nome", response.body().getNome());
                intent.putExtra("matricula", response.body().getMatricula());
                startActivity(intent);

                */

                }else{
                    et_matricula.setError("Matricula incorreta");
                    et_nome.setError("Nome insuficiente");
                    Snackbar.make(v, "verifique os campos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                // aqui acho que fica a parte de verificar se ta cadastrado

              //  Intent intent = new Intent(CadastroActivity.this, ListarActivity.class);
              //  startActivity(intent);
              //  finish();

            }
        });
    }

}