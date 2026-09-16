package com.biblio.demo.service;

import com.biblio.demo.model.Auteur;
import com.biblio.demo.model.Livre;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivreService {
    private final List<Livre> livres = new ArrayList<>();


    public Livre ajouter(String titre, Auteur auteur, String type, String genre, String edition){
        var livre = new Livre(titre, auteur, type, genre, edition);
        livres.add(livre);
        return livre;
    }

    public List<Livre> recupereToutLesLivres(){
        return List.copyOf(livres);
    }

    public Livre recupereParId(int id){
        return livres.stream()
                .filter(livre -> livre.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Livre Introuvable id : " + id));
    }

    public List<Livre> recupereParAuteur (int id){
        return livres.stream()
                .filter(livre -> livre.getAuteur().getId() == id)
                .toList();
    }

    public Livre emprunter(int id){
        var livre = recupereParId(id);
        livre.emprunter();
        return livre;
    }

    public Livre rendre(int id){
        var livre = recupereParId(id);
        livre.rendre();
        return livre;
    }

    public Livre reserver(int id){
        var livre = recupereParId(id);
        livre.reserver();
        return livre;
    }

    public Livre declarerPerdu(int id){
        var livre = recupereParId(id);
        livre.declarerPerdu();
        return livre;
    }

    public Livre vol(int id){
        var livre = recupereParId(id);
        livre.vol();
        return livre;
    }

    public Livre suprimer(int id){
        var livre = recupereParId(id);
        livre.suprimer();
        return livre;
    }

}