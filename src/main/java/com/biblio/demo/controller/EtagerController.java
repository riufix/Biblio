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
public class EtagerController {

    private final EtagereService etagereService;

    @PostMapping
    public Etagere ajouterEtagere(@RequestBody Etagere etagere) {
        return etagereService.ajouter(etagere);
    }

    @GetMapping
    public List<Etagere> recupereEtagere(){
        return etagereService.recupereToutesLesEtageres();
    }

    @GetMapping("/{id}")
    public Etagere recupereEtagereParId(@PathVariable int id){
        return etagereService.recupereParId(id);
    }
}
