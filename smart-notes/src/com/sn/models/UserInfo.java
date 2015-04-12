package com.sn.models;

import java.io.Serializable;
import java.util.Date;
//import java.sql.Timestamp;;

public class UserInfo implements Serializable{
	
	private static final long serialVersionUID = 6297385302078200511L;
	
	private long id;
	private String email;
	private String fullName;
	private String status;
	private String userType;
	private Date createDate;
	
	public UserInfo(long id, String email, String fullName, String status,
			String userType, Date createDate) {
		super();
		this.id = id;
		this.email = email;
		this.fullName = fullName;
		this.status = status;
		this.userType = userType;
		this.createDate = createDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", email=" + email + ", fullName="
				+ fullName + ", status=" + status + ", userType=" + userType
				+ ", createDate=" + createDate + "]";
	}
	
	
	
	
}
