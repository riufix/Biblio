package com.biblio.demo.service;

import com.biblio.demo.model.Lecteur;
import com.biblio.demo.model.Livre;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LecteurService {
    private final List<Lecteur> lecteurs = new ArrayList<>();

    public Lecteur ajouter(String prenom){
        var lecteur = new Lecteur(prenom);
        lecteurs.add(lecteur);
        return lecteur;
    }

    public List<Lecteur> recupererToutLesLecteurs(){ // Nom corrigé
        return List.copyOf(lecteurs);
    }

    public Lecteur recupereParId(int id){
        return lecteurs.stream()
                .filter(lecteur -> lecteur.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Lecteur Introuvable id : " + id));
    }

    public Livre emprunter(int id, Livre livre){
        var lecteur = recupereParId(id);
        return lecteur.emprunt(livre);
    }

    public Livre rendre(int id, Livre livre){
        var lecteur = recupereParId(id);
        return lecteur.rendre(livre);
    }
}