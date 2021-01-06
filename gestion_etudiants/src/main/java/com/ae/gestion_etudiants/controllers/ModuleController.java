package com.ae.gestion_etudiants.controllers;

import com.ae.gestion_etudiants.DTo.ModuleInsert;
import com.ae.gestion_etudiants.enteties.Module;
import com.ae.gestion_etudiants.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/module")
public class ModuleController {
    private ModuleService moduleService;

    @Autowired
    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> ajouterModule(@RequestBody ModuleInsert entity) {
        try {
            Module m = this.moduleService.ajoModule(entity);
            return ResponseEntity.ok().body(m);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

    }

    @GetMapping(path = "/semestre/{id}")
    public List<Module> gModulesBySemestre(@PathVariable("id") Long id) throws Exception {
        return this.moduleService.listModulesBySemester(id);
    }

}
