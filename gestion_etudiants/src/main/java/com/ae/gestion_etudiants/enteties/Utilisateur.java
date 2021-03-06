package com.ae.gestion_etudiants.enteties;

import com.ae.gestion_etudiants.enumerations.Roles;
import com.fasterxml.jackson.annotation.JsonProperty;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utilisateur implements Serializable {
  private static final long serialVersionUID = -778185407297443515L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "le nom est obligatoir")
  @Size(min = 2, max = 20, message = "Le longeur du nom est entre 5 char et 20 char")
  @Column(length = 20, nullable = false)
  private String nom;

  @NotBlank(message = "le prenom est obligatoir")
  @Size(min = 2, max = 20, message = "Le longeur du prenom est entre 5 char et 20 char")
  @Column(length = 20, nullable = false)
  private String prenom;

  @Enumerated(EnumType.STRING)
  private Roles role = Roles.USER;

  @NotBlank(message = "l'email est obligatoir")
  @Size(max = 50, message = "Le longeur de l'email doit etre < 50 char ")
  @Email(message = "Invalide email")

  @Column(length = 50, nullable = false, unique = true)
  private String email;

  @NotBlank(message = "le mot de passe est obligatoir")
  @Column(length = 255, nullable = false)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

}