package com.example.note_spring.service;

import com.example.note_spring.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    List<Note> getAllWeb();
    void saveWeb(Note note);
    Optional<Note> getByIdWeb(long id);
    void deleteByIdWeb(long id);
}
