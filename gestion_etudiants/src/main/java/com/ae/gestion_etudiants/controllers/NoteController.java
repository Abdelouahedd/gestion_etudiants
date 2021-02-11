package com.ae.gestion_etudiants.controllers;

import java.util.List;

import com.ae.gestion_etudiants.DTo.NoteDash;
import com.ae.gestion_etudiants.DTo.NoteDto;
import com.ae.gestion_etudiants.enteties.Note;
import com.ae.gestion_etudiants.services.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/notes")
public class NoteController {
    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping(path = "/elementmodule/{idModule}")
    public Note getNoteByIdElement(@PathVariable("idModule") Long idElem) throws Exception {
        return this.noteService.getNoteByIdModule(idElem);
    }

    @GetMapping(value = "/etudiant/{idEtudiant}")
    public List<Note> getNotesByEtudiant(@PathVariable("idEtudiant") Long idEtudiant) throws Exception {
        return this.noteService.getNotesOfEtudiant(idEtudiant);
    }

    @PostMapping
    public Note ajoutNote(@RequestBody Note note) throws Exception {
        return this.noteService.ajouNote(note);
    }
    @PostMapping(path = "/add")
    public Note ajoutNote(@RequestBody NoteDto note) throws Exception {
        return this.noteService.ajouNote(note);
    }

    @PutMapping(path = "{idNote}")
    public Note modifierNote(@RequestBody Note note, @PathVariable("id") Long id) throws Exception {
        return this.noteService.modifierNote(id, note);
    }

    @GetMapping(value = "/dash")
    public List<NoteDash> getNotesByEtudiant() throws Exception {
        return this.noteService.getNoteDash();
    }

}
