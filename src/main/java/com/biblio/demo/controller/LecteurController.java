package com.biblio.demo.controller;

import com.biblio.demo.model.Lecteur;
import com.biblio.demo.model.Livre;
import com.biblio.demo.service.LecteurService;
import com.biblio.demo.service.LivreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecteur")
@RequiredArgsConstructor
@Tag(name = "Gestion des Lecteurs", description = "Points d'accès pour gérer les membres inscrits et leurs actions d'emprunt")
public class LecteurController {

    private final LecteurService lecteurService;
    private final LivreService livreService;

    @PostMapping // POST /api/lecteur?prenom=Marie
    @Operation(summary = "Ajouter un lecteur", description = "Inscrit un nouveau lecteur dans la bibliothèque.")
    public Lecteur ajouterLecteur(
            @Parameter(description = "Le prénom du nouveau lecteur", example = "Romain")
            @RequestParam String prenom) {
        return lecteurService.ajouter(prenom);
    }

    @GetMapping
    @Operation(summary = "Lister tous les lecteurs", description = "Récupère la liste complète des lecteurs enregistrés.")
    public List<Lecteur> recupererLecteurs() {
        return lecteurService.recupereTousLesLecteurs();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trouver un lecteur par ID")
    public Lecteur recupereLecteurParId(
            @PathVariable int id) {
        return lecteurService.recupereParId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier le prénom d'un lecteur", description = "Met à jour le prénom d'un lecteur existant.")// PUT /api/lecteur/1?nouveauPrenom=Paul
    public Lecteur modifierPrenom(@PathVariable int id, @RequestParam String nouveauPrenom) {
        return lecteurService.modifierPrenom(id, nouveauPrenom);
    }

    @PostMapping("/{idLecteur}/emprunter/{idLivre}")
    @Operation(summary = "Emprunter un livre", description = "Associe un livre à un lecteur et passe le livre en état EMPRUNTE.")
    public Livre emprunterLivre(@PathVariable int idLecteur, @PathVariable int idLivre) {
        var livre = livreService.recupereParId(idLivre);
        return lecteurService.emprunter(idLecteur, livre);
    }

    @PostMapping("/{idLecteur}/rendre/{idLivre}")
    @Operation(summary = "Rendre un livre", description = "Retire le livre des emprunts du lecteur et le remet en état LIBRE.")
    public Livre rendreLivre(@PathVariable int idLecteur, @PathVariable int idLivre) {
        var livre = livreService.recupereParId(idLivre);
        return lecteurService.rendre(idLecteur, livre);
    }

    @PostMapping("/{idLecteur}/perdre/{idLivre}")
    @Operation(summary = "Déclarer un livre perdu par un lecteur", description = "Retire le livre des emprunts du lecteur et le passe en état PERDU.")
    public Livre perdreLivre(@PathVariable int idLecteur, @PathVariable int idLivre) {
        var livre = livreService.recupereParId(idLivre);
        return lecteurService.perdre(idLecteur, livre);
    }
}
