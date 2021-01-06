package com.ae.gestion_etudiants.DTo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleInsert {
    private Long semestre;
    private String libelle;
}
