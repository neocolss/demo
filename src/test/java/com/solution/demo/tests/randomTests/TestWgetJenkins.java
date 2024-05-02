package com.solution.demo.tests.randomTests;

import com.solution.demo.framework.connect.SSH;
import com.solution.demo.framework.utils.Constants;
import com.solution.demo.framework.utils.TestBase;
import org.junit.jupiter.api.*;

import java.util.Arrays;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestWgetJenkins extends TestBase {
    @DisplayName("Random IHM TESTS=> TEST the NEW ITEM CLASS")
    @Test
    @Tags(value = {@Tag("BATCH"), @Tag("TEST_RANDOM")})
    public void test() throws Exception {

        String script = "myscript.sh";
        String HOME = "/DEV/infinite/i8Odc7";
        String i8Odc7_server = Constants.i8Odc7_SERVER;
/*
        SSHHelperClass sshHelperClass = new SSHHelperClass("i8Odc7", "password", "", "i8Odc7");
        SSHManager.copyFileToRemoteHost(Constants.SHELL_DIRECTORY+ "myscript.sh", HOME+"/testdata/", i8Odc7_server);

        //SSHManager.executeCommand(Arrays.asList(new String[]{"cd "+HOME+"/testdata/"}), i8Odc7_server, SSHManager.ChannelType.EXEC);

        //SSHManager.executeCommand(Arrays.asList(new String[]{"pwd"}), i8Odc7_server, SSHManager.ChannelType.EXEC);

        //SSHManager.executeCommand(Arrays.asList(new String[]{"ls"}), i8Odc7_server, SSHManager.ChannelType.EXEC);

        //SSHManager.executeCommand(Arrays.asList(new String[]{"./"+script+" key \"ls -t | tail -n 1\""}), i8Odc7_server, SSHManager.ChannelType.EXEC);

        SSHManager.executeCommand(Arrays.asList(new String[]{"cd "+HOME+"/testdata/" + " && " + "chmod 777 "+ script + " && " +"./"+script+" key pwd"}), i8Odc7_server, SSHManager.ChannelType.EXEC);

        SSHManager.executeCommand(Arrays.asList(new String[]{"cd "+HOME+"/testdata/" + " && " + "chmod 777 "+ script + " && " +"./"+script+" key pwd"}), i8Odc7_server, SSHManager.ChannelType.EXEC);

        //SSHManager.close();

 */

/*
        SSH ssh_i8Odc7_server = new SSH("I8ODC7");
        String home_7 = ssh_i8Odc7_server.executeCommand(Arrays.asList(new String[]{"echo $HOME"}), SSH.ChannelType.EXEC);
        ssh_i8Odc7_server.copyFileFromLocalToRemoteHost(Constants.SHELL_DIRECTORY+ "myscript.sh", home_7+"/testdata/");


        SSH ssh_i8Odc8_server = new SSH("I8ODC8");
        String home_8 = ssh_i8Odc8_server.executeCommand(Arrays.asList(new String[]{"echo $HOME"}), SSH.ChannelType.EXEC);
        ssh_i8Odc8_server.copyFileFromLocalToRemoteHost(Constants.SHELL_DIRECTORY+ "myscript.sh", home_8+"/testdata/");

 */
        try {
            //String HOME = ssh_dctpbdda02_SERVER.executeCommand(Arrays.asList(new String[]{"echo $HOME"}), SSH.ChannelType.EXEC);
            //ssh_dctpbdda02_SERVER.copyFileFromLocalToRemoteHost(Constants.SHELL_DIRECTORY+script, HOME);
            //ssh_dctpbdda02_SERVER.executeCommand(Arrays.asList(new String[]{"cd "+HOME + " && " + "chmod 777 "+ script + " && " +"./"+script+" 990-Purge_PS_DEV2"}), SSH.ChannelType.EXEC);

            String script_jenkins_pilotage = "jenkinsREST_PILOTAGE.sh";
            String job_name = "001-denormalisation-traces-ws-Java_XX_SPT_CVG";
            SSH ssh_pilotage = new SSH("PILOTAGE");
            String HOME_ = ssh_pilotage.executeCommand(Arrays.asList(new String[]{"echo $HOME"}), SSH.ChannelType.EXEC);
            System.out.println("HOME:" + HOME_);
            //ssh_pilotage.copyFileFromLocalToRemoteHost(Constants.SHELL_DIRECTORY+script_jenkins_pilotage, HOME_);
            //ssh_pilotage.executeCommand(Arrays.asList(new String[]{"cd "+HOME_ + " && " + "chmod 777 "+ script_jenkins_pilotage + " && " +"./"+script_jenkins_pilotage+" " + job_name}), SSH.ChannelType.EXEC);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
