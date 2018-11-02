package com.aytekincomez.aktueluygulamasi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aytekincomez.aktueluygulamasi.Activity.DetayActivity;
import com.aytekincomez.aktueluygulamasi.Model.Aktuel;
import com.aytekincomez.aktueluygulamasi.R;

import java.util.ArrayList;

public class ListeAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Aktuel> aktueller;

    public ListeAdapter(Activity activity, ArrayList<Aktuel> aktueller){
        this.context = activity;
        this.aktueller = aktueller;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return aktueller.size();
    }

    @Override
    public Object getItem(int position) {
        return aktueller.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view = layoutInflater.inflate(R.layout.listview_satir_gorunumu, null);

        ImageView ivResim = (ImageView)view.findViewById(R.id.ivResim);
        TextView tvBaslik = (TextView)view.findViewById(R.id.tvBaslik);
        TextView tvTarih = (TextView)view.findViewById(R.id.tvTarih);

        tvBaslik.setText(aktueller.get(position).getIsim());
        tvTarih.setText(aktueller.get(position).getTarih());

        int resimId = view.getResources().getIdentifier(
                aktueller.get(position).getMarka_logo(),
                "drawable",
                context.getPackageName()
        );
        ivResim.setImageResource(resimId);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.getContext().startActivity(new Intent(view.getContext(), DetayActivity.class)
                    .putExtra("resim",aktueller.get(position).getAktuel_resim())
                );
            }
        });

        return view;
    }
}
