package com.biblio.demo.exeption;

import lombok.Getter;

import java.time.LocalDateTime;

// corps JSON renvoye au client quand une exeption remonte
@Getter
public class ErreurReponse {

    private final LocalDateTime date = LocalDateTime.now();
    private final int status;
    private final String erreur;
    private final String message;
    private final String chemin;

    public ErreurReponse(int status, String erreur, String message, String chemin) {
        this.status = status;
        this.erreur = erreur;
        this.message = message;
        this.chemin = chemin;
    }
}
