package com.biblio.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Etagere {

    private static final int MAX_LIVRE = 10;
    private static int CPT = 1;

    int id;
    List<Livre> livres;
    int row;
    int column;

    public Etagere(int row, int column){
        this.id = CPT++;
        this.row = row;
        this.column = column;
        this.livres = new ArrayList<>();
    }

    public void addLivre(Livre livre){
        if(livres.size() >= MAX_LIVRE)
            throw new IllegalArgumentException("Il y a trop de livre taille max = " + MAX_LIVRE);
        livres.add(livre);
        livre.setEtagere(this);
    }

    public void deleteLivre(Livre livre){
        livres.remove(livre);
        livre.setEtagere(null);
    }
}
