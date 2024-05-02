package com.solution.demo.tests.ihm.TPG_4490;

import com.solution.demo.framework.functional.Transverse;
import com.solution.demo.framework.functional.modules.ihm.HomePageModule;
import com.solution.demo.framework.functional.modules.ihm.LoginModule;
import com.solution.demo.framework.functional.modules.ihm.OCTPAGE1Module;
import com.solution.demo.framework.functional.modules.ihm.OCTPAGE2Module;
import com.solution.demo.framework.functional.webpages.variables.URLS;
import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.ItemAction;
import com.solution.demo.framework.utils.TestBase;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_TPG_4490_CREER_OCT extends TestBase {

    @DisplayName("TPG 4490 Créer un nouveau OCT")
    @Test
    @Tags(value = {@Tag("IHM"), @Tag("TPG_4490")})
    public void testCreerNouveauOCT() {
        Reporter.getInstance().logInfo("==> Start TPGCONVERG_4490 Gestion des OCT : Création Test Case. ==>");

        Transverse.get(URLS.TPG_URL_ISNT11);
        LoginModule.login("rootfab", "profils", "ADMIN");

        HomePageModule.goToPage("Gestion des OCT");


        ItemAction.switchToFrameByName("topFrame");
        OCTPAGE1Module.saisirOCT("02319922835588");
        OCTPAGE1Module.go_action("nouveau");

        ItemAction.switchToParentFrame();

        //Remplir les informations premiere
        OCTPAGE2Module.remplir_info_oct();

        //cliquer sur suivant
        OCTPAGE2Module.action("Suivant");

        //Remplir les informations compte bancaire
        OCTPAGE2Module.remplir_info_cb_oct();

        //cliquer sur Valider
        OCTPAGE2Module.action("Valider");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Reporter.getInstance().logInfo("<== Finish TPGCONVERG_4490 Gestion des OCT : Création Test Case.<==|");
    }
}
