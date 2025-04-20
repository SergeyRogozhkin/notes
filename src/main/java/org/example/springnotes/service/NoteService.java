package org.example.springnotes.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.springnotes.exeption.NoteNotFoundException;
import org.example.springnotes.model.Note;
import org.example.springnotes.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteService implements ServiceNotes {

    private final NoteRepository repository;


    @Override
    public List<Note> findAllNotes() {
        return repository.findAll();
    }

    @Override
    public Note createNote(Note noteEntity) {
        return repository.save(noteEntity);
        //todo непредвиденная ошибка из-за валидации заметок.
        // Но пока бросаю непредвиденную без конкретики по ограничениям
    }

    @Override
    public Note findNoteById(int id) {
        return repository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
    }

    @Override
    public Note updateNote(int id, Note note) {
        if (!repository.existsById(id)) {
            throw new NoteNotFoundException(id);
        }
        note.setId(id);
        return repository.save(note);
    }

    @Override
    public void deleteNote(int id) {
        if (!repository.existsById(id)) {
            throw new NoteNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
