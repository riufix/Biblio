package com.biblio.demo.controller;

import com.biblio.demo.model.Livre;
import com.biblio.demo.service.LivreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livre")
@RequiredArgsConstructor
public class LivreController {

    private final LivreService livreService;

    @PostMapping
    public Livre ajouterLivre(@RequestBody Livre livre){
        return livreService.ajouter(livre);
    }

    @GetMapping
    public List<Livre> recupereLivre(){
        return livreService.recupereToutLesLivres();
    }

    @GetMapping("/{id}")
    public Livre recupereLivreParId(@PathVariable int id){
        return livreService.recupereParId(id);
    }

    // NOUVEAUX ENDPOINTS METIERS
    @PutMapping("/{id}/emprunter")
    public Livre emprunter(@PathVariable int id){
        return livreService.emprunter(id);
    }

    @PutMapping("/{id}/rendre")
    public Livre rendre(@PathVariable int id){
        return livreService.rendre(id);
    }

    @PutMapping("/{id}/perdu")
    public Livre perdu(@PathVariable int id){
        return livreService.declarerPerdu();
    }
}