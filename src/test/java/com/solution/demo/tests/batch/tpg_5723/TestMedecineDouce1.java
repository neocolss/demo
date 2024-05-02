package com.solution.demo.tests.batch.tpg_5723;

import com.solution.demo.framework.functional.modules.batch.MedecineDouce;
import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.TestBase;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestMedecineDouce1 extends TestBase {
    @DisplayName("Test TPG 4489 Consulation AMC")
    @Test
    @Tags(value = {@Tag("BATCH"), @Tag("TPG_5723"), @Tag("MEDECINE_DOUCE")})
    public void test1() {
        Reporter.getInstance().newModule();

        //Arrêter le Goflux
        MedecineDouce.arreterGoFlux();

        //Supprimer les fichiers en erreur
        MedecineDouce.supprimerLesFichiersDerreurs();

        //Mettre les fichiers en reprise en état OK dans la BD
        MedecineDouce.changerStatutsVersOKdsLaBD();

        //Supprimer les fichiers .status.reprise
        MedecineDouce.supprimerLesFichiersReprise();

        //Effectuer une purge avec PURGEMANU.sh
        MedecineDouce.purgeManu();

        System.out.println("--------------");
        System.out.println("<<<<< Tests >>>>>");
        System.out.println("--------------");

        //Intégrer la facture dans batch/inputfile
        MedecineDouce.copyFactureToBatchInputFolder();

        //Executer le bacth GoFlux
        MedecineDouce.executerGoFlux();

        //Arrêter le Goflux
        //MedecineDouce.arreterGoFlux();

        Reporter.getInstance().closeModule();
    }
}
