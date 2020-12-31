package com.ae.gestion_etudiants;

import com.ae.gestion_etudiants.services.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionEtudiantsApplication implements CommandLineRunner {

	private final NiveauService niveauService;

	public GestionEtudiantsApplication(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	public static void main(String[] args) {
		SpringApplication.run(GestionEtudiantsApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		this.niveauService.getNiveaux().forEach(niveau -> {
			System.out.println(niveau.toString());
		});
	}
}
