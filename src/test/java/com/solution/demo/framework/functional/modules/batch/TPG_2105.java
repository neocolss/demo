package com.solution.demo.framework.functional.modules.batch;

import com.solution.demo.framework.utils.Constants;
import com.solution.demo.framework.utils.JenkinsUtils;
import com.solution.demo.framework.utils.SSHHelperClass;
import com.solution.demo.framework.utils.SSHManager;

import java.util.Arrays;

public class TPG_2105 {
    //Lancer le job du batch 302 VisioDroit V2
    static JenkinsUtils jenkinsUtils = new JenkinsUtils(Constants.JENKINS_SERVER, "302_Extraction_PS_Visiodroits_IS_ACC_CVG");
    //static ShellUtils shellUtils = new ShellUtils();

    //Credentials
    static String sshUser = "cvg-accr";
    static String sshAuthMode = "key";
    static String sshKey = Constants.SHELL_DIRECTORY + "Cle_SSH_PRIVEE_ACCR_OPENSSH";
    static String sshPassword = "";

    static String serverDetination = "/XCHANGE/CVG-ACCR/files/IS/out/EXP/ACC/";
    static String getLastModifiedFile = "cd " + serverDetination + " && ls -tc1 | head -1";


    public static String psFile = "";

    static SSHHelperClass sshHelperClass = new SSHHelperClass(sshUser, sshAuthMode, sshKey, sshPassword);

    //Execute 302 batch
    public static void execute_302_batch() {

        jenkinsUtils.execute_jenkins_job();
    }

    //Get Last Modified File from PILOTAGE SERVER after Executing 302 Batch
    public static void getLastModifiedFile() throws Exception {
        psFile = SSHManager.executeCommand(Arrays.asList(new String[]{getLastModifiedFile}), Constants.PILOTAGE_SERVER, SSHManager.ChannelType.EXEC);
        psFile = psFile.contains("\n") ? psFile.replace("\n", "") : psFile;
    }

    //Copy Last Modified File from PILOTAGE SERVER after Executing 302 Batch to Local Machine
    public static void copyFileFromServer() throws Exception {
        SSHManager.copyFileFromRemoteHost(serverDetination, psFile, Constants.SHELL_DIRECTORY, Constants.PILOTAGE_SERVER);
    }

    //Copy the file to i8Odc3
    public static void copyFileToi8Odc3() throws Exception {
        SSHHelperClass sshHelperClass = new SSHHelperClass("i8Odc3", "password", "", "i8Odc3");
        SSHManager.copyFileToRemoteHost(Constants.SHELL_DIRECTORY + psFile, "/DEV/infinite/i8Odc3/batch/inputfile/TPG-2105/", Constants.i8Odc3_SERVER);
    }

    //Integrate the file into TPG : i8Odc3 server
    public static void IntegrerFichierToTPG() throws Exception {
        SSHHelperClass sshHelperClass = new SSHHelperClass("i8Odc3", "password", "", "i8Odc3");
        String integrerFileToi8Odc3 = String.format("cd %s && goaneto.sh -b XFVIS -e FICHIER -f F1=%s/batch/inputfile/TPG-2105/%s,F2=%s/batch/inputfile/FIX55U0J", "/DEV/infinite/i8Odc3/batch", "/DEV/infinite/i8Odc3", psFile, "/DEV/infinite/i8Odc3");
        String result = SSHManager.executeCommand(Arrays.asList(new String[]{integrerFileToi8Odc3}), Constants.i8Odc3_SERVER, SSHManager.ChannelType.EXEC);
        System.out.println("result:" + result);
    }

    public static void sourceUser() throws Exception {
        SSHHelperClass sshHelperClass = new SSHHelperClass("i8Odc3", "password", "", "i8Odc3");
        String result = SSHManager.executeCommand(Arrays.asList(new String[]{". $HOME/.profile"}), Constants.i8Odc3_SERVER, SSHManager.ChannelType.EXEC);
        System.out.println("result:" + result);
    }

}
