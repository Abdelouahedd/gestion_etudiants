package com.ae.gestion_etudiants.controllers;

import javax.validation.Valid;

import com.ae.gestion_etudiants.enteties.Admin;
import com.ae.gestion_etudiants.services.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RequestMapping(path = "/api/admin")
@RestController
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(path = "/login")
    public boolean login(@RequestBody AdminForm adminForm) {
        return this.adminService.login(adminForm.getEmail(), adminForm.getPassword());
    }

    @PostMapping(path = "/signUP")
    public Admin register(@Valid @RequestBody Admin admin) throws Exception {
        Admin newAdmin = this.adminService.ajoAdmin(admin);
        return newAdmin;
    }
}

@Data
class AdminForm {
    private String email;
    private String password;
}
