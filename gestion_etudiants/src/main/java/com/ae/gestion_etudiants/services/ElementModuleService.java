package com.ae.gestion_etudiants.services;

import java.util.List;

import com.ae.gestion_etudiants.enteties.ElementModule;
import com.ae.gestion_etudiants.reposetories.ElementModuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElementModuleService {
    private ElementModuleRepository elementModuleRepository;

    @Autowired
    public ElementModuleService(ElementModuleRepository elementModuleRepository) {
        this.elementModuleRepository = elementModuleRepository;
    }

    public ElementModule ajouteElementModule(ElementModule elementModule) throws Exception {
        if (elementModule.getNomElement() == null)
            throw new Exception("Le nom du l'element");

        return this.elementModuleRepository.save(elementModule);
    }

    public ElementModule modifierElementModule(Long id, ElementModule elementModule) throws Exception {
        if (id == null)
            throw new Exception("Id est obligatoir");

        elementModule.setId(id);
        return this.elementModuleRepository.save(elementModule);
    }

    public ElementModule gElementModule(Long id) throws Exception {
        if (id == null)
            throw new Exception("Id est obligatoir");
        return this.elementModuleRepository.findById(id).orElseThrow(() -> new Exception("No module found"));
    }

    public List<ElementModule> listModuleParProf(Long idProf) throws Exception {
        if (idProf == null)
            throw new Exception("Id est obligatoir");

        List<ElementModule> elementModules = this.elementModuleRepository.findByIdProf(idProf);
        return elementModules;
    }

}
