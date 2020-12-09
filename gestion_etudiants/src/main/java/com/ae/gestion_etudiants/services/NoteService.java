package com.ae.gestion_etudiants.services;

import java.util.List;

import com.ae.gestion_etudiants.enteties.Note;
import com.ae.gestion_etudiants.reposetories.NoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note ajouNote(Note note) throws Exception {
        return this.noteRepository.save(note);

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
