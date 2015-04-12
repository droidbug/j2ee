package com.sn.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sn.files.PropertiesReader;

public class DbConnManager {
	private Connection connection;

	public DbConnManager() throws ClassNotFoundException, SQLException {
		try {
			String dataSourceUrl				= PropertiesReader.getProperty("dataSource.url","jdbc:mysql://localhost/smart_notes?useUnicode=true&characterEncoding=utf8&generateSimpleParameterMetadata=true");
			//jdbc:mysql://192.168.4.128/jsp_servlet?useUnicode=true&characterEncoding=utf8&generateSimpleParameterMetadata=true
			String 	dataSourceDriverClassName 	= PropertiesReader.getProperty("dataSource.driverClassName","com.mysql.jdbc.Driver");
			String 	dataSourcePort 				= PropertiesReader.getProperty("dataSource.port","3306");
			String 	dataSourceUsername			= PropertiesReader.getProperty("dataSource.username","root");
			String 	dataSourcePassword			= PropertiesReader.getProperty("dataSource.password","roots");
			//String value 						= PropertiesReader.getProperty("no.val","nox");
			
			Class.forName(dataSourceDriverClassName);
			//Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(dataSourceUrl, dataSourceUsername, dataSourcePassword);
		} catch (Exception e) {
			System.err.println("Contructor: @DbConnManager() : "+e.getMessage());
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		System.out.println("DbConnManager @getConnection : called");
		return this.connection;
	}
}
