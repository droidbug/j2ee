package com.sn.rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sn.builder.ModelBuilder;
import com.sn.models.Note;
import com.sn.services.NoteService;

;

@Path("/note")
public class NoteResource {
	@Context
	ServletContext context;
	
	NoteService noteService = null;
	ModelBuilder builder = null;
	Note dao = null; //not used yet

	@GET
	//@Produces ("text/html")
	@Produces({ MediaType.TEXT_HTML, MediaType.TEXT_PLAIN })
	public String noteTest() {
		noteService = new NoteService(context);
		return "Hello noteTest()";
	}

	@GET
	@Path("/demoNote")
	@Produces({ MediaType.APPLICATION_JSON })
	public Note getNote() {
		Note note = new Note();
		note.setId(1);
		note.setTitle("title-" + 1);
		return note;
	}
	
	/************ Note_REST_API ***************/
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveNote(final Note note) {
		noteService = new NoteService(context);
		Note notePersisted = null;
		try {
			notePersisted = noteService.saveNote(note);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.ok(new RestMessage("EXCEPTION", e.getMessage())).build();
		}		
		return Response.ok(notePersisted).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNoteById(final @PathParam("id") String id) {
		noteService = new NoteService(context);
		Note note = null;
		try {
			note = noteService.findNoteById(Long.parseLong(id));
			if(note.getId() == 0){
				//System.err.println("Note Not found");
				//Response.ok("{\"msg\":\"Note Not found\"}").build();
				//return Response.ok("{\"message\":\"Note Not found by id "+id+"\"}").build();
				return Response.ok(new RestMessage("DB_NOT_FOUND", "Note Not found by id "+id)).build();
				//return Response.ok(note).build();
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.ok(new RestMessage("EXCEPTION", e.getMessage())).build();
		}
		return Response.ok(note).build();
	}
	

	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	//produces means this method return JSON type data to the client from this method
	public Response findAll() {
		noteService = new NoteService(context);
		//List<Note> ll = noteService.findAllNoteDemo();
		List<Note> noteList = null;
		try {
			noteList = noteService.findAllNote();
			if(noteList.size() == 0){
				return Response.ok(new RestMessage("DB_NOT_FOUND", "Note list Not found")).build();
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.ok(new RestMessage("EXCEPTION", e.getMessage())).build();
		}
		//GenericEntity<List<Note>> list = new GenericEntity<List<Note>>(ll) {};
		return Response.ok(noteList).build();
		//return ll;
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteNote(final @PathParam("id") String id) {
		noteService = new NoteService(context);
		long noteId = 0;
		try {
			noteId = noteService.deleteNoteById(Long.parseLong(id));
			if(noteId == 0){
				return Response.ok(new RestMessage("DB_DELETE_ERROR", "Note deletion failed having id "+id)).build();
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.ok(new RestMessage("EXCEPTION", e.getMessage())).build();
		}
		return Response.ok(new RestMessage("DB_DELETE_OK", "Note deletion successfull had id "+id)).build();
	}
	
	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateNote(final @PathParam("id") long id, final Note note) {
		noteService = new NoteService(context);
		Note updatedNote = null;
		try {
			updatedNote = noteService.updateNoteByRest(id,note);
			if(updatedNote.getId() == 0){
				return Response.ok(new RestMessage("DB_NOT_FOUND", "Note Not found by id "+id)).build();
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.ok(new RestMessage("EXCEPTION", e.getMessage())).build();
		}
		System.out.println("ID: "+id);
		return Response.ok(updatedNote).build();
	}
	
	
	/*public Response saveNote(final Note note) {
		noteService = new NoteService(context);
		Note bookPersisted = noteService.saveNote(note);
		return Response.ok(bookPersisted).build();
	}*/

}
