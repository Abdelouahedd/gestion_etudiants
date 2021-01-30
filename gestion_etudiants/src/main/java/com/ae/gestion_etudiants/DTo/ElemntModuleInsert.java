package com.ae.gestion_etudiants.DTo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ElemntModuleInsert {
    private Long filiere;
    private Long Niveau;
    private Long Semestre;
    private Long Module;
    private String element_module;
    private Long prof;
}
