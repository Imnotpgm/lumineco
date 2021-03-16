package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomList_ampouleAdapter extends BaseAdapter {

    private List<Ampoule> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomList_ampouleAdapter(Context aContext, List<Ampoule> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View retour = convertView;
        if (retour == null){
            retour = layoutInflater.inflate(R.layout.list_item_layout, null);
        }
        TextView textView_Nom = (TextView) retour.findViewById(R.id.textView_Nom);
        TextView textView_Marque = (TextView) retour.findViewById(R.id.textView_Marque);
        TextView textView_Conso = (TextView) retour.findViewById(R.id.textView_Conso);
        TextView textView_Type = (TextView) retour.findViewById(R.id.textView_Type);
        textView_Nom.setText(this.listData.get(position).getAmpouleNom());
        textView_Marque.setText(this.listData.get(position).getAmpouleMarque());
        textView_Conso.setText(this.listData.get(position).getAmpouleConso());
        textView_Type.setText(this.listData.get(position).getAmpouleType());
        return retour;
    }
}
