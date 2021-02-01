package com.ae.gestion_etudiants.services;

import com.ae.gestion_etudiants.enteties.Prof;
import com.ae.gestion_etudiants.enumerations.Roles;
import com.ae.gestion_etudiants.reposetories.ProfRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfService {
    private ProfRepository profRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ProfService(ProfRepository profRepository, PasswordEncoder passwordEncoder) {
        this.profRepository = profRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean login(String email, String password) throws Exception {
        if (email == null) {
            throw new Exception("Le chemp email est obligatoir");
        }
        if (password == null) {
            throw new Exception("Le chemp password est obligatoir");
        }
        Prof prof = this.profRepository.findByEmail(email);
        if (prof != null)
            return prof.getPassword().equals(password);
        return false;
    }

    public Prof ajouProf(Prof prof) throws Exception {
        valideLesChempsObligatoir(prof);
        String password = prof.getPassword();
        String hashPass = passwordEncoder.encode(password);
        prof.setPassword(hashPass);
        prof.setRole(Roles.PROF);
        Prof savedProf = this.profRepository.save(prof);
        return savedProf;
    }

    public Prof updateProf(Long id, Prof prof) throws Exception {
        valideLesChempsObligatoir(prof);
        prof.setId(id);
        Prof savedProf = this.profRepository.save(prof);
        return savedProf;
    }

    private void valideLesChempsObligatoir(Prof prof) throws Exception {
        if (prof.getCin() == null || prof.getEmail() == null || prof.getNom() == null || prof.getPrenom() == null) {
            throw new Exception("Les chemps sont obligatoir");
        }
    }

    public List<Prof>getProfList(){
        return this.profRepository.findAll();
    }

}
