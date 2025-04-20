package org.example.springnotes.service;

import lombok.AllArgsConstructor;
import org.example.springnotes.exeption.GlobalExceptionHandler;
import org.example.springnotes.exeption.InvalidNoteDataException;
import org.example.springnotes.exeption.NoteNotFoundException;
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

    public Note createNote(Note note) {
        if (note == null) {
            throw new InvalidNoteDataException("Заметка не может быть null.");
        }
        if (note.getName().equals(" ")) {
            throw new InvalidNoteDataException("Нельзя делать имя 1 пробел");
        }

        if (note.getName().isEmpty()) {
            throw new InvalidNoteDataException("Заголовок заметки не может быть пустым.");
        }
        return repository.createNote(note);
    }

    @Override
    public Note findNoteById(int id) {
        Note note = repository.getNoteById(id);
        if (note == null) {
            throw new NoteNotFoundException(id);
        }
        return note;
    }

    @Override
    public Note updateNote(int id, Note note) {
        if (note == null) {
            throw new InvalidNoteDataException("Заметка не может быть null.");
        }
        Note existingNote = repository.getNoteById(id);
        if (existingNote == null) {
            throw new NoteNotFoundException(id);
        }
        return repository.updateNote(id, note);
    }

    @Override
    public void deleteNote(int id) {
        repository.deleteNote(id);
    }
}
