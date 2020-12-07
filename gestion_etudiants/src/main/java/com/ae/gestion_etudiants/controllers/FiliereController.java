package com.ae.gestion_etudiants.controllers;

import java.util.List;

import javax.validation.Valid;

import com.ae.gestion_etudiants.enteties.Filiere;
import com.ae.gestion_etudiants.services.FiliereService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/api/filiere")
public class FiliereController {
    private FiliereService filiereService;

    @Autowired
    public FiliereController(FiliereService filiereService) {
        this.filiereService = filiereService;
    }

    @PostMapping
    public Filiere creerFiliere(@Valid @RequestBody Filiere filiere) throws Exception {
        Filiere flr = this.filiereService.ajouFiliere(filiere);
        return flr;
    }

    @GetMapping(params = "{id}")
    public Filiere getFiliere(@PathVariable("id") Long id) throws Exception {
        Filiere flr = this.filiereService.getFiliere(id);
        return flr;
    }

    @GetMapping
    public List<Filiere> getFilieres() throws Exception {
        List<Filiere> listFiliere = this.filiereService.getFilieres();
        return listFiliere;
    }

    @PutMapping(path = "{id}")
    public Filiere modiFiliere(@PathVariable("id") Long id, @Valid @RequestBody Filiere entity) throws Exception {
        Filiere flr = this.filiereService.updateFiliere(id, entity);
        return flr;
    }

}
