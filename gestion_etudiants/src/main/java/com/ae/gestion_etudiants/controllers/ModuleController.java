package com.ae.gestion_etudiants.controllers;

import java.util.List;

import javax.validation.Valid;

import com.ae.gestion_etudiants.enteties.Module;
import com.ae.gestion_etudiants.services.ModuleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/module")
public class ModuleController {
    private ModuleService moduleService;

    @Autowired
    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @PostMapping
    public Module ajouterModule(@Valid @RequestBody Module entity) throws Exception {
        return this.moduleService.ajoModule(entity);
    }

    @GetMapping(path = "/filiere/{id}")
    public List<Module> gModulesByFiliere(@PathVariable("id") Long id) throws Exception {
        return this.moduleService.listModulesByFiliere(id);
    }

    @GetMapping(path = "/semestre/{id}")
    public List<Module> gModulesBySemestre(@PathVariable("id") Long id) throws Exception {
        return this.moduleService.listModulesBySemester(id);
    }

}
