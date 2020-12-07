package com.ae.gestion_etudiants.reposetories;

import com.ae.gestion_etudiants.enteties.Prof;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfRepository extends JpaRepository<Prof, Long> {
    public Prof findByEmail(String email);
}
