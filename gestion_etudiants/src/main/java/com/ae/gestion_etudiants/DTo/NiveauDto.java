package com.ae.gestion_etudiants.DTo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NiveauDto {
    private String niveau;
    private Long filiere;
}
