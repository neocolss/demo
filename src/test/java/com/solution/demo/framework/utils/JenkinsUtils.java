package com.solution.demo.framework.utils;

import java.net.HttpURLConnection;
import java.net.URL;

public class JenkinsUtils {
    private String jenkinsHostname;
    private String jobName;

    public JenkinsUtils(String jenkinsHostname, String jobName) {
        this.jenkinsHostname = jenkinsHostname;
        this.jobName = jobName;
    }

    //Lancer un job Jenkins
    public void execute_jenkins_job(String jenkinsHostname, String jobName) {
        String url = jenkinsHostname + jobName + "/build?token=" + Constants.JENKINS_TOKEN;
        System.out.println("jenkins job url = " + url);
        URL job_url = null;
        HttpURLConnection connection = null;
        try {
            job_url = new URL(url);
            connection = (HttpURLConnection) job_url.openConnection();
            Thread.sleep(5000);
            System.out.println("Job status is : " + connection.getResponseCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public void execute_jenkins_job() {
        String url = jenkinsHostname + "job/" + jobName + "/build?token=" + Constants.JENKINS_TOKEN;
        System.out.println("jenkins job url = " + url);
        URL job_url = null;
        HttpURLConnection connection = null;
        try {
            job_url = new URL(url);
            connection = (HttpURLConnection) job_url.openConnection();
            Thread.sleep(20000);
            System.out.println("Job status is : " + connection.getResponseCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }
}
