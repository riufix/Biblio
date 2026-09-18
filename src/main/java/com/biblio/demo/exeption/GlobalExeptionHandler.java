package com.biblio.demo.exeption;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// centralise la traduction exeption -> reponse HTTP pour tous les controllers
@RestControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(RessourceNotFoundExeption.class)
    public ResponseEntity<ErreurReponse> notFound(RessourceNotFoundExeption e, HttpServletRequest requete) {
        return construire(HttpStatus.NOT_FOUND, e.getMessage(), requete);
    }

    @ExceptionHandler(OperationInvalideExeption.class)
    public ResponseEntity<ErreurReponse> operationInvalide(OperationInvalideExeption e, HttpServletRequest requete) {
        return construire(HttpStatus.CONFLICT, e.getMessage(), requete);
    }

    // parametre manquant ou id non numerique dans l'URL
    @ExceptionHandler({
            IllegalArgumentException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ErreurReponse> requeteInvalide(Exception e, HttpServletRequest requete) {
        return construire(HttpStatus.BAD_REQUEST, e.getMessage(), requete);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErreurReponse> erreurInattendue(Exception e, HttpServletRequest requete) {
        return construire(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur interne", requete);
    }

    private ResponseEntity<ErreurReponse> construire(HttpStatus status, String message, HttpServletRequest requete) {
        var corps = new ErreurReponse(status.value(), status.getReasonPhrase(), message, requete.getRequestURI());
        return ResponseEntity.status(status).body(corps);
    }
}
