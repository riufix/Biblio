package com.biblio.demo.controller;

import com.biblio.demo.model.Auteur;
import com.biblio.demo.service.AuteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auteur")
@RequiredArgsConstructor
public class AuteurController {

    private final AuteurService auteurService;

    @PostMapping // POST /api/auteur?nom=Hugo&prenom=Victor
    public Auteur ajouterAuteur(@RequestParam String nom, @RequestParam String prenom) {
        return auteurService.ajouterAuteur(nom, prenom);
    }

    @GetMapping
    public List<Auteur> recupererAuteurs() {
        return auteurService.recupererTousLesAuteurs();
    }

    @GetMapping("/{id}")
    public Auteur recupereAuteurParId(@PathVariable int id) {
        return auteurService.recupererParId(id);
    }

    @PutMapping("/{id}") // PUT /api/auteur/1?nouveauNom=Dumas
    public Auteur modifierNom(@PathVariable int id, @RequestParam String nouveauNom) {
        return auteurService.modifieNom(id, nouveauNom);
    }
}
