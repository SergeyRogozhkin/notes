package org.example.springnotes.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Обработка исключения "Заметка не найдена"
    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<String> handleNoteNotFoundException(NoteNotFoundException ex) {
        // Возвращаем ошибку 404 (NOT_FOUND) с сообщением
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Обработка исключения для некорректных данных (например, если данные не валидны)
    @ExceptionHandler(InvalidNoteDataException.class)
    public ResponseEntity<String> handleInvalidNoteDataException(InvalidNoteDataException ex) {
        // Возвращаем ошибку 400 (BAD_REQUEST) с сообщением
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Обработка других ошибок (например, непредвиденные ошибки)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Произошла непредвиденная ошибка.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
