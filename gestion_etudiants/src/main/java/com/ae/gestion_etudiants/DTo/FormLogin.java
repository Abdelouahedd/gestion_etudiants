package com.ae.gestion_etudiants.DTo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FormLogin {
    private String email;
    private String password;
}