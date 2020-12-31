package com.ae.gestion_etudiants.services;

import com.ae.gestion_etudiants.DTo.SemestreDtoInsert;
import com.ae.gestion_etudiants.enteties.Niveau;
import com.ae.gestion_etudiants.enteties.Semestre;
import com.ae.gestion_etudiants.reposetories.NiveauRepository;
import com.ae.gestion_etudiants.reposetories.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemestreService {
    private SemestreRepository semestreRepository;
    private NiveauRepository niveauRepository;

    @Autowired
    public SemestreService(SemestreRepository semestreRepository, NiveauRepository niveauRepository) {
        this.semestreRepository = semestreRepository;
        this.niveauRepository = niveauRepository;
    }

    public Semestre ajouSemestre(SemestreDtoInsert dtoInsert) throws Exception {
        if (dtoInsert.getSemestre() == null)
            throw new Exception("Semestre est obligatoir");
        Semestre semestre = new Semestre();
        Niveau niveau = this.niveauRepository.findById(dtoInsert.getNiveau()).get();
        semestre.setSemestre(dtoInsert.getSemestre());
        semestre.setNiveau(niveau);
        return this.semestreRepository.save(semestre);
    }

    public Semestre gSemestre(Long id) throws Exception {
        if (id == null)
            throw new Exception("Id est obligatoir");
        return this.semestreRepository.findById(id).orElseThrow(() -> new Exception("Semestre not found"));
    }

    public List<Semestre> getSemestres() {
        return this.semestreRepository.findAll();
    }

    public List<Semestre> getSemestresNiveau(Long id)throws Exception {
        if (id == null)
        throw new Exception("Id est obligatoir");
        return this.semestreRepository.getSemestreOfNiveau(id);
    }

}
