package com.biblio.demo.controller;

import com.biblio.demo.model.Livre;
import com.biblio.demo.service.AuteurService;
import com.biblio.demo.service.LivreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livre")
@RequiredArgsConstructor
@Tag(name = "Gestion des Livres", description = "Points d'accès pour créer, rechercher et gérer le statut des livres")
public class LivreController {

    private final LivreService livreService;
    private final AuteurService auteurService;

    @PostMapping // POST /api/livre?titre=...&auteurId=1&type=...&genre=...&edition=...
    @Operation(summary = "Ajouter un nouveau livre", description = "Crée un livre en base de données à partir de l'objet fourni.")
    public Livre ajouterLivre(@RequestParam String titre, @RequestParam int auteurId,
                              @RequestParam String type, @RequestParam String genre,
                              @RequestParam String edition){
        var auteur = auteurService.recupererParId(auteurId);
        return livreService.ajouterLivre(titre, auteur, type, genre, edition);
    }

    @GetMapping // recupere tout les livres
    @Operation(summary = "Récupérer tous les livres", description = "Renvoie la liste complète des livres de la bibliothèque.")
    public List<Livre> recupereLivre(){
        return livreService.recupereToutLesLivres();
    }

    @GetMapping("/{id}") // recupere le livre id = id
    @Operation(summary = "Chercher un livre par son ID")
    public Livre recupereLivreParId(@PathVariable int id){
        return livreService.recupereParId(id);
    }

    @GetMapping("/auteur/{auteurId}")
    @Operation(summary = "Déclarer un vol", description = "Passe le statut du livre en VOL.")
    public List<Livre> recupereLivresParAuteur(@PathVariable int auteurId){
        return livreService.recupereParAuteur(auteurId);
    }

    @PutMapping("/{id}/reserver")
    @Operation(summary = "Réserver un livre", description = "Change l'état du livre pour indiquer qu'il est réservé.")
    public Livre reserver(@PathVariable int id){
        return livreService.reserver(id);
    }

    @PutMapping("/{id}/perdu")
    @Operation(summary = "Déclarer un livre perdu", description = "Met à jour le statut du livre en PERDU.")
    public Livre declarerPerdu(@PathVariable int id){
        return livreService.declarerPerdu(id);
    }

    @PutMapping("/{id}/vol")
    @Operation(summary = "Déclarer un vol", description = "Met à jour le statut du livre en VOL.")
    public Livre vol(@PathVariable int id){
        return livreService.vol(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un livre", description = "Marque le livre avec l'état SUPRIME.")
    public Livre suprimer(@PathVariable int id){
        return livreService.suprimer(id);
    }
}
