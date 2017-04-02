package ifpb.edu.br.keys_app.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

import ifpb.edu.br.keys_app.R;

import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import ifpb.edu.br.keys_app.models.Chave;
import ifpb.edu.br.keys_app.network.ServerConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarActivity extends AppCompatActivity {
    ImageButton bt_back, bt_userInfo;
    ListView lv_chaves;
    ArrayAdapter<Chave> adapter;
    List<Chave> chaves;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        bt_back = (ImageButton) findViewById(R.id.bt_back);
        bt_userInfo = (ImageButton) findViewById(R.id.bt_userInfo);
        lv_chaves = (ListView) findViewById(R.id.lv_chaves);
        chaves = new ArrayList<Chave>();
        adapter = new ArrayAdapter<Chave>(this, android.R.layout.simple_list_item_activated_1, chaves);
        lv_chaves.setAdapter(adapter);

        bt_back .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        listarChaves();
    }


    public void listarChaves(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                Call<List<Chave>> call = ServerConnection.getInstance().getService().getChaves();

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


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Sair")
                .setMessage("Tem certeza que quer sair?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(ListarActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).create().show();
    }
}