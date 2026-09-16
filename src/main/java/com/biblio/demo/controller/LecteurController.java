package com.biblio.demo.controller;

import com.biblio.demo.model.Lecteur;
import com.biblio.demo.model.Livre;
import com.biblio.demo.service.LecteurService;
import com.biblio.demo.service.LivreService; // NOUVEAU
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecteur")
@RequiredArgsConstructor
public class LecteurController {

    private final LecteurService lecteurService;
    private final LivreService livreService; // Ajout pour chercher les livres

    @PostMapping
    public Lecteur ajouterLecteur(@RequestBody String prenom) {
        return lecteurService.ajouter(prenom);
    }

    @GetMapping
    public List<Lecteur> recupererLecteurs() {
        return lecteurService.recupererToutLesLecteurs();
    }

    @GetMapping("/{id}")
    public Lecteur recupereLecteurParId(@PathVariable int id) {
        return lecteurService.recupereParId(id);
    }

    @PostMapping("/{idLecteur}/emprunter/{idLivre}")
    public Livre emprunterLivre(@PathVariable int idLecteur, @PathVariable int idLivre) {
        // 1. On cherche le livre dans la bibliothèque
        Livre livre = livreService.recupereParId(idLivre);
        // 2. On le passe au lecteur
        return lecteurService.emprunter(idLecteur, livre);
    }

    @PostMapping("/{idLecteur}/rendre/{idLivre}")
    public Livre rendreLivre(@PathVariable int idLecteur, @PathVariable int idLivre) {
        Livre livre = livreService.recupereParId(idLivre);
        return lecteurService.rendre(idLecteur, livre);
    }
}