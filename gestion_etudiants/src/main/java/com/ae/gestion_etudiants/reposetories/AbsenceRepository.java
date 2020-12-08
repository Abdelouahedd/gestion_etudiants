package com.ae.gestion_etudiants.reposetories;

import com.ae.gestion_etudiants.enteties.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    @Query("from Absence abs where abs.module.id = (:idModule)")
    public List<Absence> findAbsenceByIdModule(@Param("idModule") Long idModule);

    @Query("select sum(abs.nbrSeanceAbs) from Absence abs group by abs.module.id having abs.etudiant.id = (:idEtudiant) AND abs.module.id = (:idModule)")
    public Long nombreAbsence(@Param("idModule") Long idModule, @Param("idEtudiant") Long idEtudiant);
}
