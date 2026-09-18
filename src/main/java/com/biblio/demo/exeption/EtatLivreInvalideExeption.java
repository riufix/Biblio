package com.biblio.demo.exeption;

import com.biblio.demo.model.EtatLivre;

public class EtatLivreInvalideExeption extends OperationInvalideExeption {

    public EtatLivreInvalideExeption(String action, EtatLivre etat) {
        super("Impossible de " + action + " le livre, etat actuel : " + etat);
    }
}
