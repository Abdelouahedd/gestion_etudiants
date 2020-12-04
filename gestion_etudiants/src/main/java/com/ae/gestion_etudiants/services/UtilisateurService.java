package com.ae.gestion_etudiants.services;

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

    public Utilisateur addUser(Utilisateur utilisateur) {
        System.out.println("user aded "+utilisateur);
        return this.utilisateurRepository.save(utilisateur);
    }


}
