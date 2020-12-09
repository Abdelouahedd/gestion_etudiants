package com.ae.gestion_etudiants.services;

import java.util.List;

import com.ae.gestion_etudiants.enteties.ServiceDemander;
import com.ae.gestion_etudiants.reposetories.ServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {
    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public ServiceDemander demandService(ServiceDemander service) throws Exception {
        if (service.getContenue() == null) {
            throw new Exception("Le contenue doit ne doit pas etre null");
        }
        return this.serviceRepository.save(service);
    }

    public List<ServiceDemander> getServicesDemader() {
        return this.serviceRepository.findAll();
    }

}
