package com.solution.demo.framework.utils;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.webdriver.WebDriverFactory;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public abstract class TestClassBase {

    public void setUp(String _test, List<String> categories) {
        //System.out.println("test_:" + _test);
        /*Instantiate Reporting for IHM Test*/
        Reporter.getInstance().initReporter(WebDriverFactory.getInstance(), _test);
        Reporter.getInstance().newTest(_test, categories);
    }

    public void setupReporter(String _test, List<String> categories) {
        Reporter.getInstance().newTest(_test, categories);
    }

    public void tearDown() {
        boolean testFail = Reporter.getInstance().getTestStatus().equals(LogStatus.ERROR);

        Reporter.getInstance().closeTest();
        if (testFail)
            Assertions.fail();
    }

}
