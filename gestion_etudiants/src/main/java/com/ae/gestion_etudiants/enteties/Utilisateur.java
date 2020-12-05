package com.ae.gestion_etudiants.enteties;

import com.ae.gestion_etudiants.enumerations.Roles;

import java.io.Serializable;

import javax.persistence.Column;
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
public class Utilisateur implements Serializable {
  private static final long serialVersionUID = -778185407297443515L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(length = 20, nullable = false)
  private String nom;
  
  @Column(length = 20, nullable = false)
  private String prenom;
  
  @Enumerated(EnumType.STRING)
  private Roles role = Roles.USER;
  
  @Column(length = 50, nullable = false)
  private String email;
  
  @Column(length = 255, nullable = false)
  private String password;
}