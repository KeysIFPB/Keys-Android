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

import ifpb.edu.br.keys_app.models.Sine;
import ifpb.edu.br.keys_app.network.ServerConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarActivity extends AppCompatActivity {
    ImageButton bt_back, bt_userInfo;
    ListView lvSines;
    ArrayAdapter<Sine> adapter;
    List<Sine> sines;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        bt_back = (ImageButton) findViewById(R.id.bt_back);
        bt_userInfo = (ImageButton) findViewById(R.id.bt_userInfo);
        lvSines = (ListView) findViewById(R.id.lv_locais);
        sines = new ArrayList<Sine>();
        adapter = new ArrayAdapter<Sine>(this, android.R.layout.simple_list_item_activated_1, sines);
        lvSines.setAdapter(adapter);

        bt_back .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        listarSinesComRaio();
    }


    public void listarSinesComRaio(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                Call<List<Sine>> call = ServerConnection.getInstance().getService().getSinesComRaio();

                Log.i(this.getClass().getName(), "Calling list");

                call.enqueue(new Callback<List<Sine>>() {
                    @Override
                    public void onResponse(Call<List<Sine>> call, Response<List<Sine>> response) {

                        try{

                            if(response.isSuccessful()){
                                List<Sine> sinesResponse = response.body();

                                sines.addAll(sinesResponse);
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
                    public void onFailure(Call<List<Sine>> call, Throwable t) {
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