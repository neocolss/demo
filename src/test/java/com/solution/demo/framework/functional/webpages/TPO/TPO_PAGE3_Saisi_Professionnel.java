package com.solution.demo.framework.functional.webpages.TPO;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Item;
import com.solution.demo.framework.utils.ItemAction;
import org.openqa.selenium.support.ui.Select;

public class TPO_PAGE3_Saisi_Professionnel {
    public Item input_date_effet = new Item("id", "dateEffet", "date effet entree");
    public Item btn_date_effet = new Item("id", "dateEffetButton", "btn calendar");
    public Item input_numero_adeli = new Item("name", "numeroAdeli", "input numero Num�ro ADELI / FINESS G�ographique");
    public Item input_raison_sociale = new Item("name", "nom", "input raison sociale");
    public Item input_code_postal = new Item("name", "codepostal", "input code postal");
    public Item select_categorie = new Item("name", "idcategorie", "select catgorie specialite");

    public void select_Categorie(String categorie) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_categorie);
        Select select_categorie_ = new Select(ItemAction.getElement(select_categorie));
        select_categorie_.selectByVisibleText(categorie);
        Reporter.getInstance().closeModule();
    }

    //action possible: suivant
    public void go_action(String action) {
        Reporter.getInstance().newModule();
        Item btn_action = null;
        if (action.equalsIgnoreCase("Rechercher")) {
            btn_action = new Item("name", "suivant", "btn rechercher");
        }

        if (action.equalsIgnoreCase("Annuler")) {
            btn_action = new Item("name", "annuler", "btn Annuler");
        }

        ItemAction.click(btn_action);
        Reporter.getInstance().closeModule();
    }
}
