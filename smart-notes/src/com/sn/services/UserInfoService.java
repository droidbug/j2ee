package com.sn.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.sn.models.UserInfo;

public class UserInfoService {
	static Logger logger = Logger.getLogger(UserInfoService.class);
	//ServletContext servletContext = null;
	Connection con = null;
	UserInfo userInfo = null;
	List<UserInfo> userInfoList = null;
	
	
	public UserInfoService(ServletContext sContext) {
		//super();
		//servletContext = sContext;
		con = (Connection) sContext.getAttribute("DBConn"); // DBConn - DBConnection
		userInfoList = new ArrayList<UserInfo>();
	}


	public UserInfo findUserInfoByEmailAndPassword(String email, String password) throws ServletException{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			/*
			 * private long id; private String email; private String
			 * fullName; private String status; private String userType;
			 * private Date createDate;
			 */

			String sqlStr = "select id, email, full_name, status, user_type, create_date from user_info where email=? and password=? and status=? limit 1";
			//String sqlStr = "select id, email, full_name, status, user_type, create_date from user_info where email=? and password=? limit 1";
			logger.info(sqlStr);
			ps = con.prepareStatement(sqlStr);
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, "UN_BLOCKED");
			rs = ps.executeQuery();
			

			if (rs != null) { //rs != null
				if(rs.next()){
					//rs.next();
					//http://stackoverflow.com/questions/3473421/mysql-timestamp-to-java-date-conversion
					// UserInfo(long id, String email, String fullName, String
					// status, String userType, Date createDate)
					userInfo = new UserInfo(rs.getLong("id"),
							rs.getString("email"), rs.getString("full_name"),
							rs.getString("status"), rs.getString("user_type"),
							rs.getTimestamp("create_date"));
					logger.info("UserInfo found with details=" + userInfo);
				} else {
					logger.info("UserInfo rs is empty.");
				}
			} else {
				logger.info("UserInfo rs is null.");
			}
			return userInfo;
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
	}
	
	
	public UserInfo findUserInfoByEmail(String email){
		userInfo = (UserInfo)new Object();

		return userInfo;
	}
	
	public UserInfo findUserInfoById(long id){
		userInfo = (UserInfo)new Object();

		return userInfo;
	}
	
	public long changeOrBlockOrDeleteUserInfoStatusById(long id){
		userInfo = (UserInfo)new Object();

		return userInfo.getId();
	}
}
