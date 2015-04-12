package com.sn.models;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {

	private static final long serialVersionUID = 6297385302078200512L;

	private long id;
	private String title;
	private String description;
	private String importance;
	private long creator;
	private Date lastUpdateAt;
	private boolean isDeleted;
	
	
	
	public Note(long id, String title, String description, String importance,
			long creator, Date lastUpdateAt, boolean isDeleted) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.importance = importance;
		this.creator = creator;
		this.lastUpdateAt = lastUpdateAt;
		this.isDeleted = isDeleted;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getImportance() {
		return importance;
	}



	public void setImportance(String importance) {
		this.importance = importance;
	}



	public long getCreator() {
		return creator;
	}



	public void setCreator(long creator) {
		this.creator = creator;
	}



	public Date getLastUpdateAt() {
		return lastUpdateAt;
	}



	public void setLastUpdateAt(Date lastUpdateAt) {
		this.lastUpdateAt = lastUpdateAt;
	}



	public boolean isDeleted() {
		return isDeleted;
	}



	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}



	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", description="
				+ description + ", importance=" + importance + ", creator="
				+ creator + ", lastUpdateAt=" + lastUpdateAt + ", isDeleted="
				+ isDeleted + "]";
	}
	
	

}
