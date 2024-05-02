package com.solution.demo.framework.connect;

import com.solution.demo.framework.conf.Configuration;
import com.solution.demo.framework.conf.IniFile;
import com.solution.demo.framework.utils.Constants;
import com.jcraft.jsch.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * Generic Class to connect to a server using SSH
 * Authentication can be using a password or a key
 * The principal file to use is servers.ini which is in Resources/server/servers.ini
 */
public class SSH {

    private String env;
    private String host;
    private String sshUser;
    private String sshAuthMode;
    private String sshKey;
    private String sshPassword;

    private static Session session;
    private static Channel channel;
    private static ChannelSftp channelSftp;
    private static ChannelExec channelExec;
    private static final Logger log = LogManager.getLogger(SSH.class);

    /**
     * Initiate Server directly by credentials
     *
     * @param sshUser
     * @param sshAuthMode
     * @param sshKey
     * @param sshPassword
     */
    public SSH(String sshUser, String sshAuthMode, String sshKey, String sshPassword) {
        this.sshUser = sshUser;
        this.sshAuthMode = sshAuthMode;
        this.sshKey = sshKey;
        this.sshPassword = sshPassword;
    }

    /**
     * Initiate Server directly by environnement name
     *
     * @param env env head from Resources/server/servers.ini
     */
    public SSH(String env) {
        IniFile iniFile = Configuration.serversIniConf;
        this.host = iniFile.getString(env, "HOST");
        this.sshUser = iniFile.getString(env, "USER");
        this.sshAuthMode = iniFile.getString(env, "AUTH_MODE");
        this.sshKey = Constants.SHELL_DIRECTORY + iniFile.getString(env, "KEY");
        this.sshPassword = iniFile.getString(env, "PASSWORD");
        this.env = env;
    }

    /**
     * Types of communication To send commands to Server
     */
    public enum ChannelType {
        SHELL,
        SFTP,
        EXEC
    }

    /**
     * Connect to server
     *
     * @param remoteHost
     * @throws JSchException
     * @throws SecurityException
     */
    public void openConnection(String remoteHost) throws JSchException, SecurityException {
        if (session == null || !session.isConnected()) {
            //String userName = helperClass.getSshUser();
            String userName = this.getSshUser();
            //log.debug("Remote host: " + remoteHost);
            log.info("Remote host: " + remoteHost);
            log.info("SSH user: " + userName);
            //log.info("SSH user: " + userName);
            //if (helperClass.getSshAuthMode().equalsIgnoreCase("key")) {
            if (this.getSshAuthMode().equalsIgnoreCase("key")) {
                String key = this.getSshKey();
                log.info("SSH key: " + key);
                //log.info("SSH key: " + key);
                session = connectWithKey(remoteHost, userName, key);
            } else if (this.getSshAuthMode().equalsIgnoreCase("password")) {
                String password = this.getSshPassword();
                log.info("SSH password: " + password);
                //log.info("SSH password: " + password);
                session = connectWithPassword(remoteHost, userName, password);
            } else {
                String password = this.getSshPassword();
                log.warn("No SSH authentication mode provided. It will try with the default mode that is with password: " + password);
                session = connectWithPassword(remoteHost, userName, password);
            }
        } else
            log.warn("Session already connected. Disconnect first for new session.");
    }

    private Session connectWithPassword(String hostname, String username, String password) throws JSchException, SecurityException {
        JSch jSch = new JSch();
        Session _session = jSch.getSession(username, hostname, 22);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        config.put("PreferredAuthentications", "publickey,keyboard-interactive,password");
        _session.setConfig(config);
        _session.setPassword(password);

        //log.info("Connecting SSH to " + hostname + " - Please wait for few seconds... ");
        log.info("Connecting SSH to env :" + env + ", hostname: " + hostname + " - Please wait for few seconds... ");
        _session.connect();
        log.info("Connected!");
        return _session;

    }

    private Session connectWithKey(String hostname, String username, String key, int port) throws JSchException, SecurityException {
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

    private Session connectWithKey(String hostname, String username, String key) throws JSchException, SecurityException {
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

    /**
     * Open channel of communication with the server
     *
     * @param channelType
     */
    private void createChannel(SSH.ChannelType channelType) {
        if (channel == null || !channel.isConnected()) {
            try {
                if (channelType == SSH.ChannelType.SHELL) {
                    channel = session.openChannel("shell");
                    channel.connect();
                    log.info("Shell channel created!");
                } else if (channelType == SSH.ChannelType.SFTP) {
                    channel = session.openChannel("sftp");
                    channel.connect();
                    log.info("SFTP channel created!");
                } else if (channelType == SSH.ChannelType.EXEC) {
                    channel = session.openChannel("exec");
                    log.info("EXEC channel created!");
                } else
                    throw new JSchException("Invalid channel type");
            } catch (JSchException e) {
                log.error("Error while opening channel: " + e);
            }
        }
    }

    public String executeCommand(List<String> commands) throws JSchException, IOException {

        log.info("Opening SSH session to env : " + this.env + " ...");
        openConnection(this.host);
        log.info("Creating shell channel...");
        createChannel(SSH.ChannelType.SHELL);

        log.info("Sending commands...");
        PrintStream out = new PrintStream(channel.getOutputStream());

        out.println("!/bin/bash");
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

    public String executeShell(String... commands) throws Exception {
        log.info("Opening SSH session to env : " + this.env + " ...");

        openConnection(this.host);

        Channel channel = session.openChannel("shell");
        //channel.setInputStream(System.in);
        channel.setOutputStream(System.out);

        OutputStream inputstream_for_the_channel = channel.getOutputStream();
        PrintStream commander = new PrintStream(inputstream_for_the_channel, true);


        channel.connect();

        for (String command : commands) {
            System.out.println("command : " + command);
            commander.println(command + "\n");
        }
        InputStream in = channel.getInputStream();
        byte[] bt = new byte[1024];
        String str = "";

        while (true) {

            while (in.available() > 0) {
                int i = in.read(bt, 0, 1024);
                if (i < 0)
                    break;
                str = new String(bt, 0, i);
                //displays the output of the command executed.
                System.out.print(str);


            }
            if (channel.isClosed()) {

                break;
            }
            Thread.sleep(1000);
            channel.disconnect();
            session.disconnect();
        }
        return str;
    }

    public String executeCommand(List<String> commands, SSH.ChannelType channelType) throws Exception {

        log.info("Opening SSH session to env : " + this.env + " ...");
        openConnection(this.host);

        String output = "";
        if (channelType == SSH.ChannelType.SHELL) {
            log.info("Creating shell channel...");
            createChannel(SSH.ChannelType.SHELL);

            log.info("Sending commands...");
            PrintStream out = new PrintStream(channel.getOutputStream());

            out.println("#!/bin/bash");
            for (String command : commands) {
                out.println(command);
            }
            out.println("exit");
            out.flush();
            output = getChannelOutput(channel);

        } else if (channelType == SSH.ChannelType.EXEC) {
            StringBuilder output_ = new StringBuilder();
            log.info("Creating exec channel...");
            createChannel(SSH.ChannelType.EXEC);

            log.info("Sending commands...");
            channelExec = (ChannelExec) channel;

            //ChannelExec accepts only one shell command
            String command = commands.get(0);
            log.info("Sending command EXEC:" + command);
            //channelExec.setPty(true);
            channelExec.setCommand(command);

            channelExec.setInputStream((InputStream) null);
            channelExec.setErrStream(System.err, true);
            channelExec.setOutputStream(System.out, true);

            InputStreamReader stream = new InputStreamReader(channelExec.getInputStream());

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

    public void copyFileFromLocalToRemoteHost(String sourcePath, String destinationPath) throws SftpException, IOException, JSchException {
        sourcePath = sourcePath.contains("\n") ? sourcePath.replace("\n", "") : sourcePath;
        destinationPath = destinationPath.contains("\n") ? destinationPath.replace("\n", "") : destinationPath;
        log.info("Opening SSH session to env : " + this.env + " ...");
        //openConnection(remoteHost);
        openConnection(this.host);
        log.info("Creating SFTP channel...");
        createChannel(SSH.ChannelType.SFTP);
        log.info("Copying file to remote host: " + this.host + " from " + sourcePath + " to " + destinationPath);
        channelSftp = (ChannelSftp) channel;
        channelSftp.cd(destinationPath);
        File sourceFile = new File(sourcePath);
        channelSftp.put(new FileInputStream(sourceFile), sourceFile.getName(), channelSftp.OVERWRITE);
        log.info("File transfer complete.");
        log.info("Closing session...");
        close();
    }

    public void copyFileFromRemoteHost(String serverSourceFolderPath, String serverSourceFileName, String destinationPath) throws SftpException, IOException, JSchException {
        serverSourceFileName = serverSourceFileName.contains("\n") ? serverSourceFileName.replace("\n", "") : serverSourceFileName;
        destinationPath = destinationPath.contains("\n") ? destinationPath.replace("\n", "") : destinationPath;
        log.info("Opening SSH session to env : " + this.env + " ...");
        openConnection(this.host);
        log.info("Creating SFTP channel...");
        createChannel(SSH.ChannelType.SFTP);
        log.info("Copying file from remote host: " + this.host + " from " + serverSourceFolderPath + serverSourceFileName + " to " + destinationPath);
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

    public void copyFileFromRemoteHost_v2(String serverSourceFolderPath, String serverSourceFileName, String destinationPath) throws SftpException, IOException, JSchException {
        serverSourceFileName = serverSourceFileName.contains("\n") ? serverSourceFileName.replace("\n", "") : serverSourceFileName;
        destinationPath = destinationPath.contains("\n") ? destinationPath.replace("\n", "") : destinationPath;
        log.info("Opening SSH session to env : " + this.env + " ...");
        openConnection(this.host);
        log.info("Creating SFTP channel...");
        createChannel(SSH.ChannelType.SFTP);
        log.info("Copying file from remote host: " + this.host + " from " + serverSourceFolderPath + serverSourceFileName + " to " + destinationPath);
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

    public void removeFileFromRemoteHost(String targetPath) throws JSchException, SftpException, IOException {
        log.info("Opening SSH session to env : " + this.env + " ...");
        openConnection(this.host);
        log.info("Creating SFTP channel...");
        createChannel(SSH.ChannelType.SFTP);
        log.info("Removing file from remote host: " + this.host + " at " + targetPath);
        channelSftp = (ChannelSftp) channel;
        channelSftp.rm(targetPath);
        log.info("File removed");
        log.info("Closing session...");
        close();
    }

    private String getChannelOutput(Channel channel) throws IOException {
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
                log.info("output...:" + line);
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

    public void close() {
        channel.disconnect();
        session.disconnect();
        log.info("Disconnected channel and session env : " + this.env);
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSshUser() {
        return sshUser;
    }

    public void setSshUser(String sshUser) {
        this.sshUser = sshUser;
    }

    public String getSshAuthMode() {
        return sshAuthMode;
    }

    public void setSshAuthMode(String sshAuthMode) {
        this.sshAuthMode = sshAuthMode;
    }

    public String getSshKey() {
        return sshKey;
    }

    public void setSshKey(String sshKey) {
        this.sshKey = sshKey;
    }

    public String getSshPassword() {
        return sshPassword;
    }

    public void setSshPassword(String sshPassword) {
        this.sshPassword = sshPassword;
    }

    @Override
    public String toString() {
        return "SSH{" +
                "env='" + env + '\'' +
                ", host='" + host + '\'' +
                ", sshUser='" + sshUser + '\'' +
                ", sshAuthMode='" + sshAuthMode + '\'' +
                ", sshKey='" + sshKey + '\'' +
                ", sshPassword='" + sshPassword + '\'' +
                '}';
    }


}
