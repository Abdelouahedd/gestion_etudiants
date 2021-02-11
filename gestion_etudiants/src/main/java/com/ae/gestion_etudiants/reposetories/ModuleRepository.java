package com.ae.gestion_etudiants.reposetories;

import java.util.List;
import java.util.Optional;

import com.ae.gestion_etudiants.enteties.Module;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Query("from Module m where m.semestre.id = :id")
    public List<Module> findAllModuleBySemestre(@Param("id") Long idSemester);

    /*@Query("from Module where e  ")*/
   /* public Optional<Module>findByElemnAndElementModule(@Param("el") Long el);
*/
}
