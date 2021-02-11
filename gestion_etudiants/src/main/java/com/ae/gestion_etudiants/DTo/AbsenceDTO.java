package com.ae.gestion_etudiants.DTo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceDTO {
    private Long idEtudiant;
    private Long idElementModule;
    private Long seance;
}
