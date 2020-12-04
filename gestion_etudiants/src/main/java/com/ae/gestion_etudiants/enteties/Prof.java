package com.ae.gestion_etudiants.enteties;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Prof extends Utilisateur {

  private String cin;
  @OneToMany(cascade = CascadeType.ALL,mappedBy = "prof")
  private Collection<ElementModule> lElementModules;

}