package com.solution.demo.tests.randomTests;

import com.solution.demo.framework.connect.SSH;
import com.solution.demo.framework.utils.Constants;

import java.util.Arrays;

public class TestShellExecution {
    public static SSH ssh_i8Odc7 = new SSH("I8ODC7");


    public static void main(String[] args) throws Exception {

        String purgemanu = "purgemanuscript.sh";
        String test = "test.sh";
        String HOME = ssh_i8Odc7.executeCommand(Arrays.asList(new String[]{"pwd"}), SSH.ChannelType.EXEC);
        System.out.println("Home" + HOME);
        String sh_dir = HOME + "/batch/sh";
        ssh_i8Odc7.executeCommand(Arrays.asList(new String[]{"cd " + sh_dir}), SSH.ChannelType.EXEC);
        ssh_i8Odc7.copyFileFromLocalToRemoteHost(Constants.SHELL_DIRECTORY + purgemanu, sh_dir);
        String out = ssh_i8Odc7.executeCommand(Arrays.asList(new String[]{"cd " + sh_dir + " && " + "chmod 777 " + purgemanu + " && " + "./" + purgemanu}), SSH.ChannelType.EXEC);
        System.out.println(out);

        /*
        String test = "test.sh";
        String HOME = ssh_i8Odc7.executeCommand(Arrays.asList(new String[]{"pwd"}), SSH.ChannelType.EXEC);
        System.out.println("----Home:"+HOME+"---------");
        ssh_i8Odc7.executeCommand(Arrays.asList(new String[]{"cd "+HOME}), SSH.ChannelType.EXEC);
        ssh_i8Odc7.copyFileFromLocalToRemoteHost(Constants.SHELL_DIRECTORY+"echo.sh", HOME);
        ssh_i8Odc7.executeCommand(Arrays.asList(new String[]{"cd "+HOME + " && " + "chmod 777 "+ "echo.sh" + " && " +"./"+test}));
        ssh_i8Odc7.copyFileFromLocalToRemoteHost(Constants.SHELL_DIRECTORY+test, HOME);
        String out = ssh_i8Odc7.executeCommand(Arrays.asList(new String[]{"cd "+HOME + " && " + "chmod 777 "+ test + " && " +"./"+test}));
        System.out.println(out);

         */

    }
}
