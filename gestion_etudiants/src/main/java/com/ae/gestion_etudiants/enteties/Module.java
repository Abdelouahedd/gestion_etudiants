package com.ae.gestion_etudiants.enteties;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Module implements Serializable {

  private static final long serialVersionUID = 3282706209911752841L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Le nom du module est obligatoire !!")
  @NotBlank(message = "Le nom du module est obligatoire !!")
  @Size(max = 50, min = 3, message = "Le longeur du  nom de module est entre 3 char et 50 char")
  @Column(length = 50, nullable = false)
  private String libelle;

  @ToString.Exclude
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @ManyToOne(cascade = CascadeType.ALL, targetEntity = Semestre.class)
  @JoinColumn(name = "idSemestre", nullable = false)
  private Semestre semestre;

/*  @ManyToOne(cascade = CascadeType.ALL, targetEntity = Filiere.class)
  @JoinColumn(name = "idFiliere",nullable = false)
  private Filiere filiere;*/

  @OneToMany(cascade = CascadeType.ALL, targetEntity = ElementModule.class, mappedBy = "module", fetch = FetchType.LAZY)
  private Collection<ElementModule> elementModules;

  @OneToMany(targetEntity = Absence.class, mappedBy = "module")
  private Collection<Absence> absences;

}