package com.ae.gestion_etudiants.controllers;

import javax.validation.Valid;

import com.ae.gestion_etudiants.DTo.EtudiantDato;
import com.ae.gestion_etudiants.enteties.Etudiant;
import com.ae.gestion_etudiants.services.EtudiantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users/etudiants/")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class EtudiantController {
    private EtudiantService etudiantService;

    @Autowired
    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
      
    }

    @PostMapping(path = "signup")
    public Etudiant ajouterEtudiant(@RequestBody EtudiantDato etudiant) throws Exception {
        Etudiant newEtudiant = this.etudiantService.ajouterEtudiant(etudiant);
        return newEtudiant;
    }

    @GetMapping
    public List<Etudiant> getEtudiants(){
        return this.etudiantService.gEtudiants();
    }

}
