package com.example.note_spring.controller;

import com.example.note_spring.model.Note;
import com.example.note_spring.service.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteControllerWeb {

    @Autowired
    NoteServiceImpl noteService;

    Note note = new Note();

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("allnotelist", noteService.getAllWeb());
        return "index";
    }

    @GetMapping("/addnew")
    public String addNewNote(Model model){
        model.addAttribute("note", note);
        return "newnote";
    }

    @PostMapping("/save")
    public String saveNote(@ModelAttribute("note") Note note){
        noteService.createWeb(note);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model){
        note = noteService.getByIdWeb(id);
        model.addAttribute("note", note);
        return "update";
    }

    @GetMapping("deleteNote/{id}")
    public String deleteById(@PathVariable(value = "id") long id){
        noteService.deleteByIdWeb(id);
        return "redirect:/";
    }
}