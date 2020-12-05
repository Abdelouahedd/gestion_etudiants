package com.ae.gestion_etudiants.services;

import java.util.List;

import com.ae.gestion_etudiants.enteties.Utilisateur;
import com.ae.gestion_etudiants.reposetories.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository repository) {
        this.utilisateurRepository = repository;
    }

    public Utilisateur ajoUtilisateur(Utilisateur utilisateur) {
        return  this.utilisateurRepository.save(utilisateur);
    }

    public Utilisateur modifUtilisateur(Long id, Utilisateur utilisateur) {
        utilisateur.setId(id);
        return this.utilisateurRepository.save(utilisateur);
    }

    public Utilisateur gUtilisateur(Long id) {
        return this.utilisateurRepository.findById(id).get();
    }

    public List<Utilisateur> gUtilisateurs() {
        return this.utilisateurRepository.findAll();
    }

}
