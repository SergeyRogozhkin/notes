package org.example.springnotes;

import org.example.springnotes.controller.InMemoryNoteController;
import org.example.springnotes.model.Note;
import org.example.springnotes.service.InMemoryNoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestInMemoryNoteController {

    @Mock
    private InMemoryNoteService service;

    @InjectMocks
    private InMemoryNoteController controller;

    @Test
    void getAllNotes_WhenNotesExist_ReturnsNoteList() {
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
        when(service.findAllNotes()).thenReturn(expectedNotes);

        ResponseEntity<List<Note>> response = controller.getAllNotes();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("Note 1", response.getBody().get(0).getName());
        assertEquals("Text of note 1", response.getBody().get(0).getText());
        assertEquals("Note 2", response.getBody().get(1).getName());
        assertEquals("Text of note 2", response.getBody().get(1).getText());

        verify(service).findAllNotes();
    }

    @Test
    void createNote_WhenValidNoteProvided_ReturnsCreatedNote() {
        Note note = new Note();
        note.setId(1);
        note.setName("Note 1");
        note.setText("Text of note 1");
        when(service.createNote(note)).thenReturn(note);

        ResponseEntity<Note> response = controller.createNote(note);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Note 1", response.getBody().getName());
        assertEquals("Text of note 1", response.getBody().getText());

        verify(service).createNote(note);
    }

    @Test
    void updateNote_WhenNoteExists_ReturnsUpdatedNote() {
        Note updatedNote = new Note();
        updatedNote.setId(1);
        updatedNote.setName("Updated Name");
        updatedNote.setText("Updated Text");

        when(service.updateNote(1, updatedNote)).thenReturn(updatedNote);

        ResponseEntity<Note> response = controller.updateNote(1, updatedNote);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals("Updated Name", response.getBody().getName());
        assertEquals("Updated Text", response.getBody().getText());

        verify(service).updateNote(1, updatedNote);
    }

    @Test
    void getNoteById_WhenNoteExists_ReturnsNote() {
        int id = 1;
        Note expectedNote = new Note();
        expectedNote.setId(id);
        expectedNote.setName("Test Name");
        expectedNote.setText("Test Text");

        when(service.findNoteById(id)).thenReturn(expectedNote);

        ResponseEntity<Note> response = controller.getNoteById(id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Name", response.getBody().getName());
        assertEquals("Test Text", response.getBody().getText());
        assertEquals(1, response.getBody().getId());

        verify(service).findNoteById(id);
    }

    @Test
    void getNoteById_WhenNoteNotFound_ReturnsOkWithNullBody() {
        int id = 999;
        when(service.findNoteById(id)).thenReturn(null);  // Возвращается null

        ResponseEntity<Note> response = controller.getNoteById(id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());  // Ожидается OK
        assertNull(response.getBody());  // Но тело — null

        verify(service).findNoteById(id);
    }

    @Test
    void deleteNote_WhenNoteExists_ReturnsOk() {
        int id = 1;
        ResponseEntity<Note> response = controller.deleteNote(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(service).deleteNote(id);
    }

    @Test
    void deleteNote_WhenNoteDoesNotExist_ReturnsOk() {
        int id = 999;
        doNothing().when(service).deleteNote(id);// Мокаем поведение сервиса, например, что сервис не выбрасывает исключение

        ResponseEntity<Note> response = controller.deleteNote(id);

        assertNotNull(response);  // Ответ не null
        assertEquals(HttpStatus.OK, response.getStatusCode());  // Статус должен быть 200 OK

        verify(service).deleteNote(id);
    }


}
