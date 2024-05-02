package com.solution.demo.framework.functional.webpages.TPO;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Item;
import com.solution.demo.framework.utils.ItemAction;

public class TPO_PAGE4_SelectionPS {

    //action possible :
    public void go_action(String action) {
        Reporter.getInstance().newModule();
        Item btn_action = null;

        if (action.equalsIgnoreCase("AMC Bénéficiaire via base Aiguillage")) {
            btn_action = new Item("name", "suivant", "btn rechercher");
        }

        if (action.equalsIgnoreCase("Sélection directe de l'AMC")) {
            btn_action = new Item("name", "suivant2", "btn Annuler");
        }

        if (action.equalsIgnoreCase("Précédent")) {
            btn_action = new Item("name", "retour", "btn Annuler");
        }

        ItemAction.click(btn_action);
        Reporter.getInstance().closeModule();
    }
}
