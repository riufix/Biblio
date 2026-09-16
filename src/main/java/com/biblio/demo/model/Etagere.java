package com.biblio.demo.model;

import java.util.Arrays;
import java.util.List;

public class Etagere {

    private static final int MAX_LIVRE = 10;

    List<Livre> livres;

    int row;
    int column;

    public Etagere(Livre... livres){
        this.setLivres(Arrays.asList(livres));
    }

    public Etagere(List<Livre> livres){

    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) throws IllegalArgumentException{
        // check la size
        var ajoutOk = livres.size() < MAX_LIVRE;
        if(ajoutOk){

//            for(var livre : livres){
//                if(livre.titre != null){
//                    livre.setEtagere(this);
//                }
//            }

            livres.stream()
                    .filter(livre -> livre.titre!= null)
                    .forEach(livre -> livre.setEtagere(this));



        }
        else{
            throw new IllegalArgumentException("Il y a trop de livre taille max = 10");
        }
    }

    public void addLivre(Livre livre){
        this.livres.add(livre);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void deleteLivre(Livre livre){this.livres.remove(livre);}

    /*public void prendFeu(){
        livres.stream()
                .filter(livre -> livre.etat == EtatLivre.LIBRE)
                .forEach(livre -> livre.setEtat(EtatLivre.DETRUIT));
    }*/
}
