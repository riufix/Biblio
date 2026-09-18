package com.biblio.demo.controller;

import com.biblio.demo.model.Auteur;
import com.biblio.demo.service.AuteurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auteur")
@RequiredArgsConstructor
@Tag(name = "Gestion des Auteurs", description = "Points d'accès pour gérer le répertoire des auteurs")
public class AuteurController {

    private final AuteurService auteurService;

    @PostMapping // POST /api/auteur?nom=Hugo&prenom=Victor
    @Operation(summary = "Ajouter un nouveau auteur", description = "Crée un auteur en base de données à partir de l'objet fourni.")
    public Auteur ajouterAuteur(@RequestParam String nom, @RequestParam String prenom) {
        return auteurService.ajouterAuteur(nom, prenom);
    }

    @GetMapping
    @Operation(summary = "Récupérer tous les auteurs", description = "Renvoie la liste complète des auteurs de la bibliothèque.")
    public List<Auteur> recupererAuteurs() {
        return auteurService.recupererTousLesAuteurs();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trouver un auteur par ID", description = "Récupère les détails d'un auteur spécifique via son identifiant.")
    public Auteur recupereAuteurParId(@PathVariable int id) {
        return auteurService.recupererParId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier le nom d'un auteur", description = "Met à jour le nom de famille d'un auteur existant.")// PUT /api/auteur/1?nouveauNom=Dumas
    public Auteur modifierNom(@PathVariable int id, @RequestParam String nouveauNom) {
        return auteurService.modifieNom(id, nouveauNom);
    }
}
