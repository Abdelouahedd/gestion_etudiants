package com.ae.gestion_etudiants.services;

import com.ae.gestion_etudiants.enteties.Absence;
import com.ae.gestion_etudiants.reposetories.AbsenceRepository;

import org.springframework.stereotype.Service;

@Service
public class AbsenceService {

    private AbsenceRepository absenceRepository;

    public AbsenceService(AbsenceRepository absenceRepository) {
        this.absenceRepository = absenceRepository;
    }

    public Absence ajouAbsence(Absence absence) throws Exception {
        Absence abs = this.absenceRepository.save(absence);
        return abs;
    }

    public Absence modifAbsence(Long id, Absence absence) throws Exception {
        if (id == null)
            throw new Exception("Id est null");
        absence.setId(id);
        Absence abs = this.absenceRepository.save(absence);
        return abs;
    }

    public Absence getAbsenceByIdEtudiant(Long idEtudiant) throws Exception {
        if (idEtudiant == null)
            throw new Exception("Id est null");
        Absence abs = this.absenceRepository.findAbsenceByIdEtudiant(idEtudiant);
        return abs;
    }

    public Long getNbrTotaleAbsenceParModule(Long idModule, Long idEtudiant) throws Exception {
        if (idModule == null || idEtudiant == null)
            throw new Exception("Id est null");
        Long nbrAbsenceByEtudiat = this.absenceRepository.nombreAbsence(idModule, idEtudiant);
        return nbrAbsenceByEtudiat;
    }

}
