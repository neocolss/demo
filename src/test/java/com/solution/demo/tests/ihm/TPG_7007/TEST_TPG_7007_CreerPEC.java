package com.solution.demo.tests.ihm.TPG_7007;

import com.solution.demo.framework.functional.Transverse;
import com.solution.demo.framework.functional.modules.ihm.TPOModule;
import com.solution.demo.framework.functional.repository.Repository;
import com.solution.demo.framework.functional.webpages.variables.URLS;
import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.*;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TEST_TPG_7007_CreerPEC extends TestBase {

    @DisplayName("TPG 7007 TPO Créer PEC")
    @Test
    @Tags(value = {@Tag("IHM"), @Tag("TPG_7007")})
    public void testCreerPEC() {
        Reporter.getInstance().logInfo("==> Start TPG 7007 TPO : Créer PEC Test Case. ==>");

        String output_numero_demande = Constants.SHELL_DIRECTORY + "TPG_7007.txt";

        Transverse.get(URLS.TPO);

        Repository.tpo_login_page.login("CET_GAZAFA", "&Testvalid2022");

        //Choisir Option Gestion des Demandes : Page Gestion des demandes
        Repository.tpo_page1_espace_gestionnaire.action("gestionDemandes");

        //Choisir Nouvelle demande : Page CONSULTATION DES DEMANDES DéJà ENVOYEES
        Repository.tpo_page2_consultationDemandes.go_action("Nouvelle demande");

        //Saisi d'un professionnel de santé
        ItemAction.setValue(Repository.tpo_page3_saisi_professionnel.input_numero_adeli, "310019203");
        Repository.tpo_page3_saisi_professionnel.go_action("Rechercher");

        //Selection du PS : Page sélectionner le Professional de santé
        Repository.tpo_page4_selectionps.go_action("Sélection directe de l'AMC");

        //Choix de l'AMC
        Repository.tpo_page5_choix_amc.chercherAMC("baloo hospi C7");
        ItemAction.wait(3);
        Repository.tpo_page5_choix_amc.go_action("Suivant");


        //Choix d'un Bénéficiare : page 1
        ItemAction.setValue(Repository.tpo_page6_choix_beneficiare.input_num_ss_assure, "1681299999851");
        //ItemAction.setValue(Repository.tpo_page6_choix_beneficiare.input_date_naissance, "11/12/1968");
        ItemAction.click(Repository.tpo_page6_choix_beneficiare.btn_calendar_date_naissance);
        TPOModule.choisirDateCalendar(1968, 12, 11);
        Repository.tpo_page6_choix_beneficiare.go_action("Rechercher");

        //Choix d'un bénéficiare : page 2
        ItemAction.wait(3);
        Repository.tpo_page7_choix_beneficiare.go_action("Suivant");

        //Lire s'il y a dejé une demande créée : il faut l'annuler tout d'abord
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(output_numero_demande));
            StringBuilder sb = new StringBuilder();
            line = br.readLine();
            System.out.println("line:" + line);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (line != null) {
            if (Repository.tpo_page8_selectionDemande.chercherPremierDemandePECavecEtatAccord(line)) {
                Repository.tpo_page8_selectionDemande.go_action("Visualiser la demande");

                //Page VISUALISATION D'UNE DEMANDE DE PRISE EN CHARGE : Annuler la PEC
                Repository.tpo_page9_creationNouvelleDemande.action_pec_demande("Annuler la PEC");
                ItemAction.validerPopUpAlert();
                ItemAction.switchToFrameByIndex(0);
                Repository.tpo_page9_creationNouvelleDemande.action_envoi_fax_mail("Non");
                ItemAction.switchToParentFrame();
            } else {
                //Selection d'une demande
                ItemAction.click(Repository.tpo_page8_selectionDemande.btn_autre_demande);

                //Creation d'une nouvelle demande
                String currentDate = Helper.getFormatedDate("dd/MM/yyyy");
                System.out.println("current date:" + currentDate);

                ItemAction.setValue(Repository.tpo_page9_creationNouvelleDemande.input_num_entree, "1");
                ItemAction.setValue(Repository.tpo_page9_creationNouvelleDemande.input_entree_le, currentDate);
                ItemAction.setValue(Repository.tpo_page9_creationNouvelleDemande.input_debut_periode_pec, currentDate);
                ItemAction.setValue(Repository.tpo_page9_creationNouvelleDemande.input_duree_estimee_sejour, "15");
                //ItemAction.setValue(Repository.tpo_page9_creationNouvelleDemande.input_date_fin_pec, "08/11/2022");

                Repository.tpo_page9_creationNouvelleDemande.select_code_dmt("137:CHIRURGIE GENERALE");

                Repository.tpo_page9_creationNouvelleDemande.selectF1Prestations("Prix journalier", "Forfait journalier", "Participation Assuré Hospi", "Chambre Particuliére", "Honoraires", "Honoraires Non OPTAM", "Frais d'accompagnement", "Lit accompagnement");
                Repository.tpo_page9_creationNouvelleDemande.clickF2();

                Repository.tpo_page9_creationNouvelleDemande.go_action("Envoyer demande");


                //Reponse Demande Envoi Fax ou Mail
                int size = ItemAction.getIframes();
                System.out.println("size:" + size);
                ItemAction.switchToFrameByIndex(0);
                Repository.tpo_page9_creationNouvelleDemande.action_envoi_fax_mail("Non");

                //Page Votre demande a été acceptée
                ItemAction.switchToParentFrame();
                ItemAction.waitUntilJSReady();
                String numero_demande = Repository.tpo_page9_creationNouvelleDemande.recuperer_numero_demande();
                System.out.println("numero_demande:" + numero_demande);

                //Stocker ce numéro dans un fichier
                //String numero_demande = "458302";
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(output_numero_demande));
                    writer.write(numero_demande);

                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Repository.tpo_page9_creationNouvelleDemande.traitement_de_la_demande("Voir résultat");
                Repository.tpo_page9_creationNouvelleDemande.traitement_de_la_demande("Retour");
            }

        } else {
            //Selection d'une demande
            ItemAction.click(Repository.tpo_page8_selectionDemande.btn_autre_demande);

            //Creation d'une nouvelle demande
            String currentDate = Helper.getFormatedDate("dd/MM/yyyy");
            System.out.println("current date:" + currentDate);

            ItemAction.setValue(Repository.tpo_page9_creationNouvelleDemande.input_num_entree, "1");
            ItemAction.setValue(Repository.tpo_page9_creationNouvelleDemande.input_entree_le, currentDate);
            ItemAction.setValue(Repository.tpo_page9_creationNouvelleDemande.input_debut_periode_pec, currentDate);
            ItemAction.setValue(Repository.tpo_page9_creationNouvelleDemande.input_duree_estimee_sejour, "15");
            //ItemAction.setValue(Repository.tpo_page9_creationNouvelleDemande.input_date_fin_pec, "08/11/2022");

            Repository.tpo_page9_creationNouvelleDemande.select_code_dmt("137:CHIRURGIE GENERALE");

            Repository.tpo_page9_creationNouvelleDemande.selectF1Prestations("Prix journalier", "Forfait journalier", "Participation Assuré Hospi", "Chambre Particuliére", "Honoraires", "Honoraires Non OPTAM", "Frais d'accompagnement", "Lit accompagnement");
            Repository.tpo_page9_creationNouvelleDemande.clickF2();

            Repository.tpo_page9_creationNouvelleDemande.go_action("Envoyer demande");


            //Reponse Demande Envoi Fax ou Mail
            int size = ItemAction.getIframes();
            ItemAction.switchToFrameByIndex(0);
            Repository.tpo_page9_creationNouvelleDemande.action_envoi_fax_mail("Non");

            //Page Votre demande a été acceptée
            ItemAction.switchToParentFrame();
            ItemAction.waitUntilJSReady();

            //Récupérer le numéro de la demande
            ItemAction.waitElement(new Item("name", "idDemande", "N° de la demande"), 30, States.visible);
            String numero_demande = Repository.tpo_page9_creationNouvelleDemande.recuperer_numero_demande();
            System.out.println("numero_demande:" + numero_demande);

            //Stocker ce numéro dans un fichier
            //String numero_demande = "458302";
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(output_numero_demande));
                writer.write(numero_demande);

                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Repository.tpo_page9_creationNouvelleDemande.traitement_de_la_demande("Voir résultat");
            Repository.tpo_page9_creationNouvelleDemande.traitement_de_la_demande("Retour");
        }

        Reporter.getInstance().logInfo("==> Finish TPGCONVERG_7007 TPO.==>|");
    }
}
