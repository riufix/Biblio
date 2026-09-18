package com.biblio.demo.controller;

import com.biblio.demo.model.Etagere;
import com.biblio.demo.model.Livre;
import com.biblio.demo.service.EtagereService;
import com.biblio.demo.service.LivreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etagere")
@RequiredArgsConstructor
@Tag(name = "Gestion des Étagères", description = "Points d'accès pour l'organisation physique de la bibliothèque")
public class EtagereController {

    private final EtagereService etagereService;
    private final LivreService livreService;

    @PostMapping
    @Operation(summary = "Ajouter une étagère", description = "Crée une nouvelle étagère en spécifiant sa position (rangée et colonne).")// POST /api/etagere?row=1&column=2
    public Etagere ajouterEtagere(@RequestParam int row, @RequestParam int column) {
        return etagereService.ajouter(row, column);
    }

    @GetMapping
    @Operation(summary = "Lister toutes les étagères", description = "Récupère la liste complète des étagères de la bibliothèque.")
    public List<Etagere> recupereEtagere(){
        return etagereService.recupereToutesLesEtageres();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trouver une étagère par ID", description = "Récupère les détails d'une étagère spécifique.")
    public Etagere recupereEtagereParId(@PathVariable int id){
        return etagereService.recupereParId(id);
    }

    @GetMapping("/{id}/livres")
    @Operation(summary = "Lister les livres d'une étagère", description = "Récupère la liste de tous les livres actuellement rangés sur cette étagère.")
    public List<Livre> recupereLivres(@PathVariable int id){
        return etagereService.recupereLivres(id);
    }

    @PostMapping("/{id}/livre/{livreId}")
    @Operation(summary = "Ranger un livre sur l'étagère", description = "Ajoute un livre spécifique à l'étagère indiquée.")    public Etagere ajouterLivre(@PathVariable int id, @PathVariable int livreId){
        var livre = livreService.recupereParId(livreId);
        return etagereService.ajouterLivre(id, livre);
    }

    @DeleteMapping("/{id}/livre/{livreId}")
    @Operation(summary = "Retirer un livre de l'étagère", description = "Enlève un livre spécifique de son étagère.")    public Etagere retirerLivre(@PathVariable int id, @PathVariable int livreId){
        var livre = livreService.recupereParId(livreId);
        return etagereService.retirerLivre(id, livre);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Déplacer une étagère", description = "Modifie les coordonnées (rangée et colonne) d'une étagère existante.")// PUT /api/etagere/1?row=3&column=4
    public Etagere deplacer(@PathVariable int id, @RequestParam int row, @RequestParam int column){
        return etagereService.deplacer(id, row, column);
    }
}
