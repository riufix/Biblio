package com.biblio.demo.exeption;

public class EtagerePleineExeption extends OperationInvalideExeption {

    public EtagerePleineExeption(int max) {
        super("Il y a trop de livre, taille max = " + max);
    }
}
