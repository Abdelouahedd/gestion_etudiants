package com.ae.gestion_etudiants.reposetories;

import com.ae.gestion_etudiants.DTo.NoteDash;
import com.ae.gestion_etudiants.enteties.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("from Note note where note.elementModule.id = (:idEl)")
    public Note getNoteByIdElement(@Param("idEl") Long id);

    @Query("from Note note where note.etudiant.id = (:idEtudiant)")
    public List<Note> findNotesByEtudiant(@Param("idEtudiant") Long id);

    @Query("select new com.ae.gestion_etudiants.DTo.NoteDash(el.nomElement,n.noteModule)\n" +
            "from Etudiant e,\n" +
            "     ElementModule el,\n" +
            "     Note n\n" +
            "where e.id = n.etudiant.id\n" +
            "and el.id = n.elementModule.id")
    List<NoteDash> getNotesForEachModule();

}
