package com.example.note_spring.service;

import com.example.note_spring.model.Note;
import com.example.note_spring.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class NoteServiceImplTest {

    @Mock
    NoteRepository noteRepository;

//    @Spy
    @InjectMocks
    NoteService noteService = new NoteServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllWeb() {
        Note note1 = new Note(6L, "Title6", "Description6");
        Note note2 = new Note(7L, "Title7", "Description7");
        when(noteService.getAllWeb()).thenReturn(Arrays.asList(note1, note2));
        assertEquals(noteService.getAllWeb().size(), 2);
        assertEquals(noteService.getAllWeb().get(0).getTitle(), "Title6");
        assertEquals(noteService.getAllWeb().get(1).getTitle(), "Title7");
        assertEquals(noteService.getAllWeb().get(0).getDescription(), "Description6");
        assertEquals(noteService.getAllWeb().get(1).getDescription(), "Description7");
        assertNotEquals(noteService.getAllWeb().get(0), null);
        assertNotEquals(noteService.getAllWeb().get(1), null);
    }

    @Test
    void saveWeb() {

    }

    @Test
    void getByIdWeb() {
    }

    @Test
    void deleteByIdWeb() {
    }
}