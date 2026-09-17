package com.biblio.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Lecteur {
    private static int CPT = 1;

    int id;
    String prenom;
    List<Livre> livres;

    public Lecteur(String prenom) {
        this.id = CPT++;
        this.prenom = prenom;
        this.livres = new ArrayList<>();
    }

    /*public List<Livre> getLivres(){
        return List.copyOf(livres);
    }*/

    public Livre emprunt(Livre livre){
        livre.emprunter();
        this.livres.add(livre);
        return livre;
    }

    public Livre rendre(Livre livre){
        verifiePossede(livre);
        livre.rendre();
        this.livres.remove(livre);
        return livre;
    }

    public Livre perdre(Livre livre){
        verifiePossede(livre);
        livre.declarerPerdu();
        this.livres.remove(livre);
        return livre;
    }

    private void verifiePossede(Livre livre){
        if(!livres.contains(livre))
            throw new IllegalArgumentException("Ce lecteur n'a pas emprunte ce livre");
    }
}
