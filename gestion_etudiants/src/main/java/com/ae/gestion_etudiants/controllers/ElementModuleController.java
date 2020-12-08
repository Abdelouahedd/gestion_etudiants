package com.ae.gestion_etudiants.controllers;

import java.util.List;

import javax.validation.Valid;

import com.ae.gestion_etudiants.enteties.ElementModule;
import com.ae.gestion_etudiants.services.ElementModuleService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/elementModule")
public class ElementModuleController {
    private ElementModuleService elementModuleService;

    public ElementModuleController(ElementModuleService elementModuleService) {
        this.elementModuleService = elementModuleService;
    }

    @GetMapping(path = "{id}")
    public ElementModule getElementModule(@PathVariable("id") Long id) throws Exception {
        return this.elementModuleService.gElementModule(id);
    }

    @PostMapping
    public ElementModule aElementModule(@Valid @RequestBody ElementModule elementModule) throws Exception {
        return this.elementModuleService.ajouteElementModule(elementModule);
    }

    @GetMapping(path = "prof/{id}")
    public List<ElementModule> getElementModuleByIdProd(@PathVariable("id") Long idProf) throws Exception {
        return this.elementModuleService.listModuleParProf(idProf);
    }

}
