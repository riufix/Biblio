package com.biblio.demo.controller;

import com.biblio.demo.model.Etagere;
import com.biblio.demo.model.Livre;
import com.biblio.demo.service.EtagereService;
import com.biblio.demo.service.LivreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etagere")
@RequiredArgsConstructor
public class EtagereController {

    private final EtagereService etagereService;
    private final LivreService livreService;

    @PostMapping // POST /api/etagere?row=1&column=2
    public Etagere ajouterEtagere(@RequestParam int row, @RequestParam int column) {
        return etagereService.ajouter(row, column);
    }

    @GetMapping
    public List<Etagere> recupereEtagere(){
        return etagereService.recupereToutesLesEtageres();
    }

    @GetMapping("/{id}")
    public Etagere recupereEtagereParId(@PathVariable int id){
        return etagereService.recupereParId(id);
    }

    @GetMapping("/{id}/livres")
    public List<Livre> recupereLivres(@PathVariable int id){
        return etagereService.recupereLivres(id);
    }

    @PostMapping("/{id}/livre/{livreId}")
    public Etagere ajouterLivre(@PathVariable int id, @PathVariable int livreId){
        var livre = livreService.recupereParId(livreId);
        return etagereService.ajouterLivre(id, livre);
    }

    @DeleteMapping("/{id}/livre/{livreId}")
    public Etagere retirerLivre(@PathVariable int id, @PathVariable int livreId){
        var livre = livreService.recupereParId(livreId);
        return etagereService.retirerLivre(id, livre);
    }

    @PutMapping("/{id}") // PUT /api/etagere/1?row=3&column=4
    public Etagere deplacer(@PathVariable int id, @RequestParam int row, @RequestParam int column){
        return etagereService.deplacer(id, row, column);
    }
}
