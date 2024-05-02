package com.solution.demo.framework.functional;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.ItemAction;
import com.solution.demo.framework.webdriver.WebDriverFactory;

public class Transverse {
    /**
     * Go to URL
     * @param url
     */
    public static void get(String url) {
        //Reporter.getInstance().newModule();
        ItemAction.goToURL(url);
        //Reporter.getInstance().closeModule();
    }

    /**
     * Quit driver
     */
    public static void quit() {
        WebDriverFactory.quit();
    }
}
