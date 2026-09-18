package com.biblio.demo.model;

import com.biblio.demo.exeption.EtagerePleineExeption;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor // exige par JPA
public class Etagere {

    private static final int MAX_LIVRE = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    // cote inverse de Livre.etagere
    @OneToMany(mappedBy = "etagere", fetch = FetchType.EAGER)
    List<Livre> livres = new ArrayList<>();

    @Setter
    @Column(name = "ligne") // row est un mot reserve en SQL
    int row_test;

    @Setter
    @Column(name = "colonne") // column est un mot reserve en SQL
    int column_test;

    public Etagere(int row, int column){
        this.row_test = row;
        this.column_test = column;
    }

    public void addLivre(Livre livre){
        if(livres.size() >= MAX_LIVRE)
            throw new EtagerePleineExeption(MAX_LIVRE);
        livres.add(livre);
        livre.setEtagere(this);
    }

    public void deleteLivre(Livre livre){
        livres.removeIf(l -> l.getId() == livre.getId());
        livre.setEtagere(null);
    }
}
