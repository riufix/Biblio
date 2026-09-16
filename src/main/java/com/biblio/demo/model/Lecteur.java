package com.biblio.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Lecteur {
    private static int CPT = 1;

    int id;
    String prenom;
    List<Livre> livres;

    public Lecteur( String prenom) {
        this.id = CPT++;
        this.prenom = prenom;
        this.livres = new ArrayList<>();
    }

    public Livre emprunt(Livre livre){
        livre.emprunter();
        // livre hashcode = 12
        this.livres.add(livre);
        return livre;
    }

    public Livre rendre(Livre livre){
        livre.rendre();
        this.livres.remove(livre);
        return livre;
    }

    public void perdre(Livre livre){
        if(livres.contains(livre)){
            livre.declarerPerdu();
        }
        else{
         throw new IllegalArgumentException("Vous ne pouvez me perdre");
        }
    }
}
