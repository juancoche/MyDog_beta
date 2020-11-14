package com.juancoche.mydogfinalv2;

import android.provider.BaseColumns;

public class ImagenDB {

    public static abstract class ImagenEntry implements BaseColumns {
        public static final String IMAGEN_TABLE_NAME = "IMAGENES";
        public static final String IMAGEN_ID = "imagen_id"; //ID
        public static final String IMAGEN_RUTA= "imagen_ruta";


    }
}
