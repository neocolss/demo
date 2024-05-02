package com.solution.demo.framework.utils;

import com.jcraft.jsch.*;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPCmd;

import java.io.IOException;
import java.io.InputStream;

public class ShellUtils {

    int iExitValue;
    String sCommandString;

    public void execute_shell(String shellScript) {
        try {
            Process awk = new ProcessBuilder("/bin/bash", Constants.SHELL_DIRECTORY + shellScript).start();
            awk.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runScript(String command) {
        sCommandString = command;
        CommandLine oCmdLine = CommandLine.parse(sCommandString);
        DefaultExecutor oDefaultExecutor = new DefaultExecutor();
        oDefaultExecutor.setExitValue(0);
        try {
            iExitValue = oDefaultExecutor.execute(oCmdLine);
        } catch (ExecuteException e) {
            System.err.println("Execution failed.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("permission denied.");
            e.printStackTrace();
        }
    }

    public void connectToFTPServer(String server, int port, String userName, String password) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.setPassiveLocalIPAddress("10.59.14.93");
            ftpClient.connect(server, port);

            System.out.println(ftpClient.getReplyString());

            ftpClient.sendCommand(FTPCmd.USER, userName);

            System.out.println(ftpClient.getReplyString());

            ftpClient.sendCommand(FTPCmd.PASS, password);

            System.out.println(ftpClient.getReplyString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void secureConnect(String server, int port, String user, String privateKeyFile) {
        String SFTPHOST = server;
        int SFTPPORT = port;
        String SFTPUSER = user;
        // this file can be id_rsa or id_dsa based on which algorithm is used to create the key
        //String privateKey = "C:\\Users\\ymourtaji\\.ssh\\id_rsa";
        String privateKey = privateKeyFile;
        //String SFTPWORKINGDIR = "/XCHANGE/CVG-ACCR/files/IS/out/EXP/ACC";
        JSch jSch = new JSch();
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        try {
            jSch.addIdentity(privateKey);
            System.out.println("Private Key Added.");
            session = jSch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            System.out.println("session created.");
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            //channel = session.openChannel("sftp");
            //channel.connect();
            //System.out.println("shell channel connected....");
            //channelSftp = (ChannelSftp) channel;
            //channelSftp.cd(SFTPWORKINGDIR);
            //System.out.println("Changed the directory...");
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            //channelExec.setCommand("");

            channelExec.setCommand("cd /XCHANGE/CVG-ACCR/files/IS/out/EXP/ACC && ls -Atc1 | head -1");

            InputStream in = channelExec.getInputStream();
            channelExec.connect();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    System.out.print(new String(tmp, 0, i));
                }
                if (channelExec.isClosed()) {
                    break;
                }
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channelSftp != null) {
                channelSftp.disconnect();
                channelSftp.exit();
            }
            if (channel != null)
                channel.disconnect();
            if (session != null)
                session.disconnect();
        }
    }
}
