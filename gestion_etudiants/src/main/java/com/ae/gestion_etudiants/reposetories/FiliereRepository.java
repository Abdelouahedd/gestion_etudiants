package com.ae.gestion_etudiants.reposetories;

import com.ae.gestion_etudiants.enteties.Filiere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {

}
