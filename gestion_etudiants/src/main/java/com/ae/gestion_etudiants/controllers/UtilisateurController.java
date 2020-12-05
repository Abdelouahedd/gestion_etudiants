package com.ae.gestion_etudiants.controllers;

import java.util.List;

import javax.validation.Valid;

import com.ae.gestion_etudiants.enteties.Utilisateur;
import com.ae.gestion_etudiants.services.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public Object ajoUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
        Object newUtilisateur = this.utilisateurService.ajoUtilisateur(utilisateur);
        return newUtilisateur;
    }

    @PutMapping(path = "{idUser}")
    public Utilisateur modiUtilisateur(@PathVariable("idUser") Long id, @Valid @RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateurAfterModif = this.utilisateurService.modifUtilisateur(id, utilisateur);
        return utilisateurAfterModif;
    }

    @GetMapping(path = "{idUser}")
    public Utilisateur gUtilisateur(@PathVariable("idUser") Long id) {
        Utilisateur utilisateur2 = this.utilisateurService.gUtilisateur(id);
        return utilisateur2;
    }

    @GetMapping(path = "/")
    public List<Utilisateur> gUtilisateurs() {
        List<Utilisateur> listUtilisateur = this.utilisateurService.gUtilisateurs();
        return listUtilisateur;
    }

}
