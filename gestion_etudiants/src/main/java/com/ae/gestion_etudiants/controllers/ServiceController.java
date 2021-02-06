package com.ae.gestion_etudiants.controllers;

import java.util.List;

import com.ae.gestion_etudiants.DTo.ServiceStudent;
import com.ae.gestion_etudiants.enteties.ServiceDemander;
import com.ae.gestion_etudiants.services.ServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/services")
public class ServiceController {
    private ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public ServiceDemander demandService(@RequestBody ServiceStudent service) throws Exception {
        return this.serviceService.demandService(service);
    }

    @GetMapping
    public List<ServiceDemander> getServicesDemader() {
        return this.serviceService.getServicesDemader();
    }

}
