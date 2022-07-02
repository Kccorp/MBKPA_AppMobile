package com.lotte_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ScooterAdapter extends BaseAdapter {

    private List<Scooter> contenu;
    private Context context;

    public ScooterAdapter(List<Scooter> contenu, Context context) {
        this.contenu = contenu;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.contenu.size();
    }

    @Override
    public Object getItem(int i) {
        return this.contenu.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflater = LayoutInflater.from(this.context);
            view = inflater.inflate(R.layout.row_scooter, null);
        }

        TextView tv_id = view.findViewById(R.id.tv_id);
        TextView tv_pos = view.findViewById(R.id.tv_pos);


        Scooter current = (Scooter) getItem(i);

        tv_id.setText("Lottinette n°" + current.getIdScooter());
        tv_pos.setText(current.getLat() + ", " + current.getLng());
        return view;
    }

}
