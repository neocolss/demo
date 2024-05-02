package com.solution.demo.framework.readyapi;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Constants;
import com.solution.demo.framework.utils.UtilFunctions;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class GenericReadyApi {

    /***
     * Run ReadyAPI testcase
     * @param project_dir
     * @param suite_name
     * @param testcase_name
     * @return ProjectDirectory Report
     */
    public static String runTestCase(String project_dir, String suite_name, String testcase_name){
        Reporter.getInstance().newModule();
        System.out.println("test_name:"+testcase_name);
        String format_test_name = UtilFunctions.getdecodedString(testcase_name);
        System.out.println("format_test_name:"+format_test_name);
        String reportDir = suite_name+"."+format_test_name.replaceAll("[\\(,)'-]", "_");
        System.out.println("reportDir:"+reportDir);

        try {
            // https://support.smartbear.com/readyapi/docs/functional/running/automating/cli.html
            Process p = Runtime.getRuntime().exec("cmd /c start /wait " + Constants.SHELL_DIRECTORY+"SoapUIGeneric.bat" + " " + project_dir + " " + suite_name + " " + "\""+format_test_name+"\"" + " " + reportDir);
            p.waitFor();
            System.out.println("Batch file done.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Reporter.getInstance().closeModule();
        return reportDir;
    }

    /***
     * Search the file test_case_run_log_report.xml in the report dir
     */
    public static String getResultReadyAPI(String reportDir){
        Reporter.getInstance().newModule();
        File[] test_case_run_log_report = (new File(Constants.SHELL_DIRECTORY+File.separator+reportDir)).listFiles((dir1, name) -> name.startsWith("test_case_run_log_report.xml"));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        String test_case_status_attribute = "";
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(test_case_run_log_report[0].getAbsoluteFile());
            doc.getDocumentElement().normalize();
            //String test_case_attribute = doc.getDocumentElement().getAttribute("testCase");
            test_case_status_attribute = doc.getDocumentElement().getAttribute("status");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Reporter.getInstance().closeModule();
        return test_case_status_attribute;
    }
}
