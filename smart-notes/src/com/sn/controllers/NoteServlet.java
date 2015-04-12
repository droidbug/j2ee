package com.sn.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sn.services.NoteService;;

/**
 * Servlet implementation class NoteServlet
 */

@WebServlet(name = "Note Servlet", urlPatterns = {"/note/list", "/note/create", "/note/save", "/note/edit", "/note/update" })
public class NoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NoteService  noteService = null;

	static Logger logger = Logger.getLogger(NoteServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String defaultPageUrl = "/note/list.jsp";
		//RequestDispatcher rd = getServletContext().getRequestDispatcher("/note/list.jsp");
		//rd.forward(request, response);
		//String action = request.getServletPath();
		
		String actionUrl = request.getRequestURI();
		logger.info("actionUrl: "+actionUrl);
		
		if(actionUrl.endsWith("/list")){
			//logger.info("actionUrl/list: "+actionUrl);
			defaultPageUrl = "/note/list.jsp";
		} else if(actionUrl.endsWith("/create")){
			//logger.info("actionUrl/create: "+actionUrl);
			defaultPageUrl = "/note/create.jsp";
		} else if(actionUrl.endsWith("/edit")){
			String id = request.getParameter("id");
			String uid = request.getParameter("uid");
			logger.info("actionUrl/edit: "+actionUrl+" id: "+id+" uid: "+uid);
			defaultPageUrl = "/note/edit.jsp";
		}
		this.dispatch(request, response, defaultPageUrl);
		
	}

	protected void dispatch(HttpServletRequest request,
			HttpServletResponse response, String pageUrl)
			throws javax.servlet.ServletException, java.io.IOException {
		logger.info("pageUrl: "+pageUrl);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pageUrl);
		dispatcher.forward(request, response);
	}

}
