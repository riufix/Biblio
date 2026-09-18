package com.biblio.demo.exeption;

public class LivreNonEmprunteExeption extends OperationInvalideExeption {

    public LivreNonEmprunteExeption(int idLecteur, int idLivre) {
        super("Le lecteur " + idLecteur + " n'a pas emprunte le livre " + idLivre);
    }
}
