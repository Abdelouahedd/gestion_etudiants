package com.ae.gestion_etudiants.controllers;

import java.util.List;

import com.ae.gestion_etudiants.enteties.Semestre;
import com.ae.gestion_etudiants.services.SemestreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/semestre/")
public class SemestreControler {
    private SemestreService semestreService;

    @Autowired
    public SemestreControler(SemestreService semestreService) {
        this.semestreService = semestreService;
    }

    @GetMapping
    public List<Semestre> getListSemstres() {
        return this.semestreService.getSemestres();
    }

    @GetMapping(path = "{id}")
    public Semestre getListSemstre(@PathVariable("id") Long id) throws Exception {
        return this.semestreService.gSemestre(id);
    }

    @GetMapping(path = "/niveau/{id}")
    public List<Semestre> getListSemstresNiveau(@PathVariable("id") Long id) throws Exception {
        return this.semestreService.getSemestresNiveau(id);
    }

}
