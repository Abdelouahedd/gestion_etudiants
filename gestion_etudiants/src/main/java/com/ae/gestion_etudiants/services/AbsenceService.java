package com.ae.gestion_etudiants.services;

import com.ae.gestion_etudiants.DTo.AbsenceDTO;
import com.ae.gestion_etudiants.DTo.dashBord.NbrAbsByModule;
import com.ae.gestion_etudiants.DTo.dashBordStudent.DashStudent;
import com.ae.gestion_etudiants.enteties.Absence;
import com.ae.gestion_etudiants.enteties.ElementModule;
import com.ae.gestion_etudiants.enteties.Etudiant;
import com.ae.gestion_etudiants.enteties.Module;
import com.ae.gestion_etudiants.reposetories.AbsenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceService {

    private AbsenceRepository absenceRepository;
    private EtudiantService etudiantService;
    private ElementModuleService elementModuleService;

    public AbsenceService(AbsenceRepository absenceRepository, EtudiantService etudiantService, ElementModuleService elementModuleService) {
        this.absenceRepository = absenceRepository;
        this.etudiantService = etudiantService;
        this.elementModuleService = elementModuleService;
    }

    public Absence ajouAbsence(Absence absence) throws Exception {
        Absence abs = this.absenceRepository.save(absence);
        return abs;
    }

    public Absence ajouAbsence(AbsenceDTO absenceDTO) throws Exception {
        System.out.println(absenceDTO.toString());
        Etudiant etudiant = this.etudiantService.gEtudiant(absenceDTO.getIdEtudiant());
        ElementModule elementModule = this.elementModuleService.gElementModule(absenceDTO.getIdElementModule());
        Module module = elementModule.getModule();
        Absence abs = new Absence();
        abs.setEtudiant(etudiant);
        abs.setModule(module);
        abs.setNbrSeanceAbs(absenceDTO.getSeance());
        return this.absenceRepository.save(abs);
    }

    public Absence modifAbsence(Long id, Absence absence) throws Exception {
        if (id == null)
            throw new Exception("Id est null");
        absence.setId(id);
        Absence abs = this.absenceRepository.save(absence);
        return abs;
    }

    public List<Absence> findAbsenceByIdModule(Long module) throws Exception {
        if (module == null)
            throw new Exception("Id est null");
        List<Absence> abs = this.absenceRepository.findAbsenceByIdModule(module);
        return abs;
    }

    public Long getNbrTotaleAbsenceParModule(Long idModule, Long idEtudiant) throws Exception {
        if (idModule == null || idEtudiant == null)
            throw new Exception("Id est null");
        Long nbrAbsenceByEtudiat = this.absenceRepository.nombreAbsence(idModule, idEtudiant);
        return nbrAbsenceByEtudiat;
    }

    public List<DashStudent> nombreAbsenceByModule(Long idEtudiant) throws Exception {
        if (idEtudiant == null)
            throw new Exception("Id est null");
        return this.absenceRepository.nombreAbsenceByModule(idEtudiant);
    }

    public List<NbrAbsByModule> getnbrAbsByModule() {
        return this.absenceRepository.getnbrAbsByModule();
    }

}
