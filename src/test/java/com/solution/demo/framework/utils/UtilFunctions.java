package com.solution.demo.framework.utils;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class UtilFunctions {

    public static <K, V> String mapPrettyFormat(Map<K, V> map) {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<K, V>> iter = map.entrySet().iterator();
        sb.append("{");
        sb.append("\n");
        while (iter.hasNext()) {
            Map.Entry<K, V> entry = iter.next();
            sb.append(entry.getKey());
            sb.append('=').append('"');
            sb.append(entry.getValue());
            sb.append('"');
            if (iter.hasNext()) {
                sb.append(",").append("\n");
            }
        }
        sb.append("\n");
        sb.append("}");
        return sb.toString();
    }

    public static <K, V> void mapPrettyPrint(Map<K, V> map) {
        System.out.println(mapPrettyFormat(map));
    }

    //String is blank
    public static boolean isBlank(String input) {
        return input == null || input.trim().isEmpty();
    }

    //Write Map to File
    public static <K, V> void writeMaptoFile(Map<K, V> map, String outputFilePath) {
        // new file object
        File file = new File(Constants.TEST_RESOURCE + File.separator + outputFilePath);
        BufferedWriter bf = null;

        try {
            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file, true));
            // iterate map entries
            for (Map.Entry<K, V> entry : map.entrySet()) {
                // put key and value separated by a colon
                bf.write(entry.getKey().toString() + ":" + entry.getValue().toString());
                // new line
                bf.newLine();
            }
            bf.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // always close the writer
                bf.close();
            } catch (Exception e) {
            }
        }
    }

    public static long generateRandomLong() {
        long x = 000000000000001L;
        long y = 999999999999999L;
        Random r = new Random();
        long value = x + ((long) (r.nextDouble() * (y - x)));
        return Math.abs(value);
    }

    public static String[][] csvToArray(String csvFileName) {
        String thisLine;
        int count = 0;
        FileInputStream fis = null;
        DataInputStream myInput = null;
        List<String[]> lines = new ArrayList<String[]>();
        try {
            fis = new FileInputStream(csvFileName);
            myInput = new DataInputStream(fis);
            int i = 0;

            while ((thisLine = myInput.readLine()) != null) {
                lines.add(thisLine.split(";"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // convert our list to a String array.
        String[][] array = new String[lines.size()][0];
        lines.toArray(array);
        return array;

    }

    public static String[][] arrayTocsv(String csvFileName) {
        String thisLine;
        int count = 0;
        FileInputStream fis = null;
        DataInputStream myInput = null;
        List<String[]> lines = new ArrayList<String[]>();
        try {
            fis = new FileInputStream(csvFileName);
            myInput = new DataInputStream(fis);
            int i = 0;

            while ((thisLine = myInput.readLine()) != null) {
                lines.add(thisLine.split(";"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // convert our list to a String array.
        String[][] array = new String[lines.size()][0];
        lines.toArray(array);
        return array;

    }

    public static void print2dArray(String[][] puzzle) {
        for (String[] row : puzzle) {
            for (String elem : row) {
                System.out.printf("%s ", elem);
            }
            System.out.println();
        }
        System.out.println();
    }

    /*
        public static <K, V> K mapGetKeyFromValue(Map<K, V> map, V value) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                if (entry.getValue().equals(value)) {
                    return entry.getKey();
                }
            }
            return null;
        }

        public static <K, V> Set<K> mapGetKeysFromValue(Map<K, V> map, V value) {
            Set<K> keys = new HashSet<>();
            for (Map.Entry<K, V> entry : map.entrySet()) {
                if (entry.getValue().equals(value)) {
                    keys.add(entry.getKey());
                }
            }
            return keys;
        }
    */
    public static int stringCompare(String str1, String str2) {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int) str1.charAt(i);
            int str2_ch = (int) str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        // Edge case for strings like
        // String 1="Geeks" and String 2="Geeksforgeeks"
        if (l1 != l2) {
            return l1 - l2;
        }

        // If none of the above conditions is true,
        // it implies both the strings are equal
        else {
            return 0;
        }
    }

    /***
     * From a Map, Get first key from value
     * @param map
     * @param value
     * @return
     * @param <K>
     * @param <V>
     */
    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /***
     * From a Map, get all keys matching a value
     * @param map
     * @param value
     * @return
     * @param <K>
     * @param <V>
     */
    public static <K, V> Set<K> getKeys(Map<K, V> map, V value) {
        Set<K> keys = new HashSet<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    public static String getdecodedString(String str) {
        return getdecodedString(str, "UTF-8");
    }

    public static String getdecodedString(String str, String encodingValue) {
        String result = null;

        try {
            result = URLDecoder.decode(str, encodingValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
