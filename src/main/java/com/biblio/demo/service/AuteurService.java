package com.biblio.demo.service;

import com.biblio.demo.model.Auteur;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuteurService {
    private final List<Auteur> auteurs = new ArrayList<>();

    public Auteur ajouterAuteur(String nom, String prenom){
        var auteur = new Auteur(nom, prenom);
        auteurs.add(auteur);
        return auteur;
    }

    public List<Auteur> recupererTousLesAuteurs(){
        return List.copyOf(auteurs);
    }

    public Auteur recupererParId(int id){
        return auteurs.stream()
                .filter(auteur -> auteur.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Auteur Introuvable, id : " + id));
    }

    public Auteur modifieNom(int id, String nouveauNom){
        var auteur = recupererParId(id);
        auteur.setNom(nouveauNom);
        return auteur;
    }
}
