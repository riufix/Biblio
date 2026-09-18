package com.biblio.demo.service;

import com.biblio.demo.exeption.LivreNotFoundExeption;
import com.biblio.demo.model.Auteur;
import com.biblio.demo.model.Livre;
import com.biblio.demo.repository.LivreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivreService {

    private final LivreRepository livreRepository;

    @Transactional
    public Livre ajouterLivre(String titre, Auteur auteur, String type, String genre, String edition){
        return livreRepository.save(new Livre(titre, auteur, type, genre, edition));
    }

    public List<Livre> recupereToutLesLivres(){
        return livreRepository.findAll();
    }

    public Livre recupereParId(int id){
        return livreRepository.findById(id)
                .orElseThrow(() -> new LivreNotFoundExeption(id));
    }

    public List<Livre> recupereParAuteur(int auteurId){
        return livreRepository.findByAuteurId(auteurId);
    }

    // emprunter / rendre passent par LecteurService pour garder la liste du lecteur a jour

    @Transactional
    public Livre reserver(int id){
        var livre = recupereParId(id);
        livre.reserver();
        return livreRepository.save(livre);
    }

    @Transactional
    public Livre declarerPerdu(int id){
        var livre = recupereParId(id);
        livre.declarerPerdu();
        return livreRepository.save(livre);
    }

    @Transactional
    public Livre vol(int id){
        var livre = recupereParId(id);
        livre.vol();
        return livreRepository.save(livre);
    }

    @Transactional
    public Livre suprimer(int id){
        var livre = recupereParId(id);
        livre.suprimer();
        return livreRepository.save(livre);
    }
}
