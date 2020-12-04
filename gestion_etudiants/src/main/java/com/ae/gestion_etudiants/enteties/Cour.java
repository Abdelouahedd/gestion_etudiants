package com.ae.gestion_etudiants.enteties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cour {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titreCour;

  private String nbrHeure;

  private String contenue;

  @ManyToOne(cascade = CascadeType.ALL, targetEntity = ElementModule.class)
  @JoinColumn(name = "idElement")
  private ElementModule elementModule;

}