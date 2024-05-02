package com.solution.demo.tests.ihm.TPG_4489;

import com.solution.demo.framework.functional.Transverse;
import com.solution.demo.framework.functional.modules.ihm.HomePageModule;
import com.solution.demo.framework.functional.modules.ihm.LoginModule;
import com.solution.demo.framework.functional.repository.Repository;
import com.solution.demo.framework.functional.webpages.variables.URLS;
import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.ItemAction;
import com.solution.demo.framework.utils.TestBase;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_TPG_4489_Consulter_AMC extends TestBase {
    @DisplayName("Test TPG 4489 Consulation AMC")
    @Test
    @Tags(value = {@Tag("IHM"), @Tag("TPG_4489"), @Tag("AMC")})
    public void test() {
        String num_amc = "0045004429";
        Reporter.getInstance().logInfo("==> Start TPG 4489 Consulation AMC Test Case. ==>");

        Transverse.get(URLS.TPG_URL_ISNT10);
        LoginModule.login("rootfab", "profils", "ADMIN");

        HomePageModule.goToPage("Gestion des AMC");
        ItemAction.switchToFrameByName("topFrame");

        //Premiere Page
        //Saisir N AMC
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_num_amc, num_amc);
        Repository.tpg_gestion_amc_page.action("Valider");

        ItemAction.switchToParentFrame();
        ItemAction.switchToFrameByName("bottomFrame");

        List<WebElement> liste_HREF_AMC = Repository.tpg_gestion_amc_page.getAllHREFfromListeAMC();

        //click on first HREF from : Num.AMC/Norme/Sous Norme
        liste_HREF_AMC.get(0).click();

        //Attendre le chargement de la page
        ItemAction.waitUntilJSReady();

        ItemAction.switchToParentFrame();
        ItemAction.switchToFrameByName("topFrame");

        //Page 1 : Affichage des premieres informations
        String xpath_num_amc = "(" + Repository.tpg_gestion_amc_page.input_nouveau_N.getXpath() + "/ancestor::*//tr[@class='LibelleJ1']//td[@class='DonneeJ1'])[1]";
        System.out.println("xpath_num_amc : " + xpath_num_amc);
        String numero_amc = ItemAction.getElementJS(xpath_num_amc).getText();
        System.out.println("numero amc:" + numero_amc);

        String xpath_cle_noemie = "(//tr[@class='LibelleJ1']//td[@class='DonneeJ1'])[2]";
        String valeur_cle_noemie = ItemAction.getElementJS(xpath_cle_noemie).getText();
        System.out.println("valeur_cle_noemie:" + valeur_cle_noemie);

        String xpath_norme = "(//tr[@class='LibelleJ1']//td[@class='DonneeJ1'])[3]";
        String valeur_norme = ItemAction.getElementJS(xpath_norme).getText();
        System.out.println("valeur_norme:" + valeur_norme);

        String xpath_nom_amc = "(//*[@name = 'Nom1']/ancestor::*//tr[@class='DonneeJ2']//td)[4]";
        String valeur_nom = ItemAction.getElementJS(xpath_nom_amc).getText();
        System.out.println("valeur_nom:" + valeur_nom);

        String xpath_nom_long_amc = "(//*[@name = 'Nom1']/ancestor::*//tr[@class='DonneeJ2']//td)[5]";
        String valeur_nom_long = ItemAction.getElementJS(xpath_nom_long_amc).getText();
        System.out.println("valeur_nom_long:" + valeur_nom_long);

        String xpath_validite = "(" + Repository.tpg_gestion_amc_page.btn_radio_valide.getXpath() + "/ancestor::*/tr[@class='DonneeJ1']//td)[4]";
        String valeur_validite = ItemAction.getElementJS(xpath_validite).getText();
        System.out.println("valeur_validite : " + valeur_validite);

        String xpath_code_comptable = "(" + Repository.tpg_gestion_amc_page.input_code_comptable.getXpath() + "/ancestor::*/tr[@class='DonneeJ1']//td)[6]";
        String valeur_code_comptable = ItemAction.getElementJS(xpath_code_comptable).getText();
        System.out.println("valeur_code_comptable : " + valeur_code_comptable);

        String xpath_pt_remise = "((" + Repository.tpg_gestion_amc_page.input_point_remise.getXpath() + "/ancestor::*/tr[@class='DonneeJ1'])[2]//td)[2]";
        String valeur_pt_remise = ItemAction.getElementJS(xpath_pt_remise).getText();
        System.out.println("valeur_pt_remise : " + valeur_pt_remise);

        Reporter.getInstance().closeModule();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Reporter.getInstance().logInfo("<== Finish TPG 4489 Consulation AMC Test Case.<==|");

    }
}
