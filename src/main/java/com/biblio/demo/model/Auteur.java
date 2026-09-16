package com.biblio.demo.model;

public class Auteur {

    private static int ID_CPT = 1;

    int id; // = 0
    String nom;
    String prenom;

    private Auteur(int id, String nom, String prenom){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Auteur(String nom, String prenom){
        this.id = ID_CPT;
        this.nom = nom;
        this.prenom = prenom;
    }

    public void modifieNom(String nouveauNom){
        this.nom = nouveauNom;
    }

    @Override
    public String toString(){
        return "ID : " + id + " Nom : " + nom + " " + " Prénom : " + prenom ;
    }

    private Livre ecrire(String titre, String type, String genre, String edition){
        return new Livre(titre, this, type, genre, edition);
    }

}
