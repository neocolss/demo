package com.solution.demo.framework.functional.webpages.TPO;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Item;
import com.solution.demo.framework.utils.ItemAction;

public class TPO_PAGE1_ESPACE_GESTIONNAIRE {
    public Item link_gestion_des_demandes = new Item("linktext", "Gestion des demandes", "lien Gestion des demandes");
    public Item link_gestion_reseau_pro_sante = new Item("linktext", "Gestion d'un r�seau de professionnels de sant�", "lien Gestion d'un r�seau de professionnels de sant�");
    public Item btn_deconnexion = new Item("//input[@value='Se d�connecter']", "btn d�connexion");

    public void seDeconnecter() {
        ItemAction.click(btn_deconnexion);
    }

    public void action(String action) {
        Reporter.getInstance().newModule();
        if (action.equalsIgnoreCase("gestionDemandes")) {
            ItemAction.click(link_gestion_des_demandes);
        } else if (action.equalsIgnoreCase("gestionReseau")) {
            ItemAction.click(link_gestion_reseau_pro_sante);
        }
        Reporter.getInstance().closeModule();
    }
}
