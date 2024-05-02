package com.solution.demo.tests.batch.tpg_6533;

import com.solution.demo.framework.data.DB_Instances;
import com.solution.demo.framework.data.Queries;
import com.solution.demo.framework.functional.modules.batch.CR_PURGE_PS;
import com.solution.demo.framework.functional.modules.batch.LigneTiersPS;
import com.solution.demo.framework.functional.modules.batch.TPG_6533;
import com.solution.demo.framework.utils.Constants;
import com.solution.demo.framework.utils.Helper;
import com.solution.demo.framework.utils.TestBase;
import org.junit.jupiter.api.*;

import java.io.File;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_TPG_6533_PS_PurgePS_Test1 extends TestBase {

    @DisplayName("TPG 6533 CDP 1803- Archiver et Purger des PS : Cas de Test 1")
    @Tags(value = {@Tag("BATCH"), @Tag("TPG_6533")})
    @Test
    public void test_6533_Test1() {

        String db_env = "dintcdp1";
        try {


            ////Create Data for the test
            String currentDate = Helper.getFormatedDate("yyyy-MM-dd HH:mm:ss");
            System.out.println("current date:" + currentDate);

            //Create a tiers
            String numPS = "095959199";
            Queries.createQuery("select setval('tiers_idtiers_seq', (SELECT MAX(idtiers) FROM tiers), true)", db_env).execute();
            Thread.sleep(1000);
            Queries.createQuery("INSERT INTO tiers (type_tiers, numero_tiers, raison_sociale, user_creation, date_creation, user_maj, date_maj, idtraitement) values('PSF', '" + numPS + "', 'Tiers Test AUTO', 'Integration_PS', '" + currentDate + "', 'Integration_PS', '" + currentDate + "', 999)", db_env).execute();

            Thread.sleep(1000);
            ResultSet rs_currentid_tiers = Queries.getDatafromSelectQuery("select * from public.tiers where numero_tiers='" + numPS + "' limit 1", db_env);
            int currentid_tiers = 0;
            String nomPS = "";
            while (rs_currentid_tiers.next()) {
                currentid_tiers = rs_currentid_tiers.getInt("idtiers");
                nomPS = rs_currentid_tiers.getString("raison_sociale");
            }
            System.out.println("currentid tiers: " + currentid_tiers);
            System.out.println("currentid raison sociale tiers: " + nomPS);


            //Create a Bank Account (Compte Bancaire)
            String numeroCompteBancaire = "90919498919";
            Queries.createQuery("select setval('compte_bancaire_idcompte_bancaire_seq', (SELECT MAX(idcompte_bancaire) FROM compte_bancaire), true)", db_env).execute();
            Thread.sleep(1000);
            Queries.createQuery("INSERT INTO compte_bancaire (type_compte, code_etablissement, code_guichet, numero_compte, cle_compte, bic, iban, ref_mandat, date_mandat, nom_banque, intitule, user_creation, date_creation, user_maj, date_maj, idtraitement) VALUES('R', '99109', '90990', '" + numeroCompteBancaire + "', '83', NULL, NULL, NULL, NULL, 'TEST AUTO', 'Test AUTO', 'Integration_PS', '" + currentDate + "', 'Integration_PS', '" + currentDate + "', 999)", db_env).execute();

            Thread.sleep(1000);
            ResultSet rs_currentid_cb = Queries.getDatafromSelectQuery("select * from public.compte_bancaire where numero_compte='" + numeroCompteBancaire + "' limit 1", db_env);

            int currentid_cb = 0;
            while (rs_currentid_cb.next()) {
                currentid_cb = rs_currentid_cb.getInt("idcompte_bancaire");
            }
            System.out.println("currentid cb: " + currentid_cb);


            //Create Tech Data(Données Tech pour le compte bancaire)
            Queries.createQuery("select setval('donnees_tech_compte_iddonnees_tech_compte_seq', (SELECT MAX(iddonnees_tech_compte) FROM donnees_tech_compte), true)", db_env).execute();
            Thread.sleep(1000);
            Queries.createQuery("INSERT INTO donnees_tech_compte (code_proprietaire, fk_donnees_compte, chrono_prelevement, chrono_virement, rui, user_creation, date_creation, user_maj, date_maj, idtraitement) VALUES('IS', " + currentid_cb + ", 3000, 3000, NULL, 'LZL', '" + currentDate + "', 'LZL', '" + currentDate + "', 999)", db_env).execute();
            Thread.sleep(1000);

            //Create contexte tiers
            Queries.createQuery("select setval('contexte_tiers_idcontexte_tiers_seq', (SELECT MAX(idcontexte_tiers) FROM contexte_tiers), true)", db_env).execute();
            Thread.sleep(1000);
            Queries.createQuery("INSERT INTO contexte_tiers (code_proprietaire, fk_contexte_tiers, fk_contexte_compte, type_compte, type_ordre, categorie_ps, specialite_ps, idf_compte_bancaire, executant, convention, eclat_prelev, eclat_virement_ps, date_debut_effet, date_fin_effet, user_creation, date_creation, user_maj, date_maj, idtraitement) VALUES('IS', " + currentid_tiers + ", " + currentid_cb + ", 'R', 'V', '', '', '', '', '', NULL, 'R', '2011-12-15', NULL, 'Integration_PS', '" + currentDate + "', 'Integration_PS', '" + currentDate + "', 999)", db_env).execute();

            //Disconnect from database
            DB_Instances.get(db_env).disconnect();


            //Write the PS to file PURGE_PS.txt
            String psFile = "PURGE_PS.txt";
            File file = TPG_6533.writeStringToFile(psFile, Constants.SHELL_DIRECTORY, new LigneTiersPS("IS", numPS, nomPS), false);
            /*
            String psFile = "PURGE_PS.txt";
            File file = new File(Constants.SHELL_DIRECTORY + psFile);
            BufferedWriter bf = null;
            try {
                // create new BufferedWriter for the output file
                bf = new BufferedWriter(new FileWriter(file, false));
                bf.write("IS;" + numPS + ";" + nomPS);
                bf.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    // always close the writer
                    bf.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

             */

            //Copy Test Data to "/XCHANGE/CDP-RCT/files/IS/referentiel"
            TPG_6533.copyFileTodctpbdda02SERVER();

            //execute jenkins job
            TPG_6533.execute_990_batch();

            Thread.sleep(60000);

            //Get the CR in SCV from "/XCHANGE/CDP-RCT/files/IS/referentiel"
            TPG_6533.copyCRFileFromServer();

            //Etudier le fichier csv CR s'il répond au Cas de test
            LinkedHashMap<Integer, CR_PURGE_PS> cr = TPG_6533.csv_CR_To_Map(Constants.SHELL_DIRECTORY + TPG_6533.CR_File);

            System.out.println("<<<<< Tests >>>>>");

            System.out.println("Vérifier que numPS2 est purgé dans le CR");
            for (Map.Entry<Integer, CR_PURGE_PS> entry : cr.entrySet()) {
                if (numPS.equals(entry.getValue().getN_ps())) {
                    assertEquals("OUI", entry.getValue().getPurge_1());
                    System.out.println(numPS + " Purge");
                }
            }

            //Vérifier que CB2 est purgé
            System.out.println("Vérifier que CB2 est purgé dans le CR");
            for (Map.Entry<Integer, CR_PURGE_PS> entry : cr.entrySet()) {
                if (numPS.equals(entry.getValue().getN_ps())) {
                    if (numeroCompteBancaire.equals(entry.getValue().getCompte())) {
                        assertEquals("OUI", entry.getValue().getPurge_2());
                        System.out.println(numeroCompteBancaire + " doit être purgé");
                    }
                }
            }

            //Vérifier que numPS est purgé dans la base de données
            System.out.println("Vérifier que numPS est purgé dans la base de données : ");
            ResultSet rs_currentid_tiers_afterTest = Queries.getDatafromSelectQuery("select * from public.tiers where numero_tiers='" + numPS + "' limit 1", db_env);
            assertEquals(false, rs_currentid_tiers_afterTest.next());

            //Vérifier que CB est purgé dans la base de données
            System.out.println("Vérifier que CB est purgé dans la base de données : ");
            Thread.sleep(1000);
            ResultSet rs_currentid_cb_afterTest = Queries.getDatafromSelectQuery("select * from public.compte_bancaire where numero_compte='" + numeroCompteBancaire + "' limit 1", db_env);
            assertEquals(false, rs_currentid_cb_afterTest.next());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
