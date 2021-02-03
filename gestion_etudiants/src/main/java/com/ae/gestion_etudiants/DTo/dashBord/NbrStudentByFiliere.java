package com.ae.gestion_etudiants.DTo.dashBord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class NbrStudentByFiliere {
    private Long nbrStudent;
    private String filiere;
}
