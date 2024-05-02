package com.solution.demo.tests.ihm.login;

import com.solution.demo.framework.functional.Transverse;
import com.solution.demo.framework.functional.modules.ihm.LoginModule;
import com.solution.demo.framework.functional.webpages.variables.URLS;
import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.TestBase;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestLogin extends TestBase {
    @DisplayName("Test TPG LOGIN TO HOME PAGE")
    @Tags(value = {@Tag("IHM"), @Tag("Test_Login_TPG")})
    @Test
    public void testLoginHomePage() {
        Reporter.getInstance().logInfo("Start Login Test Case.");
        Transverse.get(URLS.TPG_URL_ISNT11);
        LoginModule.login("rootfab", "profils", "EXPLOIT");
        Reporter.getInstance().logInfo("Finish Login Test Case.");
    }
}
