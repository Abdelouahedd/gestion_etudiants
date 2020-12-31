package com.ae.gestion_etudiants.controllers;

import com.ae.gestion_etudiants.DTo.SemestreDtoInsert;
import com.ae.gestion_etudiants.enteties.Semestre;
import com.ae.gestion_etudiants.services.SemestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<?> ajouterSemestre(@RequestBody SemestreDtoInsert s) {
        try {
            Semestre semestre = this.semestreService.ajouSemestre(s);
            return ResponseEntity.ok(semestre);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

}
