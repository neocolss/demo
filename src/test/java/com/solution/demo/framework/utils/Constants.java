package com.solution.demo.framework.utils;

import java.io.File;

public class Constants {

    // Reporting and ExtentReport file to SquashTA Target Folder
    public static final String reportPath = "target/squashTA/reporting/";
    public static final String reportName = "report.html";
    public static final String extentReportPath = "src/test/resources/extents-config.xml";

    // Webdriver
    public static final int DEFAULT_TIMEOUT = 30; // 120
    public static final int DEFAULT_TIMEOUT_MINOR = 5;


    //public static final String TEST_RESOURCE = "src/test/resources/";
    public static final String TEST_RESOURCE = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    public static final String SHELL_DIRECTORY = TEST_RESOURCE + "batchs" + File.separator;
    public static final String chromeDriver = "src/test/resources/drivers/chrome/chromedriver";
    public static final String fireFoxDriver = "src/test/resources/drivers/firefox/geckodriver";
    public static final String edgeDriver = TEST_RESOURCE + "drivers" + File.separator + "edge" + File.separator + "msedgedriver";

    // selenium fileUpload
    public static final String autoitscriptPath = "src/test/resources/selenium/File_upload_selenium_webdriver_%BROWSER%.au";

    // DEBUG
    public static boolean DEV_MODE = false;

    // Squash WS Rest API
    public static final String HOST_NAME = "http://xxx.xxx.xxx.xxxx:8080/squash";
    public static final String END_POINT = "/api/rest/latest/";


    //Jenkins
    public static final String JENKINS_SERVER = "http://ret1crtcvgctp07.hosting.xxxx.cloud/jenkins/";
    public static final String JENKINS_TOKEN = "ansible";

    //Server PILOTAGE
    public static final String PILOTAGE_SERVER = "ret1crtcvgctp01.production.net";

    //Server i8Odc3
    public static String i8Odc3_SERVER = "tet1edttpg01.ext.tdc";
    public static String i8Odc7_SERVER = "tet1edttpg01.ext.tdc";

    public static String dctpbdda02_SERVER = "dctpbdda02.int.tdc";

    public static final String JENKINS_SERVER_990 = "http://xxxxxxx.int.tdc/jenkins/view/DEV2_I8ODC3_CDP/";
}
