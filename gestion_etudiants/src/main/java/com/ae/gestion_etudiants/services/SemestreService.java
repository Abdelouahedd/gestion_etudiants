package com.ae.gestion_etudiants.services;

import java.util.List;

import com.ae.gestion_etudiants.enteties.Semestre;
import com.ae.gestion_etudiants.reposetories.SemestreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SemestreService {
    private SemestreRepository semestreRepository;

    @Autowired
    public SemestreService(SemestreRepository semestreRepository) {
        this.semestreRepository = semestreRepository;
    }

    public Semestre ajouSemestre(Semestre semestre) {
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
