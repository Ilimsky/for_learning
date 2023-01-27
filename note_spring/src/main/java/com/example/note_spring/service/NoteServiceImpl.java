package com.example.note_spring.service;

import com.example.note_spring.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    @Override
    public List<Note> getAllWeb() {
        return null;
    }

    @Override
    public void saveWeb(Note note) {

    }

    @Override
    public Note getByIdWeb(long id) {
        return null;
    }

    @Override
    public void deleteByIdWeb(long id) {

    }
}
