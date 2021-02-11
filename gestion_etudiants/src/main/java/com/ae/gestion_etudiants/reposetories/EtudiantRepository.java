package com.ae.gestion_etudiants.reposetories;

import com.ae.gestion_etudiants.DTo.EtudiantDTO;
import com.ae.gestion_etudiants.enteties.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    public Etudiant findByCne(String cne);

    @Query("select count(distinct id) from Etudiant ")
    Long countEtudiant();

    @Query("select new com.ae.gestion_etudiants.DTo.EtudiantDTO(e.id,e.cne,e.nom,e.prenom,e.email) from Etudiant e,Niveau n,Semestre s,Module m,ElementModule el\n" +
            "where e.niveau.id = n.id\n" +
            "and s.niveau.id = n.id\n" +
            "and m.semestre.id = s.id\n" +
            "and el.module.id = m.id\n" +
            "and el.id = (:id_elemnt)")
    List<EtudiantDTO> findEtudiantByModule(@Param("id_elemnt") Long idElemnt);
}
