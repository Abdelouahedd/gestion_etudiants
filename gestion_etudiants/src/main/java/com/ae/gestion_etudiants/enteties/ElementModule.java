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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull(message = "Le nom du module est obigatoir !!")
    @NotBlank(message = "Le nom du module est obigatoir !!")
    @Size(min = 3, max = 50, message = "Le longeur du  d'element est entre 3 char et 50 char")
    @Column(length = 50, nullable = false)
    private String nomElement;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Module.class)
    @JoinColumn(name = "idModule", nullable = false)
    private Module module;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Cour.class, mappedBy = "elementModule", fetch = FetchType.LAZY)
    private Collection<Cour> cours;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Prof.class)
    @JoinColumn(name = "idProf", nullable = false)
    private Prof prof;

    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "elementModule")
    private Collection<Note> notes;*/
}
