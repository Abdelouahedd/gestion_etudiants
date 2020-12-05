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
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ElementModule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 50,nullable = false)
    private String nomElement;
    
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Module.class)
    @JoinColumn(name = "idModule")
    private Module module;
    
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Cour.class, mappedBy = "elementModule", fetch = FetchType.LAZY)
    private Collection<Cour> cours;
    
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Prof.class)
    @JoinColumn(name = "idProf")
    private Prof prof;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "elementModule")
    private Collection<Note> notes;
}
