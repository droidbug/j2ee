package com.sn.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.sn.builder.ModelBuilder;
import com.sn.models.Note;

public class NoteService {
	static Logger logger = Logger.getLogger(NoteService.class);
	// ServletContext servletContext = null;
	Connection con = null;
	Note note = null;
	ModelBuilder builder = null;
	List<Note> noteList = null;
	//executeQuery()---for getting the data from database
	//executeUpdate()---for insert,update,delete
	//execute()-any kind of operations 
	
	//From official docs: Returns: either the row count for SQL Data Manipulation Language 
	//(such as INSERT, UPDATE or DELETE) statements or 0 for SQL statements that return nothing
	//@Update It assigns the number of deleted rows.

	/*Returns: either
	
	(1) the row count for SQL Data Manipulation Language (DML) statements or
	
	(2) 0 for SQL statements that return nothing
	*/


	public NoteService(ServletContext sContext) {
		// super();
		// servletContext = sContext;
		con = (Connection) sContext.getAttribute("DBConn"); // DBConn - // DBConnection
		noteList = new ArrayList<Note>();
		builder = new ModelBuilder();
	}

	public Note findNoteById(long id) throws ServletException {
		//note = new Note();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sqlStr = "select * from note where id=? limit 1";
			logger.info(sqlStr);
			ps = con.prepareStatement(sqlStr);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			// UtilService.buildDynamicObjectFromResultSet(rs);
			if (rs != null) {
				note = builder.buildSingleNoteFromResultSet(rs);
			} else {
				logger.info("ResultSet rs is null.");
				note = new Note();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement or ResultSet");
			}

		}

		return note;
	}

	public List<Note> findAllNote() throws ServletException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sqlStr = "select * from note";
			logger.info(sqlStr);
			ps = con.prepareStatement(sqlStr);
			rs = ps.executeQuery();

			if (rs != null) {
				noteList = builder.buildNoteListFromResultSet(rs);
			} else {
				logger.info("ResultSet rs is null.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement or ResultSet");
			}

		}
		return noteList;
	}

	public List<Note> findAllNoteDemo() {
		for (long i = 1; i <= 10; i++) {
			Note note = new Note();
			note.setId(i);
			note.setTitle("title-" + i);
			note.setDescription("Description-" + (i + i));
			note.setLastUpdateAt(new Date());
			noteList.add(note);
		}
		// System.out.println("noteList.size() = "+noteList.size());
		// System.out.println(noteList.toString());
		return noteList;
	}

	public List<Note> findAllNoteByCreator(long id) {
		noteList.add((Note) new Object());
		noteList.add((Note) new Object());
		noteList.add((Note) new Object());

		return noteList;
	}

	public List<Note> findAllNoteByImportance(String importance) {
		noteList.add((Note) new Object());
		noteList.add((Note) new Object());
		noteList.add((Note) new Object());

		return noteList;
	}

	public long deleteNoteById(long id) throws ServletException {
		int affectedRowCount = 0;
		PreparedStatement ps = null;
		try {
			String sqlStr = "delete from note where id=?";
			logger.info(sqlStr);
			ps = con.prepareStatement(sqlStr);
			ps.setLong(1, id);
			affectedRowCount = ps.executeUpdate();
			if(affectedRowCount > 0){
				logger.info("Success: Note deleted having id:"+id);
			} else {
				logger.error("Error: Note deleted having id:"+id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement");
			}

		}

		return affectedRowCount;
	}

	public long updateNoteById(long id) {
		// user can not delete or edit EMERGENCY notes
		note = (Note) new Object();

		return note.getId();
	}

	/************ FOR_REST_API ***************/
	public Note saveNote(Note note) throws ServletException {
		System.out.println("saveNote:incomming: " + note.toString());
		long lastInsertedId = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sqlStr = "insert into note (title, description, importance, creator, is_deleted) values (?,?,?,?,?)";
			logger.info(sqlStr);
			ps = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, note.getTitle());
			ps.setString(2, note.getDescription());
			ps.setString(3, note.getImportance());
			ps.setLong(4, note.getCreator());
			ps.setBoolean(5, false);
			// database column has a default value CURRENT_TIMESTAMP so no need
			// to set this value
			// ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));

			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if (rs != null && rs.next()) {
				// System.out.println("Generated Note Id: "+rs.getLong(1));
				// //not working
				// System.out.println("Generated Note IdX: "+rs.getLong("id"));
				// //not working
				System.out.println("Generated Note Id: " + rs.getInt(1)); // working
				lastInsertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement or ResultSet");
			}

		}

		note = this.findNoteById(lastInsertedId);
		return note;
	}
	
	public Note updateNoteByRest(long id, Note note)  throws ServletException {
		System.out.println("updateNoteByRest: id : " +id+" Note: "+ note.toString());		
		PreparedStatement ps = null;
		try {
			String sqlStr ="UPDATE note SET title=?, description=? WHERE id=?";
			logger.info(sqlStr);
			ps = con.prepareStatement(sqlStr);
			ps.setString(1, note.getTitle());
			ps.setString(2, note.getDescription());
			ps.setLong(3, id);
			
			int countUpdatedRows = ps.executeUpdate();
			
			if (countUpdatedRows == 0) {
				logger.error("Error: Note update having id:"+id);
			    System.out.println("An existing user was updated successfully!");
			} else if(countUpdatedRows == 1){
				logger.info("Success: Note update having id:"+id);
			} else {
				logger.info("Success: Note update more than one row having id:"+id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem: "+e.getMessage());
			throw new ServletException("DB Connection problem.");
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement or ResultSet");
			}

		}

		note = this.findNoteById(id);
		return note;
	}
}
