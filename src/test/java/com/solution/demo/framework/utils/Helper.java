package com.solution.demo.framework.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Helper {

    public static String getFormatedDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        return dateFormat.format(new Date());
    }

    public static String getFormatedDate(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date());
    }

    public static String getTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //return String.valueOf(timestamp.getTime()).substring(0, 6);
        return String.valueOf(timestamp.getTime());

    }

    public static String CalculateMonth(int duree) {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        calendar.add(Calendar.MONTH, duree);

        String d2 = sdf.format(calendar.getTime());

        return d2;

    }

    public static String CalculateYear(int duree) {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        calendar.add(Calendar.YEAR, duree);
        String d2 = sdf.format(calendar.getTime());

        return d2;

    }

    public static void WriteProperties(String nom, String prenom) {

        Properties prop = load("config.properties");
        OutputStream output = null;

        try {

            output = new FileOutputStream("config.properties");

            // set the properties value
            prop.setProperty(nom, prenom);

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static String getProperty(String key) {
        Properties prop = load("config.properties");
        return prop.getProperty(key);
    }

    public static Properties load(String filename) {
        Properties properties = new Properties();

        FileInputStream input = null;
        try {
            input = new FileInputStream(filename);

            properties.load(input);
            input.close();

        } catch (Exception e) {
            System.out.println("Erreur lecture fichier :" + filename + "");
            System.out.println(e.toString());
        }
        return properties;
    }

    public static long getRandomNumber() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

}
