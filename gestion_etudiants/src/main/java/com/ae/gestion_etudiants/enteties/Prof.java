package com.ae.gestion_etudiants.enteties;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Prof extends Utilisateur {

  private static final long serialVersionUID = -8413377990222373383L;

  @NotNull(message = "Le chemps cin est obligatoir !!")
  @NotBlank(message = "Le chemps cin est obligatoir !!")
  @Column(length = 10, nullable = false)
  private String cin;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "prof")
  private Collection<ElementModule> lElementModules;

}