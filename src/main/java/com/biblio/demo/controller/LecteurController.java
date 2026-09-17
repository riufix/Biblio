package com.biblio.demo.controller;

import com.biblio.demo.model.Lecteur;
import com.biblio.demo.model.Livre;
import com.biblio.demo.service.LecteurService;
import com.biblio.demo.service.LivreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecteur")
@RequiredArgsConstructor
public class LecteurController {

    private final LecteurService lecteurService;
    private final LivreService livreService;

    @PostMapping // POST /api/lecteur?prenom=Marie
    public Lecteur ajouterLecteur(@RequestParam String prenom) {
        return lecteurService.ajouter(prenom);
    }

    @GetMapping
    public List<Lecteur> recupererLecteurs() {
        return lecteurService.recupereTousLesLecteurs();
    }

    @GetMapping("/{id}")
    public Lecteur recupereLecteurParId(@PathVariable int id) {
        return lecteurService.recupereParId(id);
    }

    @PutMapping("/{id}") // PUT /api/lecteur/1?nouveauPrenom=Paul
    public Lecteur modifierPrenom(@PathVariable int id, @RequestParam String nouveauPrenom) {
        return lecteurService.modifierPrenom(id, nouveauPrenom);
    }

    @PostMapping("/{idLecteur}/emprunter/{idLivre}")
    public Livre emprunterLivre(@PathVariable int idLecteur, @PathVariable int idLivre) {
        var livre = livreService.recupereParId(idLivre);
        return lecteurService.emprunter(idLecteur, livre);
    }

    @PostMapping("/{idLecteur}/rendre/{idLivre}")
    public Livre rendreLivre(@PathVariable int idLecteur, @PathVariable int idLivre) {
        var livre = livreService.recupereParId(idLivre);
        return lecteurService.rendre(idLecteur, livre);
    }

    @PostMapping("/{idLecteur}/perdre/{idLivre}")
    public Livre perdreLivre(@PathVariable int idLecteur, @PathVariable int idLivre) {
        var livre = livreService.recupereParId(idLivre);
        return lecteurService.perdre(idLecteur, livre);
    }
}
