package ifpb.edu.br.keys_app.models;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ifpb.edu.br.keys_app.R;

/**
 * Created by Mateus on 01/04/2017.
 */

public class AdapterLocais extends BaseAdapter {
    private final List<Sine> sines;
    private final Activity act;

    public AdapterLocais(List<Sine> sines, Activity act) {
        this.sines = sines;
        this.act = act;
    }

    // métodos

    @Override
    public int getCount() {
        return sines.size();
    }

    @Override
    public Object getItem(int position) {
        return sines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.activity_listar, parent, false);
        Sine sine = sines.get(position);

        //pegando as referências das Views
        TextView nome = (TextView)
                view.findViewById(R.id.lv_locais);
        TextView descricao = (TextView)
                view.findViewById(R.id.lv_locais);
        ImageView imagem = (ImageView)
                view.findViewById(R.id.lv_locais);

        //populando as Views
        nome.setText("Teste titulo sine");
        descricao.setText(sines.toString());
        imagem.setImageResource(R.drawable.key);

        return view;
    }




}
