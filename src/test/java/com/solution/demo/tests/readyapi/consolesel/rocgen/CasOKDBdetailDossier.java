package com.solution.demo.tests.readyapi.consolesel.rocgen;

import com.solution.demo.framework.readyapi.GenericReadyApi;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CasOKDBdetailDossier {
    @DisplayName("Test ReadyAPI ConsoleSEL ROCGEN CasOK%28IDB-detail%2Cdossier%29")
    @Tags(value = {@Tag("BATCH"), @Tag("ReadyAPI")})
    @Test
    public void test(){
        String project_dir = "C:\\Users\\ymourtaji\\Documents\\TPS-TNR\\TPS\\ConsoleSEL";
        String suite_name = "ROCGEN";
        String test_name = "CasOK%28IDB-detail%2Cdossier%29";


        String reportDir = GenericReadyApi.runTestCase(project_dir, suite_name, test_name);
        String test_case_status = GenericReadyApi.getResultReadyAPI(reportDir);

        assertEquals(test_case_status, "PASS");
    }

}
