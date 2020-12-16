package com.ae.gestion_etudiants.reposetories;

import com.ae.gestion_etudiants.enteties.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    public Optional<Utilisateur> findByEmail(String email);
}
