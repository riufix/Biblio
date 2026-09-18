package com.biblio.demo.exeption;

public class AuteurNotFoundExeption extends RessourceNotFoundExeption {

    public AuteurNotFoundExeption(int id) {
        super("Auteur Introuvable id : " + id);
    }
}
