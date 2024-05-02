package com.solution.demo.framework.data;

import com.solution.demo.framework.conf.Configuration;
import com.solution.demo.framework.conf.IniFile;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Generic Class to Handle Connection to a Database
 */
public class DB {
    private String DB_DRIVER;
    private String DB_HOST;
    private String DB_URL;
    private String DB_USERNAME;
    private String DB_PASSWORD;
    private String DB_PORT;
    private String env;
    private Connection conn;


    public DB(String _env) {

        IniFile iniFile = Configuration.databaseIniConf;

        this.DB_HOST = iniFile.getString(_env, "DB_HOST");
        this.DB_USERNAME = iniFile.getString(_env, "DB_USERNAME");
        this.DB_PASSWORD = iniFile.getString(_env, "DB_PASSWORD");

        String DB_TYPE = iniFile.getString(_env, "DB_TYPE");
        if ("oracle".equalsIgnoreCase(DB_TYPE)) {
            this.DB_PORT = iniFile.getString(_env, "PORT_ORCL", "1521");
            this.DB_URL = "jdbc:oracle:thin:@" + this.DB_HOST + ":" + this.DB_PORT + "/" + iniFile.getString(_env, "DB_NOM_SERVICE");
            this.DB_DRIVER = iniFile.getString("common", "Driver_orcl", "oracle.jdbc.driver.OracleDriver");
        } else if ("postgresql".equalsIgnoreCase(DB_TYPE)) {
            this.DB_PORT = iniFile.getString(_env, "PORT_POSTGRESQL", "5432");
            this.DB_URL = "jdbc:postgresql://" + this.DB_HOST + ":" + this.DB_PORT + "/" + iniFile.getString(_env, "DB_NAME");
            this.DB_DRIVER = iniFile.getString("common", "Driver_postgresql", "org.postgresql.Driver");
        }
        System.out.println("credentials for env:" + _env + " => " + DB_TYPE + ", PORT:" + DB_PORT + ", URL:" + DB_URL + ", DRIVER:" + DB_DRIVER + ", USER:" + DB_USERNAME + ", PASS:" + DB_PASSWORD);
        this.env = _env;
    }

    public void connect() {
        System.out.println("------------------------------------");
        try {
            Class.forName(getDB_DRIVER());
            conn = DriverManager.getConnection(getDB_URL(), getDB_USERNAME(), getDB_PASSWORD());
            if (conn != null) {
                System.out.println("Successfully connected to : " + env);
            } else {
                System.out.println("Failed to connect to : " + env);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------------");
    }


    public Connection getConnection() {
        return conn;
    }

    public void disconnect() {
        System.out.println("------------------------------------");
        try {
            if (conn != null) {
                conn.close();
                System.out.println("database connection to " + env + " is Closed.");
            } else {
                System.out.println("database connection to " + env + " is already Closed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------------");
    }

    public String getDB_DRIVER() {
        return DB_DRIVER;
    }

    public void setDB_DRIVER(String DB_DRIVER) {
        this.DB_DRIVER = DB_DRIVER;
    }

    public String getDB_HOST() {
        return DB_HOST;
    }

    public void setDB_HOST(String DB_HOST) {
        this.DB_HOST = DB_HOST;
    }

    public String getDB_URL() {
        return DB_URL;
    }

    public void setDB_URL(String DB_URL) {
        this.DB_URL = DB_URL;
    }

    public String getDB_USERNAME() {
        return DB_USERNAME;
    }

    public void setDB_USERNAME(String DB_USERNAME) {
        this.DB_USERNAME = DB_USERNAME;
    }

    public String getDB_PASSWORD() {
        return DB_PASSWORD;
    }

    public void setDB_PASSWORD(String DB_PASSWORD) {
        this.DB_PASSWORD = DB_PASSWORD;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

}
