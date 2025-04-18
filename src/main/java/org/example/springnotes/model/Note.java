package org.example.springnotes.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class Note implements Serializable {
    private String nameNote;
    private String textNote;
    private int id;
}
