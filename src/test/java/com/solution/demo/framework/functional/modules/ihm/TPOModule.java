package com.solution.demo.framework.functional.modules.ihm;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.ItemAction;
import com.solution.demo.framework.utils.UtilFunctions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TPOModule {

    /***
     * Automatisation de la sélection d'une date à partir du bouton calendar
     * @param annee
     * @param mois
     * @param jour
     */
    public static void choisirDateCalendar(int annee, int mois, int jour) {
        Reporter.getInstance().newModule();
        String mois_annee = ItemAction.getText("//*[@class='calendar']//*[@class='title']");
        List<String> moisEtAnnee = Arrays.asList(mois_annee.split(","));
        String mois_ = moisEtAnnee.get(0).trim();
        String annee_ = moisEtAnnee.get(1).trim();

        //choisir l'annee
        while (annee < Integer.valueOf(annee_)) {
            String xpath_annee = "(//*[@class='calendar']//*[@unselectable='on'])[3]";
            ItemAction.click(xpath_annee);
            String mois_annee_ = ItemAction.getText("//*[@class='calendar']//*[@class='title']");
            List<String> moisEtAnnee_ = Arrays.asList(mois_annee_.split(","));
            String _mois_ = moisEtAnnee_.get(0).trim();
            String _annee_ = moisEtAnnee_.get(1).trim();
            if (annee == Integer.valueOf(_annee_)) {
                break;
            }
        }

        while (annee > Integer.valueOf(annee_)) {
            String xpath_annee = "(//*[@class='calendar']//*[@unselectable='on'])[7]";
            ItemAction.click(xpath_annee);
            String mois_annee_ = ItemAction.getText("//*[@class='calendar']//*[@class='title']");
            List<String> moisEtAnnee_ = Arrays.asList(mois_annee_.split(","));
            String _mois_ = moisEtAnnee_.get(0).trim();
            String _annee_ = moisEtAnnee_.get(1).trim();
            if (annee == Integer.valueOf(_annee_)) {
                break;
            }
        }


        System.out.println("__________-------------____________");
        //Choisir le mois
        Map<Integer, String> tousLesMois = new HashMap<>();
        tousLesMois.put(1, "Janvier");
        tousLesMois.put(2, "Février");
        tousLesMois.put(3, "Mars");
        tousLesMois.put(4, "Avril");
        tousLesMois.put(5, "Mai");
        tousLesMois.put(6, "Juin");
        tousLesMois.put(7, "Juillet");
        tousLesMois.put(8, "Août");
        tousLesMois.put(9, "Septembre");
        tousLesMois.put(10, "Octobre");
        tousLesMois.put(11, "Novembre");
        tousLesMois.put(12, "Décembre");


        String mois_annee_ = ItemAction.getText("//*[@class='calendar']//*[@class='title']");
        List<String> moisEtAnnee_ = Arrays.asList(mois_annee_.split(","));
        String _mois_ = moisEtAnnee_.get(0).trim();
        int mois_correspondant = UtilFunctions.getKey(tousLesMois, _mois_);
        int diff = mois - mois_correspondant;


        for (int i = 0; i < Math.abs(diff); i++) {
            if (diff < 0) {
                String mois_xpath = "(//*[@class='calendar']//*[@unselectable='on'])[4]";
                ItemAction.click(mois_xpath);
            } else if (diff > 0) {
                String mois_xpath = "(//*[@class='calendar']//*[@unselectable='on'])[6]";
                ItemAction.click(mois_xpath);
            } else {
                break;
            }
        }

        //Choisir le jour
        ItemAction.click("//*[@class='calendar']//*[@class='daysrow']//*[text() = '" + jour + "']");
        Reporter.getInstance().closeModule();
    }
}
