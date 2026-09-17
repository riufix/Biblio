package com.biblio.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Livre implements Document {

    private static int CPT = 1;

    int id; // utilisation de static
    String titre;
    Auteur auteur;
    String type;
    String genre;
    EtatLivre etat;
    String edition;

    @JsonIgnore // evite la boucle JSON etagere -> livres -> etagere
    Etagere etagere;

    public Livre(String titre, Auteur auteur, String type, String genre, String edition){
        this.id = CPT++; // équivalent à this.id = CPT et CPT = CPT + 1
        this.titre = titre;
        this.auteur = auteur;
        this.type = type;
        this.genre = genre;
        this.edition = edition;
        this.etat = EtatLivre.LIBRE;
    }

    @Override
    public void emprunter(){
        if(etat != EtatLivre.LIBRE && etat != EtatLivre.RESERVE)
            throw new IllegalArgumentException("Livre non disponible : " + etat);
        etat = EtatLivre.EMPRUNTE;
    }

    @Override
    public void rendre(){
        if(etat != EtatLivre.EMPRUNTE)
            throw new IllegalArgumentException("Livre non emprunte");
        etat = EtatLivre.LIBRE;
    }

    @Override
    public void reserver(){
        if(etat != EtatLivre.LIBRE)
            throw new IllegalArgumentException("Livre non disponible : " + etat);
        etat = EtatLivre.RESERVE;
    }

    @Override
    public void suprimer(){
        if(etat == EtatLivre.SUPRIME)
            throw new IllegalArgumentException("Deja suprimer");
        etat = EtatLivre.SUPRIME;
    }

    @Override
    public void vol(){
        etat = EtatLivre.VOL;
    }

    @Override
    public void declarerPerdu(){
        etat = EtatLivre.PERDU;
    }
}
