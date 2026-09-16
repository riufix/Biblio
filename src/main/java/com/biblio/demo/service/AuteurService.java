package com.biblio.demo.service;

import com.biblio.demo.model.Auteur;
import com.biblio.demo.model.Livre;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuteurService {
    private final List<Auteur> auteurs = new ArrayList<>();

    public final List<Auteur> recupererTousLesAuteurs(){ // Nom corrigé
        return List.copyOf(auteurs);
    }

    // MODIFIÉ : Accepte l'objet Auteur complet
    public Auteur ajouter(Auteur auteur){
        auteurs.add(auteur);
        return auteur;
    }

    public Auteur recupererParId(int id){
        return auteurs.stream()
                .filter(autor -> autor.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Auteur Introuvable, id : " + id));
    }

    public Auteur modifieNom(int id, String nouveauNom){
        var auteur = recupererParId(id);
        if(auteur != null)
            auteur.setNom(nouveauNom);
        return auteur;
    }
}