package com.juancoche.mydogfinalv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mydog5.db";

    public DBHelper(Context context) {

        //Si quiero resetear la BDD, en versión puedo poner un 2 y se va a crear nueva
        super(context, DATABASE_NAME , null, 1);
    }
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table "+ MascotaDB.MascotaEntry.MASCOTA_TABLE_NAME  +"("
                        + MascotaDB.MascotaEntry.MASCOTA_NOMBRE + " text primary key,"
                        + MascotaDB.MascotaEntry.MASCOTA_FECHANAC +" text,"
                        + MascotaDB.MascotaEntry.MASCOTA_RAZA +" text,"
                        + MascotaDB.MascotaEntry.MASCOTA_CHIP+" text,"
                        + MascotaDB.MascotaEntry.MASCOTA_PESO +" text,"
                        + MascotaDB.MascotaEntry.MASCOTA_VACUNA +" text,"
                        + MascotaDB.MascotaEntry.MASCOTA_ID_IMAGEN +" text,"
                        +" FOREIGN KEY ("+MascotaDB.MascotaEntry.MASCOTA_ID_IMAGEN+") " +
                        "REFERENCES "+ImagenDB.ImagenEntry.IMAGEN_TABLE_NAME+"("+ImagenDB.ImagenEntry.IMAGEN_ID+"));"
        );
        db.execSQL(
                "create table "+ RazaDB.RazaEntry.RAZA_TABLE_NAME  +"("
                        + RazaDB.RazaEntry.RAZA_NOMBRE + " text primary key,"
                        + RazaDB.RazaEntry.RAZA_PAIS +" text,"
                        + RazaDB.RazaEntry.RAZA_GRUPO +" text,"
                        + RazaDB.RazaEntry.RAZA_TAMANO +" text,"
                        + RazaDB.RazaEntry.RAZA_VIDA +" text,"
                        + RazaDB.RazaEntry.RAZA_CARACTERISTICAS +" text,"
                        + RazaDB.RazaEntry.RAZA_SKILLS +" text,"
                        + RazaDB.RazaEntry.RAZA_FOTO +" text)"
        );
        db.execSQL("create table "+ImagenDB.ImagenEntry.IMAGEN_TABLE_NAME  +"("
                +ImagenDB.ImagenEntry.IMAGEN_ID + " text primary key, "
                +ImagenDB.ImagenEntry.IMAGEN_RUTA + " text);"
        );

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+ MascotaDB.MascotaEntry.MASCOTA_TABLE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS "+ RazaDB.RazaEntry.RAZA_TABLE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS "+ ImagenDB.ImagenEntry.IMAGEN_TABLE_NAME);
        onCreate(db);

    }
    public boolean insertMascota (String nombreMascota, String edad, String raza, String chip, String peso, String vac, String foto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MascotaDB.MascotaEntry.MASCOTA_NOMBRE, nombreMascota);
        contentValues.put(MascotaDB.MascotaEntry.MASCOTA_FECHANAC, edad);
        contentValues.put(MascotaDB.MascotaEntry.MASCOTA_RAZA, raza);
        contentValues.put(MascotaDB.MascotaEntry.MASCOTA_CHIP, chip);
        contentValues.put(MascotaDB.MascotaEntry.MASCOTA_PESO, peso);
        contentValues.put(MascotaDB.MascotaEntry.MASCOTA_VACUNA, vac);
        contentValues.put(MascotaDB.MascotaEntry.MASCOTA_ID_IMAGEN, foto);
        db.insert(MascotaDB.MascotaEntry.MASCOTA_TABLE_NAME, null, contentValues);
        return true;
    }
    public boolean insertRaza (String nombre, String pais, String grupo, String tamano, String vida, String caracteristicas, String skills, String foto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RazaDB.RazaEntry.RAZA_NOMBRE, nombre);
        contentValues.put(RazaDB.RazaEntry.RAZA_PAIS, pais);
        contentValues.put(RazaDB.RazaEntry.RAZA_GRUPO, grupo);
        contentValues.put(RazaDB.RazaEntry.RAZA_TAMANO, tamano);
        contentValues.put(RazaDB.RazaEntry.RAZA_VIDA, vida);
        contentValues.put(RazaDB.RazaEntry.RAZA_CARACTERISTICAS, caracteristicas);
        contentValues.put(RazaDB.RazaEntry.RAZA_SKILLS, skills);
        contentValues.put(RazaDB.RazaEntry.RAZA_FOTO, foto);
        db.insert(RazaDB.RazaEntry.RAZA_TABLE_NAME, null, contentValues);
        return true;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MascotaDB.MascotaEntry.MASCOTA_TABLE_NAME);
        return numRows;
    }

    public void insertImagen(Imagen imagen) throws SQLiteException {
        System.out.println("***** paso3");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ImagenDB.ImagenEntry.IMAGEN_ID,     imagen.getId());
        cv.put(ImagenDB.ImagenEntry.IMAGEN_RUTA,     imagen.getRutaImagen());
        db.insert(ImagenDB.ImagenEntry.IMAGEN_TABLE_NAME, null, cv );
    }

    public boolean updateMascota (String nombreMascota, String edad, String raza, String chip, String peso, String vac, String foto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MascotaDB.MascotaEntry.MASCOTA_NOMBRE, nombreMascota);
        contentValues.put(MascotaDB.MascotaEntry.MASCOTA_FECHANAC, edad);
        contentValues.put(MascotaDB.MascotaEntry.MASCOTA_RAZA, raza);
        contentValues.put(MascotaDB.MascotaEntry.MASCOTA_CHIP, chip);
        contentValues.put(MascotaDB.MascotaEntry.MASCOTA_PESO, peso);
        contentValues.put(MascotaDB.MascotaEntry.MASCOTA_VACUNA, vac);
        contentValues.put(MascotaDB.MascotaEntry.MASCOTA_ID_IMAGEN, foto);
        db.update(MascotaDB.MascotaEntry.MASCOTA_TABLE_NAME,
                contentValues, MascotaDB.MascotaEntry.MASCOTA_NOMBRE +" = ? ", new String[]{nombreMascota});
        return true;
    }

    public Integer deleteMascota (String nombreMascota) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(MascotaDB.MascotaEntry.MASCOTA_TABLE_NAME,
                MascotaDB.MascotaEntry.MASCOTA_NOMBRE +" = ? ", new String[]{nombreMascota});
    }

    public ArrayList<Mascota> getAllMascotas() {
        ArrayList<Mascota> array_list = new ArrayList<Mascota>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + MascotaDB.MascotaEntry.MASCOTA_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Mascota(
                    res.getString(res.getColumnIndex("petName")),
                    res.getString(res.getColumnIndex("age")),
                    res.getString(res.getColumnIndex("breed")),
                    res.getString(res.getColumnIndex("chip")),
                    res.getString(res.getColumnIndex("weight")),
                    res.getString(res.getColumnIndex("nextVac")),
                    res.getString(res.getColumnIndex("photo"))));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<Raza> getAllRazas() {
        ArrayList<Raza> array_list = new ArrayList<Raza>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + RazaDB.RazaEntry.RAZA_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Raza(
                    res.getString(res.getColumnIndex("breedName")),
                    res.getString(res.getColumnIndex("country")),
                    res.getString(res.getColumnIndex("grupo")),
                    res.getString(res.getColumnIndex("avSize")),
                    res.getString(res.getColumnIndex("avLiveExp")),
                    res.getString(res.getColumnIndex("features")),
                    res.getString(res.getColumnIndex("skills")),
                    res.getString(res.getColumnIndex("photo"))));
            res.moveToNext();
        }
        return array_list;
    }

    public String getImageSource(String nombreImagen) {
        SQLiteDatabase db = this.getReadableDatabase();
        String ruta = "";
        Cursor res =  db.rawQuery( "select * from IMAGENES where imagen_id = '" + nombreImagen + "'", null);
        res.moveToFirst();

        while(res.isAfterLast() == false) {
            ruta = res.getString(res.getColumnIndex("imagen_ruta"));
            res.moveToNext();
        }
        return ruta;
    }

    public Raza getRaza(String nombre) {
        //ArrayList<Raza> array_list = new ArrayList<Raza>();
        Raza raza = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from breeds where breedName = '" + nombre + "'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false) {
            raza = new Raza(
                    res.getString(res.getColumnIndex("breedName")),
                    res.getString(res.getColumnIndex("country")),
                    res.getString(res.getColumnIndex("grupo")),
                    res.getString(res.getColumnIndex("avSize")),
                    res.getString(res.getColumnIndex("avLiveExp")),
                    res.getString(res.getColumnIndex("features")),
                    res.getString(res.getColumnIndex("skills")),
                    res.getString(res.getColumnIndex("photo"))
            );
            res.moveToNext();
        }
        return raza;
    }

    public Mascota getMascota(String nombre) {
        Mascota m = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from pets where petName = '" + nombre + "'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            m = new Mascota(
                    res.getString(res.getColumnIndex("petName")),
                    res.getString(res.getColumnIndex("age")),
                    res.getString(res.getColumnIndex("breed")),
                    res.getString(res.getColumnIndex("chip")),
                    res.getString(res.getColumnIndex("weight")),
                    res.getString(res.getColumnIndex("nextVac")),
                    res.getString(res.getColumnIndex("photo"))
            );
            res.moveToNext();
        }
        return m;
    }



}
