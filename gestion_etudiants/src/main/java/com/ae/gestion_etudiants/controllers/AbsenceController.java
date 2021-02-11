package com.ae.gestion_etudiants.controllers;

import com.ae.gestion_etudiants.DTo.AbsenceDTO;
import com.ae.gestion_etudiants.DTo.dashBord.NbrAbsByModule;
import com.ae.gestion_etudiants.DTo.dashBordStudent.DashStudent;
import com.ae.gestion_etudiants.enteties.Absence;
import com.ae.gestion_etudiants.services.AbsenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/absence")
public class AbsenceController {
    private AbsenceService absenceService;

    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @RequestMapping(path = "/")
    public Absence ajouAbsence(@RequestBody AbsenceDTO absence) throws Exception {
        System.out.println("Request --> " + absence.toString());
        try {
            Absence abs = this.absenceService.ajouAbsence(absence);
            return abs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PutMapping(path = "{id}")
    public Absence modifAbsence(@PathVariable("id") Long id, @RequestBody Absence absence) throws Exception {
        Absence abs = this.absenceService.modifAbsence(id, absence);
        return abs;
    }

    @GetMapping(path = "{idModule}")
    public List<Absence> getAbsenceByIdModule(@PathVariable("idModule") Long id) throws Exception {
        List<Absence> abs = this.absenceService.findAbsenceByIdModule(id);
        return abs;
    }

    @GetMapping(path = "{idEtudiant}/{idModule}")
    public Long getNbrTotaleAbsenceParModule(@PathVariable("idModule") Long idModule,
            @PathVariable("idEtudiant") Long idEtudiant) throws Exception {
        Long nbrAbsenceByEtudiat = this.absenceService.getNbrTotaleAbsenceParModule(idModule, idEtudiant);
        return nbrAbsenceByEtudiat;
    }

    @GetMapping("etudiant/{id}")
    public ResponseEntity<List<DashStudent>> nombreAbsenceByModule(@PathVariable("id") Long idEtudiant) throws Exception {
        if (idEtudiant == null)
            throw new Exception("Id est null");
        List<DashStudent> absenceByModule = this.absenceService.nombreAbsenceByModule(idEtudiant);
        return new ResponseEntity<>(absenceByModule, HttpStatus.OK);
    }

    @RequestMapping(path = "/dash", method = RequestMethod.GET)
    List<NbrAbsByModule> getnbrAbsByModule() {
        try {
            return this.absenceService.getnbrAbsByModule();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }
}
