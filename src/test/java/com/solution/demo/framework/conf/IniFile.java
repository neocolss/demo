package com.solution.demo.framework.conf;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * Generic Class to Load a ini File
 */
public class IniFile {

    private Pattern _section = Pattern.compile("\\s*\\[([^]]*)\\]\\s*");
    private Pattern _keyValue = Pattern.compile("\\s*([^=]*)=(.*)");
    private Map<String, Map<String, String>> _entries = new HashMap<>();

    public IniFile(String path) /*throws IOException*/ {
        try {
            load(path);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void load(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            String section = null;
            while ((line = br.readLine()) != null) {
                Matcher m = _section.matcher(line);
                if (m.matches()) {
                    section = m.group(1).trim();
                } else if (section != null) {
                    m = _keyValue.matcher(line);
                    if (m.matches()) {
                        String key = m.group(1).trim();
                        String value = m.group(2).trim();
                        Map<String, String> kv = _entries.get(section);
                        if (kv == null) {
                            _entries.put(section, kv = new HashMap<>());
                        }
                        kv.put(key, value);
                    }
                }
            }
        }
    }

    /***
     * Get Value From Key with default value
     * @param section
     * @param key
     * @param defaultvalue
     * @return String of the value from the key
     */
    public String getString(String section, String key, String defaultvalue) {
        Map<String, String> kv = _entries.get(section);
        if (kv == null) {
            return defaultvalue;
        }
        return kv.get(key);
    }

    /***
     * Get Value From Key without default value
     * @param section
     * @param key
     * @return N/A if there is no key or value found
     */
    public String getString(String section, String key) {
        Map<String, String> kv = _entries.get(section);
        if (kv == null) {
            return "N/A";
        }
        return kv.get(key);
    }

    /***
     * Return Integer of the value from Key with a default value
     * @param section
     * @param key
     * @param defaultValue
     * @return Integer of the value from Key with a default value
     */
    public int getInt(String section, String key, int defaultValue) {
        Map<String, String> kv = _entries.get(section);
        if (kv == null) {
            return defaultValue;
        }
        return Integer.parseInt(kv.get(key));
    }

    /***
     * Return Integer of the value from Key without a default value
     * @param section
     * @param key
     * @return Integer of the value from Key, -1 if there is no value or key
     */
    public int getInt(String section, String key) {
        Map<String, String> kv = _entries.get(section);
        if (kv == null) {
            return -1;
        }
        return Integer.parseInt(kv.get(key));
    }

    /***
     * Return Float of the value from Key with a default value
     * @param section
     * @param key
     * @param defaultValue
     * @return Float of the value from Key with a default value
     */
    public float getFloat(String section, String key, float defaultValue) {
        Map<String, String> kv = _entries.get(section);
        if (kv == null) {
            return defaultValue;
        }
        return Float.parseFloat(kv.get(key));
    }

    /***
     * Return Float of the value from Key without a default value
     * @param section
     * @param key
     * @return Float of the value from Key or -1.0f if is there is no key or value
     */
    public float getFloat(String section, String key) {
        Map<String, String> kv = _entries.get(section);
        if (kv == null) {
            return -1.0f;
        }
        return Float.parseFloat(kv.get(key));
    }

    /***
     * Return Double of the value from Key with a default value
     * @param section
     * @param key
     * @param defaultValue
     * @return Double of the value from Key with a default value
     */
    public double getDouble(String section, String key, double defaultValue) {
        Map<String, String> kv = _entries.get(section);
        if (kv == null) {
            return defaultValue;
        }
        return Double.parseDouble(kv.get(key));
    }

    /***
     * Return Double of the value from Key without a default value
     * @param section
     * @param key
     * @return Double of the value from Key or -1d if is there is no key or value
     */
    public double getDouble(String section, String key) {
        Map<String, String> kv = _entries.get(section);
        if (kv == null) {
            return -1d;
        }
        return Double.parseDouble(kv.get(key));
    }

}
