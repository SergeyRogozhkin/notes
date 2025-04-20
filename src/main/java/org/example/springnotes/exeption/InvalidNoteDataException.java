package org.example.springnotes.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidNoteDataException extends RuntimeException {
  public InvalidNoteDataException(String message) {
    super(message);
  }
}