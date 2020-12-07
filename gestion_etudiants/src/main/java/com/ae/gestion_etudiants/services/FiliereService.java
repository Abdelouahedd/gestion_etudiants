package com.ae.gestion_etudiants.services;

import java.util.List;

import com.ae.gestion_etudiants.enteties.Filiere;
import com.ae.gestion_etudiants.reposetories.FiliereRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FiliereService {
    private FiliereRepository filiereRepository;

    @Autowired
    public FiliereService(FiliereRepository filiereRepository) {
        this.filiereRepository = filiereRepository;
    }

    public Filiere ajouFiliere(Filiere filiere) throws Exception {
        if (filiere.getNomFormation() == null) {
            throw new Exception("Description est obligatoir");
        }
        Filiere savedFiliere = this.filiereRepository.save(filiere);
        return savedFiliere;
    }

    public Filiere updateFiliere(Long id, Filiere filiere) {
        filiere.setId(id);
        Filiere filiere2 = this.filiereRepository.save(filiere);
        return filiere2;
    }

    public Filiere getFiliere(Long id) throws Exception {
        if (id == null) {
            throw new Exception("id est obligatoir");
        }
        Filiere savedFiliere = this.filiereRepository.findById(id)
                .orElseThrow(() -> new Exception("Filiere not Found"));
        return savedFiliere;
    }

    public List<Filiere> getFilieres() throws Exception {
        List<Filiere> listFiliere = this.filiereRepository.findAll();
        return listFiliere;
    }

}
