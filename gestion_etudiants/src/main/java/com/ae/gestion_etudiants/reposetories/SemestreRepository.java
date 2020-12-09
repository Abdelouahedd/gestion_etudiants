package com.ae.gestion_etudiants.reposetories;

import java.util.List;

import com.ae.gestion_etudiants.enteties.Semestre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long> {
    @Query("from Semestre s where s.niveau.id = :idNiveau")
    public List<Semestre>getSemestreOfNiveau(@Param("idNiveau") Long id);

}
