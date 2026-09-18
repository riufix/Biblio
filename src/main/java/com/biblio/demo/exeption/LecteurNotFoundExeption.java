package com.biblio.demo.exeption;

public class LecteurNotFoundExeption extends RessourceNotFoundExeption {

    public LecteurNotFoundExeption(int id) {
        super("Lecteur Introuvable id : " + id);
    }
}
