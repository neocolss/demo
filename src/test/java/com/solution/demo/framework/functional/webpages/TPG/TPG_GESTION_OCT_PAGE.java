package com.solution.demo.framework.functional.webpages.TPG;

import com.solution.demo.framework.utils.Item;

public class TPG_GESTION_OCT_PAGE {

    //TOP FRAME
    public Item saisi_numero_oct = new Item("//*[@name='NoOCT']", "saisie numero OCT");
    public Item saisi_nom_oct = new Item("//input[@name='NomOCT']", "saisie Nom OCT");

    public Item btn_oct_abondan = new Item("//a[normalize-space()='Abandon']", "bouton OCT Abondan");
    public Item btn_oct_nouveau = new Item("//a[normalize-space()='Nouveau']", "bouton OCT Nouveau");
    public Item btn_oct_valider = new Item("//a[normalize-space()='Valider']", "bouton OCT Valider");


    //BOTOM FRAME : Résultats
    public Item href_code_oct = new Item("//*[@id='oct0']/td[1]/a", "code oct resultat");
    public Item href_nom_oct = new Item("//*[@id='oct0']/td[2]/a", "nom oct resultat");
    public Item href_donnees_complementaires = new Item("//*[@id='oct0']/td[3]", "donnees complementaires resultat");

    //2eme IHM : Resultats Affichés en mode Edit (iframe : topFrame)
    //Page 1 : Informations OCT Contact
    //Ligne 1
    public Item numero_oct = new Item("/html/body/form/table/tbody/tr[3]/td[2]", "numero oct affiche");

    public Item get_code_type_oct_papier = new Item("/html/body/form/table/tbody/tr[2]/td[4]", "code_type_oct_papier");
    public Item code_type_oct_papier_radio = new Item("/html/body/form/table/tbody/tr[3]/td[4]/input[1]", "code_type_oct_papier_radio");
    public Item get_code_type_oct_electronique = new Item("/html/body/form/table/tbody/tr[2]/td[4]", "code_type_oct_electronique");
    public Item code_type_oct_electronique_radio = new Item("/html/body/form/table/tbody/tr[3]/td[4]/input[2]", "code_type_oct_electronique_radio");

    public Item get_mode_virement_ps = new Item("/html/body/form/table/tbody/tr[2]/td[6]", "mode virement PS choisi");
    public Item mode_virement_ps_radio = new Item("/html/body/form/table/tbody/tr[3]/td[6]/input[1]", "mode_virement_ps");

    public Item get_mode_virement_oct = new Item("/html/body/form/table/tbody/tr[2]/td[6]", "mode virement OCT choisi");
    public Item mode_virement_oct_radio = new Item("/html/body/form/table/tbody/tr[3]/td[6]/input[2]", "mode_virement_oct");

    //Ligne 2
    public Item date_effet = new Item("//*[@id='DateEffet']", "date d'effet");
    public Item date_effet_calendrier = new Item("//*[@id='DateEffetButtonCalendar']", "date d'effet calendrier");
    public Item get_validite = new Item("/html/body/form/table/tbody/tr[4]/td[4]", "Validite choisi");
    public Item invalide_radio = new Item("/html/body/form/table/tbody/tr[5]/td[4]/input[1]", "Invalide radio");
    public Item valide_radio = new Item("/html/body/form/table/tbody/tr[5]/td[4]/input[2]", "Valide radio");
    public Item get_code_comptable = new Item("/html/body/form/table/tbody/tr[4]/td[6]", "code comptable choisi");
    public Item code_comptable = new Item("/html/body/form/table/tbody/tr[5]/td[6]/input", "input de code comptable");

    //Ligne 3
    public Item get_nom = new Item("/html/body/form/table/tbody/tr[6]/td[2]", " nom");
    public Item nom = new Item("/html/body/form/table/tbody/tr[7]/td[2]/input", "input nom");
    public Item get_nom_long = new Item("/html/body/form/table/tbody/tr[6]/td[4]", " nom long");
    public Item nom_long = new Item("/html/body/form/table/tbody/tr[7]/td[4]/input", "input nom long");

    //Ligne 4
    public Item get_point_remise = new Item("/html/body/form/table/tbody/tr[8]/td[2]", "point de remise");
    public Item point_remise = new Item("/html/body/form/table/tbody/tr[9]/td[2]/input", "input point remise");

    //Ligne5
    public Item get_voie_N = new Item("/html/body/form/table/tbody/tr[10]/td[2]/table/tbody/tr/td[1]", "valeur de Voie N");
    public Item voie_N = new Item("/html/body/form/table/tbody/tr[11]/td[2]/table/tbody/tr/td[1]/input", "input Voie N");
    public Item get_type_voie = new Item("/html/body/form/table/tbody/tr[10]/td[2]/table/tbody/tr/td[2]", "valeur type voie");
    public Item select_type_voie = new Item("/html/body/form/table/tbody/tr[11]/td[2]/table/tbody/tr/td[2]/select", "select type voie");
    public Item get_nom_voie = new Item("/html/body/form/table/tbody/tr[10]/td[2]/table/tbody/tr/td[3]", "nom de la voie");
    public Item select_nom_voie = new Item("/html/body/form/table/tbody/tr[11]/td[2]/table/tbody/tr/td[3]/select", "select nom voie");
    public Item get_nom_rue = new Item("/html/body/form/table/tbody/tr[10]/td[2]/table/tbody/tr/td[4]", " valeur nom rue");
    public Item nom_rue = new Item("/html/body/form/table/tbody/tr[11]/td[2]/table/tbody/tr/td[4]/input", "nom de rue");

    //Ligne 6
    public Item get_lieu_dit = new Item("/html/body/form/table/tbody/tr[12]/td[2]", "valeur lieu dit");
    public Item lieu_dit = new Item("/html/body/form/table/tbody/tr[13]/td[2]/input", "lieu dit");

    //Ligne 7
    public Item get_cedex = new Item("/html/body/form/table/tbody/tr[14]/td[2]", "valeur cedex");
    public Item cedex = new Item("/html/body/form/table/tbody/tr[15]/td[2]/input", "cedex");
    public Item get_bureau = new Item("/html/body/form/table/tbody/tr[14]/td[4]", "valeur bureau");
    public Item bureau = new Item("/html/body/form/table/tbody/tr[15]/td[4]/input", "bureau");

    //Ligne 8
    public Item get_code_postal = new Item("/html/body/form/table/tbody/tr[16]/td[2]", "valeur code postal");
    public Item code_postal = new Item("/html/body/form/table/tbody/tr[17]/td[2]/input", "code postal");
    public Item get_communue = new Item("/html/body/form/table/tbody/tr[16]/td[4]", "valeur commune");
    public Item commune = new Item("/html/body/form/table/tbody/tr[17]/td[4]/input", "commune");

    //Ligne 9
    public Item get_telephone = new Item("/html/body/form/table/tbody/tr[18]/td[2]", "valeur telephone");
    public Item telephone = new Item("/html/body/form/table/tbody/tr[19]/td[2]/input", "telephone");
    public Item get_fax = new Item("/html/body/form/table/tbody/tr[18]/td[4]", "valeur Fax");
    public Item fax = new Item("/html/body/form/table/tbody/tr[19]/td[4]/input", "fax");

    //Ligne 10
    public Item get_email = new Item("/html/body/form/table/tbody/tr[20]/td[2]", "valeur email");
    public Item email = new Item("/html/body/form/table/tbody/tr[21]/td[2]/input", "email");

    //LIgne 11
    public Item get_norme_echange = new Item("/html/body/form/table/tbody/tr[22]/td[2]", "valeur nome echange");
    public Item select_norme_echange = new Item("/html/body/form/table/tbody/tr[23]/td[2]/select", "select norme echange");

    //Actions
    public Item link_abandon = new Item("//a[normalize-space()='Abandon']", "link abandon");
    public Item link_suivant = new Item("//a[normalize-space()='Suivant']", "link suivant");
    public Item link_valider = new Item("//a[normalize-space()='Valider']", "link valider");

    //Page 2 : Informations OCT Compte Bancaire
    //Ligne 1 : OCT
    public Item get_cb_no_cot = new Item("/html/body/form/table/tbody/tr[2]/td[2]", "valeur cb numero oct");
    public Item get_cb_date_effet = new Item("/html/body/form/table/tbody/tr[2]/td[4]", "valeur cb date effet");
    //Ligne 2 : Modification
    public Item get_cb_nom_banque = new Item("/html/body/form/table/tbody/tr[5]/td[2]", "valeur cb nom banque");
    public Item cb_nom_banque = new Item("/html/body/form/table/tbody/tr[6]/td[2]/input", "cb nom banque");
    public Item get_cb_intitule_compte = new Item("/html/body/form/table/tbody/tr[5]/td[4]", "valeur cb Intitule compte");
    public Item cb_intitule_compte = new Item("/html/body/form/table/tbody/tr[6]/td[4]/input", "cb intitule compte");
    public Item get_cb_code_banque = new Item("/html/body/form/table/tbody/tr[7]/td/table/tbody/tr[1]/td[2]", "valeur cb code banque");
    public Item get_cb_code_guichet = new Item("/html/body/form/table/tbody/tr[7]/td/table/tbody/tr[1]/td[4]", "valeur cb code guichet");
    public Item get_cb_no_compte = new Item("/html/body/form/table/tbody/tr[7]/td/table/tbody/tr[1]/td[6]", "valeur cb no compte");
    //Ligne 3 : Donnees SEPA
    public Item get_cb_code_pays_iso2 = new Item("/html/body/form/table/tbody/tr[7]/td/table/tbody/tr[3]/td[2]", "valeur cb code pays IOS2");
    public Item cb_code_pays_iso2 = new Item("/html/body/form/table/tbody/tr[7]/td/table/tbody/tr[4]/td[2]/input", "cb code pays ISO2");
    public Item get_cb_cle_iban = new Item("/html/body/form/table/tbody/tr[7]/td/table/tbody/tr[3]/td[4]", "valeur cb cle IBAN");
    public Item cb_cle_iban = new Item("/html/body/form/table/tbody/tr[7]/td/table/tbody/tr[4]/td[4]/input", "cb cle IBAN");
    public Item get_cb_bban = new Item("/html/body/form/table/tbody/tr[7]/td/table/tbody/tr[3]/td[6]", "valeur cb BBAN");
    public Item cb_bban = new Item("/html/body/form/table/tbody/tr[7]/td/table/tbody/tr[4]/td[6]/input", "cb BBAN");
    public Item get_cb_bic = new Item("/html/body/form/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]", "valeur cb BIC");
    public Item cb_bic = new Item("/html/body/form/table/tbody/tr[7]/td/table/tbody/tr[4]/td[8]/input", "cb BIC");


    //LOG Mouvements OCT (iframe : bottomFrame)
    public Item get_log_numero_oct = new Item("/html/body/form/table[1]/tbody/tr[2]/td[2]", "log numero OCT");
    public Item get_log_cree_le = new Item("/html/body/form/table[1]/tbody/tr[2]/td[4]", "log cree le");
    public Item get_log_etat = new Item("/html/body/form/table[1]/tbody/tr[2]/td[6]", "log etat");
    //Table Logs Mouvement
    public Item table_principale_logs_mouvements = new Item("//*[@id='mainTable']", "Html Table des logs des mouvements");
    public Item incremental_get_date_mouvement = new Item("//*[@id='mainTable']/tbody/tr[x]/td[1]", "valeur incremtale de la date du mouvement");
    public Item incremental_get_firstColumn_mouvement = new Item("//*[@id='mainTable']/tbody/tr[x]/td[2]", "valeur incremtale de la premiere colonne du mouvement");
    public Item incremental_get_secondColumn_mouvement = new Item("//*[@id='mainTable']/tbody/tr[x]/td[3]", "valeur incremtale de la seconde colonne du mouvement");
    public Item incremental_get_thirdColumn_mouvement = new Item("//*[@id='mainTable']/tbody/tr[x]/td[4]", "valeur incremtale de la third colonne du mouvement");
    public Item incremental_get_fourthColumn_mouvement = new Item("//*[@id='mainTable']/tbody/tr[x]/td[5]", "valeur incremtale de la fourth colonne du mouvement");


}
