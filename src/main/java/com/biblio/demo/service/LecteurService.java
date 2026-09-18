package com.biblio.demo.service;

import com.biblio.demo.model.Lecteur;
import com.biblio.demo.model.Livre;
import com.biblio.demo.repository.LecteurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LecteurService {

    private final LecteurRepository lecteurRepository; // remplace la liste en memoire

    @Transactional
    public Lecteur ajouter(String prenom){
        return lecteurRepository.save(new Lecteur(prenom));
    }

    public List<Lecteur> recupereTousLesLecteurs(){
        return lecteurRepository.findAll();
    }

    public Lecteur recupereParId(int id){
        return lecteurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lecteur Introuvable id : " + id));
    }

    @Transactional
    public Lecteur modifierPrenom(int id, String nouveauPrenom){
        var lecteur = recupereParId(id);
        lecteur.setPrenom(nouveauPrenom);
        return lecteurRepository.save(lecteur);
    }

    @Transactional
    public Livre emprunter(int id, Livre livre){
        var lecteur = recupereParId(id);
        var emprunte = lecteur.emprunt(livre);
        lecteurRepository.save(lecteur); // enregistre le lien lecteur_id et l'etat du livre
        return emprunte;
    }

    @Transactional
    public Livre rendre(int id, Livre livre){
        var lecteur = recupereParId(id);
        var rendu = lecteur.rendre(livre);
        lecteurRepository.save(lecteur);
        return rendu;
    }

    @Transactional
    public Livre perdre(int id, Livre livre){
        var lecteur = recupereParId(id);
        var perdu = lecteur.perdre(livre);
        lecteurRepository.save(lecteur);
        return perdu;
    }
}
