package com.solution.demo.framework.functional.modules.ihm;

import com.solution.demo.framework.functional.repository.Repository;
import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.ItemAction;

public class OCTPAGE1Module {

    public static void saisirOCT(String numeroOCT) {
        Reporter.getInstance().newModule();
        ItemAction.setValue(Repository.tpg_gestion_oct_page.saisi_numero_oct, numeroOCT);
        Reporter.getInstance().closeModule();
    }

    public static void oct_abondan() {
        Reporter.getInstance().newModule();
        ItemAction.click(Repository.tpg_gestion_oct_page.btn_oct_abondan);
        Reporter.getInstance().closeModule();
    }

    public static void oct_valider_numeroOCT(String numeroOCT) {
        Reporter.getInstance().newModule();
        //ItemAction.waitElement(Repository.tpg_gestion_oct_page.saisi_numero_oct, 30, States.visible);
        ItemAction.setValue(Repository.tpg_gestion_oct_page.saisi_numero_oct, numeroOCT);
        ItemAction.click(Repository.tpg_gestion_oct_page.btn_oct_valider);
        Reporter.getInstance().closeModule();
    }

    public static void oct_valider_nomOCT(String nomOCT) {
        Reporter.getInstance().newModule();
        ItemAction.setValue(Repository.tpg_gestion_oct_page.saisi_nom_oct, nomOCT);
        ItemAction.click(Repository.tpg_gestion_oct_page.btn_oct_valider);
        Reporter.getInstance().closeModule();
    }

    public static void oct_valider(String numeroOCT, String nomOCT) {
        Reporter.getInstance().newModule();
        ItemAction.setValue(Repository.tpg_gestion_oct_page.saisi_numero_oct, numeroOCT);
        ItemAction.setValue(Repository.tpg_gestion_oct_page.saisi_nom_oct, nomOCT);
        ItemAction.click(Repository.tpg_gestion_oct_page.btn_oct_valider);
        Reporter.getInstance().closeModule();
    }

    public static void oct_nouveau_numeroOCT(String numeroOCT) {
        Reporter.getInstance().newModule();
        if (ItemAction.getText(Repository.tpg_gestion_oct_page.saisi_numero_oct) == null) {
            ItemAction.setValue(Repository.tpg_gestion_oct_page.saisi_numero_oct, numeroOCT);
        }
        ItemAction.click(Repository.tpg_gestion_oct_page.btn_oct_nouveau);
        Reporter.getInstance().closeModule();
    }

    public static void oct_nouveau_nomOCT(String nomOCT) {
        Reporter.getInstance().newModule();
        ItemAction.setValue(Repository.tpg_gestion_oct_page.saisi_nom_oct, nomOCT);
        ItemAction.click(Repository.tpg_gestion_oct_page.btn_oct_nouveau);
        Reporter.getInstance().closeModule();
    }

    public static void oct_nouveau(String numeroOCT, String nomOCT) {
        Reporter.getInstance().newModule();
        ItemAction.setValue(Repository.tpg_gestion_oct_page.saisi_numero_oct, numeroOCT);
        ItemAction.setValue(Repository.tpg_gestion_oct_page.saisi_nom_oct, nomOCT);
        ItemAction.click(Repository.tpg_gestion_oct_page.btn_oct_nouveau);
        Reporter.getInstance().closeModule();
    }

    public static void go_action(String action) {
        Reporter.getInstance().newModule();
        String _action = action.toLowerCase();
        switch (action) {
            case "abandon":
                ItemAction.click(Repository.tpg_gestion_oct_page.btn_oct_abondan);
                break;
            case "nouveau":
                ItemAction.click(Repository.tpg_gestion_oct_page.btn_oct_nouveau);
                break;
            case "valider":
                ItemAction.click(Repository.tpg_gestion_oct_page.btn_oct_valider);
                break;
            default:
                System.out.println("prière de choisir une action valide : abondan, nouveau ou valider");
                break;
        }
        Reporter.getInstance().closeModule();
    }

    //Traitement des résultats
    public static void go_by_oct_code() {
        Reporter.getInstance().newModule();
        ItemAction.click(Repository.tpg_gestion_oct_page.href_code_oct);
        Reporter.getInstance().closeModule();
    }
}
