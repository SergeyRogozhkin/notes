package org.example.springnotes;

import org.example.springnotes.controller.NoteController;
import org.example.springnotes.model.Note;
import org.example.springnotes.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TestNoteController {

    @Mock
    private NoteService noteService;

    @InjectMocks
    private NoteController noteController;

    @Test
    public void getAllNotes_WhenNotesExist_ReturnsNoteList() {
        // Создаем несколько заметок для теста
        Note note1 = new Note();
        note1.setId(1);
        note1.setName("Note 1");
        note1.setText("Some text");

        Note note2 = new Note();
        note2.setId(2);
        note2.setName("Note 2");
        note2.setText("Another text");

        List<Note> notes = Arrays.asList(note1, note2); // Список заметок

        // Мокаем поведение сервиса, чтобы вернуть этот список
        when(noteService.findAllNotes()).thenReturn(notes);

        // Вызываем метод контроллера напрямую
        ResponseEntity<List<Note>> response = noteController.getAllNotes();

        // Проверяем статус ответа
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Проверяем тело ответа (список заметок)
        List<Note> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(2, responseBody.size()); // Проверяем количество заметок
        assertEquals("Note 1", responseBody.get(0).getName()); // Проверяем имя первой заметки
        assertEquals("Note 2", responseBody.get(1).getName()); // Проверяем имя второй заметки
    }

    @Test
    public void createNote_WhenValidNoteProvided_ReturnsCreatedNote() {
        // Создаем заметку для теста
        Note newNote = new Note();
        newNote.setName("New Note");
        newNote.setText("This is a new note");

        // Мокаем сервис, чтобы он вернул эту заметку при вызове createNote
        when(noteService.createNote(newNote)).thenReturn(newNote);

        // Вызываем метод контроллера напрямую
        ResponseEntity<Note> response = noteController.createNote(newNote);

        // Проверяем статус ответа (должен быть CREATED)
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Проверяем тело ответа (должна быть созданная заметка)
        Note createdNote = response.getBody();
        assertNotNull(createdNote); // Проверяем, что заметка не null
        assertEquals("New Note", createdNote.getName()); // Проверяем имя новой заметки
        assertEquals("This is a new note", createdNote.getText()); // Проверяем текст новой заметки
    }

    @Test
    public void updateNote_WhenValidNoteProvided_ReturnsUpdatedNote() {
        Note existingNote = new Note();
        existingNote.setId(1);
        existingNote.setName("Updated Note");
        existingNote.setText("Updated text");
        Note updatedNote = new Note();
        updatedNote.setName("Updated Note");
        updatedNote.setText("Updated text");

        // Мокаем сервис, чтобы он вернул обновленную заметку
        when(noteService.updateNote(1, updatedNote)).thenReturn(existingNote);

        // Вызываем метод контроллера напрямую
        ResponseEntity<Note> response = noteController.updateNote(1, updatedNote);

        // Проверяем статус ответа (должен быть OK)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Проверяем, что возвращаемая заметка не null и имеет правильное имя и текст
        Note returnedNote = response.getBody();
        assertNotNull(returnedNote);
        assertEquals("Updated Note", returnedNote.getName());
        assertEquals("Updated text", returnedNote.getText());
    }

    @Test
    public void GetNoteById_WhenNotesExist_ReturnsNote() {
        Note existingNote = new Note();
        existingNote.setId(1);
        existingNote.setName("Updated Note");
        existingNote.setText("Updated text");
        when(noteService.findNoteById(1)).thenReturn(existingNote);

        // Вызываем метод контроллера напрямую
        ResponseEntity<Note> response = noteController.getNoteById(1);

        // Проверяем статус ответа (должен быть OK)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Проверяем, что возвращаемая заметка не null и имеет правильное имя и текст
        Note returnedNote = response.getBody();
        assertNotNull(returnedNote);
        assertEquals(1, returnedNote.getId());
        assertEquals("Updated Note", returnedNote.getName());
        assertEquals("Updated text", returnedNote.getText());
    }

    @Test
    public void shouldDeleteNote() {
        // Вызываем метод контроллера напрямую
        ResponseEntity<Void> response = noteController.deleteNote(1);

        // Проверяем, что метод deleteNote был вызван с правильным id
        verify(noteService).deleteNote(1);

        // Проверяем статус ответа (должен быть NO_CONTENT)
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }








}
