package com.juancoche.mydogfinalv2;

public class Mascota {

    private String nombreMascota;
    private String fechaNac;
    private String raza;
    private String chip;
    private String peso;
    private String foto;
    private String vac;

    public Mascota(String nombreMascota, String fechaNac, String raza, String chip, String peso, String vac, String foto) {
        this.nombreMascota = nombreMascota;
        this.fechaNac = fechaNac;
        this.raza = raza;
        this.chip = chip;
        this.peso = peso;
        this.foto = foto;
        this.vac = vac;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getVac() {
        return vac;
    }

    public void setVac(String vac) {
        this.vac = vac;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "nombreMascota='" + nombreMascota + '\'' +
                ", fechaNac='" + fechaNac + '\'' +
                ", raza='" + raza + '\'' +
                ", chip='" + chip + '\'' +
                ", peso=" + peso +
                ", foto='" + foto + '\'' +
                ", vac='" + vac + '\'' +
                '}';
    }
}
