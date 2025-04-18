package org.example.springnotes.service;

import lombok.AllArgsConstructor;
import org.example.springnotes.model.Note;
import org.example.springnotes.repository.InMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryNoteService implements ServiceNotes {

    private InMemoryRepository repository;

    @Override
    public List<Note> findAllNotes() {
        return repository.getAllNotes();
    }

    @Override
    public Note createNote(Note note) {
        return repository.createNote(note);
    }

    @Override
    public Note findNoteById(int id) {
        return repository.getNoteById(id);
    }

    @Override
    public Note updateNote(int id, Note note) {
        return repository.updateNote(id,note);
    }

    @Override
    public void deleteNote(int id) {
        repository.deleteNote(id);
    }
}
