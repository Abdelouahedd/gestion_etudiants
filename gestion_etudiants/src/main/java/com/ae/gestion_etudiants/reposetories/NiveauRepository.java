package com.ae.gestion_etudiants.reposetories;

import com.ae.gestion_etudiants.enteties.Niveau;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Long> {

}
