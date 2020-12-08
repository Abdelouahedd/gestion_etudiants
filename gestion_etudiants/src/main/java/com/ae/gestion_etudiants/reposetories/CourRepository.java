package com.ae.gestion_etudiants.reposetories;

import java.util.List;

import com.ae.gestion_etudiants.enteties.Cour;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourRepository extends JpaRepository<Cour, Long> {
    public List<Cour> findByTitreCour(String titreCour);

    @Query("FROM Cour cour WHERE cour.titreCour LIKE %:query")
    public List<Cour> findCourByQuery(@Param("query") String query);

    @Query("DELETE  FROM Cour cour WHERE cour.id = (:idCour)")
    public Cour deleteCourByID(@Param("idCour") Long id);

}
