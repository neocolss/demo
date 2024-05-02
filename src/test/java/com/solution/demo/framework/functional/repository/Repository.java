package com.solution.demo.framework.functional.repository;

import com.solution.demo.framework.functional.webpages.TPG.TPG_GESTION_OCT_PAGE;
import com.solution.demo.framework.functional.webpages.TPG.TPG_Gestion_AMC_PAGE;
import com.solution.demo.framework.functional.webpages.TPG.TPG_HOME_PAGE;
import com.solution.demo.framework.functional.webpages.TPG.TPG_LOGINPAGE;
import com.solution.demo.framework.functional.webpages.TPO.*;

/***
 * Reposirory : Put All Web Pages Here
 */
public class Repository {
    //TPG
    public static TPG_LOGINPAGE tpg_loginpage = new TPG_LOGINPAGE();
    public static TPG_HOME_PAGE tpg_home_page = new TPG_HOME_PAGE();
    public static TPG_GESTION_OCT_PAGE tpg_gestion_oct_page = new TPG_GESTION_OCT_PAGE();
    public static TPG_Gestion_AMC_PAGE tpg_gestion_amc_page = new TPG_Gestion_AMC_PAGE();


    //TPO
    public static TPO_LOGIN_PAGE tpo_login_page = new TPO_LOGIN_PAGE();
    public static TPO_PAGE1_ESPACE_GESTIONNAIRE tpo_page1_espace_gestionnaire = new TPO_PAGE1_ESPACE_GESTIONNAIRE();
    public static TPO_PAGE2_ConsultationDemandes tpo_page2_consultationDemandes = new TPO_PAGE2_ConsultationDemandes();
    public static TPO_PAGE3_Saisi_Professionnel tpo_page3_saisi_professionnel = new TPO_PAGE3_Saisi_Professionnel();
    public static TPO_PAGE4_SelectionPS tpo_page4_selectionps = new TPO_PAGE4_SelectionPS();
    public static TPO_PAGE5_CHOIX_AMC tpo_page5_choix_amc = new TPO_PAGE5_CHOIX_AMC();
    public static TPO_PAGE6_CHOIX_BENEFICIARE tpo_page6_choix_beneficiare = new TPO_PAGE6_CHOIX_BENEFICIARE();
    public static TPO_PAGE7_CHOIX_BENEFICIARE tpo_page7_choix_beneficiare = new TPO_PAGE7_CHOIX_BENEFICIARE();
    public static TPO_PAGE8_SelectionDemande tpo_page8_selectionDemande = new TPO_PAGE8_SelectionDemande();
    public static TPO_PAGE9_CreationNouvelleDemande tpo_page9_creationNouvelleDemande = new TPO_PAGE9_CreationNouvelleDemande();
}
