package com.solution.demo.tests.readyapi.visiodroit.casok;

import com.solution.demo.framework.readyapi.GenericReadyApi;
import com.solution.demo.framework.utils.UtilFunctions;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RouterVersSDDSPBDD {
    @DisplayName("Test ReadyAPI Visiodroit CasOK RouterVersSDDSPBDD")
    @Tags(value = {@Tag("BATCH"), @Tag("ReadyAPI")})
    @Test
    public void execute()  {
        String project_dir = "C:\\Repos\\TPS-TNR\\TPS\\Visiodroit";
        String suite_name = "CasOK";
        String test_name = UtilFunctions.getdecodedString("RouterVersSDDSPBDD");

        String reportDir = GenericReadyApi.runTestCase(project_dir, suite_name, test_name);
        String test_case_status = GenericReadyApi.getResultReadyAPI(reportDir);

        assertEquals(test_case_status, "PASS");
    }
}
