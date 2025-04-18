package org.example.springnotes.controller;

import lombok.AllArgsConstructor;
import org.example.springnotes.model.Note;
import org.example.springnotes.service.InMemoryNoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
@AllArgsConstructor
public class InMemoryNoteController implements NoteController {

    private InMemoryNoteService service;

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(service.findAllNotes(), HttpStatus.OK);
    }

    @PostMapping("create_note")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return new ResponseEntity<>(service.createNote(note), HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable int id, @RequestBody Note note) {
        return new ResponseEntity<>(service.updateNote(id, note), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable int id) {
        return new ResponseEntity<>(service.findNoteById(id), HttpStatus.OK);
    }

    @DeleteMapping("delete_note/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable int id) {
        service.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
