package ifpb.edu.br.keys_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import ifpb.edu.br.keys_app.R;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("TAB 1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Bem vindos !");


        TabHost.TabSpec spec2 = tabHost.newTabSpec("TAB 2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Locais Ocupados");


        TabHost.TabSpec spec3 = tabHost.newTabSpec("TAB 3");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator("Locais Livres");


        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);
    }
}
