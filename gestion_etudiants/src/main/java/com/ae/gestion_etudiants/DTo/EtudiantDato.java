package com.ae.gestion_etudiants.DTo;

import lombok.Data;

@Data
public class EtudiantDato {
    private String cne;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Long niveau;
}
