package com.ae.gestion_etudiants.controllers;

import com.ae.gestion_etudiants.DTo.AuthResponse;
import com.ae.gestion_etudiants.DTo.DtoEmail;
import com.ae.gestion_etudiants.DTo.FormLogin;
import com.ae.gestion_etudiants.enteties.Utilisateur;
import com.ae.gestion_etudiants.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/users/")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;

    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody FormLogin login) {
        AuthResponse token = this.utilisateurService.login(login);
        return ResponseEntity.ok(token);
    }

    @GetMapping(path = "/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestHeader(value = "x-auth-token") String req) {
        try {
            return ResponseEntity.ok(this.utilisateurService.refereshToken(req));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied (Token not valid)");
        }
    }

    @GetMapping(path = "{idUser}")
    public Utilisateur gUtilisateur(@PathVariable("idUser") Long id) {
        return this.utilisateurService.gUtilisateur(id);
    }

   @GetMapping(path = "email")
    public Utilisateur gUtilisateurByEmail(@RequestBody DtoEmail dtoEmail) {
        System.out.println("Body --> "+dtoEmail.getEmail());
        return this.utilisateurService.gUtilisateurByEmail(dtoEmail.getEmail());
    }

    @GetMapping(value = "/user")
    public Utilisateur gUtilisateurByEmail(@RequestParam(value = "email")String email) {
        System.out.println("Body --> "+email);
        return this.utilisateurService.gUtilisateurByEmail(email);
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

