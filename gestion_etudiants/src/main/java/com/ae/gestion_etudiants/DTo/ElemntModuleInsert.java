package com.ae.gestion_etudiants.DTo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ElemntModuleInsert {
    private Long filiere;
    private Long niveau;
    private Long semestre;
    private Long module;
    private String nomElement;
    private String prof;
}
