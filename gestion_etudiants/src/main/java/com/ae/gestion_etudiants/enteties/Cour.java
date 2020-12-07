package com.ae.gestion_etudiants.enteties;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
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
public class Cour implements Serializable {

  private static final long serialVersionUID = -8967118540473153012L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "LE nom du cour !!!")
  @NotBlank(message = "LE nom du cour !!!")
  @Column(length = 30, nullable = false)

  private String titreCour;

  @NotNull(message = "LE nombre d'heure du cour !!!")
  @NotBlank(message = "LE  nombre d'heure du  cour !!!")
  @Min(4)
  @Column(length = 3, nullable = false)

  private Integer nbrHeure;

  @NotNull(message = "LE contenue du cour !!!")
  @NotBlank(message = "LE contenue du cour !!!")
  @Column(nullable = false)

  private String contenue;

  @ManyToOne(cascade = CascadeType.ALL, targetEntity = ElementModule.class)
  @JoinColumn(name = "idElement", nullable = false)
  
  private ElementModule elementModule;

}