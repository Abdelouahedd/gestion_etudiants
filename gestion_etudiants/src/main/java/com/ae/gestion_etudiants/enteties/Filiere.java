package com.ae.gestion_etudiants.enteties;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Filiere implements Serializable {

  private static final long serialVersionUID = -5484975962771588469L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
 
  @Column(length = 50,nullable = false)
  private String nomFormation;

  @Column(length = 255,nullable = true)
  private String description;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "filiere", targetEntity = Niveau.class)
  private Collection<Niveau> niveaus;

}