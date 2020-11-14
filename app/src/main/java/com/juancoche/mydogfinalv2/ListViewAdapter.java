package com.juancoche.mydogfinalv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    // Declare Variables
    DBHelper mydb;
    Context context;
    List<Raza> razas;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, List<Raza> razas) {
        this.context = context;
        this.razas = razas;
    }
    @Override
    public int getCount() {
        return razas.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView razaNombre;
        ImageView razaAvatar;
        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list, parent, false);
        // Locate the TextViews in listview_item.xml
        razaNombre = (TextView) itemView.findViewById(R.id.list_row_title);
        razaAvatar = (ImageView) itemView.findViewById(R.id.list_row_image);
        // Capture position and set to the TextViews
        int foto = Integer.parseInt(razas.get(position).getFoto());
        razaNombre.setText(razas.get(position).getNombre());
        razaAvatar.setImageResource(foto);
        return itemView;
    }
}
