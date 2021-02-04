package com.ae.gestion_etudiants.enteties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

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

/*  @OneToMany(targetEntity = Note.class, mappedBy = "etudiant")
  private Collection<Note> notes;*/

 /* @OneToMany(targetEntity = ServiceDemander.class, mappedBy = "etudiant")
  private List<ServiceDemander> listServices;*/

 /* @OneToMany(targetEntity = Absence.class, mappedBy = "etudiant")
  private Collection<Absence> absences;*/

 /* @ToString.Exclude
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)*/
  @ManyToOne(targetEntity = Niveau.class)
  @JoinColumn(name = "idNiveau", nullable = false)
  private Niveau niveau;
}