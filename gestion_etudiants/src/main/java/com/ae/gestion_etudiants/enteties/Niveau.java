package com.ae.gestion_etudiants.enteties;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Niveau implements Serializable{
   
    private static final long serialVersionUID = -357396351936344930L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Column(length = 2,nullable = false)
    private Integer niveau;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "niveau", fetch = FetchType.LAZY)
    private Collection<Semestre> semestres;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Filiere.class)
    @JoinColumn(name = "idFiliere")
    private Filiere filiere;
}
