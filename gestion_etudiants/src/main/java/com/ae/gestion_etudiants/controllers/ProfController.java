package com.ae.gestion_etudiants.controllers;

import javax.validation.Valid;

import com.ae.gestion_etudiants.enteties.Prof;
import com.ae.gestion_etudiants.services.ProfService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api/users/prof")
@RestController
public class ProfController {
    private ProfService profService;

    @Autowired
    public ProfController(ProfService profService) {
        this.profService = profService;

    }

    @PostMapping(path = "signup")
    public Prof creeProf(@Valid @RequestBody Prof prof) throws Exception {
        Prof savedProf = this.profService.ajouProf(prof);
        return savedProf;
    }

}
