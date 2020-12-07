package com.ae.gestion_etudiants.services;

import java.util.List;

import com.ae.gestion_etudiants.enteties.Cour;
import com.ae.gestion_etudiants.reposetories.CourRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourService {

    private CourRepository courRepository;

    @Autowired
    public CourService(CourRepository courRepository) {
        this.courRepository = courRepository;
    }

    public Cour ajouterCour(Cour cour) throws Exception {
        if (cour.getContenue() == null) {
            throw new Exception("Contenue du cour est obligatoir !!");
        } else if (cour.getTitreCour() == null) {
            throw new Exception("Titre du cour est obligatoir !!");
        }
        Cour newCour = this.courRepository.save(cour);
        return newCour;
    }

    public Cour ModifierCour(Long id, Cour cour) throws Exception {
        if (id == null) {
            throw new Exception("ID est obligatoir !!");
        }
        if (cour.getContenue() == null) {
            throw new Exception("Contenue du cour est obligatoir !!");
        } else if (cour.getTitreCour() == null) {
            throw new Exception("Titre du cour est obligatoir !!");
        }
        cour.setId(id);
        Cour newCour = this.courRepository.save(cour);
        return newCour;
    }

    public List<Cour> getCourParTitre(String titre) {
        List<Cour> listCours = this.courRepository.findByTitreCour(titre);
        return listCours;
    }

    public List<Cour> recherCourByQuery(String param) {
        List<Cour> cours = this.courRepository.findCourByQuery(param);
        return cours;
    }

    public Cour deleteCour(Long id) throws Exception {
        if (id == null)
            throw new Exception("id du cour est obligatoir !!");
        Cour deletedCour = this.courRepository.deleteCourByID(id);
        if (deletedCour == null)
            throw new Exception("Un problem lors de la supression du cour id" + id + "!!");

        return deletedCour;
    }

}
