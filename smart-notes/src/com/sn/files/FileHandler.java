package com.sn.files;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class FileHandler {
	private final static String EXT_DOT_PROPERTIES = ".properties";
    public  final static String WEB_INF = "WEB-INF";

    /**
     *
     * @param String propKey
     * @param String setPropDefaultValue
     * @return String result 
     */
    public static File getDotPropertiesFileFromWebInfByName(String servletContextRealPathOrHomeDir, String dotPropFileName) {
    	File propertiesFile = null;
    	try {
    		//File propertiesFile = new File(homeDir, "WEB-INF/log4j.properties");
    		String fileFullPath = WEB_INF+"/"+dotPropFileName+EXT_DOT_PROPERTIES;
    		System.out.println("\nservletContextRealPathOrHomeDir : "+servletContextRealPathOrHomeDir+"\nfileFullPath : "+fileFullPath);
        	propertiesFile = new File(servletContextRealPathOrHomeDir, fileFullPath);
        } catch (Exception ex) {
        	System.err.println("Properties File Read Exception: @FileHandler.getDotPropertiesFileFromWebInfByName : "+ex.getMessage());
        	ex.printStackTrace();
        }
    	
        return propertiesFile;
    }
}
