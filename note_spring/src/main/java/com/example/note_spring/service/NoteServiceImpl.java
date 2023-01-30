package com.example.note_spring.service;

import com.example.note_spring.model.Note;
import com.example.note_spring.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Override
    public List<Note> getAllWeb() {
        return noteRepository.findAll();
    }

    @Override
    public void createWeb(Note note) {
        noteRepository.save(note);
    }

    @Override
    public Optional<Note> getByIdWeb(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public void deleteByIdWeb(long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public List<Note> getAllMobile() {
        return noteRepository.findAll();
    }

    @Override
    public void createMobile(Note note) {
        noteRepository.save(note);
    }

    @Override
    public Optional<Note> getByIdMobile(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public void deleteByIdMobile(long id) {
        noteRepository.deleteById(id);
    }
}
