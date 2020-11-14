package com.juancoche.mydogfinalv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class MisMascotas extends AppCompatActivity {

    DBHelper mydb;
    ImageView avatarM;
    TextView nombreM;
    TextView fnacM;
    TextView razaM;
    TextView pesoM;
    TextView chipM;
    TextView fvacM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_mascotas);

        Bundle bundle = getIntent().getExtras();

        mydb = new DBHelper(this);

        avatarM = (ImageView) findViewById(R.id.avatarPerro2);
        nombreM = (TextView) findViewById(R.id.nombrePerro2);
        fnacM = (TextView) findViewById(R.id.fnacPerro2);
        razaM = (TextView) findViewById(R.id.razaPerro2);
        pesoM = (TextView) findViewById(R.id.pesoPerro2);
        chipM = (TextView) findViewById(R.id.chipPerro2);
        fvacM = (TextView) findViewById(R.id.fvacPerro2);

        avatarM.setImageBitmap(BitmapFactory.decodeFile(bundle.getString("foto")));
        nombreM.setText(bundle.getString("nombre"));
        fnacM.setText(bundle.getString("fnac"));
        fvacM.setText(bundle.getString("fvac"));
        razaM.setText(bundle.getString("raza"));
        pesoM.setText(bundle.getString("peso") + "Kg");
        chipM.setText(bundle.getString("chip"));

    }

    @Override
    protected void onResume() {
        super.onResume();
        mydb = new DBHelper(this);
        Mascota m = mydb.getMascota(nombreM.getText().toString());

        avatarM.setImageBitmap(BitmapFactory.decodeFile(mydb.getImageSource(m.getFoto())));
        fnacM.setText(m.getFechaNac());
        fvacM.setText(m.getVac());
        razaM.setText(m.getRaza());
        pesoM.setText(m.getPeso() + "Kg");
        chipM.setText(m.getChip());
    }

    public void modificarMascota(View v) {

        Mascota m = mydb.getMascota(nombreM.getText().toString());
        Intent k = new Intent(MisMascotas.this, SetMascota.class);
        k.putExtra("nombre", m.getNombreMascota());
        startActivity(k);

    }

    public void eliminarMascota(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MisMascotas.this);
        builder.setMessage("¿Deseas eliminar la mascota?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        aceptar();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        cancelar();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void aceptar(){
        mydb.deleteMascota(nombreM.getText().toString());
        Toast t=Toast.makeText(this,"Mascota eliminada con éxito.", Toast.LENGTH_SHORT);
        t.show();
        MisMascotas.this.finish();
    }

    public void cancelar(){
        Toast t=Toast.makeText(this,"Cancelado.", Toast.LENGTH_SHORT);
        t.show();
    }

    public void setReminder (View v) {
        agregarEvento(nombreM.getText().toString(), fvacM.getText().toString(), "Vacunar a");
    }

    public void agregarEvento(String nombreMascota, String fecha, String recordatorio) {

        String[] parts = fecha.split("/");

        int dia = Integer.parseInt(parts[0]);
        int mes = Integer.parseInt(parts[1]) - 1;
        int annio = Integer.parseInt(parts[2]);

        Calendar beginTime = Calendar.getInstance();
        beginTime.set(annio, mes, dia);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, recordatorio + " " + nombreMascota)
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
        startActivity(intent);
    }
}
