package com.ae.gestion_etudiants;

import com.ae.gestion_etudiants.services.NiveauService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionEtudiantsApplication {

	private final NiveauService niveauService;

	public GestionEtudiantsApplication(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	public static void main(String[] args) {
		SpringApplication.run(GestionEtudiantsApplication.class, args);
	}

}
