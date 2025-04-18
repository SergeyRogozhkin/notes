package org.example.springnotes;

import org.example.springnotes.model.Note;
import org.example.springnotes.repository.InMemoryRepository;
import org.example.springnotes.service.InMemoryNoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Test_InMemoryNoteService {
    @Mock
    private InMemoryRepository repository;

    @InjectMocks
    private InMemoryNoteService service;

    @Test
    void findAllNotes_returnsListOfNotes() {
        // Arrange
        List<Note> expectedNotes = new ArrayList<>();
        Note note1 = new Note();
        note1.setId(1);
        note1.setNameNote("Note 1");
        note1.setTextNote("Text of note 1");
        Note note2 = new Note();
        note2.setId(2);
        note2.setNameNote("Note 2");
        note2.setTextNote("Text of note 2");
        expectedNotes.add(note1);
        expectedNotes.add(note2);
        when(repository.getAllNotes()).thenReturn(expectedNotes);

        List<Note> actualNotes = service.findAllNotes();

        assertEquals(expectedNotes, actualNotes);
        verify(repository).getAllNotes();
    }

    @Test
    void createNote_returnsCreatedNote() {
        Note note = new Note();
        note.setId(1);
        note.setNameNote("Note 1");
        note.setTextNote("Text of note 1");
        when(repository.createNote(note)).thenReturn(note);

        Note result = service.createNote(note);

        assertEquals(note, result);
        verify(repository).createNote(note);
    }

    @Test
    void findNoteById_returnsNote() {
        int id = 1;
        Note note = new Note();
        note.setId(id);
        note.setNameNote("Note 1");
        note.setTextNote("Text of note 1");
        when(repository.getNoteById(id)).thenReturn(note);

        Note result = service.findNoteById(id);

        assertEquals(note, result);
        verify(repository).getNoteById(id);
    }

    @Test
    void updateNote_returnsUpdatedNote() {
        int id = 1;
        Note updatedNote = new Note();
        updatedNote.setId(id);
        updatedNote.setNameNote("Note 1");
        updatedNote.setTextNote("Text of note 1");
        when(repository.updateNote(id, updatedNote)).thenReturn(updatedNote);

        Note result = service.updateNote(id, updatedNote);

        assertEquals(updatedNote, result);
        verify(repository).updateNote(id, updatedNote);
    }

    @Test
    void deleteNote_callsRepositoryDelete() {
        int id = 1;

        service.deleteNote(id);

        verify(repository).deleteNote(id);
    }
}
