package com.ae.gestion_etudiants.controllers;

import com.ae.gestion_etudiants.enteties.Prof;
import com.ae.gestion_etudiants.services.ProfService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api/prof")
@RestController
public class ProfController {
    private ProfService profService;

    @Autowired
    public ProfController(ProfService profService) {
        this.profService = profService;
    }

    @PostMapping(path = "/register")
    public Prof creeProf(@RequestBody Prof prof) throws Exception {
        Prof savedProf = this.profService.ajouProf(prof);
        return savedProf;
    }

    @PostMapping(path = "/login")
    public boolean login(String email, String password) throws Exception {
        boolean isLoged = this.profService.login(email, password);
        return isLoged;
    }

    @PutMapping(path = "{id}")
    public Prof modifProf(@PathVariable("id") Long id, @RequestBody Prof prof) throws Exception {
        Prof newProf = this.profService.updateProf(id, prof);
        return newProf;
    }

}
