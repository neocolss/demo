package com.solution.demo.framework.functional.webpages.TPG;

import com.solution.demo.framework.utils.Item;

public class TPG_LOGINPAGE {
    /*
    public Item input_identifiant = new Item("name", "Identifiant", "input Identifiant");
    public Item input_motDePasse = new Item("name", "MotDePasse", " input mot De Passe");
    public Item select_groupe = new Item("name", "Groupe", "selection de groupe");
    public Item btn_connexion = new Item("name", "AdminBT_B", "bouton de connexion");
    */

    public Item input_identifiant = new Item("//input[@name='Identifiant']", "input Identifiant");
    public Item input_motDePasse = new Item("//input[@name='MotDePasse']", "input Mot De Passe");
    public Item select_groupe = new Item("//select[@name='Groupe']", "selection de groupe");
    public Item btn_connexion = new Item("//a[@class='AdminBT_B']", "bouton de connexion");

}
