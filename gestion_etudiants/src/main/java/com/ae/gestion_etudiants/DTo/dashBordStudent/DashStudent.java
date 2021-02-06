package com.ae.gestion_etudiants.DTo.dashBordStudent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DashStudent {
    private Long absence;
    private String module;
}
