package com.juancoche.mydogfinalv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllRazas extends AppCompatActivity {

    DBHelper mydb;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_razas);

        final ListView lista = (ListView) findViewById(R.id.listaRazas);
        mydb = new DBHelper(this);

        if (mydb.getAllRazas().size() == 0) {
            mydb.insertRaza("Pastor Alemán", "Alemania", "1", "Grande", "9–13 años", " ", "Equilibrado/Seguro/Cariñoso", String.valueOf(R.drawable.pastoraleman));
            mydb.insertRaza("Labrador Retriever", "Reino Unido", "8", "Grande", "15 años", " ", "Equilibrado/Ágil/Adaptable/", String.valueOf(R.drawable.labrador));
            mydb.insertRaza("Galgo", "España", "10", "Grande", "12–14 años", " ", "Cariñoso/Alegre/Obediente", String.valueOf(R.drawable.galgo));
            mydb.insertRaza("Yorkshire Terrier", "Reino Unido", "3", "Muy pequeño", "15–16 años", " ", "Alerta/Inteligente/Alegre", String.valueOf(R.drawable.yorkshire));
            mydb.insertRaza("Bulldog Inglés", "Reino Unido", "2", "Mediano", "8–12 años", " ", "Cariñoso/Obstinado/Alegre", String.valueOf(R.drawable.bulldog));
            mydb.insertRaza("Caniche", "Francia", "9", "Mediano", "12–15 años", " ", "Afable/Leal/Alerta", String.valueOf(R.drawable.caniche));
            mydb.insertRaza("San Bernardo", "Suiza", "2", "Muy grande", "8–10 años", " ", "Afable/Tranquilo/Alegre", String.valueOf(R.drawable.sanbernardo));
            mydb.insertRaza("Bóxer", "Alemania", "2", "Grande", "9–10 años", " ", "Alerta/Seguro/Entusiasta", String.valueOf(R.drawable.boxer));
            mydb.insertRaza("Dálmata", "Croacia", "6", "Grande", "10–13 años", " ", "Equilibrado/Afable/Seguro", String.valueOf(R.drawable.dalmata));
            mydb.insertRaza("Golden retriever", "Reino Unido", "8", "Grande", "10–13 años", " ", "Inteligente/Equilibrado/Alegre", String.valueOf(R.drawable.golden));
        }

        final ArrayList<Raza> razas = mydb.getAllRazas();
        adapter = new ListViewAdapter(this, razas);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Intent k = new Intent(AllRazas.this, RazaDetalle.class);
                k.putExtra("raza", razas.get(i).getNombre());
                startActivity(k);

            }
        });

        /*lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "presiono LARGO " + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/
    }
}
