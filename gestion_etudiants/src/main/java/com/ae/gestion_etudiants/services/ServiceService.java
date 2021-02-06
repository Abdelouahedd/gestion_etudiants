package com.ae.gestion_etudiants.services;

import com.ae.gestion_etudiants.DTo.ServiceStudent;
import com.ae.gestion_etudiants.enteties.Etudiant;
import com.ae.gestion_etudiants.enteties.ServiceDemander;
import com.ae.gestion_etudiants.reposetories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceService {
    private ServiceRepository serviceRepository;
    private EtudiantService etudiantService;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository, EtudiantService etudiantService) {
        this.serviceRepository = serviceRepository;
        this.etudiantService = etudiantService;
    }

    public ServiceDemander demandService(ServiceStudent service) throws Exception {
        if (service.getContenue() == null) {
            throw new Exception("Le contenue doit ne doit pas etre null");
        }
        Etudiant etudiant = this.etudiantService.gEtudiant(service.getIdEtudiant());
        ServiceDemander serviceDemander = new ServiceDemander();
        serviceDemander.setContenue(service.getContenue());
        serviceDemander.setEtudiant(etudiant);
        return this.serviceRepository.save(serviceDemander);
    }

    public List<ServiceDemander> getServicesDemader() {
        return this.serviceRepository.findAll();
    }

}
