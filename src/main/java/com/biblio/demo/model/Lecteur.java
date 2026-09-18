package com.biblio.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor // exige par JPA
public class Lecteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // l'id vient de la base
    int id;

    @Setter
    String prenom;

    // les livres empruntes : colonne lecteur_id dans la table livre
    @OneToMany(fetch = FetchType.EAGER)
    List<Livre> livres = new ArrayList<>();

    public Lecteur(String prenom) {
        this.prenom = prenom;
    }

    public Livre emprunt(Livre livre){
        livre.emprunter();
        this.livres.add(livre);
        return livre;
    }

    public Livre rendre(Livre livre){
        verifiePossede(livre);
        livre.rendre();
        this.livres.removeIf(l -> l.getId() == livre.getId());
        return livre;
    }

    public Livre perdre(Livre livre){
        verifiePossede(livre);
        livre.declarerPerdu();
        this.livres.removeIf(l -> l.getId() == livre.getId());
        return livre;
    }

    private void verifiePossede(Livre livre){
        var possede = livres.stream().anyMatch(l -> l.getId() == livre.getId());
        if(!possede)
            throw new IllegalArgumentException("Ce lecteur n'a pas emprunte ce livre");
    }
}
