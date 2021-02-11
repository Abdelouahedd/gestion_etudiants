package com.ae.gestion_etudiants.services;

import com.ae.gestion_etudiants.DTo.NoteDash;
import com.ae.gestion_etudiants.DTo.NoteDto;
import com.ae.gestion_etudiants.enteties.ElementModule;
import com.ae.gestion_etudiants.enteties.Etudiant;
import com.ae.gestion_etudiants.enteties.Note;
import com.ae.gestion_etudiants.reposetories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteRepository noteRepository;
    private EtudiantService etudiantService;
    private ElementModuleService elementModuleService;

    @Autowired
    public NoteService(NoteRepository noteRepository, EtudiantService etudiantService, ElementModuleService elementModuleService) {
        this.noteRepository = noteRepository;
        this.etudiantService = etudiantService;
        this.elementModuleService = elementModuleService;
    }

    public Note ajouNote(Note note) throws Exception {
        return this.noteRepository.save(note);
    }

    public  List<NoteDash>getNoteDash(){
        return this.noteRepository.getNotesForEachModule();
    }

    public Note ajouNote(NoteDto note) throws Exception {
        Etudiant etudiant = this.etudiantService.gEtudiant(note.getIdEt());
        ElementModule elementModule = this.elementModuleService.gElementModule(note.getIdElemnt());
        Note note1 = new Note();
        note1.setNoteModule(note.getNote());
        note1.setEtudiant(etudiant);
        note1.setElementModule(elementModule);

        return this.noteRepository.save(note1);
    }

    public Note modifierNote(Long id, Note note) throws Exception {
        if (id == null)
            throw new Exception("id du note est obligatoir");
        note.setId(id);
        return this.noteRepository.save(note);
    }

    public Note getNoteByIdModule(Long idModule) throws Exception {
        if (idModule == null)
            throw new Exception("id du module est obligatoir");
        Note note = this.getNoteByIdModule(idModule);
        if (note == null)
            throw new Exception("LE note du module not found");
        return note;
    }

    public List<Note> getNotesOfEtudiant(Long idEtudiant) throws Exception {
        if (idEtudiant == null)
            throw new Exception("id de l'etudiant est obligatoir");
        List<Note> notes = this.noteRepository.findNotesByEtudiant(idEtudiant);
        return notes;
    }

}
