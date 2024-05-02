package com.solution.demo.framework.functional.webpages.TPO;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Item;
import com.solution.demo.framework.utils.ItemAction;
import com.solution.demo.framework.webdriver.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class TPO_PAGE9_CreationNouvelleDemande {
    //Informations générales
    public Item input_num_entree = new Item("id", "numeroEntree", "input N entree");
    public Item input_entree_le = new Item("name", "dateAdmissionString", "input entree le");
    public Item btn_calendar_entree_le = new Item("id", "dateAdmissionButton", "btn calendar entree le");
    public Item input_debut_periode_pec = new Item("name", "dateEntreeString", "input debut periode pec");
    public Item btn_calendar_debut_periode_pec = new Item("id", "dateEntreeButton", "btn calendar debut periode pec");
    public Item input_duree_estimee_sejour = new Item("name", "dureeSejour", "input estimee sejour");
    public Item input_date_fin_pec = new Item("name", "dateFinPECSaisieString", "input date fin pec");
    public Item btn_calendar_date_fin_pec = new Item("id", "dateFinPECSaisieStringButton", "btn calendar date fin pec");
    public Item input_mode_traitement = new Item("id", "libMT", "input mode traitement");
    public Item input_code_dmt = new Item("id", "libDMT", "input code DMT");
    public Item select_mode_mcoo = new Item("id", "codeMCO", "select mode mcoo");
    public Item input_fax = new Item("id", "numeroFax", "input fax adresser la réponse");
    public Item input_mail = new Item("id", "adresseMail", "input mail auquel adresser la réponse");

    //Conditions de l'hospitalisation
    public Item check_maternite = new Item("id", "condition_maternite", "checkbox maternité");
    public Item check_accident_travail = new Item("id", "condition_accident", "checkbox Accident du travail/Maladie professionnelle");
    public Item check_accident_droit_commun = new Item("id", "condition_accident_droit_commun", "checkbox Accident de droit commun");
    public Item input_en_date_du = new Item("id", "dateConditionHospitalisation", "input en date du");
    public Item btn_calendar_en_date_du = new Item("id", "dateConditionHospitalisationButton", "btn calendar en date du");

    //Conditions d'exonération
    public Item check_participation_assure_hospi = new Item("id", "checkboxExo_802", "checkbox Participation Assuré Hospi");
    public Item check_forfait_journalier = new Item("id", "checkboxExo_801", "checkbox forfait journalier");
    public Item check_prix_journalier = new Item("id", "checkboxExo_800", "checkbox prix journalier");

    //Désignation des actes
    public Item liste_lignes_designation_actes = new Item("//*[@id='blockActes']/table/tbody/tr", "liste des lignes Désignation des actes");


    public void selectF1Prestations(String... prestations) {
        Reporter.getInstance().newModule();
        List<WebElement> rowCollection = ItemAction.getElements(liste_lignes_designation_actes);
        //System.out.println("Number of rows in this table: "+rowCollection.size());
        int i_RowNum = 1;
        for (WebElement rowElement : rowCollection) {
            //Liste des colonnes pour chaque ligne (td)
            List<WebElement> colCollection = rowElement.findElements(By.xpath("td"));
            int i_ColNum = 1;
            for (WebElement colElement : colCollection) {
                //System.out.println("Row :"+i_RowNum+", Column :"+i_ColNum+", Data : "+colElement.getText());
                for (String prestation : prestations) {
                    if (prestation.equalsIgnoreCase(colElement.getText()) && colElement.getText() != null) {
                        String f1_xpath = liste_lignes_designation_actes.getXpath() + "[" + i_RowNum + "]/td[1]/input";
                        //System.out.println("f1_xpath : " + f1_xpath);
                        Item checkbox_f1 = new Item(f1_xpath, "checkbox f1 prestation " + prestation);
                        ItemAction.click(checkbox_f1);
                    }
                }
                i_ColNum = i_ColNum + 1;
            }
            i_RowNum = i_RowNum + 1;
        }
        Reporter.getInstance().closeModule();
    }

    public void select_code_dmt(String code_dmt) {
        Reporter.getInstance().newModule();
        ItemAction.setValue(input_code_dmt, code_dmt);
        WebElement ul_code_dmt = ItemAction.getElement("/html/body/ul[1]");
        List<WebElement> li_code_dmt = ul_code_dmt.findElements(By.tagName("li"));
        for (int i = 0; i < li_code_dmt.size(); i++) {
            //System.out.println(li_code_dmt.get(i).getText());
            if (code_dmt.equalsIgnoreCase(li_code_dmt.get(i).getText())) {
                ItemAction.click(li_code_dmt.get(i));
            }
        }
        Reporter.getInstance().closeModule();
    }

    public void clickF2() {
        Reporter.getInstance().newModule();
        //Hover on column : Nombre de jours demand�s/F2
        //String xpath = "//*[@id='blockActes']/table/tbody/tr[1]/td[3]";
        //Item nmbr_jours_F2 = new Item(xpath, "colonne Nombre de jours demand�s / F2");
        //ItemAction.hoverOnElement(nmbr_jours_F2);

        //cliquer dans le premier champ input
        //ItemAction.click(new Item("//*[@id='blockActes']/table/tbody/tr[2]/td[3]", "premier input colonne"));

        //Send keyboard F2 : remplir automatiquement les champs
        WebElement element = WebDriverFactory.getInstance().findElement(By.xpath("//*[@id='blockActes']/table/tbody/tr[2]/td[3]"));
        element.click();
        //element.sendKeys(Keys.F2);

        Actions action = new Actions(WebDriverFactory.getInstance());
        action.sendKeys(Keys.F2).perform();
        Reporter.getInstance().closeModule();
    }

    public void go_action(String action) {
        Reporter.getInstance().newModule();
        Item btn_action = null;

        if (action.equalsIgnoreCase("Envoyer demande")) {
            btn_action = new Item("value", "Envoyer demande", "btn Envoyer demande");
        }

        if (action.equalsIgnoreCase("Annuler saisie")) {
            btn_action = new Item("value", "Annuler saisie", "btn Annuler saisie");
        }

        ItemAction.clickJS(btn_action);
        Reporter.getInstance().closeModule();
    }

    //R�ponse POP-UP DEMANDE ENVOI FAX OU MAIL
    public void action_envoi_fax_mail(String action) {
        Reporter.getInstance().newModule();
        Item btn_action = null;

        if (action.equalsIgnoreCase("Oui")) {
            btn_action = new Item("value", "Oui", "btn Oui");
        }

        if (action.equalsIgnoreCase("Non")) {
            btn_action = new Item("value", "Non", "btn Non");
        }

        if (action.equalsIgnoreCase("Retour")) {
            btn_action = new Item("value", "Retour", "btn Retour");
        }

        ItemAction.click(btn_action);
        Reporter.getInstance().closeModule();
    }

    public void envoi_mail(String mail) {
        Reporter.getInstance().newModule();
        Reporter.getInstance().closeModule();

    }

    /////TRAITEMENT DE LA DEMANDE
    //recuperer le numero de la demande
    public String recuperer_numero_demande() {
        Reporter.getInstance().newModule();
        Item xpath_numero_demande = new Item("name", "idDemande", "N° de la demande");
        Reporter.getInstance().closeModule();
        return ItemAction.getText(xpath_numero_demande);

    }

    public void traitement_de_la_demande(String action) {
        Reporter.getInstance().newModule();
        Item btn_action = null;

        if (action.equalsIgnoreCase("Voir résultat")) {
            btn_action = new Item("value", "Voir résultat", "btn Voir résultat");
        }

        if (action.equalsIgnoreCase("Imprimer")) {
            btn_action = new Item("value", "Imprimer", "btn Imprimer");
        }

        if (action.equalsIgnoreCase("Retour")) {
            btn_action = new Item("value", "Retour", "btn Retour");
        }

        ItemAction.clickJS(btn_action);
        Reporter.getInstance().closeModule();
    }

    //Page VISUALISATION D'UNE DEMANDE DE PRISE EN CHARGE
    public void action_pec_demande(String action) {
        Reporter.getInstance().newModule();
        Item btn_action = null;

        if (action.equalsIgnoreCase("Faxer")) {
            btn_action = new Item("value", "Faxer", "btn Faxer");
        }

        if (action.equalsIgnoreCase("Imprimer")) {
            btn_action = new Item("value", "Imprimer", "btn Imprimer");
        }

        if (action.equalsIgnoreCase("Mail")) {
            btn_action = new Item("value", "Mail", "btn Mail");
        }

        if (action.equalsIgnoreCase("Annuler la PEC")) {
            btn_action = new Item("value", "Annuler la PEC", "btn Annuler la PEC");
        }

        if (action.equalsIgnoreCase("Retour")) {
            btn_action = new Item("value", "Retour", "btn Retour");
        }

        ItemAction.click(btn_action);
        Reporter.getInstance().closeModule();
    }

}
