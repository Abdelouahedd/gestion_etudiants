package com.ae.gestion_etudiants.controllers;

import com.ae.gestion_etudiants.enteties.Utilisateur;
import com.ae.gestion_etudiants.services.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api/users/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UtilisateurController {
    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping(path = "addUser")
    public Utilisateur addUser(@RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateur2 = utilisateurService.addUser(utilisateur);
        return utilisateur2;
    }

}
