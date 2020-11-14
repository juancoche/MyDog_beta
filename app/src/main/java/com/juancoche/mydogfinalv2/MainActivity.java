package com.juancoche.mydogfinalv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private DBHelper mydb;
    private Button addM;
    private ListViewAdapter2 adapter;
    private ArrayList<Mascota> mascotas;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.listaMascotasMain);
        addM = (Button) findViewById(R.id.addMascota);
        mydb = new DBHelper(this);

        cargaMascotas();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargaMascotas();

    }

    public void cargaMascotas() {
        mascotas  = mydb.getAllMascotas();

        if (mascotas.size() < 1) {
            addM.setVisibility(View.VISIBLE);
            final Intent j = new Intent(this, NewMascota.class);
            addM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(j);
                }
            });
        } else {
            adapter = new ListViewAdapter2(this, mascotas);
            lista.setAdapter(adapter);
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                    //Toast.makeText(getApplicationContext(), "presiono " + i, Toast.LENGTH_SHORT).show();
                    Intent k = new Intent(MainActivity.this, MisMascotas.class);
                    k.putExtra("nombre", mascotas.get(i).getNombreMascota());
                    k.putExtra("raza", mascotas.get(i).getRaza());
                    k.putExtra("fnac", mascotas.get(i).getFechaNac());
                    k.putExtra("fvac", mascotas.get(i).getVac());
                    k.putExtra("peso", mascotas.get(i).getPeso());
                    k.putExtra("chip", mascotas.get(i).getChip());
                    k.putExtra("foto", mydb.getImageSource(mascotas.get(i).getFoto()));
                    startActivity(k);
                }
            });
        }
    }

    public void nearVet(View v) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=veterinario");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void nearShop(View v) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=tienda de mascotas");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void goRazas(View v) {
        Intent j = new Intent(this, AllRazas.class);
        startActivity(j);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true; /** true -> el menú ya está visible */
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.razasMenu:
                i = new Intent(this, AllRazas.class);
                startActivity(i);
                break;
            case R.id.nuevaMascota:
                i = new Intent(this, NewMascota.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setReminder (View v) {
        agregarEvento("Desparasitar a tu mascota");
    }

    public void agregarEvento(String recordatorio) {

        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DATE);
        int mes = c.get(Calendar.MONTH);
        int annio = c.get(Calendar.YEAR);

        Calendar beginTime = Calendar.getInstance();
        beginTime.set(annio, mes, dia);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, recordatorio)
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
        startActivity(intent);
    }
}
