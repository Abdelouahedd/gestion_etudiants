package com.ae.gestion_etudiants.reposetories;

import com.ae.gestion_etudiants.enteties.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    public Admin findByEmail(String email);
}
