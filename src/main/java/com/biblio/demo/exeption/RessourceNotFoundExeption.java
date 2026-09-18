package com.biblio.demo.exeption;

// classe mere de toutes les exeptions "introuvable" -> HTTP 404
public class RessourceNotFoundExeption extends RuntimeException {

    public RessourceNotFoundExeption(String message) {
        super(message);
    }
}
