package com.biblio.demo.controller;

import com.biblio.demo.model.Livre;
import com.biblio.demo.service.AuteurService;
import com.biblio.demo.service.LivreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livre")
@RequiredArgsConstructor
public class LivreController {

    private final LivreService livreService;
    private final AuteurService auteurService;

    @PostMapping // POST /api/livre?titre=...&auteurId=1&type=...&genre=...&edition=...
    public Livre ajouterLivre(@RequestParam String titre, @RequestParam int auteurId,
                              @RequestParam String type, @RequestParam String genre,
                              @RequestParam String edition){
        var auteur = auteurService.recupererParId(auteurId);
        return livreService.ajouterLivre(titre, auteur, type, genre, edition);
    }

    @GetMapping // recupere tout les livres
    public List<Livre> recupereLivre(){
        return livreService.recupereToutLesLivres();
    }

    @GetMapping("/{id}") // recupere le livre id = id
    public Livre recupereLivreParId(@PathVariable int id){
        return livreService.recupereParId(id);
    }

    @GetMapping("/auteur/{auteurId}")
    public List<Livre> recupereLivresParAuteur(@PathVariable int auteurId){
        return livreService.recupereParAuteur(auteurId);
    }

    @PutMapping("/{id}/reserver")
    public Livre reserver(@PathVariable int id){
        return livreService.reserver(id);
    }

    @PutMapping("/{id}/perdu")
    public Livre declarerPerdu(@PathVariable int id){
        return livreService.declarerPerdu(id);
    }

    @PutMapping("/{id}/vol")
    public Livre vol(@PathVariable int id){
        return livreService.vol(id);
    }

    @DeleteMapping("/{id}")
    public Livre suprimer(@PathVariable int id){
        return livreService.suprimer(id);
    }
}
