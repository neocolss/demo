package com.solution.demo.framework.connect;

import java.util.ArrayList;
import java.util.List;

public class SSH_Instances {

    /**
     * Stores a list of connected servers
     */
    static List<SSH> sshs = new ArrayList<>();

    /**
     * Use a specific Server by its environnement name
     *
     * @param environnement
     * @return instance of this server
     */
    public static SSH get(String environnement) {
        for (SSH ssh : sshs) {
            if (environnement.equalsIgnoreCase(ssh.getEnv())) {
                return ssh;
            }
        }
        SSH ssh_ = new SSH(environnement);
        sshs.add(ssh_);
        return ssh_;
    }
}
