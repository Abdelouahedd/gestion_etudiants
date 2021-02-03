package com.ae.gestion_etudiants.reposetories;

import com.ae.gestion_etudiants.enteties.Prof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfRepository extends JpaRepository<Prof, Long> {
    Prof findByEmail(String email);

    Optional<Prof> findByCin(String cin);

    @Query("select count(distinct id) from Prof ")
    Long countProf();
}
