package com.juancoche.mydogfinalv2;

public class Raza {

    private String nombre;
    private String pais;
    private String grupo;
    private String tamano;
    private String vida;
    private String caracteristicas;
    private String skills;
    private String foto;

    public Raza(String nombre, String pais, String grupo, String tamano, String vida, String caracteristicas, String skills, String foto) {
        this.nombre = nombre;
        this.pais = pais;
        this.grupo = grupo;
        this.tamano = tamano;
        this.vida = vida;
        this.caracteristicas = caracteristicas;
        this.skills = skills;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getVida() {
        return vida;
    }

    public void setVida(String vida) {
        this.vida = vida;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
