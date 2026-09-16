package com.biblio.demo.controller;

import com.biblio.demo.model.Lecteur;
import com.biblio.demo.model.Livre;
import com.biblio.demo.service.LecteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecteur")
@RequiredArgsConstructor
public class LecteurController {

    private final LecteurService lecteurService;

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
        return lecteurService.emprunterLivre(idLecteur, idLivre);
    }

    @PostMapping("/{idLecteur}/rendre/{idLivre}")
    public Livre rendreLivre(@PathVariable int idLecteur, @PathVariable int idLivre) {
        return lecteurService.rendreLivre(idLecteur, idLivre);
    }
}