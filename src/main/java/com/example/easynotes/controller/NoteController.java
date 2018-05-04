package com.example.easynotes.controller;

import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import com.example.easynotes.repository.NotesRepositoryCriteria;
import com.example.easynotes.repository.NotesRepositoryNativeSql;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.loading.PrivateClassLoader;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;
    
    @Autowired
    private NotesRepositoryCriteria custom;
    @Autowired
    private NotesRepositoryNativeSql Native;

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
    	long start_time = System.currentTimeMillis();
         List<Note> notes = noteRepository.findAll();
         long end_time = System.currentTimeMillis();
         System.out.println("hi");
        System.out.println("Time using jpa repository:"+(end_time-start_time));
        return notes;    
    }
    
    
    @GetMapping("/notes/{id}")
    public List<Note> getNoteById(@PathVariable(value = "id") String noteId) {
    	long start_time = System.currentTimeMillis();
    	List<Note> notes =noteRepository.getNoteById(noteId);
        long end_time = System.currentTimeMillis();
        System.out.println("Time using jpa id query:"+(end_time-start_time));
        System.out.println("hi");
        return notes;     
    }
    
    @GetMapping("/notesbyhql")
    public List<Note> getAllontesHql(){
    	long start_time = System.currentTimeMillis();
    	List<Note> notes =noteRepository.findNotesByHql();
    	long end_time = System.currentTimeMillis();
    	   System.out.println("hi");
        System.out.println("Time using hql query:"+(end_time-start_time));
        return notes;
    }
    
    @GetMapping("/notesbyidhql/{id}")
    public List<Note> findNotesByIdHql(@PathVariable(value = "id") String noteId){
    	long start_time = System.currentTimeMillis();
    	List<Note> notes =noteRepository.findNotesByIdHql(noteId);
    	long end_time = System.currentTimeMillis();
        System.out.println("Time using hql id query:"+(end_time-start_time));
        return notes;
    }
    
//    @GetMapping("/notesbyhqltitle/{title}")
//    public List<Note> findNotesHqlTitle(@PathVariable(value = "{title}") String title){
//    	long start_time = System.currentTimeMillis();
//    	List<Note> notes =noteRepository.findNotesHqlTitle(title);
//    	long end_time = System.currentTimeMillis();
//        System.out.println("Time using hql id query:"+(end_time-start_time));
//        return notes;
//    }
    
    @GetMapping("/notesbycriteria")
    public List<Note> getAllNotesCriteria(){
    	long start_time = System.currentTimeMillis();
    	List<Note> notes =custom.findNotesByCriteria();
    	long end_time = System.currentTimeMillis();
        System.out.println("Time using criteria query:"+(end_time-start_time));
        return notes;
    }
 
    @GetMapping("/notesbynativesql")
    public List<Note> getNotesByNativeSql(){
    	long start_time = System.currentTimeMillis();
    	List<Note> notes =Native.findNotesByNativeSql();
    	long end_time = System.currentTimeMillis();
        System.out.println("Time using jpa id query:"+(end_time-start_time));
        System.out.println("hi");
        System.out.println("Time using Native Sql query:"+(end_time-start_time));
        return notes;
    }
    public List<Note> getNotwwesByNativeSql(){
    	long start_time = System.currentTimeMillis();
    	List<Note> notes =Native.findNotesByNativeSql();
    	long end_time = System.currentTimeMillis();
        System.out.println("Time using Native Sql query:"+(end_time-start_time));
        return notes;
    }

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Note noteDetails) {
        Note note = noteRepository.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }

        noteRepository.delete(note);
        return ResponseEntity.ok().build();
    }
}
