package org.example.springnotes.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class Note implements Serializable {
    private String name;
    private String text;
    private int id;
}
