package com.example.myapplication;

public class Ampoule {

    private String nom;
    private String marque;
    private String type;
    private String conso;

    public Ampoule(String ampouleNom,String ampouleConso, String ampouleMarque, String ampouleType){
        this.nom = ampouleNom;
        this.marque = ampouleMarque;
        this.type = ampouleType;
        this.conso = ampouleConso;
    }



    public String getAmpouleNom() {
        return nom;
    }

    public String getAmpouleMarque() {
        return marque;
    }

    public String getAmpouleType() {
        return type;
    }

    public String getAmpouleConso() {
        return conso ;
    }


    public void setAmpouleNom(String ampouleNom) {
        this.nom = ampouleNom;
    }

    public void setAmpouleMarque(String ampouleMarque) {
        this.marque = ampouleMarque;
    }

    public void setAmpouleType(String ampouleType) {
        this.type = ampouleType;
    }

    public void setAmpouleConso(String ampouleConso) {
        this.conso = ampouleConso;
    }

    @Override
    public String toString() {
        return "Ampoule{" +
                "ampouleNom='" + nom + '\'' +
                ", ampouleMarque='" + marque + '\'' +
                ", ampouleType='" + type + '\'' +
                ", ampouleConso='" + conso + '\'' +
                '}';
    }
}
