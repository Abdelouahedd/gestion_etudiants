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
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Etudiant.class)
    @JoinColumn(name = "idEtudiant", nullable = false)
    private Etudiant etudiant;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Module.class)
    @JoinColumn(name = "idModule", nullable = false)
    private Module module;
    private Long nbrSeanceAbs;
}
