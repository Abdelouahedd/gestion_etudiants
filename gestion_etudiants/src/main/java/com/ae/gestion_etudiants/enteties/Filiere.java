package com.ae.gestion_etudiants.enteties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Filiere implements Serializable {

  private static final long serialVersionUID = -5484975962771588469L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
 
  @NotNull(message = "Le nom du formation est obligatoir !!")
  @NotBlank(message = "Le nom du formation est obligatoir !!")
  @Column(length = 50,nullable = false)
  private String nomFormation;

  @Column(length = 255,nullable = true)
  private String description;

  @ToString.Exclude
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "filiere", targetEntity = Niveau.class,fetch = FetchType.EAGER)
  private Collection<Niveau> niveaus;

}