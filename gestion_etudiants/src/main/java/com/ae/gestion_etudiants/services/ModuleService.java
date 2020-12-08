package com.ae.gestion_etudiants.services;

import java.util.List;

import com.ae.gestion_etudiants.enteties.Module;
import com.ae.gestion_etudiants.reposetories.ModuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {
    private ModuleRepository moduleRepository;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public Module ajoModule(Module module) throws Exception {
        if (module.getLibelle() == null)
            throw new Exception("Le nom du module est obligatoire");
        return this.moduleRepository.save(module);
    }

    public List<Module> listModulesByFiliere(Long idFiliere) throws Exception {
        if (idFiliere == null)
            throw new Exception("Id est obligatoir !!");
        return this.moduleRepository.findAllModuleByFiliere(idFiliere);
    }

    public List<Module> listModulesBySemester(Long idSemester) throws Exception {
        if (idSemester == null)
            throw new Exception("Id est obligatoir !!");
        return this.moduleRepository.findAllModuleBySemestre(idSemester);
    }

}
