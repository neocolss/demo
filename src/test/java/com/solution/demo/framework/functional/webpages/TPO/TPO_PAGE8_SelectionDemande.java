package com.solution.demo.framework.functional.webpages.TPO;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Item;
import com.solution.demo.framework.utils.ItemAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TPO_PAGE8_SelectionDemande {
    public Item img_liste_excel = new Item("html/body/table/tbody/tr/td/form/table/tbody/tr[5]/td/table/tbody/tr/td[2]/a/img", "btn image liste excel");

    //boutons des demandes
    public Item btn_autre_demande = new Item("name", "nouveau", "btn Autre demande");
    public Item btn_dupliquer_demande = new Item("value", "Dupliquer demande", "btn Dupliquer demande");
    public Item btn_autre_etablissement = new Item("name", "nouvelleSaisie", "btn Autre �tablissement");
    public Item btn_autre_beneficiaire = new Item("name", "nouvelleSaisieBenef", "btn Autre b�n�ficiaire");
    public Item btn_visualiser_demande = new Item("name", "visudemande", "btn Visualiser la demande");
    public Item btn_imprimer_demande = new Item("value", "Imprimer une demande", "btn Imprimer une demande");
    public Item btn_annuler = new Item("value", "Annuler", "btn Annuler");


    /***
     * Chercher la 1�re demande avec Etat calcul Accrod N� XXXXXXX
     */
    public boolean chercherPremierDemandePECavecEtatAccord(String numDemande) {
        Reporter.getInstance().newModule();
        boolean found = false;
        String xpath_lignes_des_demandes = "//*[@name='frmListeDemande']//div[@id='infos']/table//tr";
        List<WebElement> rowCollection = ItemAction.getElements(xpath_lignes_des_demandes);
        //System.out.println("Number of rows in this table: "+rowCollection.size());
        int i_RowNum = 1;
        outer:
        for (WebElement rowElement : rowCollection) {
            //Liste des colonnes pour chaque ligne (td)
            List<WebElement> colCollection = rowElement.findElements(By.xpath("td"));
            int i_ColNum = 1;
            for (WebElement colElement : colCollection) {
                System.out.println("Row :" + i_RowNum + ", Column :" + i_ColNum + ", Data : " + colElement.getText());
                String xpath_n_demande_table = "//*[@name='frmListeDemande']//div[@id='infos']/table//tr[" + i_RowNum + "]/td[3]";
                String n_demande_table = ItemAction.getText(xpath_n_demande_table);
                if (numDemande.equalsIgnoreCase(n_demande_table)) {
                    String xpath_etat_calcul = "//*[@name='frmListeDemande']//div[@id='infos']/table//tr[" + i_RowNum + "]/td[13]";
                    String etat_calcul_value = ItemAction.getText(xpath_etat_calcul);
                    System.out.println("etat_calcul_value:" + etat_calcul_value);
                    if (etat_calcul_value.contains("Accord")) {
                        ItemAction.click("//*[@name='frmListeDemande']//div[@id='infos']/table//tr[" + i_RowNum + "]/td[1]/input");
                        found = true;
                        break outer;
                    }

                }
                i_ColNum = i_ColNum + 1;
            }
            i_RowNum = i_RowNum + 1;
        }
        Reporter.getInstance().closeModule();
        return found;
    }

    public void go_action(String action) {
        Reporter.getInstance().newModule();
        Item btn_action = null;

        if (action.equalsIgnoreCase("Autre demande")) {
            btn_action = btn_autre_demande;
        }

        if (action.equalsIgnoreCase("Dupliquer demande")) {
            btn_action = btn_dupliquer_demande;
        }

        if (action.equalsIgnoreCase("Autre �tablissement")) {
            btn_action = btn_autre_etablissement;
        }

        if (action.equalsIgnoreCase("Autre b�n�ficiaire")) {
            btn_action = btn_autre_beneficiaire;
        }

        if (action.equalsIgnoreCase("Visualiser la demande")) {
            btn_action = btn_visualiser_demande;
        }

        if (action.equalsIgnoreCase("Imprimer une demande")) {
            btn_action = btn_imprimer_demande;
        }

        if (action.equalsIgnoreCase("Annuler")) {
            btn_action = btn_annuler;
        }

        ItemAction.click(btn_action);
        Reporter.getInstance().closeModule();
    }
}
