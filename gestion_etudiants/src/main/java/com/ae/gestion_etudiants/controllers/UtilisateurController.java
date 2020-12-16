package com.ae.gestion_etudiants.controllers;

import java.util.List;

import com.ae.gestion_etudiants.DTo.AuthResponse;
import com.ae.gestion_etudiants.DTo.FormLogin;
import com.ae.gestion_etudiants.enteties.Utilisateur;
import com.ae.gestion_etudiants.services.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody FormLogin login) {
        String token = this.utilisateurService.login(login);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping(path = "{idUser}")
    public Utilisateur gUtilisateur(@PathVariable("idUser") Long id) {
        return this.utilisateurService.gUtilisateur(id);
    }

    @DeleteMapping(path = "{id}")
    public void suprimerUtilisateur(@PathVariable("id") Long id) {
        this.utilisateurService.suprimerUtilisateur(id);
    }

    @GetMapping(path = "/")
    public List<Utilisateur> gUtilisateurs() {
        return this.utilisateurService.gUtilisateurs();
    }

}
