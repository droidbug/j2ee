package com.sn.listeners;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;

import com.sn.files.FileHandler;
import com.sn.manager.DbConnManager;


@WebListener
public class AppContextListener implements ServletContextListener {

	public void initializeLog4j(String servletContextGetRealPath){
		//PropertyConfigurator.configure("./WEB-INF/log4j.properties"); //not working
		//String homeDir = ctx.getRealPath("/");
        //File propertiesFile = new File(homeDir, "WEB-INF/log4j.properties");
		
        try {
			File propertiesFile =  FileHandler.getDotPropertiesFileFromWebInfByName(servletContextGetRealPath, "log4j");
			PropertyConfigurator.configure(propertiesFile.toString());        
			System.out.println("initializeLog4j done");
		} catch (Exception e) {
			System.err.println("Exception @initializeLog4j : "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void initializeDBConnection(ServletContext ctx){
		try {
			DbConnManager connectionManager = new DbConnManager();
			ctx.setAttribute("DBConn", connectionManager.getConnection());
			System.out.println("initializeDBConnection : DB Connection initialized successfully.");
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException @initializeDBConnection : "+e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("SQLException @initializeDBConnection : "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void closeDBConnection(ServletContext ctx){
		Connection con = (Connection) ctx.getAttribute("DBConn");
		try {
			con.close();
			System.out.println("closeDBConnection done.");
		} catch (SQLException e) {
			System.out.println("SQLException @closeDBConnection : "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext ctx = servletContextEvent.getServletContext();
		
		/*ctx.setAttribute("yes", "ppt-val-yes");		
		String value = PropertiesReader.getProperty("yes.val","yesx");		
		System.out.println("value : "+value);		
		System.out.println("ctx.getAttribute(yes) : "+ctx.getAttribute("yes"));*/
		
		this.initializeDBConnection(ctx);
		this.initializeLog4j(ctx.getRealPath("/"));
		
		System.out.println("contextInitialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		this.closeDBConnection(servletContextEvent.getServletContext());
		System.out.println("contextDestroyed");
	}

}
