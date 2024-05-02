package com.solution.demo.framework.functional.modules.ihm;

import com.solution.demo.framework.functional.repository.Repository;
import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Item;
import com.solution.demo.framework.utils.ItemAction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OCTPAGE2Module {

    public static Map<String, String> get_contact_informations_oct_mode_saisi() {
        Reporter.getInstance().newModule();
        Map<String, String> all_infos = new HashMap<>();
        //Valeur No Oct
        String No_OCT = ItemAction.getText(Repository.tpg_gestion_oct_page.numero_oct);
        all_infos.put("No_OCT", No_OCT);
        //Valeur Code Type OCT
        String code_type_OCT = ItemAction.getText(Repository.tpg_gestion_oct_page.get_code_type_oct_electronique);
        all_infos.put("code_type_OCT", code_type_OCT);
        //Valeur Mode Virement
        String mode_virement = ItemAction.getText(Repository.tpg_gestion_oct_page.get_mode_virement_ps);
        all_infos.put("mode_virement", mode_virement);
        //Valeur Date d'effet
        //String date_effet = ItemAction.getText(Repository.tpg_gestion_oct_page.date_effet);
        String date_effet = ItemAction.getValue(Repository.tpg_gestion_oct_page.date_effet);
        all_infos.put("date_effet", date_effet);
        //Valeur Validite
        String validite = ItemAction.getText(Repository.tpg_gestion_oct_page.get_validite);
        all_infos.put("validite", validite);
        //Valeur Code Comptable
        String code_comptable = ItemAction.getText(Repository.tpg_gestion_oct_page.get_code_comptable);
        all_infos.put("code_comptable", code_comptable);
        //Valeur Nom
        String nom = ItemAction.getText(Repository.tpg_gestion_oct_page.get_nom);
        all_infos.put("nom", nom);
        //Valeur Nom Long
        String nom_long = ItemAction.getText(Repository.tpg_gestion_oct_page.get_nom_long);
        all_infos.put("nom_long", nom_long);
        //Valeur POint de Remise
        String point_remise = ItemAction.getText(Repository.tpg_gestion_oct_page.get_point_remise);
        all_infos.put("point_remise", point_remise);
        //Valeur Voie Numero
        String numero_voie = ItemAction.getText(Repository.tpg_gestion_oct_page.get_voie_N);
        all_infos.put("numero_voie", numero_voie);
        //Valeur type Voie
        String type_voie = ItemAction.getText(Repository.tpg_gestion_oct_page.get_type_voie);
        all_infos.put("type_voie", type_voie);
        //Valeur nom voie
        String nom_voie = ItemAction.getText(Repository.tpg_gestion_oct_page.get_nom_voie);
        all_infos.put("nom_voie", nom_voie);
        //Valeur nom rue
        String nom_rue = ItemAction.getText(Repository.tpg_gestion_oct_page.get_nom_rue);
        all_infos.put("nom_rue", nom_rue);
        //Valeur lieu-dit
        String lieu_dit = ItemAction.getText(Repository.tpg_gestion_oct_page.get_lieu_dit);
        all_infos.put("lieu_dit", lieu_dit);
        //Valeur cedex
        String cedex = ItemAction.getText(Repository.tpg_gestion_oct_page.get_cedex);
        all_infos.put("cedex", cedex);
        //Valeur Bureau
        String bureau = ItemAction.getText(Repository.tpg_gestion_oct_page.get_bureau);
        all_infos.put("bureau", bureau);
        //Valeur Code Postal
        String code_postal = ItemAction.getText(Repository.tpg_gestion_oct_page.get_code_postal);
        all_infos.put("code_postal", code_postal);
        //Valeur commune
        String commune = ItemAction.getText(Repository.tpg_gestion_oct_page.get_communue);
        all_infos.put("commune", commune);
        //Valeur Telephone
        String telephone = ItemAction.getText(Repository.tpg_gestion_oct_page.get_telephone);
        all_infos.put("telephone", telephone);
        //Valeur Fax
        String fax = ItemAction.getText(Repository.tpg_gestion_oct_page.get_fax);
        all_infos.put("fax", fax);
        //Valeur Email
        String email = ItemAction.getText(Repository.tpg_gestion_oct_page.get_email);
        all_infos.put("email", email);
        //Valeur Norme Echange
        String norme_echange = ItemAction.getText(Repository.tpg_gestion_oct_page.get_norme_echange);
        all_infos.put("norme_echange", norme_echange);

        Reporter.getInstance().closeModule();
        return all_infos;
    }

    //Informations du compte bancaire
    public static Map<String, String> get_cb_informations_oct_mode_saisi() {
        Reporter.getInstance().newModule();
        Map<String, String> all_infos = new HashMap<>();

        String cb_no_oct = ItemAction.getText(Repository.tpg_gestion_oct_page.get_cb_no_cot);
        all_infos.put("cb_no_oct", cb_no_oct);
        String cb_date_effet = ItemAction.getText(Repository.tpg_gestion_oct_page.get_cb_date_effet);
        all_infos.put("cb_date_effet", cb_date_effet);
        String cb_nom_banque = ItemAction.getText(Repository.tpg_gestion_oct_page.get_cb_nom_banque);
        all_infos.put("cb_nom_banque", cb_nom_banque);
        String cb_intitule_compte = ItemAction.getText(Repository.tpg_gestion_oct_page.get_cb_intitule_compte);
        all_infos.put("cb_intitule_compte", cb_intitule_compte);
        String cb_code_banque = ItemAction.getText(Repository.tpg_gestion_oct_page.get_cb_code_banque);
        all_infos.put("cb_code_banque", cb_code_banque);
        String cb_code_guichet = ItemAction.getText(Repository.tpg_gestion_oct_page.get_cb_code_guichet);
        all_infos.put("cb_code_guichet", cb_code_guichet);
        String cb_no_compte = ItemAction.getText(Repository.tpg_gestion_oct_page.get_cb_no_compte);
        all_infos.put("cb_no_compte", cb_no_compte);
        String cb_code_pays_iso2 = ItemAction.getText(Repository.tpg_gestion_oct_page.get_cb_code_pays_iso2);
        all_infos.put("cb_code_pays_iso2", cb_code_pays_iso2);
        String cb_cle_iban = ItemAction.getText(Repository.tpg_gestion_oct_page.get_cb_cle_iban);
        all_infos.put("cb_cle_iban", cb_cle_iban);
        String cb_bban = ItemAction.getText(Repository.tpg_gestion_oct_page.get_cb_bban);
        all_infos.put("cb_bban", cb_bban);
        String cb_bic = ItemAction.getText(Repository.tpg_gestion_oct_page.get_cb_bic);
        all_infos.put("cb_bic", cb_bic);

        Reporter.getInstance().closeModule();
        return all_infos;
    }

    public static Map<String, List<String>> get_MapList_logMouvement_informations_oct_mode_saisi() {
        Reporter.getInstance().newModule();
        Map<String, List<String>> all_infos = new HashMap<>();
        List<String> log_mouvement_ligne_valeurs = new ArrayList<>();
        List<String> log_mouvement_infos_generale = new ArrayList<>();

        String log_numero_oct = ItemAction.getText(Repository.tpg_gestion_oct_page.get_log_numero_oct);
        //all_infos.put("log_numero_oct", log_numero_oct);
        log_mouvement_infos_generale.add(log_numero_oct);
        String log_cree_le = ItemAction.getText(Repository.tpg_gestion_oct_page.get_log_cree_le);
        log_mouvement_infos_generale.add(log_cree_le);
        //all_infos.put("log_cree_le", log_cree_le);
        String log_etat = ItemAction.getText(Repository.tpg_gestion_oct_page.get_log_cree_le);
        log_mouvement_infos_generale.add(log_etat);
        all_infos.put("info oct cree le etat", log_mouvement_infos_generale);

        /*Check if table of log mouvement details is present*/
        boolean logs_mouvements_presence = ItemAction.isElementPresent(Repository.tpg_gestion_oct_page.table_principale_logs_mouvements);
        //mouvements presents
        if (logs_mouvements_presence) {
            //Claculer le nombre des lignes
            List<WebElement> lignes_log_mouvements = ItemAction.getElements(Repository.tpg_gestion_oct_page.table_principale_logs_mouvements.getXpath() + "/tbody/tr");
            int nombre_lignes = lignes_log_mouvements.size();
            System.out.println("nombre_lignes:" + nombre_lignes);
            //Iterer sur les lignes pour obtenir les valeur du log mouvements
            for (int i = 0; i < nombre_lignes - 2; i++) {
                String column1 = ItemAction.getText(new Item(Repository.tpg_gestion_oct_page.incremental_get_date_mouvement.getXpath().replace("x", String.valueOf(i + 2)), ""));
                log_mouvement_ligne_valeurs.add(column1);
                String column2 = ItemAction.getText(new Item(Repository.tpg_gestion_oct_page.incremental_get_firstColumn_mouvement.getXpath().replace("x", String.valueOf(i + 2)), ""));
                log_mouvement_ligne_valeurs.add(column2);
                String column3 = ItemAction.getText(new Item(Repository.tpg_gestion_oct_page.incremental_get_secondColumn_mouvement.getXpath().replace("x", String.valueOf(i + 2)), ""));
                log_mouvement_ligne_valeurs.add(column3);
                String column4 = ItemAction.getText(new Item(Repository.tpg_gestion_oct_page.incremental_get_thirdColumn_mouvement.getXpath().replace("x", String.valueOf(i + 2)), ""));
                log_mouvement_ligne_valeurs.add(column4);
                String column5 = ItemAction.getText(new Item(Repository.tpg_gestion_oct_page.incremental_get_fourthColumn_mouvement.getXpath().replace("x", String.valueOf(i + 2)), ""));
                log_mouvement_ligne_valeurs.add(column5);
            }
        }
        Reporter.getInstance().closeModule();
        return all_infos;
    }

    public static Map<String, Map<String, String>> get_logMouvement_informations_oct_mode_saisi() {
        Reporter.getInstance().newModule();
        Map<String, Map<String, String>> all_infos = new HashMap<>();
        Map<String, String> log_mouvement_infos_generale = new HashMap<>();

        String log_numero_oct = ItemAction.getText(Repository.tpg_gestion_oct_page.get_log_numero_oct);
        log_mouvement_infos_generale.put("log_numero_oct", log_numero_oct);
        String log_cree_le = ItemAction.getText(Repository.tpg_gestion_oct_page.get_log_cree_le);
        log_mouvement_infos_generale.put("log_cree_le", log_cree_le);
        String log_etat = ItemAction.getText(Repository.tpg_gestion_oct_page.get_log_etat);
        log_mouvement_infos_generale.put("log_etat", log_etat);
        all_infos.put("info numero oct cree le etat", log_mouvement_infos_generale);

        /*Check if table of log mouvement details is present*/
        boolean logs_mouvements_presence = ItemAction.isElementPresent(Repository.tpg_gestion_oct_page.table_principale_logs_mouvements);
        //mouvements presents
        if (logs_mouvements_presence) {
            //Claculer le nombre des lignes
            List<WebElement> lignes_log_mouvements = ItemAction.getElements(Repository.tpg_gestion_oct_page.table_principale_logs_mouvements.getXpath() + "/tbody/tr");
            int nombre_lignes = lignes_log_mouvements.size();
            System.out.println("nombre_lignes:" + nombre_lignes);

            //Iterer sur les lignes pour obtenir les valeur du log mouvements
            for (int i = 0; i < nombre_lignes - 2; i++) {
                Map<String, String> log_mouvement_ligne_valeurs = new HashMap<>();
                String column1 = ItemAction.getText(new Item(Repository.tpg_gestion_oct_page.incremental_get_date_mouvement.getXpath().replace("x", String.valueOf(i + 2)), "date_effet"));
                log_mouvement_ligne_valeurs.put("date_effet", column1);
                String column2 = ItemAction.getText(new Item(Repository.tpg_gestion_oct_page.incremental_get_firstColumn_mouvement.getXpath().replace("x", String.valueOf(i + 2)), "code_action"));
                log_mouvement_ligne_valeurs.put("code_action", column2);
                String column3 = ItemAction.getText(new Item(Repository.tpg_gestion_oct_page.incremental_get_secondColumn_mouvement.getXpath().replace("x", String.valueOf(i + 2)), "donnees"));
                log_mouvement_ligne_valeurs.put("donnees", column3);
                String column4 = ItemAction.getText(new Item(Repository.tpg_gestion_oct_page.incremental_get_thirdColumn_mouvement.getXpath().replace("x", String.valueOf(i + 2)), "auteur"));
                log_mouvement_ligne_valeurs.put("auteur", column4);
                String column5 = ItemAction.getText(new Item(Repository.tpg_gestion_oct_page.incremental_get_fourthColumn_mouvement.getXpath().replace("x", String.valueOf(i + 2)), "date_saisie"));
                log_mouvement_ligne_valeurs.put("date_saisie", column5);
                all_infos.put("ligne " + (i + 1), log_mouvement_ligne_valeurs);
            }

        }
        Reporter.getInstance().closeModule();
        return all_infos;
    }

    //Action choisie : Valider, Suivant et Abandon
    public static void action(String action) {
        Reporter.getInstance().newModule();
        switch (action) {
            case "Abandon":
                ItemAction.click(Repository.tpg_gestion_oct_page.link_abandon);
                break;
            case "Valider":
                ItemAction.click(Repository.tpg_gestion_oct_page.link_valider);
                break;
            case "Suivant":
                ItemAction.click(Repository.tpg_gestion_oct_page.link_suivant);
                break;
        }
        Reporter.getInstance().closeModule();
    }

    public static void remplir_info_oct() {
        Reporter.getInstance().newModule();

        //choisir code type oct : mode electronique
        ItemAction.click(new Item(Repository.tpg_gestion_oct_page.get_code_type_oct_electronique.getXpath() + "/input[2]", "btn radio Electronique"));
        //ItemAction.click(new Item(Repository.tpg_gestion_oct_page.get_code_type_oct_papier.getXpath()+"/input[1]","btn radio papier"));

        //Choisir mode Virement : PS
        ItemAction.click(new Item(Repository.tpg_gestion_oct_page.get_mode_virement_oct.getXpath() + "/input[1]", "btn radio Virement OCT "));

        //CHoisir date D'effet
        /*
        String dateActuelle = ItemAction.getText(Repository.tpg_gestion_oct_page.date_effet);
        if(dateActuelle==null){
            String dateDuJour = Helper.getFormatedDate("dd/MM/yyyy") ;
            ItemAction.setValue(Repository.tpg_gestion_oct_page.date_effet, dateDuJour);
        }
        */
        //choisir validite : valide
        ItemAction.click(new Item("/html/body/form/table/tbody/tr[3]/td[4]/input[2]", "btn radio Validite Valide"));

        //choisir un code comptable
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[3]/td[6]/input", "champ saisi Code Comptable"), "99999");

        //Choisir nom
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[4]/td[2]/input", "champ saisi nom"), "nom test");
        //ItemAction.setValue(Repository.tpg_gestion_oct_page.nom, "nom");

        //choisir nom long
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[4]/td[4]/input", "champ saisi nom long"), "nom long");

        //choisir point de remise
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[5]/td[2]/input", "champ saisi point remise"), "point remise");

        //choisir voie N°
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[6]/td[2]/table/tbody/tr/td[1]/input", "champ saisi Voie N°"), "1");

        //choisir type voie : Bis
        Item select_type_voie = new Item("/html/body/form/table/tbody/tr[6]/td[2]/table/tbody/tr/td[2]/select", "select type voie");
        ItemAction.click(select_type_voie);
        Select type_voie = new Select(ItemAction.getElement(select_type_voie.getXpath()));
        type_voie.selectByVisibleText("Bis");

        //Choisir le nom de voie
        Item select_nom_voie = new Item("/html/body/form/table/tbody/tr[6]/td[2]/table/tbody/tr/td[3]/select", "select nom voie");
        ItemAction.click(select_nom_voie);
        Select nom_voie = new Select(ItemAction.getElement(select_nom_voie.getXpath()));
        nom_voie.selectByVisibleText("Avenue");

        //Choisir nom rue
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[6]/td[2]/table/tbody/tr/td[4]/input", "champ saisi nom rue"), "nom rue");

        //Choisir lieu_dit
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[7]/td[2]/input", "champ saisi lieu dit"), "lieu dit");

        //Choisir Cedex
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[8]/td[2]/input", "champ saisi cedex"), "6");

        //Choisir Bureau
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[8]/td[4]/input", "champ saisi bureau"), "bureau");

        //Choisir code postal
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[9]/td[2]/input", "champ saisi code postal"), "11111");

        //Choisir commune
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[9]/td[4]/input", "champ saisi commune"), "commune test");

        //Choisir Telephone
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[10]/td[2]/input", "champ saisi telephone"), "0612345678");

        //Choisir Fax
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[10]/td[4]/input", "champ saisi fax"), "0612345678");

        //Choisir email
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[11]/td[2]/input", "champ saisi email"), "email@test.fr");

        //Choisir Norme Echange
        Item select_norme_echange = new Item("/html/body/form/table/tbody/tr[12]/td[2]/select", "select norme echange");
        ItemAction.click(select_norme_echange);
        Select norme_echange = new Select(ItemAction.getElement(select_norme_echange.getXpath()));
        norme_echange.selectByVisibleText("IRIS B2");


        Reporter.getInstance().closeModule();
    }

    public static void remplir_info_cb_oct() {
        Reporter.getInstance().newModule();
        //nom banque
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[5]/td[2]/input", "champ saisi nom banque"), "ma banque");
        //intitule compte
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[5]/td[4]/input", "champ saisi intitule compte"), "mon compte");
        //code pays ISO2
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[6]/td/table/tbody/tr[3]/td[2]/input", "champ saisi code pays ISO2"), "FR");
        //clé Iban
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[6]/td/table/tbody/tr[3]/td[4]/input", "champ saisi cle IBAN"), "76");
        //BBAN
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[6]/td/table/tbody/tr[3]/td[6]/input", "champ saisi BBAN"), "30056001480000000000190");
        //BIC
        ItemAction.setValue(new Item("/html/body/form/table/tbody/tr[6]/td/table/tbody/tr[3]/td[8]/input", "champ saisi BIC"), "CCFRFRPP");
        Reporter.getInstance().closeModule();
    }

    public static void update_infp_oct() {
        Reporter.getInstance().newModule();

        //Modifier le code type oct vers Papier
        ItemAction.click(Repository.tpg_gestion_oct_page.code_type_oct_papier_radio);

        //Modifier le nom
        ItemAction.setValue(Repository.tpg_gestion_oct_page.nom, "RESODOM1");

        //Modifier le nom long
        ItemAction.setValue(Repository.tpg_gestion_oct_page.nom_long, "RESODOM1 Long");

        //Modifier point de remise
        ItemAction.setValue(Repository.tpg_gestion_oct_page.nom_long, "Point Remise");

        Reporter.getInstance().closeModule();
    }
}
