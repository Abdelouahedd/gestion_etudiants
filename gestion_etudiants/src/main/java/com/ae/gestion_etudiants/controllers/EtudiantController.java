package com.ae.gestion_etudiants.controllers;

import java.util.List;

import javax.validation.Valid;

import com.ae.gestion_etudiants.enteties.Etudiant;
import com.ae.gestion_etudiants.services.EtudiantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/api/etudiants/")
public class EtudiantController {
    private EtudiantService etudiantService;

    @Autowired
    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @PostMapping
    public Etudiant ajouterEtudiant(@Valid @RequestBody Etudiant etudiant) throws Exception {
        Etudiant newEtudiant = this.etudiantService.ajouterEtudiant(etudiant);
        return newEtudiant;
    }

    @PutMapping(path = "{id}")
    public Etudiant modiEtudiant(@PathVariable("id") Long id, @RequestBody Etudiant etudiant) {
        Etudiant newEtudiant = this.etudiantService.modiEtudiant(id, etudiant);
        return newEtudiant;
    }

    @DeleteMapping(path = "{id}")
    public void suprimerEtudiant(@PathVariable("id") Long id) throws Exception {
        this.etudiantService.suprimerEtudiant(id);
    }

    @GetMapping(path = "{id}")
    public Etudiant gEtudiant(@PathVariable("id") Long id) throws Exception {
        return this.etudiantService.gEtudiant(id);
    }

    @GetMapping
    public List<Etudiant> gEtudiants() {
        return this.etudiantService.gEtudiants();
    }

}
