package com.biblio.demo.exeption;

public class EtagereNotFoundExeption extends RessourceNotFoundExeption {

    public EtagereNotFoundExeption(int id) {
        super("Etagere Introuvable id : " + id);
    }
}
