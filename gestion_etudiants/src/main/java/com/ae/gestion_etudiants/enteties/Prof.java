package com.ae.gestion_etudiants.enteties;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Prof extends Utilisateur {

  private static final long serialVersionUID = -8413377990222373383L;
  
  @Column(length = 10,nullable = false)
  private String cin;
  
  @OneToMany(cascade = CascadeType.ALL,mappedBy = "prof")
  private Collection<ElementModule> lElementModules;

}