package org.example.springnotes.service;

import org.example.springnotes.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceNotes {
    List<Note> findAllNotes();
    Note createNote(Note noteEntity);
    Note findNoteById(int id);
    Note updateNote(int id, Note note);
    void deleteNote(int id);
}
