package com.biblio.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor // exige par JPA
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String nom;
    String prenom;

    public Auteur(String nom, String prenom){
        this.nom = nom;
        this.prenom = prenom;
    }

    public String toString(){
        return "ID : " + id + " Nom : " + nom + " " + " Prénom : " + prenom ;
    }
}
