package com.ae.gestion_etudiants.enteties;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
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
  private static final long serialVersionUID = 8422167425794132721L;
  
    @Column(length = 50,nullable = false)
    private String cne;
    
    @OneToMany(targetEntity = Note.class, mappedBy = "etudiant")
    private Collection<Note> notes;
    
    @OneToMany(targetEntity = Service.class, mappedBy = "etudiant")
    private List<Service> listServices;
    
    @OneToMany(targetEntity = Absence.class, mappedBy = "etudiant")
    private Collection<Absence> absences;
}