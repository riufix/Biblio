package com.biblio.demo.exeption;

// regle metier non respectee (mauvais etat, etagere pleine, ...) -> HTTP 409
public class OperationInvalideExeption extends RuntimeException {

    public OperationInvalideExeption(String message) {
        super(message);
    }
}
