package com.solution.demo.tests.ihm.TPG_4489;

import com.solution.demo.framework.functional.Transverse;
import com.solution.demo.framework.functional.modules.ihm.HomePageModule;
import com.solution.demo.framework.functional.modules.ihm.LoginModule;
import com.solution.demo.framework.functional.repository.Repository;
import com.solution.demo.framework.functional.webpages.variables.URLS;
import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.ItemAction;
import com.solution.demo.framework.utils.TestBase;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_TPG_4489_Creer_AMC extends TestBase {
    @DisplayName("Test TPG 4489 Création AMC")
    @Test
    @Tags(value = {@Tag("IHM"), @Tag("TPG_4489"), @Tag("AMC")})
    public void test() {

        String num_amc = "0149905999";
        Reporter.getInstance().newModule();

        Transverse.get(URLS.TPG_URL_ISNT11);
        LoginModule.login("rootfab", "profils", "ADMIN");

        HomePageModule.goToPage("Gestion des AMC");

        ItemAction.switchToFrameByName("topFrame");


        //Premiere Page
        //Saisir N AMC
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_num_amc, num_amc);
        //Select Norme
        Repository.tpg_gestion_amc_page.selectNorme("IF");
        //Select sous Norme
        Repository.tpg_gestion_amc_page.selectssNorme("24");
        //Saisir Nom AMC
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_nom, "AMC TEST");

        //Select Code de Regroupement
        Repository.tpg_gestion_amc_page.selectCodeRegr("MNH");

        //Select Identifiant site
        Repository.tpg_gestion_amc_page.selectidSite("MNH");

        //Saisir N voie
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_N_voie, "AUGUSSEAU");

        //Select N voie
        Repository.tpg_gestion_amc_page.selectNVoie("Bis");

        //Select type voie
        Repository.tpg_gestion_amc_page.selectTypeVoie("Avenue");

        //Saisir libelle voie
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_libelle_voie, "ma voie");

        //Saisir lieu dit
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_lieu_dit, "BP 18519");

        //Saisir Cedex
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_cedex, "6");

        //Saisir Bureau
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_bureau, "BP 18519");

        //Saisir Code Postal
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_code_postal, "BP 18519");

        //Saisir Commune
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_commune, "Commune");

        //Saisir N Telephone
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_N_telephone, "0612345678");

        //Select Groupe AMC
        Repository.tpg_gestion_amc_page.selectGroupeAMC("ISANTE");

        //Selectionner l'action Nouveau
        Repository.tpg_gestion_amc_page.action("Nouveau");

        ItemAction.switchToParentFrame();

        //Page 2
        //clicker btn radio radio cle noemie valeur oui
        ItemAction.click(Repository.tpg_gestion_amc_page.btn_radio_cle_noemie_oui);

        //Saisir Nouveau N
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_nouveau_N, num_amc);

        //Saisir Code Comptable
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_code_comptable, "12345");

        //Saisir Point de Remise
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_point_remise, "pt de remise");

        //Saisir N Fax
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_N_fax, "0612345667");

        //Saisir Email
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_email, "test@email.fr");

        //Selectionner l'action Valider
        Repository.tpg_gestion_amc_page.action("Valider");

        //Valider POP UP : IBAN obligatoire
        ItemAction.validerPopUpAlert();

        //page 3
        //Saisir nom de la banque
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_nomBanque, "HSBC");

        //Saisir intitule du compte
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_intituleCompte, "HSBC");

        //Saisir code pays ISO
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_codePaysISO, "FR");

        //Saisir cle IBAN
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_cleIBAN, "76");

        //Saisir un BBAN
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_BBAN, "30056001480148200174058");

        //Saisir cle BIC
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_BIC, "CCFRFRPP");

        //Saisir Date d'ouverture
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_dateOuverture, "08/09/22");

        //Saisir date de fermeture
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_dateFermeture, "08/09/30");

        //Saisir date de contrôle
        Repository.tpg_gestion_amc_page.selectDateControle("Date de réception");

        //Saisir RUI pour AMC
        Repository.tpg_gestion_amc_page.selectRuiAMC("0077570148-CCBPFRPPBDX-FR7610907000011012153066938");

        //Selectionner l'action Valider
        Repository.tpg_gestion_amc_page.action("Valider");


        //Page 4
        //Ligne Informatique
        Repository.tpg_gestion_amc_page.setSelect_civilite_informatique("Docteur");
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_nom_informatique, "nom info");
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_prenom_informatique, "prenom info");
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_tel_informatique, "0612345678");
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_fax_informatique, "0612345678");
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_email_informatique, "email@test.fr");

        //ligne gestionnaire
        Repository.tpg_gestion_amc_page.setSelect_civilite_gestionnaire("Docteur");
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_nom_gestionnaire, "nom gest");
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_prenom_gestionnaire, "prenom gest");
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_tel_gestionnaire, "0612345678");
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_fax_gestionnaire, "0612345678");
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_email_gestionnaire, "emai@test.fr");

        //ligne comptable
        Repository.tpg_gestion_amc_page.setSelect_civilite_comptable("Docteur");
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_nom_comptable, "nom compta");
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_prenom_comptable, "prenom compta");
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_tel_comptable, "0612345678");
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_fax_comptable, "0612345678");
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_email_comptable, "emai@test.fr");

        //select type de convention
        Repository.tpg_gestion_amc_page.setSelect_type_convent("Pas de contrôle - Circuit 00");
        //select Contestation(Mois)
        Repository.tpg_gestion_amc_page.setSelect_contest_mois("1");
        //select stockage(Mois)
        Repository.tpg_gestion_amc_page.setSelect_stockage_mois("3");
        //select Trait. Contest. (Mois)
        Repository.tpg_gestion_amc_page.setSelect_trait_contest_mois("1");
        //select Ident. Fichier
        Repository.tpg_gestion_amc_page.setSelect_ident_fichier("POLE HUMANIS - 1ADP");
        //select code retour
        Repository.tpg_gestion_amc_page.setSelect_code_retour("Norme 615 RS");
        //click btn radio Mode Retour valeur fixe
        ItemAction.click(Repository.tpg_gestion_amc_page.btn_radio_mode_retour_fixe);
        //select support
        Repository.tpg_gestion_amc_page.setSelect_support("Email");
        //click btn radio paiement par applicatiof valeur oui
        ItemAction.click(Repository.tpg_gestion_amc_page.btn_radio_paiement_applicatif_oui);
        //click btn radio Ret. fac. en erreur valeur oui
        ItemAction.click(Repository.tpg_gestion_amc_page.btn_radio_ret_fact_erreur_oui);
        //click btn radio Paiement d'autorit� valeur oui
        ItemAction.click(Repository.tpg_gestion_amc_page.btn_radio_paiement_autor_oui);
        //click btn radio Démarrage de l'activité TP Noémie valeur oui
        ItemAction.click(Repository.tpg_gestion_amc_page.btn_radio_demarage_activite_oui);


        //Selectionner l'action Valider
        Repository.tpg_gestion_amc_page.action("Valider");

        //Popup AMC est cr��
        ItemAction.validerPopUpAlert();

        Reporter.getInstance().closeModule();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
