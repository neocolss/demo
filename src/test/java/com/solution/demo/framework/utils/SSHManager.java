package com.solution.demo.framework.utils;

import com.jcraft.jsch.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;

public class SSHManager {

    private static Session session;
    private static Channel channel;
    private static ChannelSftp channelSftp;
    private static ChannelExec channelExec;
    private static Logger log = LogManager.getLogger(SSHManager.class);
    private static SSHHelperClass helperClass = SSHHelperClass.getInstance();

    public static enum ChannelType {
        SHELL,
        SFTP,
        EXEC
    }

    public static void openConnection(String remoteHost) throws JSchException, SecurityException {
        if (session == null || !session.isConnected()) {
            String userName = helperClass.getSshUser();
            log.debug("Remote host: " + remoteHost);
            log.debug("SSH user: " + userName);
            if (helperClass.getSshAuthMode().equalsIgnoreCase("key")) {
                String key = helperClass.getSshKey();
                log.debug("SSH key: " + key);
                session = connectWithKey(remoteHost, userName, key);
            } else if (helperClass.getSshAuthMode().equalsIgnoreCase("password")) {
                String password = helperClass.getSshPassword();
                log.debug("SSH password: " + password);
                session = connectWithPassword(remoteHost, userName, password);
            } else {
                String password = helperClass.getSshPassword();
                log.warn("No SSH authentication mode provided. It will try with the default mode that is with password: " + password);
                session = connectWithPassword(remoteHost, userName, password);
            }
        } else
            log.warn("Session already connected. Disconnect first for new session.");
    }

    private static Session connectWithPassword(String hostname, String username, String password) throws JSchException, SecurityException {
        JSch jSch = new JSch();
        Session _session = jSch.getSession(username, hostname, 22);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        config.put("PreferredAuthentications", "publickey,keyboard-interactive,password");
        _session.setConfig(config);
        _session.setPassword(password);

        log.info("Connecting SSH to " + hostname + " - Please wait for few seconds... ");
        _session.connect();
        log.info("Connected!");
        return _session;

    }

    private static Session connectWithKey(String hostname, String username, String key, int port) throws JSchException, SecurityException {
        JSch jSch = new JSch();
        log.info("Key file path: " + key);
        jSch.addIdentity(key);
        Session _session = jSch.getSession(username, hostname, port);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        //config.put("PreferredAuthentications","publickey,keyboard-interactive,password");
        _session.setConfig(config);

        log.info("Connecting SSH to " + hostname + " - Please wait for few seconds... ");
        _session.connect();
        log.info("Connected!");
        return _session;
    }

    private static Session connectWithKey(String hostname, String username, String key) throws JSchException, SecurityException {
        JSch jSch = new JSch();
        log.info("Key file path: " + key);
        jSch.addIdentity(key);
        Session _session = jSch.getSession(username, hostname, 22);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        //config.put("PreferredAuthentications","publickey,keyboard-interactive,password");
        _session.setConfig(config);

        log.info("Connecting SSH to " + hostname + " - Please wait for few seconds... ");
        _session.connect();
        log.info("Connected!");
        return _session;
    }


    private static void createChannel(ChannelType channelType) {
        if (channel == null || !channel.isConnected()) {
            try {
                if (channelType == ChannelType.SHELL) {
                    channel = session.openChannel("shell");
                    channel.connect();
                    log.info("Shell channel created!");
                } else if (channelType == ChannelType.SFTP) {
                    channel = session.openChannel("sftp");
                    channel.connect();
                    log.info("SFTP channel created!");
                } else if (channelType == ChannelType.EXEC) {
                    channel = session.openChannel("exec");
                    log.info("EXEC channel created!");
                } else
                    throw new JSchException("Invalid channel type");
            } catch (JSchException e) {
                log.error("Error while opening channel: " + e);
            }
        }
    }

    public static String executeCommand(List<String> commands, String remoteHost) throws JSchException, IOException {

        log.info("Opening SSH session...");
        openConnection(remoteHost);
        log.info("Creating shell channel...");
        createChannel(ChannelType.SHELL);

        log.info("Sending commands...");
        PrintStream out = new PrintStream(channel.getOutputStream());

        out.println("#!/bin/bash");
        for (String command : commands) {
            System.out.println("command : " + command);
            out.println(command);
        }
        out.println("exit");
        out.flush();
        String output = getChannelOutput(channel);
        log.info("Finished sending commands!");
        log.info("Closing session...");
        close();
        log.info("Returning execution output...");
        return output;
    }

    public static String executeCommand(List<String> commands, String remoteHost, ChannelType channelType) throws Exception {

        log.info("Opening SSH session...");
        openConnection(remoteHost);

        String output = "";
        if (channelType == ChannelType.SHELL) {
            log.info("Creating shell channel...");
            createChannel(ChannelType.SHELL);

            log.info("Sending commands...");
            PrintStream out = new PrintStream(channel.getOutputStream());

            out.println("#!/bin/bash");
            for (String command : commands) {
                out.println(command);
            }
            out.println("exit");
            out.flush();
            output = getChannelOutput(channel);

        } else if (channelType == ChannelType.EXEC) {
            StringBuilder output_ = new StringBuilder();
            log.info("Creating exec channel...");
            createChannel(ChannelType.EXEC);

            log.info("Sending commands...");
            channelExec = (ChannelExec) channel;

            //ChannelExec accepts only one shell command
            String command = commands.get(0);
            log.info("Sending command EXEC:" + command);
            //channelExec.setPty(true);
            channelExec.setCommand(command);

            channelExec.setInputStream((InputStream) null);
            InputStreamReader stream = new InputStreamReader(channelExec.getInputStream());
            channelExec.setErrStream(System.err, true);
            channelExec.connect();
            char[] buffer = new char[128];
            int read;
            while ((read = stream.read(buffer, 0, buffer.length)) >= 0) {
                output_.append(buffer, 0, read);

            }
            output = output_.toString().trim();
            System.out.println(output_.toString().trim());
            stream.close();


            //output = getChannelOutput(channelExec);
        }
        log.info("Finished sending commands!");
        log.info("Closing session...");
        close();
        log.info("Returning execution output...");
        return output;
    }

    public static void copyFileToRemoteHost(String sourcePath, String destinationPath, String remoteHost) throws SftpException, IOException, JSchException {
        sourcePath = sourcePath.contains("\n") ? sourcePath.replace("\n", "") : sourcePath;
        destinationPath = destinationPath.contains("\n") ? destinationPath.replace("\n", "") : destinationPath;
        log.info("Opening SSH session...");
        openConnection(remoteHost);
        log.info("Creating SFTP channel...");
        createChannel(ChannelType.SFTP);
        log.info("Copying file to remote host: " + remoteHost + " from " + sourcePath + " to " + destinationPath);
        channelSftp = (ChannelSftp) channel;
        channelSftp.cd(destinationPath);
        File sourceFile = new File(sourcePath);
        channelSftp.put(new FileInputStream(sourceFile), sourceFile.getName(), channelSftp.OVERWRITE);
        log.info("File transfer complete.");
        log.info("Closing session...");
        close();
    }

    public static void copyFileFromRemoteHost(String serverSourceFolderPath, String serverSourceFileName, String destinationPath, String remoteHost) throws SftpException, IOException, JSchException {
        serverSourceFileName = serverSourceFileName.contains("\n") ? serverSourceFileName.replace("\n", "") : serverSourceFileName;
        destinationPath = destinationPath.contains("\n") ? destinationPath.replace("\n", "") : destinationPath;
        log.info("Opening SSH session...");
        openConnection(remoteHost);
        log.info("Creating SFTP channel...");
        createChannel(ChannelType.SFTP);
        log.info("Copying file from remote host: " + remoteHost + " from " + serverSourceFolderPath + serverSourceFileName + " to " + destinationPath);
        channelSftp = (ChannelSftp) channel;
        channelSftp.cd(serverSourceFolderPath);
        Vector foundFiles = channelSftp.ls(serverSourceFolderPath);
        for (int i = 2; i < foundFiles.size(); i++) {

            ChannelSftp.LsEntry files = (ChannelSftp.LsEntry) foundFiles.get(i);
            String fileName = files.getFilename();
            System.out.println("found file: " + fileName + " , searched file :" + serverSourceFileName);
            System.out.println("Found file with details : " + files.getLongname());
            System.out.println("Found file on path: " + channelSftp.pwd());
            if (fileName.equals(serverSourceFileName)) {
                log.info("|==>File: " + files.getFilename() + " found.<==|");
                byte[] buffer = new byte[1024];
                BufferedInputStream bis = new BufferedInputStream(channelSftp.get(fileName));
                File newFile = new File(destinationPath + fileName);
                OutputStream os = new FileOutputStream(newFile);
                BufferedOutputStream bos = new BufferedOutputStream(os);
                int readCount;
                while ((readCount = bis.read(buffer)) > 0) {
                    bos.write(buffer, 0, readCount);
                }
                bis.close();
                bos.close();
                log.info("File transfer complete.");
                break;
            } else {
                //System.out.println("File: " + files.getFilename() + " Not found.");
            }
        }
        log.info("Closing session...");
        close();
    }

    public static void copyFileFromRemoteHost_v2(String serverSourceFolderPath, String serverSourceFileName, String destinationPath, String remoteHost) throws SftpException, IOException, JSchException {
        serverSourceFileName = serverSourceFileName.contains("\n") ? serverSourceFileName.replace("\n", "") : serverSourceFileName;
        destinationPath = destinationPath.contains("\n") ? destinationPath.replace("\n", "") : destinationPath;
        log.info("Opening SSH session...");
        openConnection(remoteHost);
        log.info("Creating SFTP channel...");
        createChannel(ChannelType.SFTP);
        log.info("Copying file from remote host: " + remoteHost + " from " + serverSourceFolderPath + serverSourceFileName + " to " + destinationPath);
        channelSftp = (ChannelSftp) channel;
        channelSftp.cd(serverSourceFolderPath);
        Vector foundFiles = channelSftp.ls(serverSourceFolderPath);
        for (int i = 0; i < foundFiles.size(); i++) {

            ChannelSftp.LsEntry files = (ChannelSftp.LsEntry) foundFiles.get(i);
            String fileName = files.getFilename();
            //System.out.println("found file: " + fileName+ " , searched file :"+serverSourceFileName);
            //System.out.println("Found file with details : " + files.getLongname());
            //System.out.println("Found file on path: " + channelSftp.pwd());
            if (fileName.equals(serverSourceFileName)) {
                log.info("|==>File: " + files.getFilename() + " found.<==|");
                byte[] buffer = new byte[1024];
                BufferedInputStream bis = new BufferedInputStream(channelSftp.get(fileName));
                File newFile = new File(destinationPath + fileName);
                OutputStream os = new FileOutputStream(newFile);
                BufferedOutputStream bos = new BufferedOutputStream(os);
                int readCount;
                while ((readCount = bis.read(buffer)) > 0) {
                    bos.write(buffer, 0, readCount);
                }
                bis.close();
                bos.close();
                log.info("File transfer complete.");
                break;
            } else {
                //System.out.println("File: " + files.getFilename() + " Not found.");
            }
        }
        log.info("Closing session...");
        close();
    }

    public static void removeFileFromRemoteHost(String targetPath, String remoteHost) throws JSchException, SftpException, IOException {
        log.info("Opening SSH session...");
        openConnection(remoteHost);
        log.info("Creating SFTP channel...");
        createChannel(ChannelType.SFTP);
        log.info("Removing file from remote host: " + remoteHost + " at " + targetPath);
        channelSftp = (ChannelSftp) channel;
        channelSftp.rm(targetPath);
        log.info("File removed");
        log.info("Closing session...");
        close();
    }

    private static String getChannelOutput(Channel channel) throws IOException {
        byte[] buffer = new byte[1024];
        InputStream in = channel.getInputStream();
        String line = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int minuteBefore = calendar.get(Calendar.MINUTE);
        int minuteAfter = 0;
        while (true) {
            while (in.available() > 0) {
                int i = in.read(buffer, 0, 1024);
                if (i < 0) {
                    break;
                }
                line += "\n" + new String(buffer, 0, i);
                //log.info("output...:" + line);
            }

            if (line.contains("logout")) {
                break;
            }

            if (channel.isClosed()) {
                break;
            }
            calendar.setTime(new Date());
            minuteAfter = calendar.get(Calendar.MINUTE);
            if (minuteAfter - minuteBefore >= 3)
                break;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.info("line:" + line);
        return line;
    }

    public static void close() {
        channel.disconnect();
        session.disconnect();
        log.info("Disconnected channel and session");
    }

}
