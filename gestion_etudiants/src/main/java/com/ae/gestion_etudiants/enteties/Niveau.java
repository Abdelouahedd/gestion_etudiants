package com.ae.gestion_etudiants.enteties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
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
    private Collection<Semestre> semestres = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Filiere.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "idFiliere", nullable = false)
    private Filiere filiere;

    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "niveau")
    private Collection<Etudiant> etudiants = new HashSet<>();
}
