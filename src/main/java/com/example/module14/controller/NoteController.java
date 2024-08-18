package com.example.module14.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.module14.entity.Note;
import com.example.module14.repository.NoteRepository;

import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/list")
    public String listAllNotes(Model model){
        List<Note> allNotes = noteService.listAll();
        model.addAttribute("notes", allNotes);
        return "list-note";
    }

    @GetMapping("/add")
    public String editNote(Model model){
        Note note = new Note();
        model.addAttribute("note", note);
        return "note-form";
    }

    @PostMapping("/save")
    public String addNote(@ModelAttribute("note") Note note){
        noteService.add(note);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("noteId") Long id, Model model){
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "note-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("noteId") Long id){
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

}
