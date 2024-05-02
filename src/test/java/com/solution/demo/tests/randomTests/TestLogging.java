package com.solution.demo.tests.randomTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;

public class TestLogging {
    private static final Logger log = LogManager.getLogger(TestLogging.class);


    public static void main(String[] args) {

        System.out.println("--------------------------");
        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();
        System.out.println(config.getConfigurationSource());
        System.out.println("--------------------------");

        log.debug("msg de debogage X");
        log.info("msg d'information X");
        log.warn("msg d'avertissement X");
        log.error("msg d'erreur X");
        log.fatal("msg d'erreur fatale X");


    }
}
