package com.solution.demo.framework.conf;

import java.util.Properties;

public class Configuration extends AbstractConfiguration {

    /*Queries est un fichier où vous pouvez mettre des reuêtes SQL génériques*/
    public static Properties queriesFile = getExecutionConfigurationProperties(QUERIES_FILENAME);

    /*Ce fichier Properties des Bases de données(Obsoléte, utilisez databaseIniConf)*/
    public static Properties databaseProperties = getExecutionConfigurationProperties(DATABASE_PROPERTIES);

    /*Fichier Ini des configurations des bases des données*/
    public static IniFile databaseIniConf = new IniFile(DATABASE_INI);

    /*Fichier Ini des configurations des serveurs*/
    public static IniFile serversIniConf = new IniFile(SERVERS_INI);


}
