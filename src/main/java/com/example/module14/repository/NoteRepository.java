package com.example.module14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.module14.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
