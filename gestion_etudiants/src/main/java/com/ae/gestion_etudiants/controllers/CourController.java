package com.ae.gestion_etudiants.controllers;

import java.util.List;

import javax.validation.Valid;

import com.ae.gestion_etudiants.enteties.Cour;
import com.ae.gestion_etudiants.services.CourService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/api/cour")
public class CourController {
    private CourService courService;

    @Autowired
    public CourController(CourService courService) {
        this.courService = courService;
    }

    @PostMapping
    public Cour ajouCour(@Valid @RequestBody Cour c) throws Exception {
        Cour cour = this.courService.ajouterCour(c);
        return cour;
    }

    @PutMapping(path = "{id}")
    public Cour mofiCour(@PathVariable("id") Long id, @Valid @RequestBody Cour c) throws Exception {
        Cour cour = this.mofiCour(id, c);
        return cour;
    }

    @GetMapping(path = "/byTitre")
    public List<Cour> getCoursParTitre(@RequestBody String titre) {
        List<Cour> list = this.courService.getCourParTitre(titre);
        return list;
    }

    @GetMapping(value = "/")
    public List<Cour> getCoursByQuery(@RequestParam("titre") String titre) {
        List<Cour> cours = this.courService.recherCourByQuery(titre);
        return cours;
    }

    @DeleteMapping(value = "{id}")
    public Cour suprimerCour(@PathVariable("id") Long param) throws Exception {
        Cour cour = this.courService.deleteCour(param);
        return cour;
    }

}
