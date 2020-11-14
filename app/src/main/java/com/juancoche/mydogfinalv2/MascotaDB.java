package com.juancoche.mydogfinalv2;

import android.provider.BaseColumns;

public class MascotaDB {
    public static abstract class MascotaEntry implements BaseColumns {
        public static final String MASCOTA_TABLE_NAME = "pets";
        public static final String MASCOTA_NOMBRE = "petName";
        public static final String MASCOTA_FECHANAC = "age";
        public static final String MASCOTA_RAZA = "breed";
        public static final String MASCOTA_CHIP = "chip";
        public static final String MASCOTA_PESO = "weight";
        public static final String MASCOTA_VACUNA = "nextVac";
        public static final String MASCOTA_ID_IMAGEN = "photo";

    }
}
