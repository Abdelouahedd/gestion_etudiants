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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Module implements Serializable{

  private static final long serialVersionUID = 3282706209911752841L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(length = 50,nullable = false)
  private String libelle;

  @ManyToOne(cascade = CascadeType.ALL, targetEntity = Semestre.class)
  @JoinColumn(name = "idSemestre")
  private Semestre semestre;

  @ManyToOne(cascade = CascadeType.ALL, targetEntity = Filiere.class)
  @JoinColumn(name = "idFiliere")
  private Filiere filiere;

  @OneToMany(cascade = CascadeType.ALL, targetEntity = ElementModule.class, mappedBy = "module", fetch = FetchType.LAZY)
  private Collection<ElementModule> elementModules;

  @OneToMany(targetEntity = Absence.class, mappedBy = "module")
  private Collection<Absence>absences;

}