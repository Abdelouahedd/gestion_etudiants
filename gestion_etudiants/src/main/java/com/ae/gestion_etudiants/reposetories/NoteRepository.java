package com.ae.gestion_etudiants.reposetories;

import java.util.List;

import com.ae.gestion_etudiants.enteties.Note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("from Note note where note.elementModule.id = (:idEl)")
    public Note getNoteByIdElement(@Param("idEl") Long id);

    @Query("from Note note where note.etudiant.id = (:idEtudiant)")
    public List<Note> findNotesByEtudiant(@Param("idEtudiant") Long id);

}
