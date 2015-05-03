package com.sn.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.sn.models.*;

public class ModelBuilder {
	static Logger logger = Logger.getLogger(ModelBuilder.class);
	private Note note;
	private UserInfo userInfo;


	public ModelBuilder(){
		
	}
	
	private Note buildNoteFromResultSet(ResultSet resultSet) throws SQLException {
		Note note = null;
		/*java.util.Date date = null;
		Timestamp timestamp = resultSet.getTimestamp("last_update_at");
		if (timestamp != null)
		    date = new java.util.Date(timestamp.getTime());
		Date dtStart = resultSet.getTimestamp("last_update_at"); */   
		note = new Note(resultSet.getLong("id"),
				resultSet.getString("title"), resultSet.getString("description"),
				resultSet.getString("importance"), resultSet.getLong("creator"),
				resultSet.getTimestamp("last_update_at"), resultSet.getBoolean("is_deleted"));
		return note;
	}
	
	public Note buildSingleNoteFromResultSet(ResultSet resultSet){
		Note note = null;
		
		try {
			if(resultSet.next()){
				note = buildNoteFromResultSet(resultSet);
				logger.info("Note found with details=" + note);
			} else {
				logger.info("Note resultSet is empty.");
				note = new Note();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return note;
	}
	
	public List<Note> buildNoteListFromResultSet(ResultSet resultSet){
		List<Note> noteList = new ArrayList<Note>();
		
		try {
			while (resultSet.next()) {
				note = buildNoteFromResultSet(resultSet);
				logger.info("Note found with details=" + note);
				noteList.add(note);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return noteList;
	}

	

	public Note createNote(long id, String title, String description,  String importance,  long creator, Date lastUpdateAt, boolean isDeleted){
		
		return new Note();
	}
	
	public Note createRestNote(String noteJsonString){
		
		return new Note();
	}
}
