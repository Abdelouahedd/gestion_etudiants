package com.ae.gestion_etudiants.controllers;

import com.ae.gestion_etudiants.DTo.dashBord.DashBord;
import com.ae.gestion_etudiants.DTo.dashBord.NbrAbsByModule;
import com.ae.gestion_etudiants.DTo.dashBord.NbrStudentByFiliere;
import com.ae.gestion_etudiants.reposetories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/dash")
public class DashbordController {
    private ProfRepository profRepository;
    private EtudiantRepository etudiantRepository;
    private CourRepository courRepository;
    private FiliereRepository filiereRepository;
    private AbsenceRepository absenceRepository;

    public DashbordController(ProfRepository profRepository, EtudiantRepository etudiantRepository, CourRepository courRepository, FiliereRepository filiereRepository, AbsenceRepository absenceRepository) {
        this.profRepository = profRepository;
        this.etudiantRepository = etudiantRepository;
        this.courRepository = courRepository;
        this.filiereRepository = filiereRepository;
        this.absenceRepository = absenceRepository;
    }

    @GetMapping
    DashBord getData() {
        DashBord dashBord = constructDashBord();
        return (dashBord);
    }

    private DashBord constructDashBord() {
        Long numberOfProf = this.profRepository.countProf();
        Long numberOfStudent = this.etudiantRepository.countEtudiant();
        Long numberOfCours = this.courRepository.countCour();
        Long numberOfFiliere = this.filiereRepository.countFiliere();
        List<NbrStudentByFiliere> nbrStudentByFiliere = this.filiereRepository.getNbrStudentByFiliere();
        List<NbrAbsByModule> nbrAbsByModule = this.absenceRepository.getnbrAbsByModule();
        DashBord dashBord = new DashBord(numberOfProf, numberOfStudent, numberOfCours, numberOfFiliere, nbrStudentByFiliere, nbrAbsByModule);
        return dashBord;
    }
}
