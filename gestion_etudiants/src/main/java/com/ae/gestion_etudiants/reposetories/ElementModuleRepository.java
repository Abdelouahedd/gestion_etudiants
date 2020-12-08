package com.ae.gestion_etudiants.reposetories;

import java.util.List;

import com.ae.gestion_etudiants.enteties.ElementModule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementModuleRepository extends JpaRepository<ElementModule, Long> {

    @Query("from ElementModule el where el.prof.id = (:idProf)")
    public List<ElementModule> findByIdProf(@Param("idProf") Long idProf);

}
