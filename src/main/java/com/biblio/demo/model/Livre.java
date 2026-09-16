package com.biblio.demo.model;

public class Livre implements Document {

    private static int CPT = 1;

    int id; // utilisation de static
    String titre;
    Auteur auteur;
    String type;
    String genre;
    EtatLivre etat = EtatLivre.LIBRE; // par defaut false
    String edition;
    Etagere etagere;

    public Livre(String titre,  Auteur auteur, String type, String genre, String edition){
        this.id = CPT++; // équivalent à this.id = CPT et CPT = CPT + 1
        this.titre = titre;
        this.auteur = auteur;
        this.type = type;
        this.genre = genre;
        this.edition = edition;
        this.etat = EtatLivre.LIBRE;
    }

    public void emprunter(){
        if(etat == EtatLivre.LIBRE)
            setEtat(EtatLivre.EMPRUNTE);
        else
            throw new IllegalArgumentException("Deja emprunter");
    }

    public void rendre(){
        setEtat(EtatLivre.LIBRE);
    }

    public void suprimer(){
        if(etat != EtatLivre.SUPRIME)
            setEtat(EtatLivre.SUPRIME);
        else
            throw new IllegalArgumentException("Deja suprimer");
    }

    @Override
    public void reserver() {

    }

    public void vol(){
        setEtat(EtatLivre.VOL);
    }

    public void declarerPerdu(){
        setEtat(EtatLivre.PERDU);
    }

    public EtatLivre getEtat() {
        return etat;
    }

    public void setEtat(EtatLivre etat){
        this.etat = etat;
    }

    public void setEtagere(Etagere etagere) {
        this.etagere = etagere;
    }
}
