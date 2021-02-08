package com.ae.gestion_etudiants.reposetories;

import com.ae.gestion_etudiants.DTo.dashBord.NbrStudentByFiliere;
import com.ae.gestion_etudiants.enteties.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    @Query(value = "select new com.ae.gestion_etudiants.DTo.dashBord.NbrStudentByFiliere(count(e.id),f.nomFormation)\n" +
            "from Etudiant e,Filiere f,Niveau n\n" +
            "where f.id= n.filiere.id\n" +
            "and e.niveau.id = n.id\n" +
            "group by f.nomFormation")
    List<NbrStudentByFiliere> getNbrStudentByFiliere();

    @Query("SELECT COUNT(DISTINCT id) FROM Filiere")
    Long countFiliere();
}

