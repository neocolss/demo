package com.solution.demo.tests.batch.tpg_2105;

import com.solution.demo.framework.data.DB_Instances;
import com.solution.demo.framework.data.Queries;
import com.solution.demo.framework.functional.modules.batch.TPG_2105;
import com.solution.demo.framework.utils.Constants;
import com.solution.demo.framework.utils.TestBase;
import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestTPG2105VisioDroit extends TestBase {


    //Controle d'intégration
    @DisplayName("TPG 2105 VisioDroit")
    @Tags(value = {@Tag("BATCH"), @Tag("TPG_2105")})
    @Test
    public void testTPG2105() throws Exception {
        //Execution du batch 302
        TPG_2105.execute_302_batch();

        Thread.sleep(30000);

        //Recupérer le fichier généré du repertoire /XCHANGE/CVG-ACCR/files/IS/out/EXP/ACC
        TPG_2105.getLastModifiedFile();
        TPG_2105.copyFileFromServer();

        String psFile_ = TPG_2105.psFile;

        //Source user
        TPG_2105.sourceUser();
        //Integration de ce fichier dans XXX par shell : Serveur i8Odc3
        TPG_2105.copyFileToi8Odc3();
        TPG_2105.IntegrerFichierToTPG();

        List<String> psFileContent = Files.readAllLines(new File(Constants.SHELL_DIRECTORY + psFile_).toPath(), Charset.defaultCharset());
        //System.out.println("psFileContent:"+psFileContent);

        //Comparer le xfpsindduo des PS avec le fichier intégré
        List<String> psTraites = new ArrayList<>();

        System.out.println("<<<<<<TEST>>>>>>>>");

        for (String ps : psFileContent) {
            String numpsa = ps.substring(0, ps.length() - 1);
            char psFileIndduo = ps.charAt(ps.length() - 1);
            System.out.println("ps du fichier en question :" + numpsa + ", indduo:" + psFileIndduo);

            //Chercher le ps en question dans la base i8odc3
            ResultSet rs = Queries.getDatafromSelectQuery("select xfpsnumpsa,xfpsindduo from xfpsante where xfpsnumpsa='" + numpsa + "'", "I8ODC3");

            //En cas de correspondance avec la DB
            while (rs.next()) {
                String xfpsnumpsa = rs.getString("xfpsnumpsa");
                String xfpsindduo = String.valueOf(rs.getString("XFPSINDDUO").charAt(0));
                System.out.println("xfpsindduo DB:" + xfpsindduo + ", xfpsindduo psFile:" + psFileIndduo);

                if (xfpsnumpsa.equalsIgnoreCase(numpsa)) {
                    //si le ps dans la base est egal à 2 ou O on fait rien
                    if (xfpsindduo.equals("2") || xfpsindduo.equals("O")) {
                        continue;
                    }
                    //Sinon on remplace par la valeur trouvée dans le fichier
                    if (!xfpsindduo.equals(String.valueOf(psFileIndduo))) {
                        Queries.createQuery("update xfpsante set xfpsindduo = " + String.valueOf(psFileIndduo) + " where xfpsnumpsa =" + numpsa, "I8ODC3").executeUpdate();
                    }

                    System.out.println("ps traité.");
                    psTraites.add(xfpsnumpsa);
                    System.out.println("liste des ps traités:" + psTraites);
                }

            }
            DB_Instances.get("I8ODC3").disconnect();
        }
    }

}
