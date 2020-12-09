package com.ae.gestion_etudiants.controllers;

import java.util.List;

import com.ae.gestion_etudiants.enteties.ServiceDemander;
import com.ae.gestion_etudiants.services.ServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/services")
public class ServiceController {
    private ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public ServiceDemander demandService(ServiceDemander service) throws Exception {
        return this.serviceService.demandService(service);
    }

    @GetMapping
    public List<ServiceDemander> getServicesDemader() {
        return this.serviceService.getServicesDemader();
    }

}
