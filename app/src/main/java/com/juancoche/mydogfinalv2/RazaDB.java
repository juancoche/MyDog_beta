package com.juancoche.mydogfinalv2;

import android.provider.BaseColumns;

public class RazaDB {
    public static abstract class RazaEntry implements BaseColumns {
        public static final String RAZA_TABLE_NAME = "breeds";
        public static final String RAZA_NOMBRE = "breedName";
        public static final String RAZA_PAIS = "country";
        public static final String RAZA_GRUPO = "grupo";
        public static final String RAZA_TAMANO = "avSize";
        public static final String RAZA_VIDA = "avLiveExp";
        public static final String RAZA_CARACTERISTICAS = "features";
        public static final String RAZA_SKILLS = "skills";
        public static final String RAZA_FOTO = "photo";

    }
}
