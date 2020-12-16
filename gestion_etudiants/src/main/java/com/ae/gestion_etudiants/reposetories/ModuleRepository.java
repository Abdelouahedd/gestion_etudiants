package com.ae.gestion_etudiants.reposetories;

import java.util.List;

import com.ae.gestion_etudiants.enteties.Module;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    @Query("from Module m where m.filiere.id = :id")
    public List<Module> findAllModuleByFiliere(@Param("id") Long idFiliere);

    @Query("from Module m where m.semestre.id = :id")
    public List<Module> findAllModuleBySemestre(@Param("id") Long idSemester);

}