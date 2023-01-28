package com.example.note_spring.service;

import com.example.note_spring.exception.NoteNotFoundException;
import com.example.note_spring.model.Note;
import com.example.note_spring.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;

import static javax.management.Query.times;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class) //ExtendWith extension is used to simulate test environment
class NoteServiceImplTest {

    @Mock //Mock annotation will create a mock object of the Service layer
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
    void getByIdWeb() {
        Note note = new Note(7L, "Title7", "Description7");
        when(noteRepository.findById(7L)).thenReturn(Optional.of(note));
        Optional<Note> noteById = noteService.getByIdWeb(7L);
        assertNotEquals(noteById, null);
        assertEquals(noteById.get().getTitle(), "Title7");
        assertEquals(noteById.get().getDescription(), "Description7");
    }

    @Test
    public void testGetInvalidOrderById() {
        when(noteService.getByIdWeb(17L)).thenThrow(new NoteNotFoundException("Note Not Found with ID"));
        Exception exception = assertThrows(NoteNotFoundException.class, () -> {
            noteService.getByIdWeb(17L);
        });
        assertFalse(exception.getMessage().contains("Order Not Found with ID"));
    }

    @Test
    void createWeb() {
        Note note = new Note(12L, "Title12", "Description12");
        noteService.createWeb(note);
        verify(noteRepository, Mockito.times(1)).save(note);

        ArgumentCaptor<Note> noteArgumentCaptor = ArgumentCaptor.forClass(Note.class);
        verify(noteRepository).save(noteArgumentCaptor.capture());
        Note noteCreated = noteArgumentCaptor.getValue();
        assertNotNull(noteCreated.getId());
        assertEquals("Title12", noteCreated.getTitle());
    }

    @Test
    void deleteByIdWeb() {
        Note note = new Note(13L, "Title13", "Description13");
        when(noteRepository.findById(13L)).thenReturn(Optional.of(note));
        noteService.deleteByIdWeb(note.getId());
        verify(noteRepository, Mockito.times(1)).deleteById(note.getId());
        ArgumentCaptor<Long> noteArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(noteRepository).deleteById(noteArgumentCaptor.capture());
        Long noteIdDeleted = noteArgumentCaptor.getValue();
        assertNotNull(noteIdDeleted);
        assertEquals(13L, noteIdDeleted);
    }

    @Test
    void deleteAll(){
        Note note = new Note(12L, "Title12", "Description12");
        when(noteRepository.findById(12L)).thenReturn(Optional.of(note));
        noteService.deleteByIdWeb(note.getId());
        verify(noteRepository, Mockito.times(1)).deleteById(12L);
        ArgumentCaptor<Long> noteArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(noteRepository).deleteById(noteArgumentCaptor.capture());
        Long noteIdDeleted = noteArgumentCaptor.getValue();
        assertNotNull(noteIdDeleted);
        assertEquals(12L, noteIdDeleted);
    }
}