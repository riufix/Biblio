package com.biblio.demo.model;

import com.biblio.demo.exeption.EtatLivreInvalideExeption;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor // exige par JPA
public class Livre implements Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String titre;

    @ManyToOne // colonne auteur_id
    Auteur auteur;

    String type;
    String genre;

    @Enumerated(EnumType.STRING) // stocke "LIBRE" et non 0
    EtatLivre etat;

    String edition;

    @ManyToOne
    @JoinColumn(name = "lecteur_id", referencedColumnName = "id")
    Lecteur lecteur;

    @ManyToOne
    @Setter
    @JsonIgnore // evite la boucle JSON etagere -> livres -> etagere
    Etagere etagere;

    public void setLecteur(Lecteur lecteur) {
        this.lecteur = lecteur;
    }

    public Livre(String titre, Auteur auteur, String type, String genre, String edition){
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
            throw new EtatLivreInvalideExeption("emprunter", etat);
        etat = EtatLivre.EMPRUNTE;
    }

    @Override
    public void rendre(){
        if(etat != EtatLivre.EMPRUNTE)
            throw new EtatLivreInvalideExeption("rendre", etat);
        etat = EtatLivre.LIBRE;
    }

    @Override
    public void reserver(){
        if(etat != EtatLivre.LIBRE)
            throw new EtatLivreInvalideExeption("reserver", etat);
        etat = EtatLivre.RESERVE;
    }

    @Override
    public void suprimer(){
        if(etat == EtatLivre.SUPRIME)
            throw new EtatLivreInvalideExeption("suprimer", etat);
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
