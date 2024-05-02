package com.solution.demo.framework.functional.modules.batch;

import com.solution.demo.framework.connect.SSH;
import com.solution.demo.framework.data.DB_Instances;
import com.solution.demo.framework.data.Queries;
import com.solution.demo.framework.utils.Constants;
import com.solution.demo.framework.utils.SSHHelperClass;

import java.util.Arrays;

/**
 * Medecine Douce Test Module
 */
public class MedecineDouce {
    public static String facture_input_file = "ISB2AFAP_TEST_AB";
    public static String input_folder_destination = "/batch/inputfile/";
    public static String HOME = "/DEV/infinite/i8Odc7";
    public static String i8Odc7_server = Constants.i8Odc7_SERVER;
    public static String sshUser = "i8Odc7";
    public static String sshAuthMode = "password";
    public static String sshPassword = "i8Odc7";
    public static String GOFLUX = "nohup $HOME/batch/sh/goflux.sh -e -R -M -S J:23:00 >> $HOME/batch/logs/goflux_$(date +\\%Y\\%m\\%d).log 2>&1 &";

    public static SSHHelperClass sshHelperClass = new SSHHelperClass(sshUser, sshAuthMode, "", sshPassword);

    static String env = "I8ODC7";
    public static SSH ssh_i8Odc7 = new SSH("I8ODC7");

    static String db_env = "I8ODC7";

    static String shFolder = "/batch/sh";
    static String purgeManuScript = "purgemanuscript.sh";


    /**
     * Home Directory for an ENV : this special case for I8ODC7
     * @return
     */
    static String HOME() {
        try {
            String HOME_i8Odc7 = ssh_i8Odc7.executeCommand(Arrays.asList(new String[]{"echo $HOME"}), SSH.ChannelType.EXEC);
            return HOME_i8Odc7;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/DEV/infinite/" + env;
    }


    public static void copyFactureToBatchInputFolder() {
        try {
            ssh_i8Odc7.copyFileFromLocalToRemoteHost(Constants.SHELL_DIRECTORY + facture_input_file, HOME() + input_folder_destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //gfc : go flux client
    public static void executerGoFlux() {
        String result = null;
        try {
            result = ssh_i8Odc7.executeCommand(Arrays.asList(new String[]{GOFLUX}), SSH.ChannelType.EXEC);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("result:" + result);
    }

    public static void arreterGoFlux() {
        try {
            ssh_i8Odc7.executeCommand(Arrays.asList(new String[]{"cd " + HOME() + "/batch/sh && ./gfc.sh stop"}), SSH.ChannelType.EXEC);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void supprimerLesFichiersDerreurs() {

        try {
            ssh_i8Odc7.executeCommand(Arrays.asList(new String[]{"cd " + HOME() + "/batch/inputfile && rm *.EN_ERREUR_GOFLUX"}), SSH.ChannelType.EXEC);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void changerStatutsVersOKdsLaBD() {
        try {
            Queries.createQuery("UPDATE ENXTRAEXCL SET ENXTSTTTRT = 'OK' , ENXTSTTFCT = 'OK' WHERE ENXTCODTRT = 'XFII'", db_env).execute();
            Queries.createQuery("UPDATE ENPCDURCL set ENPCINDTRT = 'N' WHERE ENPCCODTRT = 'XFII'", db_env).execute();
            Queries.createQuery("commit", db_env).execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB_Instances.get(db_env).disconnect();
        }

    }

    public static void supprimerLesFichiersReprise() {
        try {
            ssh_i8Odc7.executeCommand(Arrays.asList(new String[]{"cd " + HOME() + "/data/XFII/ && rm *.status.REPRISE"}), SSH.ChannelType.EXEC);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void purgeManu() {
        String nom_traitement = "XFII";
        String nom_dossier_a_purger = "XFII.FICHIER.01001.20221017-160818";
        String type_traitement = "ALL";
        String date_traitement = "20220624";
        try {
            ssh_i8Odc7.copyFileFromLocalToRemoteHost(Constants.SHELL_DIRECTORY + purgeManuScript, HOME() + shFolder);
            ssh_i8Odc7.executeCommand(Arrays.asList(new String[]{"cd " + HOME() + shFolder + " && chmod 777 " + purgeManuScript + " && ./" + purgeManuScript + " " + nom_traitement + " " + nom_dossier_a_purger + " " + type_traitement + " " + date_traitement}), SSH.ChannelType.EXEC);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
