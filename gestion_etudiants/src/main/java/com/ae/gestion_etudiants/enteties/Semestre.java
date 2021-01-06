package com.ae.gestion_etudiants.enteties;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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
public class Semestre implements Serializable {

  private static final long serialVersionUID = -5108102244325887210L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Le nom du semestre est obligatoir !!")
  @Column(length = 20,nullable = false)
  private String semestre;


  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "semestre")
  private List<Module> lModules;

  @ToString.Exclude
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Niveau.class)
  @JoinColumn(name = "idNiveau")
  private Niveau niveau;

}