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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Test_InMemoryNoteController {

    @Mock
    private InMemoryNoteService service;

    @InjectMocks
    private InMemoryNoteController controller;

    @Test
    void getAllNotes_successful() {
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
        when(service.findAllNotes()).thenReturn(expectedNotes);

        ResponseEntity<List<Note>> response = controller.getAllNotes();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("Note 1", response.getBody().get(0).getNameNote());
        assertEquals("Text of note 1", response.getBody().get(0).getTextNote());
        assertEquals("Note 2", response.getBody().get(1).getNameNote());
        assertEquals("Text of note 2", response.getBody().get(1).getTextNote());

        verify(service).findAllNotes();
    }

    @Test
    void createNote_successful() {
        Note note = new Note();
        note.setId(1);
        note.setNameNote("Note 1");
        note.setTextNote("Text of note 1");
        when(service.createNote(note)).thenReturn(note);

        ResponseEntity<Note> response = controller.createNote(note);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Note 1", response.getBody().getNameNote());
        assertEquals("Text of note 1", response.getBody().getTextNote());

        verify(service).createNote(note);
    }

    @Test
    void updateNote_successful() {
        //todo апдейт тест
    }

    @Test
    void getNoteById_successful() {
        int id = 1;
        Note expectedNote = new Note();
        expectedNote.setId(id);
        expectedNote.setNameNote("Test Name");
        expectedNote.setTextNote("Test Text");

        when(service.findNoteById(id)).thenReturn(expectedNote);

        ResponseEntity<Note> response = controller.getNoteById(id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Name", response.getBody().getNameNote());
        assertEquals("Test Text", response.getBody().getTextNote());
        assertEquals(1, response.getBody().getId());

        verify(service).findNoteById(id);
    }

    @Test
    void getNoteById_notFound() {
        //todo тест файнд бай айди нот фаунд
    }

    @Test
    void deleteNote_successful() {
        int id = 1;
        ResponseEntity<Note> response = controller.deleteNote(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(service).deleteNote(id);
    }

    @Test
    void deleteNote_notFound() {
        int id = 999;
        doNothing().when(service).deleteNote(id);// Мокаем поведение сервиса, например, что сервис не выбрасывает исключение

        ResponseEntity<Note> response = controller.deleteNote(id);

        assertNotNull(response);  // Ответ не null
        assertEquals(HttpStatus.OK, response.getStatusCode());  // Статус должен быть 200 OK

        verify(service).deleteNote(id);
    }


}
