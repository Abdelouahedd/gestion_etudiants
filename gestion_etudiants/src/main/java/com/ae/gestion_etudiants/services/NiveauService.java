package com.ae.gestion_etudiants.services;

import com.ae.gestion_etudiants.DTo.NiveauDto;
import com.ae.gestion_etudiants.enteties.Filiere;
import com.ae.gestion_etudiants.enteties.Niveau;
import com.ae.gestion_etudiants.reposetories.NiveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NiveauService {
    private NiveauRepository niveauRepository;
    private FiliereService filiereService;

    @Autowired
    public NiveauService(NiveauRepository niveauRepository, FiliereService filiereService) {
        this.niveauRepository = niveauRepository;
        this.filiereService = filiereService;
    }

    public Niveau ajoNiveau(NiveauDto niveau) throws Exception {
        if (niveau.getNiveau() == null)
            throw new Exception("Le chemps niveau est obligatoir");

        Filiere flr = this.filiereService.getFiliere(niveau.getFiliere());
        Niveau nv = new Niveau();
        nv.setNiveau(niveau.getNiveau());
        nv.setFiliere(flr);
        Niveau niveau2 = this.niveauRepository.save(nv);
        return niveau2;
    }

    @Transactional
    public List<Niveau> getNiveaux() {
        List<Niveau> niveaus = this.niveauRepository.findAll();
        return niveaus;
    }
}