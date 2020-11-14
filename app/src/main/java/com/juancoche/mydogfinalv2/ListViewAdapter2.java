package com.juancoche.mydogfinalv2;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter2 extends BaseAdapter {
    // Declare Variables
    DBHelper mydb;
    Context context;
    List<Mascota> mascotas;
    LayoutInflater inflater;

    public ListViewAdapter2(Context context, List<Mascota> mascotas) {
        this.context = context;
        this.mascotas = mascotas;
    }
    @Override
    public int getCount() {
        return mascotas.size();
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
        TextView mascotaNombre;
        ImageView mascotaAvatar;
        TextView mascotaFnac;
        TextView mascotaRaza;
        mydb = new DBHelper(context);
        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list_main, parent, false);
        // Locate the TextViews in listview_item.xml
        mascotaNombre = (TextView) itemView.findViewById(R.id.nombrePerro);
        mascotaAvatar = (ImageView) itemView.findViewById(R.id.fotoPerro);
        mascotaFnac = (TextView) itemView.findViewById(R.id.fnacPerro);
        mascotaRaza = (TextView) itemView.findViewById(R.id.razaPerro);
        // Capture position and set to the TextViews
        mascotaNombre.setText(mascotas.get(position).getNombreMascota());
        mascotaFnac.setText(mascotas.get(position).getFechaNac());
        mascotaRaza.setText(mascotas.get(position).getRaza());
        mascotaAvatar.setImageBitmap(BitmapFactory.decodeFile(mydb.getImageSource(mascotas.get(position).getFoto())));
        return itemView;
    }
}
