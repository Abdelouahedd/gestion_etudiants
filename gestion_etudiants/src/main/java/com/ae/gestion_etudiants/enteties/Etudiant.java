package com.ae.gestion_etudiants.enteties;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@PrimaryKeyJoinColumn(name = "id")
public class Etudiant extends Utilisateur {
  private static final long serialVersionUID = 8422167425794132721L;

  @NotBlank(message = "Le chemp CNE est obligatoir !!")
  @NotNull(message = "Le chemp CNE est obligatoir !!")
  @Column(length = 50, nullable = false, unique = true)
  private String cne;

  @OneToMany(targetEntity = Note.class, mappedBy = "etudiant")
  private Collection<Note> notes;

  @OneToMany(targetEntity = ServiceDemander.class, mappedBy = "etudiant")
  private List<ServiceDemander> listServices;

  @OneToMany(targetEntity = Absence.class, mappedBy = "etudiant")
  private Collection<Absence> absences;
}