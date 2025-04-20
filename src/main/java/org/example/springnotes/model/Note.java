package org.example.springnotes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@Entity
public class Note implements Serializable {
    @NotBlank(message = "Название не может быть пустым")
    private String name;

    @Size(min = 5, message = "Текст должен быть не менее 5 символов")
    private String text;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
