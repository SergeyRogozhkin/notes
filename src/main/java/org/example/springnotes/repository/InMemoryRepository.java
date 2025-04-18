package org.example.springnotes.repository;

import org.example.springnotes.model.Note;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Repository
public class InMemoryRepository {

    private final List<Note> NOTES_IN_MEMORY = new ArrayList<>();


    public List<Note> getAllNotes() {
        return NOTES_IN_MEMORY;
    }

    public Note createNote(Note note) {
        NOTES_IN_MEMORY.add(note);
        return note;
    }

    public Note getNoteById(int id) {
        return NOTES_IN_MEMORY.stream()
                .filter(element -> element.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Note updateNote(int id, Note note) {

        //проверить на нул
        Note note1 = NOTES_IN_MEMORY.get(id);
        if (note1 != null) {
            NOTES_IN_MEMORY.set(id, note);
        }
        return note1;
    }

    public void deleteNote(int id) {
        Note note = getNoteById(id);
        if (note != null) {
            NOTES_IN_MEMORY.remove(note);
        }
    }

}
