package com.solution.demo.framework.functional.modules.ihm;

import com.solution.demo.framework.functional.repository.Repository;
import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.ItemAction;

/**
 * AMC Web Page Module
 */
public class AMCPAGEModule {

    public static void creer_nouveauAMC() {
        Reporter.getInstance().newModule();
        //Saisir N AMC
        ItemAction.setValue(Repository.tpg_gestion_amc_page.input_num_amc, "0049905999");
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

        Reporter.getInstance().closeModule();
    }
}
