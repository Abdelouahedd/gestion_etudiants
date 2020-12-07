package com.ae.gestion_etudiants.controllers;

import javax.validation.Valid;

import com.ae.gestion_etudiants.enteties.Absence;
import com.ae.gestion_etudiants.services.AbsenceService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/absence")
public class AbsenceController {
    private AbsenceService absenceService;

    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @PostMapping
    public Absence ajouAbsence(@Valid @RequestBody Absence absence) throws Exception {
        Absence abs = this.absenceService.ajouAbsence(absence);
        return abs;
    }

    @PutMapping(path = "{id}")
    public Absence modifAbsence(@PathVariable("id") Long id, @RequestBody Absence absence) throws Exception {
        Absence abs = this.absenceService.modifAbsence(id, absence);
        return abs;
    }

    @GetMapping(path = "{idEtudiant}")
    public Absence getAbsenceByIdEtudiant(@PathVariable("idEtudiant") Long id) throws Exception {
        Absence abs = this.absenceService.getAbsenceByIdEtudiant(id);
        return abs;
    }

    @GetMapping(path = "{idEtudiant}/{idModule}")
    public Long getNbrTotaleAbsenceParModule(@PathVariable("idModule") Long idModule,
            @PathVariable("idEtudiant") Long idEtudiant) throws Exception {
        Long nbrAbsenceByEtudiat = this.absenceService.getNbrTotaleAbsenceParModule(idModule, idEtudiant);
        return nbrAbsenceByEtudiat;
    }

}
