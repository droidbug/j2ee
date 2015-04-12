package com.sn.files;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertiesReader {
	private static Properties ppt;
    public final static String APP_CONFIG_FILE_NAME = "app-config.properties";

    /**
     *
     * @param String propKey
     * @param String setPropDefaultValue
     * @return String result 
     */
    public static String getProperty(String propKey, String setPropDefaultValue) {
        if (ppt == null) {
            ppt = new Properties();
            try {
                URL url = PropertiesReader.class.getResource("PropertiesReader.class");
                String thisPath = url.getPath();
                thisPath = thisPath.substring(0, thisPath.lastIndexOf("classes")) + APP_CONFIG_FILE_NAME;
                url = new URL("file://" + thisPath);
                InputStream stream = url.openStream();
                ppt.load(stream);
                stream.close();
            } catch (Throwable k) {}
        }
        String result = ppt.getProperty(propKey, setPropDefaultValue.toString());
        return result != null ? result : setPropDefaultValue;
    }


    private static Properties pptOfFile;

    /**
     *
     * @param String dotPropertiesFileName
     * @param String propKey
     * @param String setPropDefaultValue
     * @return String result
     */
    public static String getPropertyOfFile(String dotPropertiesFileName, String propKey, String setPropDefaultValue) {
    	pptOfFile = new Properties();
        try {
            URL url = PropertiesReader.class.getResource("PropertiesReader.class");
            String thisPath = url.getPath();
            thisPath = thisPath.substring(0, thisPath.lastIndexOf("classes")) + dotPropertiesFileName+".properties";
            url = new URL("file://" + thisPath);
            InputStream stream = url.openStream();
            pptOfFile.load(stream);
            stream.close();
        } catch (Throwable k) {}
        String result = pptOfFile.getProperty(propKey, setPropDefaultValue.toString());
        return result != null ? result : setPropDefaultValue;
    }
}
