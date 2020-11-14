package com.juancoche.mydogfinalv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.IOException;
import android.app.AlertDialog.Builder;

public class NewMascota extends AppCompatActivity {

    private DBHelper mydb;
    private Button anadir;
    private ImageView avatar;
    private TextInputEditText nombre;
    private TextInputEditText raza;
    private TextInputEditText fechaNac;
    private TextInputEditText fechaVac;
    private TextInputEditText peso;
    private TextInputEditText chip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mascota);
        avatar = (ImageView) findViewById(R.id.avatarPerro);
        anadir = (Button) findViewById(R.id.addMascota);
        nombre = (TextInputEditText) findViewById(R.id.nombreTxt);
        raza = (TextInputEditText) findViewById(R.id.razaTxt);
        fechaNac = (TextInputEditText) findViewById(R.id.fNacText);
        fechaVac = (TextInputEditText) findViewById(R.id.proxVacText);
        peso = (TextInputEditText) findViewById(R.id.pesoText);
        chip = (TextInputEditText) findViewById(R.id.chipText);
        mydb = new DBHelper(this);

        //nombreTextField.setError("Error message")  //Esto es para fijar el error si lo introducido en el texto no es correcto

    }

    public void addMascota (View v) {

        boolean b = chequearEntradas();
        if (b) {
            Imagen imagen = new Imagen(nombre_imagen, currentPhotoPath);
            mydb.insertMascota(
                    nombre.getText().toString(),
                    fechaNac.getText().toString(),
                    raza.getText().toString(),
                    chip.getText().toString(),
                    peso.getText().toString(),
                    fechaVac.getText().toString(),
                    nombre_imagen);
            mydb.insertImagen(imagen);
            Toast t=Toast.makeText(this,"Mascota añadida con éxito.", Toast.LENGTH_SHORT);
            t.show();
            NewMascota.this.finish();
        }
    }

    boolean chequearEntradas() {
        boolean b = true;
        if (nombre.getText().toString().equals("")) {
            nombre.setError("Introduce el nombre");
            b = false;
        }
        if (raza.getText().toString().equals("")) {
            raza.setError("Introduce la Raza");
            b = false;
        }
        if (fechaNac.getText().toString().equals("")) {
            fechaNac.setError("Introduce la fecha de nacimiento");
            b = false;
        }
        if (fechaVac.getText().toString().equals("")) {
            fechaVac.setError("Introduce la próxima vacuna");
            b = false;
        }
        if (peso.getText().toString().equals("")) {
            peso.setError("Introduce el peso");
            b = false;
        }
        if (chip.getText().toString().equals("")) {
            chip.setError("Introduce el número de chip");
            b = false;
        }
        return b;
    }

    public void selectDateNac(View v) {

        showDatePickerDialogNac();
    }
    public void selectDateVac(View v) {

        showDatePickerDialogVac();
    }

    public void setImage (View v) {
        /*Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(cameraIntent);*/
        dispatchTakePictureIntent();
    }

    private void showDatePickerDialogNac() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedYear= day + "/" + (month+1) + "/" + year;
                fechaNac.setText(selectedYear);
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
                fechaVac.setText(selectedYear);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    String currentPhotoPath;
    String nombre_imagen;

    private File createImageFile() throws IOException {
        // Create an image file name
        String imagen = "imagen";
        String nombreP = nombre.getText().toString();
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
            int targetW = avatar.getWidth();
            int targetH = avatar.getHeight();

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
            avatar.setImageBitmap(bitmap);
        }
    }
}
