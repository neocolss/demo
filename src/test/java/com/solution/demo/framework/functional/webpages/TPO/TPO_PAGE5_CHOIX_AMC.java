package com.solution.demo.framework.functional.webpages.TPO;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Item;
import com.solution.demo.framework.utils.ItemAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TPO_PAGE5_CHOIX_AMC {

    //Chercher AMC par nom de l'AMC
    public void chercherAMC(String nomAMC) {
        Reporter.getInstance().newModule();
        String xpathRows = "/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table/tbody/tr[5]/td/table/tbody/tr";
        //Liste des lignes (tr)
        List<WebElement> rowCollection = ItemAction.getElements(xpathRows);
        //System.out.println("Number of rows in this table: "+rowCollection.size());
        int i_RowNum = 1;
        for (WebElement rowElement : rowCollection) {
            //Liste des colonnes pour chaque ligne (td)
            List<WebElement> colCollection = rowElement.findElements(By.xpath("td"));

            int i_ColNum = 1;
            for (WebElement colElement : colCollection) {
                //System.out.println("Row "+i_RowNum+" Column "+i_ColNum+" Data "+colElement.getText());
                if (nomAMC.equalsIgnoreCase(colElement.getText())) {
                    //Choisir le bouton radio associ� qui est le premier td
                    String xpath_radio_input = xpathRows + "[" + i_RowNum + "]" + "/td[1]/input";
                    //System.out.println("xpath: "+xpath_radio_input);
                    Item radio_input = new Item(xpath_radio_input, "bouton radio associé pour l'AMC cherché");
                    ItemAction.scrollToElement(radio_input);
                    ItemAction.click(radio_input);
                    break;
                }
                i_ColNum = i_ColNum + 1;
            }
            i_RowNum = i_RowNum + 1;
        }
        Reporter.getInstance().closeModule();
    }

    public void go_action(String action) {
        Reporter.getInstance().newModule();
        Item btn_action = null;

        if (action.equalsIgnoreCase("Suivant")) {
            btn_action = new Item("name", "suivant", "btn Suivant");
        }

        if (action.equalsIgnoreCase("Annuler")) {
            btn_action = new Item("name", "retour", "btn Annuler");
        }

        ItemAction.clickJS(btn_action);
        Reporter.getInstance().closeModule();
    }
}
