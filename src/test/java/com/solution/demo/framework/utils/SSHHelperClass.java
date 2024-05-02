package com.solution.demo.framework.utils;

public class SSHHelperClass {

    private static String sshUser;
    private static String sshAuthMode;
    private static String sshKey;
    private static String sshPassword;

    public SSHHelperClass() {
    }

    public SSHHelperClass(String sshUser, String sshAuthMode, String sshKey, String sshPassword) {
        SSHHelperClass.sshUser = sshUser;
        SSHHelperClass.sshAuthMode = sshAuthMode;
        SSHHelperClass.sshKey = sshKey;
        SSHHelperClass.sshPassword = sshPassword;
    }

    public static SSHHelperClass getInstance() {
        return new SSHHelperClass(sshUser, sshAuthMode, sshKey, sshPassword);
    }

    public String getSshUser() {
        return sshUser;
    }

    public String getSshAuthMode() {
        return sshAuthMode;
    }

    public String getSshKey() {
        return sshKey;
    }

    public String getSshPassword() {
        return sshPassword;
    }
}
