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

    Note note1;
    Note note2;
    Long testId;

    @BeforeEach
    void setUp() {
        note1 = new Note(6L, "Title6", "Description6");
        note2 = new Note(7L, "Title7", "Description7");
        testId = 7L;
    }

    @Test
    void getAllWeb() {
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
        when(noteRepository.findById(testId)).thenReturn(Optional.of(note2));
        Note noteById = noteService.getByIdWeb(testId);
        assertNotEquals(noteById, null);
        assertEquals(noteById.getTitle(), "Title7");
        assertEquals(noteById.getDescription(), "Description7");
    }

    @Test
    public void testGetInvalidOrderByIdWeb() {
        when(noteService.getByIdWeb(17L)).thenThrow(new NoteNotFoundException("Note Not Found with ID"));
        Exception exception = assertThrows(NoteNotFoundException.class, () -> {
            noteService.getByIdWeb(17L);
        });
        assertFalse(exception.getMessage().contains("Order Not Found with ID"));
    }

    @Test
    void createWeb() {
        noteService.createWeb(note2);
        verify(noteRepository, Mockito.times(1)).save(note2);

        ArgumentCaptor<Note> noteArgumentCaptor = ArgumentCaptor.forClass(Note.class);
        verify(noteRepository).save(noteArgumentCaptor.capture());
        Note noteCreated = noteArgumentCaptor.getValue();
        assertNotNull(noteCreated.getId());
        assertEquals("Title7", noteCreated.getTitle());
    }

    @Test
    void deleteByIdWeb() {
        when(noteRepository.findById(testId)).thenReturn(Optional.of(note2));
        noteService.deleteByIdWeb(note2.getId());
        verify(noteRepository, Mockito.times(1)).deleteById(note2.getId());
        ArgumentCaptor<Long> noteArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(noteRepository).deleteById(noteArgumentCaptor.capture());
        Long noteIdDeleted = noteArgumentCaptor.getValue();
        assertNotNull(noteIdDeleted);
        assertEquals(testId, noteIdDeleted);
    }

    @Test
    void deleteAllWeb(){
        when(noteRepository.findById(12L)).thenReturn(Optional.of(note2));
        noteService.deleteByIdWeb(note2.getId());
        verify(noteRepository, Mockito.times(1)).deleteById(testId);
        ArgumentCaptor<Long> noteArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(noteRepository).deleteById(noteArgumentCaptor.capture());
        Long noteIdDeleted = noteArgumentCaptor.getValue();
        assertNotNull(noteIdDeleted);
        assertEquals(testId, noteIdDeleted);
    }

    @Test
    void getAllMobile() {
        when(noteService.getAllMobile()).thenReturn(Arrays.asList(note1, note2));
        assertEquals(noteService.getAllMobile().size(), 2);
        assertEquals(noteService.getAllMobile().get(0).getTitle(), "Title6");
        assertEquals(noteService.getAllMobile().get(1).getTitle(), "Title7");
        assertEquals(noteService.getAllMobile().get(0).getDescription(), "Description6");
        assertEquals(noteService.getAllMobile().get(1).getDescription(), "Description7");
        assertNotEquals(noteService.getAllMobile().get(0), null);
        assertNotEquals(noteService.getAllMobile().get(1), null);
    }

    @Test
    void getByIdMobile() {
        when(noteRepository.findById(testId)).thenReturn(Optional.of(note2));
        Optional<Note> noteById = noteService.getByIdMobile(testId);
        assertNotEquals(noteById, null);
        assertEquals(noteById.get().getTitle(), "Title7");
        assertEquals(noteById.get().getDescription(), "Description7");
    }

    @Test
    public void testGetInvalidOrderByIdMobile() {
        when(noteService.getByIdMobile(17L)).thenThrow(new NoteNotFoundException("Note Not Found with ID"));
        Exception exception = assertThrows(NoteNotFoundException.class, () -> {
            noteService.getByIdMobile(17L);
        });
        assertFalse(exception.getMessage().contains("Order Not Found with ID"));
    }

    @Test
    void createMobile() {
        noteService.createMobile(note2);
        verify(noteRepository, Mockito.times(1)).save(note2);

        ArgumentCaptor<Note> noteArgumentCaptor = ArgumentCaptor.forClass(Note.class);
        verify(noteRepository).save(noteArgumentCaptor.capture());
        Note noteCreated = noteArgumentCaptor.getValue();
        assertNotNull(noteCreated.getId());
        assertEquals("Title7", noteCreated.getTitle());
    }

    @Test
    void deleteByIdMobile() {
        when(noteRepository.findById(testId)).thenReturn(Optional.of(note2));
        noteService.deleteByIdMobile(note2.getId());
        verify(noteRepository, Mockito.times(1)).deleteById(note2.getId());
        ArgumentCaptor<Long> noteArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(noteRepository).deleteById(noteArgumentCaptor.capture());
        Long noteIdDeleted = noteArgumentCaptor.getValue();
        assertNotNull(noteIdDeleted);
        assertEquals(testId, noteIdDeleted);
    }

    @Test
    void deleteAllMobile(){
        when(noteRepository.findById(12L)).thenReturn(Optional.of(note2));
        noteService.deleteByIdMobile(note2.getId());
        verify(noteRepository, Mockito.times(1)).deleteById(testId);
        ArgumentCaptor<Long> noteArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(noteRepository).deleteById(noteArgumentCaptor.capture());
        Long noteIdDeleted = noteArgumentCaptor.getValue();
        assertNotNull(noteIdDeleted);
        assertEquals(testId, noteIdDeleted);
    }
}