package com.ae.gestion_etudiants.controllers;

import javax.validation.Valid;

import com.ae.gestion_etudiants.enteties.Niveau;
import com.ae.gestion_etudiants.services.NiveauService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/api/niveau")
public class NiveauController {
    private NiveauService niveauService;

    public NiveauController(NiveauService niveauService) {
        this.niveauService = niveauService;
    }

    @PostMapping
    public Niveau ajoNiveau(@Valid @RequestBody Niveau niveau) throws Exception {
        Niveau niveau2 = this.niveauService.ajoNiveau(niveau);
        return niveau2;
    }

}
