package com.sn.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sn.models.UserInfo;
import com.sn.services.UserInfoService;

@WebServlet(name = "Login", urlPatterns = { "/loginApp" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserInfoService  userInfoService = null;

	static Logger logger = Logger.getLogger(LoginServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String errorMsg = null;
		/*if (request.getSession().getAttribute("UserInfo") != null) {
			response.sendRedirect("home.jsp");
		}*/
		if (email == null || email.equals("")) {
			errorMsg = "User Email can't be null or empty";
		}
		if (password == null || password.equals("")) {
			errorMsg = "Password can't be null or empty";
		}

		if (errorMsg != null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>" + errorMsg + "</font>");
			rd.include(request, response);
		} else {
			userInfoService = new UserInfoService(getServletContext());
			UserInfo userInfo = userInfoService.findUserInfoByEmailAndPassword(email, password);
			if (userInfo != null) {
				//timeFormatFromDBTimestamp(userInfo.getCreateDate());
				HttpSession session = request.getSession();
				//session.setAttribute("UserInfo", userInfo);
				request.setAttribute("userInfo", userInfo);
				userInfoService = null;
				//response.sendRedirect("home.jsp");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
				PrintWriter out = response.getWriter();
				logger.error("User not found with email=" + email);
				out.println("<font color=red>No user found with given email and password OR user may be blocked.<br/>Contact with admin.</font>");
				rd.include(request, response);
			}
			
		}
	}
	protected void timeFormatFromDBTimestamp(Date date){
		//http://www.java2s.com/Tutorial/Java/0040__Data-Type/DateParsingandFormattingwithDateFormat.htm
		System.out.println("date: "+date);
		Timestamp ts_now = new Timestamp(date.getTime());
		System.out.println("ts_now: "+ts_now);		
		/********************************/
		DateFormat shortDf = DateFormat.getDateInstance(DateFormat.SHORT);
	    DateFormat mediumDf = DateFormat.getDateInstance(DateFormat.MEDIUM);
	    DateFormat longDf = DateFormat.getDateInstance(DateFormat.LONG);
	    DateFormat fullDf = DateFormat.getDateInstance(DateFormat.FULL);
	    
	    System.out.println("shortDf: "+shortDf.format(date));
	    System.out.println("mediumDf: "+mediumDf.format(date));
	    System.out.println("longDf: "+longDf.format(date));
	    System.out.println("fullDf: "+fullDf.format(date));
	    /********************************/
	    //https://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html
	    //String pattern = "yyyy.MMMMM.dd GGG hh:mm:ss aaa";
	    //SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	    
	    //OUTPUT : 2015.April.11 AD 12:56:57 PM
	    System.out.println(date+" -> [yyyy.MMMMM.dd GGG hh:mm:ss aaa] "+new SimpleDateFormat("yyyy.MMMMM.dd GGG hh:mm:ss aaa").format(date));
	    //OUTPUT : 2015.April.11 12:56:57 PM
	    System.out.println(date+" -> [yyyy.MMMMM.dd hh:mm:ss aaa] "+new SimpleDateFormat("yyyy.MMMMM.dd hh:mm:ss aaa").format(date));
	    //OUTPUT : 11 April 2015 12:56:57 PM
	    System.out.println(date+" -> [dd MMMMM yyyy hh:mm:ss aaa] "+new SimpleDateFormat("dd MMMMM yyyy hh:mm:ss aaa").format(date));
	    //OUTPUT : 11 April 2015
	    System.out.println(date+" -> [dd MMMMM yyyy] "+new SimpleDateFormat("dd MMMMM yyyy").format(date));
		
	}

}
