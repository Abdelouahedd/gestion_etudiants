package com.ae.gestion_etudiants.enteties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Niveau implements Serializable {

    private static final long serialVersionUID = -357396351936344930L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le chemp niveau est obligatoir !!")
    @NotNull(message = "Le chemp niveau est obligatoir !!")
    @Column(length = 50, nullable = false)
    private String niveau;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "niveau", fetch = FetchType.EAGER)
    private Collection<Semestre> semestres;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Filiere.class)
    @JoinColumn(name = "idFiliere", nullable = false)
    private Filiere filiere;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "niveau")
    private Collection<Etudiant> etudiants;
}
