package com.ae.gestion_etudiants.reposetories;

import com.ae.gestion_etudiants.enteties.ServiceDemander;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceDemander, Long> {

}
