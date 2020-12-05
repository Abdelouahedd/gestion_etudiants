package com.ae.gestion_etudiants.enteties;

import javax.persistence.Entity;

import lombok.ToString;

@Entity
@ToString
public class Admin extends Utilisateur {
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        Admin admin = (Admin) o;
        return admin.getEmail().equals(this.getEmail());
    }
}