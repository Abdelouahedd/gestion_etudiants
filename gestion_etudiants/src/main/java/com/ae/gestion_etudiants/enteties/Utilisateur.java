package com.ae.gestion_etudiants.enteties;

import com.ae.gestion_etudiants.enumerations.Roles;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public  class Utilisateur {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  
  private Long id;

  private String nom;

  private String prenom;

  @Enumerated(EnumType.STRING)
  private Roles role = Roles.USER;

  private String email;

  private String password;
}