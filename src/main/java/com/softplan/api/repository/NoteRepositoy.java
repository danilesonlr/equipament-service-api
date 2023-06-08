package com.softplan.api.repository;

import com.softplan.api.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepositoy extends JpaRepository<Note, Long> {
}
