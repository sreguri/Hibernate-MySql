package com.example.easynotes.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.Note;

@Repository
public class NotesRepositoryCriteriaImpl implements NotesRepositoryCriteria{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Note> findNotesByCriteria() {
		Criteria cr=entityManager.unwrap(Session.class).createCriteria(Note.class);
		cr.add(Restrictions.eq("title", "test title1"));
		//cr.setProjection(Projections.countDistinct("content"));

        List<Note> notes = cr.list();
        //List<Note> filterednotes=notes.stream().filter(e-> e.getContent().equals("test content1")).collect(Collectors.toList());
		
		return notes;
	}

}
