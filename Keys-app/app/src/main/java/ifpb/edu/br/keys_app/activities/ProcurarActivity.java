package ifpb.edu.br.keys_app.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.List;

import ifpb.edu.br.keys_app.R;
import ifpb.edu.br.keys_app.models.Chave;
import ifpb.edu.br.keys_app.network.ServerConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProcurarActivity extends AppCompatActivity {

    TextView tv_chaves;
    EditText et_nome;
    Button bt_buscar;
    ImageButton bt_back, bt_userInfo;
    ListView lv_chaves;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar);

        tv_chaves = (TextView) findViewById(R.id.tv_chaves);
        bt_buscar = (Button) findViewById(R.id.bt_buscar);
        et_nome = (EditText) findViewById(R.id.et_nome);
        bt_back = (ImageButton) findViewById(R.id.bt_back);
        bt_userInfo = (ImageButton) findViewById(R.id.bt_user);
        lv_chaves = (ListView) findViewById(R.id.lv_chaves);


        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        bt_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Procurar();
            }
        });
    }


    public void Procurar() {
        new Thread(new Runnable() {

            String cod = et_nome.getText().toString();

            @Override
            public void run() {

                ServerConnection.getInstance().getService().getChavesPorNome(nome);

                Call<List<Chave>> call = ServerConnection.getInstance().getService().getSinePorCod(cod);

                Log.i(this.getClass().getName(), "Calling Chave");

                call.enqueue(new Callback<List<Chave>>() {
                    @Override
                    public void onResponse(Call<List<Chave>> call, Response<List<Chave>> response) {

                        try {

                            if (response.isSuccessful()) {
                                List<Chave> chaves = response.body();

                                Chave chave = chaves.get(0);
                                tv_chaves.setText(chave.toString());

                            } else {
                                Log.e(this.getClass().toString(), "Error on calling " + response.code() + " Cód: " + cod);
                            }
                        } catch (Exception e) {
                            Log.e(this.getClass().toString(), e.getMessage().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Chave>> call, Throwable t) {
                        Log.e("onFailure", "Error" + t.getMessage());
                    }
                });
            }
        }).start();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ListarActivity.class);
        startActivity(intent);
        finish();
    }

}
