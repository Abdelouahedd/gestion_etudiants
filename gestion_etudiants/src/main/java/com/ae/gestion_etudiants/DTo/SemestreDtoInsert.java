package com.ae.gestion_etudiants.DTo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SemestreDtoInsert {
    private Long filiere;
    private Long niveau;
    private String semestre;
}
