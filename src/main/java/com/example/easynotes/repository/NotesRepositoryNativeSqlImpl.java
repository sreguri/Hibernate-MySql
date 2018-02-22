package com.example.easynotes.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.Note;
import com.example.easynotes.utility.HibernateUtil;

@Repository
public class NotesRepositoryNativeSqlImpl implements NotesRepositoryNativeSql{
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Note> findNotesByNativeSql(){
		Session session = entityManager.unwrap(Session.class);
		List<Note> list=null;
		SQLQuery query=session.createSQLQuery("select * from notes where title=:test1");
		query.addEntity(Note.class);
		query.setParameter("test1", "test title1");
		list=query.list();
		HibernateUtil.closeSession(session);
		return list;
		
	}
	

}
