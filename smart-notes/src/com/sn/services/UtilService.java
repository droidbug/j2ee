package com.sn.services;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class UtilService {

	public static void buildDynamicObjectFromResultSet(ResultSet resultSet) throws SQLException {
		Map<String, String> keyMap = new HashMap<String, String>();
		ResultSetMetaData rsMetaData = resultSet.getMetaData();
	    int numberOfColumns = rsMetaData.getColumnCount();
	    for (int i = 1; i < numberOfColumns + 1; i++) {
	      String columnName = rsMetaData.getColumnName(i);
	      System.out.print(columnName + "   ");

	    }
	    System.out.println();
	    System.out.println("----------------------");

	    while (resultSet.next()) {
	      for (int i = 1; i < numberOfColumns + 1; i++) {
	        System.out.print(resultSet.getString(i) + "   ");
	      }
	      System.out.println();
	    }
	}
}
