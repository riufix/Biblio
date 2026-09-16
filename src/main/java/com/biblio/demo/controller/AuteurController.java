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

    @PostMapping
    public Auteur ajouterAuteur(@RequestBody Auteur auteur) {
        return auteurService.ajouter(auteur);
    }

    @GetMapping
    public List<Auteur> recupererAuteurs() {
        return auteurService.recupererTousLesAuteurs(); // Appel corrigé
    }

    @GetMapping("/{id}")
    public Auteur recupereAuteurParId(@PathVariable int id) {
        return auteurService.recupereParId(id);
    }
}