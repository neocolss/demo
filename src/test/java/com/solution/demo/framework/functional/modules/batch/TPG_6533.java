package com.solution.demo.framework.functional.modules.batch;

import com.solution.demo.framework.connect.SSH;
import com.solution.demo.framework.utils.Constants;
import com.solution.demo.framework.utils.JenkinsUtils;
import com.solution.demo.framework.utils.SSHHelperClass;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class TPG_6533 {

    static JenkinsUtils jenkinsUtils = new JenkinsUtils(Constants.JENKINS_SERVER_990, "990-Purge_PS_DEV2");
    //static ShellUtils shellUtils = new ShellUtils();

    //Credentials
    static String dctpbdda02_SERVER = Constants.dctpbdda02_SERVER;
    static String sshUser = "root";
    static String sshAuthMode = "password";
    static String sshKey = Constants.SHELL_DIRECTORY + "Cle_SSH_PRIVEE_ACCR_OPENSSH";
    static String sshPassword = "k1zBaDRfPA";

    static String serverDetination = "/XCHANGE/CDP-RCT/files/SE/referentiel";

    static SSHHelperClass sshHelperClass = new SSHHelperClass(sshUser, sshAuthMode, sshKey, sshPassword);
    static SSH ssh_dctpbdda02_SERVER = new SSH("dctpbdda02");

    static String psFile = "PURGE_PS.txt";

    static String get_CR_File = "cd " + serverDetination + " && " + " ls -tr  CR_Purge_PS_*.csv | sort -n | tail -1";

    public static String CR_File = "";
    static String script = "jenkinsREST_dctpbdda02_SERVER.sh";

    public static void execute_990_batch() {

        //jenkinsUtils.execute_jenkins_job();
        try {
            String HOME = ssh_dctpbdda02_SERVER.executeCommand(Arrays.asList(new String[]{"echo $HOME"}), SSH.ChannelType.EXEC);
            ssh_dctpbdda02_SERVER.copyFileFromLocalToRemoteHost(Constants.SHELL_DIRECTORY + script, HOME);
            ssh_dctpbdda02_SERVER.executeCommand(Arrays.asList(new String[]{"cd " + HOME + " && " + "chmod 777 " + script + " && " + "./" + script + " 990-Purge_PS_DEV2"}), SSH.ChannelType.EXEC);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void copyFileTodctpbdda02SERVER() throws Exception {
        //SSHHelperClass sshHelperClass = new SSHHelperClass(server, "password", "", "i8Odc3");
        //SSHManager.copyFileToRemoteHost(Constants.SHELL_DIRECTORY + psFile, serverDetination, dctpbdda02_SERVER);
        ssh_dctpbdda02_SERVER.copyFileFromLocalToRemoteHost(Constants.SHELL_DIRECTORY + psFile, serverDetination);
    }


    //get the CR File from SERVER after Executing 990 Batch to Local Machine
    public static void copyCRFileFromServer() throws Exception {
        //CR_File = SSHManager.executeCommand(Arrays.asList(new String[]{get_CR_File}), dctpbdda02_SERVER, SSHManager.ChannelType.EXEC);
        CR_File = ssh_dctpbdda02_SERVER.executeCommand(Arrays.asList(new String[]{get_CR_File}), SSH.ChannelType.EXEC);
        System.out.println("CR_File_: " + CR_File);
        CR_File = CR_File.contains("\n") ? CR_File.replace("\n", "") : CR_File;
        System.out.println("CR_File__: " + CR_File);
        //SSHManager.copyFileFromRemoteHost_v2(serverDetination + "/", CR_File, Constants.SHELL_DIRECTORY, dctpbdda02_SERVER);
        ssh_dctpbdda02_SERVER.copyFileFromRemoteHost_v2(serverDetination + "/", CR_File, Constants.SHELL_DIRECTORY);
    }

    //Convert the csv CR to Map of <line_number, CR_PURGE_PS_OBJECT>
    public static LinkedHashMap<Integer, CR_PURGE_PS> csv_CR_To_Map(String csvCRFileName) {
        String thisLine;
        int count = 0;
        FileInputStream fis = null;
        DataInputStream myInput = null;
        LinkedHashMap<Integer, CR_PURGE_PS> output = new LinkedHashMap<Integer, CR_PURGE_PS>();

        try {
            fis = new FileInputStream(csvCRFileName);
            myInput = new DataInputStream(fis);
            int i = 0;

            while ((thisLine = myInput.readLine()) != null) {
                String[] line = thisLine.split(";", -1);

                CR_PURGE_PS cr_purge_ps = new CR_PURGE_PS();
                cr_purge_ps.setProprio_cdp(line[0]);
                cr_purge_ps.setIdtiers(line[1]);
                cr_purge_ps.setN_ps(line[2]);
                cr_purge_ps.setPurge_1(line[3]);
                cr_purge_ps.setMotif_1(line[4]);
                cr_purge_ps.setNom_ps(line[5]);
                cr_purge_ps.setUsrcre_1(line[6]);
                cr_purge_ps.setDatcre_1(line[7]);
                cr_purge_ps.setUsrcre_1(line[8]);
                cr_purge_ps.setUsrmaj_1(line[9]);
                cr_purge_ps.setDatmaj_1(line[10]);
                cr_purge_ps.setIdctx(line[11]);
                cr_purge_ps.setFk_tiers(line[12]);
                cr_purge_ps.setFk_compte(line[13]);
                cr_purge_ps.setTypcpt_1(line[14]);
                cr_purge_ps.setTypodr(line[15]);
                cr_purge_ps.setCat_ps(line[16]);
                cr_purge_ps.setSpe_ps(line[17]);
                cr_purge_ps.setCode_compte(line[18]);
                cr_purge_ps.setExecutant(line[19]);
                cr_purge_ps.setConvention(line[20]);
                cr_purge_ps.setEclat_prl(line[21]);
                cr_purge_ps.setEclat_vir(line[22]);
                cr_purge_ps.setDatdeb(line[23]);
                cr_purge_ps.setDatfin(line[24]);
                cr_purge_ps.setUsrcre_2(line[25]);
                cr_purge_ps.setDatcre_2(line[26]);
                cr_purge_ps.setUsrmaj_2(line[27]);
                cr_purge_ps.setIdcpt(line[28]);
                cr_purge_ps.setTypcpt_2(line[29]);
                cr_purge_ps.setEtab(line[30]);
                cr_purge_ps.setGuichet(line[31]);
                cr_purge_ps.setCompte(line[32]);
                cr_purge_ps.setCle(line[33]);
                cr_purge_ps.setBic(line[34]);
                cr_purge_ps.setIban(line[35]);
                cr_purge_ps.setPurge_2(line[36]);
                cr_purge_ps.setMotif_2(line[37]);
                cr_purge_ps.setMandat(line[38]);
                cr_purge_ps.setDate_mandat(line[39]);
                cr_purge_ps.setNom_banque(line[40]);
                cr_purge_ps.setIntitule(line[41]);
                cr_purge_ps.setUsrcre_3(line[42]);
                cr_purge_ps.setDatcre_3(line[43]);
                cr_purge_ps.setUsrmaj_3(line[44]);
                cr_purge_ps.setDatmaj_3(line[45]);
                cr_purge_ps.setIdtech(line[46]);
                cr_purge_ps.setNumprl(line[47]);
                cr_purge_ps.setNumvir(line[48]);
                cr_purge_ps.setRui(line[49]);
                cr_purge_ps.setUsrcre_4(line[50]);
                cr_purge_ps.setDatcre_4(line[51]);
                cr_purge_ps.setUsrmaj_4(line[52]);
                cr_purge_ps.setDatmaj_4(line[53]);
                cr_purge_ps.setPurge_3(line[54]);
                cr_purge_ps.setMotif_3(line[55]);

                output.put(count, cr_purge_ps);

                ++count;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    public static File writeStringToFile(String fileName, String destination, LigneTiersPS ligneTiersPS, boolean append) {
        File file = new File(destination + fileName);
        BufferedWriter bf = null;
        try {
            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file, append));
            bf.write(ligneTiersPS.getCodeProprio() + ";" + ligneTiersPS.getNumPS() + ";" + ligneTiersPS.getRaisonSocial());
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

        return file;
    }

    public static File writeStringToFile(String fileName, String destination, List<LigneTiersPS> ligneTiersPS) {
        File file = new File(destination + fileName);
        BufferedWriter bf = null;
        try {
            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file, true));
            for (LigneTiersPS ligneTiersPSElement : ligneTiersPS) {
                bf.write(ligneTiersPSElement.getCodeProprio() + ";" + ligneTiersPSElement.getNumPS() + ";" + ligneTiersPSElement.getRaisonSocial() + "\n");
            }

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

        return file;
    }

}
