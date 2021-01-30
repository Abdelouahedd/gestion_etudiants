package com.ae.gestion_etudiants.services;

import java.util.List;

import com.ae.gestion_etudiants.DTo.ElemntModuleInsert;
import com.ae.gestion_etudiants.enteties.*;
import com.ae.gestion_etudiants.enteties.Module;
import com.ae.gestion_etudiants.reposetories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElementModuleService {
    private ElementModuleRepository elementModuleRepository;
    private FiliereRepository filiereRepository;
    private NiveauRepository niveauRepository;
    private SemestreRepository semestreRepository;
    private ModuleRepository moduleRepository;
    private ProfRepository profRepository;
    @Autowired
    public ElementModuleService(ElementModuleRepository elementModuleRepository) {
        this.elementModuleRepository = elementModuleRepository;
    }

    public ElementModule ajouteElementModule(ElemntModuleInsert elemntModuleInsert) throws Exception {
        if (elemntModuleInsert.getElement_module() == null)
            throw new Exception("Le nom du l'element");
        ElementModule elementModule = new ElementModule();
        Prof prof =profRepository.findById(elemntModuleInsert.getProf()).get();
        Module module =moduleRepository.findById(elemntModuleInsert.getModule()).get();
        elementModule.setNomElement(elemntModuleInsert.getElement_module());
        elementModule.setModule(module);
        elementModule.setProf(prof);
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
