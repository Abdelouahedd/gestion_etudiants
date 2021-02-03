package com.ae.gestion_etudiants.DTo.dashBord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class DashBord {
    private Long numberOfProf;
    private Long numberOfStudent;
    private Long numberOfCours;
    private Long numberOfFiliere;
    private List<NbrStudentByFiliere> nbrStudentByFiliere;
    private List<NbrAbsByModule> nbrAbsByModule;
}
