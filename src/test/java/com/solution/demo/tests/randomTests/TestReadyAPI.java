package com.solution.demo.tests.randomTests;

//import com.eviware.soapui.impl.wsdl.WsdlProject;
//import com.eviware.soapui.model.support.PropertiesMap;
//import com.eviware.soapui.model.testsuite.TestCase;
//import com.eviware.soapui.model.testsuite.TestRunner;
//import com.eviware.soapui.model.testsuite.TestSuite;
//import org.junit.jupiter.api.*;
//
//import static org.junit.Assert.assertEquals;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class TestReadyAPI {
//
//    @DisplayName("TPG 2105 VisioDroit")
//    @Tags(value = {@Tag("BATCH"), @Tag("TPG_2105")})
//    @Test
//    public void testRunner() throws Exception {
//        WsdlProject project = new WsdlProject("C:\\Repos\\TPS-TNR\\TPS\\Visiodroit\\settings.xml");
//        TestSuite testSuite = project.getTestSuiteByName( "CasOK" );
//        TestCase testCase = testSuite.getTestCaseByName( "RouterVersSDDSPBDD.xml" );
//        // create empty properties and run synchronously
//        TestRunner runner = testCase.run( new PropertiesMap(), false );
//        assertEquals( TestRunner.Status.FINISHED, runner.getStatus() );
//    }
//
//}
