package com.biblio.demo.service;

import com.biblio.demo.model.Etagere;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EtagereService {

    private final List<Etagere> etageres = new ArrayList<>();

    public Etagere ajouter(Etagere etagere){
        etageres.add(etagere);
        return etagere;
    }

    public List<Etagere> recupereToutesLesEtageres(){
        return List.copyOf(etageres);
    }

    public Etagere recupereParId(int id){
        // Attention : assure-toi que ton modèle Etagere a bien un attribut "id" et un "getId()" !
        return etageres.stream()
                .filter(etagere -> etagere.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Etagere Introuvable, id : " + id));
    }
}