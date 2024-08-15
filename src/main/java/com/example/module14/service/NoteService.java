package com.example.module14.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.module14.entity.Note;
import com.example.module14.dao.NoteDao;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteDao noteDao;

    public List<Note> listAll(){
        return noteDao.listAllNotes();
    }

    public Note add(Note note){
        return noteDao.addNote(note);
    }

    public void deleteById(long id){
        noteDao.deleteNoteById(id).orElseThrow(() -> new NoSuchElementException("There is no such element with id=" + id));
    }

    public void update(Note note){
        noteDao.updateNote(note).orElseThrow(() -> new NoSuchElementException("There is no such element with id=" + note.getId()));
    }

    public Note getById(long id){
        return noteDao.getNoteById(id).orElseThrow(() -> new NoSuchElementException("There is no such element with id=" + id));
    }
}
