package com.biblio.demo.service;

import com.biblio.demo.exeption.EtagereNotFoundExeption;
import com.biblio.demo.model.Etagere;
import com.biblio.demo.model.Livre;
import com.biblio.demo.repository.EtagereRepository;
import com.biblio.demo.repository.LivreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EtagereService {

    private final EtagereRepository etagereRepository;
    private final LivreRepository livreRepository;

    @Transactional
    public Etagere ajouter(int row, int column){
        return etagereRepository.save(new Etagere(row, column));
    }

    public List<Etagere> recupereToutesLesEtageres(){
        return etagereRepository.findAll();
    }

    public Etagere recupereParId(int id){
        return etagereRepository.findById(id)
                .orElseThrow(() -> new EtagereNotFoundExeption(id));
    }

    public List<Livre> recupereLivres(int id){
        return List.copyOf(recupereParId(id).getLivres());
    }

    @Transactional
    public Etagere ajouterLivre(int id, Livre livre){
        var etagere = recupereParId(id);
        etagere.addLivre(livre);
        livreRepository.save(livre); // c'est Livre qui porte la colonne etagere_id
        return etagere;
    }

    @Transactional
    public Etagere retirerLivre(int id, Livre livre){
        var etagere = recupereParId(id);
        etagere.deleteLivre(livre);
        livreRepository.save(livre);
        return etagere;
    }

    @Transactional
    public Etagere deplacer(int id, int row, int column){
        var etagere = recupereParId(id);
        etagere.setRow_test(row);
        etagere.setColumn_test(column);
        return etagereRepository.save(etagere);
    }
}
