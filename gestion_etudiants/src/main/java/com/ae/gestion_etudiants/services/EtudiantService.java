package com.ae.gestion_etudiants.services;

import com.ae.gestion_etudiants.DTo.EtudiantDTO;
import com.ae.gestion_etudiants.DTo.EtudiantDato;
import com.ae.gestion_etudiants.enteties.Etudiant;
import com.ae.gestion_etudiants.enteties.Niveau;
import com.ae.gestion_etudiants.enumerations.Roles;
import com.ae.gestion_etudiants.reposetories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EtudiantService {
    private EtudiantRepository etudiantRepository;
    private PasswordEncoder passwordEncoder;
    private NiveauService niveauService;

    @Autowired
    public EtudiantService(EtudiantRepository etudiantRepository, PasswordEncoder passwordEncoder, NiveauService niveauService) {
        this.etudiantRepository = etudiantRepository;
        this.passwordEncoder = passwordEncoder;
        this.niveauService = niveauService;
    }

    public Etudiant ajouterEtudiant(EtudiantDato etudiantDato) throws Exception {
        Etudiant etudiant2 = this.etudiantRepository.findByCne(etudiantDato.getCne());
        if (etudiant2 != null)
            throw new Exception("CNE  " + etudiantDato.getCne() + " deja exist");
        else {
            Niveau niveau = this.niveauService.geNiveau(etudiantDato.getNiveau());
            String password = etudiantDato.getPassword();
            String hashPass = passwordEncoder.encode(password);

            Etudiant etudiant = new Etudiant();
            etudiant.setPassword(hashPass);
            etudiant.setRole(Roles.ETUDIANT);
            etudiant.setCne(etudiantDato.getCne());
            etudiant.setEmail(etudiantDato.getEmail());
            etudiant.setPrenom(etudiantDato.getPrenom());
            etudiant.setNom(etudiantDato.getNom());
            etudiant.setNiveau(niveau);

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

    public List<EtudiantDTO> gEtudiants(Long idElemnt) {
        return this.etudiantRepository.findEtudiantByModule(idElemnt);
    }

}
