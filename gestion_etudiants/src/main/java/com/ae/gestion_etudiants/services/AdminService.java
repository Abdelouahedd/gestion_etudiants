package com.ae.gestion_etudiants.services;

import com.ae.gestion_etudiants.enteties.Admin;
import com.ae.gestion_etudiants.enumerations.Roles;
import com.ae.gestion_etudiants.reposetories.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private AdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin ajoAdmin(Admin admin) throws Exception {
        Admin existAdmin = this.adminRepository.findByEmail(admin.getEmail());
        if (existAdmin != null) {
            throw new Exception("email " + admin.getEmail() + " deja exist");
        } else {
            String password = admin.getPassword();
            String hashPass = passwordEncoder.encode(password);
            admin.setPassword(hashPass);
            admin.setRole(Roles.ADMIN);
            Admin ad = this.adminRepository.save(admin);
            return ad;
        }
    }

}
