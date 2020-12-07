package com.ae.gestion_etudiants.services;

import com.ae.gestion_etudiants.enteties.Niveau;
import com.ae.gestion_etudiants.reposetories.NiveauRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NiveauService {
    private NiveauRepository niveauRepository;

    @Autowired
    public NiveauService(NiveauRepository niveauRepository) {
        this.niveauRepository = niveauRepository;
    }

    public Niveau ajoNiveau(Niveau niveau) throws Exception {
        if (niveau.getNiveau() == null)
            throw new Exception("Le chemps niveau est obligatoir");
        if (niveau.getNiveau() < 1 || niveau.getNiveau() > 3) {
            throw new Exception("Le niveau doit etre 1 et 3 ");
        }
        Niveau niveau2 = this.niveauRepository.save(niveau);
        return niveau2;
    }
}