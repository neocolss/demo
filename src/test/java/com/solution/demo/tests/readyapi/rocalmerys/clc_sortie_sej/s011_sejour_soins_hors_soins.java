package com.solution.demo.tests.readyapi.rocalmerys.clc_sortie_sej;

import com.solution.demo.framework.readyapi.GenericReadyApi;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class s011_sejour_soins_hors_soins {
    @DisplayName("Test ReadyAPI ROCAlmerys CLC_Sortie_SEJ S011-SEJOUR_SOINS_HORS_SOINS")
    @Tags(value = {@Tag("BATCH"), @Tag("ReadyAPI")})
    @Test
    public void test(){
        String project_dir = "C:\\Users\\ymourtaji\\Documents\\TPS-TNR\\TPS\\ROCAlmerys";
        String suite_name  = "CLC_Sortie_SEJ";
        String test_name   = "S011-SEJOUR_SOINS_HORS_SOINS";

        String reportDir = GenericReadyApi.runTestCase(project_dir, suite_name, test_name);
        String test_case_status = GenericReadyApi.getResultReadyAPI(reportDir);

        assertEquals(test_case_status, "PASS");
    }
}
