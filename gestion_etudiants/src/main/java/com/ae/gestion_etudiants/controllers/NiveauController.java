package com.ae.gestion_etudiants.controllers;

import com.ae.gestion_etudiants.DTo.NiveauDto;
import com.ae.gestion_etudiants.enteties.Niveau;
import com.ae.gestion_etudiants.services.NiveauService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/niveau")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class NiveauController {
    private NiveauService niveauService;


    public NiveauController(NiveauService niveauService) {
        this.niveauService = niveauService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> ajoNiveau(@RequestBody NiveauDto niveauDto) {
        try {
            Niveau niveau = this.niveauService.ajoNiveau(niveauDto);
            return ResponseEntity.ok().body(niveau);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @GetMapping(path = "/")
    public ResponseEntity<?> getNiveaux() {
        try {
            List<Niveau> list = this.niveauService.getNiveaux();
            System.out.println("List " + list);
            return ResponseEntity.ok(list);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
        }
    }

}
