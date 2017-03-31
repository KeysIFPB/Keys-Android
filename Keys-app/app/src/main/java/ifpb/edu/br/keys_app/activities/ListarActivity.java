package ifpb.edu.br.keys_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import ifpb.edu.br.keys_app.R;
import ifpb.edu.br.keys_app.models.Local;

public class ListarActivity extends AppCompatActivity {

    List<Local> locais;
    ListView lv_locais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        lv_locais = (ListView) findViewById(R.id.lv_locais);

    }
}
