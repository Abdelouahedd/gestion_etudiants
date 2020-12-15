package com.ae.gestion_etudiants.controllers;

import javax.validation.Valid;

import com.ae.gestion_etudiants.enteties.Etudiant;
import com.ae.gestion_etudiants.services.EtudiantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/api/users/etudiants/")
public class EtudiantController {
    private EtudiantService etudiantService;

    @Autowired
    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
      
    }

    @PostMapping(path = "signup")
    public Etudiant ajouterEtudiant(@Valid @RequestBody Etudiant etudiant) throws Exception {
        Etudiant newEtudiant = this.etudiantService.ajouterEtudiant(etudiant);
        return newEtudiant;
    }

}
