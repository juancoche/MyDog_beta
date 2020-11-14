package com.juancoche.mydogfinalv2;

public class Imagen {

    private String rutaImagen;
    private String id;

    public Imagen(String id, String rutaImagen){

        this.id = id;
        this.rutaImagen= rutaImagen;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
