package com.biblio.demo.service;

import com.biblio.demo.model.Etagere;
import com.biblio.demo.model.Livre;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EtagereService {
    private final List<Etagere> etageres = new ArrayList<>();

    public Etagere ajouter(int row, int column){
        var etagere = new Etagere(row, column);
        etageres.add(etagere);
        return etagere;
    }

    public List<Etagere> recupereToutesLesEtageres(){
        return List.copyOf(etageres);
    }

    public Etagere recupereParId(int id){
        return etageres.stream()
                .filter(etagere -> etagere.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Etagere introuvable, id : " + id));
    }

    public List<Livre> recupereLivres(int id){
        return List.copyOf(recupereParId(id).getLivres());
    }

    public Etagere ajouterLivre(int id, Livre livre){
        var etagere = recupereParId(id);
        etagere.addLivre(livre);
        return etagere;
    }

    public Etagere retirerLivre(int id, Livre livre){
        var etagere = recupereParId(id);
        etagere.deleteLivre(livre);
        return etagere;
    }

    public Etagere deplacer(int id, int row, int column){
        var etagere = recupereParId(id);
        etagere.setRow(row);
        etagere.setColumn(column);
        return etagere;
    }
}
