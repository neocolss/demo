package com.solution.demo.framework.reporting;

import com.solution.demo.framework.utils.Constants;
import com.solution.demo.framework.utils.Helper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Reporting using Extent Reports (This is for IHM Test Only : ItemAction class)
 */
public class Reporter {

    private String reportFile;
    private WebDriver driver;
    public static ExtentReports extent;

    private ExtentTest test;
    private String currentModule;

    private boolean isInitialized;
    private int currentStep;

    private static Reporter INSTANCE = null;

    public static Reporter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Reporter();
        return INSTANCE;
    }

    private Reporter() {
        this.isInitialized = false;
        this.currentModule = "";
    }

    public void initReporter(WebDriver _driver, String title) {
        this.driver = _driver;

        if (this.isInitialized)
            return;
        this.reportFile = Constants.reportPath + Constants.reportName;

        extent = new ExtentReports(reportFile, true, NetworkMode.OFFLINE);

        if (!Constants.DEV_MODE) {
            extent.loadConfig(new File(Constants.extentReportPath));
        }

        this.isInitialized = true;
        this.currentStep = 0;
    }

    public void closeReport() {
        if (extent != null) {
            extent.flush();
            //extent.close();
        }
    }

    private void log(LogStatus _status, String _action, String _message) {
        _action = (_action.length() > 0) ? "[ " + _action + " ] " : "";
        System.out.println("Status=" + _status + ", currentModule=" + currentModule + ", _action=" + _action + ", _message=" + _message);
        test.log(_status, currentModule, _action + _message);
    }

    public void logSuccess(String _message) {
        //System.out.println("success=" + _message);
        logSuccess("LOG", _message);
    }

    public void logSuccess(String _action, String _message) {
        log(LogStatus.PASS, _action, _message);
    }

    public void logInfo(String _message) {
        //System.out.println("info=" + _message);
        log(LogStatus.INFO, "", _message);
    }

    public void logError(String _message) {
        //System.out.println("error=" + _message);
        logError("", _message);
    }

    public void logError(String _action, String _message) {
        String screenShotPath = "<br>" + test.addScreenCapture(takeScreenShot());
        log(LogStatus.ERROR, _action, _message + screenShotPath);
        //new AssertionError(_action + " - " + _message);
    }

    public void logFail(String _message) {
        //System.out.println("fail=" + _message);
        logFail("", _message);
    }

    public void logFail(String _action, String _message) {
        String screenShotPath = "<br>" + test.addScreenCapture(takeScreenShot());
        log(LogStatus.FAIL, _action, _message + screenShotPath);
        closeTest();
        Assertions.fail();
    }

    public void logWarning(String _message) {
        //System.out.println("warning=" + _message);
        logWarning("", _message);
    }

    public void logWarning(String _action, String _message) {
        log(LogStatus.WARNING, _action, _message);
    }

    public void newTest(String _test, List<String> categories) {
        this.currentStep = 0;
        test = extent.startTest(_test);
        if (categories.size() > 0) {
            if (categories.size() == 1)
                test.assignCategory(categories.get(0));
            else
                test.assignCategory(categories.get(0), categories.get(1));
        } else {
            test.assignCategory("new Category.");
        }
    }

    public void closeTest() {
        this.currentModule = "";
        extent.endTest(test);
        extent.flush();
        // extent.close();
    }

    public void newModule() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        if (this.currentModule.equals("")) {
            this.currentStep++;
            this.currentModule = "[ " + this.currentStep + " ] " + stackTraceElements[2].getMethodName();
        }
    }

    public void closeModule() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (this.currentModule.equals("[ " + this.currentStep + " ] " + stackTraceElements[2].getMethodName()))
            currentModule = "";
    }

    private String takeScreenShot() {
        String screenShotFile = "< Erreur Capture ecran ! >";
        String screenShotName = test.getTest().getName().replaceAll("==>", "-") + "-" + Helper.getFormatedDate()
                + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String tmp = Constants.reportPath + "/screenshot/" + "/" + screenShotName;
            FileUtils.copyFile(srcFile, new File(tmp));
            screenShotFile = tmp.replace(Constants.reportPath, ".");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenShotFile;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public LogStatus getTestStatus() {
        return Reporter.getInstance().test.getRunStatus();
    }

}
