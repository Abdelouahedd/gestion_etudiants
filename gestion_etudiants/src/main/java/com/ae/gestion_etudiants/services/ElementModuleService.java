package com.ae.gestion_etudiants.services;

import com.ae.gestion_etudiants.DTo.ElemntModuleInsert;
import com.ae.gestion_etudiants.enteties.ElementModule;
import com.ae.gestion_etudiants.enteties.Module;
import com.ae.gestion_etudiants.enteties.Prof;
import com.ae.gestion_etudiants.reposetories.ElementModuleRepository;
import com.ae.gestion_etudiants.reposetories.ModuleRepository;
import com.ae.gestion_etudiants.reposetories.ProfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementModuleService {
    private ElementModuleRepository elementModuleRepository;
    private ModuleRepository moduleRepository;
    private ProfRepository profRepository;

    @Autowired
    public ElementModuleService(ElementModuleRepository elementModuleRepository, ModuleRepository moduleRepository, ProfRepository profRepository) {
        this.elementModuleRepository = elementModuleRepository;
        this.moduleRepository = moduleRepository;
        this.profRepository = profRepository;
    }

    public ElementModule ajouteElementModule(ElemntModuleInsert elemntModuleInsert) throws Exception {
        if (elemntModuleInsert.getNomElement() == null) throw new Exception("Le nom du l'element");
        ElementModule elementModule = getElemModule(elemntModuleInsert);
        return this.elementModuleRepository.save(elementModule);
    }

    private ElementModule getElemModule(ElemntModuleInsert elemntModuleInsert) {
        Prof prof = this.profRepository.findByCin(elemntModuleInsert.getProf()).get();
        Module module = this.moduleRepository.findById(elemntModuleInsert.getModule()).get();
        ElementModule elementModule = constructElementModule(elemntModuleInsert, prof, module);
        return elementModule;
    }

    private ElementModule constructElementModule(ElemntModuleInsert elemntModuleInsert, Prof prof, Module module) {
        ElementModule elementModule = new ElementModule();
        elementModule.setNomElement(elemntModuleInsert.getNomElement());
        elementModule.setModule(module);
        elementModule.setProf(prof);
        return elementModule;
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
