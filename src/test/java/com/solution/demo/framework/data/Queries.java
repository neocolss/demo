package com.solution.demo.framework.data;

import com.solution.demo.framework.conf.Configuration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Handle Queries to a Database
 */
public class Queries {
    private static Properties QueriesFile = Configuration.queriesFile;

    public static String getQuery(String query) {
        System.out.println("------------------------------------");
        query = QueriesFile.getProperty(query) == null ? query : QueriesFile.getProperty(query);
        System.out.println("Query to execute:");
        System.out.println(query);
        return query;
    }

    public static PreparedStatement createQuery(String query, String env) {
        try {
            DB_Instances.get(env).connect();
            return DB_Instances.get(env).getConnection().prepareStatement(Queries.getQuery(query));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------------");
        return null;
    }

    public static ResultSet getDatafromSelectQuery(String selectQuery, String env) {

        PreparedStatement ps = createQuery(selectQuery, env);
        System.out.println("Getting Results data....");
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("------------------------------------");
        return rs;
    }
}
