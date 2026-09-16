package com.biblio.demo.service;

import com.biblio.demo.model.Livre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService {

    public Livre ajouter(String titre){
        var livre = new Livre(titre);

        // appel a la base de donnee
        // return livre ajoute

        return livre;
    }

    public List<Livre> recupereToutLesLivres(){
        var livre1 = new Livre("Les miserables");
        var livre2 = new Livre("Le Seigneur des anneaux");

        return List.of(livre1, livre2);

    }

    public Livre recupereParId(int id){
        var livre1 = new Livre("Les miserables");
        var livre2 = new Livre("Le Seigneur des anneaux");

        var listLivre = List.of(livre1, livre2);

        return listLivre.stream()
                .filter(livre -> livre.getId() == id).findFirst().orElseGet(null);
    }
}