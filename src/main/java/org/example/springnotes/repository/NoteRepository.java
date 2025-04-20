package org.example.springnotes.repository;

import org.example.springnotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    // здесь можно писать свои кастомные методы при необходимости
}
