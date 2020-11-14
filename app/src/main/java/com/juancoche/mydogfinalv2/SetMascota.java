package com.juancoche.mydogfinalv2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.IOException;

public class SetMascota extends AppCompatActivity {

    ImageView avatarM;
    TextInputEditText nombreM;
    TextInputEditText fnacM;
    TextInputEditText razaM;
    TextInputEditText pesoM;
    TextInputEditText chipM;
    TextInputEditText fvacM;
    DBHelper mydb;
    Mascota mascota;
    Button addM;
    boolean newImg = false;
    String currentPhotoPath;
    String nombre_imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mascota);

        avatarM = (ImageView) findViewById(R.id.avatarPerro);
        nombreM = (TextInputEditText) findViewById(R.id.nombreTxt);
        fnacM = (TextInputEditText) findViewById(R.id.fNacText);
        razaM = (TextInputEditText) findViewById(R.id.razaTxt);
        pesoM = (TextInputEditText) findViewById(R.id.pesoText);
        chipM = (TextInputEditText) findViewById(R.id.chipText);
        fvacM = (TextInputEditText) findViewById(R.id.proxVacText);
        addM = (Button) findViewById(R.id.addMascota);

        Bundle bundle = getIntent().getExtras();
        mydb = new DBHelper(this);
        mascota = mydb.getMascota(bundle.getString("nombre"));

        avatarM.setImageBitmap(BitmapFactory.decodeFile(mydb.getImageSource(mascota.getFoto())));
        nombreM.setText(mascota.getNombreMascota());
        nombreM.setEnabled(false);
        nombreM.setFocusable(false);
        fnacM.setText(mascota.getFechaNac());
        razaM.setText(mascota.getRaza());
        pesoM.setText(mascota.getPeso());
        chipM.setText(mascota.getChip());
        fvacM.setText(mascota.getVac());

        nombre_imagen = mascota.getFoto();

        addM.setText("MODIFICAR MASCOTA");

        addM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmacion(v);
            }
        });
    }

    public void confirmacion(View view) {

        boolean b = chequearEntradas();
        if (b) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SetMascota.this);
            builder.setMessage("¿Deseas modificar la mascota?")
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
    }
    public void aceptar(){

        Imagen imagen = new Imagen(nombre_imagen, currentPhotoPath);
        if (
            mydb.updateMascota(
                   nombreM.getText().toString(),
                   fnacM.getText().toString(),
                    razaM.getText().toString(),
                    chipM.getText().toString(),
                    pesoM.getText().toString(),
                    fvacM.getText().toString(),
                    nombre_imagen)
        ) {
            if (newImg) {
                mydb.insertImagen(imagen);
            }
            Toast t=Toast.makeText(this,"Mascota modificada con éxito.", Toast.LENGTH_SHORT);
            t.show();
            SetMascota.this.finish();
        }
    }

    public void cancelar(){
        Toast t=Toast.makeText(this,"Cancelado.", Toast.LENGTH_SHORT);
        t.show();
    }

    boolean chequearEntradas() {
        boolean b = true;
        if (razaM.getText().toString().equals("")) {
            razaM.setError("Introduce la Raza");
            b = false;
        }
        if (fnacM.getText().toString().equals("")) {
            fnacM.setError("Introduce la fecha de nacimiento");
            b = false;
        }
        if (fvacM.getText().toString().equals("")) {
            fvacM.setError("Introduce la próxima vacuna");
            b = false;
        }
        if (pesoM.getText().toString().equals("")) {
            pesoM.setError("Introduce el peso");
            b = false;
        }
        if (chipM.getText().toString().equals("")) {
            chipM.setError("Introduce el número de chip");
            b = false;
        }
        return b;
    }

    public void setImage (View v) {
        dispatchTakePictureIntent();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String imagen = "imagen";
        String nombreP = nombreM.getText().toString();
        String imageFileName = nombreP + "_" + imagen;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        nombre_imagen = image.getName();
        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        newImg = true;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                System.out.println("-----------------------------"+ex);
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            int targetW = avatarM.getWidth();
            int targetH = avatarM.getHeight();

            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;

            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
            avatarM.setImageBitmap(bitmap);
        }
    }

    public void selectDateNac(View v) {

        showDatePickerDialogNac();
    }
    public void selectDateVac(View v) {

        showDatePickerDialogVac();
    }

    private void showDatePickerDialogNac() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedYear= day + "/" + (month+1) + "/" + year;
                fnacM.setText(selectedYear);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void showDatePickerDialogVac() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedYear= day + "/" + (month+1) + "/" + year;
                fvacM.setText(selectedYear);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
