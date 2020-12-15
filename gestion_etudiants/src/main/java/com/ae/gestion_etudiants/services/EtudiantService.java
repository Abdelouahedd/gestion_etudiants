package com.ae.gestion_etudiants.services;

import java.util.List;

import javax.transaction.Transactional;

import com.ae.gestion_etudiants.enteties.Etudiant;
import com.ae.gestion_etudiants.enumerations.Roles;
import com.ae.gestion_etudiants.reposetories.EtudiantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EtudiantService {
    private EtudiantRepository etudiantRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EtudiantService(EtudiantRepository etudiantRepository, PasswordEncoder passwordEncoder) {
        this.etudiantRepository = etudiantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Etudiant ajouterEtudiant(Etudiant etudiant) throws Exception {
        Etudiant etudiant2 = this.etudiantRepository.findByCne(etudiant.getCne());
        if (etudiant2 != null)
            throw new Exception("CNE  " + etudiant.getCne() + " deja exist");
        else {
            String password = etudiant.getPassword();
            String hashPass = passwordEncoder.encode(password);
            etudiant.setPassword(hashPass);
            etudiant.setRole(Roles.ETUDIANT);
            Etudiant newEtudiant = this.etudiantRepository.save(etudiant);

            return newEtudiant;
        }
    }

    public Etudiant modiEtudiant(Long id, Etudiant etudiant) {
        etudiant.setId(id);
        Etudiant newEtudiant = this.etudiantRepository.save(etudiant);
        return newEtudiant;
    }

    public void suprimerEtudiant(Long id) throws Exception {
        if (id == null)
            throw new Exception("id de l\'etudiant est null");
        this.etudiantRepository.deleteById(id);
    }

    public Etudiant gEtudiant(Long id) throws Exception {
        if (id == null)
            throw new Exception("id de l\'etudiant est null");
        return this.etudiantRepository.findById(id).get();
    }

    public List<Etudiant> gEtudiants() {
        return this.etudiantRepository.findAll();
    }

}
