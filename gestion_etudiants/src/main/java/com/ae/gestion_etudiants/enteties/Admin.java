package com.ae.gestion_etudiants.enteties;

import javax.persistence.Entity;

import lombok.ToString;

@Entity
@ToString
public class Admin extends Utilisateur {
    private static final long serialVersionUID = 1L;
}