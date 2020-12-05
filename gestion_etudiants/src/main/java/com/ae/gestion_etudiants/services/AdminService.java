package com.ae.gestion_etudiants.services;

import com.ae.gestion_etudiants.enteties.Admin;
import com.ae.gestion_etudiants.enumerations.Roles;
import com.ae.gestion_etudiants.reposetories.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean login(String email, String password) {
        Admin admin = this.adminRepository.findByEmail(email);
        if (admin != null)
            return admin.getPassword().equals(password);
        return false;
    }

    public Admin ajoAdmin(Admin admin) throws Exception {
        Admin existAdmin = this.adminRepository.findByEmail(admin.getEmail());
        if (existAdmin != null) {
            throw new Exception("email " + admin.getEmail() + " deja exist");
        } else {
            admin.setRole(Roles.ADMIN);
            Admin ad = this.adminRepository.save(admin);
            return ad;
        }
    }
}
