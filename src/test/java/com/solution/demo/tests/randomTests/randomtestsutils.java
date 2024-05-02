package com.solution.demo.tests.randomTests;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Random;

import com.solution.demo.framework.functional.randomClass.Person;

public class randomtestsutils {

    static long generateRandomLong(int numberOfDigits) {
        long x = 000000000000001L;
        long y = 999999999999999L;
        Random r = new Random();
        long value = x + ((long) (r.nextDouble() * (y - x)));
        return Math.abs(value);
    }

    static String GenerateRandomNumber(int charLength) {
        return String.valueOf(charLength < 1 ? 0 : Math.abs(new Random().nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1) + (int) Math.pow(10, charLength - 1)));
    }

    static LinkedHashMap<Integer, Person> remplirMap(String csvFileName) {
        String thisLine;
        int count = 0;
        FileInputStream fis = null;
        DataInputStream myInput = null;
        //List<String[]> lines = new ArrayList<String[]>();
        LinkedHashMap<Integer, Person> output = new LinkedHashMap<Integer, Person>();

        try {
            fis = new FileInputStream(csvFileName);
            myInput = new DataInputStream(fis);
            int i = 0;

            while ((thisLine = myInput.readLine()) != null) {
                //lines.add(thisLine.split(";"));
                String[] line = thisLine.split(";");
                Person p = new Person();
                p.setNom(line[0]);
                p.setPrenom(line[1]);
                p.setAge(line[2]);
                output.put(count, p);
                ++count;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return output;
    }


    public static String encodeString(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        System.out.println(encodeString("CasOK%28IDB-detail%2Cdossier%29"));


    }

}
