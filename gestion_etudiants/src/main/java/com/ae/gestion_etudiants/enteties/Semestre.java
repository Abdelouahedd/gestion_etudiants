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

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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

  @Column(length = 10,nullable = false)
  private String semestre;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "semestre")
  private List<Module> lModules;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Niveau.class)
  @JoinColumn(name = "idNiveau")
  private Niveau niveau;

}