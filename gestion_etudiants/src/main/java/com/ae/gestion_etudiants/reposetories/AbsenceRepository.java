package com.ae.gestion_etudiants.reposetories;

import com.ae.gestion_etudiants.enteties.Absence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    @Query("SELECT abs FROM Absence abs where abs.etudiant.id =:idEt")
    public Absence findAbsenceByIdEtudiant(@Param("idEt") Long idEtudiant);

    @Query("SELECT sum(abs.nbrSeanceAbs) FROM Absence abs GROUP BY abs.module.id:= idMod HAVING abs.etudiant.id =:idEt")
    public Long nombreAbsence(@Param("idMod") Long idModule, @Param("idEt") Long idEtudiant);
}
