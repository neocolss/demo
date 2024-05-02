package com.solution.demo.framework.functional.webpages.TPO;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Item;
import com.solution.demo.framework.utils.ItemAction;

public class TPO_PAGE6_CHOIX_BENEFICIARE {
    public Item input_num_ss_assure = new Item("id", "numeroSS", "input num ss assure");
    public Item input_date_naissance = new Item("name", "dateNai", "input date naissance");
    public Item btn_calendar_date_naissance = new Item("id", "dateNaiButton", "btn calendar date naissance");
    public Item input_rang_naissance = new Item("id", "rangNai", "input rang naissance");
    public Item input_num_adherent = new Item("id", "numAdh", "input num adherent");
    public Item input_nom = new Item("id", "nom", "input nom");
    public Item input_prenom = new Item("id", "prenom", "input prenom");


    public void go_action(String action) {
        Reporter.getInstance().newModule();
        Item btn_action = null;

        if (action.equalsIgnoreCase("Rechercher")) {
            btn_action = new Item("//input[@value='Rechercher']", "btn Rechercher");
        }

        if (action.equalsIgnoreCase("Pr�c�dent")) {
            btn_action = new Item("//input[@value='Pr�c�dent']", "btn Pr�c�dent");
        }

        ItemAction.clickJS(btn_action);
        Reporter.getInstance().closeModule();
    }

}
