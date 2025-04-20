package org.example.springnotes;

import org.example.springnotes.model.Note;
import org.example.springnotes.repository.NoteRepository;
import org.example.springnotes.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestNoteService {

    @Mock
    private NoteRepository repository; // Мокируем NoteRepository

    @InjectMocks
    private NoteService noteService; // Внедряем мокированный репозиторий в сервис

    private Note note;

    @BeforeEach
    public void setUp() {
        // Инициализируем объект Note для тестов
        note = new Note();
        note.setId(1);
        note.setName("Test Note");
        note.setText("This is a test note.");
    }

    @Test
    public void shouldFindAllNotes() {
        // Мокаем репозиторий, чтобы он возвращал список заметок
        when(repository.findAll()).thenReturn(List.of(note));

        // Вызываем метод сервиса
        List<Note> notes = noteService.findAllNotes();

        // Проверяем, что список не пуст
        assertNotNull(notes);
        assertEquals(1, notes.size());
        assertEquals("Test Note", notes.get(0).getName());
    }

    //todo доделать тесты
}
