package ifpb.edu.br.keys_app.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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
    List<Chave> chaves;
    ArrayAdapter<Chave> adapter;
    LoginActivity loginDados = new LoginActivity();
    String matricula, nome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar);

        bt_buscar = (Button) findViewById(R.id.bt_buscar);
        et_nome = (EditText) findViewById(R.id.et_nome);
        bt_back = (ImageButton) findViewById(R.id.bt_back);
        bt_userInfo = (ImageButton) findViewById(R.id.bt_user);
        lv_chaves = (ListView) findViewById(R.id.lv_chaves);

        Bundle bundle = getIntent().getExtras();
        matricula = bundle.getString("matricula");
        nome = bundle.getString("nome");

        chaves = new ArrayList<Chave>();
        adapter = new ArrayAdapter<Chave>(this, android.R.layout.simple_list_item_activated_1, chaves);

        bt_userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), nome + ", " + matricula ,Toast.LENGTH_SHORT).show();
            }
        });

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        listarChaves();

        bt_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarChavesProcuradas();
            }
        });


    }
    public void listarChaves(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                Call<List<Chave>> call = ServerConnection.getInstance().getService().getAll();

                Log.i(this.getClass().getName(), "Calling list");

                call.enqueue(new Callback<List<Chave>>() {
                    @Override
                    public void onResponse(Call<List<Chave>> call, Response<List<Chave>> response) {

                        try{

                            if(response.isSuccessful()){
                                List<Chave> chavesResponse = response.body();

                                chaves.addAll(chavesResponse);
                                adapter.notifyDataSetChanged();
                            }
                            else{
                                Log.e(this.getClass().toString(), "Error on calling");
                            }


                        }
                        catch (Exception e){
                            Log.e(this.getClass().toString(), "Error on calling");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Chave>> call, Throwable t) {
                        Log.e("onFailure", "Error");
                    }
                });
            }
        }).start();
    }

    public void listarChavesProcuradas(){
        new Thread(new Runnable() {

            String sala = et_nome.getText().toString();

            @Override
            public void run() {

                Call<List<Chave>> call = ServerConnection.getInstance().getService().getChaveBySala(sala);

                Log.i(this.getClass().getName(), "Calling list");

                call.enqueue(new Callback<List<Chave>>() {
                    @Override
                    public void onResponse(Call<List<Chave>> call, Response<List<Chave>> response) {

                        try{

                            if(response.isSuccessful()){
                                List<Chave> chavesRespon = response.body();

                                chaves.addAll(chavesRespon);
                                adapter.notifyDataSetChanged();
                            }
                            else{
                                Log.e(this.getClass().toString(), "Error on calling");
                            }


                        }
                        catch (Exception e){
                            Log.e(this.getClass().toString(), "Error on calling");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Chave>> call, Throwable t) {
                        Log.e("onFailure", "Error");
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
