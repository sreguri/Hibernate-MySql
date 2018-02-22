package com.example.easynotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.easynotes.model.Note;


public interface NoteRepository extends JpaRepository<Note, Long> {
	
	@Query("SELECT a FROM Note a")
	  List<Note> findNotesByHql();
	
	@Query("SELECT a FROM Note a where a.title =:id")
	  List<Note> findNotesByIdHql(@Param("id") String id);
	
	@Query("SELECT a FROM Note a where a.id =:id")
	 List<Note> getNoteById(@Param("id") String noteId);
	
//	@Query("FROM Note a where a.title =:test title1")
//	  List<Note> findNotesHqlTitle(@Param("title") String title);
}
