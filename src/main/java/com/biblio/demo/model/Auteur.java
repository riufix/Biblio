package com.biblio.demo.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
        this.id = ID_CPT++;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String toString(){
        return "ID : " + id + " Nom : " + nom + " " + " Prénom : " + prenom ;
    }
}
