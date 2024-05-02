package com.solution.demo.framework.webdriver;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Constants;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class WebDriverFactory {

    /* Browsers constants */
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String OPERA = "opera";
    public static final String INTERNET_EXPLORER = "ie";
    public static final String EDGE = "edge";
    public static final String SAFARI = "safari";
    public static final String IPHONE = "iphone";

    /* Platform constants */
    public static final String WINDOWS = "windows";
    public static final String ANDROID = "android";
    public static final String XP = "xp";
    public static final String VISTA = "vista";
    public static final String MAC = "mac";
    public static final String LINUX = "linux";
    public static boolean isAngularDefined = true;

    /* Webdriver */

    private static WebDriver webDriver;

    /*Current browser to be used in test cases*/
    private static String currentBrowser = CHROME;

    public WebDriverFactory() {
    }

    public static void quit() {
        try {
            if (webDriver != null) {
                webDriver.quit();
                Reporter.getInstance().logInfo("Closing webdriver");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Reporter.getInstance().logError("error in closing webdriver");
        } finally {
            webDriver = null;
            String processToKill = "";
            if (System.getProperty("os.name").contains("Windows")) {
                switch (currentBrowser) {
                    case FIREFOX:
                        processToKill = "geckodriver.exe";
                        break;
                    case CHROME:
                        processToKill = "chromedriver.exe";
                        break;
                    case EDGE:
                        processToKill = "msedgedriver.exe";
                        break;
                }
                try {
                    String commandProcessToKill = "cmd /c start taskkill /F /IM " + processToKill + " /T && exit || ver > nul";
                    System.out.println("commandProcessToKill: " + commandProcessToKill);
                    Process process = Runtime.getRuntime().exec(commandProcessToKill);
                    process.waitFor();
                    process.destroy();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public synchronized static WebDriver getInstance() {
        if (webDriver == null) {
            switch (currentBrowser) {
                case CHROME:
                    setChromeDriver();
                    webDriver = new ChromeDriver();
                    setBrowserSettings();
                    break;

                case FIREFOX:
                    setFirefoxDriver();
                    webDriver = new FirefoxDriver();
                    setBrowserSettings();
                    break;

                case INTERNET_EXPLORER:
                    isSupportedPlatform(currentBrowser);
                    webDriver = new InternetExplorerDriver();
                    break;
                case EDGE:
                    setEDGEDriver();
                    webDriver = new EdgeDriver();
                    setBrowserSettings();
                default:
                    break;
            }
        }
        setDefaultTimeout();
        return webDriver;
    }

    /*
     * Helper method to set ChromeDriver location into the right system property
     */
    private static void setChromeDriver() {

        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        String chromeBinary = Constants.chromeDriver + (os.equals("win") ? ".exe" : "");
        System.setProperty("webdriver.chrome.driver", chromeBinary);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Shutdown Hook is running !");
            }
        });
    }

    private static void setFirefoxDriver() {
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        String fireFoxBinary = Constants.fireFoxDriver + (os.equals("win") ? ".exe" : "");
        System.setProperty("webdriver.gecko.driver", fireFoxBinary);
    }

    private static void setEDGEDriver() {
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        String edgeBinary = Constants.edgeDriver + (os.equals("win") ? ".exe" : "");
        System.setProperty("webdriver.edge.driver", edgeBinary);
    }

    private static void isSupportedPlatform(String browser) {
        boolean is_supported = true;
        Platform current = Platform.getCurrent();
        if (INTERNET_EXPLORER.equals(browser)) {
            is_supported = Platform.WINDOWS.is(current);
        } else if (SAFARI.equals(browser)) {
            is_supported = Platform.MAC.is(current) || Platform.WINDOWS.is(current);
        }
        assert is_supported : "Platform is not supported by " + browser.toUpperCase() + " browser";
    }

    public static String setBrowser(String browser) {
        currentBrowser = browser;
        return currentBrowser;
    }

    public static void setDefaultTimeout() {
        webDriver.manage().timeouts().implicitlyWait(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    public static String getCurrentBrowser() {
        return currentBrowser;
    }

    private static void addCommonCaps(DesiredCapabilities caps) {
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.SEVERE);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        caps.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, false);
    }

    public static void setBrowserSettings() {
        webDriver.manage().window().maximize();
        webDriver.manage().getCookies();
        webDriver.manage().timeouts().pageLoadTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

}
