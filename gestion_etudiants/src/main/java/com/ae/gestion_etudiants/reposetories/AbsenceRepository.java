package com.ae.gestion_etudiants.reposetories;

import com.ae.gestion_etudiants.DTo.dashBord.NbrAbsByModule;
import com.ae.gestion_etudiants.DTo.dashBordStudent.DashStudent;
import com.ae.gestion_etudiants.enteties.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    @Query("from Absence  where module.id = (:idModule)")
    public List<Absence> findAbsenceByIdModule(@Param("idModule") Long idModule);

    @Query("select sum(nbrSeanceAbs) from Absence  group by module.id having etudiant.id = (:idEtudiant) AND module.id = (:idModule)")
    public Long nombreAbsence(@Param("idModule") Long idModule, @Param("idEtudiant") Long idEtudiant);

    @Query("select new com.ae.gestion_etudiants.DTo.dashBord.NbrAbsByModule(sum(a.nbrSeanceAbs),m.libelle)" +
            "from Absence a,Module m " +
            "where a.module.id=m.id " +
            "group by m.libelle")
    List<NbrAbsByModule> getnbrAbsByModule();


    @Query("select new com.ae.gestion_etudiants.DTo.dashBordStudent.DashStudent(sum(nbrSeanceAbs),module.libelle) from Absence  group by module.id,etudiant.id having etudiant.id = :id")
    public List<DashStudent> nombreAbsenceByModule(@Param("id") Long idEtudiant);


}
