package com.solution.demo.tests.batch;

//import com.smartbear.ready.cmd.runner.pro.SoapUIProTestCaseRunner;
//import org.junit.jupiter.api.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class Batch_001 {
//
//    @DisplayName("TPG 2105 VisioDroit")
//    @Tags(value = {@Tag("BATCH"), @Tag("TPG_2105")})
//    @Test
//    public void lancer_batch001() {
//        //JenkinsUtils jenkinsUtils = new JenkinsUtils(Constants.JENKINS_SERVER, "001-denormalisation-traces-ws-Talend_XX_SPT_CVG");
//        //jenkinsUtils.execute_jenkins_job();
//        try {
//            // Create a test runner object
//            SoapUIProTestCaseRunner runner = new SoapUIProTestCaseRunner();
//
//            // Specify the path to the project file
//            runner.setProjectFile("C://Work//sampleproject.xml");
//
//            // Specify the test suite to run
//            runner.setTestSuite("Test Suite 1");
//
//            // Specify the test case to run
//            // It must be in the test suite specified above
//            runner.setTestCase("Test Case 1");
//
//            // Command the runner to create a printable report
//            runner.setPrintReport(true);
//
//            // Start the test run
//            runner.run();
//        }
//        // Handle the exception, if any
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
