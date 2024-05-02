package com.solution.demo.framework.conf;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * AbstractConfiguration for the project
 */
public abstract class AbstractConfiguration {
    protected static final String SRC_DIR = "src" + File.separator + "test" + File.separator + "resources";
    protected static final String CONF_DIR = SRC_DIR + File.separator + "Configurations";
    protected static final String EXECUTION_CONFIGURATION_FILENAME = CONF_DIR + File.separator + "execution_configuration.properties";
    protected static final String QUERIES_FILENAME = SRC_DIR + File.separator + "database" + File.separator + "queries.properties";

    protected static final String DATABASE_PROPERTIES = SRC_DIR + File.separator + "database" + File.separator + "database.properties";
    protected static final String DATABASE_INI = SRC_DIR + File.separator + "database" + File.separator + "database.ini";

    protected static final String SERVERS_INI = SRC_DIR + File.separator + "server" + File.separator + "servers.ini";

    /***
     * Read the whole Properties File
     * @param Filepath
     * @return Properties Object
     */
    public static Properties getProperties(final String Filepath) {
        final Properties prop = new Properties();
        InputStream input = null;
        try {
            // String current = new java.io.File( "." ).getCanonicalPath();
            // System.out.println("Current dir:"+current);
            input = new FileInputStream(Filepath);
            // load a properties file
            prop.load(input);

        } catch (final IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

    /**
     * @param key           of the property to return
     * @param configuration the properties object to get the property from
     * @return the value of the property that is associated to @key given as
     * parameter in the properties object
     */
    public static String getProperty(final Properties configuration, final String key) {
        final String value = configuration.getProperty(key);
        return value == null ? "" : value;
    }


    public static String getDriversPath() {
        final String value = getProperties(EXECUTION_CONFIGURATION_FILENAME).getProperty("DriversPath");
        return value == null ? "" : value;
    }

    public static boolean isBrowserInstalled(String browserName) {

        return true;
    }

    protected static Properties getExecutionConfigurationProperties(final String FilePath) {
        return getProperties(FilePath);
    }


    /****
     * Convert Properties Object to Object[][]
     * @param properties
     * @return Object[][]
     */
    public static Object[][] getInputDataFromPropertieFile(Properties properties) {
        Map<String, List<String>> map = getDataAsMap(properties);
        int colCount = map.size();
        int rowCount = map.entrySet().iterator().next().getValue().size();
        Object[][] data = new Object[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            int j = 0;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                data[i][j] = entry.getValue().get(i);
                j++;
            }
        }
        return data;
    }

    /****
     * Convert Properties Object to Map<String, List<String>>
     * @param properties
     * @return Map<String, List < String>>
     */
    public static Map<String, List<String>> getDataAsMap(Properties properties) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list;
        Set<Object> keys = properties.keySet();
        Iterator<Object> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            list = Arrays.asList(properties.getProperty(key).split(" , "));
            map.put(key, list);
        }
        return map;
    }

}

