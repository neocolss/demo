package com.solution.demo.framework.functional.webpages.TPO;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Item;
import com.solution.demo.framework.utils.ItemAction;

public class TPO_LOGIN_PAGE {
    public Item input_login = new Item("name", "LOGIN", "input Login TPO");
    public Item input_mdp = new Item("name", "PASSWORD", "input PASSWORD TPO");
    public Item btn_connexion = new Item("name", "Submit", "btn connexion TPO");

    public void login(String input_login, String input_mdp) {
        Reporter.getInstance().newModule();
        ItemAction.setValue(this.input_login, input_login);
        ItemAction.setValue(this.input_mdp, input_mdp);
        ItemAction.click(this.btn_connexion);
        Reporter.getInstance().closeModule();
    }
}
