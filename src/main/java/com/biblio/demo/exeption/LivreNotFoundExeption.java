package com.biblio.demo.exeption;

public class LivreNotFoundExeption extends RessourceNotFoundExeption {

    public LivreNotFoundExeption(int id) {
        super("Livre Introuvable id : " + id);
    }
}
