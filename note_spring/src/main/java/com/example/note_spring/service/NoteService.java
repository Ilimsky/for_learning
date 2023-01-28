package com.example.note_spring.service;

import com.example.note_spring.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    List<Note> getAllWeb();
    void createWeb(Note note);
    Optional<Note> getByIdWeb(Long id);
    void deleteByIdWeb(long id);
}
