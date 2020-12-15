package com.ae.gestion_etudiants.controllers;

import java.util.List;

import com.ae.gestion_etudiants.enteties.Utilisateur;
import com.ae.gestion_etudiants.services.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(path = "/login")
    public boolean login(String email, String password) throws Exception {
        boolean isLoged = this.utilisateurService.login(email, password);
        return isLoged;
    }

    @GetMapping(path = "{idUser}")
    public Utilisateur gUtilisateur(@PathVariable("idUser") Long id) {
        Utilisateur utilisateur2 = this.utilisateurService.gUtilisateur(id);
        return utilisateur2;
    }

    @DeleteMapping(path = "{id}")
    public void suprimerUtilisateur(@PathVariable("id") Long id) throws Exception {
        this.utilisateurService.suprimerUtilisateur(id);
    }

    @GetMapping(path = "/")
    public List<Utilisateur> gUtilisateurs() {
        List<Utilisateur> listUtilisateur = this.utilisateurService.gUtilisateurs();
        return listUtilisateur;
    }

}
