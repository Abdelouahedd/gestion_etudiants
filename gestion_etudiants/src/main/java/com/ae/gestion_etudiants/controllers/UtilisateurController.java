package com.ae.gestion_etudiants.controllers;

import com.ae.gestion_etudiants.DTo.AuthResponse;
import com.ae.gestion_etudiants.DTo.DtoEmail;
import com.ae.gestion_etudiants.DTo.FormLogin;
import com.ae.gestion_etudiants.enteties.Utilisateur;
import com.ae.gestion_etudiants.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/users/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

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

    @GetMapping(path = "email")
    public Utilisateur gUtilisateurByEmail(@RequestBody DtoEmail DtoEmail) {
        return this.utilisateurService.gUtilisateurByEmail(DtoEmail.getEmail());
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

