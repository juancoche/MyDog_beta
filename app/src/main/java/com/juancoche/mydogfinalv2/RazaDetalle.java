package com.juancoche.mydogfinalv2;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RazaDetalle extends AppCompatActivity {

    DBHelper mydb;
    ImageView avatarR;
    TextView nombreR, grupoR, paisR, tamanoR, vidaR, skillsR;
    Raza raza;
    ImageButton ib, ib2, ib3;
    TextView lblNombre, lblGrupo, lblPais, lblTamano, lblVida, lblSkills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_mascotas);

        Bundle bundle = getIntent().getExtras();

        mydb = new DBHelper(this);

        raza = mydb.getRaza(bundle.getString("raza"));

        avatarR = (ImageView) findViewById(R.id.avatarPerro2);
        nombreR = (TextView) findViewById(R.id.nombrePerro2);
        paisR = (TextView) findViewById(R.id.razaPerro2);
        grupoR = (TextView) findViewById(R.id.fnacPerro2);
        tamanoR = (TextView) findViewById(R.id.pesoPerro2);
        vidaR = (TextView) findViewById(R.id.chipPerro2);
        skillsR = (TextView) findViewById(R.id.fvacPerro2);

        ib = (ImageButton) findViewById(R.id.addReminder);
        ib2 = (ImageButton) findViewById(R.id.deleteMascota);
        ib3 = (ImageButton) findViewById(R.id.setMascota);

        lblNombre = (TextView) findViewById(R.id.textView2);
        lblPais = (TextView) findViewById(R.id.textView3);
        lblGrupo = (TextView) findViewById(R.id.textView4);
        lblTamano = (TextView) findViewById(R.id.textView7);
        lblVida = (TextView) findViewById(R.id.textView6);
        lblSkills = (TextView) findViewById(R.id.textView5);

        int foto = Integer.parseInt(raza.getFoto());

        avatarR.setImageResource(foto);
        nombreR.setText(raza.getNombre());
        nombreR.setTextSize(28);
        lblNombre.setText("Raza:");
        paisR.setText(raza.getPais());
        lblPais.setText("Pais:");
        grupoR.setText(raza.getGrupo());
        lblGrupo.setText("Grupo:");
        tamanoR.setText(raza.getTamano());
        lblTamano.setText("Tamaño:");
        vidaR.setText(raza.getVida());
        lblVida.setText("Esp. Vida:");
        skillsR.setTextSize(15);
        skillsR.setText(raza.getSkills());
        lblSkills.setText("Skills:");

        ib.setVisibility(View.INVISIBLE);
        ib2.setVisibility(View.INVISIBLE);
        ib3.setVisibility(View.INVISIBLE);
        ib.invalidate();
        ib2.invalidate();
        ib3.invalidate();

    }
}
