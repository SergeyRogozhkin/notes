package org.example.springnotes.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Note implements Serializable {
    private String nameNote;
    private String textNote;
    private int id;
}
