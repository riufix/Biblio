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
    public Livre ajouterLivre(@RequestBody String titre){
        return livreService.ajouter(titre);
    }

    @GetMapping // recupere tout les livres
    public List<Livre> recupereLivre(){
        return livreService.recupereToutLesLivres();
    }

    @GetMapping("/{id}") // recupere le livre id = id
    public Livre recupereLivreParId(@PathVariable int id){
        return livreService.recupereParId(id);
    }

}
