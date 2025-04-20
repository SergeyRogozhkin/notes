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
public class TestInMemoryNoteService {
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
        note1.setName("Note 1");
        note1.setText("Text of note 1");
        Note note2 = new Note();
        note2.setId(2);
        note2.setName("Note 2");
        note2.setText("Text of note 2");
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
        note.setName("Note 1");
        note.setText("Text of note 1");
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
        note.setName("Note 1");
        note.setText("Text of note 1");
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
        updatedNote.setName("Note 1");
        updatedNote.setText("Text of note 1");

        Note existingNote = new Note();
        existingNote.setId(id);
        existingNote.setName("Old name");
        existingNote.setText("Old text");

        when(repository.getNoteById(id)).thenReturn(existingNote);
        when(repository.updateNote(id, updatedNote)).thenReturn(updatedNote);

        Note result = service.updateNote(id, updatedNote);

        assertEquals(updatedNote, result);
        verify(repository).getNoteById(id);
        verify(repository).updateNote(id, updatedNote);
    }

    @Test
    void deleteNote_callsRepositoryDelete() {
        int id = 1;

        service.deleteNote(id);

        verify(repository).deleteNote(id);
    }
}
