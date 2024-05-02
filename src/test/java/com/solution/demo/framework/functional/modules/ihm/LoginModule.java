package com.solution.demo.framework.functional.modules.ihm;

import com.solution.demo.framework.functional.repository.Repository;
import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.ItemAction;
import org.openqa.selenium.support.ui.Select;

/**
 * TPG HOME PAGE Login
 */
public class LoginModule {
    public static void login(String identifiant, String motDePasse, String groupe) {
        Reporter.getInstance().newModule();
        ItemAction.setValue(Repository.tpg_loginpage.input_identifiant, identifiant);
        //input_identifiant.sendKeys(identifiant);
        ItemAction.setValue(Repository.tpg_loginpage.input_motDePasse, motDePasse);
        //input_motDePasse.sendKeys(motDePasse);
        selectionnerGroupe(groupe);
        ItemAction.click(Repository.tpg_loginpage.btn_connexion);
        Reporter.getInstance().closeModule();
    }

    public static void selectionnerGroupe(String groupName) {
        Reporter.getInstance().newModule();
        ItemAction.click(Repository.tpg_loginpage.select_groupe);
        //select_groupe.click();
        Select Groupe = new Select(ItemAction.getElement(Repository.tpg_loginpage.select_groupe.getXpath()));
        Groupe.selectByValue(groupName);
        Reporter.getInstance().closeModule();
    }
}
