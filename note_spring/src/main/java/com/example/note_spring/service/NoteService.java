package com.example.note_spring.service;

import com.example.note_spring.model.Note;
import com.example.note_spring.repository.NoteRepository;

import java.util.List;

public interface NoteService {
    List<Note> getAllWeb();
    void saveWeb(Note note);
    Note getByIdWeb(long id);
    void deleteByIdWeb(long id);
}
