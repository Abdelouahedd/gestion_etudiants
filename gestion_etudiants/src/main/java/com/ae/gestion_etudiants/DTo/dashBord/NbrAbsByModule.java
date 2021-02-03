package com.ae.gestion_etudiants.DTo.dashBord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class NbrAbsByModule {
    private Long nbrAbsence;
    private String module;
}
