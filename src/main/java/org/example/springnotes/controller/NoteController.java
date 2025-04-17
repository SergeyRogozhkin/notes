package org.example.springnotes.controller;

import org.example.springnotes.model.Note;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public interface NoteController {

    ResponseEntity<List<Note>> getAllNotes();
    ResponseEntity<Note> createNote(Note note);
    ResponseEntity<Note> updateNote(Note note);
    ResponseEntity<Note> getNoteById(int id);
    ResponseEntity<Note> deleteNote(Note note);
}
