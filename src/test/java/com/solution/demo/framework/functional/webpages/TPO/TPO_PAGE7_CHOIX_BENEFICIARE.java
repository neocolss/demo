package com.solution.demo.framework.functional.webpages.TPO;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Item;
import com.solution.demo.framework.utils.ItemAction;

public class TPO_PAGE7_CHOIX_BENEFICIARE {

    public void go_action(String action) {
        Reporter.getInstance().newModule();
        Item btn_action = null;

        if (action.equalsIgnoreCase("Suivant")) {
            btn_action = new Item("//input[@name='suivant']", "btn Suivant");
        }

        if (action.equalsIgnoreCase("Pr�c�dent")) {
            btn_action = new Item("//input[@value='Pr�c�dent']", "btn Pr�c�dent");
        }

        ItemAction.click(btn_action);
        Reporter.getInstance().closeModule();
    }
}
