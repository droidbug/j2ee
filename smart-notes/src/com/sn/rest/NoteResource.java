package com.sn.rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sn.models.Note;
import com.sn.services.NoteService;

;

@Path("/note")
public class NoteResource {
	@Context
	ServletContext context;
	NoteService noteService = null;
	Note dao = null;

	@GET
	// @Produces ("text/html")
	@Produces({ MediaType.TEXT_HTML, MediaType.TEXT_PLAIN })
	public String noteTest() {
		noteService = new NoteService(context);
		return "Hello noteTest()";
	}

	@GET
	@Path("/noteTest")
	@Produces({ MediaType.APPLICATION_JSON })
	public Note getNote() {
		Note note = new Note();
		note.setId(1);
		note.setTitle("title-" + 1);
		return note;
	}

	@GET
	@Path("/all")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Note> findAll() {
		noteService = new NoteService(context);
		List<Note> ll = noteService.findAllNoteDemo();
		//GenericEntity<List<Note>> list = new GenericEntity<List<Note>>(ll) {};
		//return Response.ok(list).build();
		return ll;
	}

	/*
	 * @GET
	 * 
	 * @Path("{id}")
	 * 
	 * @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	 * public Employee findById(@PathParam("id") String id) { return
	 * dao.findById(Integer.parseInt(id)); }
	 * 
	 * @GET
	 * 
	 * @Path("{id}/reports")
	 * 
	 * @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	 * public List<Employee> findByManager(@PathParam("id") String managerId) {
	 * return dao.findByManager(Integer.parseInt(managerId)); }
	 */

}
