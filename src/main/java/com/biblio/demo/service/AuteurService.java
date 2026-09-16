package com.biblio.demo.service;

import com.biblio.demo.model.Auteur;
import com.biblio.demo.model.Livre;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuteurService {
    private final List<Auteur> auteurs = new ArrayList<>();

    public final List<Auteur> recupererTousLesAuteur(){
        return List.copyOf(auteurs);
    }

    public Auteur ajouter(String nom, String prenom){
        var auteur = new Auteur(nom, prenom);
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

    private Livre ecrire(int id, String titre, String type, String genre, String edition){
        var auteur = recupererParId(id);
        return new Livre(titre, auteur, type, genre, edition);
    }
}
