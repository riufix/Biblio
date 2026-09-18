package com.biblio.demo.service;

import com.biblio.demo.model.Auteur;
import com.biblio.demo.repository.AuteurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuteurService {

    private final AuteurRepository auteurRepository;

    @Transactional
    public Auteur ajouterAuteur(String nom, String prenom){
        return auteurRepository.save(new Auteur(nom, prenom));
    }

    public List<Auteur> recupererTousLesAuteurs(){
        return auteurRepository.findAll();
    }

    public Auteur recupererParId(int id){
        return auteurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Auteur Introuvable, id : " + id));
    }

    @Transactional
    public Auteur modifieNom(int id, String nouveauNom){
        var auteur = recupererParId(id);
        auteur.setNom(nouveauNom);
        return auteurRepository.save(auteur);
    }
}
