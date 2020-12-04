package com.ae.gestion_etudiants.enteties;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Etudiant extends Utilisateur {
    private String cne;
    @OneToMany(targetEntity = Note.class, mappedBy = "etudiant")
    private Collection<Note> notes;
    @OneToMany(targetEntity = Service.class, mappedBy = "etudiant")
    private List<Service> listServices;
    @OneToMany(targetEntity = Absence.class, mappedBy = "etudiant")
    private Collection<Absence> absences;
}