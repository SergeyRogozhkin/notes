package org.example.springnotes.repository;

import org.example.springnotes.model.Note;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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

    public Note updateNote(Note note) {
        int noteIndex = IntStream.range(0, NOTES_IN_MEMORY.size())
                .filter(index -> NOTES_IN_MEMORY.get(index).getNameNote().equals(note.getNameNote()))
                .findFirst()
                .orElse(-1);
        if (noteIndex != -1) {
            NOTES_IN_MEMORY.set(noteIndex, note);
            return note;
        }
        return null;
    }

    public void deleteNote(int id) {
        Note note = getNoteById(id);
        if (note != null) {
            NOTES_IN_MEMORY.remove(note);
        }
    }

}
