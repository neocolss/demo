package com.solution.demo.framework.functional.webpages.TPO;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Item;
import com.solution.demo.framework.utils.ItemAction;
import org.openqa.selenium.support.ui.Select;

public class TPO_PAGE2_ConsultationDemandes {
    public Item input_date_creation = new Item("id", "dateDebut", "input date creation");
    public Item btn_calendar_date_creation = new Item("id", "dateDebutButton", "btn calendar date creation");
    public Item input_au = new Item("id", "dateFin", "input au date fin");
    public Item btn_date_au = new Item("id", "dateFinButton", "btn calendar au date fin");
    public Item input_num_ss = new Item("id", "numeroSS", "input numero ss");
    public Item input_nom = new Item("name", "nom", "input nom");
    public Item input_accord_pec = new Item("name", "accord", "input accord pec");
    public Item input_numero_adeli = new Item("name", "numeroAdeli", "input numero adeli");
    public Item btn_3pts_choose_amc = new Item("id", "choose_amc", "btn 3 points choose amc");
    public Item select_domaine = new Item("name", "domaine", "select domaine");
    public Item input_id_demande = new Item("name", "idDemande", "input identifiant demande");
    public Item select_type_demande = new Item("name", "TYPEDEMANDE", "select type demande");
    public Item select_etat_calcul = new Item("name", "ETAT", "select etat calcul");
    public Item select_motif_rejet = new Item("id", "MOTIF", "select motif rejet");
    public Item select_controle_grille_tarifiaire = new Item("name", "GRILLE", "select controle grille tarifiaire");

    //selectionner un domaine
    public void selectionner_domaine(String domaine) {
        ItemAction.click(select_domaine);
        Select select_domaine_ = new Select(ItemAction.getElement(select_domaine));
        select_domaine_.selectByVisibleText(domaine);
    }

    //selectionner un type de demande
    public void selectionner_type_demande(String type_demande) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_type_demande);
        Select select_type_demande_ = new Select(ItemAction.getElement(select_type_demande));
        select_type_demande_.selectByVisibleText(type_demande);
        Reporter.getInstance().closeModule();
    }

    //selectionner un etat calcul
    public void selectionner_etat_calcul(String etat_calcul) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_etat_calcul);
        Select select_etat_calcul_ = new Select(ItemAction.getElement(select_etat_calcul));
        select_etat_calcul_.selectByVisibleText(etat_calcul);
        Reporter.getInstance().closeModule();
    }

    //selectionner un motif rejet
    public void selectionner_motif_rejet(String motif_rejet) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_motif_rejet);
        Select select_motif_rejet_ = new Select(ItemAction.getElement(select_motif_rejet));
        select_motif_rejet_.selectByVisibleText(motif_rejet);
        Reporter.getInstance().closeModule();
    }

    //selectionner controle_grille_tarifiaire
    public void selectionner_controle_grille_tarifiaire(String controle_grille_tarifiaire) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_controle_grille_tarifiaire);
        Select controle_grille_tarifiaire_ = new Select(ItemAction.getElement(select_controle_grille_tarifiaire));
        controle_grille_tarifiaire_.selectByVisibleText(controle_grille_tarifiaire);
        Reporter.getInstance().closeModule();
    }

    //Selectionner action : Rechercher, Nouvelle demande, Retour � l'accueil, Se d�connecter
    public void go_action(String action) {
        Reporter.getInstance().newModule();
        Item btn_action = new Item("//input[@value='" + action + "']", "btn action " + action);
        ItemAction.clickJS(btn_action);
        Reporter.getInstance().closeModule();
    }
}
