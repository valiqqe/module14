package com.example.module14.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.example.module14.entity.Note;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class NoteDao {
    private EntityManagerFactory entityManagerFactory;

    public List<Note> listAllNotes(){
        EntityManager entityManager = getEntityManager();
        List<Note> resultList = entityManager.createQuery("SELECT * FROM note", Note.class).getResultList();
        entityManager.close();
        return resultList;
    }

    public Note addNote(Note note){
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(note);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return note;
    }

    public void deleteNoteById(long id){
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Note note = entityManager.find(Note.class, id);
        if(note == null){
            throw new NoSuchElementException("There is no such element with id=" + id);
        }
        entityManager.remove(note);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateNote(Note note){
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Note savedNote = entityManager.find(Note.class, note.getId());
        if(savedNote == null){
            throw new NoSuchElementException("There is no such element with id=" + note.getId());
        }
        entityManager.merge(savedNote);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Note getNoteById(long id){
        EntityManager entityManager = getEntityManager();
        Note note = entityManager.find(Note.class, id);
        if (note == null){
            throw new NoSuchElementException("There is no such element with id=" + id);
        }
        entityManager.close();
        return note;
    }

    private EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

}
