package com.solution.demo.framework.functional.webpages.TPG;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Item;
import com.solution.demo.framework.utils.ItemAction;
import com.solution.demo.framework.webdriver.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TPG_Gestion_AMC_PAGE {
    //TOP Frame
    public Item input_num_amc = new Item("name", "NoAMC", "input N° AMC");
    public Item select_norme = new Item("//*[@id='selectionNorme']", "select norme");
    public Item select_sous_norme = new Item("//*[@id='selectionSousNorme']", "select sous norme");
    public Item input_nom = new Item("name", "Nom", "input nom amc");
    public Item select_code_regr = new Item("//*[@id='selectionCodeRegroupement']", "select code regr.");
    public Item select_identifiant_site = new Item("//*[@id='selectionIdentifiantSite']", "select identifiant site");
    public Item input_N_voie = new Item("name", "NumVoie", "input N° voie");
    public Item select_N_voie = new Item("//*[@id='selectionBisTer']", "select N° voie");
    public Item select_type_voie = new Item("name", "TypVoie", "select nom voie");
    public Item input_libelle_voie = new Item("name", "LibVoie", "input libelle voie");
    public Item input_lieu_dit = new Item("name", "LieuDit", "lieu dit");
    public Item input_cedex = new Item("name", "Cedex", "input cedex");
    public Item input_bureau = new Item("name", "BureauDistributeur", "input bureau");
    public Item input_code_postal = new Item("name", "CodePostal", "input code postal");
    public Item input_commune = new Item("name", "Commune", "input commune");
    public Item input_N_telephone = new Item("name", "NoTelephone", "input N° telephone");
    public Item select_groupe_amc = new Item("name", "GroupeAMC", "select groupe amc");

    //Liste AMC : Appara�t apr�s la recherche d'un AMC valide
    public Item table_liste_amc = new Item("id", "mainTable", "LISTE AMC TABLE");

    //Page 2
    public Item btn_radio_cle_noemie_oui = new Item("//*[@name='TypeCle'][@value='O']", "btn_radio_cle_noemie_oui");
    public Item btn_radio_cle_noemie_non = new Item("//*[@name='TypeCle'][@value='N']", "btn_radio_cle_noemie_non");
    public Item input_nouveau_N = new Item("name", "NouveauNumero", "nouveau N");
    public Item input_date_effet = new Item("//*[@id='DateEffet']", "input date effet");
    public Item btn_calendrier = new Item("//*[@id='DateEffetButtonCalendar']", "btn calendrier");
    public Item btn_radio_valide = new Item("//*[@name='Validite'][@value='V']", "btn radio Valide");
    public Item btn_radio_invalide = new Item("//*[@name='Validite'][@value='I']", "btn radio Invalide");
    public Item btn_radio_suspendu = new Item("//*[@name='Validite'][@value='S']", "btn radio Suspendu");
    public Item input_code_comptable = new Item("name", "CodeComptable", "input code comptable");
    public Item input_point_remise = new Item("name", "PtRemise", "input point de remise");
    public Item input_N_fax = new Item("name", "NoFax", "input N Fax");
    public Item input_email = new Item("name", "NoEmail", "input Email");

    //Page 3
    public Item input_nomBanque = new Item("name", "NomBanque", "input nom banque");
    public Item input_intituleCompte = new Item("name", "IntituleCompte", "input intitule compte");
    public Item input_codePaysISO = new Item("name", "CodePays", "input_codePaysISO");
    public Item input_cleIBAN = new Item("name", "CleIBAN", "input_cleIBAN");
    public Item input_BBAN = new Item("name", "BBAN", "input_BBAN");
    public Item input_BIC = new Item("name", "CODBIC", "input_BIC");
    public Item input_dateOuverture = new Item("//*[@id='OuvertureService']", "input_dateOuverture");
    public Item btn_calendrierDateOuverture = new Item("//*[@id='OuvertureServiceButtonCalendar']", "btn_calendrierDateOuverture");
    public Item input_dateFermeture = new Item("//*[@id='FermetureService']", "input_dateFermeture");
    public Item btn_calendrierDateFermeture = new Item("//*[@id='FermetureServiceButtonCalendar']", "btn_calendrierDateFermeture");
    public Item select_dateControle = new Item("name", "TypeCtrlService", "select date pour le contr�le");
    public Item select_ruiAMC = new Item("name", "IdentRUI", "select RUI pour AMC");


    //Page 4
    public Item select_civilite_informatique = new Item("name", "InfCivilite", "select civilite informatique");
    public Item input_nom_informatique = new Item("name", "InfNom", "input_nom_informatique");
    public Item input_prenom_informatique = new Item("name", "InfPrenom", "input_prenom_informatique");
    public Item input_tel_informatique = new Item("name", "InfTelephone", "input_tel_informatique");
    public Item input_fax_informatique = new Item("name", "InfFax", "input fax informatique");
    public Item input_email_informatique = new Item("name", "InfEmail", "input email informatique");
    public Item select_civilite_gestionnaire = new Item("name", "GesCivilite", "select civilite gestionnaire");
    public Item input_nom_gestionnaire = new Item("name", "GesNom", "input nom gestionnaire");
    public Item input_prenom_gestionnaire = new Item("name", "GesPrenom", "input prenom gestionnaire");
    public Item input_tel_gestionnaire = new Item("name", "GesTelephone", "input tel gestionnaire");
    public Item input_fax_gestionnaire = new Item("name", "GesFax", "input fax gestionnaire");
    public Item input_email_gestionnaire = new Item("name", "GesEmail", "input email gestionnaire");
    public Item select_civilite_comptable = new Item("name", "CptCivilite", "select civilite comptable");
    public Item input_nom_comptable = new Item("name", "CptNom", "input nom comptable");
    public Item input_prenom_comptable = new Item("name", "CptPrenom", "input prenom comptable");
    public Item input_tel_comptable = new Item("name", "CptTelephone", "input tel comptable");
    public Item input_fax_comptable = new Item("name", "CptFax", "input fax comptable");
    public Item input_email_comptable = new Item("name", "CptEmail", "input email comptable");
    public Item select_type_convent = new Item("name", "TypeConvention", "select type conevnt");
    public Item select_contest_mois = new Item("name", "DelaiContest", "select Contestation (Mois)");
    public Item select_stockage_mois = new Item("name", "DelaiStock", "select stockage(Mois");
    public Item select_trait_contest_mois = new Item("name", "DelaiTrtContest", "select Trait. Contest. (Mois)");
    public Item select_alert_hotline = new Item("name", "DelaiAlertHotline", "select Alerte Hotline (Jours)");
    public Item select_alert_amc_jours = new Item("name", "DelaiAlerteAMC", "select Alerte AMC (Jours)");
    public Item select_recyclage_jours = new Item("name", "DelaiPaiement", "select Recyclage(Jours)");
    public Item select_ident_fichier = new Item("name", "IdentFichier", "select Ident. fichier");
    public Item select_code_retour = new Item("name", "NormeRetour", "select code retour");
    public Item btn_radio_mode_retour_fixe = new Item("//*[@name='ModeRetour'][@value='1']", "btn radio mode retour valeur : fixe");
    public Item btn_radio_mode_retour_variable = new Item("//*[@name='ModeRetour'][@value='2']", "btn radio retout valeur : variable");
    public Item select_support = new Item("name", "Support", "select support");
    public Item btn_radio_paiement_applicatif_oui = new Item("//*[@name='DelegationPaiement'][@value='O']", "btn radio Paiement par applicatif valeur : oui");
    public Item btn_radio_paiement_applicatif_non = new Item("//*[@name='DelegationPaiement'][@value='N']", "btn radio Paiement par applicatif valeur : non");
    public Item btn_radio_ret_fact_erreur_oui = new Item("//*[@name='RetourFacErr'][@value='O']", "btn radio Ret. fac. en erreur valeur : oui");
    public Item btn_radio_ret_fact_erreur_non = new Item("//*[@name='RetourFacErr'][@value='N']", "btn radio Ret. fac. en erreur valeur : non");
    public Item btn_radio_paiement_autor_oui = new Item("//*[@name='DetailFacture'][@value='1']", "btn radio paiement autorite valeur : oui");
    public Item btn_radio_paiement_autor_non = new Item("//*[@name='DetailFacture'][@value='0']", "btn radio paiement autorite valeur : non");
    public Item btn_radio_demarage_activite_oui = new Item("//*[@name='DemarrageNoemie'][@value='O']", "btn radio D�marrage de l'activit� TP No�mie valeur : oui");
    public Item btn_radio_demarage_activite_non = new Item("//*[@name='DemarrageNoemie'][@value='N']", "btn radio D�marrage de l'activit� TP No�mie valeur : non");


    //Boutons des Actions
    public static Item btn_abondan = new Item("linktext", "Abandon", "btn abandon");
    public static Item btn_effacer = new Item("linktext", "Effacer", "btn_effacer");
    public static Item btn_nouveau = new Item("linktext", "Nouveau", "btn_nouveau");
    public static Item btn_valider = new Item("linktext", "Valider", "btn_valider");
    public static Item btn_validETCopie = new Item("linktext", "Valid et Copie", "btn Valid et Copie");

    public Item btn_suivant = new Item("linktext", "Suivant", "btn suivant");
    public Item btn_precedent = new Item("linktext", "Precedent", "btn precedent");

    public void selectNorme(String norme) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_norme);
        Select select_norme_ = new Select(ItemAction.getElement(select_norme));
        select_norme_.selectByVisibleText(norme);
        Reporter.getInstance().closeModule();
    }

    public void selectssNorme(String ssnorme) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_sous_norme);
        Select select_ss_norme_ = new Select(ItemAction.getElement(select_sous_norme));
        select_ss_norme_.selectByVisibleText(ssnorme);
        Reporter.getInstance().closeModule();
    }

    public void selectCodeRegr(String codeRegr) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_code_regr);
        Select select_code_regr_ = new Select(ItemAction.getElement(select_code_regr));
        select_code_regr_.selectByVisibleText(codeRegr);
        Reporter.getInstance().closeModule();
    }

    public void selectidSite(String idSite) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_identifiant_site);
        Select select_id_site_ = new Select(ItemAction.getElement(select_identifiant_site));
        select_id_site_.selectByVisibleText(idSite);
        Reporter.getInstance().closeModule();
    }

    public void selectNVoie(String nVoie) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_N_voie);
        Select select_N_voie_ = new Select(ItemAction.getElement(select_N_voie));
        select_N_voie_.selectByVisibleText(nVoie);
        Reporter.getInstance().closeModule();
    }

    public void selectTypeVoie(String nomVoie) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_type_voie);
        Select select_nom_voie_ = new Select(ItemAction.getElement(select_type_voie));
        select_nom_voie_.selectByVisibleText(nomVoie);
        Reporter.getInstance().closeModule();
    }

    public void selectGroupeAMC(String groupeAMC) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_groupe_amc);
        Select select_groupe_amc_ = new Select(ItemAction.getElement(select_groupe_amc));
        select_groupe_amc_.selectByVisibleText(groupeAMC);
        Reporter.getInstance().closeModule();
    }


    //Choisir valeur cle n
    /*
    public void choisir_cle_noemie(String valeur_cle){
        if("oui".equalsIgnoreCase(valeur_cle)){
            if(new WebElement(btn_radio_cle_noemie_oui.getXpath()).isEnabled()){

            }
        }
    }
    */

    //Choisir action : premiere page seulement (xpath change dans la page suivante)
    public void action(String action_) {
        Reporter.getInstance().newModule();
        switch (action_) {
            case "Abandon":
                ItemAction.click(btn_abondan);
                break;
            case "Effacer":
                ItemAction.click(btn_effacer);
                break;
            case "Nouveau":
                ItemAction.click(btn_nouveau);
                break;
            case "Valider":
                ItemAction.click(btn_valider);
                break;
            case "Valid et Copie":
                ItemAction.click(btn_validETCopie);
                break;
            case "Suivant":
                ItemAction.click(btn_suivant);
                break;
            case "Precedent":
                ItemAction.click(btn_precedent);
                break;
        }
        Reporter.getInstance().closeModule();
    }

    public void selectDateControle(String str) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_dateControle);
        Select date_controle = new Select(ItemAction.getElement(select_dateControle));
        date_controle.selectByVisibleText(str);
        Reporter.getInstance().closeModule();
    }

    public void selectRuiAMC(String str) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_ruiAMC);
        Select rui_amc = new Select(ItemAction.getElement(select_ruiAMC));
        rui_amc.selectByVisibleText(str);
        Reporter.getInstance().closeModule();
    }

    public void setSelect_civilite_informatique(String civilite) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_civilite_informatique);
        Select select_civilite_informatique_ = new Select(ItemAction.getElement(select_civilite_informatique));
        select_civilite_informatique_.selectByVisibleText(civilite);
        Reporter.getInstance().closeModule();
    }

    public void setSelect_civilite_gestionnaire(String civilite) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_civilite_gestionnaire);
        Select select_civilite_gestionnaire_ = new Select(ItemAction.getElement(select_civilite_gestionnaire));
        select_civilite_gestionnaire_.selectByVisibleText(civilite);
        Reporter.getInstance().closeModule();
    }

    public void setSelect_civilite_comptable(String civilite) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_civilite_comptable);
        Select select_civilite_comptable_ = new Select(ItemAction.getElement(select_civilite_comptable));
        select_civilite_comptable_.selectByVisibleText(civilite);
        Reporter.getInstance().closeModule();
    }

    public void setSelect_type_convent(String type_convent) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_type_convent);
        Select select_type_convent_ = new Select(ItemAction.getElement(select_type_convent));
        select_type_convent_.selectByVisibleText(type_convent);
        Reporter.getInstance().closeModule();
    }

    public void setSelect_contest_mois(String contest_mois) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_contest_mois);
        Select select_contest_mois_ = new Select(ItemAction.getElement(select_contest_mois));
        select_contest_mois_.selectByVisibleText(contest_mois);
        Reporter.getInstance().closeModule();
    }

    public void setSelect_stockage_mois(String stockage_mois) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_stockage_mois);
        Select select_stockage_mois_ = new Select(ItemAction.getElement(select_stockage_mois));
        select_stockage_mois_.selectByVisibleText(stockage_mois);
        Reporter.getInstance().closeModule();
    }

    public void setSelect_trait_contest_mois(String trait_contest_mois) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_trait_contest_mois);
        Select select_trait_contest_mois_ = new Select(ItemAction.getElement(select_trait_contest_mois));
        select_trait_contest_mois_.selectByVisibleText(trait_contest_mois);
        Reporter.getInstance().closeModule();
    }

    public void setSelect_alert_hotline(String alert_hotline) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_alert_hotline);
        Select select_alert_hotline_ = new Select(ItemAction.getElement(select_alert_hotline));
        select_alert_hotline_.selectByVisibleText(alert_hotline);
        Reporter.getInstance().closeModule();
    }

    public void setSelect_alert_amc_jours(String alert_amc_jours) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_alert_hotline);
        Select select_alert_amc_jours_ = new Select(ItemAction.getElement(select_alert_amc_jours));
        select_alert_amc_jours_.selectByVisibleText(alert_amc_jours);
        Reporter.getInstance().closeModule();
    }

    public void setSelect_recyclage_jours(String recyclage_jours) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_recyclage_jours);
        Select select_recyclage_jours_ = new Select(ItemAction.getElement(select_recyclage_jours));
        select_recyclage_jours_.selectByVisibleText(recyclage_jours);
        Reporter.getInstance().closeModule();
    }

    public void setSelect_ident_fichier(String ident_fichier) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_ident_fichier);
        Select select_ident_fichier_ = new Select(ItemAction.getElement(select_ident_fichier));
        select_ident_fichier_.selectByVisibleText(ident_fichier);
        Reporter.getInstance().closeModule();
    }

    public void setSelect_code_retour(String code_retour) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_code_retour);
        Select select_code_retour_ = new Select(ItemAction.getElement(select_code_retour));
        select_code_retour_.selectByVisibleText(code_retour);
        Reporter.getInstance().closeModule();
    }

    public void setSelect_support(String support) {
        Reporter.getInstance().newModule();
        ItemAction.click(select_support);
        Select select_support_ = new Select(ItemAction.getElement(select_support));
        select_support_.selectByVisibleText(support);
        Reporter.getInstance().closeModule();
    }

    public List<WebElement> getAllHREFfromListeAMC() {
        Reporter.getInstance().newModule();
        WebElement tableListeAMC = ItemAction.getElement(table_liste_amc);
        List<WebElement> tableRows = new WebDriverWait(WebDriverFactory.getInstance(), 10).until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(tableListeAMC, By.tagName("a")));
        Reporter.getInstance().closeModule();
        return tableRows;
    }
}
