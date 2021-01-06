package com.ae.gestion_etudiants.services;

import com.ae.gestion_etudiants.DTo.ModuleInsert;
import com.ae.gestion_etudiants.enteties.Module;
import com.ae.gestion_etudiants.enteties.Semestre;
import com.ae.gestion_etudiants.reposetories.ModuleRepository;
import com.ae.gestion_etudiants.reposetories.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {
    private ModuleRepository moduleRepository;
    private SemestreRepository semestreRepository;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository, SemestreRepository semestreRepository) {
        this.moduleRepository = moduleRepository;
        this.semestreRepository = semestreRepository;
    }

    public Module ajoModule(ModuleInsert moduleInsert) throws Exception {
        if (moduleInsert.getLibelle() == null)
            throw new Exception("Le nom du module est obligatoire");
        Semestre s = this.semestreRepository.findById(moduleInsert.getSemestre()).get();
        Module module = new Module();
        module.setLibelle(moduleInsert.getLibelle());
        module.setSemestre(s);

        return this.moduleRepository.save(module);
    }


    public List<Module> listModulesBySemester(Long idSemester) throws Exception {
        if (idSemester == null)
            throw new Exception("Id est obligatoir !!");
        return this.moduleRepository.findAllModuleBySemestre(idSemester);
    }

}
