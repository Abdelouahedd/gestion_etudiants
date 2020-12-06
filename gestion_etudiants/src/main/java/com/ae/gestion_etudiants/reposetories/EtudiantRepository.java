package com.ae.gestion_etudiants.reposetories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ae.gestion_etudiants.enteties.Etudiant;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    public Etudiant findByCne(String cne);
}
