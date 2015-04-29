package com.sn.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.sn.models.Note;

public class NoteService {
	static Logger logger = Logger.getLogger(NoteService.class);
	//ServletContext servletContext = null;
	Connection con = null;
	Note note = null;
	List<Note> noteList = null;
	
	
	public NoteService(ServletContext sContext) {
		//super();
		//servletContext = sContext;
		con = (Connection) sContext.getAttribute("DBConn"); // DBConn - DBConnection
		noteList = new ArrayList<Note>();
	}
	
	public Note findNoteById(long id){
		note = (Note)new Object();

		return note;
	}
	
	public List<Note> findAllNote(){
		noteList.add((Note)new Object());
		noteList.add((Note)new Object());
		noteList.add((Note)new Object());

		return noteList;
	}
	
	public List<Note> findAllNoteDemo(){
		for(long i=1;i<=10;i++) {
			Note note = new Note();
			note.setId(i);
			note.setTitle("title-"+i);
			note.setDescription("Description-"+(i+i));
			note.setLastUpdateAt(new Date());
			noteList.add(note);
		}
		//System.out.println("noteList.size() = "+noteList.size());
		//System.out.println(noteList.toString());
		return noteList;
	}
	
	public List<Note> findAllNoteByCreator(long id){
		noteList.add((Note)new Object());
		noteList.add((Note)new Object());
		noteList.add((Note)new Object());

		return noteList;
	}
	
	public List<Note> findAllNoteByImportance(String importance){
		noteList.add((Note)new Object());
		noteList.add((Note)new Object());
		noteList.add((Note)new Object());

		return noteList;
	}
	
	public long deleteNoteById(long id){
		//user can not delete or edit EMERGENCY notes
		note = (Note)new Object();

		return note.getId();
	}
	
	public long updateNoteById(long id){
		//user can not delete or edit EMERGENCY notes
		note = (Note)new Object();

		return note.getId();
	}

}
