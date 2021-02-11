package com.ae.gestion_etudiants.DTo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDTO {
    private Long id;
    private String cne;
    private String nom;
    private String prenom;
    private String email;

}
